package android.icu.impl.data;

import java.io.IOException;

public class TokenIterator {
    private StringBuffer buf = new StringBuffer();
    private boolean done = false;
    private int lastpos = -1;
    private String line = null;
    private int pos = -1;
    private ResourceReader reader;

    public TokenIterator(ResourceReader r) {
        this.reader = r;
    }

    public String next() throws IOException {
        if (this.done) {
            return null;
        }
        while (true) {
            if (this.line == null) {
                this.line = this.reader.readLineSkippingComments();
                if (this.line == null) {
                    this.done = true;
                    return null;
                }
                this.pos = 0;
            }
            this.buf.setLength(0);
            int i = this.pos;
            this.lastpos = i;
            this.pos = nextToken(i);
            if (this.pos >= 0) {
                return this.buf.toString();
            }
            this.line = null;
        }
    }

    public int getLineNumber() {
        return this.reader.getLineNumber();
    }

    public String describePosition() {
        return this.reader.describePosition() + ':' + (this.lastpos + 1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x009e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int nextToken(int r10) {
        /*
        // Method dump skipped, instructions count: 194
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.data.TokenIterator.nextToken(int):int");
    }
}
