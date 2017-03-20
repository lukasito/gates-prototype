package controllers;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

public class Authenticator extends Security.Authenticator {
  @Override
  public String getUsername(Http.Context ctx) {
    return getUsernameFromHeader(ctx);
  }

  @Override
  public Result onUnauthorized(Http.Context context) {
    return super.onUnauthorized(context);
  }

  private String getUsernameFromHeader(Http.Context ctx) {
    String user = ctx.request().getHeader("Authorization");
    return "valid".equals(user) ? user : null;
  }
}
