package controllers.gate;

import controllers.gate.api.Gate.InOutGate;
import controllers.gate.api.GatingException;
import controllers.gate.context.ApiRequestContext;
import controllers.gate.context.ApiResponseContext;
import model.ApiRequestModel;
import model.ApiResponseModel;

/**
 * Legacy functionality from v1 where 'type' was part of an API
 */
public class AllowTypeGate implements InOutGate<ApiRequestContext, ApiResponseContext> {

  @Override
  public void acceptIn(ApiRequestContext ctx) {
    ApiRequestModel requestModel = ctx.getRequestModel();

    if (requestModel.getType() == null) {
      throw new GatingException("Missing type!");
    }

    if (requestModel.getType() != null) {
      requestModel.setCardBrand(requestModel.getType());
    }
  }

  @Override
  public void acceptOut(ApiResponseContext ctx) {
    ApiResponseModel responseModel = ctx.responseModel();
    responseModel.setType(responseModel.getCardBrand());
    responseModel.setCardBrand(null);
  }
}
