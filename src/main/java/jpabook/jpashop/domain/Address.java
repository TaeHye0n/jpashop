package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    //JPA가 생성할 때 리플렉션, 프록시 기술을 써야하는데 이를 위해 기본 생성자 제공
    protected Address() {
    }

    //값 타입은 immutable 하게 설계되어야함, 생성할 떄만 값이 세팅
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
