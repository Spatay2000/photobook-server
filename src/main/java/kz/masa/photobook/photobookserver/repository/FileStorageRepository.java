package kz.masa.photobook.photobookserver.repository;

import kz.masa.photobook.photobookserver.model.FileStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage, Long> {
    Optional<FileStorage> findByIdAndDeletedAtIsNull(Long id);
    FileStorage findFileStorageByIdAndDeletedAtIsNull(Long id);
    List<FileStorage> findAllByDeletedAtIsNull();
    List<FileStorage> findAllByIdInAndDeletedAtIsNull(List<Long> idList);
}
