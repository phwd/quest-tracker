package com.facebook.graphservice.interfaces;

import androidx.annotation.Nullable;
import com.facebook.proguard.annotations.DoNotStrip;
import com.google.common.base.MoreObjects;

@DoNotStrip
public class Summary {
    public final int A00;
    public final int A01;
    public final long A02;
    public final long A03;
    public final long A04;
    public final long A05;
    public final long A06;
    public final long A07;
    public final long A08;
    public final long A09;
    public final long A0A;
    public final long A0B;
    public final long A0C;
    public final long A0D;
    public final long A0E;
    @Nullable
    public final GraphQLQuery A0F;
    public final String A0G;
    public final String A0H;
    public final String A0I;
    @Source
    public final String A0J;
    public final boolean A0K;
    public final boolean A0L;
    public final boolean A0M;
    public final boolean A0N;
    public final String[] A0O;

    public @interface Source {
    }

    public final String toString() {
        MoreObjects.ToStringHelper toStringHelper = new MoreObjects.ToStringHelper(getClass().getSimpleName());
        toStringHelper.add("source", this.A0J);
        toStringHelper.add("isFinal", this.A0L);
        toStringHelper.add("isNetworkComplete", this.A0M);
        toStringHelper.add("attempts", this.A00);
        toStringHelper.add("fbRequestId", this.A0H);
        toStringHelper.add("requestStart", this.A0A);
        toStringHelper.add("networkStart", this.A07);
        toStringHelper.add("networkEnd", this.A06);
        toStringHelper.add("parseStart", this.A08);
        toStringHelper.add("requestEnd", this.A09);
        toStringHelper.add("parsedDataSize", this.A01);
        toStringHelper.add("additiveParseTimeMs", this.A02);
        toStringHelper.add("fetchCachedResponseStart", this.A05);
        toStringHelper.add("fetchCachedResponseEnd", this.A04);
        toStringHelper.add("cachedResponseAge", this.A03);
        toStringHelper.add("freshResponse", this.A0K);
        toStringHelper.add("consistencySource", this.A0G);
        toStringHelper.add("serverStartTime", this.A0D);
        toStringHelper.add("serverFlushTime", this.A0C);
        toStringHelper.add("rejectedFromAdaptiveFetch", this.A0N);
        toStringHelper.add("prefetchPredictionID", this.A0I);
        toStringHelper.add("rtt", this.A0B);
        toStringHelper.add("upstreamLatency", this.A0E);
        Object obj = this.A0O;
        if (obj == null) {
            obj = new int[0];
        }
        toStringHelper.add("experimentalUriPrefetch", obj);
        return toStringHelper.toString();
    }

    @DoNotStrip
    public Summary(@Source String str, boolean z, boolean z2, String str2, int i, long j, long j2, long j3, long j4, long j5, int i2, long j6, long j7, long j8, long j9, boolean z3, int i3, int i4, String str3, String str4, String str5, boolean z4, boolean z5, boolean z6, String str6, long j10, long j11, long j12, long j13, boolean z7, String str7, long j14, long j15, String[] strArr) {
        this(str, z, z2, str2, i, j, j2, j3, j4, j5, i2, j6, j7, j8, j9, z3, i3, i4, str3, str4, str5, z4, z5, z6, str6, j10, j11, j12, j13, z7, str7, j14, j15, strArr, null);
    }

    @DoNotStrip
    public Summary(@Source String str, boolean z, boolean z2, String str2, int i, long j, long j2, long j3, long j4, long j5, int i2, long j6, long j7, long j8, long j9, boolean z3, int i3, int i4, String str3, String str4, String str5, boolean z4, boolean z5, boolean z6, String str6, long j10, long j11, long j12, long j13, boolean z7, String str7, long j14, long j15, String[] strArr, @Nullable GraphQLQuery graphQLQuery) {
        this.A0J = str;
        this.A0L = z;
        this.A0M = z2;
        this.A0H = str2;
        this.A00 = i;
        this.A0A = j;
        this.A07 = j2;
        this.A06 = j3;
        this.A08 = j4;
        this.A09 = j5;
        this.A01 = i2;
        this.A02 = j6;
        this.A05 = j7;
        this.A04 = j8;
        this.A03 = j9;
        this.A0K = z3;
        this.A0G = str6;
        this.A0D = j10;
        this.A0C = j11;
        this.A0N = z7;
        this.A0I = str7;
        this.A0B = j14;
        this.A0E = j15;
        this.A0O = strArr;
        this.A0F = graphQLQuery;
    }
}
