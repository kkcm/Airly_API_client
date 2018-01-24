package main;

public class HistoryOrForecast {
    private String fromDateTime;
    private Measurements measurements;
    private String tillDateTime;

    public String getFromDateTime(){
        return fromDateTime;
    }

    public String getTillDateTime() {
        return tillDateTime;
    }

    public Measurements getMeasurements() {
        return measurements;
    }
}
