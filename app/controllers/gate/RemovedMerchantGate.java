package controllers.gate;

import controllers.gate.api.Gate;
import controllers.gate.api.Gate.RequestGating;
import controllers.gate.api.GatingException;
import controllers.gate.context.ApiRequestContext;
import model.ApiRequestModel;

/**
 * Gate where field merchant-id is now removed
 */
public class RemovedMerchantGate implements Gate<ApiRequestContext>, RequestGating {

  private final Gate<ApiRequestContext> previous;

  public RemovedMerchantGate(Gate<ApiRequestContext> previous) {
    this.previous = previous;
  }

  @Override
  public void accept(ApiRequestContext apiRequestContext) {
    previous.accept(apiRequestContext);
    ApiRequestModel requestModel = apiRequestContext.getRequestModel();
    if (requestModel.getMerchantId() != null) {
      throw new GatingException("Invalid parameter: merchant-id");
    } else {
      requestModel.setMerchantId(request().username());
    }
  }
}
