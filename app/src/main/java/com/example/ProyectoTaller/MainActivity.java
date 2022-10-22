package com.example.ProyectoTaller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.Proxy;

public class MainActivity extends AppCompatActivity {

    String id, msj;
    String resultado = "";
    Object resultString;
    private TextView result;

    String URL = "http://25.3.113.141:8080/WS_TALLER/wsOrden?WSDL";
    String NAMESPACE = "http://servicio/";
    String METHOD_NAME = "getOrdenById";
    String SOAP_ACTION = "http://servicio/wsOrden/getOrdenByIdRequest";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText searchId = findViewById(R.id.global_search);
        result = findViewById(R.id.result);
        searchId.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                id = textView.getEditableText().toString();
               WebServicePostData exec = new WebServicePostData();
               exec.execute();
                return false;
            }
        });

    }


    private class WebServicePostData extends  AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            try {

                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                request.addProperty("", id);
                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                soapEnvelope.dotNet = true;
                soapEnvelope.setOutputSoapObject(request);

                HttpTransportSE transportSE = new HttpTransportSE(URL);
                transportSE.call(SOAP_ACTION, soapEnvelope);
                resultString = soapEnvelope.getResponse();
            } catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

    }

    public String wsGetOrderBy() {
        String METHOD_NAME = "getOrdenById";
        String NAMESPACE = "http://servicio/";
        String URL = "http://25.3.113.141:8080/WS_TALLER/wsOrden?WSDL";
        String SOAP_ACTION = NAMESPACE + METHOD_NAME;

        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            //request.addProperty("", id);
            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.implicitTypes = true;
            soapEnvelope.setOutputSoapObject(request);
            //  HttpTransportSE httpTransport = getHttpTransportSE(URL);

            //  httpTransport.call(SOAP_ACTION, soapEnvelope);
            //resultString = (SoapPrimitive) soapEnvelope.getResponse();



            try {
                SoapObject soapObject = (SoapObject) soapEnvelope.getResponse();
                soapObject = (SoapObject) soapObject.getProperty("response");
                String cliente = soapObject.getProperty("cliente").toString();
                String equipo = soapObject.getProperty("equipo").toString();
                String estado = soapObject.getProperty("estado").toString();
                String numeroOrden = soapObject.getProperty("numeroOrden").toString();
                String observaciones = soapObject.getProperty("observaciones").toString();

                resultado = String.valueOf(cliente);

                Log.i("RESPONSE", String.valueOf(soapObject)); // see output in the console
            } catch (SoapFault e) {
                // TODO Auto-generated catch block
                Log.e("SOAPLOG", e.getMessage());
                e.printStackTrace();
            }

        } catch (Exception ex) {
            msj = "ERROR: " + ex.getMessage();
            Toast.makeText(getApplicationContext(),
                    "Fracaso", Toast.LENGTH_SHORT).show();
        }
        return resultado;
    }

}
