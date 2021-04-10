package defpackage;

import android.content.Context;
import android.os.Handler;
import java.util.Objects;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;

/* renamed from: yI  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5744yI extends AbstractC4798sm0 {
    @Override // defpackage.AbstractC0865Oe
    public void c(Context context) {
        C5914zI.b().c();
    }

    @Override // defpackage.AbstractC4798sm0
    public int e(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        return 0;
    }

    @Override // defpackage.AbstractC4798sm0
    public void f(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        Objects.requireNonNull(C5914zI.b());
        AbstractC5064uI.f11404a.i();
        new Handler().post(new RunnableC5574xI(ne));
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean g(Context context, C2046cf1 cf1) {
        return true;
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean h(Context context, C2046cf1 cf1) {
        return false;
    }

    @Override // defpackage.AbstractC4798sm0
    public boolean j() {
        return CachedFeatureFlags.isEnabled("ServiceManagerForDownload");
    }
}
