package controllers.gate.context;

import controllers.gate.api.Gate.GatingContext;
import model.ApiRequestModel;

public class ApiRequestContext implements GatingContext {

  private final ApiRequestModel requestModel;

  public ApiRequestContext(ApiRequestModel requestModel) {
    this.requestModel = requestModel;
  }

  public ApiRequestModel getRequestModel() {
    return requestModel;
  }
}
