package main;

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

    public CommandLineAnalizer(CommandLine cmd, Options options) {
        this.formatter = new HelpFormatter();
        this.cmd = cmd;
        this.options = options;
    }

    public void optionReader() throws IOException, JSONException {
        if (cmd.hasOption("history")) {
            this.history = true;
        }

        if (this.apiKey == null && !cmd.hasOption("k")) {
            throw new Exception("Nie podałeś apiKey, ani nie znajduje się on w systemowej zmiennej.");
        } else if (cmd.hasOption("k")) {
            this.apiKey = cmd.getOptionValue("k");
        }


        if (cmd.hasOption("help")) {
            formatter.printHelp("Airly", options);
        } else if (cmd.hasOption("sensor-id") && cmd.hasOption("longitude") && cmd.hasOption("latitude")) {
            throw new Exception("Podałeś współrzędne i numer sensora, to za dużo danych.");
        } else if (cmd.hasOption("sensor-id")) {
            this.connection = new APIConnector(this.apiKey);
            Sensor sensor = connection.getInfoFromSensor(cmd.getOptionValue("sensor-id"));
            DataViewer view = new DataViewer(sensor, this.history);
            view.printData();
        } else if (cmd.hasOption("longitude") && cmd.hasOption("latitude")) {
            this.connection = new APIConnector(this.apiKey);
            Sensor sensor = connection.getIDtoNearestSensor(cmd.getOptionValue("latitude"), cmd.getOptionValue("longitude"));
            DataViewer view = new DataViewer(sensor, this.history);
            view.printData();
        } else {
            throw new Exception("Brak wystarczających argumentów do uruchomienia programu - sensora bądź współrzędnych.");
        }
    }
}
