import Sensor.AccessDatabase;
import Sensor.WeatherMetricsFetcher;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException, InterruptedException {

        WeatherMetricsFetcher wf = new WeatherMetricsFetcher();
        wf.FetchData();

        AccessDatabase ad = new AccessDatabase();
       // ad.getData(AccessDatabase.Level.TEMP);
       // ad.getData(AccessDatabase.Level.WIND);
       // ad.getData(AccessDatabase.Level.RAIN);
    }
}
