package retrofit;

import java.io.IOException;
import java.io.InputStream;
import retrofit.mime.TypedInput;

class ExceptionCatchingTypedInput implements TypedInput {
    private final TypedInput delegate;
    private final ExceptionCatchingInputStream delegateStream;

    ExceptionCatchingTypedInput(TypedInput delegate2) throws IOException {
        this.delegate = delegate2;
        this.delegateStream = new ExceptionCatchingInputStream(delegate2.in());
    }

    @Override // retrofit.mime.TypedInput
    public String mimeType() {
        return this.delegate.mimeType();
    }

    @Override // retrofit.mime.TypedInput
    public long length() {
        return this.delegate.length();
    }

    @Override // retrofit.mime.TypedInput
    public InputStream in() throws IOException {
        return this.delegateStream;
    }

    /* access modifiers changed from: package-private */
    public IOException getThrownException() {
        return this.delegateStream.thrownException;
    }

    /* access modifiers changed from: package-private */
    public boolean threwException() {
        return this.delegateStream.thrownException != null;
    }

    private static class ExceptionCatchingInputStream extends InputStream {
        private final InputStream delegate;
        private IOException thrownException;

        ExceptionCatchingInputStream(InputStream delegate2) {
            this.delegate = delegate2;
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
        public int read(byte[] buffer) throws IOException {
            try {
                return this.delegate.read(buffer);
            } catch (IOException e) {
                this.thrownException = e;
                throw e;
            }
        }

        @Override // java.io.InputStream
        public int read(byte[] buffer, int offset, int length) throws IOException {
            try {
                return this.delegate.read(buffer, offset, length);
            } catch (IOException e) {
                this.thrownException = e;
                throw e;
            }
        }

        @Override // java.io.InputStream
        public long skip(long byteCount) throws IOException {
            try {
                return this.delegate.skip(byteCount);
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

        public synchronized void mark(int readLimit) {
            this.delegate.mark(readLimit);
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

        public boolean markSupported() {
            return this.delegate.markSupported();
        }
    }
}
