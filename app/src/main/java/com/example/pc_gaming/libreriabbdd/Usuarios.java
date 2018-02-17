package com.example.pc_gaming.libreriabbdd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Usuarios extends AppCompatActivity {

    private TextView tvNombre, tvApellido, tvEmail, tvDireccion, tvTelefono, tvPagWeb, tvEmpresa;
    private ImageButton ibtnSiguiente, ibtnAnterior, ibtnGoogleMaps;
    private int pos = 0;

    private ArrayList<Usuario> usuarios;

    //public static final String URL = "https://jsonplaceholder.typicode.com/users/1";
    public static final String URL = "http://www.tunometescabra.es/empresa/users.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        usuarios = new ArrayList<Usuario>();

        tvNombre = findViewById(R.id.tvNombreText);
        tvApellido = findViewById(R.id.tvApellidoText);
        tvEmail = findViewById(R.id.tvEmailText);
        tvDireccion = findViewById(R.id.tvDireccionText);
        tvTelefono = findViewById(R.id.tvTelefonoText);
        tvPagWeb = findViewById(R.id.tvWebText);
        tvEmpresa = findViewById(R.id.tvEmpresaText);

        ibtnSiguiente = findViewById(R.id.ibtnSiguiente);
        ibtnAnterior = findViewById(R.id.ibtnAnterior);
        ibtnGoogleMaps = findViewById(R.id.ibtnGoogleMaps);


        RequestQueue request = Volley.newRequestQueue(getApplicationContext());

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {

                    // EL LOG SE UTILIZA PARA VER SI NOS RESCATA EL JSON!
                    Log.d("RESPONSE", response.toString(0));

                    // Creo un objeto JSONObject
                    JSONObject jsonObjectPrincipal = new JSONObject(response.toString(0));

                    JSONArray jsonArray = jsonObjectPrincipal.getJSONArray("users");

                    for (int i = 0; i < jsonArray.length(); i++){

                        JSONObject jsonObjectHijo = jsonArray.getJSONObject(i);

                        String nombre = jsonObjectHijo.getString("name");
                        String apellido = jsonObjectHijo.getString("username");
                        String email = jsonObjectHijo.getString("email");
                        String calle = jsonObjectHijo.getJSONObject("address").getString("street");
                        String suite = jsonObjectHijo.getJSONObject("address").getString("suite");
                        String ciudad = jsonObjectHijo.getJSONObject("address").getString("city");
                        String codigoZip = jsonObjectHijo.getJSONObject("address").getString("zipcode");
                        String latitud = jsonObjectHijo.getJSONObject("address").getJSONObject("geo").getString("lat");
                        String longitud = jsonObjectHijo.getJSONObject("address").getJSONObject("geo").getString("lng");
                        String telefono = jsonObjectHijo.getString("phone");
                        String pagWeb = jsonObjectHijo.getString("website");
                        String nombreEmpresa = jsonObjectHijo.getJSONObject("company").getString("name");
                        String catchPhrase= jsonObjectHijo.getJSONObject("company").getString("catchPhrase");
                        String bs = jsonObjectHijo.getJSONObject("company").getString("bs");


//                                                 NOMBRE   APELLIDO   EMAIL                          DIRECCION                              TELF.    PAG.WEB                   EMPRESA
                        usuarios.add(new Usuario (nombre, apellido, email, calle + ", " + suite + ", " + ciudad + ", " + codigoZip, latitud, longitud, telefono, pagWeb, nombreEmpresa + ", " + catchPhrase + ", " + bs));

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }


                mostrarUser(pos);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        request.add(jsonObjectRequest);

        ibtnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /////////////////////
                pos++;
                if(pos >= usuarios.size()){
                    pos = 0;
                }
                mostrarUser(pos);
            }
        });

        ibtnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///////////////////
                pos--;
                if(pos <= 0){
                    pos = usuarios.size() - 1;
                }
                mostrarUser(pos);
            }
        });

        ibtnGoogleMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                i.putExtra("longitud", usuarios.get(pos).getLongitud().toString());
                i.putExtra("latitud", usuarios.get(pos).getLatitud().toString());
                startActivity(i);
            }
        });
    }

    public void mostrarUser(int i){

        tvNombre.setText(usuarios.get(i).getNombre().toString());
        tvApellido.setText(usuarios.get(i).getApellidos().toString());
        tvEmail.setText(usuarios.get(i).getEmail().toString());
        tvDireccion.setText(usuarios.get(i).getDireccion().toString());
        tvTelefono.setText(usuarios.get(i).getTelefono().toString());
        tvPagWeb.setText(usuarios.get(i).getWeb().toString());
        tvEmpresa.setText(usuarios.get(i).getEmpresa().toString());
        Toast.makeText(getApplicationContext(), "Long: " + usuarios.get(i).getLongitud().toString() + " Lat: " + usuarios.get(i).getLatitud().toString(), Toast.LENGTH_SHORT).show();

    }
}
