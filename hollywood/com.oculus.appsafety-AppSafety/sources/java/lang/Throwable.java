package java.lang;

import android.icu.text.PluralRules;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import libcore.util.EmptyArray;

public class Throwable implements Serializable {
    private static final String CAUSE_CAPTION = "Caused by: ";
    private static Throwable[] EMPTY_THROWABLE_ARRAY = null;
    private static final String NULL_CAUSE_MESSAGE = "Cannot suppress a null exception.";
    private static final String SELF_SUPPRESSION_MESSAGE = "Self-suppression not permitted";
    private static final String SUPPRESSED_CAPTION = "Suppressed: ";
    private static final long serialVersionUID = -3042686055658047285L;
    private transient Object backtrace;
    private Throwable cause = this;
    private String detailMessage;
    private StackTraceElement[] stackTrace = EmptyArray.STACK_TRACE_ELEMENT;
    private List<Throwable> suppressedExceptions = Collections.emptyList();

    private static native Object nativeFillInStackTrace();

    private static native StackTraceElement[] nativeGetStackTrace(Object obj);

    private static class SentinelHolder {
        public static final StackTraceElement STACK_TRACE_ELEMENT_SENTINEL = new StackTraceElement("", "", null, Integer.MIN_VALUE);
        public static final StackTraceElement[] STACK_TRACE_SENTINEL = {STACK_TRACE_ELEMENT_SENTINEL};

        private SentinelHolder() {
        }
    }

    public Throwable() {
        fillInStackTrace();
    }

    public Throwable(String message) {
        fillInStackTrace();
        this.detailMessage = message;
    }

    public Throwable(String message, Throwable cause2) {
        fillInStackTrace();
        this.detailMessage = message;
        this.cause = cause2;
    }

    public Throwable(Throwable cause2) {
        fillInStackTrace();
        this.detailMessage = cause2 == null ? null : cause2.toString();
        this.cause = cause2;
    }

    protected Throwable(String message, Throwable cause2, boolean enableSuppression, boolean writableStackTrace) {
        if (writableStackTrace) {
            fillInStackTrace();
        } else {
            this.stackTrace = null;
        }
        this.detailMessage = message;
        this.cause = cause2;
        if (!enableSuppression) {
            this.suppressedExceptions = null;
        }
    }

    public String getMessage() {
        return this.detailMessage;
    }

    public String getLocalizedMessage() {
        return getMessage();
    }

    public synchronized Throwable getCause() {
        return this.cause == this ? null : this.cause;
    }

    public synchronized Throwable initCause(Throwable cause2) {
        if (this.cause != this) {
            throw new IllegalStateException("Can't overwrite cause with " + Objects.toString(cause2, "a null"), this);
        } else if (cause2 != this) {
            this.cause = cause2;
        } else {
            throw new IllegalArgumentException("Self-causation not permitted", this);
        }
        return this;
    }

    public String toString() {
        String s = getClass().getName();
        String message = getLocalizedMessage();
        if (message == null) {
            return s;
        }
        return s + PluralRules.KEYWORD_RULE_SEPARATOR + message;
    }

    public void printStackTrace() {
        printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream s) {
        printStackTrace(new WrappedPrintStream(s));
    }

