package defpackage;

import android.os.Handler;
import android.util.Size;
import android.widget.OverScroller;

/* renamed from: YD0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class YD0 {

    /* renamed from: a  reason: collision with root package name */
    public C4372qE0 f9263a;
    public boolean b;
    public float c;
    public final OverScroller d;
    public final Handler e = new Handler();
    public final C1980cE0 f;
    public final Size g;
    public final TD0 h;
    public final Runnable i;
    public final Runnable j;
    public boolean k;

    public YD0(OverScroller overScroller, TD0 td0, Runnable runnable, Runnable runnable2) {
        this.d = overScroller;
        SD0 sd0 = (SD0) td0;
        this.f = sd0.i;
        this.g = sd0.b;
        this.h = td0;
        this.i = runnable;
        this.j = runnable2;
        this.k = true;
    }

    public final void a() {
        if (!this.d.isFinished()) {
            boolean computeScrollOffset = this.d.computeScrollOffset();
            b((float) (this.d.getCurrX() - Math.round(this.f.d())), (float) (this.d.getCurrY() - Math.round(this.f.e())));
            if (computeScrollOffset) {
                this.e.post(new XD0(this));
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0053 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00b8 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00c9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean b(float r10, float r11) {
        /*
        // Method dump skipped, instructions count: 253
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.YD0.b(float, float):boolean");
    }
}
