package X;

import java.util.Iterator;
import java.util.LinkedList;

public final class RW extends AnonymousClass9r {
    public static final long serialVersionUID = 1;
    public LinkedList<AnonymousClass6v> _path;

    public static RW A00(Rn rn, String str) {
        AnonymousClass9u r0;
        if (rn == null) {
            r0 = null;
        } else {
            r0 = ((AnonymousClass6z) rn).A01;
            if (r0 == null) {
                r0 = AnonymousClass9u.A01;
            }
        }
        return new RW(str, r0);
    }

    public static RW A01(Throwable th, Object obj, String str) {
        RW rw;
        AnonymousClass6v r3 = new AnonymousClass6v(obj, str);
        if (th instanceof RW) {
            rw = (RW) th;
        } else {
            String message = th.getMessage();
            if (message == null || message.length() == 0) {
                message = AnonymousClass06.A04("(was ", th.getClass().getName(), ")");
            }
            rw = new RW(message, th);
        }
        LinkedList<AnonymousClass6v> linkedList = rw._path;
        if (linkedList == null) {
            linkedList = new LinkedList<>();
            rw._path = linkedList;
        }
        if (linkedList.size() < 1000) {
            linkedList.addFirst(r3);
        }
        return rw;
    }

    private final String A02() {
        StringBuilder sb;
        String message = super.getMessage();
        LinkedList<AnonymousClass6v> linkedList = this._path;
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
            Iterator<AnonymousClass6v> it = linkedList.iterator();
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

    @Override // X.AnonymousClass9r
    public final String getMessage() {
        return A02();
    }

    public RW(String str, AnonymousClass9u r3) {
        super(str, r3, null);
    }

    public RW(String str, Throwable th) {
        super(str, null, th);
    }
}
