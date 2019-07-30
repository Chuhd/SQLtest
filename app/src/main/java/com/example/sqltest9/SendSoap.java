package com.example.sqltest9;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;


class SendSoap extends AsyncTask<String, String ,String>{  //번호, 진행 중 쓰이는 값, 출력값,


    private  String newid = null;
    private  String newpwd =  null;
    private String query = null;

    public SendSoap() {

    }


    public SendSoap(String query){
        //this.newid = newid;
        //this.newpwd = newpwd;
        this.query = query;
    }


    private static final String SOAP_ACTION2 = "http://210.121.210.22:8088/JSON_ExecuteNonQuery";
    private static final String SOAP_METHOD2 = "JSON_ExecuteNonQuery";

    private static final String NAMESPACE = "http://210.121.210.22:8088/";
    private static final String URL = "http://210.121.210.22:8088/WebService/DbAccess.asmx";



    @Override
    protected String doInBackground(String... strings) {

        String result = null;
        try {
            SoapObject request = new SoapObject(NAMESPACE,SOAP_METHOD2);
            //request.addProperty("script", "insert into users (id, pwd) values ('"+ newid +"', '"+ newpwd +"')");//전달 파라미터

            request.addProperty("script", query);//전달 파라미터

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            envelope.dotNet=true;
            HttpTransportSE transportSE = new HttpTransportSE(URL);
            transportSE.call(SOAP_ACTION2,envelope);//웹서비스 호출
            result= envelope.getResponse().toString(); //결과 출력

        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {

            Log.e("SOAP RESULT","Error : "+ e.getMessage());
            result=""+e.fillInStackTrace();


        }

        return result;
    }





}




