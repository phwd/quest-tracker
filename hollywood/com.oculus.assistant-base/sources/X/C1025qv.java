package X;

import java.util.Iterator;
import java.util.LinkedList;

/* renamed from: X.qv  reason: case insensitive filesystem */
public final class C1025qv extends NV {
    public static final long serialVersionUID = 1;
    public LinkedList _path;

    public static C1025qv A00(AbstractC1014qi qiVar, String str) {
        NT A0S;
        if (qiVar == null) {
            A0S = null;
        } else {
            A0S = qiVar.A0S();
        }
        return new C1025qv(str, A0S);
    }

    public static C1025qv A01(Throwable th, O9 o9) {
        C1025qv qvVar;
        if (th instanceof C1025qv) {
            qvVar = (C1025qv) th;
        } else {
            String message = th.getMessage();
            if (message == null || message.length() == 0) {
                message = AnonymousClass08.A05("(was ", th.getClass().getName(), ")");
            }
            qvVar = new C1025qv(message, null, th);
        }
        qvVar.A03(o9);
        return qvVar;
    }

    public final void A03(O9 o9) {
        LinkedList linkedList = this._path;
        if (linkedList == null) {
            linkedList = new LinkedList();
            this._path = linkedList;
        }
        if (linkedList.size() < 1000) {
            linkedList.addFirst(o9);
        }
    }

    private final String A02() {
        StringBuilder sb;
        String message = super.getMessage();
        LinkedList linkedList = this._path;
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
            Iterator it = linkedList.iterator();
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

    @Override // X.NV
    public final String getMessage() {
        return A02();
    }

    public C1025qv(String str) {
        super(str);
    }

    public C1025qv(String str, NT nt) {
        super(str, nt, null);
    }

    public C1025qv(String str, NT nt, Throwable th) {
        super(str, null, th);
    }

    public C1025qv(String str, Throwable th) {
        super(str, null, th);
    }
}
