package kz.masa.photobook.photobookserver.repository;

import kz.masa.photobook.photobookserver.enums.AlbumStatus;
import kz.masa.photobook.photobookserver.enums.AlbumType;
import kz.masa.photobook.photobookserver.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    Optional<Album> findByIdAndDeletedAtIsNull(Long id);
    Album findAlbumByIdAndDeletedAtIsNull(Long id);
    List<Album> findAllByDeletedAtIsNull();
    List<Album> findAllByAlbumStatusAndDeletedAtIsNull(AlbumStatus albumStatus);
    List<Album> findAllByAlbumStatusAndAuthorEmailAndDeletedAtIsNull(AlbumStatus albumStatus, String email);
}
