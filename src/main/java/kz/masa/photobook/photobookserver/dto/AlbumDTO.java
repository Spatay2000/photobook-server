package kz.masa.photobook.photobookserver.dto;

import kz.masa.photobook.photobookserver.enums.AlbumStatus;
import kz.masa.photobook.photobookserver.enums.AlbumType;
import kz.masa.photobook.photobookserver.model.FileStorage;
import kz.masa.photobook.photobookserver.model.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class AlbumDTO {
    private Long id;
    private String title;
    private String description;
    private AlbumType albumType;
    private AlbumStatus albumStatus;
    private LocalDateTime publishedDate;
    private String authorEmail;
    private User author;
    private List<FileStorage> fileStorages;
}
