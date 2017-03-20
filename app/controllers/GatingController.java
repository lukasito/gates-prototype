package controllers;

import com.google.common.collect.ImmutableMap;
import controllers.gate.AllowTypeGate;
import controllers.gate.MandatoryMerchantGate;
import controllers.gate.api.Gate;
import controllers.gate.api.Gate.GatingContext;
import controllers.gate.api.GateName;
import play.mvc.Controller;
import play.mvc.Http;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.function.Function.identity;

public abstract class GatingController extends Controller {

  private static Pattern versionPattern = Pattern.compile("^application\\/vnd.company.v(\\d+)\\+.*");

  private Map<GateName, Gate> requestGates = ImmutableMap.<GateName, Gate>builder()
    .put(GateName.ALLOW_TYPE, new AllowTypeGate())
    .put(GateName.REMOVED_MERCHANT_ID, new MandatoryMerchantGate(new AllowTypeGate()))
    .build();

  private Map<GateName, Gate> responseGates = ImmutableMap.<GateName, Gate>builder()
    .put(GateName.ALLOW_TYPE, new AllowTypeGate())
    .put(GateName.REMOVED_MERCHANT_ID, new MandatoryMerchantGate(new AllowTypeGate()))
    .build();

  protected void requestGating(GatingContext gatingContext) {
    String header = request().getHeader(Http.HeaderNames.ACCEPT);
    Matcher matcher = versionPattern.matcher(header);
    if (matcher.matches()) {
      String version = matcher.group(1);
      GateName.from(version)
        .map(requestGates::get)
        .map(Gate::asInGate)
        .flatMap(identity())
        .ifPresent(gate -> gate.acceptIn(gatingContext));
    }
  }

  protected void responseGating(GatingContext gatingContext) {
    String header = request().getHeader(Http.HeaderNames.ACCEPT);
    Matcher matcher = versionPattern.matcher(header);
    if (matcher.matches()) {
      String version = matcher.group(1);
      GateName.from(version)
        .map(responseGates::get)
        .map(Gate::asOutGate)
        .flatMap(identity())
        .ifPresent(gate -> gate.acceptOut(gatingContext));
    }
  }

  protected String responseContentType() {
    return request().getHeader(Http.HeaderNames.ACCEPT);
  }
}
