package defpackage;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Process;
import org.chromium.base.ContextUtils;

/* renamed from: oU  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4067oU {

    /* renamed from: a  reason: collision with root package name */
    public static C4067oU f10552a;
    public int b;
    public C5089uU c;
    public boolean d;

    public C4067oU(Context context) {
        Context applicationContext = context.getApplicationContext();
        applicationContext.registerReceiver(new C3896nU(), new IntentFilter("com.google.android.apps.now.account_update_broadcast"), "com.google.android.apps.now.CURRENT_ACCOUNT_ACCESS", null);
        C5089uU uUVar = new C5089uU(applicationContext, new C3725mU(this));
        this.c = uUVar;
        uUVar.a();
        C3554lU lUVar = new C3554lU(this);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addDataScheme("package");
        context.registerReceiver(lUVar, intentFilter);
    }

    public static boolean a() {
        return AbstractC3153j7.a(ContextUtils.getApplicationContext(), "com.google.android.apps.now.CURRENT_ACCOUNT_ACCESS", Process.myPid(), Process.myUid()) == 0;
    }
}
