package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;

/* renamed from: xz1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5688xz1 extends ContextWrapper {

    /* renamed from: a  reason: collision with root package name */
    public BroadcastReceiver f11652a;

    public C5688xz1(Context context) {
        super(context);
    }

    public Intent registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (intentFilter.hasAction("com.google.android.gms.auth.api.phone.SMS_CODE_RETRIEVED")) {
            this.f11652a = broadcastReceiver;
        }
        return super.registerReceiver(broadcastReceiver, intentFilter);
    }

    public void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver == this.f11652a) {
            this.f11652a = null;
        }
        super.unregisterReceiver(broadcastReceiver);
    }
}
