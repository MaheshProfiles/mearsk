package com.mearsk.weatherreport.utils;

import android.util.Log;
import com.mearsk.weatherreport.BuildConfig;

@SuppressWarnings("unused")
public final class MRLog {

    private MRLog() {
        // hide constructor
    }


    public static int v(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            return Log.v(tag, msg);
        }
        return 0;
    }

    public static int v(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG) {
            return Log.v(tag, msg, tr);
        }
        return 0;
    }

    public static int d(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            return Log.d(tag, msg);
        }
        return 0;
    }

    public static int d(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG) {
            return Log.d(tag, msg, tr);
        }
        return 0;
    }

    public static int i(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            return Log.i(tag, msg);
        }
        return 0;
    }

    public static int i(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG) {
            return Log.i(tag, msg, tr);
        }
        return 0;
    }

    public static int w(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            return Log.w(tag, msg);
        }
        return 0;
    }

    public static int w(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG) {
            return Log.w(tag, msg, tr);
        }
        return 0;
    }

    public static int w(String tag, Throwable tr) {
        if (BuildConfig.DEBUG) {
            return Log.w(tag, tr);
        }
        return 0;
    }

    public static int e(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            return Log.e(tag, msg);
        }
        return 0;
    }

    public static int e(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG) {
            return Log.e(tag, msg, tr);
        }
        return 0;
    }

    public static int wtf(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            return Log.wtf(tag, msg);
        }
        return 0;
    }

    public static int wtf(String tag, Throwable tr) {
        if (BuildConfig.DEBUG) {
            return Log.wtf(tag, tr);
        }
        return 0;
    }

    public static int wtf(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG) {
            return Log.wtf(tag, msg, tr);
        }
        return 0;
    }

    public static String getStackTraceString(Throwable tr) {
        if (BuildConfig.DEBUG) {
            return Log.getStackTraceString(tr);
        }
        return null;
    }

    public static int println(int priority, String tag, String msg) {
        if (BuildConfig.DEBUG) {
            return Log.println(priority, tag, msg);
        }
        return 0;
    }
}
