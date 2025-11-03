package com.example.akashadiyath;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

//import com.mashood.kaudisorders.R;
//import com.mashood.kaudisorders.disorder.DisorderListActivity;
//import com.squareup.picasso.Picasso;

//import com.example.wecan.ui.dashboard.DashboardFragment;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {


    private LayoutInflater inflater;
    private ArrayList<model> dataModelArrayList;
    private Context c;

    public adapter(Context ctx, ArrayList<model> dataModelArrayList) {
        c = ctx;
        inflater = LayoutInflater.from(c);
        this.dataModelArrayList = dataModelArrayList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        final model omodel = dataModelArrayList.get(position);
        Picasso.get().load(config.imageurl+ omodel.getProductimage()).into(holder.image);


        holder.productname.setText( "productname:"+dataModelArrayList.get(position).getProduct_name());
        holder.price.setText("price:"+ dataModelArrayList.get(position).getProductprice());


        holder.productlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, productfullactivity.class);
                intent.putExtra("productname",dataModelArrayList.get(position).getProduct_name());
                intent.putExtra("price",dataModelArrayList.get(position).getProductprice() );
                intent.putExtra("package", dataModelArrayList.get(position).getPackage1());
                intent.putExtra("description", dataModelArrayList.get(position).getDescription());
                intent.putExtra("image",dataModelArrayList.get(position).getProductimage());
                intent.putExtra("id",dataModelArrayList.get(position).getId());


                c.startActivity(intent);

//                        if (!dataModelArrayList.get(position).getImage().equals("")) {
//            Picasso.get.load(config.imgurl+dataModelArrayList.get(position).getImage()).into(holder.image);
            }

        });
//
    }


    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }


    public void filterList(ArrayList<model> filteredSongs) {
        this.dataModelArrayList = filteredSongs;
        notifyDataSetChanged();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {


        public
        CardView productlist;
        TextView productname,price;
        ImageView image;




        public MyViewHolder(View itemView) {
            super(itemView);
            productlist=itemView.findViewById(R.id.bmx);
            productname= itemView.findViewById(R.id.xyz);
            price= itemView.findViewById(R.id.mon);
            image= itemView.findViewById(R.id.abc);


        }

    }
}