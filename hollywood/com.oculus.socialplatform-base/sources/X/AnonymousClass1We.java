package X;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.annotation.Nullable;

/* renamed from: X.1We  reason: invalid class name */
public class AnonymousClass1We implements AbstractC00810Jt {
    public final /* synthetic */ AnonymousClass1Wf A00;
    public final /* synthetic */ AnonymousClass1Wg A01;

    public AnonymousClass1We(AnonymousClass1Wf r1, AnonymousClass1Wg r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    @Override // X.AbstractC00810Jt
    public final boolean A9K() {
        return false;
    }

    @Override // X.AbstractC00810Jt
    public final void A9G(C00860Jy<Object> r6, @Nullable Throwable th) {
        String str;
        String obj;
        Object A012 = r6.A01();
        if (A012 != null) {
            str = A012.getClass().getName();
        } else {
            str = "<value is null>";
        }
        Integer valueOf = Integer.valueOf(System.identityHashCode(this));
        Integer valueOf2 = Integer.valueOf(System.identityHashCode(r6));
        if (th == null) {
            obj = "";
        } else {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            obj = stringWriter.toString();
        }
        AnonymousClass0J5.A05("Fresco", "Finalized without closing: %x %x (type = %s).\nStack:\n%s", valueOf, valueOf2, str, obj);
    }
}
