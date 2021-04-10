package com.squareup.okhttp.internal;

import X.AnonymousClass0HR;
import X.AnonymousClass0Oa;
import X.AnonymousClass0h1;
import java.io.IOException;

public class FaultHidingSink extends AnonymousClass0Oa {
    public boolean hasErrors;

    public void onException(IOException iOException) {
    }

    @Override // X.AnonymousClass0Oa, java.io.Closeable, java.lang.AutoCloseable, X.AnonymousClass0h1
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

    @Override // X.AnonymousClass0Oa, X.AnonymousClass0h1, java.io.Flushable
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

    @Override // X.AnonymousClass0Oa, X.AnonymousClass0h1
    public void write(AnonymousClass0HR r3, long j) throws IOException {
        if (this.hasErrors) {
            r3.A8T(j);
            return;
        }
        try {
            super.write(r3, j);
        } catch (IOException e) {
            this.hasErrors = true;
            onException(e);
        }
    }

    public FaultHidingSink(AnonymousClass0h1 r1) {
        super(r1);
    }
}
