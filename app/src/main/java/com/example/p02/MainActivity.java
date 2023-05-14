package com.example.p02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import android.text.TextUtils;


public class MainActivity extends AppCompatActivity {

    private Button btnCalcular;
    private Button btnLimpiar;
    private Button btnCerrar;


    private TextView lblMostrarResultado;
    private EditText inputAltura;
    private EditText inputPeso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DecimalFormat formato = new DecimalFormat("#.##");

        //Relacion de los objetos Java con las vistas xml
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnLimpiar = (Button) findViewById(R.id.btnLimpiar);
        btnCerrar = (Button) findViewById(R.id.btnCerrar);

        inputAltura = (EditText) findViewById(R.id.inputAltura);
        inputPeso = (EditText) findViewById(R.id.inputPeso);
        lblMostrarResultado = (TextView) findViewById(R.id.lblMostrarResultado);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String altura = inputAltura.getText().toString();
                String peso = inputPeso.getText().toString();

                if (TextUtils.isEmpty(altura) || TextUtils.isEmpty(peso)) {
                    // Falta capturar el nombre
                    Toast.makeText(MainActivity.this, "Favor Llene Todos Los Campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double alturaEnCm = Double.parseDouble(altura);
                    double pesoEnKg = Double.parseDouble(peso);

                    double alturaEnMetros = alturaEnCm / 100;

                    double IMC = pesoEnKg / Math.pow(alturaEnMetros, 2);
                    String IMCFormateado = formato.format(IMC);

                    lblMostrarResultado.setText(IMCFormateado + " kg/m2");

                    inputAltura.setHint(""+alturaEnCm);
                    inputPeso.setHint(""+pesoEnKg);
                    inputAltura.setText("");
                    inputPeso.setText("");
                } catch (NumberFormatException e) {
                    // Captura de excepción por si el usuario ingresa un valor no numérico
                    Toast.makeText(MainActivity.this, "Los campos de entrada deben ser numéricos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputAltura.setText("");
                inputPeso.setText("");
                lblMostrarResultado.setText("" );
            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}