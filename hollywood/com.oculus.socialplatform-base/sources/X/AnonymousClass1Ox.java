package X;

import io.reactivex.annotations.NonNull;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/* renamed from: X.1Ox  reason: invalid class name */
public final class AnonymousClass1Ox extends RuntimeException {
    public static final long serialVersionUID = 3026362227162912146L;
    public Throwable cause;
    public final List<Throwable> exceptions;
    public final String message;

    @NonNull
    public final synchronized Throwable getCause() {
        Throwable th;
        th = this.cause;
        if (th == null) {
            th = new AnonymousClass1P0();
            HashSet hashSet = new HashSet();
            Iterator<Throwable> it = this.exceptions.iterator();
            Throwable th2 = th;
            while (it.hasNext()) {
                Throwable next = it.next();
                if (!hashSet.contains(next)) {
                    hashSet.add(next);
                    ArrayList<Throwable> arrayList = new ArrayList();
                    Throwable cause2 = next.getCause();
                    if (cause2 != null) {
                        if (cause2 != next) {
                            while (true) {
                                arrayList.add(cause2);
                                Throwable cause3 = cause2.getCause();
                                if (cause3 == null || cause3 == cause2) {
                                    break;
                                }
                                cause2 = cause3;
                            }
                        }
                    }
                    for (Throwable th3 : arrayList) {
                        if (hashSet.contains(th3)) {
                            next = new RuntimeException("Duplicate found in causal chain so cropping to prevent loop ...");
                        } else {
                            hashSet.add(th3);
                        }
                    }
                    try {
                        th2.initCause(next);
                    } catch (Throwable unused) {
                    }
                    Throwable cause4 = th2.getCause();
                    if (!(cause4 == null || this.cause == cause4)) {
                        while (true) {
                            Throwable cause5 = cause4.getCause();
                            if (cause5 == null || cause5 == cause4) {
                                th2 = cause4;
                            } else {
                                cause4 = cause5;
                            }
                        }
                        th2 = cause4;
                    }
                }
            }
            this.cause = th;
        }
        return th;
    }

    private void A00(AnonymousClass1P1 r8) {
        StringBuilder sb = new StringBuilder(128);
        sb.append(this);
        sb.append('\n');
        StackTraceElement[] stackTrace = getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            sb.append("\tat ");
            sb.append(stackTraceElement);
            sb.append('\n');
        }
        int i = 1;
        for (Throwable th : this.exceptions) {
            sb.append("  ComposedException ");
            sb.append(i);
            sb.append(" :\n");
            A01(sb, th, "\t");
            i++;
        }
        String obj = sb.toString();
        if (!(r8 instanceof AnonymousClass1Oy)) {
            ((AnonymousClass1Oz) r8).A00.println((Object) obj);
        } else {
            ((AnonymousClass1Oy) r8).A00.println((Object) obj);
        }
    }

    @NonNull
    public final String getMessage() {
        return this.message;
    }

    public AnonymousClass1Ox(@NonNull Iterable<? extends Throwable> iterable) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayList arrayList = new ArrayList();
        if (iterable != null) {
            for (Object obj : iterable) {
                if (obj instanceof AnonymousClass1Ox) {
                    linkedHashSet.addAll(((AnonymousClass1Ox) obj).exceptions);
                } else {
                    linkedHashSet.add(obj == null ? new NullPointerException("Throwable was null!") : obj);
                }
            }
        } else {
            linkedHashSet.add(new NullPointerException("errors was null"));
        }
        if (!linkedHashSet.isEmpty()) {
            arrayList.addAll(linkedHashSet);
            List<Throwable> unmodifiableList = Collections.unmodifiableList(arrayList);
            this.exceptions = unmodifiableList;
            StringBuilder sb = new StringBuilder();
            sb.append(unmodifiableList.size());
            sb.append(" exceptions occurred. ");
            this.message = sb.toString();
            return;
        }
        throw new IllegalArgumentException("errors is empty");
    }

    private void A01(StringBuilder sb, Throwable th, String str) {
        sb.append(str);
        sb.append(th);
        sb.append('\n');
        StackTraceElement[] stackTrace = th.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            sb.append("\t\tat ");
            sb.append(stackTraceElement);
            sb.append('\n');
        }
        if (th.getCause() != null) {
            sb.append("\tCaused by: ");
            A01(sb, th.getCause(), "");
        }
    }

    public final void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override // java.lang.Throwable
    public final void printStackTrace(PrintStream printStream) {
        A00(new AnonymousClass1Oz(printStream));
    }

    @Override // java.lang.Throwable
    public final void printStackTrace(PrintWriter printWriter) {
        A00(new AnonymousClass1Oy(printWriter));
    }
}
