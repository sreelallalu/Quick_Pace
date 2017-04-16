package admin.nyesteveture.user.admin_case;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lalu on 4/15/2017.
 */
public class AdMinPage extends AppCompatActivity {

    String adminId,_userName;
    RecyclerView recyclerView;
    Button green,red;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminpage);
        Intent io=getIntent();
        adminId=io.getStringExtra("iduser");
        _userName=io.getStringExtra("nameuser");
       // getSupportActionBar().setTitle(_userName);
        /*green=(Button)findViewById(R.id.bgreen);
        red=(Button)findViewById(R.id.bred);
*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        /* green.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 GREEN_();
             }
         });
     red.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             RED_();
         }*/


    // });


       /* mAdapter = new FileuploadAdapter(upload, HomeActivityStudent.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(mLayoutManager);
        recycler.setAdapter(mAdapter);

        mAdapter.setRecyclerView(recyclerView,
                studentList.size());
        mAdapter.Add(studentList);
        mAdapter.setMoreLoading(false);*/
    }
    /*private void RED_() {
        HashMap<String,String> hasmap=new HashMap<>();
        hasmap.put("tag","view_department");
        hasmap.put("status","0");
        new PostASynClass(AdMinPage.this, hasmap, new PostASynClass.Response() {
            @Override
            public void Response(String s) {

                try {
                    JSONObject df=new JSONObject(s);
                    int succ=df.getInt("success");
                    if(succ==1)
                    {
                        List<DepartmentList> list=new ArrayList<>();
                        JSONArray frt=df.getJSONArray("department_details");
                        for(int i=0;i<frt.length();i++)
                        {
                            DepartmentList dip=new DepartmentList();
                            JSONObject op=frt.getJSONObject(i);
                            String idd=op.getString("Department_id");
                            String name=op.getString("Name");
                            String loaction=op.getString("Location");
                            String head_name=op.getString("Head Name");
                            String phone=op.getString("Phone");
                            String satus=op.getString("Status");
                            String appoved=op.getString("Approved?");

                            dip.setAppoved(appoved);
                            dip.setSatus(satus);
                            dip.setPhone(phone);
                            dip.setHead_name(head_name);
                            dip.setLoaction(loaction);
                            dip.setName(name);
                            dip.setIdd(idd);
                            list.add(dip);
                        }
                     RecyclerView   recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
                        FileuploadAdapterUpdate1      mAdapter = new FileuploadAdapterUpdate1(list, AdMinPage.this);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();

                    }
                    else
                    {
                        showsnackbar("something went wrong");
                    }





                } catch (JSONException e) {
                    showsnackbar("something went wrong");

                }
            }
        });

    }*/

    boolean exit=false;
    @Override
    public void onBackPressed() {
        if (exit)
            System.exit(0);
        else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment=null;

            if(position==0)
            {
                fragment=new GreenFragment();


            }else if(position==1)
            {
                fragment=new RedFragment();

            }

            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "APPROVED";
                case 1:
                    return "PENDING";

            }
            return null;
        }
    }

    /*private void GREEN_() {

        HashMap<String,String> hasmap=new HashMap<>();
        hasmap.put("tag","view_department");
        hasmap.put("status","1");
        new PostASynClass(AdMinPage.this, hasmap, new PostASynClass.Response() {
            @Override
            public void Response(String s) {

                try {
                    JSONObject df=new JSONObject(s);
                    int succ=df.getInt("success");
                    if(succ==1)
                    {
                        List<DepartmentList> list=new ArrayList<>();
                        JSONArray frt=df.getJSONArray("department_details");
                        for(int i=0;i<frt.length();i++)
                        {
                            DepartmentList dip=new DepartmentList();
                            JSONObject op=frt.getJSONObject(i);
                            String idd=op.getString("Department_id");
                            String name=op.getString("Name");
                            String loaction=op.getString("Location");
                            String head_name=op.getString("Head Name");
                            String phone=op.getString("Phone");
                            String satus=op.getString("Status");
                            String appoved=op.getString("Approved?");

                               dip.setAppoved(appoved);
                               dip.setSatus(satus);
                               dip.setPhone(phone);
                               dip.setHead_name(head_name);
                               dip.setLoaction(loaction);
                               dip.setName(name);
                               dip.setIdd(idd);
                          list.add(dip);
                        }
                        RecyclerView   recyclerView=(RecyclerView)findViewById(R.id.recyclerview);

                        FileuploadAdapterUpdate      mAdapter = new FileuploadAdapterUpdate(list, AdMinPage.this);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }else
                    {
                        showsnackbar("something went wrong");
                    }





                } catch (JSONException e) {
                    showsnackbar("something went wrong");

                }
            }
        });
    }*/
    /*public void showsnackbar(String message){


        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.afddhd), message, Snackbar.LENGTH_SHORT);


        View sbView = snackbar.getView();
        sbView.setBackgroundColor(Color.parseColor("#A981CA"));
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.BLACK);
        snackbar.show();




    }*/

}
