package com.oculus.common.quickpromotion;

import com.oculus.common.quickpromotion.QuickPromotionController;
import com.oculus.vrshell.SystemUXRoute;

public class AUIGuideBarQuickPromotionModel {
    private SystemUXRoute mCallToActionRoute;
    private String mCallToActionTitle;
    private QuickPromotionController.QPPromotion mCorePromotion;
    private String mGuideBarText;
    private String mLoggingData;
    private double mMaxImpressions;
    private String mTrigger;

    public AUIGuideBarQuickPromotionModel(QuickPromotionController.QPPromotion qPPromotion) {
        if (qPPromotion != null) {
            this.mCorePromotion = qPPromotion;
            this.mGuideBarText = QuickPromotionController.extractContentTextFromPromotion(qPPromotion);
            this.mLoggingData = qPPromotion.logging_data;
            this.mMaxImpressions = qPPromotion.max_impressions;
            this.mTrigger = QuickPromotionController.extractTriggerFromPromotion(qPPromotion);
            this.mCallToActionRoute = QuickPromotionController.extractCTARouteFromPromotion(qPPromotion);
            this.mCallToActionTitle = QuickPromotionController.extractCTATitleFromPromotion(qPPromotion);
        }
    }

    public String getGuideBarText() {
        return this.mGuideBarText;
    }

    public SystemUXRoute getCallToActionRoute() {
        return this.mCallToActionRoute;
    }

    public String getCallToActionTitle() {
        return this.mCallToActionTitle;
    }

    public QuickPromotionController.QPPromotion getCorePromotion() {
        return this.mCorePromotion;
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
