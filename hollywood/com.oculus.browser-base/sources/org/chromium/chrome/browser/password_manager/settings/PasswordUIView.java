package org.chromium.chrome.browser.password_manager.settings;

import J.N;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class PasswordUIView {

    /* renamed from: a  reason: collision with root package name */
    public long f10742a = N.Mx3ZU1Lr(this);
    public final AbstractC4323py0 b;

    public PasswordUIView(AbstractC4323py0 py0) {
        this.b = py0;
    }

    public static SavedPasswordEntry createSavedPasswordEntry(String str, String str2, String str3) {
        return new SavedPasswordEntry(str, str2, str3);
    }

    public final void passwordExceptionListAvailable(int i) {
        this.b.l(i);
    }

    public final void passwordListAvailable(int i) {
        this.b.j(i);
    }
}
