package com.billbasher.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserAlreadyExistsException extends RuntimeException {
  private String message;

  public UserAlreadyExistsException(String message) {
    super(message);
    this.message = message;
  }
}
