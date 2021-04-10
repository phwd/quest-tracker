package com.oculus.panellib;

public class SystraceBlock implements AutoCloseable {
    private final int eventId;

    public SystraceBlock(String name) {
        this.eventId = Systrace.beginBlock(name);
    }

    public SystraceBlock(String name, long startTime) {
        this.eventId = Systrace.beginBlock(name, startTime);
    }

    public SystraceBlock(String tag, String name) {
        this.eventId = Systrace.beginBlock(tag, name);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        Systrace.end(this.eventId);
    }
}
