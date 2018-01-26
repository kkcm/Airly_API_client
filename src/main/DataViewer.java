package main;


import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciithemes.TA_GridThemes;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import java.util.List;


public class DataViewer {
    private Sensor sensor;
    private boolean history;

    public DataViewer(Sensor sensor, boolean history) {
        this.sensor = sensor;
        this.history = history;
    }

    public void printData() {
        if (this.history) {
            this.printHistoryMeasurements();
        } else {
            this.printCurrentMeasurements();
        }
    }

    public void printCurrentMeasurements() {

        Measurements currentMeasurements = this.sensor.getCurrentMeasurements();
        currentMeasurements = this.formatData(currentMeasurements);
        AsciiTable table = new AsciiTable();

        this.printASCIIart(currentMeasurements);

        table.addRule();
        table.addRow(null, "Current Measurements: ", "Unit of measurement:");
        table.addRule();
        table.addRow("airQualityIndex", currentMeasurements.getAirQualityIndex(), "-");
        table.addRule();
        table.addRow("pm1", currentMeasurements.getPm1(), " μg/m3");
        table.addRule();
        table.addRow("pm2.5", currentMeasurements.getPm25(), " μg/m3");
        table.addRule();
        table.addRow("pm2.5", this.roundData(currentMeasurements.getPm25() / 25 * 100), " %");
        table.addRule();
        table.addRow("pm10", currentMeasurements.getPm10(), " μg/m3");
        table.addRule();
        table.addRow("pm10", this.roundData(currentMeasurements.getPm10() / 50 * 100), " %");
        table.addRule();
        table.addRow("pressure", currentMeasurements.getPressure(), " hPa");
        table.addRule();
        table.addRow("polutionLevel", currentMeasurements.getPolutionLevel(), "-");
        table.addRule();
        table.addRow("humidity", currentMeasurements.getHumidity(), "%");
        table.addRule();
        table.addRow("temperature", currentMeasurements.getTemperature(), "\u00b0" + "C");
        table.addRule();

        table.setTextAlignment(TextAlignment.CENTER);
        table.getContext().setGridTheme(TA_GridThemes.LINE_TOPBOTTOM);
        String rend = table.render();
        System.out.println(rend);

    }

    public void printHistoryMeasurements() {
        List<HistoryOrForecast> history = this.sensor.getHistory();
        for (HistoryOrForecast historyMeasurements : history) {
            Measurements measurements = historyMeasurements.getMeasurements();
            measurements = this.formatData(measurements);
        }

        this.printASCIIart(this.sensor.getCurrentMeasurements());

        AsciiTable table = new AsciiTable();

        table.addRule();
        table.addRow(null, null, null, null, null, null, null, null, null, "History of measurements in last 24 hours:");
        table.addRule();
        table.addRow("fromDateTime", "tillDateTime", "airQualityIndex", "pm1 [μg/m3]", "pm2.5 [μg/m3]", "pm10 [μg/m3]", "pressure [hPa]", "polutionLevel", "humidity [%]", "temperature [" + "\u00b0" + "C]");
        table.addRule();

        for (HistoryOrForecast historyMeasurements : history) {
            Measurements measurements = historyMeasurements.getMeasurements();
            table.addRow(historyMeasurements.getFromDateTime().substring(11, 19), historyMeasurements.getTillDateTime().substring(11, 19),
                    measurements.getAirQualityIndex(), measurements.getPm1(), measurements.getPm25(),
                    measurements.getPm10(), measurements.getPressure(), measurements.getPolutionLevel(),
                    measurements.getHumidity(), measurements.getTemperature());
            table.addRule();
        }

        table.setTextAlignment(TextAlignment.CENTER);
        table.getContext().setGridTheme(TA_GridThemes.LINE_TOPBOTTOM);
        table.getContext().setWidth(175);

        String rend = table.render();
        System.out.println(rend);

    }


    public Measurements formatData(Measurements measurements) {
        measurements.airQualityIndex = this.roundData(measurements.airQualityIndex);
        measurements.pm1 = this.roundData(measurements.pm1);
        measurements.pm10 = this.roundData(measurements.pm10);
        measurements.pm25 = this.roundData(measurements.pm25);
        measurements.pressure = this.roundData(measurements.pressure / 100);
        measurements.humidity = this.roundData(measurements.humidity);
        measurements.temperature = this.roundData(measurements.temperature);
        return measurements;
    }

    public Double roundData(Double data) {
        double roundNumber = (double) Math.round(data * 100) / 100;
        return roundNumber;
    }

    public void printASCIIart(Measurements currentMeasurements) {
        System.out.println("            Current Air Quality Index is " + this.roundData(currentMeasurements.getAirQualityIndex()));
        if (currentMeasurements.getAirQualityIndex() <= 50) {
            String happyFace = "                  _.-'''''-._      \n" +
                    "               .'  _     _  '.    \n" +
                    "              /   (_)   (_)   \\  \n" +
                    "             |  ,           ,  |  \n" +
                    "             |  \\`.       .`/  | \n" +
                    "              \\  '.`'''''`.'  /  \n" +
                    "               '.  `'---'`  .'    \n" +
                    "                 '-._____.-'      \n";


            System.out.println(happyFace);
        } else if (currentMeasurements.getAirQualityIndex() <= 100) {
            String sadFace = "                 .-''''''-.    \n" +
                    "               .'          '.   \n" +
                    "              /   O      O   \\  \n" +
                    "             :                :  \n" +
                    "             |                |   \n" +
                    "             :     ------     :  \n" +
                    "              \\              /  \n" +
                    "               '.          .'   \n" +
                    "                 '-......-'     \n";


            System.out.println(sadFace);
        } else {
            String angryFace = "                  .-''''''-.      \n" +
                    "               .'  \\  //  '.     \n" +
                    "              /   O      O   \\   \n" +
                    "             :                :   \n" +
                    "             |                |   \n" +
                    "             :       __       :     \n" +
                    "              \\  .-'`  `'-.  /     \n" +
                    "               '.          .'     \n" +
                    "                 '-......-'     \n";


            System.out.println(angryFace);
        }
        System.out.println("            Let's see more data: ");
    }
}






