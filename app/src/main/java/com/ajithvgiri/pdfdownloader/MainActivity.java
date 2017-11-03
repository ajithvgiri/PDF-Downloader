package com.ajithvgiri.pdfdownloader;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1;

    PermissionUtils permissionUtils;

    TextInputLayout urlTextInputLayout;
    EditText urlEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        permissionUtils = new PermissionUtils();

        urlTextInputLayout = (TextInputLayout) findViewById(R.id.urlTextInputLayout);
        urlEditText = (EditText) findViewById(R.id.urlEditText);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (permissionUtils.checkPermission(MainActivity.this, STORAGE_PERMISSION_REQUEST_CODE, view)) {
                    if (urlEditText.getText().toString().length() > 0) {
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlEditText.getText().toString())));
                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                    }

                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case STORAGE_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Snackbar.make(urlTextInputLayout, "Permission Granted, Now you can write storage.", Snackbar.LENGTH_LONG).show();
                } else {
                    //Snackbar.make(urlTextInputLayout, "Permission Denied, You cannot access storage.", Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }
}
