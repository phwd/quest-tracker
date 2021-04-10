package defpackage;

import android.content.Intent;
import org.chromium.base.Callback;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: C1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1 implements Ky1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Callback f7779a;

    public C1(D1 d1, Callback callback) {
        this.f7779a = callback;
    }

    @Override // defpackage.Ky1
    public void a(WindowAndroid windowAndroid, int i, Intent intent) {
        if (i == -1) {
            this.f7779a.onResult(intent.getStringExtra("authAccount"));
        }
    }
}
