package mears.mearsdrivercalendarapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import entities.DriverRequest;



public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


      //matching buttons and editTexts between Java and XML.
      final Button loginButton = (Button) findViewById(R.id.loginButton);
      final EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        final EditText passwordEditText= (EditText) findViewById(R.id.passwordTextEdit);
        final TextView statusTextView = (TextView) findViewById(R.id.statusText);

        //On click, will create a custom AsyncTask to handle networking properties
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(usernameEditText.getText().equals("") || passwordEditText.getText().toString().equals("")){

                    Toast.makeText(getApplicationContext(), "Please enter Driver ID and/or password!", Toast.LENGTH_LONG).show();
                }

                else

                {
                //Validating driverNum and password.
                try {

                    statusTextView.setText(new LoginTask().execute(new AsyncTaskParams(usernameEditText.getText().toString(), passwordEditText.getText().toString())).get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


                if (statusTextView.getText().toString().equals("200")) {
                    Intent calendarIntent = new Intent(v.getContext(), calendarActivity.class);
                    calendarIntent.putExtra("userID", usernameEditText.getText().toString());
                    calendarActivity.events.clear();
                    startActivity(calendarIntent);

                }

                else  {

                    statusTextView.setText("Wrong login information!");
                    usernameEditText.setText("");
                    passwordEditText.setText("");

                }
            }}
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


    //Custom Login params for AsyncTask
    private static class AsyncTaskParams{

        String driverNum;
        String password;

        AsyncTaskParams(String driverNum, String password) {
            this.driverNum = driverNum;
            this.password = password;
        }
    }

    private static class RequestAsyncParam{
    }

    //Login AsyncTask
    class LoginTask extends AsyncTask<AsyncTaskParams, String, String>{
        private ResponseEntity<String> response;
        @Override
        protected String doInBackground(AsyncTaskParams... params)  {


                // Create Rest Template for request
                RestTemplate restTemplate = new RestTemplate();

                String url = "http://10.0.2.2:8099/authenticate/login/{driverNum}/{password}";
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

                //input the driverNum and Password
                Map<String, String> vars = new HashMap<String, String>();
                vars.put("driverNum", params[0].driverNum);
                vars.put("password", params[0].password);

                try {
                    //Receive response from server.
                    response = restTemplate.getForEntity(url, String.class, vars);

                } catch (HttpClientErrorException e) {
                    Log.e("DRIVERSCHEDULE", e.getLocalizedMessage(), e);
                }


           return response.getStatusCode().toString();

        }


    }

    //InsertRequest AsyncTask
   class RequestAsyncTask extends AsyncTask<Void, Void, Void>{

            private  DriverRequest newDriverRequest;

        RequestAsyncTask(DriverRequest newDriverRequest){
            this.newDriverRequest = newDriverRequest;
        }

        @Override
        protected Void doInBackground(Void... params) {

            // Create Rest Template for request
            RestTemplate restTemplate = new RestTemplate();

            String url = "http://10.0.2.2:8099/get/schedule/{driverNum}";
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());


            //Populate the data

            return null;
        }
    }


    //Switching to Calendar without Login.
    public void goToCalendar(View v){
        Intent intent = new Intent(this.getApplicationContext(), calendarActivity.class);
        startActivity(intent);
    }


}



