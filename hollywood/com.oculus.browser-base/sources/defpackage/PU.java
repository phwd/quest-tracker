package defpackage;

import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;

/* renamed from: PU  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PU {

    /* renamed from: a  reason: collision with root package name */
    public int f8693a;
    public int b;
    public int[] c;
    public int d;

    public void a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("Layout positions must be non-negative");
        } else if (i2 >= 0) {
            int i3 = this.d * 2;
            int[] iArr = this.c;
            if (iArr == null) {
                int[] iArr2 = new int[4];
                this.c = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i3 >= iArr.length) {
                int[] iArr3 = new int[(i3 * 2)];
                this.c = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            }
            int[] iArr4 = this.c;
            iArr4[i3] = i;
            iArr4[i3 + 1] = i2;
            this.d++;
        } else {
            throw new IllegalArgumentException("Pixel distance must be non-negative");
        }
    }

    public void b(RecyclerView recyclerView, boolean z) {
        this.d = 0;
        int[] iArr = this.c;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
        IK0 ik0 = recyclerView.U;
        if (recyclerView.T != null && ik0 != null && ik0.k) {
            if (z) {
                if (!recyclerView.L.g()) {
                    ik0.k(recyclerView.T.b(), this);
                }
            } else if (!recyclerView.R()) {
                ik0.j(this.f8693a, this.b, recyclerView.Q0, this);
            }
            int i = this.d;
            if (i > ik0.l) {
                ik0.l = i;
                ik0.m = z;
                recyclerView.f9482J.l();
            }
        }
    }

    public boolean c(int i) {
        if (this.c != null) {
            int i2 = this.d * 2;
            for (int i3 = 0; i3 < i2; i3 += 2) {
                if (this.c[i3] == i) {
                    return true;
                }
            }
        }
        return false;
    }
}
