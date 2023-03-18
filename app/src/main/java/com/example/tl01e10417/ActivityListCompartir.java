package com.example.tl01e10417;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tl01e10417.Configuracion.SQLiteConexion;
import com.example.tl01e10417.Configuracion.Transacciones;
import com.example.tl01e10417.Tablas.Contactos;

import java.util.ArrayList;

public class ActivityListCompartir extends AppCompatActivity {

    SQLiteConexion conexion;
    ListView listaConta;
    ArrayList<Contactos> lista;
    ArrayList<String>  ArregloContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_compartir);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        listaConta = (ListView)findViewById(R.id.listaconta);

        ObtenerListaCont();


        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ArregloContactos );
        listaConta.setAdapter(adp);

        listaConta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String info = "ID : " + lista.get(i).getId() +"\n";
                Toast.makeText(ActivityListCompartir.this,info, Toast.LENGTH_SHORT).show();
                Intent CompartirCont = new Intent();
                CompartirCont.setAction(Intent.ACTION_SEND);
                CompartirCont.putExtra(Intent.EXTRA_TEXT,info );
                CompartirCont.setType("text/plain");
                Intent Share = Intent.createChooser(CompartirCont, null);
                startActivity(Share);
            }
        });


    }

    private void ObtenerListaCont() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Contactos listCont = null;
        lista = new ArrayList<Contactos>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.tablacontacto, null);

        while (cursor.moveToNext())
        {
            listCont = new Contactos();
            listCont.setId(cursor.getInt(0));
            listCont.setPais(cursor.getString(1));
            listCont.setNombre(cursor.getString(2));
            listCont.setTelefono(cursor.getString(3));
            listCont.setNota(cursor.getString(4));


            lista.add(listCont);
        }
        cursor.close();

        fillList();
    }

    private void fillList() {


            ArregloContactos = new ArrayList<String>();
            for(int i = 0; i < lista.size(); i++)
            {
                ArregloContactos.add(lista.get(i).getId() + " | "
                        +lista.get(i).getNombre() + " | "
                        +lista.get(i).getTelefono());
            }

    }
}