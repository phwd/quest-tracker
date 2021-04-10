package com.facebook.acra.anr;

import java.io.IOException;
import javax.annotation.Nullable;

public interface IANRReport {
    void finalizeAndTryToSendReport(long j);

    void logAmExpiration(long j);

    void logExtraSigquit(long j);

    void logMainThreadUnblocked(long j);

    void logOtherProcessAnr(String str, @Nullable String str2, @Nullable String str3, long j);

    void logProcessMonitorFailure(long j, int i);

    void logProcessMonitorStart(long j);

    void logSigquitData(@Nullable String str, @Nullable String str2, long j);

    void logSystemInfo(@Nullable String str, @Nullable String str2, long j);

    void startReport(boolean z, @Nullable String str, @Nullable String str2, int i, boolean z2, boolean z3, long j, long j2, long j3, long j4, @Nullable String str3, @Nullable String str4, boolean z4, boolean z5, @Nullable Long l) throws IOException;
}
