package br.com.sysmap.driver;

import org.openqa.selenium.By;

import java.util.List;

public class Browser extends DriverFactory{
    public static boolean elementoExiste(By element){
        return driver.findElements(element).size() !=0;
    }
    public static void digitar(By element, String valor){
        driver.findElement(element).sendKeys(valor);
    }
    public static void clicar(By element){
        driver.findElement(element).click();
    }
    public  static String getTexto(By element){
      return driver.findElement(element).getText();
    }
    public static void aguardar(Integer segundos){
        try{
            Thread.sleep(segundos*2000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
