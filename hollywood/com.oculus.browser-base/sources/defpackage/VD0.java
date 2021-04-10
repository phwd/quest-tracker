package defpackage;

import android.graphics.Matrix;
import android.util.Size;
import org.chromium.base.Callback;

/* renamed from: VD0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VD0 {

    /* renamed from: a  reason: collision with root package name */
    public float f9071a = 0.0f;
    public final C1980cE0 b;
    public final Size c;
    public final Matrix d;
    public final TD0 e;
    public final Callback f;
    public boolean g;

    public VD0(Matrix matrix, TD0 td0, Callback callback) {
        SD0 sd0 = (SD0) td0;
        this.b = sd0.i;
        this.c = sd0.b;
        this.d = matrix;
        this.e = td0;
        this.f = callback;
        this.g = true;
    }
}
