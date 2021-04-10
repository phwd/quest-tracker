package com.squareup.okhttp.internal;

import X.AnonymousClass8k;
import X.Dn;
import X.WG;
import java.io.IOException;

public class FaultHidingSink extends Dn {
    public boolean hasErrors;

    public void onException(IOException iOException) {
    }

    @Override // java.io.Closeable, X.Dn, java.lang.AutoCloseable, X.WG
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

    @Override // X.Dn, X.WG, java.io.Flushable
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

    @Override // X.Dn, X.WG
    public void write(AnonymousClass8k r3, long j) throws IOException {
        if (this.hasErrors) {
            r3.A3i(j);
            return;
        }
        try {
            super.write(r3, j);
        } catch (IOException e) {
            this.hasErrors = true;
            onException(e);
        }
    }

    public FaultHidingSink(WG wg) {
        super(wg);
    }
}
