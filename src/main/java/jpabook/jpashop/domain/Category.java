package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

  @Id
  @GeneratedValue
  @Column(name = "category_id")
  private Long id;

  private String name;

  @ManyToMany // 실무에선 쓰지 않음. 연습용
  @JoinTable(name = "category_item", joinColumns = @JoinColumn(name = "category_id"))
  private List<Item> items = new ArrayList<>();
}
