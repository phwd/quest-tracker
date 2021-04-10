package defpackage;

import android.graphics.drawable.Animatable;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: Xb  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1405Xb extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final AtomicBoolean f9217a;

    public C1405Xb(AtomicBoolean atomicBoolean) {
        this.f9217a = atomicBoolean;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Animatable animatable = (Animatable) obj;
        this.f9217a.set(true);
    }
}
