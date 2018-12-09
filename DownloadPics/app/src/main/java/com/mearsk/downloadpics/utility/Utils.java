package com.mearsk.downloadpics.utility;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

public class Utils {
    public static final int Store_Read_Permission = 111;
    public static final int Store_Write_Permission = 112;


    public static boolean askForReadWritePermission(Activity activity) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int hasWriteContactsPermission = activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                int hasReadContactsPermission = activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
                if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                    activity.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            Store_Write_Permission);
                    return false;
                }
                if (hasReadContactsPermission != PackageManager.PERMISSION_GRANTED) {
                    if (!activity.shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    }
                    activity.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            Store_Read_Permission);
                    return false;
                }
            }
        } catch (Exception e) {
        }
        return true;
    }


    public static boolean checkForReadWritePermission(Activity activity) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int hasWriteContactsPermission = activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                int hasReadContactsPermission = activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
                if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
                if (hasReadContactsPermission != PackageManager.PERMISSION_GRANTED) {
                    if (!activity.shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    }
                    return false;
                }
            }
        } catch (Exception e) {
        }
        return true;
    }
}
