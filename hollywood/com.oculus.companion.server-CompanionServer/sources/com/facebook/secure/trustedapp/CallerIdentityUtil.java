package com.facebook.secure.trustedapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Process;
import com.facebook.secure.logger.Reporter;
import java.util.Locale;

public class CallerIdentityUtil {
    public static AppIdentity getCallerAppIdentity(Context context, Intent intent, Reporter reporter) {
        AppIdentity callerFromIntent = intent != null ? getCallerFromIntent(context, intent, reporter) : null;
        if (callerFromIntent == null && (callerFromIntent = getCallerFromActivity(context)) == null) {
            callerFromIntent = getCallerFromBinder(context, reporter);
        }
        if (callerFromIntent == null) {
            tryToReport(reporter, "AppIdentity not found for caller");
        }
        return callerFromIntent;
    }

    protected static AppIdentity getCallerFromBinder(Context context, Reporter reporter) {
        if (Binder.getCallingPid() != Process.myPid()) {
            return AppIdentityUtil.getAppIdentityFromUid(context, Binder.getCallingUid());
        }
        tryToReport(reporter, "This method must be called on behalf of an IPC transaction from binder thread.");
        return null;
    }

    protected static AppIdentity getCallerFromActivity(Context context) {
        ComponentName callingActivity;
        if ((context instanceof Activity) && (callingActivity = ((Activity) context).getCallingActivity()) != null) {
            return AppIdentityUtil.getAppIdentityFromPackageName(context, callingActivity.getPackageName());
        }
        return null;
    }

    protected static AppIdentity getCallerFromIntent(Context context, Intent intent, Reporter reporter) {
        AppIdentity callerInfo = CallerInfoHelper.getCallerInfo(context, intent, reporter);
        if (callerInfo == null) {
            return null;
        }
        if (Binder.getCallingPid() == Process.myPid() || Binder.getCallingUid() == callerInfo.getUid()) {
            return callerInfo;
        }
        tryToReport(reporter, String.format(Locale.US, "Uid %d from PI not equal to uid %d from binder data", Integer.valueOf(callerInfo.getUid()), Integer.valueOf(Binder.getCallingUid())));
        return null;
    }

    private static void tryToReport(Reporter reporter, String str) {
        if (reporter != null && !str.isEmpty()) {
            reporter.report(str);
        }
    }
}
