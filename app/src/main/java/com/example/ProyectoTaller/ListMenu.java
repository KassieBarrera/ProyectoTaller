package com.example.ProyectoTaller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.net.Proxy;

public class ListMenu extends AppCompatActivity {

    String msj, res;
    int idSearch = 0;
    TextView cliente, estado;
    TextView equipo, tecnico, observacion;
    CardView cardInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu);


        EditText searchId = findViewById(R.id.global_search);
        cliente = findViewById(R.id.cliente);
        equipo = findViewById(R.id.equipo);
        tecnico = findViewById(R.id.tecnico);
        observacion = findViewById(R.id.observacion);
        estado = findViewById(R.id.estado);
        cardInfo = findViewById(R.id.card_info);
    }


    private class segundoPlano extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return wsTest();
        }

        @Override
        protected void onPreExecute() {
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

    }

    private void setText_(final TextView text, final String value){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
            }
        });
    }

    private final HttpTransportSE getHttpTransportSE(String MAIN_REQUEST_URL) {
        HttpTransportSE ht = new HttpTransportSE(Proxy.NO_PROXY, MAIN_REQUEST_URL, 60000);
        ht.debug = true;
        ht.setXmlVersionTag("<!--?xml version=\"1.0\" encoding= \"UTF-8\" ?-->");
        return ht;
    }


    public String wsTest() {
        String METHOD_NAME = "getListaOrdenByCliente";
        String NAMESPACE = "http://servicio/";
        String URL = "http://25.3.113.141:8080/WS_TALLER/wsOrden?wsdl";
        String SOAP_ACTION = NAMESPACE + METHOD_NAME;


        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            //se añade aca el id de la orden en vez del 1012
            request.addProperty("arg0", idSearch);


            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            soapEnvelope.dotNet = false;
            soapEnvelope.setOutputSoapObject(request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);


            SoapObject soapObject = (SoapObject) soapEnvelope.getResponse();
            /*se solicitan aca los elementos de la respuesta del ws*/
            setText_(cliente, soapObject.getProperty("cliente").toString());
            setText_(estado, soapObject.getProperty("estado").toString());
            setText_(equipo, soapObject.getProperty("equipo").toString());
            setText_(tecnico, soapObject.getProperty("tecnico").toString());
            setText_(observacion, soapObject.getProperty("observaciones").toString());


        } catch (Exception ex) {
            msj = "error!! " + ex.getMessage();
            System.out.println(msj);

        }
        return res;
    }
}