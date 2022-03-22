package Sensor;


import netscape.javascript.JSObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.*;

import org.json.JSONObject;



public class WeatherMetricsFetcher {

    String output = "";
    String JSONString = "";


    public void PostData(double WindSpeed, String WindDirection, double WindDegree, double Temp, double Humidity,  double cloud,  double uv, String last_updated) throws ClassNotFoundException, SQLException {
        String databaseURL = "jdbc:postgresql://ziggy.db.elephantsql.com/";
        String user = "eujkisqi";
        String pass = "E9mNN7TqwlNb1GUguaQbTKq9a1XrwcJv";
        String INSERT_DATA = "INSERT INTO sensor" + "(windspeed, winddir,windc,temp,humidity,cloud,uv,last_updated) VALUES" + "(?,?,?,?,?,?,?,?);";



        try(Connection connection = DriverManager.getConnection(databaseURL, user, pass)){


            Class.forName("org.postgresql.Driver");

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DATA);

            preparedStatement.setDouble(1, WindSpeed );
            preparedStatement.setString(2, WindDirection );
            preparedStatement.setDouble(3, WindDegree );
            preparedStatement.setDouble(4, Temp );
            preparedStatement.setDouble(5, Humidity );
            preparedStatement.setDouble(6, cloud );
            preparedStatement.setDouble(7, uv );
            preparedStatement.setString(8, last_updated );


            System.out.println(preparedStatement);


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } {


        }


    }


    public void FetchData() throws IOException, InterruptedException {


   try{
       URL url = new URL("http://api.weatherapi.com/v1/current.json?key=06365bf72fc34c95a68155827222103&q=Odense&aqi=no\n");
       HttpURLConnection connection = (HttpURLConnection) url.openConnection();
       connection.setRequestMethod("GET");
       connection.setRequestProperty("Accept", "application/json");

       if (connection.getResponseCode() != 200) {
           throw new RuntimeException("Failed : HTTP error code : "
                   + connection.getResponseCode());
       }

       BufferedReader br = new BufferedReader(new InputStreamReader(
               (connection.getInputStream())));


       System.out.println("Output from Server .... \n");
       while ((output = br.readLine()) != null) {
           System.out.println(output);
           JSONString = output ;

       }


     JSONObject jsonObject = new JSONObject(JSONString);
       JSONObject current = jsonObject.getJSONObject("current");
       JSONObject location = jsonObject.getJSONObject("location");


       Double WindSpeed = Double.valueOf(current.get("wind_kph").toString());
       String WindDir = current.get("wind_dir").toString();
       Double WindDegree = Double.valueOf(current.get("wind_degree").toString());
       Double Temp = Double.valueOf(current.get("temp_c").toString());
       Double Humidity = Double.valueOf(current.get("humidity").toString());
       String last_updated = current.get("last_updated").toString();
       Double cloud = Double.valueOf(current.get("cloud").toString());
       Double uv = Double.valueOf(current.get("uv").toString());


       PostData(WindSpeed,WindDir,WindDegree,Temp,Humidity,cloud,uv,last_updated);

       //  Timestamp time = Timestamp.valueOf(location.get("localtime").toString());


       //timeocation.get("localtime");

   //    System.out.println(time);



       connection.disconnect();



   } catch (MalformedURLException e) {

       e.printStackTrace();

   } catch (IOException e) {

       e.printStackTrace();

   } catch (SQLException e) {
       e.printStackTrace();
   } catch (ClassNotFoundException e) {
       e.printStackTrace();
   }


    }



}
