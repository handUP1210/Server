package domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@RequiredArgsConstructor
@Getter
public class User {

    @Id @GeneratedValue
    private Long id; // PK

    @NotBlank
    private String name;

    private int phoneNumber;

    @Embedded
    private Address address;
}
