package java.lang;

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
    private static Throwable[] EMPTY_THROWABLE_ARRAY = null;
    private static final long serialVersionUID = -3042686055658047285L;
    private transient Object backtrace;
    private Throwable cause = this;
    private String detailMessage;
    private StackTraceElement[] stackTrace = EmptyArray.STACK_TRACE_ELEMENT;
    private List suppressedExceptions = Collections.emptyList();

    private static class SentinelHolder {
        public static final StackTraceElement STACK_TRACE_ELEMENT_SENTINEL = new StackTraceElement("", "", null, Integer.MIN_VALUE);
        public static final StackTraceElement[] STACK_TRACE_SENTINEL = {STACK_TRACE_ELEMENT_SENTINEL};
    }

    private static native Object nativeFillInStackTrace();

    private static native StackTraceElement[] nativeGetStackTrace(Object obj);

    public Throwable() {
        fillInStackTrace();
    }

    public Throwable(String str) {
        fillInStackTrace();
        this.detailMessage = str;
    }

    public Throwable(String str, Throwable th) {
        fillInStackTrace();
        this.detailMessage = str;
        this.cause = th;
    }

    public Throwable(Throwable th) {
        String str;
        fillInStackTrace();
        if (th == null) {
            str = null;
        } else {
            str = th.toString();
        }
        this.detailMessage = str;
        this.cause = th;
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

    public synchronized Throwable initCause(Throwable th) {
        if (this.cause != this) {
            throw new IllegalStateException("Can't overwrite cause with " + Objects.toString(th, "a null"), this);
        } else if (th != this) {
            this.cause = th;
        } else {
            throw new IllegalArgumentException("Self-causation not permitted", this);
        }
        return this;
    }

    public String toString() {
        String name = getClass().getName();
        String localizedMessage = getLocalizedMessage();
        if (localizedMessage == null) {
            return name;
        }
        return name + ": " + localizedMessage;
    }

    public void printStackTrace() {
        printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream printStream) {
        printStackTrace(new WrappedPrintStream(printStream));
    }

    private void printStackTrace(PrintStreamOrWriter printStreamOrWriter) {
        Set newSetFromMap = Collections.newSetFromMap(new IdentityHashMap());
        newSetFromMap.add(this);
        synchronized (printStreamOrWriter.lock()) {
            printStreamOrWriter.println(this);
            StackTraceElement[] ourStackTrace = getOurStackTrace();
            for (StackTraceElement stackTraceElement : ourStackTrace) {
                printStreamOrWriter.println("\tat " + stackTraceElement);
            }
            for (Throwable th : getSuppressed()) {
                th.printEnclosedStackTrace(printStreamOrWriter, ourStackTrace, "Suppressed: ", "\t", newSetFromMap);
            }
            Throwable cause2 = getCause();
            if (cause2 != null) {
                cause2.printEnclosedStackTrace(printStreamOrWriter, ourStackTrace, "Caused by: ", "", newSetFromMap);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v3, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    private void printEnclosedStackTrace(PrintStreamOrWriter printStreamOrWriter, StackTraceElement[] stackTraceElementArr, String str, String str2, Set set) {
        if (set.contains(this)) {
            printStreamOrWriter.println("\t[CIRCULAR REFERENCE:" + this + "]");
            return;
        }
        set.add(this);
        StackTraceElement[] ourStackTrace = getOurStackTrace();
        int length = ourStackTrace.length - 1;
        int length2 = stackTraceElementArr.length - 1;
        while (length >= 0 && length2 >= 0 && ourStackTrace[length].equals(stackTraceElementArr[length2])) {
            length--;
            length2--;
        }
        int length3 = (ourStackTrace.length - 1) - length;
        printStreamOrWriter.println(str2 + str + this);
        for (int i = 0; i <= length; i++) {
            printStreamOrWriter.println(str2 + "\tat " + ourStackTrace[i]);
        }
        if (length3 != 0) {
            printStreamOrWriter.println(str2 + "\t... " + length3 + " more");
        }
        for (Throwable th : getSuppressed()) {
            th.printEnclosedStackTrace(printStreamOrWriter, ourStackTrace, "Suppressed: ", str2 + "\t", set);
        }
        Throwable cause2 = getCause();
        if (cause2 != null) {
            cause2.printEnclosedStackTrace(printStreamOrWriter, ourStackTrace, "Caused by: ", str2, set);
        }
    }

    public void printStackTrace(PrintWriter printWriter) {
        printStackTrace(new WrappedPrintWriter(printWriter));
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
        public void println(Object obj) {
            this.printStream.println(obj);
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
        public void println(Object obj) {
            this.printWriter.println(obj);
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

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }

    private synchronized void writeObject(ObjectOutputStream objectOutputStream) {
        getOurStackTrace();
        StackTraceElement[] stackTraceElementArr = this.stackTrace;
        try {
            if (this.stackTrace == null) {
                stackTraceElementArr = SentinelHolder.STACK_TRACE_SENTINEL;
            }
            objectOutputStream.defaultWriteObject();
            throw null;
        } finally {
            this.stackTrace = stackTraceElementArr;
        }
    }

    public final synchronized void addSuppressed(Throwable th) {
        if (th == this) {
            throw new IllegalArgumentException("Self-suppression not permitted", th);
        } else if (th == null) {
            throw new NullPointerException("Cannot suppress a null exception.");
        } else if (this.suppressedExceptions != null) {
            if (this.suppressedExceptions.isEmpty()) {
                this.suppressedExceptions = new ArrayList(1);
            }
            this.suppressedExceptions.add(th);
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
