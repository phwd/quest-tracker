package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: qH  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4380qH extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C4721sH f11129a;

    public C4380qH(C4721sH sHVar, AbstractC3867nH nHVar) {
        this.f11129a = sHVar;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.MEDIA_REMOVED") || intent.getAction().equals("android.intent.action.MEDIA_MOUNTED") || intent.getAction().equals("android.intent.action.MEDIA_EJECT")) {
            this.f11129a.c = true;
        }
    }
}
