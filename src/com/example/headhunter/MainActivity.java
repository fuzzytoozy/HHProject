package com.example.headhunter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;



public class MainActivity extends FragmentActivity implements DatePickerFragment.DatePasser {

	TextView tvDate1;
	EditText edit_name;
	EditText edit_post;
	EditText edit_salary;
	EditText edit_phone;
	EditText edit_mail;
	Spinner genderSp;
	Button b1;
	Button new_act;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        tvDate1 = (TextView) findViewById(R.id.tvDate);
        b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View vArg){
        	    	DialogFragment newFragment = new DatePickerFragment();
        	    	newFragment.show(getSupportFragmentManager(), "datePicker");
        	}
        });
        
        new_act = (Button) findViewById(R.id.button_send);
        new_act.setOnClickListener(new OnClickListener(){
        	
        	public void onClick(View v){
        		
        		Intent dataIntent = new Intent(v.getContext(),
        				SecondActivity.class);
        		
        		edit_name = (EditText) findViewById(R.id.edit_name);
        		edit_post = (EditText) findViewById(R.id.edit_post);
        		edit_salary = (EditText) findViewById(R.id.edit_salary);
        		edit_phone = (EditText) findViewById(R.id.edit_phone);
        		edit_mail = (EditText) findViewById(R.id.edit_mail);
        		tvDate1 = (TextView) findViewById(R.id.tvDate);
        		genderSp = (Spinner) findViewById(R.id.spinner1);
        		
        		dataIntent.putExtra("name", edit_name.getText().toString());
        		dataIntent.putExtra("date", tvDate1.getText().toString());
        		dataIntent.putExtra("gender", genderSp.getSelectedItem().toString());
        		dataIntent.putExtra("post", edit_post.getText().toString());
        		dataIntent.putExtra("salary", edit_salary.getText().toString());
        		dataIntent.putExtra("phone", edit_phone.getText().toString());
        		dataIntent.putExtra("mail", edit_mail.getText().toString());

        		startActivityForResult(dataIntent,0);
        	}
        	
        });
        } 
        

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
    	super.onActivityResult(requestCode, resultCode, data);
        switch(resultCode){
        
        case Activity.RESULT_OK:
        if(data.getStringExtra("response").toString().trim().length()!= 0){
        	AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
			  builder.setCancelable(false);
			  builder.setMessage("Ответ работодателя:\n" +
			  		data.getStringExtra("response").toString());
			  builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog,int which){
						dialog.dismiss();
					}
        });
			  AlertDialog alert = builder.create();
			  alert.show();
       }
        
        case 0:

        }
    	
    }

    @Override
    public void onBackPressed(){
    	finish();
    }
    
    public void returnDate(String date){
    	tvDate1.setText(date);
}
}