package org.chromium.components.feature_engagement.internal;

import J.N;
import org.chromium.base.Callback;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TrackerImpl implements Tm1 {

    /* renamed from: a  reason: collision with root package name */
    public long f10844a;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class DisplayLockHandleAndroid {
        public DisplayLockHandleAndroid(long j) {
        }

        public static DisplayLockHandleAndroid create(long j) {
            return new DisplayLockHandleAndroid(j);
        }

        public final void clearNativePtr() {
        }
    }

    public TrackerImpl(long j) {
        this.f10844a = j;
    }

    public static TrackerImpl create(long j) {
        return new TrackerImpl(j);
    }

    @Override // defpackage.Tm1
    public void a(Callback callback) {
        N.MLFWzkLW(this.f10844a, this, callback);
    }

    public final void clearNativePtr() {
        this.f10844a = 0;
    }

    @Override // defpackage.Tm1
    public void dismissed(String str) {
        N.M21A_pP$(this.f10844a, this, str);
    }

    public final long getNativePtr() {
        return this.f10844a;
    }

    @Override // defpackage.Tm1
    public int getTriggerState(String str) {
        return N.MtnFGh0Q(this.f10844a, this, str);
    }

    @Override // defpackage.Tm1
    public boolean hasEverTriggered(String str, boolean z) {
        return N.MRyrQ9qM(this.f10844a, this, str, z);
    }

    @Override // defpackage.Tm1
    public boolean isInitialized() {
        return N.MzNVGr12(this.f10844a, this);
    }

    @Override // defpackage.Tm1
    public void notifyEvent(String str) {
        N.M0aLPz1m(this.f10844a, this, str);
    }

    @Override // defpackage.Tm1
    public boolean shouldTriggerHelpUI(String str) {
        if (AbstractC1575Zv.e().g("enable-screenshot-ui-mode")) {
            return false;
        }
        return N.Mr$ygyBZ(this.f10844a, this, str);
    }

    @Override // defpackage.Tm1
    public boolean wouldTriggerHelpUI(String str) {
        return N.ME$bTNVi(this.f10844a, this, str);
    }
}
