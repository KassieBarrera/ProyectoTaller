package com.example.ProyectoTaller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.ProyectoTaller.Adapter.ItemAdapter;
import com.example.ProyectoTaller.Model.OrderItem;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList <OrderItem> itemsList = new ArrayList<OrderItem>();
    String idUser;
    ItemAdapter adaptador;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       idUser =  getIntent().getStringExtra("idUser");
        rv = findViewById(R.id.list_orders);



        itemsList.add(new OrderItem("Kassie", "iPhone", "En limpieza", "1002", "El dispositivo presentaba mucho polvo", "Emily Morales"));
        itemsList.add(new OrderItem("Kassie", "iPhone", "En limpieza", "1002", "El dispositivo presentaba mucho polvo", "Emily Morales"));
        itemsList.add(new OrderItem("Kassie", "iPhone", "En limpieza", "1002", "El dispositivo presentaba mucho polvo", "Emily Morales"));



        adaptador = new ItemAdapter(itemsList, getApplicationContext());
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setAdapter(adaptador);


       /* searchId.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                idSearch = Integer.parseInt(textView.getEditableText().toString());
                //postData();
                cardInfo.setVisibility(View.VISIBLE);
                segundoPlano exec = new segundoPlano();
                exec.execute();
                return false;
            }


        });*/

    }


    private class menuConection extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return wsMenuList();
        }

        @Override
        protected void onPreExecute() {
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

    }

    public String wsMenuList() {
        String METHOD_NAME = "getListaOrdenByCliente";
        String NAMESPACE = "http://servicio/";
        String URL = "http://25.3.113.141:8080/WS_TALLER/wsOrden?wsdl";
        String SOAP_ACTION = NAMESPACE + METHOD_NAME;


        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        request.addProperty("arg0", 4);


        try  {

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            HttpTransportSE transport = new HttpTransportSE(URL);
            transport.call(SOAP_ACTION, soapEnvelope);

            SoapObject response = (SoapObject) soapEnvelope.bodyIn;


        }  catch (Exception exception)   {
            Log.e("MainError:", exception.toString());
        }
        return "";
    }


}
