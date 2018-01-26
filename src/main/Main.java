package main;

import org.apache.commons.cli.*;
import java.io.*;
import org.json.*;


public class Main {

    public static void main(String[] args) {
        Options options = new Options();
        OptionsBuilder opt = new OptionsBuilder();
        opt.addOptions(options);

        HelpFormatter formatter = new HelpFormatter();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
            CommandLineAnalizer cmdLineAnalizer = new CommandLineAnalizer(cmd, options);
            cmdLineAnalizer.optionReader();
        } catch (ParseException ex) {
            System.out.println("Unexpected ParseException: " + ex.getMessage());
            formatter.printHelp(" ", options);
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        } catch (JSONException ex) {
            System.out.println("JSONException: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("IndexOutOfBoundsException: " + ex.getMessage());
        }
    }
}
