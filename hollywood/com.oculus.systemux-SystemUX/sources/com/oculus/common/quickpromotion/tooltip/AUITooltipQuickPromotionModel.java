package com.oculus.common.quickpromotion.tooltip;

import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.quickpromotion.QuickPromotionController;

public class AUITooltipQuickPromotionModel {
    private static final String TAG = LoggingUtil.tag(AUITooltipQuickPromotionModel.class);
    private QuickPromotionController.QPPromotion mCorePromotion;
    private String mLoggingData;
    private double mMaxImpressions;
    private QuickPromotionTooltipAnchorLabel mTooltipAnchorLabel;
    private String mTooltipText;
    private String mTrigger;

    public AUITooltipQuickPromotionModel(QuickPromotionController.QPPromotion qPPromotion) {
        if (qPPromotion != null) {
            this.mCorePromotion = qPPromotion;
            this.mTooltipAnchorLabel = extractLabelFromPromotion(qPPromotion);
            this.mTooltipText = extractTooltipTextFromPromotion(qPPromotion);
            this.mLoggingData = qPPromotion.logging_data;
            this.mMaxImpressions = qPPromotion.max_impressions;
            this.mTrigger = extractTriggerFromPromotion(qPPromotion);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0073, code lost:
        if (r4.equals("ANDROID_LIBRARY_HOME_BUTTON") != false) goto L_0x008c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.oculus.common.quickpromotion.tooltip.QuickPromotionTooltipAnchorLabel extractLabelFromPromotion(com.oculus.common.quickpromotion.QuickPromotionController.QPPromotion r4) {
        /*
        // Method dump skipped, instructions count: 242
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.common.quickpromotion.tooltip.AUITooltipQuickPromotionModel.extractLabelFromPromotion(com.oculus.common.quickpromotion.QuickPromotionController$QPPromotion):com.oculus.common.quickpromotion.tooltip.QuickPromotionTooltipAnchorLabel");
    }

    private static String extractTooltipTextFromPromotion(QuickPromotionController.QPPromotion qPPromotion) {
        if (qPPromotion.promotion_creatives == null || qPPromotion.promotion_creatives.get(0) == null) {
            return null;
        }
        return qPPromotion.promotion_creatives.get(0).content_text;
    }

    private static String extractTriggerFromPromotion(QuickPromotionController.QPPromotion qPPromotion) {
        if (qPPromotion.triggers == null) {
            return null;
        }
        return qPPromotion.triggers.get(0);
    }

    public QuickPromotionController.QPPromotion getCorePromotion() {
        return this.mCorePromotion;
    }

    public QuickPromotionTooltipAnchorLabel getTooltipAnchorLabel() {
        return this.mTooltipAnchorLabel;
    }

    public String getTooltipText() {
        return this.mTooltipText;
    }

    public String getLoggingData() {
        return this.mLoggingData;
    }

    public double getMaxImpressions() {
        return this.mMaxImpressions;
    }

    public String getTrigger() {
        return this.mTrigger;
    }
}
