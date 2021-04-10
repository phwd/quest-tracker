package X;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

/* renamed from: X.0Cu  reason: invalid class name */
public final class AnonymousClass0Cu extends Exception {
    public static final long serialVersionUID = 1;
    public List<Throwable> innerThrowables;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0Cu(String str, List<? extends Throwable> list) {
        super(str, list.size() > 0 ? (Throwable) list.get(0) : null);
        this.innerThrowables = Collections.unmodifiableList(list);
    }

    @Override // java.lang.Throwable
    public final void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        int i = -1;
        for (Throwable th : this.innerThrowables) {
            printWriter.append("\n");
            printWriter.append("  Inner throwable #");
            i++;
            printWriter.append((CharSequence) Integer.toString(i));
            printWriter.append(": ");
            th.printStackTrace(printWriter);
            printWriter.append("\n");
        }
    }

    @Override // java.lang.Throwable
    public final void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        int i = -1;
        for (Throwable th : this.innerThrowables) {
            printStream.append("\n");
            printStream.append("  Inner throwable #");
            i++;
            printStream.append((CharSequence) Integer.toString(i));
            printStream.append(": ");
            th.printStackTrace(printStream);
            printStream.append("\n");
        }
    }
}
