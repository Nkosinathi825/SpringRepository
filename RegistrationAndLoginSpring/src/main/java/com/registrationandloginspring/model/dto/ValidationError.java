package com.registrationandloginspring.model.dto;

import java.util.Objects;

public final class ValidationError {

  private final String field;
  private final String message;

  public ValidationError(String field, String message) {
    this.field = field;
    this.message = message;
  }

  public String getField() {
    return field;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null || getClass() != object.getClass()) return false;
    ValidationError that = (ValidationError) object;
    return Objects.equals(field, that.field) && Objects.equals(message, that.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(field, message);
  }
}
