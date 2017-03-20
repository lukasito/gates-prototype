package controllers.gate.context;

import controllers.gate.api.Gate.GatingContext;
import model.ApiResponseModel;

public class ApiResponseContext implements GatingContext {

  private final ApiResponseModel responseModel;

  public ApiResponseContext(ApiResponseModel responseModel) {
    this.responseModel = responseModel;
  }

  public ApiResponseModel responseModel() {
    return responseModel;
  }
}
