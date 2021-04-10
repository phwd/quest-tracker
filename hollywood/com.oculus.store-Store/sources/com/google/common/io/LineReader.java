package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.LinkedList;
import java.util.Queue;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Beta
@GwtIncompatible
public final class LineReader {
    private final char[] buf = this.cbuf.array();
    private final CharBuffer cbuf = CharStreams.createBuffer();
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
    @NullableDecl
    private final Reader reader;

    public LineReader(Readable readable2) {
        this.readable = (Readable) Preconditions.checkNotNull(readable2);
        this.reader = readable2 instanceof Reader ? (Reader) readable2 : null;
    }

    @CanIgnoreReturnValue
    public String readLine() throws IOException {
        while (true) {
            if (this.lines.peek() != null) {
                break;
            }
            this.cbuf.clear();
            int read = this.reader != null ? this.reader.read(this.buf, 0, this.buf.length) : this.readable.read(this.cbuf);
            if (read == -1) {
                this.lineBuf.finish();
                break;
            }
            this.lineBuf.add(this.buf, 0, read);
        }
        return this.lines.poll();
    }
}
