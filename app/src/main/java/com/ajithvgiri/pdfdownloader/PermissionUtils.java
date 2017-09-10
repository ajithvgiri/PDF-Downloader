package com.ajithvgiri.pdfdownloader;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

/**
 * Created by ajithvgiri on 10/09/17.
 */

public class PermissionUtils {

    public PermissionUtils() {
    }

    public boolean checkPermission(Activity activity, int request_code, View view) {
        int result = 0;
        switch (request_code) {
            case 1:
                result = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
            default:
                break;
        }

        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestPermission(activity, request_code, view);
            return false;
        }
    }

    public void requestPermission(final Activity activity, int request_code, View view) {
        switch (request_code) {
            case 1:
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    Snackbar.make(view, activity.getString(R.string.permission_request_message), Snackbar.LENGTH_LONG).setAction("Allow", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Toast.makeText(activity, activity.getString(R.string.permission_request_message), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + activity.getPackageName()));
                            intent.addCategory(Intent.CATEGORY_DEFAULT);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            activity.startActivity(intent);
                        }
                    }).show();

                } else {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, request_code);
                }
                break;
            default:
                break;
        }
    }

}
