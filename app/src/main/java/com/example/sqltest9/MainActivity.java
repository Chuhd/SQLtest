package com.example.sqltest9;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.soap.Envelope;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

//    callSoap cs = new callSoap();
//    AsyncTask<String,String,String> at = cs.execute("","","");


    TextView tv;
    EditText et_id, et_pwd;

    Button btn;
    Button btn2;
    Button btn3;

    public static String inputid = null;
    public static String inputpwd = null;

    boolean idkey = false;
    boolean pwdkey = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.tv);
        et_id = findViewById(R.id.et_id);
        et_pwd = findViewById(R.id.et_pwd);


        btn = findViewById(R.id.btn);
        btn2 = findViewById(R.id.btn2);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputid = et_id.getText().toString();
                inputpwd = et_pwd.getText().toString();


                CallSoap cs = new CallSoap();
                AsyncTask<String, String, String> at = cs.execute("","","");


                String s1 = null;
                try {
                    s1 = at.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                ReadJson(s1);
                //at.cancel(true);
                //tv.setText(call_process());
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                register();


            }
        });
    }
    public void register(){


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.signin,null);

        final EditText et_newid= view.findViewById(R.id.newid);
        final EditText et_newpwd = view.findViewById(R.id.newpwd);
        btn3 = view .findViewById(R.id.btn3);

        builder.setView(view);



        final AlertDialog dialog = builder.create();
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newid = et_newid.getText().toString();
                String newpwd = et_newpwd.getText().toString();

//                아웃풋 입력 칸
                SendSoap ss = new SendSoap("insert into users (id, pwd) values ('"+ newid +"', '"+ newpwd +"')");
                AsyncTask<String, String, String> at2 = ss.execute("","","");


                String s2 = null;
                try {
                    s2 = at2.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //상단이 call_process() 과정 (at값을 변경가능하면 쓸 수 있다.)
                //                s2=call_process();





                Toast.makeText(MainActivity.this, newid + ", " + newpwd + " "+s2 + "_입력완료", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                //at2.cancel(true);
            }
        });
        dialog.show();

    }


//    public String call_process(){
//
//        String s = null;
//        AsyncTask<String,String,String> at = ss.execute("","","");
//        try {
//            //at2 -> at 로 바꿀것
//            s= at.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        return s;
//    }
    public void ReadJson(String s){

        try{
            JSONArray jsonArray = new JSONArray(s);


            for(int i =0 ; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String userid = jsonObject.getString("id");
                String userpwd = jsonObject.getString("pwd");

                if(userid!=null) {
                    if (userid.equals(inputid)) {
                        if (userpwd.equals(inputpwd)) {

                            idkey=true;
                            pwdkey=true;

                            break;
                        }else{
                            idkey=true;
                            break;
                        }
                    }

                }else Toast.makeText(this, "연결 실패", Toast.LENGTH_SHORT).show();


            }
            if(idkey){

                if (pwdkey){
                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(this,SecondPage.class);
                    startActivity(intent);
                    //finish();
                }else {
                    Toast.makeText(this, "Check your password", Toast.LENGTH_SHORT).show();
                }

            }else Toast.makeText(this, "Check your id", Toast.LENGTH_SHORT).show();



        } catch (JSONException e) {
            e.printStackTrace();
        }
        idkey=false;
        pwdkey=false;
    }
}



