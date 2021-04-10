package defpackage;

import android.content.ComponentName;

/* renamed from: Fg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0324Fg0 {

    /* renamed from: a  reason: collision with root package name */
    public final ComponentName f8030a;

    public C0324Fg0(ComponentName componentName) {
        if (componentName != null) {
            this.f8030a = componentName;
            return;
        }
        throw new IllegalArgumentException("componentName must not be null");
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("ProviderMetadata{ componentName=");
        i.append(this.f8030a.flattenToShortString());
        i.append(" }");
        return i.toString();
    }
}
