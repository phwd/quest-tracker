package defpackage;

import android.media.MediaCodecList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: Ad0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0010Ad0 implements Iterator {
    public int F;
    public final /* synthetic */ C0071Bd0 G;

    public C0010Ad0(C0071Bd0 bd0, AbstractC5791yd0 yd0) {
        this.G = bd0;
    }

    public boolean hasNext() {
        return this.F < C0071Bd0.a(this.G);
    }

    @Override // java.util.Iterator
    public Object next() {
        if (this.F != C0071Bd0.a(this.G)) {
            C0071Bd0 bd0 = this.G;
            int i = this.F;
            this.F = i + 1;
            if (bd0.b()) {
                return bd0.F[i];
            }
            return MediaCodecList.getCodecInfoAt(i);
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
