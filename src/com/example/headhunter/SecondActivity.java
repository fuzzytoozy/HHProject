package com.example.headhunter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends Activity{
	
	TextView comp_name;
	TextView birth_date;
	TextView post_name;
	TextView gender_value;
	TextView salary_value;
	EditText response;
	Button phone_number;
	Button e_mail;
	Button send_response;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		Intent recIntent = getIntent();
		comp_name = (TextView) findViewById(R.id.textName);
		birth_date = (TextView) findViewById(R.id.textDate);
		gender_value = (TextView) findViewById(R.id.textGender);
		post_name = (TextView) findViewById(R.id.textPost);
		salary_value = (TextView) findViewById(R.id.textSalary);
		phone_number = (Button) findViewById(R.id.textPhone);
		e_mail = (Button) findViewById(R.id.textMail);
		
		comp_name.setText(recIntent.getStringExtra("name"));
		birth_date.setText(recIntent.getStringExtra("date"));
		gender_value.setText(recIntent.getStringExtra("gender"));
		post_name.setText(recIntent.getStringExtra("post"));
		salary_value.setText(recIntent.getStringExtra("salary"));
		phone_number.setText(recIntent.getStringExtra("phone"));
		e_mail.setText(recIntent.getStringExtra("mail"));
		
		
		response = (EditText) findViewById(R.id.editResponse);
		send_response = (Button) findViewById(R.id.buttonSend);
		
		e_mail.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View V){
				Intent response_intent = new Intent(Intent.ACTION_SEND);
				response_intent.setType("plain/text");
				response_intent.putExtra(Intent.EXTRA_EMAIL,
						e_mail.getText().toString());
				response_intent.putExtra(Intent.EXTRA_SUBJECT, 
						"Вакансия "+post_name.getText().toString());
				response_intent.putExtra(Intent.EXTRA_TEXT, 
						response.getText().toString());
				
				startActivity(Intent.createChooser(response_intent, 
						"Отправить сообщение..."));
				
			}
		});
		
		
		phone_number.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
			try{
				Intent phone_intent = new Intent(Intent.ACTION_CALL);
				String phone = phone_number.getText().toString();
				if(phone.trim().length() == 0){
					throw new PhoneException();
				}
				phone_intent.setData(Uri.parse("tel:"+phone));
				startActivity(phone_intent);
			} 
			  catch(PhoneException myException){
				  AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
				  builder.setCancelable(false);
				  builder.setMessage("Мой юный друг, желавший дозвониться!\n"+
				  "Спешу скорей тебе я сообщить,\n"+
				  "Что поле телефона номера пустое.\n"+
				  "Кому же будем мы тогда звонить?");
				  builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
					
					  @Override
					public void onClick(DialogInterface dialog,int which){
						dialog.dismiss();
					}
				});
				  AlertDialog alert = builder.create();
				  alert.show();
			  }
		 }
		});
	
		send_response.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				Intent respIntent = new Intent(v.getContext(),MainActivity.class);
				respIntent.putExtra("response", response.getText().toString());
				setResult(Activity.RESULT_OK,respIntent);
				finish();
			}
			
		});
		
		}
	
    @Override
    public void onBackPressed(){
    	setResult(0);
    	this.finish();
    }
	
}