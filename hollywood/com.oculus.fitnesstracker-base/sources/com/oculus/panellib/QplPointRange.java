package com.oculus.panellib;

public class QplPointRange implements AutoCloseable {
    private final int markerId;
    private final String name;
    private final int systraceBlock;

    public QplPointRange(int i, String str) {
        this(i, str, 0);
    }

    public QplPointRange(int i, String str, int i2) {
        this.systraceBlock = Systrace.beginBlock(str);
        Qpl.markerPointStart(i, str);
        this.markerId = i;
        this.name = str;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        Qpl.markerPointEnd(this.markerId, this.name);
        Systrace.end(this.systraceBlock);
    }
}
