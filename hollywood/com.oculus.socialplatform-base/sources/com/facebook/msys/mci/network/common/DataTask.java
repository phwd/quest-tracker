package com.facebook.msys.mci.network.common;

import X.AnonymousClass1Nh;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.concurrent.Immutable;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
public class DataTask {
    @DoNotStrip
    public final long mContentLength;
    @DoNotStrip
    public final String mContentUrl;
    @DoNotStrip
    public final long mNativeContext;
    @DoNotStrip
    public final String mTaskCategory;
    @DoNotStrip
    public final String mTaskIdentifier;
    @DoNotStrip
    public final int mTaskMode;
    @DoNotStrip
    public final int mTaskType;
    @DoNotStrip
    public final UrlRequest mUrlRequest;

    static {
        AnonymousClass1Nh.A00();
    }

    @DoNotStrip
    public DataTask(String str, String str2, int i, UrlRequest urlRequest, String str3, long j, int i2, long j2) {
        this.mTaskCategory = str;
        this.mTaskIdentifier = str2;
        this.mTaskType = i;
        this.mUrlRequest = urlRequest;
        this.mContentUrl = str3;
        this.mContentLength = j;
        this.mTaskMode = i2;
        this.mNativeContext = j2;
    }
}
