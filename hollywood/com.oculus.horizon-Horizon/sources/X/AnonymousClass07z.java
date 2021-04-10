package X;

import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.FacebookSdk;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.squareup.okhttp.internal.framed.Http2;

/* renamed from: X.07z  reason: invalid class name */
public class AnonymousClass07z {
    public static final AnonymousClass07z A01 = new AnonymousClass07z(null, 64);
    public static final AnonymousClass07z A02 = new AnonymousClass07z(null, FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE);
    public static final AnonymousClass07z A03 = new AnonymousClass07z(null, 2);
    public static final AnonymousClass07z A04 = new AnonymousClass07z(null, 8);
    public static final AnonymousClass07z A05 = new AnonymousClass07z(null, 16);
    public static final AnonymousClass07z A06 = new AnonymousClass07z(null, ApkUpdateInfoContract.STATE_TYPE_CANCELABLE);
    public static final AnonymousClass07z A07;
    public static final AnonymousClass07z A08 = new AnonymousClass07z(null, Http2.INITIAL_MAX_FRAME_SIZE);
    public static final AnonymousClass07z A09 = new AnonymousClass07z(null, 65536);
    public static final AnonymousClass07z A0A = new AnonymousClass07z(null, ApkUpdateInfoContract.UPDATE_TYPE_FULL);
    public static final AnonymousClass07z A0B = new AnonymousClass07z(null, 262144);
    public static final AnonymousClass07z A0C = new AnonymousClass07z(null, 1);
    public static final AnonymousClass07z A0D;
    public static final AnonymousClass07z A0E = new AnonymousClass07z(null, 32);
    public static final AnonymousClass07z A0F;
    public static final AnonymousClass07z A0G = new AnonymousClass07z(null, 256);
    public static final AnonymousClass07z A0H = new AnonymousClass07z(null, 1024);
    @NonNull
    public static final AnonymousClass07z A0I;
    @NonNull
    public static final AnonymousClass07z A0J;
    @NonNull
    public static final AnonymousClass07z A0K;
    @NonNull
    public static final AnonymousClass07z A0L;
    public static final AnonymousClass07z A0M = new AnonymousClass07z(null, 32768);
    public static final AnonymousClass07z A0N = new AnonymousClass07z(null, 512);
    public static final AnonymousClass07z A0O = new AnonymousClass07z(null, 2048);
    public static final AnonymousClass07z A0P = new AnonymousClass07z(null, 8192);
    public static final AnonymousClass07z A0Q;
    public static final AnonymousClass07z A0R = new AnonymousClass07z(null, 4096);
    public static final AnonymousClass07z A0S;
    public static final AnonymousClass07z A0T;
    public static final AnonymousClass07z A0U;
    public static final AnonymousClass07z A0V;
    public static final AnonymousClass07z A0W = new AnonymousClass07z(null, 4);
    public static final AnonymousClass07z A0X;
    public static final AnonymousClass07z A0Y = new AnonymousClass07z(null, 131072);
    public static final AnonymousClass07z A0Z = new AnonymousClass07z(null, ApkUpdateInfoContract.UPDATE_TYPE_PATCH);
    public static final AnonymousClass07z A0a;
    public static final AnonymousClass07z A0b;
    public final Object A00;

    static {
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction2;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction3;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction4;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction5;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction6;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction7;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction8;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction9;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction10;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction11;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction12;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction13;
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction14 = null;
        if (Build.VERSION.SDK_INT >= 23) {
            accessibilityAction = AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN;
        } else {
            accessibilityAction = null;
        }
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction15 = null;
        A0a = new AnonymousClass07z(accessibilityAction, 16908342);
        if (Build.VERSION.SDK_INT >= 23) {
            accessibilityAction2 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION;
        } else {
            accessibilityAction2 = null;
        }
        A0U = new AnonymousClass07z(accessibilityAction2, 16908343);
        if (Build.VERSION.SDK_INT >= 23) {
            accessibilityAction3 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP;
        } else {
            accessibilityAction3 = null;
        }
        A0V = new AnonymousClass07z(accessibilityAction3, 16908344);
        if (Build.VERSION.SDK_INT >= 23) {
            accessibilityAction4 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT;
        } else {
            accessibilityAction4 = null;
        }
        A0S = new AnonymousClass07z(accessibilityAction4, 16908345);
        if (Build.VERSION.SDK_INT >= 23) {
            accessibilityAction5 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN;
        } else {
            accessibilityAction5 = null;
        }
        A0Q = new AnonymousClass07z(accessibilityAction5, 16908346);
        if (Build.VERSION.SDK_INT >= 23) {
            accessibilityAction6 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT;
        } else {
            accessibilityAction6 = null;
        }
        A0T = new AnonymousClass07z(accessibilityAction6, 16908347);
        if (Build.VERSION.SDK_INT >= 29) {
            accessibilityAction7 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP;
        } else {
            accessibilityAction7 = null;
        }
        A0L = new AnonymousClass07z(accessibilityAction7, 16908358);
        if (Build.VERSION.SDK_INT >= 29) {
            accessibilityAction8 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN;
        } else {
            accessibilityAction8 = null;
        }
        A0I = new AnonymousClass07z(accessibilityAction8, 16908359);
        if (Build.VERSION.SDK_INT >= 29) {
            accessibilityAction9 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT;
        } else {
            accessibilityAction9 = null;
        }
        A0J = new AnonymousClass07z(accessibilityAction9, 16908360);
        if (Build.VERSION.SDK_INT >= 29) {
            accessibilityAction10 = AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT;
        } else {
            accessibilityAction10 = null;
        }
        A0K = new AnonymousClass07z(accessibilityAction10, 16908361);
        if (Build.VERSION.SDK_INT >= 23) {
            accessibilityAction11 = AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK;
        } else {
            accessibilityAction11 = null;
        }
        A07 = new AnonymousClass07z(accessibilityAction11, 16908348);
        if (Build.VERSION.SDK_INT >= 24) {
            accessibilityAction12 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS;
        } else {
            accessibilityAction12 = null;
        }
        A0X = new AnonymousClass07z(accessibilityAction12, 16908349);
        if (Build.VERSION.SDK_INT >= 26) {
            accessibilityAction13 = AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW;
        } else {
            accessibilityAction13 = null;
        }
        A0F = new AnonymousClass07z(accessibilityAction13, 16908354);
        if (Build.VERSION.SDK_INT >= 28) {
            accessibilityAction15 = AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP;
        }
        A0b = new AnonymousClass07z(accessibilityAction15, 16908356);
        if (Build.VERSION.SDK_INT >= 28) {
            accessibilityAction14 = AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP;
        }
        A0D = new AnonymousClass07z(accessibilityAction14, 16908357);
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Object;ILjava/lang/CharSequence;Landroidx/core/view/accessibility/AccessibilityViewCommand;Ljava/lang/Class<+Landroidx/core/view/accessibility/AccessibilityViewCommand$CommandArguments;>;)V */
    public AnonymousClass07z(Object obj, int i) {
        if (obj == null) {
            this.A00 = new AccessibilityNodeInfo.AccessibilityAction(i, null);
        } else {
            this.A00 = obj;
        }
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj != null && (obj instanceof AnonymousClass07z)) {
            Object obj2 = this.A00;
            Object obj3 = ((AnonymousClass07z) obj).A00;
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