    private void printStackTrace(PrintStreamOrWriter s) {
        Set<Throwable> dejaVu = Collections.newSetFromMap(new IdentityHashMap());
        dejaVu.add(this);
        synchronized (s.lock()) {
            s.println(this);
            StackTraceElement[] trace = getOurStackTrace();
            for (StackTraceElement traceElement : trace) {
                s.println("\tat " + ((Object) traceElement));
            }
            for (Throwable se : getSuppressed()) {
                se.printEnclosedStackTrace(s, trace, SUPPRESSED_CAPTION, "\t", dejaVu);
            }
            Throwable ourCause = getCause();
            if (ourCause != null) {
                ourCause.printEnclosedStackTrace(s, trace, CAUSE_CAPTION, "", dejaVu);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v3, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    private void printEnclosedStackTrace(PrintStreamOrWriter s, StackTraceElement[] enclosingTrace, String caption, String prefix, Set<Throwable> dejaVu) {
        if (dejaVu.contains(this)) {
            s.println("\t[CIRCULAR REFERENCE:" + ((Object) this) + "]");
            return;
        }
        dejaVu.add(this);
        StackTraceElement[] trace = getOurStackTrace();
        int m = trace.length - 1;
        int n = enclosingTrace.length - 1;
        while (m >= 0 && n >= 0 && trace[m].equals(enclosingTrace[n])) {
            m--;
            n--;
        }
        int framesInCommon = (trace.length - 1) - m;
        s.println(prefix + caption + ((Object) this));
        for (int i = 0; i <= m; i++) {
            s.println(prefix + "\tat " + ((Object) trace[i]));
        }
        if (framesInCommon != 0) {
            s.println(prefix + "\t... " + framesInCommon + " more");
        }
        Throwable[] suppressed = getSuppressed();
        int i2 = 0;
        for (int length = suppressed.length; i2 < length; length = length) {
            suppressed[i2].printEnclosedStackTrace(s, trace, SUPPRESSED_CAPTION, prefix + "\t", dejaVu);
            i2++;
            suppressed = suppressed;
        }
        Throwable ourCause = getCause();
        if (ourCause != null) {
            ourCause.printEnclosedStackTrace(s, trace, CAUSE_CAPTION, prefix, dejaVu);
        }
    }

    public void printStackTrace(PrintWriter s) {
        printStackTrace(new WrappedPrintWriter(s));
    }

    /* access modifiers changed from: private */
    public static abstract class PrintStreamOrWriter {
        /* access modifiers changed from: package-private */
        public abstract Object lock();

        /* access modifiers changed from: package-private */
        public abstract void println(Object obj);

        private PrintStreamOrWriter() {
        }
    }

    /* access modifiers changed from: private */
    public static class WrappedPrintStream extends PrintStreamOrWriter {
        private final PrintStream printStream;

        WrappedPrintStream(PrintStream printStream2) {
            super();
            this.printStream = printStream2;
        }

        /* access modifiers changed from: package-private */
        @Override // java.lang.Throwable.PrintStreamOrWriter
        public Object lock() {
            return this.printStream;
        }

        /* access modifiers changed from: package-private */
        @Override // java.lang.Throwable.PrintStreamOrWriter
        public void println(Object o) {
            this.printStream.println(o);
        }
    }

    private static class WrappedPrintWriter extends PrintStreamOrWriter {
        private final PrintWriter printWriter;

        WrappedPrintWriter(PrintWriter printWriter2) {
            super();
            this.printWriter = printWriter2;
        }

        /* access modifiers changed from: package-private */
        @Override // java.lang.Throwable.PrintStreamOrWriter
        public Object lock() {
            return this.printWriter;
        }

        /* access modifiers changed from: package-private */
        @Override // java.lang.Throwable.PrintStreamOrWriter
        public void println(Object o) {
            this.printWriter.println(o);
        }
    }

    public synchronized Throwable fillInStackTrace() {
        if (!(this.stackTrace == null && this.backtrace == null)) {
            this.backtrace = nativeFillInStackTrace();
            this.stackTrace = EmptyArray.STACK_TRACE_ELEMENT;
        }
        return this;
    }

    public StackTraceElement[] getStackTrace() {
        return (StackTraceElement[]) getOurStackTrace().clone();
    }

    private synchronized StackTraceElement[] getOurStackTrace() {
        if (this.stackTrace != EmptyArray.STACK_TRACE_ELEMENT) {
            if (this.stackTrace != null || this.backtrace == null) {
                if (this.stackTrace == null) {
                    return EmptyArray.STACK_TRACE_ELEMENT;
                }
                return this.stackTrace;
            }
        }
        this.stackTrace = nativeGetStackTrace(this.backtrace);
        this.backtrace = null;
        if (this.stackTrace == null) {
            return EmptyArray.STACK_TRACE_ELEMENT;
        }
        return this.stackTrace;
    }

    public void setStackTrace(StackTraceElement[] stackTrace2) {
        StackTraceElement[] defensiveCopy = (StackTraceElement[]) stackTrace2.clone();
        for (int i = 0; i < defensiveCopy.length; i++) {
            if (defensiveCopy[i] == null) {
                throw new NullPointerException("stackTrace[" + i + "]");
            }
        }
        synchronized (this) {
            if (!(this.stackTrace == null && this.backtrace == null)) {
                this.stackTrace = defensiveCopy;
            }
        }
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        List<Throwable> suppressed;
        s.defaultReadObject();
        List<Throwable> list = this.suppressedExceptions;
        if (list != null) {
            if (list.isEmpty()) {
                suppressed = Collections.emptyList();
            } else {
                suppressed = new ArrayList<>(1);
                for (Throwable t : this.suppressedExceptions) {
                    if (t == null) {
                        throw new NullPointerException(NULL_CAUSE_MESSAGE);
                    } else if (t != this) {
                        suppressed.add(t);
                    } else {
                        throw new IllegalArgumentException(SELF_SUPPRESSION_MESSAGE);
                    }
                }
            }
            this.suppressedExceptions = suppressed;
        }
        StackTraceElement[] stackTraceElementArr = this.stackTrace;
        if (stackTraceElementArr == null) {
            this.stackTrace = new StackTraceElement[0];
        } else if (stackTraceElementArr.length != 0) {
            if (stackTraceElementArr.length != 1 || !SentinelHolder.STACK_TRACE_ELEMENT_SENTINEL.equals(this.stackTrace[0])) {
                for (StackTraceElement ste : this.stackTrace) {
                    if (ste == null) {
                        throw new NullPointerException("null StackTraceElement in serial stream. ");
                    }
                }
                return;
            }
            this.stackTrace = null;
        }
    }

    private synchronized void writeObject(ObjectOutputStream s) throws IOException {
        Throwable th;
        getOurStackTrace();
        StackTraceElement[] oldStackTrace = this.stackTrace;
        try {
            if (this.stackTrace == null) {
                try {
                    this.stackTrace = SentinelHolder.STACK_TRACE_SENTINEL;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            s.defaultWriteObject();
            this.stackTrace = oldStackTrace;
        } catch (Throwable th3) {
            th = th3;
            this.stackTrace = oldStackTrace;
            throw th;
        }
    }

    public final synchronized void addSuppressed(Throwable exception) {
        if (exception == this) {
            throw new IllegalArgumentException(SELF_SUPPRESSION_MESSAGE, exception);
        } else if (exception == null) {
            throw new NullPointerException(NULL_CAUSE_MESSAGE);
        } else if (this.suppressedExceptions != null) {
            if (this.suppressedExceptions.isEmpty()) {
                this.suppressedExceptions = new ArrayList(1);
            }
            this.suppressedExceptions.add(exception);
        }
    }

    public final synchronized Throwable[] getSuppressed() {
        if (EMPTY_THROWABLE_ARRAY == null) {
            EMPTY_THROWABLE_ARRAY = new Throwable[0];
        }
        if (this.suppressedExceptions != null) {
            if (!this.suppressedExceptions.isEmpty()) {
                return (Throwable[]) this.suppressedExceptions.toArray(EMPTY_THROWABLE_ARRAY);
            }
        }
        return EMPTY_THROWABLE_ARRAY;
    }
}
