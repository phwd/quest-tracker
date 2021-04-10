package X;

import java.util.Iterator;
import java.util.LinkedList;

/* renamed from: X.Wj  reason: case insensitive filesystem */
public class C0223Wj extends q0 {
    public static final long serialVersionUID = 1;
    public LinkedList<C0434i2> _path;

    public static C0223Wj A00(AbstractC0232Ww ww, String str) {
        pw A0O;
        if (ww == null) {
            A0O = null;
        } else {
            A0O = ww.A0O();
        }
        return new C0223Wj(str, A0O);
    }

    public static C0223Wj A01(Throwable th, Object obj, String str) {
        C0223Wj wj;
        C0434i2 i2Var = new C0434i2(obj, str);
        if (th instanceof C0223Wj) {
            wj = (C0223Wj) th;
        } else {
            String message = th.getMessage();
            if (message == null || message.length() == 0) {
                message = AnonymousClass06.A05("(was ", th.getClass().getName(), ")");
            }
            wj = new C0223Wj(message, null, th);
        }
        LinkedList<C0434i2> linkedList = wj._path;
        if (linkedList == null) {
            linkedList = new LinkedList<>();
            wj._path = linkedList;
        }
        if (linkedList.size() < 1000) {
            linkedList.addFirst(i2Var);
        }
        return wj;
    }

    private final String A02() {
        StringBuilder sb;
        String message = super.getMessage();
        LinkedList<C0434i2> linkedList = this._path;
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
            Iterator<C0434i2> it = linkedList.iterator();
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

    @Override // X.q0
    public final String getMessage() {
        return A02();
    }

    public C0223Wj(String str) {
        super(str);
    }

    public C0223Wj(String str, pw pwVar) {
        super(str, pwVar, null);
    }

    public C0223Wj(String str, pw pwVar, Throwable th) {
        super(str, pwVar, th);
    }

    public C0223Wj(String str, Throwable th) {
        super(str, null, th);
    }
}
