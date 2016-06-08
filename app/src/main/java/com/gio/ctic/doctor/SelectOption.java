package com.gio.ctic.doctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gio.ctic.doctor.Pacient.PacienteActivity;

public class SelectOption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option);
    }

    public void lanzaCrearNuevaHistoria(View view){
        Intent intent = new Intent(this, PacienteActivity.class);
        startActivity(intent);
    }
}
