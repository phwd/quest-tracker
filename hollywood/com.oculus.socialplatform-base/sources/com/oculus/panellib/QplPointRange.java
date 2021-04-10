package com.oculus.panellib;

import com.oculus.panellib.Systrace;

public class QplPointRange implements AutoCloseable {
    public final int markerId;
    public final String name;
    public final int systraceBlock;

    @Override // java.lang.AutoCloseable
    public void close() {
        Qpl.markerPointEnd(this.markerId, this.name, 0);
        Systrace.end(this.systraceBlock);
    }

    public QplPointRange(int i, String str) {
        this(i, str, 0);
    }

    public QplPointRange(int i, String str, int i2) {
        this.systraceBlock = Systrace.begin(Systrace.EventType.BLOCK, str);
        Qpl.markerPointStart(i, str, 0);
        this.markerId = i;
        this.name = str;
    }
}
