package br.com.sysmap;

import br.com.sysmap.driver.Api;
import br.com.sysmap.driver.Browser;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Projeto {

    // Letra A
    @BeforeAll
    static void inicio(){
        Browser.abrirSite("https://openweathermap.org/");
    }

    //Testes

    //CT1 - Consultar Cidade
    @Test
    @Order(1)
    void consultarCidade() throws InterruptedException{
        String cidade = "Barueri";
        //Step1
        Browser.digitar((By.xpath("//*[@class='search-container']//input[@placeholder='Search city']")),cidade);
        //Step2
        Browser.clicar(By.xpath("//div[@class='search']//button[@class='button-round dark']"));

        //Step3
        validarNomeCidade(cidade);

        System.out.println("Validando Caso de Teste 1 ");
    }
    @Test
    @Order(2)
    void escolhaCidade(){
        Browser.clicar(By.xpath("//ul[@class='search-dropdown-menu']//li/span[text()='Barueri, BR ']"));
    }

    //CT2 - Validar se a temperatura em grau Celsius
    @Test
    @Order(3)
    void validaCelsius(){

        //Step 1
        Browser.clicar(By.xpath("//div[@class='switch-container']//div[text()='Metric: °C, m/s']"));
        //Step 2
        String tempSite = Browser.getTexto(By.xpath("//div[@class='current-temp']//span[@class='heading']"));


        System.out.println(tempSite);
    }

    void validarNomeCidade(String cidade){
        String valorApi = Api.currentWeather(cidade);
        assertEquals(cidade,valorApi);
        System.out.printf("Validando que retornou o valor da cidade: %s solicitada\n", valorApi);

    }

//    @AfterAll
//    static void fim(){
//    Browser.fecharSite();
//    }
}
