package com.facebook.flexiblesampling;

import X.AnonymousClass08;
import X.E9;
import java.util.Random;

public final class SamplingResult {
    public static SamplingResult A02;
    public static final Random A03 = new Random();
    public int A00;
    public boolean A01;

    public final String toString() {
        String A002 = AnonymousClass08.A00("\nSamplingRate: ", this.A00);
        StringBuilder sb = new StringBuilder("\nHasUserConfig: ");
        sb.append(this.A01);
        String obj = sb.toString();
        StringBuilder sb2 = new StringBuilder("\nInUserConfig: ");
        sb2.append(false);
        String obj2 = sb2.toString();
        StringBuilder sb3 = new StringBuilder("\nInSessionlessConfig: ");
        sb3.append(false);
        return AnonymousClass08.A07("com.facebook.flexiblesampling.SamplingResult", A002, obj, obj2, sb3.toString());
    }

    public SamplingResult(E9 e9) {
        this.A00 = e9.A00;
        this.A01 = e9.A01;
    }
}
