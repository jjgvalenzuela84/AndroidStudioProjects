package cursoandroid.jjgv.appcalculadora;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;
    private TextView tv3;
    private RadioButton r1, r2;

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
        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
        tv3 = findViewById(R.id.tv3);
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

    public void operar(View view){

        String opStr1 = et1.getText().toString();
        String opStr2 = et2.getText().toString();

        if (opStr1.isEmpty() || opStr2.isEmpty()){
            Toast.makeText(this,"Debe ingresar los dos valores", Toast.LENGTH_SHORT).show();
            tv3.setText("Debe ingresar los dos valores");
        }else{

            float op1 = Float.parseFloat(opStr1);
            float op2 = Float.parseFloat(opStr2);
            Float res;
            if (r1.isChecked()) {
                res = op1 + op2;
            } else res = op1 - op2;
            tv3.setText(res.toString());
        }

    }
}
