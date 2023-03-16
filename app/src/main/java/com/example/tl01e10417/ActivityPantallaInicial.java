package com.example.tl01e10417;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tl01e10417.Configuracion.SQLiteConexion;
import com.example.tl01e10417.Configuracion.Transacciones;


public class ActivityPantallaInicial extends AppCompatActivity {

    String [] country ={"Honduras (+504)","Costa Rica (+506)",
            "Guatemala (+502)","El Salvador(+503)",
            "Nicaragua (+505)","Belice (+501)",
            "Ecuador (+593)","Venezuela (+58)" };
    AlertDialog.Builder builder;
    ImageView foto;
    EditText Nombre, Telefono,nota;
    Button BtnSalvar,BtnSalvados;
    //Combo
    Spinner combopais;
    AlertDialog AlertTextNombre,AlertTextTelefono,AlertTextNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_pantalla_inicial);




            combopais =(Spinner) findViewById(R.id.combopais);
            Nombre = (EditText)  findViewById(R.id.txtnombre);
            Telefono = (EditText) findViewById(R.id.txttelefono);
            nota=(EditText) findViewById(R.id.txtNota);
            builder = new AlertDialog.Builder(this);
            //BOTONES
            BtnSalvar =(Button) findViewById(R.id.btnSalvar);
            BtnSalvados= (Button) findViewById(R.id.btnSalvando);



         //Combobox
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,country);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        combopais.setAdapter(adapter);

        combopais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String value =adapterView.getItemAtPosition(i).toString();
               //Toast.makeText(ActivityPantallaInicial.this, value, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        BtnSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AgregarContacto();

                }
            });

        BtnSalvados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paginaLista = new Intent(getApplicationContext(),ActivityLista.class);
                startActivity(paginaLista);
            }
        });



    }




    private void AgregarContacto()
    {

        try {

            SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null,1);


            SQLiteDatabase db = conexion.getWritableDatabase();

            ContentValues valores = new ContentValues();

            //valores.put(Transacciones.pais, pais.getText().toString());

            valores.put(Transacciones.nombre, Nombre.getText().toString());
            valores.put(Transacciones.telefono,Telefono.getText().toString());
            valores.put(Transacciones.nota, nota.getText().toString());

            Long resultado =db.insert(Transacciones.tablacontacto,Transacciones.id,valores);

            Toast.makeText(this,"Ingresado con exito",Toast.LENGTH_SHORT).show();

            CleanPantalla();

        } catch (Exception ex){

            ex.toString();

        }






    }

    private void CleanPantalla()
    {
       // pais.setText("");
        Nombre.setText("");
        Telefono.setText("");
        nota.setText("");
    }


}