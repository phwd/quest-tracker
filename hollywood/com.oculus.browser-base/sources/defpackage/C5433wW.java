package defpackage;

import android.view.View;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: wW  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5433wW extends BW {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BW f11548a;
    public final /* synthetic */ BW b;

    public C5433wW(BW bw, BW bw2) {
        this.f11548a = bw;
        this.b = bw2;
    }

    @Override // defpackage.BW
    public int a(View view, int i, int i2) {
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        boolean z = true;
        if (view.getLayoutDirection() != 1) {
            z = false;
        }
        return (!z ? this.f11548a : this.b).a(view, i, i2);
    }

    @Override // defpackage.BW
    public String c() {
        StringBuilder i = AbstractC2531fV.i("SWITCHING[L:");
        i.append(this.f11548a.c());
        i.append(", R:");
        i.append(this.b.c());
        i.append("]");
        return i.toString();
    }

    @Override // defpackage.BW
    public int d(View view, int i) {
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        boolean z = true;
        if (view.getLayoutDirection() != 1) {
            z = false;
        }
        return (!z ? this.f11548a : this.b).d(view, i);
    }
}
