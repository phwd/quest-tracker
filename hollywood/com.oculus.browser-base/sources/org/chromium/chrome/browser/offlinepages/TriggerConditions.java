package org.chromium.chrome.browser.offlinepages;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TriggerConditions {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f10721a;
    public final int b;
    public final boolean c;

    public TriggerConditions(boolean z, int i, boolean z2) {
        this.f10721a = z;
        this.b = i;
        this.c = z2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TriggerConditions)) {
            return false;
        }
        TriggerConditions triggerConditions = (TriggerConditions) obj;
        if (this.f10721a == triggerConditions.f10721a && this.b == triggerConditions.b && this.c == triggerConditions.c) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((403 + (this.f10721a ? 1 : 0)) * 31) + this.b) * 31) + (this.c ? 1 : 0);
    }
}
