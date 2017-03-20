package model.core;

import java.util.UUID;

/**
 * Domain model can also have some functionality comparing to simple Data transfer object.
 */
public class DomainModel {

  private UUID merchantId;
  private String cardBrand;

  public UUID getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(UUID merchantId) {
    this.merchantId = merchantId;
  }

  public String getCardBrand() {
    return cardBrand;
  }

  public void setCardBrand(String cardBrand) {
    this.cardBrand = cardBrand;
  }
}
