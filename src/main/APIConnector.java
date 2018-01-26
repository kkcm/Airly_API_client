package main;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;


public class APIConnector {
    private String apiKey;
    private OkHttpClient client = new OkHttpClient();

    public APIConnector(String apiKey) {
        this.apiKey = apiKey;
    }

    public Sensor getIDtoNearestSensor(String latitude, String longitude) throws IOException, JSONException {
        String url = "https://airapi.airly.eu/v1/nearestSensor/measurements?latitude=" + latitude + "&longitude=" + longitude;
        String jsonNearest = this.setConnection(url);

        JSONObject object = new JSONObject(jsonNearest);

        if (object.isNull("id")) {
            throw new Exception("Nie znaleziono w pobliżu tej lokalizacji żadnego sensora");
        } else {
            String sensorID = object.getString("id");
            Sensor sensor = this.getInfoFromSensor(sensorID);
            return sensor;
        }
    }

    public Sensor getInfoFromSensor(String sensorID) throws IOException {
        String url = "https://airapi.airly.eu/v1/sensor/measurements?sensorId=" + sensorID;
        String jsonSensor = this.setConnection(url);
        Gson gson = new Gson();
        Sensor sensor = gson.fromJson(jsonSensor, Sensor.class);
        return sensor;
    }

    public String setConnection(String url) throws IOException, NullPointerException {

        Request request = new Request.Builder().url(url + "&apikey=" + this.apiKey).build();
        Response response = client.newCall(request).execute();
        int code = response.code();

        if (code == 200) {
            String json = response.body().string();
            response.body().close();
            return json;
        } else {
            response.body().close();
            if (code == 404) throw new Exception("Error 404 - Not found");
            else if (code == 403) throw new Exception("Error 403 - Forbidden");
            else if (code == 401) throw new Exception("Error 401 - Unauthorized");
            else if (code == 400) throw new Exception("Error 400 - Input validation error");
            else if (code == 500) throw new Exception("Error 500 - Unexpected error");
            return null;
        }
    }
}
