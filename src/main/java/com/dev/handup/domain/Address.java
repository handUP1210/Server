package com.dev.handup.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Address {

    private String city;
    private String team;

    public Address() { // 변경 불가능 제약 조건

    }

    public Address(String city, String team) {
        this.city = city;
        this.team = team;
    }
}
