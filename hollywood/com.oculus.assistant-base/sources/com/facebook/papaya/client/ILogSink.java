package com.facebook.papaya.client;

public interface ILogSink {
    void event(long j, long j2, long j3, int i, String str);

    String getId();

    void log(long j, long j2, long j3, int i, String str, int i2, String str2);
}
