package X;

import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0IR  reason: invalid class name */
public final class AnonymousClass0IR extends AbstractC05970mG {
    @Override // X.AbstractC05970mG
    public final String A00() {
        return "com.facebook.rti.mqtt.ACTION_WORK_SWITCH";
    }

    @Override // X.AbstractC05970mG
    public final String A01() {
        return "WorkConnectionConfigOverrides";
    }

    @Override // X.AbstractC05970mG
    public final Set<String> A02() {
        return Collections.unmodifiableSet(new HashSet(Arrays.asList("facebook.com", "workplace.com")));
    }

    @Override // X.AbstractC05970mG
    public final void A05(String str, String str2) {
        C06520nY A2L = this.A04.A00(AnonymousClass0WE.LAST_HOST).A2L();
        A2L.A00.putString("work_last_host", str);
        A2L.A00.putString("work_last_analytics_endpoint", str2);
        A2L.A00();
    }

    public AnonymousClass0IR(Context context, AnonymousClass0X3 r5, Integer num, @Nullable C06510nV r7) {
        super(context, r5, num, r7);
        AnonymousClass0WD A00 = this.A04.A00(AnonymousClass0WE.LAST_HOST);
        this.A06 = A00.A4R("work_last_host", null);
        this.A05 = A00.A4R("work_last_analytics_endpoint", null);
    }

    @Override // X.AbstractC05970mG
    public final void A03() {
    }
}
