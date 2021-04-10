package org.chromium.third_party.android.datausagechart;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.CharArrayWriter;
import java.io.PrintWriter;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NetworkStatsHistory implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0828Nn0();
    public long F;
    public int G;
    public long[] H;
    public long[] I;

    /* renamed from: J  reason: collision with root package name */
    public long[] f11013J;
    public long[] K;
    public long[] L;
    public long[] M;
    public long[] N;
    public long O;

    public NetworkStatsHistory(long j, int i, int i2) {
        this.F = j;
        this.H = new long[i];
        if ((i2 & 1) != 0) {
            this.I = new long[i];
        }
        if ((i2 & 2) != 0) {
            this.f11013J = new long[i];
        }
        if ((i2 & 4) != 0) {
            this.K = new long[i];
        }
        if ((i2 & 8) != 0) {
            this.L = new long[i];
        }
        if ((i2 & 16) != 0) {
            this.M = new long[i];
        }
        if ((i2 & 32) != 0) {
            this.N = new long[i];
        }
        this.G = 0;
        this.O = 0;
    }

    public static void b(long[] jArr, int i, long j) {
        if (jArr != null) {
            jArr[i] = jArr[i] + j;
        }
    }

    public long c() {
        int i = this.G;
        if (i > 0) {
            return this.H[i - 1] + this.F;
        }
        return Long.MIN_VALUE;
    }

    public int describeContents() {
        return 0;
    }

    public int h(long j) {
        int binarySearch = Arrays.binarySearch(this.H, 0, this.G, j);
        return Math.max(0, Math.min(this.G - 1, binarySearch < 0 ? ~binarySearch : binarySearch + 1));
    }

    public String toString() {
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        PrintWriter printWriter = new PrintWriter(charArrayWriter);
        printWriter.print("NetworkStatsHistory: bucketDuration=");
        printWriter.println(this.F);
        int max = Math.max(0, this.G - 32);
        if (max > 0) {
            printWriter.print("(omitting ");
            printWriter.print(max);
            printWriter.println(" buckets)");
        }
        while (max < this.G) {
            printWriter.print("bucketStart=");
            printWriter.print(this.H[max]);
            if (this.I != null) {
                printWriter.print(" activeTime=");
                printWriter.print(this.I[max]);
            }
            if (this.f11013J != null) {
                printWriter.print(" rxBytes=");
                printWriter.print(this.f11013J[max]);
            }
            if (this.K != null) {
                printWriter.print(" rxPackets=");
                printWriter.print(this.K[max]);
            }
            if (this.L != null) {
                printWriter.print(" txBytes=");
                printWriter.print(this.L[max]);
            }
            if (this.M != null) {
                printWriter.print(" txPackets=");
                printWriter.print(this.M[max]);
            }
            if (this.N != null) {
                printWriter.print(" operations=");
                printWriter.print(this.N[max]);
            }
            printWriter.println();
            max++;
        }
        return charArrayWriter.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.F);
        AbstractC0950Pn0.b(parcel, this.H, this.G);
        AbstractC0950Pn0.b(parcel, this.I, this.G);
        AbstractC0950Pn0.b(parcel, this.f11013J, this.G);
        AbstractC0950Pn0.b(parcel, this.K, this.G);
        AbstractC0950Pn0.b(parcel, this.L, this.G);
        AbstractC0950Pn0.b(parcel, this.M, this.G);
        AbstractC0950Pn0.b(parcel, this.N, this.G);
        parcel.writeLong(this.O);
    }

    public NetworkStatsHistory(Parcel parcel) {
        this.F = parcel.readLong();
        this.H = AbstractC0950Pn0.a(parcel);
        this.I = AbstractC0950Pn0.a(parcel);
        this.f11013J = AbstractC0950Pn0.a(parcel);
        this.K = AbstractC0950Pn0.a(parcel);
        this.L = AbstractC0950Pn0.a(parcel);
        this.M = AbstractC0950Pn0.a(parcel);
        this.N = AbstractC0950Pn0.a(parcel);
        this.G = this.H.length;
        this.O = parcel.readLong();
    }
}
