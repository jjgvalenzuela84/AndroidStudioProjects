package cursoandroid.jjgv.appSumar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;
    private TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        tv3 = findViewById(R.id.tv3);
    }

    public void sumar(View view){
        String s1 = et1.getText().toString();
        String s2 = et2.getText().toString();
        if (s1.isEmpty() || s2.isEmpty()){
            Toast.makeText(this,"Debe introducir los dos valores", Toast.LENGTH_SHORT).show();
            tv3.setText("Debe introducir los dos valores");
        } else {
            float sum1 = Float.parseFloat(s1);
            float sum2 = Float.parseFloat(s2);
            float res = sum1 + sum2;
            String resultado = "El resultado es: " + res;
            tv3.setText(resultado);
        }
    }
}
