package cursoandroid.jjgv.appdatossharedpreferences_3;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private TextView tv2;
    private int numAleatorio;
    private int puntuacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        et1 = findViewById(R.id.et1);
        tv2 = findViewById(R.id.tv2);

        Random r = new Random();
        numAleatorio = r.nextInt(50)+1;

        obtenerPutuacion();
    }

    private void obtenerPutuacion() {
        SharedPreferences pref = getSharedPreferences("puntuacion", MODE_PRIVATE);
        puntuacion =  pref.getInt("puntos", 0);
        tv2.setText("Tu puntuacion actual es de : " + String.valueOf(puntuacion)+ " puntos");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void jugar(View view){
        String strNum = et1.getText().toString();
        if (strNum.isEmpty()){
            Toast.makeText(this, "Debe ingresar un numero", Toast.LENGTH_SHORT).show();
        }else {
            int num = Integer.parseInt(et1.getText().toString());
            if (num == numAleatorio) {
                Toast.makeText(this, "!!!!HAS ACERTADO!!!", Toast.LENGTH_LONG).show();
                SharedPreferences preferences = getSharedPreferences("puntuacion", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("puntos", puntuacion + 1);
                editor.commit();
                obtenerPutuacion();
                et1.setText("");
                et1.requestFocus();
            }
            if (num > numAleatorio) {
                Toast.makeText(this, "El numero es demasiado alto", Toast.LENGTH_SHORT).show();
            }
            if (num < numAleatorio) {
                Toast.makeText(this, "El numero es demasiado bajo", Toast.LENGTH_SHORT).show();
            }
        }


    }
}
