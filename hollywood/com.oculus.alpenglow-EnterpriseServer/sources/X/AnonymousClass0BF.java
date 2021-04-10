package X;

import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.squareup.okhttp.internal.framed.Http2;

/* renamed from: X.0BF  reason: invalid class name */
public class AnonymousClass0BF {
    public static final AnonymousClass0BF A01 = new AnonymousClass0BF(null, 64);
    public static final AnonymousClass0BF A02 = new AnonymousClass0BF(null, 128);
    public static final AnonymousClass0BF A03 = new AnonymousClass0BF(null, 2);
    public static final AnonymousClass0BF A04 = new AnonymousClass0BF(null, 8);
    public static final AnonymousClass0BF A05 = new AnonymousClass0BF(null, 16);
    public static final AnonymousClass0BF A06 = new AnonymousClass0BF(null, ApkUpdateInfoContract.STATE_TYPE_CANCELABLE);
    public static final AnonymousClass0BF A07 = new AnonymousClass0BF(AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK, 16908348);
    public static final AnonymousClass0BF A08 = new AnonymousClass0BF(null, Http2.INITIAL_MAX_FRAME_SIZE);
    public static final AnonymousClass0BF A09 = new AnonymousClass0BF(null, 65536);
    public static final AnonymousClass0BF A0A = new AnonymousClass0BF(null, ApkUpdateInfoContract.UPDATE_TYPE_FULL);
    public static final AnonymousClass0BF A0B = new AnonymousClass0BF(null, 262144);
    public static final AnonymousClass0BF A0C = new AnonymousClass0BF(null, 1);
    public static final AnonymousClass0BF A0D;
    public static final AnonymousClass0BF A0E = new AnonymousClass0BF(null, 32);
    public static final AnonymousClass0BF A0F;
    public static final AnonymousClass0BF A0G = new AnonymousClass0BF(null, 256);
    public static final AnonymousClass0BF A0H = new AnonymousClass0BF(null, 1024);
    @NonNull
    public static final AnonymousClass0BF A0I;
    @NonNull
    public static final AnonymousClass0BF A0J;
    @NonNull
    public static final AnonymousClass0BF A0K;
    @NonNull
    public static final AnonymousClass0BF A0L;
    public static final AnonymousClass0BF A0M = new AnonymousClass0BF(null, 32768);
    public static final AnonymousClass0BF A0N = new AnonymousClass0BF(null, 512);
    public static final AnonymousClass0BF A0O = new AnonymousClass0BF(null, 2048);
    public static final AnonymousClass0BF A0P = new AnonymousClass0BF(null, 8192);
    public static final AnonymousClass0BF A0Q = new AnonymousClass0BF(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN, 16908346);
    public static final AnonymousClass0BF A0R = new AnonymousClass0BF(null, 4096);
    public static final AnonymousClass0BF A0S = new AnonymousClass0BF(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT, 16908345);
    public static final AnonymousClass0BF A0T = new AnonymousClass0BF(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT, 16908347);
    public static final AnonymousClass0BF A0U = new AnonymousClass0BF(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION, 16908343);
    public static final AnonymousClass0BF A0V = new AnonymousClass0BF(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP, 16908344);
    public static final AnonymousClass0BF A0W = new AnonymousClass0BF(null, 4);
    public static final AnonymousClass0BF A0X = new AnonymousClass0BF(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS, 16908349);
    public static final AnonymousClass0BF A0Y = new AnonymousClass0BF(null, 131072);
    public static final AnonymousClass0BF A0Z = new AnonymousClass0BF(null, ApkUpdateInfoContract.UPDATE_TYPE_PATCH);
    public static final AnonymousClass0BF A0a = new AnonymousClass0BF(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN, 16908342);
    public static final AnonymousClass0BF A0b;
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
        A0L = new AnonymousClass0BF(accessibilityAction, 16908358);
        if (Build.VERSION.SDK_INT >= 29) {
            accessibilityAction2 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN;
        } else {
            accessibilityAction2 = null;
        }
        A0I = new AnonymousClass0BF(accessibilityAction2, 16908359);
        if (Build.VERSION.SDK_INT >= 29) {
            accessibilityAction3 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT;
        } else {
            accessibilityAction3 = null;
        }
        A0J = new AnonymousClass0BF(accessibilityAction3, 16908360);
        if (Build.VERSION.SDK_INT >= 29) {
            accessibilityAction4 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT;
        } else {
            accessibilityAction4 = null;
        }
        A0K = new AnonymousClass0BF(accessibilityAction4, 16908361);
        if (Build.VERSION.SDK_INT >= 26) {
            accessibilityAction5 = AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW;
        } else {
            accessibilityAction5 = null;
        }
        A0F = new AnonymousClass0BF(accessibilityAction5, 16908354);
        if (Build.VERSION.SDK_INT >= 28) {
            accessibilityAction7 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP;
        }
        A0b = new AnonymousClass0BF(accessibilityAction7, 16908356);
        if (Build.VERSION.SDK_INT >= 28) {
            accessibilityAction6 = AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP;
        }
        A0D = new AnonymousClass0BF(accessibilityAction6, 16908357);
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Object;ILjava/lang/CharSequence;Landroidx/core/view/accessibility/AccessibilityViewCommand;Ljava/lang/Class<+Landroidx/core/view/accessibility/AccessibilityViewCommand$CommandArguments;>;)V */
    public AnonymousClass0BF(Object obj, int i) {
        if (obj == null) {
            this.A00 = new AccessibilityNodeInfo.AccessibilityAction(i, null);
        } else {
            this.A00 = obj;
        }
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj != null && (obj instanceof AnonymousClass0BF)) {
            Object obj2 = this.A00;
            Object obj3 = ((AnonymousClass0BF) obj).A00;
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
