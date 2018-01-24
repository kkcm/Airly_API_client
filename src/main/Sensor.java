package main;

import java.util.List;

public class Sensor {
    private Measurements measurements;
    private List<HistoryOrForecast> history;
    private List<HistoryOrForecast> forecast;

    public Measurements getMeasurements() {
        return measurements;
    }

    public List<HistoryOrForecast> getForecast() {
        return forecast;
    }

    public List<HistoryOrForecast> getHistory() {
        return history;
    }
}
