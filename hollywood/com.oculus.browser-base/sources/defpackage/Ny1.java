package defpackage;

import android.view.accessibility.AccessibilityManager;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Ny1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Ny1 {

    /* renamed from: a  reason: collision with root package name */
    public AccessibilityManager.TouchExplorationStateChangeListener f8588a;
    public final /* synthetic */ WindowAndroid b;

    public Ny1(WindowAndroid windowAndroid) {
        this.b = windowAndroid;
        My1 my1 = new My1(this, windowAndroid);
        this.f8588a = my1;
        windowAndroid.N.addTouchExplorationStateChangeListener(my1);
    }
}
