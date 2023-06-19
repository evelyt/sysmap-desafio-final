package br.com.sysmap.driver;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    //System.setProperty("webdriver.chrome.driver","C:\\dev\\chromedriver.exe");
    private static final String DRIVER_CHROME = "C:\\dev\\chromedriver.exe";
    static WebDriver driver;

    public static void abrirSite(String url){
        System.setProperty("webdriver.chrome.driver", DRIVER_CHROME);
        driver = new ChromeDriver();
        driver.get(url);
        System.out.println("Navegador Aberto");
    }

    public static void fecharSite(){
        driver.quit();
    }

}
