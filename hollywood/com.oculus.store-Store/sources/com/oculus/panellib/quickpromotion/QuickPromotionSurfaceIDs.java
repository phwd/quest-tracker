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

    public static List<Integer> getSurfacePriorityList(QuickPromotionModule.QPApplication application) {
        switch (application) {
            case HOME:
                return Arrays.asList(Integer.valueOf((int) OCULUS_HOME_GDPR_INTERSTITIAL), Integer.valueOf((int) OCULUS_HOME_INTERSTITIAL), Integer.valueOf((int) OCULUS_HOME_TOAST_QP), Integer.valueOf((int) OCULUS_HOME_TOOLTIP), Integer.valueOf((int) OCULUS_HOME_GUIDED_ACTIONS_QP), Integer.valueOf((int) OCULUS_HOME_PREPENDED_STORY));
            case LIVING_ROOM:
                return Arrays.asList(Integer.valueOf((int) OCULUS_LIVING_ROOM_TOOLTIP), Integer.valueOf((int) OCULUS_LIVING_ROOM_SNACKBAR));
            default:
                return null;
        }
    }
}
