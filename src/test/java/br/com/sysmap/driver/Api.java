package br.com.sysmap.driver;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


public class Api {
    static String apiKey = "efc8e054dff34462aa9c53a2298458d1";
 //   @Override
    public static String getLatLon(String cidade) {

        String retorno = null;

        try(CloseableHttpClient client = HttpClients.createDefault()){

            HttpGet get = new HttpGet(
                    "http://api.openweathermap.org/geo/1.0/direct?q="+cidade+"&appid="+apiKey);
            HttpResponse response = client.execute(get);
            String resp = EntityUtils.toString(response.getEntity());
            System.out.println(resp);
            JSONObject obj = new JSONObject(resp.replace("[","").replace("]",""));
            System.out.println(obj.getBigDecimal("lat"));
            System.out.println(obj.getBigDecimal("lon"));
//            System.out.println(obj);

            retorno = "lat="
                    .concat(String.valueOf(obj.getBigDecimal("lat")))
                    .concat("&lon=")
                    .concat(String.valueOf(obj.getBigDecimal("lon")));

        }catch (Exception e){
            System.out.println(e);
        }
        return retorno;
    }


    public static String currentWeather(String cidade) {

        String retorno = null;

        try(CloseableHttpClient client = HttpClients.createDefault()){

            String uri = "https://api.openweathermap.org/data/2.5/weather?"+getLatLon(cidade)+"&appid="+apiKey;

            HttpGet get = new HttpGet(uri);
            HttpResponse response = client.execute(get);
            String resp = EntityUtils.toString(response.getEntity());
            JSONObject obj = new JSONObject(resp);



            retorno = obj.getString("name");

        }catch (Exception e){
            System.out.println(e);
        }
        return retorno;
    }

    public static String tempResponse(String cidade) {
        String retorno = null;

        try(CloseableHttpClient client = HttpClients.createDefault()){

            HttpGet get = new HttpGet(
                    "http://api.openweathermap.org/geo/1.0/direct?q="+cidade+"&appid="+apiKey);
            HttpResponse response = client.execute(get);
            String resp = EntityUtils.toString(response.getEntity());
            System.out.println(resp);
            JSONObject obj = new JSONObject(resp);
            System.out.println(obj.getBigDecimal("temp"));

            retorno = String.valueOf(obj.getBigDecimal("temp"));

        }catch (Exception e){
            System.out.println(e);
        }
        return retorno;
    }
}
