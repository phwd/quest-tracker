package defpackage;

import android.view.View;
import java.util.List;

/* renamed from: B80  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class B80 {

    /* renamed from: a  reason: collision with root package name */
    public boolean f7718a = true;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h = 0;
    public int i = 0;
    public int j;
    public List k = null;
    public boolean l;

    public void a(View view) {
        int a2;
        int size = this.k.size();
        View view2 = null;
        int i2 = Integer.MAX_VALUE;
        for (int i3 = 0; i3 < size; i3++) {
            View view3 = ((XK0) this.k.get(i3)).G;
            JK0 jk0 = (JK0) view3.getLayoutParams();
            if (view3 != view && !jk0.c() && (a2 = (jk0.a() - this.d) * this.e) >= 0 && a2 < i2) {
                view2 = view3;
                if (a2 == 0) {
                    break;
                }
                i2 = a2;
            }
        }
        if (view2 == null) {
            this.d = -1;
        } else {
            this.d = ((JK0) view2.getLayoutParams()).a();
        }
    }

    public boolean b(VK0 vk0) {
        int i2 = this.d;
        return i2 >= 0 && i2 < vk0.b();
    }

    public View c(PK0 pk0) {
        List list = this.k;
        if (list != null) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = ((XK0) this.k.get(i2)).G;
                JK0 jk0 = (JK0) view.getLayoutParams();
                if (!jk0.c() && this.d == jk0.a()) {
                    a(view);
                    return view;
                }
            }
            return null;
        }
        View view2 = pk0.j(this.d, false, Long.MAX_VALUE).G;
        this.d += this.e;
        return view2;
    }
}
