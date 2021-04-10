package defpackage;

import J.N;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.Map;
import org.chromium.base.ThreadUtils;

/* renamed from: Sy  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1154Sy {

    /* renamed from: a  reason: collision with root package name */
    public static Map f8928a;

    public static int a(int i) {
        if (i == 1) {
            return R.string.f64930_resource_name_obfuscated_RES_2131953810;
        }
        if (i == 2) {
            return R.string.f64990_resource_name_obfuscated_RES_2131953816;
        }
        if (i != 3) {
            return 0;
        }
        return R.string.f64980_resource_name_obfuscated_RES_2131953815;
    }

    public static int b(int i, boolean z) {
        Integer num;
        if (z) {
            num = e(i).c;
        } else {
            num = e(i).d;
        }
        return a(num.intValue());
    }

    public static Drawable c(int i, Resources resources) {
        Drawable c = AbstractC3153j7.c(resources, d(i));
        c.mutate();
        c.setColorFilter(resources.getColor(R.color.f14730_resource_name_obfuscated_RES_2131100163), PorterDuff.Mode.SRC_IN);
        return c;
    }

    public static int d(int i) {
        return e(i).f8864a;
    }

    public static C1093Ry e(int i) {
        Object obj = ThreadUtils.f10596a;
        if (f8928a == null) {
            HashMap hashMap = new HashMap();
            hashMap.put(26, new C1093Ry(R.drawable.f35550_resource_name_obfuscated_RES_2131231595, R.string.f46610_resource_name_obfuscated_RES_2131951978, 1, 2, 0, R.string.f64910_resource_name_obfuscated_RES_2131953808));
            hashMap.put(58, new C1093Ry(R.drawable.f35540_resource_name_obfuscated_RES_2131231594, R.string.f46980_resource_name_obfuscated_RES_2131952015, 3, 2, R.string.f64960_resource_name_obfuscated_RES_2131953813, R.string.f64970_resource_name_obfuscated_RES_2131953814));
            hashMap.put(13, new C1093Ry(R.drawable.f33280_resource_name_obfuscated_RES_2131231368, R.string.f47630_resource_name_obfuscated_RES_2131952080, 3, 2, R.string.f64980_resource_name_obfuscated_RES_2131953815, 0));
            hashMap.put(22, new C1093Ry(R.drawable.f34460_resource_name_obfuscated_RES_2131231486, R.string.f47760_resource_name_obfuscated_RES_2131952093, 1, 2, R.string.f64950_resource_name_obfuscated_RES_2131953812, 0));
            hashMap.put(53, new C1093Ry(R.drawable.f34880_resource_name_obfuscated_RES_2131231528, 0, 3, 2, 0, 0));
            hashMap.put(21, new C1093Ry(R.drawable.f34880_resource_name_obfuscated_RES_2131231528, R.string.f64890_resource_name_obfuscated_RES_2131953806, 3, 2, R.string.f65000_resource_name_obfuscated_RES_2131953817, R.string.f65010_resource_name_obfuscated_RES_2131953818));
            hashMap.put(44, new C1093Ry(R.drawable.f29630_resource_name_obfuscated_RES_2131231003, R.string.f64900_resource_name_obfuscated_RES_2131953807, 3, 2, R.string.f65020_resource_name_obfuscated_RES_2131953819, 0));
            hashMap.put(54, new C1093Ry(R.drawable.f29820_resource_name_obfuscated_RES_2131231022, R.string.f49150_resource_name_obfuscated_RES_2131952232, 3, 2, R.string.f65040_resource_name_obfuscated_RES_2131953821, R.string.f65050_resource_name_obfuscated_RES_2131953822));
            hashMap.put(0, new C1093Ry(R.drawable.f34470_resource_name_obfuscated_RES_2131231487, R.string.f50180_resource_name_obfuscated_RES_2131952335, 1, 2, R.string.f65090_resource_name_obfuscated_RES_2131953826, 0));
            hashMap.put(5, new C1093Ry(R.drawable.f32410_resource_name_obfuscated_RES_2131231281, R.string.f65430_resource_name_obfuscated_RES_2131953860, 3, 2, R.string.f65180_resource_name_obfuscated_RES_2131953835, 0));
            hashMap.put(40, new C1093Ry(R.drawable.f34480_resource_name_obfuscated_RES_2131231488, R.string.f65460_resource_name_obfuscated_RES_2131953863, 3, 2, R.string.f65150_resource_name_obfuscated_RES_2131953832, R.string.f65160_resource_name_obfuscated_RES_2131953833));
            hashMap.put(2, new C1093Ry(R.drawable.f34490_resource_name_obfuscated_RES_2131231489, R.string.f53580_resource_name_obfuscated_RES_2131952675, 1, 2, R.string.f65170_resource_name_obfuscated_RES_2131953834, 0));
            hashMap.put(10, new C1093Ry(R.drawable.f32910_resource_name_obfuscated_RES_2131231331, R.string.f65640_resource_name_obfuscated_RES_2131953881, 3, 2, R.string.f65030_resource_name_obfuscated_RES_2131953820, 0));
            hashMap.put(9, new C1093Ry(R.drawable.f34500_resource_name_obfuscated_RES_2131231490, R.string.f65650_resource_name_obfuscated_RES_2131953882, 3, 2, R.string.f65190_resource_name_obfuscated_RES_2131953836, 0));
            hashMap.put(14, new C1093Ry(R.drawable.f34510_resource_name_obfuscated_RES_2131231491, R.string.f55020_resource_name_obfuscated_RES_2131952819, null, null, 0, 0));
            hashMap.put(52, new C1093Ry(R.drawable.f34900_resource_name_obfuscated_RES_2131231530, R.string.f55930_resource_name_obfuscated_RES_2131952910, 3, 2, R.string.f65220_resource_name_obfuscated_RES_2131953839, R.string.f65230_resource_name_obfuscated_RES_2131953840));
            hashMap.put(6, new C1093Ry(R.drawable.f34540_resource_name_obfuscated_RES_2131231494, R.string.f59620_resource_name_obfuscated_RES_2131953279, 3, 2, R.string.f65240_resource_name_obfuscated_RES_2131953841, 0));
            hashMap.put(4, new C1093Ry(R.drawable.f34520_resource_name_obfuscated_RES_2131231492, R.string.f59000_resource_name_obfuscated_RES_2131953217, 1, 2, 0, R.string.f65270_resource_name_obfuscated_RES_2131953844));
            hashMap.put(16, new C1093Ry(R.drawable.f34530_resource_name_obfuscated_RES_2131231493, R.string.f59610_resource_name_obfuscated_RES_2131953278, 3, 2, 0, 0));
            int i2 = R.string.f55140_resource_name_obfuscated_RES_2131952831;
            int i3 = R.string.f65200_resource_name_obfuscated_RES_2131953837;
            int i4 = R.string.f65210_resource_name_obfuscated_RES_2131953838;
            try {
                if (N.MVi$blz$("GenericSensorExtraClasses")) {
                    i2 = R.string.f61310_resource_name_obfuscated_RES_2131953448;
                    i3 = R.string.f65330_resource_name_obfuscated_RES_2131953850;
                    i4 = R.string.f65340_resource_name_obfuscated_RES_2131953851;
                }
            } catch (IllegalArgumentException unused) {
            }
            hashMap.put(33, new C1093Ry(R.drawable.f34910_resource_name_obfuscated_RES_2131231531, i2, 1, 2, i3, i4));
            hashMap.put(31, new C1093Ry(R.drawable.f33040_resource_name_obfuscated_RES_2131231344, R.string.f62330_resource_name_obfuscated_RES_2131953550, 1, 2, R.string.f65350_resource_name_obfuscated_RES_2131953852, R.string.f65360_resource_name_obfuscated_RES_2131953853));
            hashMap.put(20, new C1093Ry(R.drawable.f34930_resource_name_obfuscated_RES_2131231533, 0, 3, 2, 0, 0));
            hashMap.put(37, new C1093Ry(R.drawable.f34930_resource_name_obfuscated_RES_2131231533, R.string.f65630_resource_name_obfuscated_RES_2131953880, 3, 2, R.string.f65380_resource_name_obfuscated_RES_2131953855, R.string.f65390_resource_name_obfuscated_RES_2131953856));
            hashMap.put(57, new C1093Ry(R.drawable.f35540_resource_name_obfuscated_RES_2131231594, R.string.f64570_resource_name_obfuscated_RES_2131953774, 3, 2, R.string.f65400_resource_name_obfuscated_RES_2131953857, R.string.f65410_resource_name_obfuscated_RES_2131953858));
            f8928a = hashMap;
        }
        return (C1093Ry) f8928a.get(Integer.valueOf(i));
    }

    public static int f(Integer num) {
        int intValue = num.intValue();
        if (intValue == 1) {
            return R.string.f65490_resource_name_obfuscated_RES_2131953866;
        }
        if (intValue != 2) {
            return 0;
        }
        return R.string.f65520_resource_name_obfuscated_RES_2131953869;
    }

    public static int g(int i) {
        return e(i).b;
    }
}
