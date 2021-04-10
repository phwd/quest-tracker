package defpackage;

import android.graphics.Insets;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build;
import android.util.Log;
import com.oculus.os.Version;
import java.lang.reflect.Field;

/* renamed from: XI  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class XI {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f9200a = {16842912};
    public static final int[] b = new int[0];
    public static final Rect c = new Rect();
    public static Class d;

    static {
        try {
            d = Class.forName("android.graphics.Insets");
        } catch (ClassNotFoundException unused) {
        }
    }

    public static boolean a(Drawable drawable) {
        if (drawable instanceof DrawableContainer) {
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (!(constantState instanceof DrawableContainer.DrawableContainerState)) {
                return true;
            }
            for (Drawable drawable2 : ((DrawableContainer.DrawableContainerState) constantState).getChildren()) {
                if (!a(drawable2)) {
                    return false;
                }
            }
            return true;
        } else if (drawable instanceof AbstractC4156oz1) {
            return a(((AbstractC4327pz1) ((AbstractC4156oz1) drawable)).F);
        } else {
            if (drawable instanceof YI) {
                return a(((YI) drawable).F);
            }
            if (drawable instanceof ScaleDrawable) {
                return a(((ScaleDrawable) drawable).getDrawable());
            }
            return true;
        }
    }

    public static Rect b(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 29) {
            Insets opticalInsets = drawable.getOpticalInsets();
            Rect rect = new Rect();
            rect.left = opticalInsets.left;
            rect.right = opticalInsets.right;
            rect.top = opticalInsets.top;
            rect.bottom = opticalInsets.bottom;
            return rect;
        }
        if (d != null) {
            try {
                Drawable d2 = VI.d(drawable);
                Object invoke = d2.getClass().getMethod("getOpticalInsets", new Class[0]).invoke(d2, new Object[0]);
                if (invoke != null) {
                    Rect rect2 = new Rect();
                    Field[] fields = d.getFields();
                    for (Field field : fields) {
                        String name = field.getName();
                        char c2 = 65535;
                        switch (name.hashCode()) {
                            case -1383228885:
                                if (name.equals("bottom")) {
                                    c2 = 3;
                                    break;
                                }
                                break;
                            case 115029:
                                if (name.equals("top")) {
                                    c2 = 1;
                                    break;
                                }
                                break;
                            case 3317767:
                                if (name.equals("left")) {
                                    c2 = 0;
                                    break;
                                }
                                break;
                            case 108511772:
                                if (name.equals("right")) {
                                    c2 = 2;
                                    break;
                                }
                                break;
                        }
                        if (c2 == 0) {
                            rect2.left = field.getInt(invoke);
                        } else if (c2 == 1) {
                            rect2.top = field.getInt(invoke);
                        } else if (c2 == 2) {
                            rect2.right = field.getInt(invoke);
                        } else if (c2 == 3) {
                            rect2.bottom = field.getInt(invoke);
                        }
                    }
                    return rect2;
                }
            } catch (Exception unused) {
                Log.e("DrawableUtils", "Couldn't obtain the optical insets. Ignoring.");
            }
        }
        return c;
    }

    public static PorterDuff.Mode c(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i) {
            case Version.VERSION_14 /*{ENCODED_INT: 14}*/:
                return PorterDuff.Mode.MULTIPLY;
            case Version.VERSION_15 /*{ENCODED_INT: 15}*/:
                return PorterDuff.Mode.SCREEN;
            case Version.VERSION_16 /*{ENCODED_INT: 16}*/:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }
}
