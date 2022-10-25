package com.example.ProyectoTaller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class LoginActivity extends AppCompatActivity {
     String user, pass, sms, res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button imgButton = findViewById(R.id.img_login);
        final TextView edtUser = findViewById(R.id.txt_user);
       user = edtUser.getText().toString();
        final TextView edtPass = findViewById(R.id.txt_password);
        pass = edtPass.getText().toString();
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( edtUser.getEditableText().toString().isEmpty() && edtPass.getEditableText().toString().isEmpty() ) {
                    edtUser.setError("Requerido");
                    edtPass.setError("Requerdio");
                } else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                    login exec = new login();
                    exec.execute();
                }

            }
        });
    }


    private class login extends AsyncTask<String, Void, String> {

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
        String METHOD_NAME = "getOrdenById";
        String NAMESPACE = "http://servicio/";
        String URL = "http://25.3.113.141:8080/WS_TALLER/wsOrden?wsdl";
        String SOAP_ACTION = NAMESPACE + METHOD_NAME;


        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            //se añade aca el id de la orden en vez del 1012
            request.addProperty("arg0", user);
            request.addProperty("arg1", pass);


            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            soapEnvelope.dotNet = false;
            soapEnvelope.setOutputSoapObject(request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);


            SoapObject soapObject = (SoapObject) soapEnvelope.getResponse();
            /*se solicitan aca los elementos de la respuesta del ws*/
            //  setText_(cliente, soapObject.getProperty("cliente").toString());
            res = soapObject.getProperty(0).toString();


        } catch (Exception ex) {
            sms = "error!! " + ex.getMessage();
            System.out.println(sms);

        }

        return res;
    }
}