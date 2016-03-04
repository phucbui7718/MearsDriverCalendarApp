package mears.mearsdrivercalendarapp;

/**
 * Steve Hello World
 * Will says "System setup is such a pain!"
 * Steve Agrees
 * Test of VCS
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import controllers.AuthenticationController;
import services.AuthenticationService;

public class MainActivity extends AppCompatActivity {

    public AuthenticationController authenticationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //matching buttons and editTexts between Java and XML.
      Button loginButton = (Button) findViewById(R.id.loginButton);
      final EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);
      final EditText passwordEditText= (EditText) findViewById(R.id.passwordTextEdit);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                authenticationController.login(usernameEditText.getText().toString(), passwordEditText.getText().toString());

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
}
