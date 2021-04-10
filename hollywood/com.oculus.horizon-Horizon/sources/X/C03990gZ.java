package X;

import java.util.Iterator;
import java.util.LinkedList;

/* renamed from: X.0gZ  reason: invalid class name and case insensitive filesystem */
public final class C03990gZ extends AnonymousClass0jg {
    public static final long serialVersionUID = 1;
    public LinkedList<C05210kY> _path;

    public static C03990gZ A00(AbstractC04100gp r1, String str) {
        AnonymousClass0jc A0P;
        if (r1 == null) {
            A0P = null;
        } else {
            A0P = r1.A0P();
        }
        return new C03990gZ(str, A0P);
    }

    public static C03990gZ A01(Throwable th, Object obj, String str) {
        C03990gZ r4;
        C05210kY r3 = new C05210kY(obj, str);
        if (th instanceof C03990gZ) {
            r4 = (C03990gZ) th;
        } else {
            String message = th.getMessage();
            if (message == null || message.length() == 0) {
                message = AnonymousClass006.A07("(was ", th.getClass().getName(), ")");
            }
            r4 = new C03990gZ(message, null, th);
        }
        LinkedList<C05210kY> linkedList = r4._path;
        if (linkedList == null) {
            linkedList = new LinkedList<>();
            r4._path = linkedList;
        }
        if (linkedList.size() < 1000) {
            linkedList.addFirst(r3);
        }
        return r4;
    }

    private final String A02() {
        StringBuilder sb;
        String message = super.getMessage();
        LinkedList<C05210kY> linkedList = this._path;
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
            Iterator<C05210kY> it = linkedList.iterator();
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

    @Override // X.AnonymousClass0jg
    public final String getMessage() {
        return A02();
    }

    public C03990gZ(String str) {
        super(str);
    }

    public C03990gZ(String str, AnonymousClass0jc r3) {
        super(str, r3, null);
    }

    public C03990gZ(String str, AnonymousClass0jc r3, Throwable th) {
        super(str, null, th);
    }

    public C03990gZ(String str, Throwable th) {
        super(str, null, th);
    }
}
