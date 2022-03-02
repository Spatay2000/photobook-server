package kz.masa.photobook.photobookserver.enums;

public enum AlbumType {
    PHOTO_ALBUM("PHOTO_ALBUM"),
    DOCUMENT_ALBUM("DOCUMENT_ALBUM");

    private String name;

    public String getName() {
        return name;
    }

    AlbumType(String name){
        this.name = name;
    }
}

