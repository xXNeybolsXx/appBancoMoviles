package com.example.appbancobr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Cuenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);
        Button agregarC = findViewById(R.id.btnagregarC);
        Button actualizarC = findViewById(R.id.btnactualizarC);
        Button eliminarC = findViewById(R.id.btneliminarC);
        Button buscarC = findViewById(R.id.btnbuscarC);


        TextView usuarioc = findViewById(R.id.tvusuarioc);

        //Mostrar El Nombre Y El Rol Enviados Desde MainActivity.java
        usuarioc.setText(usuarioc.getText().toString()+" Usuario: "+getIntent().getStringExtra("sname" )+" Rol: "+getIntent().getStringExtra("srol"));

        //Crear EL Evento Click Para agregarC
        agregarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Invocar La Actividad Del Registro
                startActivity(new Intent(getApplicationContext(),AgregarCuentas.class));
            }
        });

        //Crear EL Evento Click Para Actualizar
        actualizarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Invocar La Actividad Del Registro
                startActivity(new Intent(getApplicationContext(),ActualizarCuentas.class));
            }
        });

        //Crear EL Evento Click Para Eliminar
        eliminarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Invocar La Actividad Del Registro
                startActivity(new Intent(getApplicationContext(),EliminarCuentas.class));
            }
        });

        //Crear EL Evento Click Para Buscar
        buscarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Invocar La Actividad Del Registro
                startActivity(new Intent(getApplicationContext(),BuscarCuenta.class));
            }
        });
    }
}