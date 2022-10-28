package com.example.ProyectoTaller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
     String user, pass, sms, res;
     TextView edtUser, edtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button imgButton = findViewById(R.id.img_login);
        edtUser = findViewById(R.id.txt_user);
        user = edtUser.getEditableText().toString();
       edtPass = findViewById(R.id.txt_password);
        pass = edtPass.getEditableText().toString();
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( edtUser.getEditableText().toString().isEmpty() && edtPass.getEditableText().toString().isEmpty() ) {
                    edtUser.setError("Requerido");
                    edtPass.setError("Requerdio");
                } else {

                    user = edtUser.getEditableText().toString();
                    pass = edtPass.getEditableText().toString();
                    consumoLogin exec = new consumoLogin();
                    exec.execute();
                }

            }
        });
    }


    private class consumoLogin extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return wsLogin();
        }

        @Override
        protected void onPreExecute() {
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

    }


    public String wsLogin() {
        String METHOD_NAME = "login";
        String NAMESPACE = "http://servicio/";
        String URL = "http://25.3.113.141:8080/WS_TALLER/wsCliente";
        String SOAP_ACTION = NAMESPACE + METHOD_NAME;


        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            //se añade aca el id de la orden en vez del 1012
            request.addProperty("arg0", user);
            request.addProperty("arg1", pass);


            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            soapEnvelope.dotNet = false;
            soapEnvelope.setOutputSoapObject(request);
            try {

                HttpTransportSE transport = new HttpTransportSE(URL);

                transport.call(SOAP_ACTION, soapEnvelope);


                SoapObject soapObject = (SoapObject) soapEnvelope.getResponse();
                // res = soapObject.toString();
                Log.e("Respuesta", soapObject.getProperty("idUser").toString());
                if (soapObject.getProperty("idUser").toString().isEmpty()){
                    edtUser.setError("Requerido");
                    edtPass.setError("Requerdio");
                }else{
                    Intent intnt = new Intent(this, MainActivity.class);
                    intnt.putExtra("idUser", soapObject.getProperty("idUser").toString());
                    startActivity(intnt);
                }

            }catch (IOException ioe){
                Log.e("IOException", ioe.toString());
            }
            catch (XmlPullParserException xmlExeption){
                Log.e("XmlPullParserException", xmlExeption.toString());
            }

        } catch (Exception ex) {
            sms = "error!! " + ex.getMessage();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (!isFinishing()){
                        new AlertDialog.Builder(LoginActivity.this)
                                .setTitle("Alerta")
                                .setMessage("El usuario o contraseña no coinciden")
                                .setCancelable(false)
                                .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).show();
                    }
                }
            });
            Log.e("ERROR", sms);
        }
        return res;
    }
}