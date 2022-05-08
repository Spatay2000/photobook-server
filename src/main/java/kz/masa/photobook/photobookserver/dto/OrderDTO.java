package kz.masa.photobook.photobookserver.dto;

import kz.masa.photobook.photobookserver.model.Album;
import kz.masa.photobook.photobookserver.model.FileStorage;
import kz.masa.photobook.photobookserver.model.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class OrderDTO {
    private Long id;
    private Long albumId;
    private Album album;
    private String phoneNumber;
    private String address;
    private Integer quantity;
    private Long receiptId;
    private FileStorage receipt;
    private User author;
}
