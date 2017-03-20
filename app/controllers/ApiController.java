package controllers;

import controllers.gate.context.ApiRequestContext;
import controllers.gate.context.ApiResponseContext;
import model.ApiRequestModel;
import model.ApiResponseModel;
import model.core.DomainModel;
import play.mvc.Result;
import play.mvc.Security;

import javax.xml.bind.JAXB;
import javax.xml.transform.dom.DOMSource;
import java.io.StringWriter;
import java.util.UUID;

@Security.Authenticated(Authenticator.class)
public class ApiController extends GatingController {

  public Result apiRequest() {
    ApiRequestModel requestModel = bodyAs(ApiRequestModel.class);

    // Request compatibility layer
    gating(new ApiRequestContext(requestModel));

    // CORE translation after normalized model (version unspecific)
    DomainModel domainModel = new DomainModel();
    domainModel.setCardBrand(requestModel.getCardBrand());
    domainModel.setMerchantId(UUID.fromString(requestModel.getMerchantId()));

    // do some business logic....
    // ...
    // done.

    // CORE translation to response model (version unspecific)
    ApiResponseModel apiResponseModel = new ApiResponseModel();
    apiResponseModel.setCardBrand(domainModel.getCardBrand());
    apiResponseModel.setMerchantId(domainModel.getMerchantId().toString());

    // Response compatibility layer
    gating(new ApiResponseContext(apiResponseModel));

    return toXml(apiResponseModel);
  }

  private static <T> T bodyAs(Class<T> klass) {
    return JAXB.unmarshal(new DOMSource(request().body().asXml()), klass);
  }

  private Result toXml(Object body) {
    StringWriter xml = new StringWriter();
    JAXB.marshal(body, xml);
    return ok(xml.toString()).as(responseContentType());
  }
}
