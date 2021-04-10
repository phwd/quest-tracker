package defpackage;

import android.app.Activity;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.vr.VrModuleProvider;

/* renamed from: dw1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2267dw1 implements AbstractC1593a20 {

    /* renamed from: a  reason: collision with root package name */
    public final C2609fw1 f9820a;
    public final AtomicBoolean b;

    public C2267dw1(C2609fw1 fw1, AtomicBoolean atomicBoolean) {
        this.f9820a = fw1;
        this.b = atomicBoolean;
    }

    @Override // defpackage.AbstractC1593a20
    public void a(boolean z) {
        C2609fw1 fw1 = this.f9820a;
        AtomicBoolean atomicBoolean = this.b;
        Objects.requireNonNull(fw1);
        boolean z2 = true;
        if (!atomicBoolean.getAndSet(true)) {
            Activity activity = ApplicationStatus.e;
            if (activity instanceof ChromeActivity) {
                if (!z) {
                    fw1.c(activity);
                } else if (C2474f80.f9900a.f()) {
                    if (ApplicationStatus.e(activity) != 3) {
                        z2 = false;
                    }
                    if (z2) {
                        Objects.requireNonNull((C2609fw1) VrModuleProvider.b());
                    }
                }
            }
        }
    }
}
