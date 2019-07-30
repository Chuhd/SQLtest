package com.example.sqltest9;

import android.content.Intent;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.sqltest9.MainActivity.inputid;
import static com.example.sqltest9.MainActivity.inputpwd;

//public class ReadJson {
//
//    public void ReadJson(String s){
//        boolean idkey = false;
//        boolean pwdkey = false;
//        try{
//        JSONArray jsonArray = new JSONArray(s);
//
//
//        for(int i =0 ; i<jsonArray.length(); i++){
//            JSONObject jsonObject = jsonArray.getJSONObject(i);
//            String userid = jsonObject.getString("id");
//            String userpwd = jsonObject.getString("pwd");
//
//            if(userid!=null) {
//                if (userid.equals(inputid)) {
//                    if (userpwd.equals(inputpwd)) {
//
//                        idkey=true;
//                        pwdkey=true;
//
//                        break;
//                    }else{
//                        idkey=true;
//                        break;
//                    }
//                }
//
//            }else {
//                Toast.makeText(this, "연결 실패", Toast.LENGTH_SHORT).show();
//            }
//
//
//        }
//        if(idkey){
//
//            if (pwdkey){
//                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(this,SecondPage.class);
//                startActivity(intent);
//                //finish();
//            }else {
//                Toast.makeText(this, "Check your password", Toast.LENGTH_SHORT).show();
//            }
//
//        }else Toast.makeText(this, "Check your id", Toast.LENGTH_SHORT).show();
//
//
//
//    } catch (
//    JSONException e) {
//        e.printStackTrace();
//    }
//    idkey=false;
//    pwdkey=false;
//}
//
//}
