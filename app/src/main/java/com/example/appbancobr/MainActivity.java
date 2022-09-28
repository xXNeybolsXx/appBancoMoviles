package com.example.appbancobr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText email = findViewById(R.id.etemail);
        EditText password = findViewById(R.id.etpassword);
        Button startsession = findViewById(R.id.btnstartsesion);
        TextView regLink = findViewById(R.id.tvregister);

        //Crear EL Evento Click Para Registrarse
        regLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Invocar La Actividad Del Registro
                startActivity(new Intent(getApplicationContext(),register.class));
            }
        });


        //Haciendo El Click De Inicio De Sección
        startsession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sEmail = email.getText().toString();
                String sPass = password.getText().toString();

                //Chequear Si Email Y Password Se Digitaron Correctamente

                if (!sEmail.isEmpty() && !sPass.isEmpty()){

                    //Conexion A La Base De Datos
                    sqlBanco ohBanco = new sqlBanco (getApplicationContext(),"dbbanco",null,1);

                    //Vamos A Buscar El Email Y La Password
                    SQLiteDatabase dbseccion = ohBanco.getReadableDatabase();
                    String query = "SELECT name, rol FROM customer WHERE email = '"+sEmail+"' and password = '"+sPass+"'";

                    Cursor cCust = dbseccion.rawQuery(query, null);

                    if (cCust.moveToFirst()){

                        //Chequear
                        String rol = cCust.getString(1);
                        String name = cCust.getString(0);

                        if (rol.equals("Administrador")){
                            //Invocar La Actividad De Cuenta Con El Parámetro De Name
                            Intent iCuenta = new Intent(getApplicationContext(),Cuenta.class);
                            iCuenta.putExtra("sname",name);  //Parametro Enviado A La Actividad De Cuenta
                            iCuenta.putExtra("srol",rol);
                            startActivity(iCuenta);  //Inicia La LLamada De La Actividad

                        }
                        else {
                            startActivity(new Intent(getApplicationContext(),Usuarios.class));
                        }

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Email Y/O Contraseña Invalidos",Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    //Notificacion Que Debe Validar Los Datos
                    Toast.makeText(getApplicationContext(),"Debe Ingresar Este Campo",Toast.LENGTH_LONG).show();
                }



            }
        });

    }
}