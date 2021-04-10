package com.oculus.panellib.quickpromotion;

import com.oculus.modules.codegen.QuickPromotionModule;
import com.oculus.panellib.Qpl;
import java.util.Arrays;
import java.util.List;

public class QuickPromotionSurfaceIDs {

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panellib.quickpromotion.QuickPromotionSurfaceIDs$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$modules$codegen$QuickPromotionModule$QPApplication = new int[QuickPromotionModule.QPApplication.values().length];

        static {
            try {
                $SwitchMap$com$oculus$modules$codegen$QuickPromotionModule$QPApplication[QuickPromotionModule.QPApplication.HOME.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$oculus$modules$codegen$QuickPromotionModule$QPApplication[QuickPromotionModule.QPApplication.LIVING_ROOM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public static List<Integer> getSurfacePriorityList(QuickPromotionModule.QPApplication application) {
        switch (AnonymousClass1.$SwitchMap$com$oculus$modules$codegen$QuickPromotionModule$QPApplication[application.ordinal()]) {
            case 1:
                return Arrays.asList(5643, 5259, 6386, 5628, 7777, 8226);
            case Qpl.ACTION_SUCCESS:
                return Arrays.asList(8646, 8651);
            default:
                return null;
        }
    }
}
