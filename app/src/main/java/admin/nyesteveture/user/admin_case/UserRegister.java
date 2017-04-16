package admin.nyesteveture.user.admin_case;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by lalu on 4/15/2017.
 */
public class UserRegister extends AppCompatActivity {

    EditText name,email,pass,phone;
    Button regi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_regi);
        name=(EditText)findViewById(R.id.user_reg_name);
        email=(EditText)findViewById(R.id.user_regi_email);
        pass=(EditText)findViewById(R.id.user_reg_pass);
        phone=(EditText)findViewById(R.id.user_reg_phone);
         regi=(Button)findViewById(R.id.user_reg_register);
        regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendValue();
            }
        });
    }

    private void SendValue() {
        String name_=name.getText().toString();
        String email_=email.getText().toString();
        String pass_=pass.getText().toString();
        String phone_=phone.getText().toString();

       if(name_.isEmpty())
       {
           name.setError("invalid");
           return;
       }
        else
           if(email_.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(email_).matches()){
               email.setError("invalid");
               return ;
           }
    else
               if(pass_.isEmpty())
               {
                   pass.setError("invalid");
                 return;
               }
        else
               if(phone_.isEmpty()||phone_.length()<10){
                   phone.setError("invalid");
                   return;

               }
        else
               {

                   HashMap<String,String> hashmap=new HashMap<>();
                   hashmap.put("tag","user_register");
                   hashmap.put("name",name_);
                   hashmap.put("phone",phone_);
                   hashmap.put("email",email_);
                   hashmap.put("password",pass_);
                   new PostASynClass(UserRegister.this, hashmap, new PostASynClass.Response() {
                       @Override
                       public void Response(String s) {
                           Log.e("result",s);
                          try{
                               JSONObject ss = new JSONObject(s);
                               if (ss.getInt("success")==1) {
                                   String g = ss.getString("user_details");
                                   JSONObject cf = new JSONObject(g);
                                   String id = cf.getString("id");
                                   String name = cf.getString("username");
                                   Intent df=new Intent(UserRegister.this,UserManipage.class);
                                   df.putExtra("iduser",id);
                                   df.putExtra("nameuser",name);
                                   startActivity(df);
                                   finish();

                               } else {
                                   showsnackbar("Registration error");
                               }

                          }catch(JSONException e)
                          {
                              showsnackbar("Registration error");
                          }
                           }

                   });


               }


    }
    public void showsnackbar(String message){


        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.userregyu), message, Snackbar.LENGTH_SHORT);


        View sbView = snackbar.getView();
        sbView.setBackgroundColor(Color.parseColor("#A981CA"));
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.BLACK);
        snackbar.show();




    }

}
