package com.oculus.tablet;

import X.AbstractC003408r;
import X.AnonymousClass1uS;
import X.AnonymousClass1uW;
import X.AnonymousClass2Q8;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import com.oculus.socialplatform.R;
import com.oculus.systemdialog.DialogProgressIndicator;
import com.oculus.tablet.databinding.CommonTabletRectangularButtonBindingImpl;
import com.oculus.tablet.databinding.OsigButtonBorderlessBindingImpl;
import com.oculus.tablet.databinding.OsigSeekbarBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends AnonymousClass1uS {
    public static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    public static final int LAYOUT_COMMONTABLETRECTANGULARBUTTON = 1;
    public static final int LAYOUT_OSIGBUTTONBORDERLESS = 2;
    public static final int LAYOUT_OSIGSEEKBAR = 3;

    public static class InnerBrLookup {
        public static final SparseArray<String> sKeys;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(7);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "activeIndicator");
            sparseArray.put(2, "enabled");
            sparseArray.put(3, "icon");
            sparseArray.put(4, DialogProgressIndicator.DIALOG_PROGRESS_INDICATOR_PROGRESS_KEY);
            sparseArray.put(5, "progressDrawable");
            sparseArray.put(6, "text");
        }
    }

    public static class InnerLayoutIdLookup {
        public static final HashMap<String, Integer> sKeys;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(3);
            sKeys = hashMap;
            hashMap.put("layout/common_tablet_rectangular_button_0", Integer.valueOf((int) R.layout.common_tablet_rectangular_button));
            HashMap<String, Integer> hashMap2 = sKeys;
            hashMap2.put("layout/osig_button_borderless_0", Integer.valueOf((int) R.layout.osig_button_borderless));
            hashMap2.put("layout/osig_seekbar_0", Integer.valueOf((int) R.layout.osig_seekbar));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(3);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.common_tablet_rectangular_button, 1);
        sparseIntArray.put(R.layout.osig_button_borderless, 2);
        sparseIntArray.put(R.layout.osig_seekbar, 3);
    }

    @Override // X.AnonymousClass1uS
    public List<AnonymousClass1uS> collectDependencies() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new AnonymousClass2Q8());
        return arrayList;
    }

    @Override // X.AnonymousClass1uS
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = InnerLayoutIdLookup.sKeys.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // X.AnonymousClass1uS
    public String convertBrIdToString(int i) {
        return InnerBrLookup.sKeys.get(i);
    }

    @Override // X.AnonymousClass1uS
    public AnonymousClass1uW getDataBinder(AbstractC003408r r4, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag == null) {
            throw new RuntimeException("view must have a tag");
        } else if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    return null;
                }
                if ("layout/osig_seekbar_0".equals(tag)) {
                    return new OsigSeekbarBindingImpl(r4, view);
                }
                StringBuilder sb = new StringBuilder("The tag for osig_seekbar is invalid. Received: ");
                sb.append(tag);
                throw new IllegalArgumentException(sb.toString());
            } else if ("layout/osig_button_borderless_0".equals(tag)) {
                return new OsigButtonBorderlessBindingImpl(r4, view);
            } else {
                StringBuilder sb2 = new StringBuilder("The tag for osig_button_borderless is invalid. Received: ");
                sb2.append(tag);
                throw new IllegalArgumentException(sb2.toString());
            }
        } else if ("layout/common_tablet_rectangular_button_0".equals(tag)) {
            return new CommonTabletRectangularButtonBindingImpl(r4, view);
        } else {
            StringBuilder sb3 = new StringBuilder("The tag for common_tablet_rectangular_button is invalid. Received: ");
            sb3.append(tag);
            throw new IllegalArgumentException(sb3.toString());
        }
    }

    @Override // X.AnonymousClass1uS
    public AnonymousClass1uW getDataBinder(AbstractC003408r r3, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }
}
