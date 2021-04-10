package retrofit;

import java.io.IOException;
import java.io.InputStream;
import retrofit.mime.TypedInput;

public class ExceptionCatchingTypedInput implements TypedInput {
    public final TypedInput delegate;
    public final ExceptionCatchingInputStream delegateStream;

    public static class ExceptionCatchingInputStream extends InputStream {
        public final InputStream delegate;
        public IOException thrownException;

        public synchronized void mark(int i) {
            this.delegate.mark(i);
        }

        @Override // java.io.InputStream
        public synchronized void reset() throws IOException {
            try {
                this.delegate.reset();
            } catch (IOException e) {
                this.thrownException = e;
                throw e;
            }
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            try {
                return this.delegate.available();
            } catch (IOException e) {
                this.thrownException = e;
                throw e;
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
        public void close() throws IOException {
            try {
                this.delegate.close();
            } catch (IOException e) {
                this.thrownException = e;
                throw e;
            }
        }

        public boolean markSupported() {
            return this.delegate.markSupported();
        }

        @Override // java.io.InputStream
        public long skip(long j) throws IOException {
            try {
                return this.delegate.skip(j);
            } catch (IOException e) {
                this.thrownException = e;
                throw e;
            }
        }

        public ExceptionCatchingInputStream(InputStream inputStream) {
            this.delegate = inputStream;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            try {
                return this.delegate.read();
            } catch (IOException e) {
                this.thrownException = e;
                throw e;
            }
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr) throws IOException {
            try {
                return this.delegate.read(bArr);
            } catch (IOException e) {
                this.thrownException = e;
                throw e;
            }
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            try {
                return this.delegate.read(bArr, i, i2);
            } catch (IOException e) {
                this.thrownException = e;
                throw e;
            }
        }
    }

    public IOException getThrownException() {
        return this.delegateStream.thrownException;
    }

    @Override // retrofit.mime.TypedInput
    public InputStream in() throws IOException {
        return this.delegateStream;
    }

    @Override // retrofit.mime.TypedInput
    public long length() {
        return this.delegate.length();
    }

    @Override // retrofit.mime.TypedInput
    public String mimeType() {
        return this.delegate.mimeType();
    }

    public boolean threwException() {
        if (this.delegateStream.thrownException != null) {
            return true;
        }
        return false;
    }

    public ExceptionCatchingTypedInput(TypedInput typedInput) throws IOException {
        this.delegate = typedInput;
        this.delegateStream = new ExceptionCatchingInputStream(typedInput.in());
    }
}
