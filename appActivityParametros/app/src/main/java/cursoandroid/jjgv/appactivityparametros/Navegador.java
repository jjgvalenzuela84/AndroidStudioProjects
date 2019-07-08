package cursoandroid.jjgv.appactivityparametros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class Navegador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegador);

        WebView webView = findViewById(R.id.webView);

        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("direccion");

        webView.loadUrl(url);

        Button cerrar = findViewById(R.id.button2);
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
