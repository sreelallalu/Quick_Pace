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
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lalu on 4/15/2017.
 */

public class View_Nofication_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{  private List<Dep_Noti> contactlist;
    Context activity;
    public View_Nofication_Adapter(List<Dep_Noti>contactlist,Context activity){
        this.contactlist = contactlist;
        this.activity = activity;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewlistg,parent,false);
        return new ViewHolder1(itemView);


    }


    class ViewHolder1 extends RecyclerView.ViewHolder {
        public TextView name,type;


        public ViewHolder1(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.bh_text);

        }  }


    @Override  public int getItemCount() {
        return contactlist.size();  }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Dep_Noti upload = contactlist.get(position);
        if (holder instanceof ViewHolder1)
        {
            ((ViewHolder1) holder).name.setText(contactlist.get(position).getMessage());
            ((ViewHolder1) holder).name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog=new Dialog(activity);
                    dialog.setContentView(R.layout.view_list);
                    dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                    TextView name = (TextView)dialog.findViewById(R.id.dial_name);
                    TextView  msg = (TextView)dialog.findViewById(R.id.dial_msg);
                    name.setText(contactlist.get(position).getUsername());
                    msg.setText(contactlist.get(position).getMessage());
                    dialog.show();



                }
            });



        }






    }

}