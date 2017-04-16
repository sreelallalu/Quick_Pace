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
public class ViewPhoto extends android.support.v4.app.Fragment {
   private RecyclerView recyclerView;
  private   LinearLayoutManager mLayoutManager;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.redlayout,container,false);
        recyclerView=(RecyclerView)v.findViewById(R.id.redrecycle);
        final FragmentActivity cc=getActivity();

        mLayoutManager = new LinearLayoutManager(cc);
        recyclerView.setLayoutManager(mLayoutManager);
        View_Notification(v);
        return v;
    }

    private void View_Notification(View v) {
        HashMap<String, String> hashmap = new HashMap<>();
        hashmap.put("tag", "view_photo");
        hashmap.put("status", "1");
        new PostASynClass(getActivity(), hashmap, new PostASynClass.Response() {
            @Override
            public void Response(String s) {
                        try {
                            Log.e("response",s);
                            JSONObject df = new JSONObject(s);
                            int succ = df.getInt("success");
                            if (succ == 1) {
                                List<Dep_Noti> list = new ArrayList<>();
                                JSONArray frt = df.getJSONArray("View_photo");
                                for (int i = 0; i < frt.length(); i++) {
                                    Dep_Noti dip = new Dep_Noti();
                                    JSONObject op = frt.getJSONObject(i);


                                    String head_name = op.getString("picname");
                                    String phone = op.getString("User ID");
                                    String satus = op.getString("Messege");
                                    String appoved = op.getString("pic_id");

                                    dip.setNofification_id(appoved);
                                    dip.setMessage(satus);
                                    dip.setUserid(phone);
                                    dip.setUsername(head_name);

                                    list.add(dip);
                                }
                                // RecyclerView   recyclerView=(RecyclerView)vfindViewById(R.id.recyclerview);
                                View_Photo_Adapter mAdapter = new View_Photo_Adapter(list, getActivity());
                        /*RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                        recyclerView.setLayoutManager(mLayoutManager);*/
                                recyclerView.setAdapter(mAdapter);
                                mAdapter.notifyDataSetChanged();

                            } else {

                            }


                        } catch (JSONException e) {


                        }


            }


        });
    }


}
