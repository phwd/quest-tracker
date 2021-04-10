package org.chromium.components.signin.identitymanager;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PrimaryAccountChangeEvent {

    /* renamed from: a  reason: collision with root package name */
    public final int f10896a;
    public final int b;

    public PrimaryAccountChangeEvent(int i, int i2) {
        this.b = i;
        this.f10896a = i2;
    }

    public int a(int i) {
        if (i == 1) {
            return this.f10896a;
        }
        return this.b;
    }
}
