package com.example.sqltest9;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

class CallSoap extends AsyncTask<String, String ,String> {


    private static final String SOAP_ACTION = "http://210.121.210.22:8088/JSON_GetDataTable";
    private static final String SOAP_METHOD = "JSON_GetDataTable";

    private static final String SOAP_ACTION2 = "http://210.121.210.22:8088/JSON_ExecuteNonQuery";
    private static final String SOAP_METHOD2 = "JSON_ExecuteNonQuery";

    private static final String NAMESPACE = "http://210.121.210.22:8088/";
    private static final String URL = "http://210.121.210.22:8088/WebService/DbAccess.asmx";


    @Override
    protected String doInBackground(String... strings) {

        String result = null;
        try {
            SoapObject request = new SoapObject(NAMESPACE,SOAP_METHOD);
            request.addProperty("script","select * from users");//전달 파라미터

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            envelope.dotNet=true;
            HttpTransportSE transportSE = new HttpTransportSE(URL);
            transportSE.call(SOAP_ACTION,envelope);//웹서비스 호출
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