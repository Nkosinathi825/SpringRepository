package com.registrationandloginspring.service;

import java.util.List;

public final class Result<T> {

  private final boolean successful;

  private final T data;

  private final List<ValidationError> errors;

  private Result(Boolean successful, T data, List<ValidationError> errors) {
    this.successful = successful;
    this.errors = errors;
    this.data = data;
  }

  public static <T> Result<T> success(T data) {
    return new Result<T>(true, data, null);
  }

  public static <T> Result<T> failure(List<ValidationError> errors) {
    return new Result<T>(false, null, errors);
  }

  public Boolean isSuccessful() {
    return successful;
  }

  public List<ValidationError> getErrors() {
    return errors;
  }

  public T getData() {
    return data;
  }
}
