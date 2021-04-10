package com.oculus.panellib;

public class SystraceBlock implements AutoCloseable {
    private final int eventId;

    public SystraceBlock(String str) {
        this.eventId = Systrace.beginBlock(str);
    }

    public SystraceBlock(String str, long j) {
        this.eventId = Systrace.beginBlock(str, j);
    }

    public SystraceBlock(String str, String str2) {
        this.eventId = Systrace.beginBlock(str, str2);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        Systrace.end(this.eventId);
    }
}
