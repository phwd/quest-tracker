package defpackage;

import android.content.Context;
import android.os.IBinder;
import java.util.HashMap;
import java.util.concurrent.Callable;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;

/* renamed from: Zw1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Zw1 {

    /* renamed from: a  reason: collision with root package name */
    public String f9386a;
    public String b;
    public C3070if1 c;
    public AbstractC2387ef1 d;
    public int e;
    public HashMap f = new HashMap();

    public Zw1(C3070if1 if1, String str, String str2) {
        this.c = if1;
        this.f9386a = str;
        this.b = str2;
    }

    public void a(Context context, String str, Yw1 yw1) {
        Xw1 xw1 = (Xw1) this.f.get(str);
        if (xw1 != null) {
            IBinder iBinder = xw1.c;
            if (iBinder != null) {
                yw1.a(iBinder);
            } else {
                xw1.b.add(yw1);
            }
        } else {
            Xw1 xw12 = new Xw1(this);
            this.f.put(str, xw12);
            xw12.b.add(yw1);
            c(new Rw1(this, str, context, xw12), new Sw1(xw12));
        }
    }

    public void b(Context context) {
        if (!this.f.isEmpty()) {
            Xw1[] xw1Arr = (Xw1[]) this.f.values().toArray(new Xw1[this.f.size()]);
            this.f.clear();
            for (Xw1 xw1 : xw1Arr) {
                xw1.onServiceConnected(null, null);
            }
            c(new Tw1(xw1Arr, context), new Uw1(this));
        }
    }

    public final void c(Callable callable, Callback callback) {
        this.e++;
        if (this.d == null) {
            this.d = PostTask.a(C3070if1.b);
        }
        this.d.b(new Vw1(this, callable, callback));
    }
}
