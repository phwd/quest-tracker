package X;

import android.os.SystemClock;
import com.facebook.assistant.oacr.OacrConstants;
import java.util.Collections;

/* renamed from: X.gb  reason: case insensitive filesystem */
public final class C0751gb implements AbstractC0105Aj {
    public final /* synthetic */ C00608k A00;

    public C0751gb(C00608k r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C00608k r9;
        C00618l r1;
        long j;
        C0817iM iMVar = (C0817iM) ak.A2L();
        if (!iMVar.A07) {
            String str = iMVar.A04;
            String str2 = iMVar.A03;
            if (!str2.isEmpty()) {
                this.A00.A06 = str2;
            }
            if (iMVar.A06) {
                C0139Dd.A0F("KeyboardAssistant", "mOacrTranscriptionSubscriber final transcription %s", str);
                r9 = this.A00;
                if (!r9.A05.isEmpty() || !str.isEmpty()) {
                    if (!str.isEmpty()) {
                        Integer num = iMVar.A01;
                        if (num != null) {
                            C00839n.A00.A00((long) iMVar.A00, (long) num.intValue());
                        }
                        r9.A09.A08.add(str);
                        r9.A03.A01(new C0806hu(str, iMVar.A02, Collections.unmodifiableList(iMVar.A05)));
                    }
                    if (r9.A02.A08) {
                        r9.A03.A01(new C0809hx(EnumC00528b.LISTENING));
                        r9.A01 = 0;
                        r9.A05 = OacrConstants.AUTO_SPEECH_DOMAIN;
                    }
                    r9.A03.A01(new C0774h0());
                    r9.A01 = 0;
                    r9.A05 = OacrConstants.AUTO_SPEECH_DOMAIN;
                }
                j = r9.A01;
                if (j == 0) {
                    j = SystemClock.elapsedRealtime();
                    r9.A01 = j;
                }
                r1 = r9.A02;
                if (r1.A07) {
                    return;
                }
            } else {
                C0139Dd.A0F("KeyboardAssistant", "mOacrTranscriptionSubscriber partial transcription %s", str);
                r9 = this.A00;
                String str3 = r9.A05;
                if (str.equals(str3)) {
                    if (str3.isEmpty() && r9.A01 == 0) {
                        r9.A01 = SystemClock.elapsedRealtime();
                    }
                    r1 = r9.A02;
                    if (!r1.A07) {
                        j = r9.A01;
                    } else {
                        return;
                    }
                } else {
                    r9.A01 = 0;
                    r9.A05 = str;
                    r9.A03.A01(new C0811hz(str, Collections.unmodifiableList(iMVar.A05)));
                    return;
                }
            }
            if (j != 0 && SystemClock.elapsedRealtime() > j + r1.A01) {
                C00799i.A00.logLocalEvent("KeyboardAssistant", "Timeout, closing connection.");
                r9.A03.A01(new C0774h0());
                r9.A01 = 0;
                r9.A05 = OacrConstants.AUTO_SPEECH_DOMAIN;
            }
        }
    }
}
