package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.util.Objects;

/* renamed from: lU  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3554lU extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C4067oU f10348a;

    public C3554lU(C4067oU oUVar) {
        this.f10348a = oUVar;
    }

    public void onReceive(Context context, Intent intent) {
        Uri data = intent.getData();
        if (data != null && "com.google.android.googlequicksearchbox".equals(data.getEncodedSchemeSpecificPart())) {
            Context applicationContext = context.getApplicationContext();
            C5259vU.b(applicationContext).f(null);
            C4067oU oUVar = this.f10348a;
            oUVar.c = null;
            Objects.requireNonNull(oUVar);
            C5089uU uUVar = new C5089uU(applicationContext, new C3725mU(oUVar));
            oUVar.c = uUVar;
            uUVar.a();
        }
    }
}
