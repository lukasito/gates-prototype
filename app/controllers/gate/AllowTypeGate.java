package controllers.gate;

import controllers.gate.api.Gate;
import controllers.gate.api.GatingException;
import controllers.gate.context.ApiRequestContext;
import model.ApiRequestModel;

/**
 * Legacy functionality from v1 where 'type' was part of an API
 */
public class AllowTypeGate implements Gate<ApiRequestContext> {

  @Override
  public void accept(ApiRequestContext apiRequestContext) {
    ApiRequestModel requestModel = apiRequestContext.getRequestModel();

    if (requestModel.getType() == null) {
      throw new GatingException("Missing type!");
    }

    if (requestModel.getType() != null) {
      requestModel.setCardBrand(requestModel.getType());
    }
  }
}
