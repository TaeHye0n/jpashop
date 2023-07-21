package jpabook.jpashop.web;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {

    private Long id; //상품 수정을 위해 필요
    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;
}
