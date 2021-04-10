package com.oculus.logging.analytics2;

import X.AnonymousClass2z;
import X.C0219We;
import X.C0482rT;
import X.MG;
import X.YE;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.logging.OculusLoggingEvent;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class OculusDebugParamCollectionOnlyEvent implements OculusLoggingEvent {
    public final YE mExtras = MG.A00().A02();
    public final C0219We mObjectMapper;

    @Override // com.oculus.logging.OculusLoggingEvent
    public final OculusLoggingEvent A12(String str, String str2) {
        return this;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final boolean A3I() {
        return true;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final void A4z() {
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final void A50() {
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final void A53() {
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final void A55() {
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final OculusLoggingEvent A59(long j) {
        return this;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final void A5A() {
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final void A5C() {
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final OculusLoggingEvent A1A(String str) {
        if (str != null) {
            C0219We we = this.mObjectMapper;
            YE A2R = A2R();
            AnonymousClass2z A00 = JsonAndParamsCollectionHelper.A00(str, we);
            if (A00 != null) {
                C0482rT.A01(A00, A2R);
            }
        }
        return this;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final void A3Q() {
        this.mExtras.A02();
    }

    public OculusDebugParamCollectionOnlyEvent(C0219We we) {
        this.mObjectMapper = we;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final OculusLoggingEvent A13(String str, int i) {
        YE.A00(A2R(), str, Integer.valueOf(i));
        return this;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final OculusLoggingEvent A14(String str, long j) {
        YE.A00(A2R(), str, Long.valueOf(j));
        return this;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final OculusLoggingEvent A15(String str, @Nullable String str2) {
        YE.A00(A2R(), str, str2);
        return this;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final OculusLoggingEvent A16(String str, boolean z) {
        YE.A00(A2R(), str, Boolean.valueOf(z));
        return this;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final YE A2R() {
        return this.mExtras;
    }
}
