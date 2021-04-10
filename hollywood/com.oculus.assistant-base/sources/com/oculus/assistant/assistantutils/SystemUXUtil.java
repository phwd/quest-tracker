package com.oculus.assistant.assistantutils;

import X.BX;
import X.C0209Jx;
import X.ZG;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.debug.tracer.Tracer;
import com.facebook.proxygen.HTTPTransportCallback;
import com.facebook.proxygen.TraceFieldType;

public final class SystemUXUtil {
    public static final Uri A00 = C0209Jx.A00("apk://com.oculus.vrshell.desktop");
    public static final Uri A01 = C0209Jx.A00(ZG.ASSISTANT.path());

    public static int A00() {
        return A01("com.oculus.assistant.panel.version", "com.oculus.systemux");
    }

    public static int A01(String str, String str2) {
        int i;
        Tracer.A01("getMetadataVersion");
        try {
            try {
                i = BX.A00().getPackageManager().getApplicationInfo(str2, HTTPTransportCallback.BODY_BYTES_RECEIVED).metaData.getInt(str, 0);
            } catch (PackageManager.NameNotFoundException e) {
                Log.e("com.oculus.assistant.assistantutils.SystemUXUtil", e.getMessage(), e);
                i = -1;
            }
            return i;
        } finally {
            Tracer.A00();
        }
    }

    public static Intent A02(ZG zg, String str) {
        Intent intent = new Intent();
        Uri build = A01.buildUpon().appendPath("launch").appendQueryParameter("component", zg.path()).appendQueryParameter(TraceFieldType.Uri, str).build();
        intent.setAction("com.oculus.assistant.LAUNCH");
        intent.putExtra("intent_data", build.toString());
        intent.setComponent(new ComponentName("com.oculus.vrshell", "com.oculus.vrshell.AssistantBroadcastReceiver"));
        return intent;
    }

    public static Intent A03(ZG zg, boolean z, String... strArr) {
        int A012 = A01("com.oculus.vrshell.assistant_shell_version", "com.oculus.vrshell");
        Intent intent = new Intent();
        StringBuilder sb = new StringBuilder();
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String str = strArr[i];
            if (str == null) {
                sb = new StringBuilder();
                break;
            }
            if (!z || sb.length() > 0) {
                sb.append("/");
            }
            if (str.startsWith("/")) {
                str = str.substring(1);
            }
            sb.append(Uri.encode(str));
            i++;
        }
        if (A012 >= 1) {
            Uri build = A01.buildUpon().appendPath("launch").appendQueryParameter("component", zg.path()).appendQueryParameter(TraceFieldType.Uri, sb.toString()).build();
            intent.setAction("com.oculus.assistant.LAUNCH");
            intent.putExtra("intent_data", build.toString());
            intent.setComponent(new ComponentName("com.oculus.vrshell", "com.oculus.vrshell.AssistantBroadcastReceiver"));
            return intent;
        }
        String path = zg.path();
        String obj = sb.toString();
        Intent intent2 = new Intent();
        intent2.setAction("com.oculus.vrshell.intent.action.LAUNCH");
        intent2.putExtra("intent_data", path);
        if (!TextUtils.isEmpty(obj)) {
            intent2.putExtra(TraceFieldType.Uri, obj);
        }
        return intent2;
    }

    public static Intent A04(String str) {
        Intent intent = new Intent();
        intent.setAction("com.oculus.assistant.COMMAND");
        intent.setClassName("com.oculus.vrshell", "com.oculus.vrshell.AssistantBroadcastReceiver");
        intent.putExtra("command", str);
        intent.setComponent(new ComponentName("com.oculus.vrshell", "com.oculus.vrshell.AssistantBroadcastReceiver"));
        return intent;
    }

    public static Intent A05(String str) {
        StringBuilder sb = new StringBuilder(String.format("vrdesktop://%s/%s", "com.oculus.assistant", str));
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setComponent(new ComponentName("com.oculus.vrshell", "com.oculus.vrshell.MainActivity"));
        intent.putExtra(TraceFieldType.Uri, sb.toString());
        intent.setData(A00);
        return intent;
    }

    public static Intent A06(String str, String str2) {
        Intent intent = new Intent();
        Uri build = A01.buildUpon().appendPath("launch").appendQueryParameter("component", str).appendQueryParameter(TraceFieldType.Uri, str2).build();
        intent.setAction("com.oculus.assistant.LAUNCH");
        intent.putExtra("intent_data", build.toString());
        intent.setComponent(new ComponentName("com.oculus.vrshell", "com.oculus.vrshell.AssistantBroadcastReceiver"));
        return intent;
    }
}
