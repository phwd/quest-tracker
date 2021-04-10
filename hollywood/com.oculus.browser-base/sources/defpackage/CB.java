package defpackage;

import android.graphics.Matrix;
import android.view.View;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.InputMethodManager;
import org.chromium.content.browser.input.ImeAdapterImpl;

/* renamed from: CB  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class CB {

    /* renamed from: a  reason: collision with root package name */
    public boolean f7792a;
    public boolean b;
    public boolean c;
    public float[] d;
    public boolean e;
    public float f;
    public float g;
    public float h;
    public boolean i;
    public boolean j;
    public float k;
    public float l;
    public float m;
    public CursorAnchorInfo n;
    public final Matrix o = new Matrix();
    public final int[] p = new int[2];
    public final CursorAnchorInfo.Builder q = new CursorAnchorInfo.Builder();
    public S10 r;
    public final TZ s;
    public final BB t;

    public CB(S10 s10, TZ tz, BB bb) {
        this.r = s10;
        this.s = tz;
        this.t = bb;
    }

    public boolean a(boolean z, boolean z2, View view) {
        boolean z3 = this.f7792a;
        if (!z3) {
            return false;
        }
        if (this.c && !z2 && z3) {
            this.n = null;
        }
        this.c = z2;
        if (z) {
            this.b = true;
            b(view);
        }
        return true;
    }

    public final void b(View view) {
        if (this.e) {
            if (this.n == null) {
                this.q.reset();
                ImeAdapterImpl imeAdapterImpl = this.s.f8964a;
                String str = imeAdapterImpl.Y;
                int i2 = imeAdapterImpl.W;
                int i3 = imeAdapterImpl.X;
                int i4 = imeAdapterImpl.Z;
                int i5 = imeAdapterImpl.a0;
                if (str != null && i4 >= 0 && i5 <= str.length()) {
                    this.q.setComposingText(i4, str.subSequence(i4, i5));
                    float[] fArr = this.d;
                    if (fArr != null) {
                        int length = fArr.length / 4;
                        for (int i6 = 0; i6 < length; i6++) {
                            int i7 = i6 * 4;
                            this.q.addCharacterBounds(i4 + i6, fArr[i7], fArr[i7 + 1], fArr[i7 + 2], fArr[i7 + 3], 1);
                        }
                    }
                }
                this.q.setSelectionRange(i2, i3);
                Matrix matrix = this.o;
                float f2 = this.f;
                matrix.setScale(f2, f2);
                this.o.postTranslate(this.g, this.h);
                this.q.setMatrix(this.o);
                if (this.i) {
                    CursorAnchorInfo.Builder builder = this.q;
                    float f3 = this.k;
                    float f4 = this.l;
                    float f5 = this.m;
                    builder.setInsertionMarkerLocation(f3, f4, f5, f5, this.j ? 1 : 2);
                }
                this.n = this.q.build();
            }
            S10 s10 = this.r;
            if (s10 != null) {
                CursorAnchorInfo cursorAnchorInfo = this.n;
                InputMethodManager b2 = s10.b();
                if (b2 != null) {
                    b2.updateCursorAnchorInfo(view, cursorAnchorInfo);
                }
            }
            this.b = false;
        }
    }
}
