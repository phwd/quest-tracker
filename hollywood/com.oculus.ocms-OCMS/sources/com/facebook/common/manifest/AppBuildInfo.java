package com.facebook.common.manifest;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
public class AppBuildInfo {
    public final String buildPacificTimeString;
    public final long buildUTCTimestamp;
    public final String gitBranch;
    public final String gitHash;

    public AppBuildInfo(String str, String str2, long j, String str3) {
        this.gitHash = str;
        this.gitBranch = str2;
        this.buildUTCTimestamp = j;
        this.buildPacificTimeString = str3;
    }
}
