package controllers.gate;

import controllers.gate.api.Gate.InOutGate;
import controllers.gate.api.GatingException;
import controllers.gate.context.ApiRequestContext;
import controllers.gate.context.ApiResponseContext;
import model.ApiRequestModel;

/**
 * Gate where field merchant-id is now removed
 */
public class MandatoryMerchantGate implements InOutGate<ApiRequestContext, ApiResponseContext> {

  private final InOutGate<ApiRequestContext, ApiResponseContext> previous;

  public MandatoryMerchantGate(InOutGate<ApiRequestContext, ApiResponseContext> previous) {
    this.previous = previous;
  }

  @Override
  public void acceptIn(ApiRequestContext ctx) {
    previous.acceptIn(ctx);
    ApiRequestModel requestModel = ctx.getRequestModel();
    if (requestModel.getMerchantId() == null) {
      throw new GatingException("Missing parameter: merchant-id");
    }
  }

  @Override
  public void acceptOut(ApiResponseContext ctx) {
    previous.acceptOut(ctx);
  }
}
