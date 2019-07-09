package cursoandroid.jjgv.appdatossharedpreferences_2;

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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;
    private FloatingActionButton fav;

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
        et2 = findViewById(R.id.et2);
        fav = findViewById(R.id.fab);

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et1.setText("");
                et2.setText("");
                et1.requestFocus();
            }
        });

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

    public void grabar(View view){
        String nombre = et1.getText().toString();
        String tlf = et2.getText().toString();
        if (tlf.isEmpty()){
            et2.requestFocus();
            Toast.makeText(this,"No ha introducido el telefono", Toast.LENGTH_SHORT).show();
        }else {
            SharedPreferences preferences = getSharedPreferences("agenda", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(nombre, tlf);
            editor.commit();
        }
    }

    public void recuperar(View view){
        String nombre = et1.getText().toString();
        SharedPreferences pref = getSharedPreferences("agenda", MODE_PRIVATE);
        String tlf = pref.getString(nombre, "");
        if (tlf.isEmpty()) {
            et2.setText("");
            et1.requestFocus();
            Toast.makeText(this, "No existe el contacto en la agenda", Toast.LENGTH_SHORT).show();
        }else {
            et2.setText(tlf);
        }
    }
}
