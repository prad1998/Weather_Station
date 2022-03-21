package Sensor;

import java.sql.*;
import java.util.logging.Level;

public class AccessDatabase {
    public enum Level {
        TEMP, //maxTemp, minTemp
        RAIN,
        WIND //winddirection

    }
    public String getData(Level sensorType) {
        String databaseURL = "jdbc:postgresql://ziggy.db.elephantsql.com/";
        String user = "eujkisqi";
        String pass = "E9mNN7TqwlNb1GUguaQbTKq9a1XrwcJv";

        String data = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection db = DriverManager.getConnection(databaseURL, user, pass);
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM SensorData");
            ResultSetMetaData rsmd = rs.getMetaData();

            switch(sensorType){
                case TEMP:



                    while (rs.next()) {
                        for(int i = 1; i <= 3; i++){
                            String tempValue = rs.getString(1);
                            String maxTempValue = rs.getString(2);
                            String minTempValue = rs.getString(3);

                            String tempString = tempValue + " " + rsmd.getColumnName(1) + "\n";
                            String maxTempString = maxTempValue + " " + rsmd.getColumnName(2) + "\n";
                            String minTempString = minTempValue + " " + rsmd.getColumnName(3) + "\n";

                            data = (new StringBuilder()).append(tempString).append(maxTempString).append(minTempString).toString();
                            System.out.println(data);
                            return data;
                        }

                    }
                    break;
                case RAIN:

                    while (rs.next()) {
                        for(int i = 1; i <= 3; i++){
                            String columnValue = rs.getString(4);

                            String rainString = columnValue + " " + rsmd.getColumnName(4) + "\n";

                            data = (new StringBuilder()).append(rainString).toString();
                            System.out.println(data);
                            return data;
                        }

                    }
                    break;
                case WIND:
                    while (rs.next()) {
                        for(int i = 1; i <= 3; i++){
                            String windValue = rs.getString(5);
                            String windDirValue = rs.getString(6);

                            String windString = windValue + " " + rsmd.getColumnName(5) + "\n";
                            String windDirectionString = windDirValue + " " + rsmd.getColumnName(6) + "\n";

                            data = (new StringBuilder()).append(windString).append(windDirectionString).toString();
                            System.out.println(data);
                            return data;
                        }

                    }
                    break;
            }




            rs.close();
            st.close();


        } catch (SQLException ex) {
            ex.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }


}
