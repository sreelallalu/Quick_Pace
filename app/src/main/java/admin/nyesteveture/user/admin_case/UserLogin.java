package admin.nyesteveture.user.admin_case;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
public class UserLogin extends AppCompatActivity {
TextView register;
    EditText _name,_pass;
    Button _login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);
        register=(TextView)findViewById(R.id.user_register);
        _name=(EditText)findViewById(R.id.user_email);
        _pass=(EditText)findViewById(R.id.user_password);
        _login=(Button)findViewById(R.id.user_login);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserLogin.this,UserRegister.class);
                startActivity(intent);

            }
        });
        _login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendValue();
            }
        });
    }

    private void SendValue() {
        String name_=_name.getText().toString();
        String pass_=_pass.getText().toString();
        if(name_.isEmpty()||!android.util.Patterns.EMAIL_ADDRESS.matcher(name_).matches()){
            return;
        }else
            if(pass_.isEmpty()){
                return;
            }
        else{

                HashMap<String,String> hashmap=new HashMap<>();
                hashmap.put("tag","userLogin");
                hashmap.put("email",name_);
                hashmap.put("password",pass_);

                new PostASynClass(UserLogin.this, hashmap, new PostASynClass.Response() {
                    @Override
                    public void Response(String s) {
                        Log.e("result",s);
                        try{
                            JSONObject ss = new JSONObject(s);
                            if (ss.getInt("success")==1) {
                                String g = ss.getString("user_details");
                                JSONObject cf = new JSONObject(g);
                                String id = cf.getString("id");
                                String name = cf.getString("Name");
                                Intent df=new Intent(UserLogin.this,UserManipage.class);
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
                .make(findViewById(R.id.loginmmm), message, Snackbar.LENGTH_SHORT);


        View sbView = snackbar.getView();
        sbView.setBackgroundColor(Color.parseColor("#A981CA"));
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.BLACK);
        snackbar.show();




    }
}
