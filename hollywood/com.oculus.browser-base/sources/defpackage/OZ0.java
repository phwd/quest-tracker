package defpackage;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import java.util.Arrays;

/* renamed from: OZ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OZ0 {

    /* renamed from: a  reason: collision with root package name */
    public int f8632a;
    public int b;
    public boolean c;
    public boolean d;
    public boolean e;
    public int[] f;
    public final /* synthetic */ StaggeredGridLayoutManager g;

    public OZ0(StaggeredGridLayoutManager staggeredGridLayoutManager) {
        this.g = staggeredGridLayoutManager;
        b();
    }

    public void a() {
        int i;
        if (this.c) {
            i = this.g.t.g();
        } else {
            i = this.g.t.k();
        }
        this.b = i;
    }

    public void b() {
        this.f8632a = -1;
        this.b = Integer.MIN_VALUE;
        this.c = false;
        this.d = false;
        this.e = false;
        int[] iArr = this.f;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
    }
}
