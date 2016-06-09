package com.gio.ctic.doctor.Otros;

import android.util.Log;

import com.gio.ctic.doctor.Pacient.Paciente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by giovanny on 08/06/16.
 */

public class ConexionServer {

    String urlp="http://52.40.252.10:8081/";

    public String sendHistorial(String func, Historial historial) throws IOException {
        InputStream is = null;
        int len = 100;

        try {
            String furl=urlp+func+historial.toString().replace(" ","__");
            URL url = new URL(furl);
            Log.d("respuesta", furl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("respuesta", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public Paciente[] receiveJson(String func)throws IOException {

        InputStream is = null;
        int len = 1000;

        try {
            String furl=urlp+func.toString().replace(" ","__");
            URL url = new URL(furl);
            Log.d("respuesta", furl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // Starts the query
            conn.connect();


            int response = conn.getResponseCode();
            Log.d("respuesta", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            Log.d("respuesta", contentAsString);
            JSONObject jsonObject = new JSONObject(contentAsString);
            JSONArray jsonArray = jsonObject.getJSONArray("array_json");

            Log.d("respuesta", "Tamano json: " + jsonArray.length());

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject json_data = jsonArray.getJSONObject(i);
                Log.i("respuesta", "id_pac" + json_data.getString("id_pac") +
                                ", nom_pac" + json_data.getString("nom_pac") +
                                ", fecha_nac" + json_data.getString("fecha_nac") +
                                ", pass_pac" + json_data.getString("pass_pac")
                );
            }


            //return contentAsString;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
        }

        return new Paciente[]{};
    }

    public String LoginDoc(String func,String user,String pass) throws IOException {
        InputStream is = null;
        int len = 100;

        try {
            String furl=urlp+func+(user+"@!"+pass).replace(" ","__");
            URL url = new URL(furl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("respuesta", "The response is: " + response);
            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    private String readIt(InputStream stream, int len) throws IOException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

}

