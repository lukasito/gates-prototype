package controller;

import org.junit.BeforeClass;
import org.junit.Test;
import play.libs.ws.WS;
import play.libs.ws.WSClient;
import play.test.Helpers;
import play.test.WithApplication;

public class ApiControllerTest extends WithApplication {

  private static WSClient wsClient;

  @BeforeClass
  public static void setup() {
    wsClient = WS.newClient(Helpers.testServer().port());
  }

  @Test
  public void thatV1Works() {
    wsClient.url("/index")
      .setHeader("Authorization", "valid")
      .post(
        "<api-model>\n" +
          "<merchant-id>15541d01-0937-4330-8435-af4de14719b1</merchant-id>\n" +
          "<type>visa</type>\n" +
        "</<api-model>"
      );
  }
}
