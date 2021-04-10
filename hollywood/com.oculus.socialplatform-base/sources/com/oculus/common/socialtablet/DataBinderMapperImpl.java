package com.oculus.common.socialtablet;

import X.AbstractC003408r;
import X.AnonymousClass1uS;
import X.AnonymousClass1uW;
import X.AnonymousClass2Q8;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import com.oculus.common.socialtablet.databinding.SocialTabletSideNavBindingImpl;
import com.oculus.socialplatform.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends AnonymousClass1uS {
    public static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    public static final int LAYOUT_SOCIALTABLETSIDENAV = 1;

    public static class InnerBrLookup {
        public static final SparseArray<String> sKeys;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(5);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "chatVisibility");
            sparseArray.put(2, "peopleVisibility");
            sparseArray.put(3, "settingsVisibility");
            sparseArray.put(4, "socialTabletSideNavViewModel");
        }
    }

    public static class InnerLayoutIdLookup {
        public static final HashMap<String, Integer> sKeys;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(1);
            sKeys = hashMap;
            hashMap.put("layout/social_tablet_side_nav_0", Integer.valueOf((int) R.layout.social_tablet_side_nav));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(1);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.social_tablet_side_nav, 1);
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
            return null;
        } else {
            if ("layout/social_tablet_side_nav_0".equals(tag)) {
                return new SocialTabletSideNavBindingImpl(r4, view);
            }
            StringBuilder sb = new StringBuilder("The tag for social_tablet_side_nav is invalid. Received: ");
            sb.append(tag);
            throw new IllegalArgumentException(sb.toString());
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
