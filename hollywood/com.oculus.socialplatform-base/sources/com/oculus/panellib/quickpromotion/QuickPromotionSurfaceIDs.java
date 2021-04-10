package com.oculus.panellib.quickpromotion;

import com.oculus.modules.codegen.QuickPromotionModule;
import java.util.Arrays;
import java.util.List;

public class QuickPromotionSurfaceIDs {
    public static final int OCULUS_HOME_GDPR_INTERSTITIAL = 5643;
    public static final int OCULUS_HOME_GUIDED_ACTIONS_QP = 7777;
    public static final int OCULUS_HOME_INTERSTITIAL = 5259;
    public static final int OCULUS_HOME_PREPENDED_STORY = 8226;
    public static final int OCULUS_HOME_TOAST_QP = 6386;
    public static final int OCULUS_HOME_TOOLTIP = 5628;
    public static final int OCULUS_LIVING_ROOM_SNACKBAR = 8651;
    public static final int OCULUS_LIVING_ROOM_TOOLTIP = 8646;

    /* renamed from: com.oculus.panellib.quickpromotion.QuickPromotionSurfaceIDs$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$modules$codegen$QuickPromotionModule$QPApplication;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.oculus.modules.codegen.QuickPromotionModule$QPApplication[] r0 = com.oculus.modules.codegen.QuickPromotionModule.QPApplication.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panellib.quickpromotion.QuickPromotionSurfaceIDs.AnonymousClass1.$SwitchMap$com$oculus$modules$codegen$QuickPromotionModule$QPApplication = r2
                com.oculus.modules.codegen.QuickPromotionModule$QPApplication r0 = com.oculus.modules.codegen.QuickPromotionModule.QPApplication.HOME     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.modules.codegen.QuickPromotionModule$QPApplication r0 = com.oculus.modules.codegen.QuickPromotionModule.QPApplication.LIVING_ROOM     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panellib.quickpromotion.QuickPromotionSurfaceIDs.AnonymousClass1.<clinit>():void");
        }
    }

    public static List<Integer> getSurfacePriorityList(QuickPromotionModule.QPApplication qPApplication) {
        Integer[] numArr;
        switch (qPApplication.ordinal()) {
            case 0:
                numArr = new Integer[]{Integer.valueOf((int) OCULUS_HOME_GDPR_INTERSTITIAL), Integer.valueOf((int) OCULUS_HOME_INTERSTITIAL), Integer.valueOf((int) OCULUS_HOME_TOAST_QP), Integer.valueOf((int) OCULUS_HOME_TOOLTIP), Integer.valueOf((int) OCULUS_HOME_GUIDED_ACTIONS_QP), Integer.valueOf((int) OCULUS_HOME_PREPENDED_STORY)};
                break;
            case 1:
                numArr = new Integer[]{Integer.valueOf((int) OCULUS_LIVING_ROOM_TOOLTIP), Integer.valueOf((int) OCULUS_LIVING_ROOM_SNACKBAR)};
                break;
            default:
                return null;
        }
        return Arrays.asList(numArr);
    }
}
