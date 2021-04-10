package defpackage;

import android.graphics.Canvas;
import android.graphics.Matrix;
import java.util.List;

/* renamed from: nT0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3895nT0 extends AbstractC4918tT0 {
    public final /* synthetic */ List b;
    public final /* synthetic */ Matrix c;

    public C3895nT0(C5088uT0 ut0, List list, Matrix matrix) {
        this.b = list;
        this.c = matrix;
    }

    @Override // defpackage.AbstractC4918tT0
    public void a(Matrix matrix, C3041iT0 it0, int i, Canvas canvas) {
        for (AbstractC4918tT0 tt0 : this.b) {
            tt0.a(this.c, it0, i, canvas);
        }
    }
}
