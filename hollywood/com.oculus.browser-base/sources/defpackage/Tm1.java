package defpackage;

import org.chromium.base.Callback;

/* renamed from: Tm1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public interface Tm1 {
    void a(Callback callback);

    void dismissed(String str);

    int getTriggerState(String str);

    boolean hasEverTriggered(String str, boolean z);

    boolean isInitialized();

    void notifyEvent(String str);

    boolean shouldTriggerHelpUI(String str);

    boolean wouldTriggerHelpUI(String str);
}
