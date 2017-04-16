package admin.nyesteveture.user.admin_case;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by lalu on 4/15/2017.
 */
public class DepartRegister extends AppCompatActivity {
    EditText email,pass,phone,h_name,location;
    Spinner sp;
    Button regi;
    String type[]={"police","ambulance","hospital","fire force"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.depart_regi);
        sp=(Spinner) findViewById(R.id.type_spinner);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(DepartRegister.this,android.R.layout.simple_list_item_1,type);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new ITEMSDD());
       // sp.setSE(new ONITEMCLICK());
        h_name=(EditText)findViewById(R.id.deaprt_reg_headname);
        email=(EditText)findViewById(R.id.depart_regi_email);
        pass=(EditText)findViewById(R.id.depart_reg_pass);
        phone=(EditText)findViewById(R.id.depart_reg_phone);
        regi=(Button)findViewById(R.id.depa_reg_register);
        location=(EditText)findViewById(R.id.depart_regi_location) ;
        regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendValue();
            }
        });
    }

    private void SendValue() {
        String name_=sp.getSelectedItem().toString();
        String h_name_=h_name.getText().toString();
        String email_=email.getText().toString();
        String pass_=pass.getText().toString();
        String phone_=phone.getText().toString();
        String location_=location.getText().toString();

        if(name_.isEmpty())
        {

            return;
        }else
        if(h_name_.isEmpty())
        {
            h_name.setError("invalid");
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
        if(location_.isEmpty())
        {
            location.setError("invalid");
            return;
        }
        else
        if(phone_.isEmpty()||phone_.length()<10){
            phone.setError("invalid");
            return;

        }
        else
        {
            Log.e("name",name_);

            HashMap<String,String> hashmap=new HashMap<>();
            hashmap.put("tag","department_reg");
            hashmap.put("name",name_);
            hashmap.put("head_name",h_name_);
            hashmap.put("phone",phone_);
            hashmap.put("email",email_);
            hashmap.put("location",location_);
            hashmap.put("password",pass_);
            new PostASynClass(DepartRegister.this, hashmap, new PostASynClass.Response() {
                @Override
                public void Response(String s) {
               Log.e("result",s);
                    try{
                        JSONObject ss = new JSONObject(s);
                        if (ss.getInt("success")==1) {
                            String g = ss.getString("Department");
                            JSONObject cf = new JSONObject(g);
                            String id = cf.getString("departmentid");
                            String name = cf.getString("Department");
                            Intent df=new Intent(DepartRegister.this,DepartmentMAin.class);
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
                .make(findViewById(R.id.vbghu), message, Snackbar.LENGTH_SHORT);


        View sbView = snackbar.getView();
        sbView.setBackgroundColor(Color.parseColor("#A981CA"));
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.BLACK);
        snackbar.show();




    }


    private class ONITEMCLICK implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }
    }

    private class ITEMSDD implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}
