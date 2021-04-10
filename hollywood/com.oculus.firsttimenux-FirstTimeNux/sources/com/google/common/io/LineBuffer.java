package com.google.common.io;

import com.facebook.common.exceptionhandler.ExceptionHandlerManager;
import com.facebook.zstd.AbstractZstdOutputStream;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;

/* access modifiers changed from: package-private */
@GwtIncompatible
public abstract class LineBuffer {
    private StringBuilder line = new StringBuilder();
    private boolean sawReturn;

    /* access modifiers changed from: protected */
    public abstract void handleLine(String str, String str2) throws IOException;

    LineBuffer() {
    }

    /* access modifiers changed from: protected */
    public void add(char[] cbuf, int off, int len) throws IOException {
        boolean z;
        int pos = off;
        if (this.sawReturn && len > 0) {
            if (cbuf[pos] == '\n') {
                z = true;
            } else {
                z = false;
            }
            if (finishLine(z)) {
                pos++;
            }
        }
        int start = pos;
        int end = off + len;
        while (pos < end) {
            switch (cbuf[pos]) {
                case ExceptionHandlerManager.PRIORITY_EARLY:
                    this.line.append(cbuf, start, pos - start);
                    finishLine(true);
                    start = pos + 1;
                    break;
                case AbstractZstdOutputStream.DEFAULT_COMPRESSION_LEVEL:
                    this.line.append(cbuf, start, pos - start);
                    this.sawReturn = true;
                    if (pos + 1 < end) {
                        if (finishLine(cbuf[pos + 1] == '\n')) {
                            pos++;
                        }
                    }
                    start = pos + 1;
                    break;
            }
            pos++;
        }
        this.line.append(cbuf, start, (off + len) - start);
    }

    @CanIgnoreReturnValue
    private boolean finishLine(boolean sawNewline) throws IOException {
        handleLine(this.line.toString(), this.sawReturn ? sawNewline ? "\r\n" : "\r" : sawNewline ? "\n" : "");
        this.line = new StringBuilder();
        this.sawReturn = false;
        return sawNewline;
    }

    /* access modifiers changed from: protected */
    public void finish() throws IOException {
        if (this.sawReturn || this.line.length() > 0) {
            finishLine(false);
        }
    }
}
