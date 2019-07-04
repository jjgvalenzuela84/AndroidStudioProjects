package cursoandroid.jjgv.appcalculadora3;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;
    private TextView tv3;
    private Spinner spinner;

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
                limpiar();
            }
        });

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operar();
            }

        });

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        tv3 = findViewById(R.id.tv3);
        spinner = findViewById(R.id.spinner);
        String[] opciones = {"Sumar", "Restar", "Multiplicar", "Dividir"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,opciones);
        spinner.setAdapter(adapter);
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

    public void operar(){

        String res = null;
        try {

            String strN1 = et1.getText().toString();
            String strN2 = et2.getText().toString();

            if (!strN1.isEmpty() && !strN2.isEmpty()) {

                Double n1 = Double.parseDouble(strN1);
                Double n2 = Double.parseDouble(strN2);
                String select = spinner.getSelectedItem().toString();

                if (select.equals("Sumar")){
                    res = String.valueOf(n1+n2);
                }else if (select.equals("Restar")){
                    res = String.valueOf(n1-n2);
                }else if (select.equals("Multiplicar")){
                    res = String.valueOf(n1*n2);
                }else if (select.equals("Dividir")){
                    res = String.valueOf(n1/n2);
                }else{
                    Toast.makeText(this, "Debe seleccionar alguna operación", Toast.LENGTH_LONG).show();
                    res = "-ERROR-";
                }
            }else{
                Toast.makeText(this, "Debe ingresar los dos valores", Toast.LENGTH_LONG).show();
                res = "-ERROR-";
            }
            tv3.setText(res);
        }catch (Exception e){
            Toast.makeText(this, "Operacion no permitida", Toast.LENGTH_LONG).show();
            limpiar();
        }

    }

    public void limpiar(){
        et1.setText("");
        et2.setText("");
        tv3.setText(R.string.StrTv3);
    }
}
