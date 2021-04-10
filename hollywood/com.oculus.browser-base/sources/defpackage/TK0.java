package defpackage;

import android.util.Log;
import android.view.animation.Interpolator;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: TK0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TK0 {

    /* renamed from: a  reason: collision with root package name */
    public int f8950a;
    public int b;
    public int c;
    public int d = -1;
    public Interpolator e;
    public boolean f = false;
    public int g = 0;

    public TK0(int i, int i2) {
        this.f8950a = i;
        this.b = i2;
        this.c = Integer.MIN_VALUE;
        this.e = null;
    }

    public void a(RecyclerView recyclerView) {
        int i = this.d;
        if (i >= 0) {
            this.d = -1;
            recyclerView.U(i);
            this.f = false;
        } else if (this.f) {
            Interpolator interpolator = this.e;
            if (interpolator == null || this.c >= 1) {
                int i2 = this.c;
                if (i2 >= 1) {
                    recyclerView.N0.b(this.f8950a, this.b, i2, interpolator);
                    int i3 = this.g + 1;
                    this.g = i3;
                    if (i3 > 10) {
                        Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                    }
                    this.f = false;
                    return;
                }
                throw new IllegalStateException("Scroll duration must be a positive number");
            }
            throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
        } else {
            this.g = 0;
        }
    }

    public void b(int i, int i2, int i3, Interpolator interpolator) {
        this.f8950a = i;
        this.b = i2;
        this.c = i3;
        this.e = interpolator;
        this.f = true;
    }
}
