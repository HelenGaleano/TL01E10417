package com.example.tl01e10417;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button BtnIngresar, BtnLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BtnIngresar=(Button) findViewById(R.id.btnIngresar);
        BtnLista=(Button) findViewById(R.id.btnList);

        //BOTON PARA INGRESAR A INGRESAR A CONTACTOS
        BtnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paginaIni  = new Intent(getApplicationContext(),ActivityPantallaInicial.class);
                startActivity(paginaIni);

            }
        });

        //BOTON PARA INGRESAR A LISTA DE CONTACTOS
        BtnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent paginaLi = new Intent(getApplicationContext(),ActivityLista.class);
                startActivity(paginaLi);

            }
        });

    }
}