package ca.qc.johnabbott.cs603;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ca.qc.johnabbott.cs603.AsyncTasks.AsynDone;
import ca.qc.johnabbott.cs603.AsyncTasks.AsyncRegister;
import ca.qc.johnabbott.cs603.Globals.Environment;

/**
 * Created by benjamin on 3/27/2015.
 */
public class CreateFragment extends Fragment implements AsynDone {
    private boolean registering = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final AsynDone callback = this;
        final View rootView = inflater.inflate(R.layout.fragment_create_account, container, false);

        Button createBtn = (Button) rootView.findViewById(R.id.btnCreate);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String validated = validateInfo(rootView);
                Context rootContext = rootView.getContext();
                if(validated.isEmpty()){
                    EditText name     = (EditText) rootView.findViewById(R.id.createUser);
                    EditText email    = (EditText) rootView.findViewById(R.id.createEmail);
                    EditText password = (EditText) rootView.findViewById(R.id.createPassword);
                    String strName  = name.getText().toString();
                    String strEmail = email.getText().toString();
                    String strPass  = password.getText().toString();

                    if(!registering){
                        registering = true;
                        AsyncRegister register = new AsyncRegister(rootView, callback);
                        register.execute(strName, strEmail, strPass);
                    }
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
        EditText name     = (EditText) theView.findViewById(R.id.createUser);
        EditText email    = (EditText) theView.findViewById(R.id.createEmail);
        EditText password = (EditText) theView.findViewById(R.id.createPassword);
        String strName  = name.getText().toString();
        String strEmail = email.getText().toString();
        String strPass  = password.getText().toString();

        if(!strEmail.matches(Environment.EMAIL_PATTERN)){
            returnErrors += "Improper email\n";
        }

        if(strName.isEmpty() || strEmail.isEmpty() || strPass.isEmpty()){
            returnErrors += "Fields cannot be empty\n";
        }

        if(!returnErrors.isEmpty()) {
            returnErrors = returnErrors.substring(0, returnErrors.length() - 1);
        }

        return returnErrors;
    }

    @Override
    public void done(String message){
        this.registering = false;
        this.getView().findViewById(R.id.loadingPanel).setVisibility(View.INVISIBLE);
        Toast displayStatus = Toast.makeText(this.getView().getContext(), message, Toast.LENGTH_SHORT);
        displayStatus.show();
    }
}
