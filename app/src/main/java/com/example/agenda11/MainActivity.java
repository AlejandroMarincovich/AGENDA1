package com.example.agenda11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtnombre,txtapellido,txtedad, txtcorreo, txtnumero;
    private Button  btn_agregar,btn_ver,btn_extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtnombre = findViewById(R.id.txtnombre);
        txtapellido = findViewById(R.id.txtapellido);
        txtedad = findViewById(R.id.txtedad);
        txtcorreo = findViewById(R.id.txtcorreo);
        txtnumero = findViewById(R.id.txtnumero);


        btn_agregar = findViewById(R.id.btn_agregar);
        btn_ver = findViewById(R.id.btn_ver);

        btn_ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), Leer.class);
                startActivity(i);
            }
        });

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
            }
        });

    }
    public void Extras(View view){
        Intent extras = new Intent(this, ExtrasActivity.class);
        startActivity(extras);
    }

    public void insertar()
    {
        try
        {
            String nombre = txtnombre.getText().toString();
            String apellido = txtapellido.getText().toString();
            String edad = txtedad.getText().toString();
            String correo = txtcorreo.getText().toString();
            String telefono = txtnumero.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("BDBD", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS persona(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre VARCHAR," +
                    "apellido VARCHAR," +
                    "edad VARCHAR," +
                    "correo VARCHAR," +
                    "telefono VARCHAR)");

            String sql = "insert into persona(nombre,apellido,edad,correo,telefono)values(?,?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,nombre);
            statement.bindString(2,apellido);
            statement.bindString(3,edad);
            statement.bindString(4,correo);
            statement.bindString(5,telefono);
            statement.execute();
            Toast.makeText(this,"Datos agregados satisfactoriamente en la base de datos.",Toast.LENGTH_LONG).show();

            txtnombre.setText("");
            txtapellido.setText("");
            txtedad.setText("");
            txtcorreo.setText("");
            txtnumero.setText("");
            txtnombre.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Error no se pudieron guardar los datos.",Toast.LENGTH_LONG).show();
        }
    }
}