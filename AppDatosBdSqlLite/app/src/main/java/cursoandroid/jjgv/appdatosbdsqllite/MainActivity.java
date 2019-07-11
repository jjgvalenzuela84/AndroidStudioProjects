package cursoandroid.jjgv.appdatosbdsqllite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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

    private EditText et1, et2, et3;

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
        et3 = findViewById(R.id.et3);
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


    public void alta(View view){
        String codigo = et1.getText().toString();
        if (!codigo.isEmpty()) {
            String descripcion = et2.getText().toString();
            String precio = et3.getText().toString();

            AdminSQLLiteOpenHelper admin = new AdminSQLLiteOpenHelper(this, "Administracion", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("codigo", codigo);
            contentValues.put("descripcion", descripcion);
            contentValues.put("precio", precio);
            db.insert("Articulos", null, contentValues);
            db.close();
            et1.setText("");
            et2.setText("");
            et3.setText("");
            Toast.makeText(this, "El articulo se ha insertado", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "El codigo del articulo es obligatorio", Toast.LENGTH_SHORT).show();
        }

    }
    public void consultPorCodigo(View view){

        String codigo = et1.getText().toString();
        if (!codigo.isEmpty()) {
            AdminSQLLiteOpenHelper admin = new AdminSQLLiteOpenHelper(this, "Administracion",null,1);
            SQLiteDatabase db = admin.getReadableDatabase();

            Cursor fila = db.rawQuery("select descripcion, precio from Articulos where codigo = " + codigo, null);
            if (fila.moveToFirst()){
                et2.setText(fila.getString(0));
                et3.setText(fila.getString(1));
            }else{
                Toast.makeText(this,"Articulo no encotrado", Toast.LENGTH_SHORT).show();
            }
            db.close();
        }else{
            Toast.makeText(this, "El codigo del articulo es obligatorio", Toast.LENGTH_SHORT).show();
        }




    }
    public void consultPorDescripcion(View view){

        AdminSQLLiteOpenHelper admin = new AdminSQLLiteOpenHelper(this, "Administracion",null,1);
        SQLiteDatabase db = admin.getReadableDatabase();
        String descripcion = et2.getText().toString();
        Cursor fila = db.rawQuery("select codigo, precio from Articulos where descripcion = '" + descripcion + "'", null);
        if (fila.moveToFirst()){
            et1.setText(fila.getString(0));
            et3.setText(fila.getString(1));
        }else{
            Toast.makeText(this,"Articulo no encotrado", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public void bajaPorCodigo(View view){
        String codigo = et1.getText().toString();
        if (!codigo.isEmpty()) {
            AdminSQLLiteOpenHelper admin = new AdminSQLLiteOpenHelper(this, "Administracion",null,1);
            SQLiteDatabase db = admin.getWritableDatabase();

            int filasBorradas = db.delete("Articulos", "codigo = "+ codigo, null);
            if (filasBorradas > 0){
                Toast.makeText(this,"Articulo borrado", Toast.LENGTH_SHORT).show();
                et1.setText("");
                et2.setText("");
                et3.setText("");
            }else{
                Toast.makeText(this,"Articulo no encotrado", Toast.LENGTH_SHORT).show();
            }
            db.close();
        }else{
            Toast.makeText(this, "El codigo del articulo es obligatorio", Toast.LENGTH_SHORT).show();
        }

    }
    public void modificacion(View view){
        String cod = et1.getText().toString();
        if (!cod.isEmpty()) {

            AdminSQLLiteOpenHelper admin = new AdminSQLLiteOpenHelper(this, "Administracion", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();

            String desc = et2.getText().toString();
            String precio = et3.getText().toString();
            ContentValues cv = new ContentValues();
            cv.put("codigo", cod);
            cv.put("descripcion", desc);
            cv.put("precio", precio);
            int cont = db.update("Articulos", cv, "codigo = " + cod, null);
            db.close();
            if (cont > 0) {
                Toast.makeText(this, "Articulo modificado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Articulo no encontrado", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "El codigo del articulo es obligatorio", Toast.LENGTH_SHORT).show();
        }

    }
}
