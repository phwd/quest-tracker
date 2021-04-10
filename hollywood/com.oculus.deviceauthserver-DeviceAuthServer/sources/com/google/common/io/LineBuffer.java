package com.google.common.io;

import java.io.IOException;

/* access modifiers changed from: package-private */
public abstract class LineBuffer {
    private StringBuilder line = new StringBuilder();
    private boolean sawReturn;

    /* access modifiers changed from: protected */
    public abstract void handleLine(String str, String str2) throws IOException;

    LineBuffer() {
    }

    /* access modifiers changed from: protected */
    public void add(char[] cbuf, int off, int len) throws IOException {
        int pos = off;
        if (this.sawReturn && len > 0) {
            if (finishLine(cbuf[pos] == '\n')) {
                pos++;
            }
        }
        int start = pos;
        int end = off + len;
        while (pos < end) {
            char c = cbuf[pos];
            if (c == '\n') {
                this.line.append(cbuf, start, pos - start);
                finishLine(true);
                start = pos + 1;
            } else if (c == '\r') {
                this.line.append(cbuf, start, pos - start);
                this.sawReturn = true;
                if (pos + 1 < end) {
                    if (finishLine(cbuf[pos + 1] == '\n')) {
                        pos++;
                    }
                }
                start = pos + 1;
            }
            pos++;
        }
        this.line.append(cbuf, start, (off + len) - start);
    }

    private boolean finishLine(boolean sawNewline) throws IOException {
        String str;
        String sb = this.line.toString();
        if (this.sawReturn) {
            str = sawNewline ? "\r\n" : "\r";
        } else {
            str = sawNewline ? "\n" : "";
        }
        handleLine(sb, str);
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
