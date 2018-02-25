package nyc.c4q.android_final_dogsapp.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nyc.c4q.android_final_dogsapp.R;
import nyc.c4q.android_final_dogsapp.model.Dogs;

/**
 * Created by bobbybah on 2/25/18.
 */

public class DogViewHolder extends RecyclerView.ViewHolder {
    ImageView dogImage;


    public DogViewHolder(View itemView) {
        super(itemView);

        dogImage = itemView.findViewById(R.id.dog_images);
    }

    public void onBind(Dogs dogs){
        String dogIMG_URL = dogs.getMessage();
        Picasso.with(dogImage.getContext())
                .load(dogIMG_URL)
                .into(dogImage);
    }
}
