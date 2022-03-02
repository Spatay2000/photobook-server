package kz.masa.photobook.photobookserver.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "file_storage")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class FileStorage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private Long size;

    @Column(name = "file_extension")
    private String fileExtension;

    @Column(name = "bucket")
    private String bucket;

    @Column(name = "storage_url")
    private String storageUrl;
}
