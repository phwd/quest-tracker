package X;

import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.horizon.abuse_prevention.VideoUploaderCleanerServiceManager;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0IQ  reason: invalid class name */
public final class AnonymousClass0IQ extends AbstractC05970mG {
    public final AnonymousClass0nN A00;

    @Override // X.AbstractC05970mG
    public final String A00() {
        return "com.facebook.rti.mqtt.ACTION_ZR_SWITCH";
    }

    @Override // X.AbstractC05970mG
    public final String A01() {
        return "ZeroRatingConnectionConfigOverrides";
    }

    @Override // X.AbstractC05970mG
    public final void A05(String str, String str2) {
        C06520nY A2L = this.A04.A00(AnonymousClass0WE.LAST_HOST).A2L();
        A2L.A00.putString("zero_rating_last_host", str);
        A2L.A00.putLong("zero_rating_last_host_timestamp", System.currentTimeMillis());
        A2L.A00();
    }

    public AnonymousClass0IQ(Context context, AnonymousClass0X3 r8, Integer num, @Nullable C06510nV r10, AnonymousClass0nN r11) {
        super(context, r8, num, r10);
        this.A00 = r11;
        AnonymousClass0WD A002 = this.A04.A00(AnonymousClass0WE.LAST_HOST);
        if (System.currentTimeMillis() - A002.A3l("zero_rating_last_host_timestamp", 0) < VideoUploaderCleanerServiceManager.STALE_THRESHOLD_MILLIS) {
            this.A06 = A002.A4R("zero_rating_last_host", null);
        }
    }
}
