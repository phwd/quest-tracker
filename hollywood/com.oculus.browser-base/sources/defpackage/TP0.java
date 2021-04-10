package defpackage;

import android.view.View;

/* renamed from: TP0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class TP0 {
    public static int a(VK0 vk0, AbstractC4308pt0 pt0, View view, View view2, IK0 ik0, boolean z) {
        if (ik0.z() == 0 || vk0.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return Math.abs(ik0.R(view) - ik0.R(view2)) + 1;
        }
        return Math.min(pt0.l(), pt0.b(view2) - pt0.e(view));
    }

    public static int b(VK0 vk0, AbstractC4308pt0 pt0, View view, View view2, IK0 ik0, boolean z, boolean z2) {
        int i;
        if (ik0.z() == 0 || vk0.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        int min = Math.min(ik0.R(view), ik0.R(view2));
        int max = Math.max(ik0.R(view), ik0.R(view2));
        if (z2) {
            i = Math.max(0, (vk0.b() - max) - 1);
        } else {
            i = Math.max(0, min);
        }
        if (!z) {
            return i;
        }
        return Math.round((((float) i) * (((float) Math.abs(pt0.b(view2) - pt0.e(view))) / ((float) (Math.abs(ik0.R(view) - ik0.R(view2)) + 1)))) + ((float) (pt0.k() - pt0.e(view))));
    }

    public static int c(VK0 vk0, AbstractC4308pt0 pt0, View view, View view2, IK0 ik0, boolean z) {
        if (ik0.z() == 0 || vk0.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return vk0.b();
        }
        return (int) ((((float) (pt0.b(view2) - pt0.e(view))) / ((float) (Math.abs(ik0.R(view) - ik0.R(view2)) + 1))) * ((float) vk0.b()));
    }
}
