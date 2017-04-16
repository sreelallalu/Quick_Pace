package admin.nyesteveture.user.admin_case;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lalu on 4/15/2017.
 */

public class FileuploadAdapterUpdate1 extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{  private List<DepartmentList> contactlist;
    Context activity;
    public FileuploadAdapterUpdate1(List<DepartmentList>contactlist,Context activity){
        this.contactlist = contactlist;
        this.activity = activity;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.attch_file,parent,false);
        return new ViewHolder1(itemView);


    }


    class ViewHolder1 extends RecyclerView.ViewHolder {
        public TextView name,type;
        public LinearLayout vbm;

        public Button delete;
        public ViewHolder1(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.atta_name);
            type = (TextView)itemView.findViewById(R.id.atta_headname);
            delete = (Button) itemView.findViewById(R.id.attach_approved);
            vbm=(LinearLayout)itemView.findViewById(R.id.rtyuio);


        }  }


    @Override  public int getItemCount() {
        return contactlist.size();  }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DepartmentList upload = contactlist.get(position);
        if (holder instanceof ViewHolder1)
        {
            ((ViewHolder1) holder).name.setText(contactlist.get(position).getName());
            ((ViewHolder1) holder).type.setText(contactlist.get(position).getHead_name());

            ((ViewHolder1) holder).vbm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog=new Dialog(activity);
                    dialog.setContentView(R.layout.admin_viewlist);
                    dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                    TextView name = (TextView)dialog.findViewById(R.id.ad_dia_name);
                    TextView  location = (TextView)dialog.findViewById(R.id.ad_dia_location);
                    TextView  headname = (TextView)dialog.findViewById(R.id.ad_dia_headname);
                    TextView  phone = (TextView)dialog.findViewById(R.id.ad_dia_phone);
                    name.setText(contactlist.get(position).getName());
                    location.setText(contactlist.get(position).getLoaction());
                    headname.setText(contactlist.get(position).getHead_name());
                    phone.setText(contactlist.get(position).getPhone());

                    dialog.show();

                }
            });

            ((ViewHolder1) holder).delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    HashMap<String,String> hashmap=new HashMap<String, String>();
                    hashmap.put("tag","dept_approve");
                    hashmap.put("id",contactlist.get(position).getIdd());
                    hashmap.put("approve","1");



                    new PostASynClass(activity, hashmap, new PostASynClass.Response() {
                        @Override
                        public void Response(String s) {

                            Log.e("response",s);

                        }
                    });


                }
            });


        }






    }

}