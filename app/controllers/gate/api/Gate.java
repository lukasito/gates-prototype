package controllers.gate.api;

import play.mvc.Http;

import java.util.function.Consumer;

import static controllers.gate.api.Gate.GatingContext;

public interface Gate<T extends GatingContext> extends Consumer<T> {

  interface GatingContext {
  }

  interface RequestGating {

    default Http.Request request() {
      return Http.Context.current().request();
    }
  }
}
