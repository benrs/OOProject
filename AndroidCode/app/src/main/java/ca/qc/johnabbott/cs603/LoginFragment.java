package ca.qc.johnabbott.cs603;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ca.qc.johnabbott.cs603.AsyncTasks.AsynDone;
import ca.qc.johnabbott.cs603.AsyncTasks.AsyncLogin;
import ca.qc.johnabbott.cs603.Globals.Environment;

/**
 * Created by benjamin on 3/27/2015.
 */
public class LoginFragment extends Fragment implements AsynDone{
    private boolean good = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final AsynDone callback = this;
        final View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        Button loginBtn = (Button) rootView.findViewById(R.id.btnLogin);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String validated = validateInfo(rootView);
                Context rootContext = rootView.getContext();
                if(validated.isEmpty()) {
                    EditText username = (EditText) rootView.findViewById(R.id.loginUser);
                    EditText password = (EditText) rootView.findViewById(R.id.loginPassword);
                    String strUsername = username.getText().toString();
                    String strPass = password.getText().toString();
                    Toast displaySuccess = Toast.makeText(rootContext, "", Toast.LENGTH_SHORT);
                    AsyncLogin register = new AsyncLogin(rootView, callback);
                    register.execute(strUsername, strPass);
                }else{
                    Toast displayErrors = Toast.makeText(rootContext, validated, Toast.LENGTH_SHORT);
                    displayErrors.show();
                }
            }
        });

        return rootView;
    }

    public String validateInfo(View theView){
        String returnErrors = "";
        EditText email    = (EditText) theView.findViewById(R.id.loginUser);
        EditText password = (EditText) theView.findViewById(R.id.loginPassword);
        String strEmail = email.getText().toString();
        String strPass  = password.getText().toString();

        if(strEmail.isEmpty() || strPass.isEmpty()){
            returnErrors += "Fields cannot be empty\n";
        }

        if(!returnErrors.isEmpty()) {
            returnErrors = returnErrors.substring(0, returnErrors.length() - 1);
        }

        return returnErrors;
    }
    @Override
    public void done(String message){
        //this.registering = false;
        this.getView().findViewById(R.id.loadingPanel).setVisibility(View.INVISIBLE);
        Toast displayStatus = Toast.makeText(this.getView().getContext(), message, Toast.LENGTH_SHORT);
        if(message.equals("Successfully logged in"))
        {
            this.good = true;
            Intent drawAct = new Intent(this.getView().getContext(), DrawActivity.class);
            startActivity(drawAct);
            getActivity().finish();
        }
        else {
            this.good = false;
        }
        displayStatus.show();
    }
}
