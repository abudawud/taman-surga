package com.envoss.tamansurga;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class LandingActivity extends BaseActivity {
    public static final String IDENTITY = "identity";
    private final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    public static String guestID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        //setGuestID();
        if(!checkPermission(Manifest.permission.INTERNET))
            requestPermission(Manifest.permission.INTERNET);
        setSerial();
    }

    public void guestLogin(View v){
        Intent i = new Intent(LandingActivity.this, UserLandingActivity.class);
        i.putExtra(IDENTITY, guestID);
        startActivity(i);
    }

    private void setGuestID(){
        String id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        Log.d(TAG, "setGuestID: " + id);
    }

    private void setSerial(){
        if(Build.VERSION.SDK_INT > 26) {
            if(checkPermission(Manifest.permission.READ_PHONE_STATE))
                guestID = Build.getSerial();
            else{
                requestPermission(Manifest.permission.READ_PHONE_STATE);
            }
        }
        else {
            guestID = Build.SERIAL;
        }

        Log.d(TAG, "setSerial: " + guestID);
    }

    private boolean checkPermission(String permission){
        Log.d(TAG, "checkPermission: ");
        int permissionCheck = ContextCompat.checkSelfPermission(LandingActivity.this,
                permission);

        if(permissionCheck == PackageManager.PERMISSION_GRANTED)
            return true;

        return false;
    }

    private void requestPermission(String permission){
        Log.d(TAG, "requestPermission: ");
        if (ContextCompat.checkSelfPermission(LandingActivity.this,
                permission)
                != PackageManager.PERMISSION_GRANTED) {


            // No explanation needed, we can request the permission.

            ActivityCompat.requestPermissions(LandingActivity.this,
                    new String[]{permission},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    setSerial();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
