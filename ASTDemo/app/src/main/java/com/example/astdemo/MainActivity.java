package com.example.astdemo;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity implements AysncResponse {

	ProgressBar progressBar;
	Button toastButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		toastButton = (Button) findViewById(R.id.totastButton);
		MyAsyncTask newTask = new MyAsyncTask();
		newTask.delegate = this;
		newTask.execute();

		toastButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Toast.makeText(getApplicationContext(), "It Works",
						Toast.LENGTH_LONG).show();
			}
		});

	}

	@Override
	public void processFinish(ArrayList<String> list) {
		setContentView(R.layout.list);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(), R.layout.row, list);

		ListView listView = (ListView) findViewById(R.id.list);
		listView.setAdapter(adapter);
	}

	@Override
	public void processUpdate(int progress) {
		progressBar.setProgress(progress);
	}

}
