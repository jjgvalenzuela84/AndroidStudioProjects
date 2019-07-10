package cursoandroid.jjgv.appdatosarchivotxt_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);


    }

    public void guardar(View view){
        String nombreFichero = et1.getText().toString().replace('/', '-');
        String strActividades = et2.getText().toString();
        if (nombreFichero.isEmpty()){
            Toast.makeText(this,"Debe ingresar una fecha", Toast.LENGTH_SHORT).show();
        }else if (strActividades.isEmpty()) {
            Toast.makeText(this,"No ha introducido ninguna actividad", Toast.LENGTH_SHORT).show();
        }else {
            try {
                OutputStreamWriter fileAct = new OutputStreamWriter(openFileOutput(nombreFichero,MODE_PRIVATE));
                fileAct.write(strActividades);
                fileAct.flush();
                fileAct.close();
                Toast.makeText(this,"Los datos han sido grabados", Toast.LENGTH_SHORT).show();
                et1.setText("");
                et2.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void recuperar(View view){
        String nombreFichero = et1.getText().toString().replace('/', '-');
        if (nombreFichero.isEmpty()){
            Toast.makeText(this,"Debe ingresar una fecha", Toast.LENGTH_SHORT).show();
        }else {
            String[] files = fileList();
            boolean existeFile = false;

            for (int i = 0; i < files.length; i++) {
                if (files[i].equals(nombreFichero))
                    existeFile = true;
            }
            if (existeFile) {
                try {
                    InputStreamReader file = new InputStreamReader(openFileInput(nombreFichero));
                    BufferedReader bf = new BufferedReader(file);
                    String linea = bf.readLine();
                    String todo = "";
                    while (linea != null) {
                        todo = todo + linea + "\n";
                        linea = bf.readLine();
                    }
                    bf.close();
                    file.close();
                    et2.setText(todo);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(this, "No tiene actividades este día", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
