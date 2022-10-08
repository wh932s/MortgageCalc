package com.example.mortgagev31;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;




public class DataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_activitydata);
        updateDataActivityView();




    }

    private void updateDataActivityView() {

        Mortgage mortgage = MainActivity.mortgage;


        if( mortgage.getYears( ) == 10 ) {
            RadioButton rb10 = findViewById( R.id.rb10 );
            rb10.setChecked( true );
        } else if( mortgage.getYears( ) == 15 ) {
            RadioButton rb15 = findViewById( R.id.rb15 );
            rb15.setChecked( true );
        } // else do nothing (default is 30)

        EditText amountET = findViewById( R.id.data_amount );
        amountET.setText( "" + mortgage.getAmount( ) );
        EditText rateET = findViewById( R.id.data_rate );
        rateET.setText( "" + mortgage.getRate( ) );

    }



    private void updateMortgage() {
        Mortgage mortgage = MainActivity.mortgage;


//Finding radio buttons and setting year length
        RadioButton rb10 =  findViewById( R.id.rb10 );
        RadioButton rb15 =  findViewById( R.id.rb15 );

        int years = 30;

        if( rb10.isChecked( ) )
            years = 10;

        else if( rb15.isChecked( ) )
            years = 15;

        mortgage.setYears(years);

//Finding and reading values in edit text views
        EditText amountET =  findViewById( R.id.data_amount );
        String amountString = amountET.getText( ).toString( );


        EditText rateET =  findViewById( R.id.data_rate );
        String rateString = rateET.getText( ).toString( );


        try {
            float amount = Float.parseFloat( amountString );
            mortgage.setAmount( amount );

            float rate = Float.parseFloat( rateString );
            mortgage.setRate( rate );

//Passing in default preferences
            mortgage.setPreferences(this);

        } catch( NumberFormatException nfe ) {
            mortgage.setAmount( 100000.0f );
            mortgage.setRate( .035f );
        }
    }
    public void goBack(View view) {
        updateMortgage();
        this.finish();

        overridePendingTransition( R.anim.fade_in_and_scale, 0 );

    }

}
