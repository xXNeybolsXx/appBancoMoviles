package com.example.appbancobr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String [] rols = {"Administrador","Usuario"};
    String rolSel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText email = findViewById(R.id.etemailreg);
        EditText name = findViewById(R.id.etusernamereg);
        EditText password = findViewById(R.id.etpasswordreg);
        Spinner rol = findViewById(R.id.sprolreg);
        Button register = findViewById(R.id.btnregister);
        ArrayAdapter adpRol = new ArrayAdapter(this, android.R.layout.simple_list_item_checked,rols);
        rol.setAdapter(adpRol);
        //Generar El Evento Para Seleccionar Un rol
        rol.setOnItemSelectedListener(this);

        //Enento Click Del Boton Register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCustomer(email.getText().toString(), name.getText().toString(), password.getText().toString(), rolSel);
            }
        });


    }

    private void searchCustomer(String sEmail, String sName, String sPassword, String srolSel) {

        //Crear Array Para Almacenar Los Datos De La Consulta (query)
        ArrayList<String> dataCustomer = new ArrayList<String>();

        //Instanciar La Clase sqlBanco
        sqlBanco ohBanco = new sqlBanco(this, "dbbanco", null, 1);

        //Instanciar La Clase SQLiteDatabase Para El Crud
        SQLiteDatabase db = ohBanco.getReadableDatabase();

        //Crear vble Para Consulta
        String sql = "Select Email From Customer Where Email = '"+sEmail+"'";

        //Ejecutar La Instruccion Que Contiene La vble sql, A Traves De Una Tabla Cursor
        Cursor cCustomer = db.rawQuery(sql, null);

        //Chequear Si La Tabla Cursor cCustomer Quedo Con, Al Menos, Un Registro
        if (!cCustomer.moveToFirst()){ //No Lo Encontro
            //Imstanciar La bd En Modo Escritura Por Que Se Agregara Un Cliente
            SQLiteDatabase dbadd = ohBanco.getWritableDatabase();

            //Manejo De Excepciones
            try {

                ContentValues cvCustomer = new ContentValues();
                cvCustomer.put("email", sEmail);
                cvCustomer.put("name", sName);
                cvCustomer.put("password", sPassword);
                cvCustomer.put("rol", srolSel);
                dbadd.insert("customer", null, cvCustomer);
                dbadd.close();
                Toast.makeText(getApplicationContext(), "Cliente Agregado Correctamente", Toast.LENGTH_SHORT).show();

                //Chequear Si El Rol Es Administrador O Usuario
                if (srolSel.equals("Administrador")){

                    startActivity(new Intent(getApplicationContext(),Cuenta.class));

                }else {

                    startActivity(new Intent(getApplicationContext(),Usuarios.class));

                }

            } catch (Exception e){
                Toast.makeText(getApplicationContext(), "Error "+e.getMessage(),Toast.LENGTH_SHORT).show();

            }

            // Agregar El Cliente con Todos Sus Datos
        }else {
            Toast.makeText(getApplicationContext(),"Email Existente!. Intente Con Otro", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        rolSel = rols [position];

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}