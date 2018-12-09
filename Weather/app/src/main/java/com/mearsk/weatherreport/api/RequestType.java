package com.mearsk.weatherreport.api;


import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class RequestType {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({GET, POST, DELETE, PUT, PATCH})
    public @interface Interface {
    }

    public static final int GET = 1;
    public static final int POST = 2;
    public static final int DELETE = 3;
    public static final int PUT = 4;
    public static final int PATCH = 5;
}
