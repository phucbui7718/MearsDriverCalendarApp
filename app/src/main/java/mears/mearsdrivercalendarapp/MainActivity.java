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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //matching buttons and editTexts between Java and XML.
//        Button loginButton = (Button) findViewById(R.id.loginButton);
//        EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);
//        EditText passwordEditText= (EditText) findViewById(R.id.passwordTextEdit);

        String loginPath = "android_asset/login.html";

        WebView loginWebView = (WebView) findViewById(R.id.loginVebView);
        loginWebView.loadUrl("file:///" + loginPath);


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
