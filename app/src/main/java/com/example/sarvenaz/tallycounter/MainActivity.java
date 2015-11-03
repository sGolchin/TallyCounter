package com.example.sarvenaz.tallycounter;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private int numberOfDays;
    private Boolean isEnable=true;
    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;
    private CounterManager counterManager;
    //Eliminate findViewById calls by using @Bind on fields.
    @Bind(R.id.tvCounter) TextView counterView;
    @Bind(R.id.btMinus) Button btMinus;
    @Bind(R.id.btPlus) Button btPlus;
    @Bind(R.id.toolbar) Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing the GUI
        initGUI();

        setSupportActionBar(toolbar);
    }

    //This method is called to save the current state of the activity
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //check if the alert dialog is displaying or not
        Boolean dialogIsShown = alertDialog.isShowing();
        outState.putBoolean("DialogIsShown",dialogIsShown);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //if before changing the screen orienation the dialog was displayed, it will be displayed again after rotation
        if(savedInstanceState.getBoolean("DialogIsShown")){
            displayAlertDialog();
        }
    }


    private void initGUI() {

        counterManager = new CounterManager(this);
        builder = new AlertDialog.Builder(this);
        alertDialog = builder.create();
        //
        ButterKnife.bind(this);
        //When the app launches, the counter should be retrieved from the sharedPreferences! because it shouldn't be reset everytime the app starts.
        // Moreover, it should be checked if the counter is 0, then the minus button should be disable in order to prevent negative number
        numberOfDays=counterManager.getCounter();
        counterView.setText("" + numberOfDays);
        if(numberOfDays==0) {
            btMinus.setEnabled(false);
            isEnable=false;
        }
    }
    //Eliminate anonymous inner-classes for listeners by annotating methods with @OnClick and others.

    @OnClick(R.id.btPlus) void btPlus(){

             counterManager.updateCounter("plus");
             updateCounterView();
             //check if the minus button is not enable, that means the counter was 0, and now it is increased by 1, so the counter is 1 now and the minus button should be enabled again
             if (!isEnable) {
                 btMinus.setEnabled(true);
             }
    }

    @OnClick(R.id.btMinus) void btMinus(){

            counterManager.updateCounter("minus");
            updateCounterView();
          // check if the counter is zero, then the minus button should be disabled, because negative numbers are not allowed
            if (numberOfDays == 0) {
                btMinus.setEnabled(false);
                isEnable = false;
            }
        }


      public void updateCounterView(){
          //The value of the counter is displayed on the textView with bounce feature
          numberOfDays= counterManager.getCounter();
          counterView.setText("" + numberOfDays);
          bounceAnimation();
      }

    //start the bounce animation on counterView, this method should be called each time the value of counter is updated
    public void bounceAnimation(){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        counterView.startAnimation(animation);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //When the user clicks on the reset button, it checks if the counter is 0, then a toast will be displayed! but if not, the alertDialog will appear
        if (id == R.id.btReset) {
            numberOfDays=counterManager.getCounter();
            if(numberOfDays==0) {
                Toast.makeText(getApplicationContext(),"The counter is already 0!",Toast.LENGTH_SHORT).show();
            }else{
               // displayAlertDialog();
                displayAlertDialog();
            }
        }
        if(id==R.id.btShare){
            //By clicking on the Share button, it gives user the ability to send the counter plus predefined text to other apps
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            numberOfDays=counterManager.getCounter();
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Wooow, I haven't smoked for "+numberOfDays+" days!:D");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }

        return super.onOptionsItemSelected(item);
    }

     public void displayAlertDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Reset the counter!");
        builder.setMessage("Are you sure you want to reset the counter?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        counterManager.resetCounter();
                        updateCounterView();
                        // By reseting the counter, the value is 0, therefore the minus button should be disabled to prevent negative numbers
                        btMinus.setEnabled(false);
                        isEnable = false;

                    }
                });
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        alertDialog = builder.create();
        alertDialog.show();
    }

}
