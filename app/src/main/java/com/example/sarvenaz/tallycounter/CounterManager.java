package com.example.sarvenaz.tallycounter;

import android.content.Context;
import android.content.SharedPreferences;

public class CounterManager {

    private int counter;
    private static SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;

    public CounterManager(Context context) {
        mSharedPreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
    }

    public void updateCounter(String s){

        String status=s;
        //fetch the counter from the sharedPreferences, update it based on which button is clicked by the user.
        counter = getCounter();
        if (status == "plus"){
            counter += 1;
        }
        else {  //status=="minus")
            counter -= 1;
        }
        //store the value of the counter on the sharedPreferences in order to prevent reseting the value everytime the app starts
        editor = mSharedPreferences.edit();
        editor.putInt("Counter", counter);
        editor.commit();
    }

    public int getCounter(){
        counter = mSharedPreferences.getInt("Counter",0);
        return counter;
    }
    public void resetCounter(){
        counter = 0;
        editor = mSharedPreferences.edit();
        editor.putInt("Counter", counter);
        editor.commit();
    }
}
