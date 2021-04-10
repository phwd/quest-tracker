package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.LinkedList;
import java.util.Queue;

@Beta
public final class LineReader {
    private final char[] buf = new char[4096];
    private final CharBuffer cbuf = CharBuffer.wrap(this.buf);
    private final LineBuffer lineBuf = new LineBuffer() {
        /* class com.google.common.io.LineReader.AnonymousClass1 */

        /* access modifiers changed from: protected */
        @Override // com.google.common.io.LineBuffer
        public void handleLine(String line, String end) {
            LineReader.this.lines.add(line);
        }
    };
    private final Queue<String> lines = new LinkedList();
    private final Readable readable;
    private final Reader reader;

    public LineReader(Readable readable2) {
        this.readable = (Readable) Preconditions.checkNotNull(readable2);
        this.reader = readable2 instanceof Reader ? (Reader) readable2 : null;
    }

    public String readLine() throws IOException {
        int read;
        while (true) {
            if (this.lines.peek() != null) {
                break;
            }
            this.cbuf.clear();
            Reader reader2 = this.reader;
            if (reader2 != null) {
                char[] cArr = this.buf;
                read = reader2.read(cArr, 0, cArr.length);
            } else {
                read = this.readable.read(this.cbuf);
            }
            if (read == -1) {
                this.lineBuf.finish();
                break;
            }
            this.lineBuf.add(this.buf, 0, read);
        }
        return this.lines.poll();
    }
}
