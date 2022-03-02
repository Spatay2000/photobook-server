package kz.masa.photobook.photobookserver.repository;

import kz.masa.photobook.photobookserver.model.FileStorageAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileStorageAlbumRepository extends JpaRepository<FileStorageAlbum, Long> {

    List<FileStorageAlbum> findAllByAlbumId(Long albumId);
}
