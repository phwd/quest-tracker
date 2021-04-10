package com.facebook.graphservice.interfaces;

import com.facebook.proxygen.TraceFieldType;
import com.google.common.base.MoreObjects$ToStringHelper;

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
    public final GraphQLQuery A0F;
    public final String A0G;
    public final String A0H;
    public final String A0I;
    public final String A0J;
    public final boolean A0K;
    public final boolean A0L;
    public final boolean A0M;
    public final boolean A0N;
    public final String[] A0O;

    public final String toString() {
        MoreObjects$ToStringHelper moreObjects$ToStringHelper = new MoreObjects$ToStringHelper(getClass().getSimpleName());
        moreObjects$ToStringHelper.add("source", this.A0J);
        moreObjects$ToStringHelper.add("isFinal", this.A0L);
        moreObjects$ToStringHelper.add("isNetworkComplete", this.A0M);
        moreObjects$ToStringHelper.add("attempts", this.A00);
        moreObjects$ToStringHelper.add("fbRequestId", this.A0H);
        moreObjects$ToStringHelper.add("requestStart", this.A0A);
        moreObjects$ToStringHelper.add("networkStart", this.A07);
        moreObjects$ToStringHelper.add("networkEnd", this.A06);
        moreObjects$ToStringHelper.add("parseStart", this.A08);
        moreObjects$ToStringHelper.add("requestEnd", this.A09);
        moreObjects$ToStringHelper.add("parsedDataSize", this.A01);
        moreObjects$ToStringHelper.add("additiveParseTimeMs", this.A02);
        moreObjects$ToStringHelper.add("fetchCachedResponseStart", this.A05);
        moreObjects$ToStringHelper.add("fetchCachedResponseEnd", this.A04);
        moreObjects$ToStringHelper.add("cachedResponseAge", this.A03);
        moreObjects$ToStringHelper.add("freshResponse", this.A0K);
        moreObjects$ToStringHelper.add("consistencySource", this.A0G);
        moreObjects$ToStringHelper.add("serverStartTime", this.A0D);
        moreObjects$ToStringHelper.add("serverFlushTime", this.A0C);
        moreObjects$ToStringHelper.add("rejectedFromAdaptiveFetch", this.A0N);
        moreObjects$ToStringHelper.add("prefetchPredictionID", this.A0I);
        moreObjects$ToStringHelper.add(TraceFieldType.RTT, this.A0B);
        moreObjects$ToStringHelper.add("upstreamLatency", this.A0E);
        Object obj = this.A0O;
        if (obj == null) {
            obj = new int[0];
        }
        moreObjects$ToStringHelper.add("experimentalUriPrefetch", obj);
        return moreObjects$ToStringHelper.toString();
    }

    public Summary(String str, boolean z, boolean z2, String str2, int i, long j, long j2, long j3, long j4, long j5, int i2, long j6, long j7, long j8, long j9, boolean z3, int i3, int i4, String str3, String str4, String str5, boolean z4, boolean z5, boolean z6, String str6, long j10, long j11, long j12, long j13, boolean z7, String str7, long j14, long j15, String[] strArr) {
        this(str, z, z2, str2, i, j, j2, j3, j4, j5, i2, j6, j7, j8, j9, z3, i3, i4, str3, str4, str5, z4, z5, z6, str6, j10, j11, j12, j13, z7, str7, j14, j15, strArr, null);
    }

    public Summary(String str, boolean z, boolean z2, String str2, int i, long j, long j2, long j3, long j4, long j5, int i2, long j6, long j7, long j8, long j9, boolean z3, int i3, int i4, String str3, String str4, String str5, boolean z4, boolean z5, boolean z6, String str6, long j10, long j11, long j12, long j13, boolean z7, String str7, long j14, long j15, String[] strArr, GraphQLQuery graphQLQuery) {
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
