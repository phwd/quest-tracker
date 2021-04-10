package javax.xml.datatype;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class DatatypeConfigurationException extends Exception {
    private static final long serialVersionUID = -1699373159027047238L;
    private Throwable causeOnJDK13OrBelow;
    private transient boolean isJDK14OrAbove;

    public DatatypeConfigurationException() {
        this.isJDK14OrAbove = false;
    }

    public DatatypeConfigurationException(String message) {
        super(message);
        this.isJDK14OrAbove = false;
    }

    public DatatypeConfigurationException(String message, Throwable cause) {
        super(message);
        this.isJDK14OrAbove = false;
        initCauseByReflection(cause);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DatatypeConfigurationException(Throwable cause) {
        super(cause == null ? null : cause.toString());
        this.isJDK14OrAbove = false;
        initCauseByReflection(cause);
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        if (this.isJDK14OrAbove || this.causeOnJDK13OrBelow == null) {
            super.printStackTrace();
        } else {
            printStackTrace0(new PrintWriter((OutputStream) System.err, true));
        }
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream s) {
        if (this.isJDK14OrAbove || this.causeOnJDK13OrBelow == null) {
            super.printStackTrace(s);
        } else {
            printStackTrace0(new PrintWriter(s));
        }
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter s) {
        if (this.isJDK14OrAbove || this.causeOnJDK13OrBelow == null) {
            super.printStackTrace(s);
        } else {
            printStackTrace0(s);
        }
    }

    private void printStackTrace0(PrintWriter s) {
        this.causeOnJDK13OrBelow.printStackTrace(s);
        s.println("------------------------------------------");
        super.printStackTrace(s);
    }

    private void initCauseByReflection(Throwable cause) {
        this.causeOnJDK13OrBelow = cause;
        try {
            getClass().getMethod("initCause", Throwable.class).invoke(this, cause);
            this.isJDK14OrAbove = true;
        } catch (Exception e) {
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        try {
            Throwable cause = (Throwable) getClass().getMethod("getCause", new Class[0]).invoke(this, new Object[0]);
            if (this.causeOnJDK13OrBelow == null) {
                this.causeOnJDK13OrBelow = cause;
            } else if (cause == null) {
                getClass().getMethod("initCause", Throwable.class).invoke(this, this.causeOnJDK13OrBelow);
            }
            this.isJDK14OrAbove = true;
        } catch (Exception e) {
        }
    }
}
