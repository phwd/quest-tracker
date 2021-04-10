package X;

import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.appmanager.info.ApkUpdateInfoContract;

/* renamed from: X.07x  reason: invalid class name */
public class AnonymousClass07x {
    public static final AnonymousClass07x A01 = new AnonymousClass07x(null, 64);
    public static final AnonymousClass07x A02 = new AnonymousClass07x(null, 128);
    public static final AnonymousClass07x A03 = new AnonymousClass07x(null, 2);
    public static final AnonymousClass07x A04 = new AnonymousClass07x(null, 8);
    public static final AnonymousClass07x A05 = new AnonymousClass07x(null, 16);
    public static final AnonymousClass07x A06 = new AnonymousClass07x(null, 524288);
    public static final AnonymousClass07x A07 = new AnonymousClass07x(AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK, 16908348);
    public static final AnonymousClass07x A08 = new AnonymousClass07x(null, 16384);
    public static final AnonymousClass07x A09 = new AnonymousClass07x(null, 65536);
    public static final AnonymousClass07x A0A = new AnonymousClass07x(null, 1048576);
    public static final AnonymousClass07x A0B = new AnonymousClass07x(null, 262144);
    public static final AnonymousClass07x A0C = new AnonymousClass07x(null, 1);
    public static final AnonymousClass07x A0D;
    public static final AnonymousClass07x A0E = new AnonymousClass07x(null, 32);
    public static final AnonymousClass07x A0F;
    public static final AnonymousClass07x A0G = new AnonymousClass07x(null, 256);
    public static final AnonymousClass07x A0H = new AnonymousClass07x(null, 1024);
    @NonNull
    public static final AnonymousClass07x A0I;
    @NonNull
    public static final AnonymousClass07x A0J;
    @NonNull
    public static final AnonymousClass07x A0K;
    @NonNull
    public static final AnonymousClass07x A0L;
    public static final AnonymousClass07x A0M = new AnonymousClass07x(null, 32768);
    public static final AnonymousClass07x A0N = new AnonymousClass07x(null, 512);
    public static final AnonymousClass07x A0O = new AnonymousClass07x(null, 2048);
    public static final AnonymousClass07x A0P = new AnonymousClass07x(null, 8192);
    public static final AnonymousClass07x A0Q = new AnonymousClass07x(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN, 16908346);
    public static final AnonymousClass07x A0R = new AnonymousClass07x(null, 4096);
    public static final AnonymousClass07x A0S = new AnonymousClass07x(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT, 16908345);
    public static final AnonymousClass07x A0T = new AnonymousClass07x(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT, 16908347);
    public static final AnonymousClass07x A0U = new AnonymousClass07x(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION, 16908343);
    public static final AnonymousClass07x A0V = new AnonymousClass07x(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP, 16908344);
    public static final AnonymousClass07x A0W = new AnonymousClass07x(null, 4);
    public static final AnonymousClass07x A0X = new AnonymousClass07x(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS, 16908349);
    public static final AnonymousClass07x A0Y = new AnonymousClass07x(null, 131072);
    public static final AnonymousClass07x A0Z = new AnonymousClass07x(null, ApkUpdateInfoContract.UPDATE_TYPE_PATCH);
    public static final AnonymousClass07x A0a = new AnonymousClass07x(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN, 16908342);
    public static final AnonymousClass07x A0b;
    public final Object A00;

    static {
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction2;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction3;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction4;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction5;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction6 = null;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction7 = null;
        if (Build.VERSION.SDK_INT >= 29) {
            accessibilityAction = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP;
        } else {
            accessibilityAction = null;
        }
        A0L = new AnonymousClass07x(accessibilityAction, 16908358);
        if (Build.VERSION.SDK_INT >= 29) {
            accessibilityAction2 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN;
        } else {
            accessibilityAction2 = null;
        }
        A0I = new AnonymousClass07x(accessibilityAction2, 16908359);
        if (Build.VERSION.SDK_INT >= 29) {
            accessibilityAction3 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT;
        } else {
            accessibilityAction3 = null;
        }
        A0J = new AnonymousClass07x(accessibilityAction3, 16908360);
        if (Build.VERSION.SDK_INT >= 29) {
            accessibilityAction4 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT;
        } else {
            accessibilityAction4 = null;
        }
        A0K = new AnonymousClass07x(accessibilityAction4, 16908361);
        if (Build.VERSION.SDK_INT >= 26) {
            accessibilityAction5 = AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW;
        } else {
            accessibilityAction5 = null;
        }
        A0F = new AnonymousClass07x(accessibilityAction5, 16908354);
        if (Build.VERSION.SDK_INT >= 28) {
            accessibilityAction7 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP;
        }
        A0b = new AnonymousClass07x(accessibilityAction7, 16908356);
        if (Build.VERSION.SDK_INT >= 28) {
            accessibilityAction6 = AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP;
        }
        A0D = new AnonymousClass07x(accessibilityAction6, 16908357);
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Object;ILjava/lang/CharSequence;Landroidx/core/view/accessibility/AccessibilityViewCommand;Ljava/lang/Class<+Landroidx/core/view/accessibility/AccessibilityViewCommand$CommandArguments;>;)V */
    public AnonymousClass07x(Object obj, int i) {
        if (obj == null) {
            this.A00 = new AccessibilityNodeInfo.AccessibilityAction(i, null);
        } else {
            this.A00 = obj;
        }
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj != null && (obj instanceof AnonymousClass07x)) {
            Object obj2 = this.A00;
            Object obj3 = ((AnonymousClass07x) obj).A00;
            return obj2 == null ? obj3 == null : obj2.equals(obj3);
        }
    }

    public final int hashCode() {
        Object obj = this.A00;
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }
}
