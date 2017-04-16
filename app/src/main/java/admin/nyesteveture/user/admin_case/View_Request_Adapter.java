

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

public class View_Request_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{  private List<View_Req_Items> contactlist;
    Context activity;
    public View_Request_Adapter(List<View_Req_Items>contactlist,Context activity){
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
        View_Req_Items upload = contactlist.get(position);
        if (holder instanceof ViewHolder1)
        {
            ((ViewHolder1) holder).name.setText(contactlist.get(position).getSubject());
            ((ViewHolder1) holder).name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog=new Dialog(activity);
                    dialog.setContentView(R.layout.view_req_list);
                    dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                    TextView sub = (TextView)dialog.findViewById(R.id.v_r_sub);
                    TextView  loca = (TextView)dialog.findViewById(R.id.v_r_loca);
                    TextView  polic = (TextView)dialog.findViewById(R.id.v_r_poli);
                    TextView  hospi = (TextView)dialog.findViewById(R.id.v_r_hosp);
                    TextView  ambu = (TextView)dialog.findViewById(R.id.v_r_ambu);
                    TextView  firet = (TextView)dialog.findViewById(R.id.v_r_fire);
                    sub.setText(contactlist.get(position).getSubject());
                    loca.setText(contactlist.get(position).getLocation());
                    polic.setText(contactlist.get(position).getRequest_police());
                    hospi.setText(contactlist.get(position).getHospital());
                    ambu.setText(contactlist.get(position).getAmbulance());
                    firet.setText(contactlist.get(position).getFireForce());
                    dialog.show();



                }
            });



        }






    }

}