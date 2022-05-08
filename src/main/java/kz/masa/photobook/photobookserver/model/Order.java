package kz.masa.photobook.photobookserver.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "album_order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "album_id", nullable = false)
    private Long albumId;

    @ManyToOne
    @JoinColumn(name="album_id", nullable=false, insertable = false, updatable = false)
    private Album album;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "receipt_id")
    private Long receiptId;

    @ManyToOne
    @JoinColumn(name = "receipt_id", referencedColumnName = "id", insertable = false, updatable = false)
    private FileStorage receipt;

    @Column(name = "author_email")
    private String authorEmail;

    @ManyToOne
    @JoinColumn(name="author_email", nullable=false, insertable = false, updatable = false)
    private User author;
}
