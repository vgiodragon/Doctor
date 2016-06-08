package com.gio.ctic.doctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gio.ctic.doctor.Pacient.Paciente;

public class NewHistoryActivity extends AppCompatActivity {

    TextView fecha;
    TextView nombre;
    TextView id;
    TextView dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_historial);
        Intent i = getIntent();
        Paciente paciente = i.getParcelableExtra("pacient_selected");
        nombre= (TextView) findViewById(R.id.tnamep);
        id= (TextView) findViewById(R.id.tidp);
        dni= (TextView) findViewById(R.id.tdnip);

        nombre.setText(paciente.getNombre());
        id.setText(paciente.getId());
        dni.setText(paciente.getDni());

    }



    public void bGuardar(View view){
        Intent intent = new Intent(this,SelectOption.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}

