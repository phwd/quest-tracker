package defpackage;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

/* renamed from: YY0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class YY0 extends IntentService {
    public String F;
    public XY0 G;

    public YY0(String str, String str2) {
        super(str2);
        this.F = str;
    }

    public void attachBaseContext(Context context) {
        Context a2 = AbstractC2369eZ0.a(context);
        XY0 xy0 = (XY0) AbstractC2369eZ0.b(a2, this.F);
        this.G = xy0;
        xy0.f9216a = this;
        xy0.b();
        super.attachBaseContext(a2);
    }

    public void onHandleIntent(Intent intent) {
        this.G.a(intent);
    }
}
