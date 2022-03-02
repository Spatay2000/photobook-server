package kz.masa.photobook.photobookserver.enums;

public enum AlbumStatus {
    DRAFT("DRAFT"),
    PUBLISHED("PUBLISHED"),
    DELETED("DELETED");

    private String name;

    public String getName() {
        return name;
    }

    AlbumStatus(String name){
        this.name = name;
    }
}

