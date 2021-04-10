package X;

import android.graphics.Bitmap;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Queue;
import java.util.TreeMap;

@RequiresApi(19)
/* renamed from: X.1hY  reason: invalid class name */
public final class AnonymousClass1hY implements AnonymousClass1hF {
    public static final Bitmap.Config[] A03 = {Bitmap.Config.ALPHA_8};
    public static final Bitmap.Config[] A04 = {Bitmap.Config.ARGB_4444};
    public static final Bitmap.Config[] A05;
    public static final Bitmap.Config[] A06;
    public static final Bitmap.Config[] A07 = {Bitmap.Config.RGB_565};
    public final C09281hc A00 = new C09281hc();
    public final Map<Bitmap.Config, NavigableMap<Integer, Integer>> A01 = new HashMap();
    public final AnonymousClass1hZ<C09311hf, Bitmap> A02 = new AnonymousClass1hZ<>();

    static {
        Bitmap.Config[] configArr = {Bitmap.Config.ARGB_8888, null};
        if (Build.VERSION.SDK_INT >= 26) {
            configArr = (Bitmap.Config[]) Arrays.copyOf(configArr, 3);
            configArr[configArr.length - 1] = Bitmap.Config.RGBA_F16;
        }
        A05 = configArr;
        A06 = configArr;
    }

    public static String A00(int i, Bitmap.Config config) {
        StringBuilder sb = new StringBuilder("[");
        sb.append(i);
        sb.append("](");
        sb.append(config);
        sb.append(")");
        return sb.toString();
    }

    @Override // X.AnonymousClass1hF
    @Nullable
    public final Bitmap A99() {
        Bitmap A002 = this.A02.A00();
        if (A002 != null) {
            A01(Integer.valueOf(C08381eW.A01(A002)), A002);
        }
        return A002;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SizeConfigStrategy{groupedMap=");
        sb.append(this.A02);
        sb.append(", sortedSizes=(");
        Map<Bitmap.Config, NavigableMap<Integer, Integer>> map = this.A01;
        for (Map.Entry<Bitmap.Config, NavigableMap<Integer, Integer>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append('[');
            sb.append(entry.getValue());
            sb.append("], ");
        }
        if (!map.isEmpty()) {
            sb.replace(sb.length() - 2, sb.length(), "");
        }
        sb.append(")}");
        return sb.toString();
    }

    private void A01(Integer num, Bitmap bitmap) {
        Bitmap.Config config = bitmap.getConfig();
        Map<Bitmap.Config, NavigableMap<Integer, Integer>> map = this.A01;
        NavigableMap<Integer, Integer> navigableMap = map.get(config);
        if (navigableMap == null) {
            navigableMap = new TreeMap<>();
            map.put(config, navigableMap);
        }
        Number number = (Number) navigableMap.get(num);
        if (number != null) {
            int intValue = number.intValue();
            if (intValue == 1) {
                navigableMap.remove(num);
            } else {
                navigableMap.put(num, Integer.valueOf(intValue - 1));
            }
        } else {
            StringBuilder sb = new StringBuilder("Tried to decrement empty size, size: ");
            sb.append(num);
            sb.append(", removed: ");
            sb.append(A6L(bitmap));
            sb.append(", this: ");
            sb.append(this);
            throw new NullPointerException(sb.toString());
        }
    }

    @Override // X.AnonymousClass1hF
    @Nullable
    public final Bitmap A3J(int i, int i2, Bitmap.Config config) {
        Bitmap.Config[] configArr;
        int intValue;
        int A002 = C08381eW.A00(i, i2, config);
        C09281hc r3 = this.A00;
        C09311hf A003 = r3.A00(A002, config);
        if (Build.VERSION.SDK_INT < 26 || !Bitmap.Config.RGBA_F16.equals(config)) {
            int i3 = AnonymousClass1hD.A00[config.ordinal()];
            if (i3 == 1) {
                configArr = A05;
            } else if (i3 == 2) {
                configArr = A07;
            } else if (i3 == 3) {
                configArr = A04;
            } else if (i3 != 4) {
                configArr = new Bitmap.Config[]{config};
            } else {
                configArr = A03;
            }
        } else {
            configArr = A06;
        }
        int length = configArr.length;
        int i4 = 0;
        while (true) {
            if (i4 >= length) {
                break;
            }
            Bitmap.Config config2 = configArr[i4];
            Map<Bitmap.Config, NavigableMap<Integer, Integer>> map = this.A01;
            NavigableMap<Integer, Integer> navigableMap = map.get(config2);
            if (navigableMap == null) {
                navigableMap = new TreeMap<>();
                map.put(config2, navigableMap);
            }
            Integer ceilingKey = navigableMap.ceilingKey(Integer.valueOf(A002));
            if (ceilingKey == null || (intValue = ceilingKey.intValue()) > (A002 << 3)) {
                i4++;
            } else if (intValue != A002 || (config2 != null ? !config2.equals(config) : config != null)) {
                Queue<T> queue = r3.A00;
                if (queue.size() < 20) {
                    queue.offer(A003);
                }
                A003 = r3.A00(intValue, config2);
            }
        }
        Bitmap A012 = this.A02.A01(A003);
        if (A012 != null) {
            A01(Integer.valueOf(A003.A00), A012);
            A012.reconfigure(i, i2, config);
        }
        return A012;
    }

    @Override // X.AnonymousClass1hF
    public final int A4v(Bitmap bitmap) {
        return C08381eW.A01(bitmap);
    }

    @Override // X.AnonymousClass1hF
    public final String A6K(int i, int i2, Bitmap.Config config) {
        return A00(C08381eW.A00(i, i2, config), config);
    }

    @Override // X.AnonymousClass1hF
    public final String A6L(Bitmap bitmap) {
        return A00(C08381eW.A01(bitmap), bitmap.getConfig());
    }

    @Override // X.AnonymousClass1hF
    public final void A8l(Bitmap bitmap) {
        C09311hf A002 = this.A00.A00(C08381eW.A01(bitmap), bitmap.getConfig());
        this.A02.A02(A002, bitmap);
        Bitmap.Config config = bitmap.getConfig();
        Map<Bitmap.Config, NavigableMap<Integer, Integer>> map = this.A01;
        NavigableMap<Integer, Integer> navigableMap = map.get(config);
        if (navigableMap == null) {
            navigableMap = new TreeMap<>();
            map.put(config, navigableMap);
        }
        Number number = (Number) navigableMap.get(Integer.valueOf(A002.A00));
        Integer valueOf = Integer.valueOf(A002.A00);
        int i = 1;
        if (number != null) {
            i = 1 + number.intValue();
        }
        navigableMap.put(valueOf, Integer.valueOf(i));
    }
}
