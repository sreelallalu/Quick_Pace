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
public class DepartLogin extends AppCompatActivity {
    TextView register;

    EditText name,pass;
    Button login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.department_login);
        register=(TextView)findViewById(R.id.depa_register);
        name=(EditText)findViewById(R.id.depa_email);
        pass=(EditText)findViewById(R.id.depa_password);
        login=(Button)findViewById(R.id.depa_login);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DepartLogin.this,DepartRegister.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendLogin();
            }
        });
    }

    private void SendLogin() {
        String name_=name.getText().toString();
        String pass_=pass.getText().toString();
        if(name_.isEmpty())
        {
            name.setError("invalid");
            return;
        }
        else
            if(pass_.isEmpty())
            {
                pass.setError("invalid");
                return;
            }
        else
            {
                HashMap<String,String> hashmap=new HashMap<>();
                hashmap.put("tag","department_login");
                hashmap.put("email",name_);
                hashmap.put("password",pass_);
                new PostASynClass(DepartLogin.this, hashmap, new PostASynClass.Response() {
                    @Override
                    public void Response(String s) {
                        Log.e("response",s);
                        try{
                            JSONObject ss = new JSONObject(s);
                            if (ss.getInt("success")==1) {
                                String g = ss.getString("Department_details");
                                JSONObject cf = new JSONObject(g);
                                String id = cf.getString("id");
                                String name = cf.getString("Department");
                                Intent df=new Intent(DepartLogin.this,DepartmentMAin.class);
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
                .make(findViewById(R.id.kkopp), message, Snackbar.LENGTH_SHORT);


        View sbView = snackbar.getView();
        sbView.setBackgroundColor(Color.parseColor("#A981CA"));
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.BLACK);
        snackbar.show();




    }

}
