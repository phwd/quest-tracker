package com.oculus.panellib;

import com.oculus.panellib.Systrace;

public class SystraceBlock implements AutoCloseable {
    public final int eventId;

    @Override // java.lang.AutoCloseable
    public void close() {
        Systrace.end(this.eventId);
    }

    public SystraceBlock(String str) {
        this.eventId = Systrace.begin(Systrace.EventType.BLOCK, str);
    }

    public SystraceBlock(String str, long j) {
        this.eventId = Systrace.begin(Systrace.EventType.BLOCK, str, j);
    }

    public SystraceBlock(String str, String str2) {
        this.eventId = Systrace.beginBlock(str, str2);
    }
}
