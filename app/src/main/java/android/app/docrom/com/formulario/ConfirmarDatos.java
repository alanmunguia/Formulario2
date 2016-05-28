package android.app.docrom.com.formulario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


public class ConfirmarDatos extends AppCompatActivity {
    TextView nombre;
    TextView fecha;
    TextView telefono;
    TextView email;
    TextView descripcion;
    Bundle parametros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_datos);

        parametros = getIntent().getExtras();
        String nombreS = parametros.getString(getResources().getString(R.string.nombre_completo));
        String anioS = parametros.getString("anio");
        String mesS = parametros.getString("mes");
        String diaS = parametros.getString("dia");
        String telefonoS = parametros.getString(getResources().getString(R.string.telefono));
        String emailS = parametros.getString(getResources().getString(R.string.email));
        String descripcionS = parametros.getString(getResources().getString(R.string.descripcion_contacto));

        nombre = (TextView) findViewById(R.id.tvNombreConfirmar);
        fecha = (TextView) findViewById(R.id.tvFechaConfirmar);
        telefono = (TextView) findViewById(R.id.tvTelefonoConfirmar);
        email = (TextView) findViewById(R.id.tvEmailConfirmar);
        descripcion = (TextView) findViewById(R.id.tvDescripcionConfirmar);

        nombre.setText("Nombre: " + nombreS);
        fecha.setText("Fecha de Nacimiento: " + diaS + "/" + mesS + "/" + anioS );
        telefono.setText("Telefono: " +telefonoS);
        email.setText("Email: " + emailS);
        descripcion.setText("Descripcion: " + descripcionS);
    }

    public void onClick(View v) {

        Intent inten = new Intent(ConfirmarDatos.this,MainActivity.class);
        inten.putExtra("bundle",parametros);
        startActivity(inten);
        finish();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("restaurado",1);

    }
}
