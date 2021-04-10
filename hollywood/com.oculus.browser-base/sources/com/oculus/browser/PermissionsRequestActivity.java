package com.oculus.browser;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PermissionsRequestActivity extends Activity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String[] stringArrayExtra = getIntent().getStringArrayExtra("request_permission");
        if (stringArrayExtra == null || stringArrayExtra.length == 0) {
            finishAndRemoveTask();
        }
        Object obj = K2.f8337a;
        for (String str : stringArrayExtra) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException(AbstractC2531fV.h(AbstractC2531fV.i("Permission request for permissions "), Arrays.toString(stringArrayExtra), " must not contain null or empty values"));
            }
        }
        if (this instanceof J2) {
            AbstractActivityC3892nS nSVar = (AbstractActivityC3892nS) ((J2) this);
            AbstractActivityC3892nS.X(0);
        }
        requestPermissions(stringArrayExtra, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3, types: [int] */
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        String str;
        String str2;
        ArrayList arrayList;
        int i2;
        boolean z;
        ArrayList arrayList2;
        Intent intent = new Intent();
        intent.setAction("com.oculus.vrshell.panel.vr_permission.RESULT");
        intent.putExtra("response_permission", strArr);
        intent.putExtra("results_permission", iArr);
        U90 a2 = U90.a(this);
        synchronized (a2.d) {
            String action = intent.getAction();
            String resolveTypeIfNeeded = intent.resolveTypeIfNeeded(a2.c.getContentResolver());
            Uri data = intent.getData();
            String scheme = intent.getScheme();
            Set<String> categories = intent.getCategories();
            boolean z2 = false;
            boolean z3 = (intent.getFlags() & 8) != 0;
            if (z3) {
                String str3 = "Resolving type " + resolveTypeIfNeeded + " scheme " + scheme + " of intent " + intent;
            }
            ArrayList arrayList3 = (ArrayList) a2.e.get(intent.getAction());
            if (arrayList3 != null) {
                if (z3) {
                    String str4 = "Action list: " + arrayList3;
                }
                ArrayList arrayList4 = null;
                int i3 = 0;
                while (i3 < arrayList3.size()) {
                    T90 t90 = (T90) arrayList3.get(i3);
                    if (z3) {
                        String str5 = "Matching against filter " + t90.f8942a;
                    }
                    if (t90.c) {
                        i2 = i3;
                        arrayList = arrayList3;
                        str2 = action;
                        str = resolveTypeIfNeeded;
                        arrayList2 = arrayList4;
                        z = z2;
                    } else {
                        i2 = i3;
                        str2 = action;
                        arrayList2 = arrayList4;
                        arrayList = arrayList3;
                        str = resolveTypeIfNeeded;
                        z = z2;
                        int match = t90.f8942a.match(action, resolveTypeIfNeeded, scheme, data, categories, "LocalBroadcastManager");
                        if (match >= 0) {
                            if (z3) {
                                Integer.toHexString(match);
                            }
                            arrayList4 = arrayList2 == null ? new ArrayList() : arrayList2;
                            arrayList4.add(t90);
                            t90.c = true;
                            i3 = i2 + 1;
                            action = str2;
                            z2 = z;
                            arrayList3 = arrayList;
                            resolveTypeIfNeeded = str;
                        }
                    }
                    arrayList4 = arrayList2;
                    i3 = i2 + 1;
                    action = str2;
                    z2 = z;
                    arrayList3 = arrayList;
                    resolveTypeIfNeeded = str;
                }
                if (arrayList4 != null) {
                    int i4 = z2;
                    while (i4 < arrayList4.size()) {
                        ((T90) arrayList4.get(i4 == true ? 1 : 0)).c = z2;
                        i4++;
                    }
                    a2.f.add(new S90(intent, arrayList4));
                    if (!a2.g.hasMessages(1)) {
                        a2.g.sendEmptyMessage(1);
                    }
                }
            }
        }
        finishAndRemoveTask();
    }
}
