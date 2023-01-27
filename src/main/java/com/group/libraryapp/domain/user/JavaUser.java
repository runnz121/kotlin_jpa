package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.user.loanhistory.JavaUserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class JavaUser {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;


  @Column(nullable = false)
  private String name;

  private Integer age;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private final List<JavaUserLoanHistory> userLoanHistories = new ArrayList<>();

  public JavaUser() {

  }

  public JavaUser(String name, Integer age) {
    if (name.isBlank()) {
      throw new IllegalArgumentException("이름은 비어 있을 수 없습니다");
    }
    this.name = name;
    this.age = age;
  }

  public void updateName(String name) {
    this.name = name;
  }

  public void loanBook(Book book) {
    this.userLoanHistories.add(new JavaUserLoanHistory(this, book.getName(), false));
  }

  public void returnBook(String bookName) {
    JavaUserLoanHistory targetHistory = this.userLoanHistories.stream()
        .filter(history -> history.getBookName().equals(bookName))
        .findFirst()
        .orElseThrow();
    targetHistory.doReturn();
  }


  @NotNull
  public String getName() {
    return name;
  }


  @Nullable//코틀린이 알 수 있게 null 올 수 있음을 명시(jet.brains 패키지 적용)
  public Integer getAge() {
    return age;
  }

  public Long getId() {
    return id;
  }

}
