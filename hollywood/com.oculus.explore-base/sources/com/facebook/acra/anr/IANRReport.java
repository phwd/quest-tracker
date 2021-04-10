package com.facebook.acra.anr;

import java.io.IOException;

public interface IANRReport {
    void finalizeAndTryToSendReport(long j);

    void logOtherProcessAnr(String str, String str2, String str3, long j);

    void logProcessMonitorFailure(long j, int i);

    void logProcessMonitorStart(long j);

    void logSystemInfo(String str, String str2, long j);

    void startReport(boolean z, String str, String str2, int i, boolean z2, boolean z3, long j, long j2, long j3, long j4, String str3, String str4, boolean z4, boolean z5, Long l) throws IOException;
}
