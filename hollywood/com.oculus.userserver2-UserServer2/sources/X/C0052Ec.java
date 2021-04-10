package X;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Deque;
import java.util.logging.Level;

/* renamed from: X.Ec  reason: case insensitive filesystem */
public final class C0052Ec implements Cloneable {
    public boolean A00;
    public final AbstractC0054Ej A01;
    public final XN A02;
    public final ES A03;
    public final boolean A04;

    public final XK A00() throws IOException {
        Object obj;
        Deque<C0052Ec> deque;
        synchronized (this) {
            if (!this.A00) {
                this.A00 = true;
            } else {
                throw new IllegalStateException("Already Executed");
            }
        }
        WV wv = WV.A01;
        if (wv instanceof E3) {
            WY wy = ((E3) wv).A00;
            Method method = wy.A00;
            obj = null;
            if (method != null) {
                try {
                    Object invoke = method.invoke(null, new Object[0]);
                    wy.A01.invoke(invoke, "response.body().close()");
                    obj = invoke;
                } catch (Exception unused) {
                }
            }
        } else if (WV.A00.isLoggable(Level.FINE)) {
            obj = new Throwable("response.body().close()");
        } else {
            obj = null;
        }
        ES es = this.A03;
        es.A00 = obj;
        try {
            AbstractC0054Ej ej = this.A01;
            C0175Xa xa = ej.A0J;
            synchronized (xa) {
                deque = xa.A02;
                deque.add(this);
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(ej.A08);
            arrayList.add(es);
            arrayList.add(new EW(ej.A0I));
            arrayList.add(new EZ());
            arrayList.add(new EY(ej));
            boolean z = this.A04;
            if (!z) {
                arrayList.addAll(ej.A09);
            }
            arrayList.add(new EV(z));
            XN xn = this.A02;
            XK A002 = new EU(arrayList, null, null, null, 0, xn).A00(xn);
            C0175Xa.A00(xa, deque, this);
            return A002;
        } catch (Throwable th) {
            C0175Xa xa2 = this.A01.A0J;
            C0175Xa.A00(xa2, xa2.A02, this);
            throw th;
        }
    }

    @Override // java.lang.Object
    public final Object clone() throws CloneNotSupportedException {
        return new C0052Ec(this.A01, this.A02, this.A04);
    }

    public C0052Ec(AbstractC0054Ej ej, XN xn, boolean z) {
        this.A01 = ej;
        this.A02 = xn;
        this.A04 = z;
        this.A03 = new ES(ej, z);
    }
}
