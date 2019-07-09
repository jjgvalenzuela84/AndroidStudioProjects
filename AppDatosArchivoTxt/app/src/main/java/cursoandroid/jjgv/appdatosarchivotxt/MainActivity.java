package cursoandroid.jjgv.appdatosarchivotxt;

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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et1;

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

        String[] archivos = fileList();
        if (existe(archivos, "notas.txt")) {
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("notas.txt"));
                BufferedReader bf = new BufferedReader(archivo);
                String linea = bf.readLine();
                String todo = "";
                while (linea != null){
                    todo = todo + linea + "\n";
                    linea = bf.readLine();
                }
                et1.setText(todo);


            } catch (FileNotFoundException e) {
                Toast.makeText(this,"Fichero no encontrado", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(this,"Error E/S del archivo", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean existe(String[] archivos, String archBuscar) {
        for (int i=0;i<archivos.length;i++){
            if (archivos[i].equals(archBuscar))  return true;
        }
        return false;
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
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("notas.txt", MODE_PRIVATE));
            archivo.write(et1.getText().toString());
            archivo.flush();
            archivo.close();
            finish();
        }catch (IOException e){
            Toast.makeText(this,"Error E/S del archivo", Toast.LENGTH_SHORT).show();
        }
    }
}
