package com.example.pc_gaming.libreriabbdd;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ProductosyWeb extends AppCompatActivity {

    TextView tvProductos, tvWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productosy_web);

        tvProductos = findViewById(R.id.tvProductos);
        tvWeb = findViewById(R.id.tvWeb);

        // Importar  android.support.v4.app.FragmentManager;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        FragmentPaginaWeb fragmentWeb = new FragmentPaginaWeb();

        transaction.add(R.id.linearLayout, fragmentWeb);
        transaction.commit();

    }

    public void seleccionarFragment(View v){

        Fragment miFragmento = null;

        if(v == findViewById(R.id.tvWeb)){
            miFragmento = new FragmentPaginaWeb();
        }else if(v == findViewById(R.id.tvProductos)){
            miFragmento = new FragmentProductos();
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.linearLayout, miFragmento);
        transaction.addToBackStack(null);
        transaction.commit();

    }

}
