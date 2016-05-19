package com.flyou.girls.utils;

import android.util.Log;


public class DebugUtil {
    static String className;
    private DebugUtil(){}

    public static boolean isDebugEnable() {
        return true;
    }

    private static String createLog( String log ) {
        return log;
    }

    private static void getMethodNames(StackTraceElement[] sElements){
        String methodName = sElements[1].getMethodName();
        int lineNumber = sElements[1].getLineNumber();
        className = "{ " + sElements[1].getFileName() + " : " + lineNumber + " - " + methodName + " }";
    }

    public static void e(String message){
        if (!isDebugEnable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.e(className, createLog(message));
    } public static void e(String Tag ,String message){
        if (!isDebugEnable())
            return;

        Log.e(Tag, createLog(message));
    }

    public static void i(String message){
        if (!isDebugEnable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.i(className, createLog(message));
    }public static void i(String Tag , String message){
        if (!isDebugEnable())
            return;

        Log.i(Tag ,   createLog(message));
    }

    public static void d(String message){
        if (!isDebugEnable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.d(className, createLog(message));
    }

    public static void v(String message){
        if (!isDebugEnable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.v(className, createLog(message));
    }

    public static void w(String message){
        if (!isDebugEnable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.w(className, createLog(message));
    }

    public static void wtf(String message){
        if (!isDebugEnable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.wtf(className, createLog(message));
    }

}
