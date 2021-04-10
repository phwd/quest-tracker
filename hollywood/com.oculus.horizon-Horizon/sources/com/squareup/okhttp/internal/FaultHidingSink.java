package com.squareup.okhttp.internal;

import X.AbstractC07640v7;
import X.AnonymousClass0B3;
import X.AnonymousClass0Lu;
import java.io.IOException;

public class FaultHidingSink extends AnonymousClass0Lu {
    public boolean hasErrors;

    public void onException(IOException iOException) {
    }

    @Override // X.AbstractC07640v7, java.io.Closeable, java.lang.AutoCloseable, X.AnonymousClass0Lu
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

    @Override // X.AbstractC07640v7, X.AnonymousClass0Lu, java.io.Flushable
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

    @Override // X.AbstractC07640v7, X.AnonymousClass0Lu
    public void write(AnonymousClass0B3 r3, long j) throws IOException {
        if (this.hasErrors) {
            r3.A94(j);
            return;
        }
        try {
            super.write(r3, j);
        } catch (IOException e) {
            this.hasErrors = true;
            onException(e);
        }
    }

    public FaultHidingSink(AbstractC07640v7 r1) {
        super(r1);
    }
}
