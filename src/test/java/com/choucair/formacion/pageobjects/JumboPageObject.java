package com.choucair.formacion.pageobjects;


import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DefaultUrl("https://www.tiendasjumbo.co")
public class JumboPageObject extends PageObject {

    @FindBy(xpath = "//button[@class='red change-store']")
    WebElementFacade btnMetoEntrega;

    @FindBy(xpath = "//a[@class='select-recoge-en-tienda']//*[@class='delivery__option--svg']")
    WebElementFacade btnRecogeTienda;

    @FindBy (xpath = "//*[@id='select-department-container']/child::span")
    WebElementFacade btnDepartamento;

    @FindBy (xpath = "//*[@id='select-city-container']/child::span")
    WebElementFacade btnCiudad;

    @FindBy (xpath = "(//span[@class='select2-selection__rendered'])[3]")
    WebElementFacade btnTienda;

    @FindBy (xpath = "//*[@id='confirmation__store__selection']")
    WebElementFacade btnGuardar;

        @FindBy (xpath = "//span[contains(text(),'CATEGORÍAS')]")
    WebElementFacade btnCategorias;

    @FindBy (xpath = "//body/div/div/div/ul/li[1]/span[1]/a[1]/span[1]")
    WebElementFacade btnSupermercado;

    @FindBy (xpath = "//span[contains(text(),'Despensa')]")
    WebElementFacade btnDespensa;

    @FindBy (xpath = "//span[@class='selection']")
    WebElementFacade btnOrdenarPor;

    @FindBy (css = "li.select2-results__option:nth-of-type(4)")
    WebElementFacade btnMenorPrecio;

    @FindBy (xpath = "//button[@class='product-item__add-to-cart product-add-to-cart btn red add-to-cart']")
    List<WebElementFacade> btnComprar;

    @FindBy (xpath = "//*[@id='footer']/div[1]/div[1]")
    WebElementFacade btnCerrar;

    @FindBy (xpath = "//button[@class='btn red minicart__action--toggle-open']")
    WebElementFacade btnItem;

    @FindBy (xpath = "//div[@class='center']//a[@class='btn primary minicart__action minicart__action--buy']")
    WebElementFacade btnFinalizarCarrito;

    @FindBy (xpath = "//div[@class='total-prices-wrapper']//div[2]//div[2]")
    WebElementFacade btnValorTotal;

    @FindBy (xpath = "//div//span[@class='terms-text']")
    WebElementFacade terminosycondiciones;

    @FindBy (xpath = "//button[@class='btn primary go-to-checkout']")
    WebElementFacade finalizarCompra;




    public void metodoEntrega() {
        btnMetoEntrega.click();
        btnRecogeTienda.click();
    }

    public void departamento (String departamento) {
        Actions actdepart = new Actions(getDriver());
        waitFor(btnDepartamento).waitUntilEnabled();
        actdepart.moveToElement(btnDepartamento).click().perform();
       actdepart.moveToElement($("//ul[@class='select2-results__options']//li[text()='"+departamento+"']")).click().perform();

    }
        public void ciudad(String ciudad){
            Actions actciudad = new Actions(getDriver());
            waitFor(btnCiudad).waitUntilEnabled();
            actciudad.moveToElement(btnCiudad).click().perform();
            actciudad.moveToElement($("//span[@class='select2-results']/ul/li[text()='"+ciudad+"']")).click().perform();
    }
    public void tienda(String tienda) {

        Actions acttienda = new Actions(getDriver());
        waitFor(btnTienda).waitUntilEnabled();
        acttienda.moveToElement(btnTienda).click().perform();
        acttienda.moveToElement($("//span[@class='select2-results']/ul/li[text()='"+ tienda + "']")).click().perform();
    }

    public void formularioGuardar() {
        btnGuardar.click();
        waitFor(5).second();

    }
    public void despensa() {
        Actions actdespensa = new Actions(getDriver());
        waitFor(5).second();
       List<WebElement> div = getDriver().findElements(By.xpath("//li[@class='despensa']//li"));
        int count = div.size();
        btnCerrar.click();
        for (int i = 1 ; i <= count; i++) {
            if (i !=1 ) {

            actdespensa.moveToElement($(btnCategorias)).perform();
            actdespensa.moveToElement($(btnSupermercado)).perform();
            actdespensa.moveToElement($(btnDespensa)).perform();
        // $("li.despensa li:nth-of-type("+i+") a").waitUntilClickable().click();
            waitFor(5).second();
                       actdespensa.moveToElement($("/html[1]/body[1]/div[20]/div[2]/div[1]/ul[1]/li[1]/ul[2]/li[1]/div[2]/ul[1]/li["+i+"]")).click().perform();
            waitFor(7).second();
            btnOrdenarPor.click();
            btnMenorPrecio.click();
            waitFor(7).second();
            btnComprar.get(0).waitUntilClickable().click();
            waitFor(10).second();
            }else {
                actdespensa.moveToElement($(btnCategorias)).perform();
                actdespensa.moveToElement($(btnSupermercado)).perform();
                actdespensa.moveToElement($(btnDespensa)).perform();
                waitFor(5).second();
                actdespensa.moveToElement($("/html[1]/body[1]/div[18]/div[2]/div[1]/ul[1]/li[1]/ul[2]/li[1]/div[2]/ul[1]/li["+i+"]")).click().perform();
                waitFor(7).second();
                btnOrdenarPor.click();
                btnMenorPrecio.click();
                waitFor(7).second();
                btnComprar.get(0).waitUntilClickable().click();
                waitFor(10).second();

            }
        }

    }

    public void precio(String precio) {
        Actions actvalidar = new Actions(getDriver());
        int precioIngresado = Integer.parseInt(precio);
        String valor = btnValorTotal.getText();
        int tamañoVal = valor.length();
        String valortotal = valor.replace(".","");
        String valorfinal = valortotal.substring(1,tamañoVal-1);
        int preciototal = Integer.parseInt(valorfinal);
            if (preciototal<=precioIngresado){
                waitFor(3).second();
                actvalidar.moveToElement($(terminosycondiciones)).click().perform();
                waitFor(3).second();
                actvalidar.moveToElement($(finalizarCompra)).click().perform();
            } else {
                SoftAssertions softAssertions = new SoftAssertions();
                softAssertions.fail("No existe caso");
                softAssertions.assertAll();
                getDriver().close();
            }
    }

    public void validar() {

        Actions actvalidar = new Actions(getDriver());
        actvalidar.moveToElement($(btnItem)).click().perform();
        waitFor(5).second();
        actvalidar.moveToElement($(btnFinalizarCarrito)).click().perform();
    }




}



