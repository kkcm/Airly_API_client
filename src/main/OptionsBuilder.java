package main;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;


public class OptionsBuilder {
    private Option help;
    private Option sensor;
    private Option key;
    private Option latitude;
    private Option longitude;
    private Option history;


    public OptionsBuilder(){
        this.help = new Option("h", "help", false, "wyświetla tą instrukcję -> -h");

        this.sensor = new Option("s", "sensor-id", true, "podaj numer sensoru -> np. -s 204");
        this.sensor.setArgs(1);

        this.key = new Option("k", "api-key", true, "podaj klucz api -> np. -k fae55480ef384880871f8b40e77bbef9" );
        this.key.setArgs(1);

        this.latitude = new Option("lat", "latitude", true, "podaj szerokość geograficzną -> np. -lat 19.94");
        this.latitude.setArgs(1);

        this.longitude = new Option("long", "longitude", true, "podaj długość geograficzną -> np. -long 43.33");
        this.latitude.setArgs(1);

        this.history = new Option("his", "history", false, "wyświetla historię jakości powietrza z ostatnich 24h -> np. -his");
    }

    public Options addOptions (Options options){

        options.addOption(this.help);
        options.addOption(this.sensor);
        options.addOption(this.key);
        options.addOption(this.latitude);
        options.addOption(this.longitude);
        options.addOption(this.history);

        return options;
    }

}
