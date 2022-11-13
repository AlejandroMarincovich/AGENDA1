package com.example.agenda11;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Editar extends AppCompatActivity {

    private EditText txt_nombre,txt_apellido,txt_edad,ed_id,txt_correo,txt_numero;
    private Button b_editar,b_eliminar,b_volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        txt_nombre = findViewById(R.id.txtnombre);
        txt_apellido = findViewById(R.id.txtapellido);
        txt_edad = findViewById(R.id.txtedad);
        ed_id = findViewById(R.id.id);
        txt_correo = findViewById(R.id.txtcorreo);
        txt_numero = findViewById(R.id.txtnumero);

        b_editar = findViewById(R.id.btn_editar);
        b_eliminar = findViewById(R.id.btn_eliminar);
        b_volver = findViewById(R.id.btn_volver);

        Intent i = getIntent();

        String et_id = i.getStringExtra("id").toString();
        String txtnombre = i.getStringExtra("nombre").toString();
        String txtapellido = i.getStringExtra("apellido").toString();
        String txtedad = i.getStringExtra("edad").toString();
        String txtcorreo = i.getStringExtra("correo").toString();
        String txtnumero = i.getStringExtra("numero").toString();

        ed_id.setText(et_id);
        txt_nombre.setText(txtnombre);
        txt_apellido.setText(txtapellido);
        txt_edad.setText(txtedad);
        txt_correo.setText(txtcorreo);
        txt_numero.setText(txtnumero);


        b_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar();
            }
        });

        b_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminar();
            }
        });

        b_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Leer.class);
                startActivity(i);
            }
        });
    }

    public void eliminar()
    {
        try
        {
            String id = ed_id.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("BD_EJEMPLO", Context.MODE_PRIVATE,null);


            String sql = "delete from persona where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this,"Datos eliminados de la base de datos.",Toast.LENGTH_LONG).show();

            txt_nombre.setText("");
            txt_apellido.setText("");
            txt_edad.setText("");
            txt_correo.setText("");
            txt_numero.setText("");
            txt_nombre.requestFocus();

        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Error no se pudieron guardar los datos.",Toast.LENGTH_LONG).show();
        }
    }
    public void editar()
    {
        try
        {
            String nombre = txt_nombre.getText().toString();
            String apellido = txt_apellido.getText().toString();
            String edad = txt_edad.getText().toString();
            String correo = txt_correo.getText().toString();
            String numero = txt_numero.getText().toString();
            String id = ed_id.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("BD_EJEMPLO",Context.MODE_PRIVATE,null);

            String sql = "update persona set nombre = ?,apellido=?,edad=? where id= ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,nombre);
            statement.bindString(2,apellido);
            statement.bindString(3,edad);
            statement.bindString(4,id);
            statement.execute();
            Toast.makeText(this,"Datos actualizados satisfactoriamente en la base de datos.",Toast.LENGTH_LONG).show();

            txt_nombre.setText("");
            txt_apellido.setText("");
            txt_edad.setText("");
            txt_correo.setText("");
            txt_numero.setText("");
            txt_nombre.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Error no se pudieron guardar los datos.",Toast.LENGTH_LONG).show();
        }
    }
}