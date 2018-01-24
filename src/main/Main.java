package main;


import com.google.gson.*;
import com.*;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.cli.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
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

        } catch (ParseException ex){
            System.out.println("Unexpected ParseException: " + ex.getMessage());
            formatter.printHelp( " ", options );

//        } catch (TestException ex){
//            System.out.println("TestException: " + ex.getMessage());
        } catch (IndexOutOfBoundsException ex){
            System.out.println("IndexOutOfBoundsException: "+ ex.getMessage());
        }

    }
}

 /*


     private static OkHttpClient client = new OkHttpClient();
    public static String getJSON (String url) throws IOException{
        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

   public static String[] getUserData (){
        String json= null;
        try{
            json = getJSON("https://airapi.airly.eu/v1/mapPoint/measurements?latitude=50.06&longitude=19.93&apikey=fae55480ef384880871f8b40e77bbef9");
            System.out.println(json);
        } catch (Exception e ){
            e.printStackTrace();
        }

        */