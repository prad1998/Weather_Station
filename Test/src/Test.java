import Sensor.AccessDatabase;

public class Test {

    public static void main(String[] args) {
        AccessDatabase ad = new AccessDatabase();
        ad.getData(AccessDatabase.Level.TEMP);
        ad.getData(AccessDatabase.Level.WIND);
        ad.getData(AccessDatabase.Level.RAIN);
    }
}
