package admin.nyesteveture.user.admin_case;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lalu on 4/15/2017.
 */
public class ViewRequest extends android.support.v4.app.Fragment {
    private RecyclerView recyclerView;
   private LinearLayoutManager mLayoutManager;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.greenlayout,container,false);
        recyclerView=(RecyclerView)v.findViewById(R.id.greenrecycle);
        final FragmentActivity cc=getActivity();

        mLayoutManager = new LinearLayoutManager(cc);
        recyclerView.setLayoutManager(mLayoutManager);
        View_Notification(v);
        return v;
    }

    private void View_Notification(View v) {


        HashMap<String,String> hashmap=new HashMap<>();
        hashmap.put("tag","view_request");
        hashmap.put("status","1");
        new PostASynClass(getActivity(), hashmap, new PostASynClass.Response() {
            @Override
            public void Response(String s) {

               try{
                   Log.e("response",s);
                JSONObject df=new JSONObject(s);
                int succ=df.getInt("success");
                if(succ==1)
                {
                    List<View_Req_Items> list=new ArrayList<>();
                    JSONArray frt=df.getJSONArray("View_Requests");
                    for(int i=0;i<frt.length();i++)
                    {
                        View_Req_Items dip=new View_Req_Items();
                        JSONObject op=frt.getJSONObject(i);


                        String sub=op.getString("Subject");
                        String poli=op.getString("Request police");
                        String fire=op.getString("FireForce");
                        String hospi=op.getString("Hospital");
                        String ambu=op.getString("ambulance");
                        String loca=op.getString("Location");

                       dip.setAmbulance(ambu);
                       dip.setFireForce(fire);
                       dip.setHospital(hospi);
                       dip.setLocation(loca);
                       dip.setSubject(sub);
                       dip.setRequest_police(poli);

                        list.add(dip);
                    }
                    // RecyclerView   recyclerView=(RecyclerView)vfindViewById(R.id.recyclerview);
                    View_Request_Adapter     mAdapter = new View_Request_Adapter(list, getActivity());
                        /*RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                        recyclerView.setLayoutManager(mLayoutManager);*/
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();

                }
                else
                {

                }





            } catch (JSONException e) {


            }



        }
        });
    }
}
