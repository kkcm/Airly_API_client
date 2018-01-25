package main;

import java.util.List;

public class Sensor {
    private Measurements currentMeasurements;
    private List<HistoryOrForecast> history;
    private List<HistoryOrForecast> forecast;

    public Measurements getCurrentMeasurements() {
        return currentMeasurements;
    }

    public List<HistoryOrForecast> getForecast() {
        return forecast;
    }

    public List<HistoryOrForecast> getHistory() {
        return history;
    }
}
