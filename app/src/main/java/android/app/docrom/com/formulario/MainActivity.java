package android.app.docrom.com.formulario;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity   {

    EditText nombre;
    DatePicker fecha;
    EditText telefono;
    EditText email;
    EditText descripcion;
    int restaurado = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre =  (EditText)findViewById(R.id.etNombreCompleto);
        fecha =  (DatePicker)findViewById(R.id.dpFecha);
        telefono =  (EditText)findViewById(R.id.etTelefono);
        email = (EditText)findViewById(R.id.etEmail);
        descripcion =  (EditText)findViewById(R.id.etDescripcionContacto);

        SharedPreferences preferencias = getSharedPreferences("preferencias",Context.MODE_PRIVATE);
        int valorRestaurado = (int) preferencias.getInt("restaurado",0);
        if(valorRestaurado == 1){
            restaurarDatos(savedInstanceState);
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putInt("restaurado",0);
            editor.commit();

        }

    }



    public void onClick(View v) {


        Intent inten = new Intent(MainActivity.this,ConfirmarDatos.class);
        inten.putExtra(getResources().getString(R.string.nombre_completo),nombre.getText().toString());
        inten.putExtra("anio",String.valueOf(fecha.getYear()));
        inten.putExtra("mes",String.valueOf(fecha.getMonth()));
        inten.putExtra("dia",String.valueOf(fecha.getDayOfMonth()));
        inten.putExtra(getResources().getString(R.string.telefono),telefono.getText().toString());
        inten.putExtra(getResources().getString(R.string.email),email.getText().toString());
        inten.putExtra(getResources().getString(R.string.descripcion_contacto),descripcion.getText().toString());
        startActivity(inten);
        SharedPreferences preferencias = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putInt("restaurado",1);
        editor.commit();

        finish();
    }



    public void restaurarDatos(Bundle restaurado){
        Bundle parametros = getIntent().getExtras();
        Bundle bundleConfirmarDatos = parametros.getBundle("bundle");

        String nombreS = bundleConfirmarDatos.getString(getResources().getString(R.string.nombre_completo));
        String anioS = bundleConfirmarDatos.getString("anio");
        String mesS = bundleConfirmarDatos.getString("mes");
        String diaS = bundleConfirmarDatos.getString("dia");
        String telefonoS =bundleConfirmarDatos.getString(getResources().getString(R.string.telefono));
        String emailS = bundleConfirmarDatos.getString(getResources().getString(R.string.email));
        String descripcionS = bundleConfirmarDatos.getString(getResources().getString(R.string.descripcion_contacto));

        nombre.setText(nombreS);
        fecha.updateDate(Integer.parseInt(anioS),Integer.parseInt(mesS),Integer.parseInt(diaS));
        telefono.setText(telefonoS);
        email.setText(emailS);
        descripcion.setText(descripcionS);

    }

}
