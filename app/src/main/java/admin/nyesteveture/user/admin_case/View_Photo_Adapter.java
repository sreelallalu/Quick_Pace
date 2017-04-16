package admin.nyesteveture.user.admin_case;

/**
 * Created by lalu on 4/15/2017.
 */
// View_Photo_Adapter {

import java.util.List;



import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lalu on 4/15/2017.
 */

public class View_Photo_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{  private List<Dep_Noti> contactlist;
    Context activity;
    public View_Photo_Adapter(List<Dep_Noti>contactlist,Context activity){
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
                    try {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse(contactlist.get(position).getUsername()));

                        activity.startActivity(intent);
                    }catch (Exception e){}}
            });



        }






    }

}