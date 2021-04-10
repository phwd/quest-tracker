package com.android.okhttp.internal;

import com.android.okhttp.okio.Buffer;
import com.android.okhttp.okio.ForwardingSink;
import com.android.okhttp.okio.Sink;
import java.io.IOException;

/* access modifiers changed from: package-private */
public class FaultHidingSink extends ForwardingSink {
    private boolean hasErrors;

    public FaultHidingSink(Sink delegate) {
        super(delegate);
    }

    @Override // com.android.okhttp.okio.Sink, com.android.okhttp.okio.ForwardingSink
    public void write(Buffer source, long byteCount) throws IOException {
        if (this.hasErrors) {
            source.skip(byteCount);
            return;
        }
        try {
            super.write(source, byteCount);
        } catch (IOException e) {
            this.hasErrors = true;
            onException(e);
        }
    }

    @Override // com.android.okhttp.okio.Sink, com.android.okhttp.okio.ForwardingSink, java.io.Flushable
    public void flush() throws IOException {
        if (!this.hasErrors) {
            try {
                super.flush();
            } catch (IOException e) {
                this.hasErrors = true;
                onException(e);
            }
        }
    }

    @Override // com.android.okhttp.okio.Sink, com.android.okhttp.okio.ForwardingSink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.hasErrors) {
            try {
                super.close();
            } catch (IOException e) {
                this.hasErrors = true;
                onException(e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onException(IOException e) {
    }
}
