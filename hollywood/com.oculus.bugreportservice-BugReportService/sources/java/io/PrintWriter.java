package java.io;

import java.security.AccessController;
import sun.security.action.GetPropertyAction;

public class PrintWriter extends Writer {
    private final boolean autoFlush;
    private final String lineSeparator;
    protected Writer out;
    private PrintStream psOut;
    private boolean trouble;

    public PrintWriter(Writer writer) {
        this(writer, false);
    }

    public PrintWriter(Writer writer, boolean z) {
        super(writer);
        this.trouble = false;
        this.psOut = null;
        this.out = writer;
        this.autoFlush = z;
        this.lineSeparator = (String) AccessController.doPrivileged(new GetPropertyAction("line.separator"));
    }

    private void ensureOpen() {
        if (this.out == null) {
            throw new IOException("Stream closed");
        }
    }

    @Override // java.io.Writer
    public void flush() {
        try {
            synchronized (this.lock) {
                ensureOpen();
                this.out.flush();
            }
        } catch (IOException unused) {
            this.trouble = true;
        }
    }

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public void close() {
        try {
            synchronized (this.lock) {
                if (this.out != null) {
                    this.out.close();
                    this.out = null;
                }
            }
        } catch (IOException unused) {
            this.trouble = true;
        }
    }

    @Override // java.io.Writer
    public void write(int i) {
        try {
            synchronized (this.lock) {
                ensureOpen();
                this.out.write(i);
            }
        } catch (InterruptedIOException unused) {
            Thread.currentThread().interrupt();
        } catch (IOException unused2) {
            this.trouble = true;
        }
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        try {
            synchronized (this.lock) {
                ensureOpen();
                this.out.write(cArr, i, i2);
            }
        } catch (InterruptedIOException unused) {
            Thread.currentThread().interrupt();
        } catch (IOException unused2) {
            this.trouble = true;
        }
    }

    @Override // java.io.Writer
    public void write(String str, int i, int i2) {
        try {
            synchronized (this.lock) {
                ensureOpen();
                this.out.write(str, i, i2);
            }
        } catch (InterruptedIOException unused) {
            Thread.currentThread().interrupt();
        } catch (IOException unused2) {
            this.trouble = true;
        }
    }

    @Override // java.io.Writer
    public void write(String str) {
        write(str, 0, str.length());
    }

    private void newLine() {
        try {
            synchronized (this.lock) {
                ensureOpen();
                this.out.write(this.lineSeparator);
                if (this.autoFlush) {
                    this.out.flush();
                }
            }
        } catch (InterruptedIOException unused) {
            Thread.currentThread().interrupt();
        } catch (IOException unused2) {
            this.trouble = true;
        }
    }

    public void print(String str) {
        if (str == null) {
            str = "null";
        }
        write(str);
    }

    public void println() {
        newLine();
    }

    public void println(Object obj) {
        String valueOf = String.valueOf(obj);
        synchronized (this.lock) {
            print(valueOf);
            println();
        }
    }

    @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
    public PrintWriter append(CharSequence charSequence) {
        if (charSequence == null) {
            write("null");
        } else {
            write(charSequence.toString());
        }
        return this;
    }

    @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
    public PrintWriter append(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            charSequence = "null";
        }
        write(charSequence.subSequence(i, i2).toString());
        return this;
    }

    @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
    public PrintWriter append(char c) {
        write(c);
        return this;
    }
}
