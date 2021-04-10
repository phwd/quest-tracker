package X;

import androidx.annotation.Nullable;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.1Or  reason: invalid class name */
public final class AnonymousClass1Or extends Exception {
    public static final StackTraceElement[] A00 = new StackTraceElement[0];
    public static final long serialVersionUID = 1;
    public final List<Throwable> causes;
    public Class<?> dataClass;
    public AnonymousClass1fM dataSource;
    public String detailMessage;
    @Nullable
    public Exception exception;
    public AbstractC06491aL key;

    public final Throwable fillInStackTrace() {
        return this;
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Throwable;Ljava/util/List<Ljava/lang/Throwable;>;)V */
    public static void A00(AnonymousClass1Or r2, Throwable th, List list) {
        if (th instanceof AnonymousClass1Or) {
            for (Throwable th2 : ((AnonymousClass1Or) th).causes) {
                A00(r2, th2, list);
            }
            return;
        }
        list.add(th);
    }

    public final String getMessage() {
        String str;
        String str2;
        String str3;
        StringBuilder sb = new StringBuilder(71);
        sb.append(this.detailMessage);
        Class<?> cls = this.dataClass;
        String str4 = "";
        if (cls != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(", ");
            sb2.append(cls);
            str = sb2.toString();
        } else {
            str = str4;
        }
        sb.append(str);
        AnonymousClass1fM r1 = this.dataSource;
        if (r1 != null) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(", ");
            sb3.append(r1);
            str2 = sb3.toString();
        } else {
            str2 = str4;
        }
        sb.append(str2);
        AbstractC06491aL r12 = this.key;
        if (r12 != null) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(", ");
            sb4.append(r12);
            str4 = sb4.toString();
        }
        sb.append(str4);
        ArrayList<Throwable> arrayList = new ArrayList();
        A00(this, this, arrayList);
        if (!arrayList.isEmpty()) {
            if (arrayList.size() == 1) {
                str3 = "\nThere was 1 cause:";
            } else {
                sb.append("\nThere were ");
                sb.append(arrayList.size());
                str3 = " causes:";
            }
            sb.append(str3);
            for (Throwable th : arrayList) {
                sb.append('\n');
                sb.append(th.getClass().getName());
                sb.append('(');
                sb.append(th.getMessage());
                sb.append(')');
            }
            sb.append("\n call GlideException#logRootCauses(String) for more detail");
        }
        return sb.toString();
    }

    public AnonymousClass1Or(String str, List<Throwable> list) {
        this.detailMessage = str;
        setStackTrace(A00);
        this.causes = list;
    }

    private void A01(Appendable appendable) {
        A02(this, appendable);
        List<Throwable> list = this.causes;
        AnonymousClass1Os r3 = new AnonymousClass1Os(appendable);
        try {
            int size = list.size();
            int i = 0;
            while (i < size) {
                int i2 = i + 1;
                r3.append("Cause (").append(String.valueOf(i2)).append(" of ").append(String.valueOf(size)).append("): ");
                Throwable th = list.get(i);
                if (th instanceof AnonymousClass1Or) {
                    ((AnonymousClass1Or) th).A01(r3);
                } else {
                    A02(th, r3);
                }
                i = i2;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void A02(Throwable th, Appendable appendable) {
        try {
            appendable.append(th.getClass().toString()).append(": ").append(th.getMessage()).append('\n');
        } catch (IOException unused) {
            throw new RuntimeException(th);
        }
    }

    public final void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override // java.lang.Throwable
    public final void printStackTrace(PrintStream printStream) {
        A01(printStream);
    }

    @Override // java.lang.Throwable
    public final void printStackTrace(PrintWriter printWriter) {
        A01(printWriter);
    }
}
