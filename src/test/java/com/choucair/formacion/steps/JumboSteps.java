package com.choucair.formacion.steps;

import com.choucair.formacion.pageobjects.JumboPageObject;
import com.opencsv.CSVReader;
import net.thucydides.core.annotations.Step;

import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

public class JumboSteps {

    JumboPageObject jumboPageObject;
    private static String[] datos;

    @Step

    public void paginacion() {
        jumboPageObject.open();
        jumboPageObject.metodoEntrega();

    }

    public static void leerCSV(String casoPrueba) {
        CSVReader reaader = null;
        try {
            String CSV_file = "src/test/resources/Datadriven/jumbo.csv";
            reaader = new CSVReader(new FileReader(CSV_file));
            String[] cell = reaader.readNext();
            while ((cell = reaader.readNext()) != null) {
                if (casoPrueba.equals(cell[0])) { //.trim() elimina los espacios a los lados
                    datos = cell;
                }
            }


        } catch (IOException e) {
            Logger.getLogger("" + e);
        }
    }
    public void llenarFormulario (String casoPrueba){
        leerCSV(casoPrueba);
        jumboPageObject.departamento(datos[1]);
        jumboPageObject.ciudad(datos[2]);
        jumboPageObject.tienda(datos[3]);
        jumboPageObject.formularioGuardar();


    }

    public void menu(){
        jumboPageObject.despensa();
    }

    public void validacion(){
    jumboPageObject.validar();
    jumboPageObject.precio(datos[4]);

    }

}


