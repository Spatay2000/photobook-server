package kz.masa.photobook.photobookserver.model;

import com.nimbusds.jose.crypto.impl.CompositeKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "file_storage_album")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@IdClass(FileStorageAlbumId.class)
public class FileStorageAlbum {

    @Id
    @Column(name = "file_storage_id")
    private Long fileStorageId;

    @Id
    @Column(name = "album_id")
    private Long albumId;

    @ManyToOne
    @JoinColumn(name = "file_storage_id", nullable=false, insertable = false, updatable = false)
    private FileStorage fileStorage;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable=false, insertable = false, updatable = false)
    private Album album;
}
