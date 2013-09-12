package com.example.headhunter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

public class SpinnerActivity extends Activity implements OnItemSelectedListener {
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		setContentView(R.id.spinner1);
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				R.array.gender_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		spinner.setAdapter(adapter);
	}
	
	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
		
	}

	public void onNothingSelected(AdapterView<?> parent){
		
	}
}
