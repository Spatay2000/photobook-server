package kz.masa.photobook.photobookserver.service;

import kz.masa.photobook.photobookserver.dto.AlbumDTO;
import kz.masa.photobook.photobookserver.enums.AlbumStatus;
import kz.masa.photobook.photobookserver.enums.AlbumType;
import kz.masa.photobook.photobookserver.model.Album;
import kz.masa.photobook.photobookserver.model.FileStorageAlbum;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IAlbumService {
    AlbumDTO getAlbumById(Long id);
    List<AlbumDTO> getAllAlbums();
    List<AlbumDTO> getAllFiltered(AlbumStatus albumStatus);
    AlbumDTO publishAlbum(Album album, String email);
    AlbumDTO draftAlbum(Album album, String email);
    AlbumDTO createAlbum(Album album, String email);
    AlbumDTO updateAlbum(Album album, String email);
    List<FileStorageAlbum> uploadFiles(List<MultipartFile> multipartFiles, Long albumId);
    void deleteAlbumById(Long id);
}
