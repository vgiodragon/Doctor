package com.gio.ctic.doctor;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gio.ctic.doctor.Otros.ConexionServer;
import com.gio.ctic.doctor.Otros.Historial;
import com.gio.ctic.doctor.Pacient.Paciente;

import java.io.IOException;

public class NewHistoryActivity extends AppCompatActivity {

    TextView fecha;
    TextView nombre;
    TextView id;
    TextView dni;
    EditText esintomas;
    EditText edescrip;
    ConexionServer cs;
    String idDoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_historial);
        cs= new ConexionServer();
        Intent i = getIntent();
        Paciente paciente = i.getParcelableExtra("pacient_selected");
        nombre= (TextView) findViewById(R.id.tnamep);
        id = (TextView) findViewById(R.id.tidp);
        dni = (TextView) findViewById(R.id.tdnip);
        fecha =(TextView)findViewById(R.id.tfecha);
        esintomas= (EditText) findViewById(R.id.etsintomas);
        edescrip= (EditText) findViewById(R.id.etdescrip);
        nombre.setText(paciente.getNombre());
        id.setText(paciente.getId());
        dni.setText(paciente.getDni());
        idDoc =paciente.getIdDoc();
        esintomas.setText("Estos son los sintomas");
        edescrip.setText("Esta es la descrip");
    }





    public void bGuardar(View view){

        Historial historial = new Historial();
        historial.setFecha((String) fecha.getText());
        historial.setSintomas(esintomas.getText().toString());
        historial.setDescripcion(edescrip.getText().toString());
        historial.setId_pac(id.getText().toString());
        historial.setIddoctor( idDoc);

        new SJServer("saveHistory/",historial).execute();

    }

    public void regresar(String res){
        Toast.makeText(this,res,Toast.LENGTH_LONG);
        Log.d("respuesta",res);
        Intent intent = new Intent(this,SelectOption.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private class SJServer extends AsyncTask<String, Void, String> {
        String url;
        Historial h;
        public SJServer (String url,Historial h){
            this.url=url;
            this.h=h;
        }

        @Override
        protected String doInBackground(String... urls) {
            String respues="...";
            try {
                respues = cs.sendHistorial(url,h);
            }catch (IOException e) {
                e.printStackTrace();
            }
            return respues;
        }

        @Override
        protected void onPostExecute(String result) {
            regresar(result);
        }
    }
}

