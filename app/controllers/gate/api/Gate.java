package controllers.gate.api;

import play.mvc.Http;

import java.util.Optional;

public interface Gate {

  boolean isInGate();

  boolean isOutGate();

  @SuppressWarnings("unchecked")
  default <T extends GatingContext> Optional<InGate<T>> asInGate() {
    return isInGate() ? Optional.of((InGate<T>) this) : Optional.empty();
  }

  @SuppressWarnings("unchecked")
  default <R extends GatingContext> Optional<OutGate<R>> asOutGate() {
    return isOutGate() ? Optional.of((OutGate<R>) this) : Optional.empty();
  }

  interface GatingContext {
  }

  interface WithRequest {

    default Http.Request request() {
      return Http.Context.current().request();
    }
  }

  interface InGate<T extends GatingContext> extends Gate {

    void acceptIn(T ctx);

    @Override
    default boolean isInGate() {
      return true;
    }

    @Override
    default boolean isOutGate() {
      return false;
    }
  }

  interface OutGate<R extends GatingContext> extends Gate {

    void acceptOut(R ctx);

    @Override
    default boolean isInGate() {
      return false;
    }

    @Override
    default boolean isOutGate() {
      return true;
    }
  }

  interface InOutGate<T extends GatingContext, R extends GatingContext> extends InGate<T>, OutGate<R> {

    @Override
    default boolean isInGate() {
      return true;
    }

    @Override
    default boolean isOutGate() {
      return true;
    }
  }
}
