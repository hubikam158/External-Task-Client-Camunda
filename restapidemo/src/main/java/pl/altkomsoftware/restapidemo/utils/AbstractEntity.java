package pl.altkomsoftware.restapidemo.utils;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private ZonedDateTime createDate;
    private ZonedDateTime modificationDate;
    private Status status;

    @PrePersist
    private void createDateMethod() {
        createDate = ZonedDateTime.now();
    }

    @PreUpdate
    private void updateDate() {
        modificationDate = ZonedDateTime.now();
    }
}
