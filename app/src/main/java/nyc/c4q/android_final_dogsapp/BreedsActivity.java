package nyc.c4q.android_final_dogsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nyc.c4q.android_final_dogsapp.Networking.Dog_Caller;
import nyc.c4q.android_final_dogsapp.Networking.RetrofitClient;
import nyc.c4q.android_final_dogsapp.model.Dogs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BreedsActivity extends AppCompatActivity {

    TextView welcomeTV;
    ImageView terrierImage;
    TextView terrierName;

    ImageView poodleImage;
    TextView poodleName;

    ImageView spanielImage;
    TextView spanielName;

    ImageView retrieverImage;
    TextView retrieverName;

    SharedPreferences loginInfo;
    private static final String SHARED_PREFS_KEY = "mySharedPrefs";

    Dog_Caller dog_caller;
    private static final String TAG = "HELP!!";
    Dogs dBreed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeds);

        welcomeTV = (TextView) findViewById(R.id.welcome_textView);
        terrierImage = (ImageView) findViewById(R.id.terrier_image);
        terrierName = (TextView) findViewById(R.id.terrier_name);

        retrieverImage = findViewById(R.id.retriever_image);
        retrieverName = findViewById(R.id.retriever_name);

        spanielImage = findViewById(R.id.spaniel_image);
        spanielName = findViewById(R.id.spaniel_name);

        poodleImage = findViewById(R.id.poodle_image);
        poodleName = findViewById(R.id.poodle_name);

        terrierName.setText(R.string.Terrier);
        retrieverName.setText(R.string.Retriever);
        spanielName.setText(R.string.Spaniel);
        poodleName.setText(R.string.Poodle);


        Intent intent = getIntent();
        loginInfo = getApplicationContext().getSharedPreferences(SHARED_PREFS_KEY,MODE_PRIVATE);
        String userName = loginInfo.getString("username", null);

        if (userName.isEmpty()){
            Intent backintent = new Intent(BreedsActivity.this, LoginActivity.class);
            startActivity(backintent);
        } else{
            welcomeTV.setText("What kind of dog would you like to see, " + userName + "?");
        }
        Dog_Call("terrier");
        Dog_Call("retriever");
        Dog_Call("poodle");
        Dog_Call("spaniel");
    }






    public void Dog_Call(String dogBreed){
        dog_caller = RetrofitClient.getRetrofit("https://dog.ceo/api/breed/")
                .create(Dog_Caller.class);

        dog_caller.getDogImage(dogBreed).enqueue(new Callback<Dogs>() {
            @Override
            public void onResponse(Call<Dogs> call, Response<Dogs> response) {
                dBreed = response.body();
                if (dBreed.getMessage().contains("terrier")) {
                    Picasso.with(BreedsActivity.this).load(dBreed.getMessage()).into(terrierImage);
                }
                if (dBreed.getMessage().contains("spaniel")) {
                    Picasso.with(BreedsActivity.this).load(dBreed.getMessage()).into(spanielImage);
                }
                if (dBreed.getMessage().contains("retriever")) {
                    Picasso.with(BreedsActivity.this).load(dBreed.getMessage()).into(retrieverImage);
                }
                if (dBreed.getMessage().contains("poodle")) {
                    Picasso.with(BreedsActivity.this).load(dBreed.getMessage()).into(poodleImage);
                }
                Log.d(TAG, "onResponse " + BreedsActivity.this.dBreed);
            }

            @Override
            public void onFailure(Call<Dogs> call, Throwable t) {
                Log.d(TAG, "ONFAILURE ");
            }
        });
    }
}
