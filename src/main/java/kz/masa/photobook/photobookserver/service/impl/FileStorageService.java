package kz.masa.photobook.photobookserver.service.impl;

import kz.masa.photobook.photobookserver.config.MinioConfProperties;
import kz.masa.photobook.photobookserver.model.FileStorage;
import kz.masa.photobook.photobookserver.repository.FileStorageRepository;
import kz.masa.photobook.photobookserver.service.IFileStorageService;
import kz.masa.photobook.photobookserver.util.minio.MinioUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class FileStorageService implements IFileStorageService {

    private final String minioPrefixUrl = "fileStorage";

    private final FileStorageRepository fileStorageRepository;

    private final MinioUtil minioService;

    private final MinioConfProperties minioProperties;

    @Override
    public FileStorage getFileStorageById(Long id) {
        return fileStorageRepository.findFileStorageByIdAndDeletedAtIsNull(id);
    }

    @Override
    public List<FileStorage> getAllFileStorages() {
        return fileStorageRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public List<FileStorage> getAllFileStoragesIn(List<Long> idList) {
        return fileStorageRepository.findAllByIdInAndDeletedAtIsNull(idList);
    }

    @Override
    public FileStorage addFileStorage(MultipartFile file) {
        FileStorage fileStorage = new FileStorage();
        fileStorage.setName(file.getName());
        fileStorage.setSize(file.getSize());
        fileStorage.setFileExtension(file.getContentType());
        fileStorage.setBucket(minioProperties.getBucket());
        fileStorage = fileStorageRepository.saveAndFlush(fileStorage);

        minioService.putObject(minioProperties.getBucket(), file, minioPrefixUrl + "/" + fileStorage.getId(), fileStorage.getFileExtension());
        fileStorage.setStorageUrl(minioProperties.getUrl() + "/" + minioProperties.getBucket() + "/" + minioPrefixUrl + "/" + fileStorage.getId());
        return fileStorageRepository.saveAndFlush(fileStorage);
    }

    @Override
    public void removeFileStorageById(Long id) {
        FileStorage fileStorage = fileStorageRepository.findFileStorageByIdAndDeletedAtIsNull(id);
        fileStorage.setDeletedAt(Timestamp.valueOf(LocalDateTime.now()));
        fileStorageRepository.saveAndFlush(fileStorage);
    }
}
