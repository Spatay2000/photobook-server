package kz.masa.photobook.photobookserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    @JsonIgnore
    protected Timestamp createdAt;

    @Column(name = "updated_at")
    @JsonIgnore
    protected Timestamp updatedAt;

    @Column(name = "deleted_at")
    @JsonIgnore
    protected Timestamp deletedAt;
}
