package com.example.tl01e10417;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.tl01e10417.Configuracion.SQLiteConexion;
import com.example.tl01e10417.Configuracion.Transacciones;
import com.example.tl01e10417.Tablas.Contactos;

import java.util.ArrayList;

public class ActivityVerlista extends AppCompatActivity {


    EditText nombres,telefonos,notas;
    Button guarda,elimina,actualiza;
    Spinner combpais;
    ArrayList<Contactos> lista;
    SQLiteConexion conexion;
    ListView listacontactos;

    ArrayList<String>  ArregloContactos;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verlista);
        nombres= (EditText) findViewById(R.id.txnombre);
        telefonos=(EditText) findViewById(R.id.txtelefono);
        notas=(EditText) findViewById(R.id.txnota);
        guarda=(Button) findViewById(R.id.btnGurda);
        actualiza=(Button) findViewById(R.id.btnActu);
        elimina=(Button) findViewById(R.id.btnEli);





    }



}