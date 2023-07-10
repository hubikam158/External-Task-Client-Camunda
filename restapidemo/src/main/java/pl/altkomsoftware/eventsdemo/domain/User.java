package pl.altkomsoftware.eventsdemo.domain;

import lombok.Data;
import pl.altkomsoftware.eventsdemo.utils.AbstractEntity;

import javax.persistence.Entity;

@Data
@Entity
public class User extends AbstractEntity {

    private String firstName;
    private String lastName;
    private String userEmail;

    @Override
    public String toString() {
        return "User{" +
                "id= " + getId() +
                ", createDate= " + getCreateDate() +
                ", modDate= " + getModificationDate() +
                ", status= " + getStatus() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + userEmail + '\'' +
                '}';
    }
}
