package defpackage;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;

/* renamed from: Si1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1123Si1 extends ContextWrapper {
    public C1123Si1(C1184Ti1 ti1, Context context) {
        super(context);
    }

    public ApplicationInfo getApplicationInfo() {
        ApplicationInfo applicationInfo = new ApplicationInfo(super.getApplicationInfo());
        applicationInfo.targetSdkVersion = 19;
        applicationInfo.flags &= -536870913;
        return applicationInfo;
    }
}
