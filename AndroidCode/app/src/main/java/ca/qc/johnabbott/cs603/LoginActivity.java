package ca.qc.johnabbott.cs603;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText username = (EditText)this.findViewById(R.id.etUsername);
        EditText password = (EditText)this.findViewById(R.id.etPassword);
        Button login = (Button)this.findViewById(R.id.btnLogin);
        Button createAcc = (Button)this.findViewById(R.id.btnCreateAccount);
        login.setOnClickListener(aClick);
        createAcc.setOnClickListener(aClick);
        Intent i = new Intent(this, MainActivity.class);
        //Intent i = getIntent();
    }

    View.OnClickListener aClick= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnLogin:
                    startMainActivity();
                    break;
                case R.id.btnCreateAccount:
                    startCreateAccountActivity();
                    break;

            }
        }
    };
    public void startMainActivity()
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void startCreateAccountActivity()
    {
        Intent i = new Intent(this, CreateAccountActivity.class);
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_activity2, menu);
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
