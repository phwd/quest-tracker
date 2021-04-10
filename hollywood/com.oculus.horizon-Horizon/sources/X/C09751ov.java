package X;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.annotation.Nullable;

/* renamed from: X.1ov  reason: invalid class name and case insensitive filesystem */
public class C09751ov implements AbstractC10391sj {
    public final /* synthetic */ C09761ow A00;
    public final /* synthetic */ C09781oy A01;

    public C09751ov(C09761ow r1, C09781oy r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    @Override // X.AbstractC10391sj
    public final void A8G(C09961qf<Object> r5, @Nullable Throwable th) {
        String str;
        String obj;
        Object A012 = r5.A01();
        if (A012 != null) {
            str = A012.getClass().getName();
        } else {
            str = "<value is null>";
        }
        Object[] objArr = new Object[4];
        objArr[0] = Integer.valueOf(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(System.identityHashCode(r5));
        objArr[2] = str;
        if (th == null) {
            obj = "";
        } else {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            obj = stringWriter.toString();
        }
        objArr[3] = obj;
        C01080Kb.A05("Fresco", "Finalized without closing: %x %x (type = %s).\nStack:\n%s", objArr);
    }

    @Override // X.AbstractC10391sj
    public final boolean A8M() {
        return false;
    }
}
