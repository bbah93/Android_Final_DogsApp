package nyc.c4q.android_final_dogsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.BaseBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private static final String SHARED_PREFS_KEY = "mySharedPrefs";
    EditText username;
    EditText password;
    Button submitButton;
    SharedPreferences login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username_container);
        password = (EditText) findViewById(R.id.password_container);
        submitButton = (Button) findViewById(R.id.submit_button);

        login = getApplicationContext().getSharedPreferences(SHARED_PREFS_KEY,MODE_PRIVATE);
        final SharedPreferences.Editor editor = login.edit();

        if (login.contains("username") && login.contains("password")){
            Intent intent = new Intent(LoginActivity.this,BreedsActivity.class);
            intent.putExtra("LoginData", SHARED_PREFS_KEY);
            startActivity(intent);
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (username.getText().toString().isEmpty()) {
                        toastUSer();
                    }
                    if (password.getText().toString().isEmpty() && !username.getText().toString().isEmpty()) {
                        toastPassword();
                    }

                    if (password.getText().toString().contains(username.getText().toString())) {
                    toastBadPass();
                        }
                else if (!password.getText().toString().isEmpty() && !username.getText().toString().isEmpty()){
                    editor.putString("username", username.getText().toString());
                    editor.putString("password", password.getText().toString());
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this,BreedsActivity.class);
                    intent.putExtra("LoginData", SHARED_PREFS_KEY);
                    startActivity(intent);
                }
            }
        });

    }

    public void toastUSer(){
        Toast.makeText(getApplicationContext(), "Please enter a username", Toast.LENGTH_LONG).show();
    }
    public void toastPassword(){
        Toast.makeText(getApplicationContext(), "Please enter a password", Toast.LENGTH_LONG).show();
    }
    public void toastBadPass(){
        Toast.makeText(getApplicationContext(), "Your username should not be in password", Toast.LENGTH_LONG).show();
    }

}
