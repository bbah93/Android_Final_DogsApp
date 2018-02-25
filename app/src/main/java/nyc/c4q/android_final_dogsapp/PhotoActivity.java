package nyc.c4q.android_final_dogsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PhotoActivity extends AppCompatActivity {

        private static final String TAG ="PhotoActivity";
        private String breedName;
        private ImageView imageView;
        SharedPreferences loginInfo;
        private static final String SHARED_PREFS_KEY = "mySharedPrefs";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_photo);
            imageView = findViewById(R.id.dog_photo);
            Intent intent = getIntent();

            loginInfo = getApplicationContext().getSharedPreferences(SHARED_PREFS_KEY,MODE_PRIVATE);
            if (intent != null) {
                breedName = intent.getStringExtra("breed");
                Log.d(TAG, "onCreate: " + intent.getStringExtra("breed"));
            } else if (savedInstanceState != null){
                breedName = savedInstanceState.getString("breed");
            }
            else {
                breedName = "";
            }
            if(!breedName.equals("")){
                Picasso.with(PhotoActivity.this).load(breedName)
                        .fit()
                        .centerInside()
                        .into(imageView);

            }
        }

        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            if (!breedName.equals("")){
                outState.putString("breed", breedName);
            }
        }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu, menu);
            return true;
        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            switch (item.getItemId()) {
                case R.id.logging:
                    loginInfo.edit().remove("username").commit();
                    Intent intent = new Intent(PhotoActivity.this, LoginActivity.class);
                    startActivity(intent);

                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
}
