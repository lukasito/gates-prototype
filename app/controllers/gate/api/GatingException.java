package controllers.gate.api;

public class GatingException extends RuntimeException {

  public GatingException(String message) {
    super(message);
  }
}
