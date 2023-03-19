package com.example.tl01e10417;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tl01e10417.Configuracion.SQLiteConexion;
import com.example.tl01e10417.Configuracion.Transacciones;
import com.example.tl01e10417.Tablas.Contactos;

import java.util.ArrayList;

public class ActivityLista extends AppCompatActivity {

    SQLiteConexion conexion;
    ListView listacontactos;
    ArrayList<Contactos> lista;
    ArrayList<String>  ArregloContactos;
    EditText id;
    Button btnbusc, btnCompartir, btnVerimg, btnEliminar,btnActualizar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        // llamar a la conexion de bd sqlite
        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        //lista
        listacontactos= (ListView)findViewById(R.id.ListaContactos);
        ObtenerListaContactos();
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ArregloContactos );
        listacontactos.setAdapter(adp);

        // Botones
        btnbusc = (Button) findViewById(R.id.btnbuscar);
        btnCompartir = (Button) findViewById(R.id.btncompartir);
        btnVerimg = (Button) findViewById(R.id.btnverImag);
        btnEliminar =  (Button) findViewById(R.id.btneliminar);
        btnActualizar =  (Button) findViewById(R.id.btnactualizar);
        //llamando
        id = (EditText) findViewById(R.id.txtcid);

        btnVerimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llamando();
            }
        });
        btnbusc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Buscar();
            }
        });

        btnCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Compartir();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Actualizar();

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar();

            }
        });

       /* listacontactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               Intent sendIntent = new Intent();
               sendIntent.setAction(Intent.ACTION_SEND);
               sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
               sendIntent.setType("text/plain");
               Intent shareIntent = Intent.createChooser(sendIntent, null);
               startActivity(shareIntent);


            }
        });*/

        //Selecionarlis();



    }



   /* private void Selecionarlis() {
        listacontactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String mensaje = ArregloContactos.get(i);
                Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_SHORT).show();



            }
        });
    }*/


    private void llamando() {
       Intent paginaLista = new Intent(getApplicationContext(),ActivityllamarContacto.class);
       startActivity(paginaLista);

       // Intent intent = new Intent(Intent.ACTION_DIAL);
       // intent.setData(Uri.parse("tel:"));
        //startActivity(intent);
    }

    private void Buscar()
    {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {id.getText().toString()};
        String [] fields = {Transacciones.pais,
                Transacciones.nombre,
                Transacciones.telefono,
                Transacciones.nota };
        String wherecond = Transacciones.id + "=?";

        try
        {
            Cursor cdata = db.query(Transacciones.tablacontacto, fields, wherecond, params, null,null, null );
            cdata.moveToFirst();
            id.setText(cdata.getString(1));
            Toast.makeText(getApplicationContext(), "Consultado con exito",Toast.LENGTH_LONG).show();
            // String mensaje = ArregloContactos.get(0);
           //Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_SHORT).show();


        }
        catch (Exception ex)
        {
            ClearScreen();
            Toast.makeText(getApplicationContext(), "Elemento no encontrado",Toast.LENGTH_LONG).show();
        }

    }

    private void Compartir()
    {
        //Intent sendIntent = new Intent();
       // sendIntent.setAction(Intent.ACTION_SEND);
       // sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
       // sendIntent.setType("text/plain");
       // Intent shareIntent = Intent.createChooser(sendIntent, null);
       // startActivity(shareIntent);

        Intent paginaLista = new Intent(getApplicationContext(),ActivityListCompartir.class);
        startActivity(paginaLista);


    }

    private void Actualizar()
    {
        //Conexion a la db
        //SQLiteDatabase db = conexion.getWritableDatabase();
        //String [] params = {id.getText().toString()};
        //ContentValues valores = new ContentValues();
       // valores.put(Transacciones.id, id.getText().toString());
       // db.update(Transacciones.tablacontacto, valores, Transacciones.id + "=?", params);
       // Toast.makeText(getApplicationContext(), "Dato actualizado", Toast.LENGTH_LONG).show();
       // ClearScreen();

        Intent paginaLista = new Intent(getApplicationContext(),ActivityVerlista.class);
        startActivity(paginaLista);

    }

    private void Eliminar()
    {
        try {

           // SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null,1);
           // SQLiteDatabase db = conexion.getWritableDatabase();
           // ContentValues valores = new ContentValues();
           // valores.put(Transacciones.id, id.getText().toString());
           // Long resultado =db.delete(Transacciones.tablacontacto,Transacciones.id,valores);
           // Toast.makeText(this,"Eliminado con exito",Toast.LENGTH_SHORT).show()
           // ClearScreen();

            //Conexion a la base de datos
            SQLiteDatabase db = conexion.getWritableDatabase();
            String [] params = {id.getText().toString()};
            String wherecond = Transacciones.id + "=?";
            db.delete(Transacciones.tablacontacto, wherecond, params);
            Toast.makeText(getApplicationContext(), "Dato eliminado", Toast.LENGTH_LONG).show();

            ClearScreen();

        } catch (Exception ex){

            ex.toString();
        }

    }

    private void ClearScreen() {
        id.setText("");

    }


    private void ObtenerListaContactos() {
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