package com.google.android.gms.gcm;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.util.Base64;
import android.util.Log;

@Deprecated
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GcmReceiver extends AbstractC5849yw1 {
    public static DH1 c;
    public static DH1 d;

    public static int c(Context context, Intent intent) {
        ComponentName componentName;
        ServiceInfo serviceInfo;
        String str;
        ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
        if (resolveService == null || (serviceInfo = resolveService.serviceInfo) == null) {
            Log.e("GcmReceiver", "Failed to resolve target intent service, skipping classname enforcement");
        } else if (!context.getPackageName().equals(serviceInfo.packageName) || (str = serviceInfo.name) == null) {
            String str2 = serviceInfo.packageName;
            String str3 = serviceInfo.name;
            StringBuilder sb = new StringBuilder(String.valueOf(str3).length() + String.valueOf(str2).length() + 94);
            sb.append("Error resolving target intent service, skipping classname enforcement. Resolved service was: ");
            sb.append(str2);
            sb.append("/");
            sb.append(str3);
            Log.e("GcmReceiver", sb.toString());
        } else {
            if (str.startsWith(".")) {
                String valueOf = String.valueOf(context.getPackageName());
                str = str.length() != 0 ? valueOf.concat(str) : new String(valueOf);
            }
            if (Log.isLoggable("GcmReceiver", 3)) {
                String valueOf2 = String.valueOf(str);
                if (valueOf2.length() != 0) {
                    "Restricting intent to a specific service: ".concat(valueOf2);
                } else {
                    new String("Restricting intent to a specific service: ");
                }
            }
            intent.setClassName(context.getPackageName(), str);
        }
        try {
            if (context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0) {
                componentName = AbstractC5849yw1.a(context, intent);
            } else {
                componentName = context.startService(intent);
            }
            if (componentName != null) {
                return -1;
            }
            Log.e("GcmReceiver", "Error while delivering the message: ServiceIntent not found.");
            return 404;
        } catch (SecurityException e) {
            Log.e("GcmReceiver", "Error while delivering the message to the serviceIntent", e);
            return 401;
        } catch (IllegalStateException e2) {
            String valueOf3 = String.valueOf(e2);
            StringBuilder sb2 = new StringBuilder(valueOf3.length() + 45);
            sb2.append("Failed to start service while in background: ");
            sb2.append(valueOf3);
            Log.e("GcmReceiver", sb2.toString());
            return 402;
        }
    }

    public final int b(Context context, Intent intent) {
        DH1 dh1;
        if (isOrderedBroadcast()) {
            setResultCode(-1);
        }
        String action = intent.getAction();
        synchronized (this) {
            if ("com.google.android.c2dm.intent.RECEIVE".equals(action)) {
                if (d == null) {
                    d = new DH1(context, action);
                }
                dh1 = d;
            } else {
                if (c == null) {
                    c = new DH1(context, action);
                }
                dh1 = c;
            }
        }
        BroadcastReceiver.PendingResult goAsync = goAsync();
        synchronized (dh1) {
            dh1.d.add(new EG1(intent, goAsync, dh1.c));
            dh1.a();
        }
        return -1;
    }

    public void onReceive(Context context, Intent intent) {
        int i;
        int i2;
        Log.isLoggable("GcmReceiver", 3);
        intent.setComponent(null);
        intent.setPackage(context.getPackageName());
        if ("google.com/iid".equals(intent.getStringExtra("from"))) {
            intent.setAction("com.google.android.gms.iid.InstanceID");
        }
        String stringExtra = intent.getStringExtra("gcm.rawData64");
        boolean z = false;
        if (stringExtra != null) {
            intent.putExtra("rawData", Base64.decode(stringExtra, 0));
            intent.removeExtra("gcm.rawData64");
        }
        if (isOrderedBroadcast()) {
            setResultCode(500);
        }
        boolean z2 = AbstractC4539rD0.a() && context.getApplicationInfo().targetSdkVersion >= 26;
        if ((intent.getFlags() & 268435456) != 0) {
            z = true;
        }
        if (!z2 || z) {
            if ("com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
                i2 = c(context, intent);
            } else {
                i2 = c(context, intent);
            }
            if (!AbstractC4539rD0.a() || i2 != 402) {
                i = i2;
            } else {
                b(context, intent);
                i = 403;
            }
        } else {
            b(context, intent);
            i = -1;
        }
        if (isOrderedBroadcast()) {
            setResultCode(i);
        }
    }
}
