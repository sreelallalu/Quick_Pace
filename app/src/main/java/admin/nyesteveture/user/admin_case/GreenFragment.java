package admin.nyesteveture.user.admin_case;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lalu on 4/15/2017.
 */
public class GreenFragment extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener {
RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;
    SwipeRefreshLayout swipeView;
    View vc=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.greenlayout,container,false);
        recyclerView=(RecyclerView)v.findViewById(R.id.greenrecycle);
      final FragmentActivity cc=getActivity();
        swipeView = (SwipeRefreshLayout) v.findViewById(R.id.swiperrr);
        swipeView.setOnRefreshListener(this);
        swipeView.setColorSchemeColors(Color.GRAY, Color.GREEN, Color.BLUE,
                Color.RED, Color.CYAN);
        swipeView.setSize(SwipeRefreshLayout.DEFAULT);
         mLayoutManager = new LinearLayoutManager(cc);
        recyclerView.setLayoutManager(mLayoutManager);
        vc=v;
        GREEN_(v);
        return v;
    }
    private void GREEN_(final View v) {

        HashMap<String,String> hasmap=new HashMap<>();
        hasmap.put("tag","view_department");
        hasmap.put("status","1");
        new PostASynClass(getActivity(), hasmap, new PostASynClass.Response() {
            @Override
            public void Response(String s) {
                swipeView.setRefreshing(false);

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
                      //  RecyclerView   recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
                        Log.e("size",""+list.size());
                        FileuploadAdapterUpdate      mAdapter = new FileuploadAdapterUpdate(list, getActivity());
                       /* RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
                        recyclerView.setLayoutManager(mLayoutManager);*/

                        recyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }else
                    {
                        showsnackbar("something went wrong",v);
                    }





                } catch (JSONException e) {
                    showsnackbar("something went wrong",v);
                    swipeView.setRefreshing(false);

                }
            }
        });
    }
    public void showsnackbar(String message,View v){


        Snackbar snackbar = Snackbar
                .make(v.findViewById(R.id.vbbnm), message, Snackbar.LENGTH_SHORT);


        View sbView = snackbar.getView();
        sbView.setBackgroundColor(Color.parseColor("#A981CA"));
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.BLACK);
        snackbar.show();




    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                View v = null;
                GREEN_(vc);
            }
        },400);

    }
}
