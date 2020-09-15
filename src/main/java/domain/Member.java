package domain;

import lombok.RequiredArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@RequiredArgsConstructor
public class Member {

    @Id @GeneratedValue
    private Long id; // PK

    private String name;
    private int phoneNumber;

    @Embedded
    private Address address;
}
