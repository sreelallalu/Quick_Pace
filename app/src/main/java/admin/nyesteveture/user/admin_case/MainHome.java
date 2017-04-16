package admin.nyesteveture.user.admin_case;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by lalu on 4/15/2017.
 */

public class MainHome extends AppCompatActivity {
    ImageView user,admin,department;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        user=(ImageView)findViewById(R.id.main_user);
        admin=(ImageView)findViewById(R.id.main_admin);
        department=(ImageView)findViewById(R.id.main_depart);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainHome.this,UserLogin.class);
                startActivity(intent);
              //  finish();
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainHome.this,AdminLogin.class);
                startActivity(intent);
                //finish();

            }
        });
        department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainHome.this,DepartLogin.class);
                startActivity(intent);
                //finish();
            }
        });

    }
}
