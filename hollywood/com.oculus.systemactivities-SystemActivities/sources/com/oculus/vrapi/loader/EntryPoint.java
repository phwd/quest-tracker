package com.oculus.vrapi.loader;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class EntryPoint {
    private static final String DRIVER_LOADER_CLASS = "com.oculus.systemdriver.DriverLoader";
    private static final String DRIVER_PACKAGE = "com.oculus.systemdriver";
    private static final String TAG = "VrDriverRouter";

    EntryPoint() {
    }

    private static Context createDriverContext(Context appContext) {
        try {
            return appContext.createPackageContext(DRIVER_PACKAGE, 3);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Couldn't load host package", e);
        }
    }

    private static Class getLoadClass(Context driverContext) {
        try {
            return Class.forName(DRIVER_LOADER_CLASS, true, driverContext.getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't find target class", e);
        }
    }

    private static Method getLoadMethod(Class loadClass, String methodName) {
        try {
            return loadClass.getMethod(methodName, Context.class, Context.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Couldn't find target method", e);
        }
    }

    public static String load(Context appContext, Context routerContext, int productVersion, int majorVersion, int minorVersion, int patchVersion, int driverVersion) {
        Log.v(TAG, "Routing VrApi to driver package");
        Context driverContext = createDriverContext(appContext);
        try {
            return (String) getLoadMethod(getLoadClass(driverContext), "loadLegacy").invoke(null, appContext, driverContext, Integer.valueOf(productVersion), Integer.valueOf(majorVersion), Integer.valueOf(minorVersion), Integer.valueOf(patchVersion), Integer.valueOf(driverVersion));
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't invoke method", e);
        } catch (InvocationTargetException e2) {
            throw new RuntimeException("Couldn't invoke method", e2);
        }
    }

    public static long load32(Context appContext, Context routerContext, int productVersion, int majorVersion, int minorVersion, int patchVersion, int driverVersion) {
        Log.v(TAG, "Routing VrApi to driver package");
        Context driverContext = createDriverContext(appContext);
        try {
            return ((Long) getLoadMethod(getLoadClass(driverContext), "load32").invoke(null, appContext, driverContext, Integer.valueOf(productVersion), Integer.valueOf(majorVersion), Integer.valueOf(minorVersion), Integer.valueOf(patchVersion), Integer.valueOf(driverVersion))).longValue();
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't invoke method", e);
        } catch (InvocationTargetException e2) {
            throw new RuntimeException("Couldn't invoke method", e2);
        }
    }
}
