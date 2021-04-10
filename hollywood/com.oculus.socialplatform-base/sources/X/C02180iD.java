package X;

import java.util.Iterator;
import java.util.LinkedList;

/* renamed from: X.0iD  reason: invalid class name and case insensitive filesystem */
public class C02180iD extends C03620oC {
    public static final long serialVersionUID = 1;
    public LinkedList<C04030p3> _path;

    public static C02180iD A00(AbstractC02280iQ r1, String str) {
        AnonymousClass0o8 A0W;
        if (r1 == null) {
            A0W = null;
        } else {
            A0W = r1.A0W();
        }
        return new C02180iD(str, A0W);
    }

    public static C02180iD A01(Throwable th, C04030p3 r4) {
        C02180iD r3;
        if (th instanceof C02180iD) {
            r3 = (C02180iD) th;
        } else {
            String message = th.getMessage();
            if (message == null || message.length() == 0) {
                message = AnonymousClass006.A09("(was ", th.getClass().getName(), ")");
            }
            r3 = new C02180iD(message, null, th);
        }
        r3.A04(r4);
        return r3;
    }

    public final void A04(C04030p3 r4) {
        LinkedList<C04030p3> linkedList = this._path;
        if (linkedList == null) {
            linkedList = new LinkedList<>();
            this._path = linkedList;
        }
        if (linkedList.size() < 1000) {
            linkedList.addFirst(r4);
        }
    }

    private final String A02() {
        StringBuilder sb;
        String message = super.getMessage();
        LinkedList<C04030p3> linkedList = this._path;
        if (linkedList == null) {
            return message;
        }
        if (message == null) {
            sb = new StringBuilder();
        } else {
            sb = new StringBuilder(message);
        }
        sb.append(" (through reference chain: ");
        if (linkedList != null) {
            Iterator<C04030p3> it = linkedList.iterator();
            while (it.hasNext()) {
                sb.append(it.next().toString());
                if (it.hasNext()) {
                    sb.append("->");
                }
            }
        }
        sb.append(')');
        return sb.toString();
    }

    public final String getLocalizedMessage() {
        return A02();
    }

    @Override // X.C03620oC
    public final String getMessage() {
        return A02();
    }

    public C02180iD(String str) {
        super(str);
    }

    public C02180iD(String str, AnonymousClass0o8 r3) {
        super(str, r3, null);
    }

    public C02180iD(String str, AnonymousClass0o8 r2, Throwable th) {
        super(str, r2, th);
    }

    public C02180iD(String str, Throwable th) {
        super(str, null, th);
    }
}
