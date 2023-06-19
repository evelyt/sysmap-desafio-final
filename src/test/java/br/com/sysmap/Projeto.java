package br.com.sysmap;

import br.com.sysmap.driver.Api;
import br.com.sysmap.driver.Browser;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Projeto {

    String cidade = "Barueri";
    // Letra A
    @BeforeAll
    static void inicio(){
        Browser.abrirSite("https://openweathermap.org/");
    }

    @Test
    @Order(1)
    void validarSite(){
        boolean validate = Browser.elementoExiste(By.xpath(
                "//div[@class='search']//button[@class='button-round dark']"));
        assertTrue(validate);
        System.out.println("Validando se estamos no site");
    }


    //Testes

    //Letra B) CT1 - Consultar Cidade
    @Test
    @Order(2)
    void consultarCidade(){

        //Step1
        Browser.digitar((By.xpath("//*[@class='search-container']//input[@placeholder='Search city']")),cidade);
        Browser.aguardar(4);
        //Step2
        Browser.clicar(By.xpath("//div[@class='search']//button[@class='button-round dark']"));
        //Step3
        validarNomeCidade();

        System.out.println("--Validando Caso de Teste 1--");

    }
    @Test
    @Order(3)
    void escolhaCidade(){
        Browser.clicar(By.xpath("//ul[@class='search-dropdown-menu']//li/span[text()='Barueri, BR ']"));
        Browser.aguardar(2);
    }

    //Letra C) CT2 - Validar se a temperatura em grau Celsius
    @Test
    @Order(4)
    void validaCelsius(){
        //Step 1
        Browser.clicar(By.xpath("//div[@class='switch-container']//div[text()='Metric: °C, m/s']"));
        Browser.aguardar(2);
        //Step 2
        String tempSite = Browser.getTexto(By.xpath("//div[@class='current-temp']//span[@class='heading']"));
        //Step 3
        String valorApi = Api.tempResponse(cidade,"metric");
        int temp = Integer.parseInt(tempSite.replace("°C",""));
        Integer valor= (int) Math.round(Double.parseDouble(valorApi));
        //Step 4
        //Validando se as variáveis ‘tempSite’ e ‘valorApi’ são iguais em todos os caracteres
        System.out.println("Temperatura em Celsius");
        System.out.println((temp == valor) ? "Sao iguais!\nTemperatura: "+valor : "Sao diferentes!\nValores: tempSite="+temp+" valorApi"+valor);

        System.out.println("--Validando Caso de Teste 2--");

    }

    //Letra D) CT3 - Validar se a temperatura em grau Fahrenheit
    @Test
    @Order(5)
    void validaFahrenheit(){
        //Step 1
        Browser.clicar(By.xpath("//div[@class='switch-container']//div[text()='Imperial: °F, mph']"));
        Browser.aguardar(2);
        //Step 2
        String tempSite = Browser.getTexto(By.xpath("//div[@class='current-temp']//span[@class='heading']"));
        //Step 3
        String valorApi = Api.tempResponse(cidade,"imperial");
        int temp = Integer.parseInt(tempSite.replace("°F",""));
        Integer valor= (int) Math.round(Double.parseDouble(valorApi));
        //Step 4
        //Validando se as variáveis ‘tempSite’ e ‘valorApi’ são iguais em todos os caracteres.
        System.out.println("Temperatura em Fahrenheit");
        System.out.println((temp == valor)?"Sao iguais!\nTemperatura: "+valor : "Sao diferentes!\nValores: tempSite="+temp+" valorApi"+valor);

        System.out.println("--Validando Caso de Teste 3--");
    }
    void validarNomeCidade(){
        String valorApi = Api.currentWeather(cidade);
        assertEquals(cidade,valorApi);
        System.out.printf("Validando que retornou o valor da cidade: %s solicitada\n", valorApi);
    }
    @AfterAll
    static void fim(){
    Browser.fecharSite();
    }
}
