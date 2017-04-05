package com.docomodigital.sdk.sampleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.docomodigital.sdk.Dcb;
import com.docomodigital.sdk.DcbCallback;
import com.docomodigital.sdk.DocomoUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dcb.with(this, "put here your apikey", "put here youe product id").recognise(new DcbCallback() {
            @Override
            public void onSuccess(DocomoUser dcbUser) {
                //User is recognised as Docomo Acquisition​​
                if (dcbUser.isSubscribed) {
                    //User is subscribed, goto ahead with product​
                    Toast.makeText(MainActivity.this, "User Subscribed, give me the access to the product please", Toast.LENGTH_SHORT).show();
                } else {
                    //User expired, not subscribed​
                    //user must pay again to access the product​
                    Toast.makeText(MainActivity.this, "User is expired, acquisition page will be opened", Toast.LENGTH_SHORT).show();
                    dcbUser.openAcquisitionPage(MainActivity.this);
                    finish();
                }
            }

            @Override
            public void onFailure() {
                //Normal user discover the app from goole play​
                //Here do normal thing of the app​

                Toast.makeText(MainActivity.this, "User not coming from direct carrier billind", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
