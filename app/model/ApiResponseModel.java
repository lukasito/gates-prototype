package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlRootElement(name = "api-model")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApiResponseModel {

  // v1
  @XmlElement
  private String merchantId;

  // v1
  @XmlElement
  private String type;

  // v2: we want to change type to cardBrand
  @XmlElement(name = "card-brand")
  private String cardBrand;

  // v3: we want to remove merchantId from request model because its implicit


  public String getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(String merchantId) {
    this.merchantId = merchantId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getCardBrand() {
    return cardBrand;
  }

  public void setCardBrand(String cardBrand) {
    this.cardBrand = cardBrand;
  }
}
