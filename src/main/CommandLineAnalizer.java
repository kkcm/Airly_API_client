package main;

import com.google.gson.Gson;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.json.JSONException;

import java.io.IOException;

public class CommandLineAnalizer {
    private HelpFormatter formatter;
    private CommandLine cmd;
    private Options options;
    private APIConnector connection;
    private boolean history = false;
    private String apiKey = System.getenv("API_KEY");

    public CommandLineAnalizer(CommandLine cmd, Options options){
        this.formatter = new HelpFormatter();
        this.cmd = cmd;
        this.options = options;
    }

    public void optionReader () throws IOException, JSONException {
        if (cmd.hasOption("history")){
            this.history = true;
            System.out.println("Wyświetlę 24h historii pogody." + this.history);
        }

        if (this.apiKey == null && !cmd.hasOption("k")){
            System.out.println("Nie podałeś apiKey, ani nie znajduje się on w systemowej zmiennej.");
        } else if (cmd.hasOption("k")){
            this.apiKey = cmd.getOptionValue("k");
            System.out.println("Podałeś apiKey: " + this.apiKey);
        } else {
            System.out.println("Użyję systemowej zmiennej.");
        }


        if (cmd.hasOption("help")){
            formatter.printHelp("Airly", options);
        } else if(cmd.hasOption("sensor-id") && cmd.hasOption("longitude") && cmd.hasOption("latitude")){
            System.out.println("Podałeś współrzędne i numer sensora, to za dużo danych.");
        } else if (cmd.hasOption("sensor-id")){
            System.out.println("Połączę się z sensorem o id: " + cmd.getOptionValue("sensor-id"));
            this.connection = new APIConnector(this.apiKey);
            Sensor sensor = connection.getInfoFromSensor(cmd.getOptionValue("sensor-id"));
            System.out.println(sensor.getCurrentMeasurements().getAirQualityIndex());
            DataViewer view = new DataViewer(sensor, this.history);
            view.printData();
        } else if (cmd.hasOption("longitude") && cmd.hasOption("latitude")){
            System.out.println("Połączę się z sensorem o współrzędnych najbliższych longitude " + cmd.getOptionValue("longitude") + " i latitude " + cmd.getOptionValue("latitude"));
            this.connection = new APIConnector(this.apiKey);
            Sensor sensor = connection.getIDtoNearestSensor(cmd.getOptionValue("latitude"), cmd.getOptionValue("longitude"));
            System.out.println(sensor.getCurrentMeasurements().getAirQualityIndex());
            DataViewer view = new DataViewer(sensor, this.history);
            view.printData();
        }
        else {
            System.out.println("Brak wystarczających argumentów - sensora bądź współrzędnych.");
        }
    }
}
