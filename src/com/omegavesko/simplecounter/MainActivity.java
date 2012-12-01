package com.omegavesko.simplecounter;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.omegavesko.holocounter.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ActionBar actionBar = getActionBar();
        actionBar.show();
        actionBar.setDisplayShowHomeEnabled(false);
        
        setTitle();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    


@Override
public boolean onOptionsItemSelected(MenuItem item)
{
	switch(item.getItemId())
	{
		case R.id.item_reset:
			reset();
			return true;
		case R.id.settingsButton:
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
			
	}
}

public void increment(View view)
{
	String counter_step = getPreference("counter_step");
	int counter_step_int;
	
	if(counter_step.equals("Unset"))
	{
		counter_step_int = 1;
	}
	else
	{
		counter_step_int = Integer.parseInt(counter_step);
	}
	
	TextView counterDisplay = (TextView) findViewById(R.id.number_display);
	
	String string_counter = counterDisplay.getText().toString();
	int int_counter = Integer.parseInt(string_counter);
	
	if(int_counter < (1000 - counter_step_int))
	{	
		int_counter = int_counter + counter_step_int;
	}
	
	string_counter = String.valueOf(int_counter);
	counterDisplay.setText(string_counter);
}

public void decrement(View view)
{
	String counter_step = getPreference("counter_step");
	int counter_step_int;
	
	if(counter_step.equals("Unset"))
	{
		counter_step_int = 1;
	}
	else
	{
		counter_step_int = Integer.parseInt(counter_step);
	}
	
	TextView counterDisplay = (TextView) findViewById(R.id.number_display);
	
	String string_counter = counterDisplay.getText().toString();
	int int_counter = Integer.parseInt(string_counter);
	
	if(int_counter > (-100 + counter_step_int))
	{
		int_counter = int_counter - counter_step_int;
	}
	
	
	string_counter = String.valueOf(int_counter);
	counterDisplay.setText(string_counter);
}

public void reset()
{
TextView counterDisplay = (TextView) findViewById(R.id.number_display);
	
	String string_counter = counterDisplay.getText().toString();
	int int_counter = Integer.parseInt(string_counter);
	
	int_counter = 0;
	
	string_counter = String.valueOf(int_counter);
	counterDisplay.setText(string_counter);
}



public String getPreference(String preference)
{
	SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
	String returnString = sharedPref.getString(preference, "Unset");
	
	return returnString;
}


public void setTitle()
{
	Button titleText = (Button) findViewById(R.id.titleText);
	
	String titlePreference = getPreference("counter_title");
	
	if(titlePreference.equals("Unset"))
	{
		titlePreference = "No Title";
	}
	
	if(titlePreference != "No Title" && titlePreference != "" && titlePreference != null)
	{
		titleText.setText("Currently counting: " + titlePreference);
	}
	else
	{
		titleText.setText(" ");
	}
} 
}