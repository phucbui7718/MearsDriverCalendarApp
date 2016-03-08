package mears.mearsdrivercalendarapp;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import controllers.AuthenticationController;
import services.AuthenticationService;


public class MainActivity extends AppCompatActivity {

    public AuthenticationController authenticationController;
    private String BASE_URL = "http://localhost:8090";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


      //matching buttons and editTexts between Java and XML.
      Button loginButton = (Button) findViewById(R.id.loginButton);
      EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        EditText passwordEditText= (EditText) findViewById(R.id.passwordTextEdit);


        //On click, will create a custom AsyncTask to handle networking properties
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new LoginTask().execute(BASE_URL);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    //Login AsyncTask
    class LoginTask extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... params) {
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(org.springframework.http.MediaType.TEXT_HTML);
            HttpEntity<String> entity = new HttpEntity<String>(requestHeaders);
            RestTemplate restTemplate = new RestTemplate();

            return null;
        }
    }
}



