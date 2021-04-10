package defpackage;

import java.util.NoSuchElementException;

/* renamed from: Pk  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0943Pk extends AbstractC1004Qk {
    public int F = 0;
    public final int G;
    public final /* synthetic */ AbstractC1248Uk H;

    public C0943Pk(AbstractC1248Uk uk) {
        this.H = uk;
        this.G = uk.size();
    }

    @Override // defpackage.AbstractC1004Qk
    public byte a() {
        int i = this.F;
        if (i < this.G) {
            this.F = i + 1;
            return this.H.f(i);
        }
        throw new NoSuchElementException();
    }

    public boolean hasNext() {
        return this.F < this.G;
    }
}
