package com.choucair.formacion.definition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.choucair.formacion.steps.JumboSteps;
import net.thucydides.core.annotations.Steps;

public class JumboDefinitions {
    @Steps
    JumboSteps jumboSteps;
    String casoPrueba;


    @Given("^registramos los datos de entrega \"([^\"]*)\"$")
    public void registramos_los_datos_de_entrega(String id) {
        this.casoPrueba = id;
        jumboSteps.paginacion();
        jumboSteps.llenarFormulario(id);

    }

    @When("^seleccionamos los productos$")
    public void seleccionamos_los_productos() {
        jumboSteps.menu();

    }

    @Then("^validamos carrito de compras$")
    public void validamos_carrito_de_compras() {
        jumboSteps.validacion();

    }
}
