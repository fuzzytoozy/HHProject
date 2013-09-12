package com.example.headhunter;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;


public class DatePickerFragment extends DialogFragment 
				implements DatePickerDialog.OnDateSetListener{
	
	DatePasser passer;
	
	public interface DatePasser{
		public void returnDate(String date);
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		passer = (DatePasser) getActivity();
		
		return new DatePickerDialog(getActivity(),this,year,month,day);
	}
	
	public void onDateSet(DatePicker view,int year, int month, int day){
		
		StringBuilder sb = new StringBuilder();
		sb.append(day);
		sb.append("/");
		sb.append(month+1);
		sb.append("/");
		sb.append(year);
		String fDate = sb.toString();
		if(passer != null)
		{
			passer.returnDate(fDate);
		}
	}
	
}
