package X;

import java.util.Iterator;
import java.util.LinkedList;

/* renamed from: X.0aG  reason: invalid class name */
public class AnonymousClass0aG extends C05910ld {
    public static final long serialVersionUID = 1;
    public LinkedList<C06290mV> _path;

    public static AnonymousClass0aG A00(AnonymousClass0aT r1, String str) {
        C05880lZ A0E;
        if (r1 == null) {
            A0E = null;
        } else {
            A0E = r1.A0E();
        }
        return new AnonymousClass0aG(str, A0E);
    }

    public static AnonymousClass0aG A01(Throwable th, C06290mV r4) {
        AnonymousClass0aG r3;
        if (th instanceof AnonymousClass0aG) {
            r3 = (AnonymousClass0aG) th;
        } else {
            String message = th.getMessage();
            if (message == null || message.length() == 0) {
                message = AnonymousClass006.A07("(was ", th.getClass().getName(), ")");
            }
            r3 = new AnonymousClass0aG(message, null, th);
        }
        r3.A04(r4);
        return r3;
    }

    public final void A04(C06290mV r4) {
        LinkedList<C06290mV> linkedList = this._path;
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
        LinkedList<C06290mV> linkedList = this._path;
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
            Iterator<C06290mV> it = linkedList.iterator();
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

    @Override // X.C05910ld
    public final String getMessage() {
        return A02();
    }

    @Override // X.C05910ld
    public final String toString() {
        return AnonymousClass006.A07(getClass().getName(), ": ", getMessage());
    }

    public AnonymousClass0aG(String str) {
        super(str);
    }

    public AnonymousClass0aG(String str, C05880lZ r3) {
        super(str, r3, null);
    }

    public AnonymousClass0aG(String str, C05880lZ r2, Throwable th) {
        super(str, r2, th);
    }

    public AnonymousClass0aG(String str, Throwable th) {
        super(str, null, th);
    }
}
