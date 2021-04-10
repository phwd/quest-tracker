package com.squareup.okhttp.internal;

import X.AbstractC0313cc;
import X.AnonymousClass98;
import X.KA;
import java.io.IOException;

public class FaultHidingSink extends KA {
    public boolean hasErrors;

    public void onException(IOException iOException) {
    }

    @Override // X.KA, java.io.Closeable, java.lang.AutoCloseable, X.AbstractC0313cc
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

    @Override // X.KA, X.AbstractC0313cc, java.io.Flushable
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

    @Override // X.KA, X.AbstractC0313cc
    public void write(AnonymousClass98 r3, long j) throws IOException {
        if (this.hasErrors) {
            r3.A5F(j);
            return;
        }
        try {
            super.write(r3, j);
        } catch (IOException e) {
            this.hasErrors = true;
            onException(e);
        }
    }

    public FaultHidingSink(AbstractC0313cc ccVar) {
        super(ccVar);
    }
}
