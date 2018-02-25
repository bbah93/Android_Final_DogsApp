package nyc.c4q.android_final_dogsapp.controller;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nyc.c4q.android_final_dogsapp.PhotoActivity;
import nyc.c4q.android_final_dogsapp.R;

/**
 * Created by bobbybah on 2/25/18.
 */

public class DogsAdapter extends RecyclerView.Adapter<DogsAdapter.ViewHolder> {
    ArrayList<String> dogIMG_URL;
    public DogsAdapter(){
        this.dogIMG_URL = new ArrayList<>();
    }

    public void setDogIMG_URL(ArrayList<String> dogIMG_URL){
        this.dogIMG_URL = dogIMG_URL;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Picasso.with(holder.itemView.getContext()).load(dogIMG_URL.get(position)).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(),PhotoActivity.class);
                intent.putExtra("breedName", dogIMG_URL.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dogIMG_URL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.dog_images);
        }
    }

}
