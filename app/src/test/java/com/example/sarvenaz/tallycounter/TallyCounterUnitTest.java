package com.example.sarvenaz.tallycounter;

import android.content.Context;
import android.content.SharedPreferences;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TallyCounterUnitTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mainActivity ;
    private static SharedPreferences mSharedPreferences;
    private TextView textView;
    private Button btPlus;
    private Button btMinus;

    public TallyCounterUnitTest() {
        super( MainActivity.class);

    }
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mainActivity = getActivity();

        textView = (TextView) mainActivity.findViewById(R.id.tvCounter);
        btPlus = (Button) mainActivity.findViewById(R.id.btPlus);
        btMinus = (Button) mainActivity.findViewById(R.id.btMinus);
    }

    //Check if the number displayed on the textView is the same as the value stored in sharedPreferences
    @Test
    public void textView_equal_sharedPref() throws Exception{

        mSharedPreferences = getInstrumentation().getTargetContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        int intCounter = mSharedPreferences.getInt("Counter",0);
        String counter = Integer.toString(intCounter);
        assertEquals("text should show the value of the counter", counter, textView.getText());
    }

    //
    @Test
    public void test_btPlus() throws Exception{


        int oldTextViewContent = getTextViewContent();
        int newTextViewContent = oldTextViewContent + 1 ;
        mainActivity.runOnUiThread(new Runnable() {
                                       @Override
                                       public void run() {
                                           btPlus.performClick();
                                       }
                                   }
        );
        int currentTextViewContent = getTextViewContent();
        assertEquals("TextView is updated correctly when the plus button is clicked!", newTextViewContent,currentTextViewContent );
    }

    @SmallTest
    public void test_btMinus() throws Exception{

        int oldTextViewContent = getTextViewContent();
        int newTextViewContent = oldTextViewContent - 1;
        mainActivity.runOnUiThread(new Runnable() {
                                       @Override
                                       public void run() {
                                           btMinus.performClick();
                                       }
                                   }
        );
        int currentTextViewContent = getTextViewContent();
        assertEquals("TextView is updated correctly when the minus button is clicked!", newTextViewContent, currentTextViewContent);
    }

    public int getTextViewContent(){
        String TextViewContent =  (textView.getText()).toString();
        return Integer.parseInt(TextViewContent);
    }
}