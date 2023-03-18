package com.example.tl01e10417;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tl01e10417.Configuracion.SQLiteConexion;
import com.example.tl01e10417.Configuracion.Transacciones;
import com.example.tl01e10417.Tablas.Contactos;

import java.util.ArrayList;

public class ActivityVerlista extends AppCompatActivity {

    SQLiteConexion conexion;
    EditText id,nombre,telefono,nota;
    Button btnelimina,btnactualiza,btnbusca;
    Spinner combpais;

    String [] country ={"Honduras (+504)","Costa Rica (+506)",
            "Guatemala (+502)","El Salvador(+503)",
            "Nicaragua (+505)","Belice (+501)",
            "Ecuador (+593)","Venezuela (+58)" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verlista);


        id = (EditText) findViewById(R.id.txtid);
        nombre= (EditText) findViewById(R.id.txnombre);
        combpais =(Spinner) findViewById(R.id.combopais);
        telefono=(EditText) findViewById(R.id.txtelefono);
        nota=(EditText) findViewById(R.id.txnota);
        //Botones

        btnactualiza=(Button) findViewById(R.id.btnActu);
        btnelimina=(Button) findViewById(R.id.btnEli);
        btnbusca=(Button) findViewById(R.id.btnbusca);
        // llamar a la conexion de bd sqlite
        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);


        //Combobox
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,country);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        combpais.setAdapter(adapter);

        combpais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String value =adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(ActivityPantallaInicial.this, value, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        btnbusca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buscar();
            }
        });

        btnactualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Actualizar();
            }
        });
        btnelimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar();
            }
        });

    }

    private void Buscar() {
        SQLiteDatabase db = conexion.getWritableDatabase();

        /* Parametros de configuracion de la sentencia SELECT */
        String [] params = {id.getText().toString()}; // parametro de la busqueda
        String [] fields = {Transacciones.nombre,
                Transacciones.telefono,
                Transacciones.nota };
        String wherecond = Transacciones.id + "=?";

        try
        {
            Cursor cdata = db.query(Transacciones.tablacontacto, fields, wherecond, params, null,null, null );
            cdata.moveToFirst();

            nombre.setText(cdata.getString(0));
            telefono.setText(cdata.getString(1));
            nota.setText(cdata.getString(2));


            Toast.makeText(getApplicationContext(), "Consultado con exito",Toast.LENGTH_LONG).show();
        }
        catch (Exception ex)
        {
            ClearScreen();
            Toast.makeText(getApplicationContext(), "Elemento no encontrado",Toast.LENGTH_LONG).show();
        }
    }

    private void Actualizar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {id.getText().toString()};

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombre, nombre.getText().toString());
        valores.put(Transacciones.telefono,telefono.getText().toString());
        valores.put(Transacciones.nota, nota.getText().toString());

        db.update(Transacciones.tablacontacto, valores, Transacciones.id + "=?", params);
        Toast.makeText(getApplicationContext(), "Dato actualizado", Toast.LENGTH_LONG).show();
        //Toast.makeText(this, "Datos actulizado", Toast.LENGTH_SHORT).show();
        ClearScreen();
    }

    private void ClearScreen() {
        nombre.setText("");
        telefono.setText("");
        nota.setText("");

    }

    private void Eliminar() {
        //conexion de base de datos
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {id.getText().toString()};
        String wherecond = Transacciones.id + "=?";
        db.delete(Transacciones.tablacontacto, wherecond, params);
        Toast.makeText(getApplicationContext(), "Dato eliminado", Toast.LENGTH_LONG).show();
        ClearScreen();

    }


}