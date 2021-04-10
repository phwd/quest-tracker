package javax.xml.transform;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class TransformerException extends Exception {
    private static final long serialVersionUID = 975798773772956428L;
    Throwable containedException;
    SourceLocator locator;

    public SourceLocator getLocator() {
        return this.locator;
    }

    public void setLocator(SourceLocator location) {
        this.locator = location;
    }

    public Throwable getException() {
        return this.containedException;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        Throwable th = this.containedException;
        if (th == this) {
            return null;
        }
        return th;
    }

    @Override // java.lang.Throwable
    public synchronized Throwable initCause(Throwable cause) {
        if (this.containedException != null) {
            throw new IllegalStateException("Can't overwrite cause");
        } else if (cause != this) {
            this.containedException = cause;
        } else {
            throw new IllegalArgumentException("Self-causation not permitted");
        }
        return this;
    }

    public TransformerException(String message) {
        super(message);
        this.containedException = null;
        this.locator = null;
    }

    public TransformerException(Throwable e) {
        super(e.toString());
        this.containedException = e;
        this.locator = null;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TransformerException(java.lang.String r2, java.lang.Throwable r3) {
        /*
            r1 = this;
            if (r2 == 0) goto L_0x000b
            int r0 = r2.length()
            if (r0 != 0) goto L_0x0009
            goto L_0x000b
        L_0x0009:
            r0 = r2
            goto L_0x000f
        L_0x000b:
            java.lang.String r0 = r3.toString()
        L_0x000f:
            r1.<init>(r0)
            r1.containedException = r3
            r0 = 0
            r1.locator = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.xml.transform.TransformerException.<init>(java.lang.String, java.lang.Throwable):void");
    }

    public TransformerException(String message, SourceLocator locator2) {
        super(message);
        this.containedException = null;
        this.locator = locator2;
    }

    public TransformerException(String message, SourceLocator locator2, Throwable e) {
        super(message);
        this.containedException = e;
        this.locator = locator2;
    }

    public String getMessageAndLocation() {
        StringBuilder sbuffer = new StringBuilder();
        String message = super.getMessage();
        if (message != null) {
            sbuffer.append(message);
        }
        SourceLocator sourceLocator = this.locator;
        if (sourceLocator != null) {
            String systemID = sourceLocator.getSystemId();
            int line = this.locator.getLineNumber();
            int column = this.locator.getColumnNumber();
            if (systemID != null) {
                sbuffer.append("; SystemID: ");
                sbuffer.append(systemID);
            }
            if (line != 0) {
                sbuffer.append("; Line#: ");
                sbuffer.append(line);
            }
            if (column != 0) {
                sbuffer.append("; Column#: ");
                sbuffer.append(column);
            }
        }
        return sbuffer.toString();
    }

    public String getLocationAsString() {
        if (this.locator == null) {
            return null;
        }
        StringBuilder sbuffer = new StringBuilder();
        String systemID = this.locator.getSystemId();
        int line = this.locator.getLineNumber();
        int column = this.locator.getColumnNumber();
        if (systemID != null) {
            sbuffer.append("; SystemID: ");
            sbuffer.append(systemID);
        }
        if (line != 0) {
            sbuffer.append("; Line#: ");
            sbuffer.append(line);
        }
        if (column != 0) {
            sbuffer.append("; Column#: ");
            sbuffer.append(column);
        }
        return sbuffer.toString();
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(new PrintWriter((OutputStream) System.err, true));
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream s) {
        printStackTrace(new PrintWriter(s));
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter s) {
        if (s == null) {
            s = new PrintWriter((OutputStream) System.err, true);
        }
        try {
            String locInfo = getLocationAsString();
            if (locInfo != null) {
                s.println(locInfo);
            }
            super.printStackTrace(s);
        } catch (Throwable th) {
        }
    }
}
