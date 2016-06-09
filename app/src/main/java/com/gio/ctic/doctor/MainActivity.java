package com.gio.ctic.doctor;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gio.ctic.doctor.Otros.ConexionServer;
import com.gio.ctic.doctor.Otros.Doctor;
import com.gio.ctic.doctor.Otros.Historial;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String user,pass;
    EditText etuser;
    EditText etpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=pass="";
        etuser= (EditText) findViewById(R.id.eUser);
        etpass= (EditText) findViewById(R.id.ePass);
    }

    public void getEditText(){
        user=etuser.getText().toString();
        pass=etpass.getText().toString();
    }

    public void Login(View view){
        //Intent intent = new Intent(this,SelectOption.class);
        //startActivity(intent);
        new LoginServer("logDoc/").execute();
    }

    public void lanzaLogin(String result){

        String part[]= result.split("_");
        if(part.length>2){
            Doctor doc = new Doctor(part[1],part[2],part[3]);
            Intent intent = new Intent(this,SelectOption.class);
            intent.putExtra("doctor_login",doc);
            startActivity(intent);
        }else {
            Toast.makeText(this,result,Toast.LENGTH_LONG).show();
        }
    }

    private class LoginServer extends AsyncTask<String, Void, String> {
        String url;
        public LoginServer (String url){
            this.url=url;
            getEditText();
        }

        @Override
        protected String doInBackground(String... urls) {
            String respues="...";
            try {

                ConexionServer cs= new ConexionServer();
                respues = cs.LoginDoc(url,user,pass);
            }catch (IOException e) {
                e.printStackTrace();
            }
            return respues;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("respuesta",result);
            lanzaLogin(result);
        }
    }
}
