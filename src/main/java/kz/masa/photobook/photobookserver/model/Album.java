package kz.masa.photobook.photobookserver.model;

import kz.masa.photobook.photobookserver.enums.AlbumStatus;
import kz.masa.photobook.photobookserver.enums.AlbumType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "album")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Album extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(name = "album_type")
    @Enumerated(EnumType.STRING)
    private AlbumType albumType;

    @Column(name = "album_status")
    @Enumerated(EnumType.STRING)
    private AlbumStatus albumStatus;

    @Column(name = "published_date")
    private LocalDateTime publishedDate;

    @Column(name = "author_email")
    private String authorEmail;

    @ManyToOne
    @JoinColumn(name="author_email", nullable=false, insertable = false, updatable = false)
    private User author;
}
