package org.chromium.components.dom_distiller.content;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class DistillablePageUtils {

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface PageDistillableDelegate {
        void g(boolean z, boolean z2, boolean z3);
    }

    public static void callOnIsPageDistillableUpdate(PageDistillableDelegate pageDistillableDelegate, boolean z, boolean z2, boolean z3) {
        if (pageDistillableDelegate != null) {
            pageDistillableDelegate.g(z, z2, z3);
        }
    }
}
