package com.universeapp.Adapter;



import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;
import com.universeapp.Model.MainListModel;
import com.universeapp.R;


public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.FinalorderViewHolder>{

    private Context context;
    MainListModel[] data;
    public MainListAdapter(Context context, MainListModel[] data){
        this.context=context;
        this.data=data;
    }

    @Override
    public FinalorderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.main_item_layout,parent,false);
        return new FinalorderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FinalorderViewHolder holder, int position) {
        MainListModel list= data[position];

        holder.title.setText(list.getTitle());
        Picasso.get()
                .load(list.getImage())
                 .into(holder.imageView);

        System.out.println(list.getImage());

    }

    @Override
    public int getItemCount() {
        try{return data.length;}
        catch (Exception e){

        }
        return 0;
    }

    public class FinalorderViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        de.hdodenhof.circleimageview.CircleImageView imageView;
        public FinalorderViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            imageView=itemView.findViewById(R.id.image);
        }
    }
}