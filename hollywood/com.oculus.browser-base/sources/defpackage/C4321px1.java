package defpackage;

import android.view.ViewStructure;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content_public.browser.AccessibilitySnapshotCallback;
import org.chromium.content_public.browser.AccessibilitySnapshotNode;

/* renamed from: px1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4321px1 extends AccessibilitySnapshotCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ViewStructure f11104a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ WebContentsAccessibilityImpl c;

    public C4321px1(WebContentsAccessibilityImpl webContentsAccessibilityImpl, ViewStructure viewStructure, boolean z) {
        this.c = webContentsAccessibilityImpl;
        this.f11104a = viewStructure;
        this.b = z;
    }

    @Override // org.chromium.content_public.browser.AccessibilitySnapshotCallback
    public void a(AccessibilitySnapshotNode accessibilitySnapshotNode) {
        this.f11104a.setClassName("");
        this.f11104a.setHint(this.c.f10921J);
        if (accessibilitySnapshotNode == null) {
            this.f11104a.asyncCommit();
        } else {
            this.c.e(this.f11104a, accessibilitySnapshotNode, this.b);
        }
    }
}
