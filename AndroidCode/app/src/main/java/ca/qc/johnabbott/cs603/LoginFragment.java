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

import ca.qc.johnabbott.cs603.Globals.Environment;

/**
 * Created by benjamin on 3/27/2015.
 */
public class LoginFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        Button loginBtn = (Button) rootView.findViewById(R.id.btnLogin);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String validated = validateInfo(rootView);
                Context rootContext = rootView.getContext();
                if(validated.isEmpty()){
                    Intent drawAct = new Intent(rootContext, DrawActivity.class);
                    startActivity(drawAct);
                    getActivity().finish();
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
}
