package defpackage;

import android.view.accessibility.AccessibilityManager;

/* renamed from: E60  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class E60 {

    /* renamed from: a  reason: collision with root package name */
    public AccessibilityManager f7935a;
    public AccessibilityManager.AccessibilityStateChangeListener b;
    public boolean c;
    public final /* synthetic */ H60 d;

    public E60(H60 h60) {
        this.d = h60;
        AccessibilityManager accessibilityManager = (AccessibilityManager) h60.I.getSystemService("accessibility");
        this.f7935a = accessibilityManager;
        this.c = accessibilityManager.isEnabled();
        D60 d60 = new D60(this);
        this.b = d60;
        this.f7935a.addAccessibilityStateChangeListener(d60);
    }
}
