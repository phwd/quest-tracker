package com.oculus.common.quickpromotion;

import com.oculus.common.quickpromotion.QuickPromotionController;
import java.util.Arrays;
import java.util.List;

public class QuickPromotionSurfaceIDs {
    public static final int OCULUS_AUI_GUIDE_BAR_QP = 8529;
    public static final int OCULUS_AUI_TOOLTIP_QP = 7790;

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.common.quickpromotion.QuickPromotionSurfaceIDs$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$common$quickpromotion$QuickPromotionController$QPApplication = new int[QuickPromotionController.QPApplication.values().length];

        static {
            try {
                $SwitchMap$com$oculus$common$quickpromotion$QuickPromotionController$QPApplication[QuickPromotionController.QPApplication.AUI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static List<Integer> getSurfacePriorityList(QuickPromotionController.QPApplication qPApplication) {
        if (AnonymousClass1.$SwitchMap$com$oculus$common$quickpromotion$QuickPromotionController$QPApplication[qPApplication.ordinal()] != 1) {
            return null;
        }
        return Arrays.asList(Integer.valueOf((int) OCULUS_AUI_GUIDE_BAR_QP), Integer.valueOf((int) OCULUS_AUI_TOOLTIP_QP));
    }
}
