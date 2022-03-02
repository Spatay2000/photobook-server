package kz.masa.photobook.photobookserver.service;

import kz.masa.photobook.photobookserver.model.FileStorage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileStorageService {
    FileStorage getFileStorageById(Long id);
    List<FileStorage> getAllFileStorages();
    List<FileStorage> getAllFileStoragesIn(List<Long> idList);
    FileStorage addFileStorage(MultipartFile file);
    void removeFileStorageById(Long id);
}
