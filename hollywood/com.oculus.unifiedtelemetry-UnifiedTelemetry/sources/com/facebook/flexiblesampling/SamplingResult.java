package com.facebook.flexiblesampling;

import X.AnonymousClass06;
import X.O5;
import com.facebook.infer.annotation.NullsafeStrict;
import java.util.Random;

@NullsafeStrict
public final class SamplingResult {
    public static SamplingResult A02;
    public static final Random A03 = new Random();
    public int A00;
    public boolean A01;

    public final String toString() {
        String A012 = AnonymousClass06.A01("\nSamplingRate: ", this.A00);
        StringBuilder sb = new StringBuilder("\nHasUserConfig: ");
        sb.append(this.A01);
        String obj = sb.toString();
        StringBuilder sb2 = new StringBuilder("\nInUserConfig: ");
        sb2.append(false);
        String obj2 = sb2.toString();
        StringBuilder sb3 = new StringBuilder("\nInSessionlessConfig: ");
        sb3.append(false);
        return AnonymousClass06.A07("com.facebook.flexiblesampling.SamplingResult", A012, obj, obj2, sb3.toString());
    }

    public SamplingResult(O5 o5) {
        this.A00 = o5.A00;
        this.A01 = o5.A01;
    }
}
