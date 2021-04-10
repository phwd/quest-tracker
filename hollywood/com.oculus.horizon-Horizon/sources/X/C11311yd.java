package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1yd  reason: invalid class name and case insensitive filesystem */
public final class C11311yd implements AnonymousClass1yR {
    public C11451yu A00;
    public C11351yh A01;

    public final boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            C11311yd r5 = (C11311yd) obj;
            if (!this.A00.equals(r5.A00) || !this.A01.equals(r5.A01)) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.A00, this.A01});
    }

    public final Map<String, String> A00() {
        C11351yh r2 = this.A01;
        HashMap hashMap = new HashMap();
        hashMap.put("AudioEncoderConfig.bitRate", String.valueOf(64000));
        hashMap.put("AudioEncoderConfig.sampleRate", String.valueOf(r2.A05));
        hashMap.put("AudioEncoderConfig.channelCount", String.valueOf(1));
        hashMap.put("AudioEncoderConfig.bufferSize", String.valueOf(r2.A00));
        hashMap.put("AudioEncoderConfig.pcmEncoding", String.valueOf(r2.A04));
        hashMap.put("AudioEncoderConfig.dequeueInputBufferTimeoutMs", String.valueOf(r2.A01));
        hashMap.put("AudioEncoderConfig.endOfStreamDequeueOutputBufferTimeoutUs", String.valueOf(r2.A02));
        hashMap.put("AudioEncoderConfig.maxTryAgainLaterRetries", String.valueOf(r2.A03));
        C11451yu r4 = this.A00;
        HashMap hashMap2 = new HashMap();
        hashMap2.put("AudioRecorderConfig.audioBufferMultiplier", String.valueOf(r4.A00));
        hashMap2.put("AudioRecorderConfig.channelType", String.valueOf(16));
        hashMap2.put("AudioRecorderConfig.encoding", String.valueOf(2));
        hashMap2.put("AudioRecorderConfig.sampleRateHz", String.valueOf(r4.A01));
        hashMap2.put("AudioRecorderConfig.source", String.valueOf(r4.A02));
        HashMap hashMap3 = new HashMap(hashMap.size() + hashMap2.size());
        hashMap3.putAll(hashMap2);
        hashMap3.putAll(hashMap);
        return hashMap3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0026, code lost:
        if (r6.A90() == false) goto L_0x0028;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C11311yd(@javax.annotation.Nullable X.C11011xI r5, @javax.annotation.Nullable X.AnonymousClass1z3 r6) {
        /*
            r4 = this;
            r4.<init>()
            X.1zB r1 = new X.1zB
            r1.<init>()
            java.lang.Integer r0 = r5.A00
            if (r0 == 0) goto L_0x0012
            int r0 = r0.intValue()
            r1.A01 = r0
        L_0x0012:
            X.1yu r3 = new X.1yu
            r3.<init>(r1)
            r4.A00 = r3
            X.1z4 r2 = new X.1z4
            r2.<init>()
            if (r6 == 0) goto L_0x0028
            boolean r1 = r6.A90()
            r0 = 4096(0x1000, float:5.74E-42)
            if (r1 != 0) goto L_0x002b
        L_0x0028:
            r0 = 409600(0x64000, float:5.73972E-40)
        L_0x002b:
            r2.A00 = r0
            int r0 = r3.A01
            r2.A05 = r0
            if (r6 == 0) goto L_0x003f
            int r0 = r6.A30()
            r2.A02 = r0
            int r0 = r6.A31()
            r2.A03 = r0
        L_0x003f:
            r0 = 100
            r2.A01 = r0
            X.1yh r0 = new X.1yh
            r0.<init>(r2)
            r4.A01 = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C11311yd.<init>(X.1xI, X.1z3):void");
    }

    @Override // X.AnonymousClass1yR
    public final AnonymousClass1yA A4Z() {
        return AnonymousClass1yA.AUDIO;
    }
}
