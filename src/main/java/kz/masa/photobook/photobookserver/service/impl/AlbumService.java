package kz.masa.photobook.photobookserver.service.impl;

import kz.masa.photobook.photobookserver.dto.AlbumDTO;
import kz.masa.photobook.photobookserver.enums.AlbumStatus;
import kz.masa.photobook.photobookserver.enums.AlbumType;
import kz.masa.photobook.photobookserver.mapper.AlbumMapper;
import kz.masa.photobook.photobookserver.model.Album;
import kz.masa.photobook.photobookserver.model.FileStorage;
import kz.masa.photobook.photobookserver.model.FileStorageAlbum;

import kz.masa.photobook.photobookserver.repository.AlbumRepository;
import kz.masa.photobook.photobookserver.repository.FileStorageAlbumRepository;
import kz.masa.photobook.photobookserver.repository.UserRepository;
import kz.masa.photobook.photobookserver.service.IAlbumService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlbumService implements IAlbumService {

    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;
    private final FileStorageAlbumRepository fileStorageAlbumRepository;

    private final AlbumMapper albumMapper;

    private final FileStorageService fileStorageService;


    @Override
    public AlbumDTO getAlbumById(Long id) {
        AlbumDTO response = albumMapper.entityToApi(albumRepository.findAlbumByIdAndDeletedAtIsNull(id));
        response.setFileStorages(fileStorageService.getAllFileStoragesIn(fileStorageAlbumRepository.findAllByAlbumId(id).stream().map(FileStorageAlbum::getFileStorageId).collect(Collectors.toList())));
        return response;
    }

    @Override
    public List<AlbumDTO> getAllAlbums() {
        return albumRepository.findAllByDeletedAtIsNull().stream().map(album -> {
            AlbumDTO albumDTO = albumMapper.entityToApi(album);
            albumDTO.setFileStorages(fileStorageService.getAllFileStoragesIn(fileStorageAlbumRepository.findAllByAlbumId(album.getId()).stream().map(FileStorageAlbum::getFileStorageId).collect(Collectors.toList())));
            return albumDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<AlbumDTO> getAllFiltered(AlbumStatus albumStatus, String email, boolean currentUser) {
        List<Album> result;
        if (currentUser) {
            result = albumRepository.findAllByAlbumStatusAndAuthorEmailAndDeletedAtIsNull(albumStatus, email);
        } else {
            result = albumRepository.findAllByAlbumStatusAndDeletedAtIsNull(albumStatus);
        }
        return result.stream().map(album -> {
            AlbumDTO albumDTO = albumMapper.entityToApi(album);
            albumDTO.setFileStorages(fileStorageService.getAllFileStoragesIn(fileStorageAlbumRepository.findAllByAlbumId(album.getId()).stream().map(FileStorageAlbum::getFileStorageId).collect(Collectors.toList())));
            return albumDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public AlbumDTO publishAlbum(Album album, String email) {
        Album publishAlbum;
        if (album.getId() == null) {
            publishAlbum = albumMapper.apiToEntity(this.createAlbum(album, email));
        } else {
            publishAlbum = albumMapper.apiToEntity(this.updateAlbum(album, email));
        }

        publishAlbum.setAlbumStatus(AlbumStatus.PUBLISHED);
        publishAlbum.setPublishedDate(LocalDateTime.now());
        albumRepository.save(publishAlbum);
        return this.getAlbumById(album.getId());
    }

    @Override
    public AlbumDTO draftAlbum(Album album, String email) {
        Album draftAlbum;
        if (album.getId() == null) {
            draftAlbum = albumMapper.apiToEntity(this.createAlbum(album, email));
        } else {
            draftAlbum = albumMapper.apiToEntity(this.updateAlbum(album, email));
        }

        draftAlbum.setAlbumStatus(AlbumStatus.DRAFT);
        draftAlbum.setPublishedDate(LocalDateTime.now());
        albumRepository.save(draftAlbum);
        return this.getAlbumById(album.getId());
    }

    @Override
    public AlbumDTO createAlbum(Album album, String email) {

        album.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        album.setAlbumStatus(AlbumStatus.DRAFT);
        album.setAuthorEmail(email);

        album = albumRepository.save(album);
        userRepository.findByEmail(email).ifPresent(album::setAuthor);

        return albumMapper.entityToApi(album);
    }

    @Override
    public AlbumDTO updateAlbum(Album album, String email) {
        albumRepository.findByIdAndDeletedAtIsNull(album.getId()).ifPresent(album1 -> {
            if (email.equals(album1.getAuthorEmail())) {

                album1.setTitle(album.getTitle());
                album1.setDescription(album.getDescription());
                album1.setAlbumType(album.getAlbumType());

                album1.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
                albumRepository.save(album1);
            }
        });

        return this.getAlbumById(album.getId());
    }

    @Override
    public List<FileStorageAlbum> uploadFiles(List<MultipartFile> multipartFiles, Long albumId) {
        List<FileStorageAlbum> fileStorageAlbums = new ArrayList<>();
        multipartFiles.forEach(multipartFile -> {
            FileStorage fileStorage = fileStorageService.addFileStorage(multipartFile);

            FileStorageAlbum fileStorageAlbum = new FileStorageAlbum();
            fileStorageAlbum.setAlbumId(albumId);
            fileStorageAlbum.setFileStorageId(fileStorage.getId());
            fileStorageAlbums.add(fileStorageAlbum);
        });

        return fileStorageAlbumRepository.saveAllAndFlush(fileStorageAlbums);
    }



    @Override
    public void deleteAlbumById(Long id) {
        Album albumEntity = albumRepository.findAlbumByIdAndDeletedAtIsNull(id);
        albumEntity.setDeletedAt(Timestamp.valueOf(LocalDateTime.now()));
        albumRepository.save(albumEntity);
    }
}
