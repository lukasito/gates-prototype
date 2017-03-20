package controllers;

import com.google.common.collect.ImmutableMap;
import controllers.gate.AllowTypeGate;
import controllers.gate.RemovedMerchantGate;
import controllers.gate.api.Gate;
import controllers.gate.api.Gate.GatingContext;
import controllers.gate.api.GateName;
import play.mvc.Controller;
import play.mvc.Http;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class GatingController extends Controller {

  private static Pattern versionPattern = Pattern.compile("^application\\/vnd.company.v(\\d+)");

  private Map<GateName, Gate<? extends GatingContext>> gates = ImmutableMap.<GateName, Gate<? extends GatingContext>>builder()
    .put(GateName.ALLOW_TYPE, new AllowTypeGate())
    .put(GateName.REMOVED_MERCHANT_ID, new RemovedMerchantGate(new AllowTypeGate()))
    .build();

  protected void gating(GatingContext gatingContext) {
    String header = request().getHeader(Http.HeaderNames.ACCEPT);
    Matcher matcher = versionPattern.matcher(header);
    if (matcher.matches()) {
      String version = matcher.group(1);
      GateName.from(version)
        .map(gates::get)
        .map(GatingController::narrow)
        .ifPresent(gate -> gate.accept(gatingContext));
    }
  }

  @SuppressWarnings("unchecked")
  private static Gate<GatingContext> narrow(Gate<? extends GatingContext> gate) {
    return (Gate<GatingContext>) gate;
  }

  protected String responseContentType() {
    return request().getHeader(Http.HeaderNames.ACCEPT);
  }
}
