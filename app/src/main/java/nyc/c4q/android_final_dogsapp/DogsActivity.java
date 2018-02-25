package nyc.c4q.android_final_dogsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import nyc.c4q.android_final_dogsapp.Networking.Dog_Caller;
import nyc.c4q.android_final_dogsapp.controller.DogsAdapter;
import nyc.c4q.android_final_dogsapp.model.Dogs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class DogsActivity extends AppCompatActivity {

    private static final String TAG = "DogsActivity";
    private Retrofit retrofit;
    private String breedName;
    private RecyclerView recyclerView;
    private DogsAdapter breedAdapter;

    SharedPreferences loginInfo;
    private static final String SHARED_PREFS_KEY = "mySharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs);
        recyclerView = findViewById(R.id.recyclerview);

        loginInfo = getApplicationContext().getSharedPreferences(SHARED_PREFS_KEY,MODE_PRIVATE);

        Intent intent = getIntent();
        if (intent != null) {
            breedName = intent.getStringExtra("breed");
            Log.d(TAG, "onCreate: " + intent.getStringExtra("breed"));
        } else if (savedInstanceState != null){
            breedName = savedInstanceState.getString("breed");
        } else {
            breedName = "";
        }

        breedAdapter = new DogsAdapter();
        GridLayoutManager layoutManager;
        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            layoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        } else {
            layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        }
        recyclerView.setAdapter(breedAdapter);
        recyclerView.setLayoutManager(layoutManager);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/breed/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        if (!breedName.equals("")){
            Dog_Caller breedService = retrofit.create(Dog_Caller.class);
            Call<Dogs.breedList> breedImgListCall = breedService.getList(breedName);
            breedImgListCall.enqueue(new Callback<Dogs.breedList>() {
                @Override
                public void onResponse(Call<Dogs.breedList> call, Response<Dogs.breedList> response) {
                    if (response.isSuccessful()) {
                        breedAdapter.setDogIMG_URL(response.body().getMessage());
                    }
                }
                @Override
                public void onFailure(Call<Dogs.breedList> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (!breedName.equals("")){
            outState.putString("breed", breedName);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logging:
                loginInfo.edit().remove("username").commit();
                Intent intent = new Intent(DogsActivity.this, LoginActivity.class);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
