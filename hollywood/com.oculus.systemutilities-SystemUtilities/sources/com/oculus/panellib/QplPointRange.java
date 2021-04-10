package com.oculus.panellib;

public class QplPointRange implements AutoCloseable {
    private final int markerId;
    private final String name;
    private final int systraceBlock;

    public QplPointRange(int markerId2, String name2) {
        this(markerId2, name2, 0);
    }

    public QplPointRange(int markerId2, String name2, int instanceKey) {
        this.systraceBlock = Systrace.beginBlock(name2);
        Qpl.markerPointStart(markerId2, name2);
        this.markerId = markerId2;
        this.name = name2;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        Qpl.markerPointEnd(this.markerId, this.name);
        Systrace.end(this.systraceBlock);
    }
}
