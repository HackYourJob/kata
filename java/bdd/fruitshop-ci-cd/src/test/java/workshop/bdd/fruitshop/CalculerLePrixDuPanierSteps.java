package workshop.bdd.fruitshop;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Percentage;
import workshop.bdd.fruitshop.domain.Cart;

public class CalculerLePrixDuPanierSteps {

    private Cart cart = new Cart();
    private double totalPrice;

    @Given("^un panier avec un \"([^\"]*)\"$")
    public void unPanierAvecUn(String productName) throws Throwable {
        cart.addProduct(productName, 1);
    }

    @And("^le prix du \"([^\"]*)\" est de \"([^\"]*)\"€$")
    public void lePrixDuEstDe€(String productName, String price) throws Throwable {
        cart.setProductPrice(productName, Double.valueOf(price));
    }

    @When("^je calcule prix du panier$")
    public void jeCalculePrixDuPanier() {
        this.totalPrice = cart.computeTotalPrice();
    }

    @Then("^le montant du panier est de \"([^\"]*)\"€$")
    public void leMontantDuPanierEstDe€(String basketTotalPrice) throws Throwable {
        leTotalEstDe€(basketTotalPrice);
    }

    @Given("^un panier avec une quantité de \"([^\"]*)\" \"([^\"]*)\"$")
    public void unPanierAvecUneQuantitéDe(String productQuantity, String productName) throws Throwable {
        cart.addProduct(productName, Integer.parseInt(productQuantity));
    }

    @And("^le prix du \"([^\"]*)\" étant de \"([^\"]*)\" €$")
    public void lePrixDuÉtantDe€(String productName, String productUnitPrice) throws Throwable {
        cart.setProductPrice(productName, Double.valueOf(productUnitPrice));
    }

    @When("^je calcule le prix du panier$")
    public void jeCalculeLePrixDuPanier() {
        this.totalPrice = cart.computeTotalPrice();
    }

    @Then("^le total est de \"([^\"]*)\" €$")
    public void leTotalEstDe€(String totalPrice) throws Throwable {
        Assertions.assertThat(this.totalPrice).isCloseTo(Double.valueOf(totalPrice), Percentage.withPercentage(1));
    }

    @And("^une quantité de \"([^\"]*)\" \"([^\"]*)\"$")
    public void uneQuantitéDe(String productQuantity, String productName) throws Throwable {
        unPanierAvecUneQuantitéDe(productQuantity, productName);
    }

    @And("^on applique une réduction de \"([^\"]*)\"% sur les \"([^\"]*)\"$")
    public void onAppliqueUneRéductionDeSurLes(String discountRatio, String productName) throws Throwable {
        cart.applyReduction(productName, Double.valueOf(discountRatio));
    }
}
