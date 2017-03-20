package controllers.gate.api;

import java.util.Optional;
import java.util.stream.Stream;

public enum GateName {

  ALLOW_TYPE("18032017"),
  REMOVED_MERCHANT_ID("19032017");


  private final String versionNumber;

  GateName(String versionNumber) {
    this.versionNumber = versionNumber;
  }

  public static Optional<GateName> from(String versionNumber) {
    return Stream.of(values())
      .filter(name -> name.versionNumber.equals(versionNumber))
      .findFirst();
  }
}
