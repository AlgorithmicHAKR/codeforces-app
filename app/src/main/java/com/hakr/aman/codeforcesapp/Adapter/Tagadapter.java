package com.hakr.aman.codeforcesapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hakr.aman.codeforcesapp.R;
import com.hakr.aman.codeforcesapp.model.Question;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class Tagadapter extends RecyclerView.Adapter<Tagadapter.MyViewHolder> {
        private List<Question> tagsandcount;
        private Context context;private String rank;
    public Tagadapter(Context context, List<Question> tagsandcount,String rank) {
         this.context=context;this.rank=rank;
         this.tagsandcount=tagsandcount;
    }

    @NonNull
    @Override
    public Tagadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.asingletagandcount,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Tagadapter.MyViewHolder holder, int position) {
        Question q=tagsandcount.get(position);
        holder.tag.setText(q.getTag());
        holder.count.setText(String.valueOf(q.getCount()));
    }

    @Override
    public int getItemCount() {
        return tagsandcount.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tag,count;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tag=(TextView)itemView.findViewById(R.id.asingletagandcounttag);
            count=(TextView) itemView.findViewById(R.id.asingletagandcountcount);
        }
    }
}
