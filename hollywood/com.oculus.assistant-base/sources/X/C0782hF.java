package X;

import com.facebook.quicklog.QuickPerformanceLogger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* renamed from: X.hF  reason: case insensitive filesystem */
public final class C0782hF implements AbstractC00869q {
    public C00849o A00 = null;
    public boolean mIsSensitiveTranscription = false;

    @Override // X.AbstractC00869q
    public final void A3u() {
    }

    @Override // X.AbstractC00869q
    public final void A4F() {
        this.mIsSensitiveTranscription = false;
    }

    public final void A00(long j, long j2) {
        long j3;
        C00849o r0 = this.A00;
        if (r0 != null) {
            long j4 = r0.A01;
            int i = r0.A00;
            if (i == 0) {
                C0139Dd.A0A("ASRInteractionLatencyLoggerAdapter", "Failed to calculate the audioDuration. Received a sample rate = 0.");
                j3 = 0;
            } else {
                j3 = ((j4 * 1000) / 2) / ((long) i);
            }
            C00849o r4 = this.A00;
            long j5 = (r4.A02 - j3) + (j - j2);
            hG hGVar = C00879r.A00;
            EnumC00909u r7 = EnumC00909u.END_OF_SPEECH;
            String valueOf = String.valueOf(j3);
            String valueOf2 = String.valueOf(r4.A00);
            HashMap hashMap = new HashMap(2);
            hashMap.put("clientComputedAudioDurationMs", valueOf);
            hashMap.put("sampleRate", valueOf2);
            Map unmodifiableMap = Collections.unmodifiableMap(hashMap);
            QuickPerformanceLogger quickPerformanceLogger = hGVar.A00;
            int i2 = hGVar.A02;
            quickPerformanceLogger.markerPoint(i2, r7.getPointName(), AbstractC00889s.A00(unmodifiableMap), j5, TimeUnit.MILLISECONDS);
            EnumC00909u r72 = EnumC00909u.FINAL_TRANSCRIPTION;
            String valueOf3 = String.valueOf(j);
            String valueOf4 = String.valueOf(j2);
            HashMap hashMap2 = new HashMap(2);
            hashMap2.put("serverAudioDurationMs", valueOf3);
            hashMap2.put("serverEndpointLagMs", valueOf4);
            hGVar.A00.markerPoint(i2, r72.getPointName(), AbstractC00889s.A00(Collections.unmodifiableMap(hashMap2)));
            this.A00 = null;
        }
    }
}
