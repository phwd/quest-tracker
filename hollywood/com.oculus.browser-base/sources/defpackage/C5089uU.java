package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Messenger;
import android.util.Log;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.AppHooks;

/* renamed from: uU  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5089uU {

    /* renamed from: a  reason: collision with root package name */
    public final Messenger f11412a;
    public final HandlerC4919tU b;
    public final ServiceConnectionC4749sU c = new ServiceConnectionC4749sU(this, null);
    public final C4409qU d;
    public Context e;
    public Callback f;
    public Messenger g;

    public C5089uU(Context context, Callback callback) {
        this.e = context.getApplicationContext();
        this.f = callback;
        HandlerC4919tU tUVar = new HandlerC4919tU(this, null);
        this.b = tUVar;
        this.f11412a = new Messenger(tUVar);
        Objects.requireNonNull(AppHooks.get());
        this.d = new C4409qU();
    }

    public boolean a() {
        if (this.g != null) {
            Log.e("GSAServiceClient", "Already connected.");
        }
        return this.e.bindService(new Intent("com.google.android.ssb.action.SSB_SERVICE").setPackage("com.google.android.googlequicksearchbox"), this.c, 5);
    }

    public void b() {
        if (this.g != null) {
            this.e.unbindService(this.c);
            this.g = null;
            this.b.removeCallbacksAndMessages(null);
        }
    }
}
