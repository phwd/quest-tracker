package defpackage;

import android.content.Intent;
import org.chromium.base.Callback;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Y5  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Y5 extends U5 implements Ky1 {

    /* renamed from: a  reason: collision with root package name */
    public final WebContents f9250a;
    public Callback b;

    public Y5(WebContents webContents) {
        this.f9250a = webContents;
    }

    @Override // defpackage.Ky1
    public void a(WindowAndroid windowAndroid, int i, Intent intent) {
        windowAndroid.B0(this);
        T5 t5 = new T5();
        t5.f8937a = i;
        t5.b = intent;
        this.b.onResult(t5);
        this.b = null;
    }
}
