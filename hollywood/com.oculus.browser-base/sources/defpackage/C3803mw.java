package defpackage;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

/* renamed from: mw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3803mw extends Ou1 {

    /* renamed from: a  reason: collision with root package name */
    public final List f10461a;

    public C3803mw(int i) {
        this.f10461a = new ArrayList(i);
    }

    @Override // defpackage.Ou1
    public void a(int i) {
        try {
            for (Ou1 ou1 : this.f10461a) {
                ou1.a(i);
            }
        } catch (ConcurrentModificationException e) {
            d(e);
            throw null;
        }
    }

    @Override // defpackage.Ou1
    public void b(int i, float f, int i2) {
        try {
            for (Ou1 ou1 : this.f10461a) {
                ou1.b(i, f, i2);
            }
        } catch (ConcurrentModificationException e) {
            d(e);
            throw null;
        }
    }

    @Override // defpackage.Ou1
    public void c(int i) {
        try {
            for (Ou1 ou1 : this.f10461a) {
                ou1.c(i);
            }
        } catch (ConcurrentModificationException e) {
            d(e);
            throw null;
        }
    }

    public final void d(ConcurrentModificationException concurrentModificationException) {
        throw new IllegalStateException("Adding and removing callbacks during dispatch to callbacks is not supported", concurrentModificationException);
    }
}
