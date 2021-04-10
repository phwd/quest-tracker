package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import com.oculus.browser.R;

/* renamed from: m8  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3669m8 {

    /* renamed from: a  reason: collision with root package name */
    public final int[] f10399a = {R.drawable.f28080_resource_name_obfuscated_RES_2131230848, R.drawable.f28060_resource_name_obfuscated_RES_2131230846, R.drawable.f27250_resource_name_obfuscated_RES_2131230765};
    public final int[] b = {R.drawable.f27490_resource_name_obfuscated_RES_2131230789, R.drawable.f27900_resource_name_obfuscated_RES_2131230830, R.drawable.f27560_resource_name_obfuscated_RES_2131230796, R.drawable.f27510_resource_name_obfuscated_RES_2131230791, R.drawable.f27520_resource_name_obfuscated_RES_2131230792, R.drawable.f27550_resource_name_obfuscated_RES_2131230795, R.drawable.f27540_resource_name_obfuscated_RES_2131230794};
    public final int[] c = {R.drawable.f28050_resource_name_obfuscated_RES_2131230845, R.drawable.f28070_resource_name_obfuscated_RES_2131230847, R.drawable.f27420_resource_name_obfuscated_RES_2131230782, R.drawable.f27980_resource_name_obfuscated_RES_2131230838, R.drawable.f27990_resource_name_obfuscated_RES_2131230839, R.drawable.f28010_resource_name_obfuscated_RES_2131230841, R.drawable.f28030_resource_name_obfuscated_RES_2131230843, R.drawable.f28000_resource_name_obfuscated_RES_2131230840, R.drawable.f28020_resource_name_obfuscated_RES_2131230842, R.drawable.f28040_resource_name_obfuscated_RES_2131230844};
    public final int[] d = {R.drawable.f27800_resource_name_obfuscated_RES_2131230820, R.drawable.f27400_resource_name_obfuscated_RES_2131230780, R.drawable.f27790_resource_name_obfuscated_RES_2131230819};
    public final int[] e = {R.drawable.f27960_resource_name_obfuscated_RES_2131230836, R.drawable.f28090_resource_name_obfuscated_RES_2131230849};
    public final int[] f = {R.drawable.f27280_resource_name_obfuscated_RES_2131230768, R.drawable.f27340_resource_name_obfuscated_RES_2131230774, R.drawable.f27290_resource_name_obfuscated_RES_2131230769, R.drawable.f27350_resource_name_obfuscated_RES_2131230775};

    public final boolean a(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public final ColorStateList b(Context context, int i) {
        int c2 = AbstractC1361Wg1.c(context, R.attr.f3070_resource_name_obfuscated_RES_2130968753);
        int b2 = AbstractC1361Wg1.b(context, R.attr.f3050_resource_name_obfuscated_RES_2130968751);
        return new ColorStateList(new int[][]{AbstractC1361Wg1.b, AbstractC1361Wg1.d, AbstractC1361Wg1.c, AbstractC1361Wg1.f}, new int[]{b2, AbstractC1331Vv.e(c2, i), AbstractC1331Vv.e(c2, i), i});
    }

    public ColorStateList c(Context context, int i) {
        if (i == R.drawable.f27450_resource_name_obfuscated_RES_2131230785) {
            ThreadLocal threadLocal = AbstractC5544x8.f11592a;
            return context.getColorStateList(R.color.f9790_resource_name_obfuscated_RES_2131099669);
        } else if (i == R.drawable.f27950_resource_name_obfuscated_RES_2131230835) {
            ThreadLocal threadLocal2 = AbstractC5544x8.f11592a;
            return context.getColorStateList(R.color.f9820_resource_name_obfuscated_RES_2131099672);
        } else if (i == R.drawable.f27940_resource_name_obfuscated_RES_2131230834) {
            int[][] iArr = new int[3][];
            int[] iArr2 = new int[3];
            ColorStateList d2 = AbstractC1361Wg1.d(context, R.attr.f3260_resource_name_obfuscated_RES_2130968772);
            if (d2 == null || !d2.isStateful()) {
                iArr[0] = AbstractC1361Wg1.b;
                iArr2[0] = AbstractC1361Wg1.b(context, R.attr.f3260_resource_name_obfuscated_RES_2130968772);
                iArr[1] = AbstractC1361Wg1.e;
                iArr2[1] = AbstractC1361Wg1.c(context, R.attr.f3060_resource_name_obfuscated_RES_2130968752);
                iArr[2] = AbstractC1361Wg1.f;
                iArr2[2] = AbstractC1361Wg1.c(context, R.attr.f3260_resource_name_obfuscated_RES_2130968772);
            } else {
                iArr[0] = AbstractC1361Wg1.b;
                iArr2[0] = d2.getColorForState(iArr[0], 0);
                iArr[1] = AbstractC1361Wg1.e;
                iArr2[1] = AbstractC1361Wg1.c(context, R.attr.f3060_resource_name_obfuscated_RES_2130968752);
                iArr[2] = AbstractC1361Wg1.f;
                iArr2[2] = d2.getDefaultColor();
            }
            return new ColorStateList(iArr, iArr2);
        } else if (i == R.drawable.f27330_resource_name_obfuscated_RES_2131230773) {
            return b(context, AbstractC1361Wg1.c(context, R.attr.f3050_resource_name_obfuscated_RES_2130968751));
        } else {
            if (i == R.drawable.f27270_resource_name_obfuscated_RES_2131230767) {
                return b(context, 0);
            }
            if (i == R.drawable.f27320_resource_name_obfuscated_RES_2131230772) {
                return b(context, AbstractC1361Wg1.c(context, R.attr.f3020_resource_name_obfuscated_RES_2130968748));
            }
            if (i == R.drawable.f27920_resource_name_obfuscated_RES_2131230832 || i == R.drawable.f27930_resource_name_obfuscated_RES_2131230833) {
                ThreadLocal threadLocal3 = AbstractC5544x8.f11592a;
                return context.getColorStateList(R.color.f9810_resource_name_obfuscated_RES_2131099671);
            } else if (a(this.b, i)) {
                return AbstractC1361Wg1.d(context, R.attr.f3080_resource_name_obfuscated_RES_2130968754);
            } else {
                if (a(this.e, i)) {
                    ThreadLocal threadLocal4 = AbstractC5544x8.f11592a;
                    return context.getColorStateList(R.color.f9780_resource_name_obfuscated_RES_2131099668);
                } else if (a(this.f, i)) {
                    ThreadLocal threadLocal5 = AbstractC5544x8.f11592a;
                    return context.getColorStateList(R.color.f9770_resource_name_obfuscated_RES_2131099667);
                } else if (i != R.drawable.f27890_resource_name_obfuscated_RES_2131230829) {
                    return null;
                } else {
                    ThreadLocal threadLocal6 = AbstractC5544x8.f11592a;
                    return context.getColorStateList(R.color.f9800_resource_name_obfuscated_RES_2131099670);
                }
            }
        }
    }

    public final void d(Drawable drawable, int i, PorterDuff.Mode mode) {
        if (XI.a(drawable)) {
            drawable = drawable.mutate();
        }
        if (mode == null) {
            mode = C3840n8.f10473a;
        }
        drawable.setColorFilter(C3840n8.c(i, mode));
    }
}
