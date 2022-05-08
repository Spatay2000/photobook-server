package kz.masa.photobook.photobookserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileStorageAlbumId implements Serializable {

    private Long fileStorageId;
    private Long albumId;

}
