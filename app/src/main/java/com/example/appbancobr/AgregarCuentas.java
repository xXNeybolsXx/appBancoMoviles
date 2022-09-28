package com.example.appbancobr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AgregarCuentas extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cuentas);

        EditText nroCuentaAgregar = findViewById(R.id.etnrocuentaagregar);
        EditText emailAgregar = findViewById(R.id.etemailagregar);
        EditText fechaAgregar = findViewById(R.id.etfechaagregar);
        EditText balanceAgregar = findViewById(R.id.etbalanceagregar);
        Button agregarCuenta = findViewById(R.id.btnagregarCuenta);

        agregarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serAccount(nroCuentaAgregar.getText().toString(), emailAgregar.getText().toString(),fechaAgregar.getText().toString(), balanceAgregar.getText().toString());
            }

            private void serAccount(String snroCuentaAgregar, String sEmailAgregar, String sFechaAgregar, String sBalanceAgregar) {

            }
        });
    }
}