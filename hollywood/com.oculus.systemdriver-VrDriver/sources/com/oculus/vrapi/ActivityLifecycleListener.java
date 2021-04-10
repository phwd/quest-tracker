package com.oculus.vrapi;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class ActivityLifecycleListener implements Application.ActivityLifecycleCallbacks {
    public static final String TAG = "ActivityLifecycleListener";
    private static ActivityLifecycleListener activityLifecyleListener = null;
    private static long instancePtr = 0;
    private static CheckForReadyTask readyTask = null;

    private static native void nativeOnActivityPaused(long j);

    /* access modifiers changed from: private */
    public static native void nativeOnActivityReady(long j);

    public static boolean hasValidWindow(Context context) {
        Window window;
        if (context == null || !(context instanceof Activity) || (window = ((Activity) context).getWindow()) == null || window.getDecorView() == null || window.getDecorView().getWindowId() == null) {
            return false;
        }
        return true;
    }

    public static boolean isResumed(Context context) {
        if (context instanceof Activity) {
            try {
                Activity activity = (Activity) context;
                boolean resumed = ((Boolean) Activity.class.getMethod("isResumed", new Class[0]).invoke(activity, new Object[0])).booleanValue();
                Log.d(TAG, "activity=" + activity + ", resumed=" + resumed);
                return resumed;
            } catch (Exception e) {
                Log.d(TAG, "Failed to call isResumed using reflection", e);
            }
        }
        return false;
    }

    public static boolean isReady(Context context) {
        return isResumed(context) && hasValidWindow(context);
    }

    public class CheckForReadyTask extends AsyncTask<Void, Void, Void> {
        Context context;
        long instancePtr = 0;

        CheckForReadyTask(Context context2, long instancePtr2) {
            this.context = context2;
            this.instancePtr = instancePtr2;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... params) {
            long startTime = System.currentTimeMillis();
            while (!isCancelled()) {
                try {
                    if (ActivityLifecycleListener.isReady(this.context)) {
                        Log.d(ActivityLifecycleListener.TAG, "Activity ready after " + (System.currentTimeMillis() - startTime) + "ms");
                        ActivityLifecycleListener.nativeOnActivityReady(this.instancePtr);
                        return null;
                    }
                    Thread.sleep(10);
                } catch (Exception e) {
                    Log.w(ActivityLifecycleListener.TAG, "doInBackground: Failed to check ready state:" + e);
                }
            }
            return null;
        }
    }

    ActivityLifecycleListener(long instancePtr_) {
        instancePtr = instancePtr_;
        readyTask = null;
    }

    public static void registerLifecycleListener(Context context, long instancePtr_) {
        instancePtr = instancePtr_;
        if (activityLifecyleListener == null) {
            activityLifecyleListener = new ActivityLifecycleListener(instancePtr);
        }
        if (context instanceof Activity) {
            Log.d(TAG, "Registering LifecycleListener");
            ((Activity) context).getApplication().registerActivityLifecycleCallbacks(activityLifecyleListener);
        }
    }

    public static void unregisterLifecycleListener(Context context) {
        if (context instanceof Activity) {
            Log.d(TAG, "Unregistering LifecycleListener");
            ((Activity) context).getApplication().unregisterActivityLifecycleCallbacks(activityLifecyleListener);
        }
        CheckForReadyTask checkForReadyTask = readyTask;
        if (checkForReadyTask != null) {
            checkForReadyTask.cancel(true);
        }
    }

    public void onActivityResumed(Activity activity) {
        Log.d(TAG, "onActivityResumed");
        readyTask = new CheckForReadyTask(activity, instancePtr);
        readyTask.execute(new Void[0]);
    }

    public void onActivityPaused(Activity activity) {
        Log.d(TAG, "onActivityPaused");
        CheckForReadyTask checkForReadyTask = readyTask;
        if (checkForReadyTask != null) {
            checkForReadyTask.cancel(true);
        }
        nativeOnActivityPaused(instancePtr);
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    public void onActivityDestroyed(Activity activity) {
    }
}
