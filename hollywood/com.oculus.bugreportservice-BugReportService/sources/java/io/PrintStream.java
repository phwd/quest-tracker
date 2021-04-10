package java.io;

import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

public class PrintStream extends FilterOutputStream implements Appendable, Closeable {
    private final boolean autoFlush;
    private OutputStreamWriter charOut;
    private Charset charset;
    private boolean closing;
    private BufferedWriter textOut;
    private boolean trouble;

    private static Object requireNonNull(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(str);
    }

    private static Charset toCharset(String str) {
        requireNonNull(str, "charsetName");
        try {
            return Charset.forName(str);
        } catch (IllegalCharsetNameException | UnsupportedCharsetException unused) {
            throw new UnsupportedEncodingException(str);
        }
    }

    private PrintStream(boolean z, OutputStream outputStream) {
        super(outputStream);
        this.trouble = false;
        this.closing = false;
        this.autoFlush = z;
    }

    private PrintStream(boolean z, OutputStream outputStream, Charset charset2) {
        super(outputStream);
        this.trouble = false;
        this.closing = false;
        this.autoFlush = z;
        this.charset = charset2;
    }

    public PrintStream(OutputStream outputStream) {
        this(outputStream, false);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PrintStream(OutputStream outputStream, boolean z) {
        this(z, outputStream);
        requireNonNull(outputStream, "Null output stream");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PrintStream(OutputStream outputStream, boolean z, String str) {
        this(z, outputStream, toCharset(str));
        requireNonNull(outputStream, "Null output stream");
    }

    private void ensureOpen() {
        if (this.out == null) {
            throw new IOException("Stream closed");
        }
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void flush() {
        synchronized (this) {
            try {
                ensureOpen();
                this.out.flush();
            } catch (IOException unused) {
                this.trouble = true;
            }
        }
    }

    private BufferedWriter getTextOut() {
        OutputStreamWriter outputStreamWriter;
        if (this.textOut == null) {
            Charset charset2 = this.charset;
            if (charset2 != null) {
                outputStreamWriter = new OutputStreamWriter(this, charset2);
            } else {
                outputStreamWriter = new OutputStreamWriter(this);
            }
            this.charOut = outputStreamWriter;
            this.textOut = new BufferedWriter(this.charOut);
        }
        return this.textOut;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.io.FilterOutputStream, java.lang.AutoCloseable
    public void close() {
        synchronized (this) {
            if (!this.closing) {
                this.closing = true;
                try {
                    if (this.textOut != null) {
                        this.textOut.close();
                    }
                    this.out.close();
                } catch (IOException unused) {
                    this.trouble = true;
                }
                this.textOut = null;
                this.charOut = null;
                this.out = null;
            }
        }
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        try {
            synchronized (this) {
                ensureOpen();
                this.out.write(i);
                if (i == 10 && this.autoFlush) {
                    this.out.flush();
                }
            }
        } catch (InterruptedIOException unused) {
            Thread.currentThread().interrupt();
        } catch (IOException unused2) {
            this.trouble = true;
        }
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(byte[] bArr, int i, int i2) {
        try {
            synchronized (this) {
                ensureOpen();
                this.out.write(bArr, i, i2);
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

    private void write(String str) {
        try {
            synchronized (this) {
                ensureOpen();
                BufferedWriter textOut2 = getTextOut();
                textOut2.write(str);
                textOut2.flushBuffer();
                this.charOut.flushBuffer();
                if (this.autoFlush && str.indexOf(10) >= 0) {
                    this.out.flush();
                }
            }
        } catch (InterruptedIOException unused) {
            Thread.currentThread().interrupt();
        } catch (IOException unused2) {
            this.trouble = true;
        }
    }

    private void newLine() {
        try {
            synchronized (this) {
                ensureOpen();
                BufferedWriter textOut2 = getTextOut();
                textOut2.newLine();
                textOut2.flushBuffer();
                this.charOut.flushBuffer();
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

    public void print(char c) {
        write(String.valueOf(c));
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

    public void println(String str) {
        synchronized (this) {
            print(str);
            newLine();
        }
    }

    public void println(Object obj) {
        String valueOf = String.valueOf(obj);
        synchronized (this) {
            print(valueOf);
            newLine();
        }
    }

    @Override // java.lang.Appendable
    public PrintStream append(CharSequence charSequence) {
        if (charSequence == null) {
            print("null");
        } else {
            print(charSequence.toString());
        }
        return this;
    }

    @Override // java.lang.Appendable
    public PrintStream append(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            charSequence = "null";
        }
        write(charSequence.subSequence(i, i2).toString());
        return this;
    }

    @Override // java.lang.Appendable
    public PrintStream append(char c) {
        print(c);
        return this;
    }
}
