package com.hakr.aman.codeforcesapp.Adapter;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//import com.example.aman.codeforcesapp.model.Post;
import com.hakr.aman.codeforcesapp.R;
import com.hakr.aman.codeforcesapp.model.userinfo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<userinfo> plist; public String rank1;

    public MyAdapter(Context context, List<userinfo> plist,String rank1) {
        this.context = context;
        this.plist = plist;this.rank1=rank1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.asingleuser,parent,false);
       /* Toast.makeText(context,"HI ",Toast.LENGTH_LONG).show();;
*/      return new MyViewHolder(view,rank1);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        userinfo p=plist.get(position);
           holder.rating.setText("Rating: "+p.getRating());
           holder.lastOnlineTimeSeconds.setText("Friend Of: "+p.getFriendOfCount()+" users");
           holder.organization.setText("Organization: "+p.getOrganization());
           holder.handle.setText("Handle: "+p.getHandle());
        holder.rank.setText("Rank: "+p.getRank());
        holder.name.setText("Name: "+p.getFirstName());

        //  holder.body.setText("Body: "+p.getBody());
    }

    @Override
    public int getItemCount() {
        return plist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView organization, lastOnlineTimeSeconds,rating,rank,handle,name;
        private CardView usercard;
        public MyViewHolder(@NonNull View view,String rank1) {
            super(view);
            usercard=view.findViewById(R.id.usercard);
           rating=view.findViewById(R.id.urating);
           rank=view.findViewById(R.id.urank);
           organization=view.findViewById(R.id.uorganization);
           handle=view.findViewById(R.id.uhandle);
           name=view.findViewById(R.id.uname);
           lastOnlineTimeSeconds=view.findViewById(R.id.ulastOnlineTimeSeconds);
           name.setTextColor(Color.WHITE);
            rating.setTextColor(Color.WHITE);
            rank.setTextColor(Color.WHITE);
            organization.setTextColor(Color.WHITE);
            lastOnlineTimeSeconds.setTextColor(Color.WHITE);
            handle.setTextColor(Color.WHITE);
            if(rank1!=null){
            if(rank1.toLowerCase().equals("expert")){
                usercard.setCardBackgroundColor(Color.parseColor("#3f51b5"));
            }else if(rank1.toLowerCase().equals("specialist")){
                usercard.setCardBackgroundColor(Color.parseColor("#81c784"));

            }else if(rank1.toLowerCase().equals("pupil")){
                usercard.setCardBackgroundColor(Color.parseColor("#43a047"));

            }else if(rank1.toLowerCase().equals("newbie")){
                usercard.setCardBackgroundColor(Color.parseColor("#78909c"));


            }
            else if(rank1.toLowerCase().equals("master")){
                usercard.setCardBackgroundColor(Color.parseColor("#ffa726"));


            }  else if(rank1.toLowerCase().equals("international master")){
                usercard.setCardBackgroundColor(Color.parseColor("#ffa726"));

            }
            else if(rank1.toLowerCase().equals("candidate master")){
                usercard.setCardBackgroundColor(Color.parseColor("#7b1fa2"));

            }
            else if(rank1.toLowerCase().equals("grandmaster")){
                usercard.setCardBackgroundColor(Color.parseColor("#e53935"));

            }else if(rank1.toLowerCase().equals("international grandmaster")){
                usercard.setCardBackgroundColor(Color.parseColor("#e53935"));

            }else if(rank1.toLowerCase().equals("legendary grandmaster")){
                usercard.setCardBackgroundColor(Color.parseColor("#e53935"));

            }}
            //   body=view.findViewById(R.id.body);
        }

    }
}
