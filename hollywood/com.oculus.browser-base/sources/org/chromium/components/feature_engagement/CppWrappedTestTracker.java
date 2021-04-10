package org.chromium.components.feature_engagement;

import android.text.TextUtils;
import org.chromium.base.Callback;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CppWrappedTestTracker implements Tm1 {
    @Override // defpackage.Tm1
    public void a(Callback callback) {
        callback.onResult(Boolean.TRUE);
    }

    public final boolean b(String str) {
        return TextUtils.equals(null, str);
    }

    @Override // defpackage.Tm1
    public void dismissed(String str) {
        b(str);
    }

    @Override // defpackage.Tm1
    public int getTriggerState(String str) {
        return b(str) ? 1 : 0;
    }

    @Override // defpackage.Tm1
    public boolean hasEverTriggered(String str, boolean z) {
        return true;
    }

    @Override // defpackage.Tm1
    public boolean isInitialized() {
        return true;
    }

    @Override // defpackage.Tm1
    public void notifyEvent(String str) {
    }

    @Override // defpackage.Tm1
    public boolean shouldTriggerHelpUI(String str) {
        return b(str);
    }

    @Override // defpackage.Tm1
    public boolean wouldTriggerHelpUI(String str) {
        return b(str);
    }
}
