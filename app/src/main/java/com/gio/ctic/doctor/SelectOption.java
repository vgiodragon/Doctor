package com.gio.ctic.doctor;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.gio.ctic.doctor.Otros.ConexionServer;
import com.gio.ctic.doctor.Otros.Doctor;
import com.gio.ctic.doctor.Pacient.Paciente;
import com.gio.ctic.doctor.Pacient.PacienteActivity;

import java.io.IOException;

public class SelectOption extends AppCompatActivity {

    TextView tdocname;
    Doctor doctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option);
        tdocname= (TextView) findViewById(R.id.nomdoc);
        Intent i = getIntent();
        doctor= i.getParcelableExtra("doctor_login");
        if(doctor!=null)
            tdocname.setText("Bienvenido Dr. "+doctor.getNom_doc());


    }

    public void lanzaCrearNuevaHistoria(View view){
        Intent intent = new Intent(this, PacienteActivity.class);
        intent.putExtra("doctor_login", doctor);
        new getPacJs("/consPac/").execute();
        //startActivity(intent);
    }


    private class getPacJs extends AsyncTask<String, Void, String> {
        String url;
        public getPacJs(String url){
            this.url=url;

        }

        @Override
        protected String doInBackground(String... urls) {
            String respues="...";
            try {

                ConexionServer cs= new ConexionServer();
                Paciente[]pacientes = cs.receiveJson(url);
            }catch (IOException e) {
                e.printStackTrace();
            }
            return respues;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("respuesta", result);

        }
    }
}
