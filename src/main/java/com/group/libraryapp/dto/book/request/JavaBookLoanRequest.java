package com.group.libraryapp.dto.book.request;

public class JavaBookLoanRequest {

  private String userName;

  public JavaBookLoanRequest(String userName, String bookName) {
    this.userName = userName;
    this.bookName = bookName;
  }

  private String bookName;

  public String getUserName() {
    return userName;
  }

  public String getBookName() {
    return bookName;
  }

}
