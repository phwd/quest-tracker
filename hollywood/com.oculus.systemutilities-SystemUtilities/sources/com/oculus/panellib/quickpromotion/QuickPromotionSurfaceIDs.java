package com.oculus.panellib.quickpromotion;

import com.oculus.modules.codegen.QuickPromotionModule;
import java.util.Arrays;
import java.util.List;

public class QuickPromotionSurfaceIDs {
    public static List<Integer> getSurfacePriorityList(QuickPromotionModule.QPApplication application) {
        switch (application) {
            case HOME:
                return Arrays.asList(5643, 5259, 6386, 5628, 7777, 8226);
            case LIVING_ROOM:
                return Arrays.asList(8646, 8651);
            default:
                return null;
        }
    }
}
