package com.example.pc_gaming.libreriabbdd;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Javi on 18/02/2018.
 */

public class Contacto  extends AppCompatActivity {
    private static final int MAKE_CALL_PERMISSION_REQUEST_CODE = 1;
    EditText etMensaje;
    Button btSms, btLlamar;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        etMensaje=(EditText)findViewById(R.id.etMensaje);
        btSms=(Button)findViewById(R.id.btSms);
        btLlamar=(Button)findViewById(R.id.btLlamar);
    }


    public void enviarMensaje(View v){
        //Siguiendo los pasos del ejercicio EnviarSMS para probar
        String numTel=""+911344338; //Numero de Re-Readme
        String datosCompletos="Usuario -- Libreria -- " + etMensaje.getText().toString();

        try{
            //Verificamos permisos necesarios para enviar SMS
            int permisoEnvioSMS = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

            if (permisoEnvioSMS != PackageManager.PERMISSION_GRANTED){

                Toast.makeText(getApplicationContext(), "No tiene permiso para enviar SMS", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 225);
            }
            else{ //Nada//
            }

            SmsManager sms=SmsManager.getDefault();
            sms.sendTextMessage(numTel ,null, datosCompletos, null, null);
            Toast.makeText(getApplicationContext(), "Mensaje enviado con éxito", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Mensaje no enviado, verifique persmisos o datos" + e.getMessage().toString(),Toast.LENGTH_LONG ).show();
        }
    }


    public void llamar(View v){
        //Siguiendo los pasos del ejercicio EnviarSMS para probar
        String numeTel=""+911344338; //Numero de Re-Readme

        try{
            //Verificamos permisos necesarios para enviar SMS
            int permisoLlamada = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

            // Esta linea de codigo pide los permisos necesarios!
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MAKE_CALL_PERMISSION_REQUEST_CODE);

            if (comprobarPermiso(Manifest.permission.CALL_PHONE)) {
                String dial = "tel:" + numeTel; //Se tiene que poner literalmente esto
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            } else {
                Toast.makeText(getApplicationContext(), "Permiso de llamada denegado", Toast.LENGTH_SHORT).show();
            }


        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "La llamada no ha sido realizada, verifique persmisos o datos" + e.getMessage().toString(),Toast.LENGTH_LONG ).show();
        }
    }

    private boolean comprobarPermiso(String permission) {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case MAKE_CALL_PERMISSION_REQUEST_CODE :
                if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    btLlamar.setEnabled(true);
                    Toast.makeText(this, "Para llamar al número indicado, pulse el botón de llamada", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }




}