package com.oculus.panelapp.anytimeui.quickpromotion;

import android.text.TextUtils;
import com.oculus.common.quickpromotion.AUIGuideBarQuickPromotionModel;
import com.oculus.common.quickpromotion.QuickPromotionController;
import com.oculus.common.quickpromotion.QuickPromotionSurfaceIDs;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryViewV2Binding;
import com.oculus.panelapp.anytimeui.guidebar.QPGuideBarView;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;

public class QPGuideBarController implements QPGuideBarView.QPGuideBarActionHandler {
    private static final String DISMISS_ACTION = "dismiss";
    private static final String PRIMARY_ACTION = "primary";
    private AUIGuideBarQuickPromotionModel mCurrentPromotion;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private QuickPromotionController mQPController;
    private QPGuideBarView mQPGuideBarView;

    public QPGuideBarController(AnytimeTabletLibraryViewV2Binding anytimeTabletLibraryViewV2Binding, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        this.mQPController = anytimeUIAndroidPanelAppV2.getQuickPromotionController();
        QuickPromotionController quickPromotionController = this.mQPController;
        if (quickPromotionController != null) {
            this.mCurrentPromotion = quickPromotionController.getAUIGuideBarQP();
            if (!isInValidGuideBar(this.mCurrentPromotion)) {
                this.mPanelApp = anytimeUIAndroidPanelAppV2;
                this.mQPGuideBarView = anytimeTabletLibraryViewV2Binding.qpGuideBar;
                this.mQPGuideBarView.initialize(anytimeTabletLibraryViewV2Binding, this, anytimeUIAndroidPanelAppV2);
                this.mQPGuideBarView.configureGuideBar(this.mCurrentPromotion.getGuideBarText(), this.mCurrentPromotion.getCallToActionTitle());
                exposeAndMaybeShowGuideBar();
            }
        }
    }

    public static boolean shouldShowQPGuideBar(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        QuickPromotionController quickPromotionController = anytimeUIAndroidPanelAppV2.getQuickPromotionController();
        return quickPromotionController != null && !isInValidGuideBar(quickPromotionController.getAUIGuideBarQP());
    }

    private void exposeAndMaybeShowGuideBar() {
        QuickPromotionController.QPPromotion corePromotion = this.mCurrentPromotion.getCorePromotion();
        this.mQPController.markPromotionExposed(corePromotion, this.mCurrentPromotion.getTrigger(), QuickPromotionSurfaceIDs.OCULUS_AUI_GUIDE_BAR_QP);
        if (!corePromotion.is_holdout) {
            this.mQPGuideBarView.showGuideBar();
            this.mQPController.markPromotionShown(corePromotion, this.mCurrentPromotion.getTrigger(), QuickPromotionSurfaceIDs.OCULUS_AUI_GUIDE_BAR_QP);
        }
        this.mQPController.refetchAllSurfaces();
    }

    private static boolean isInValidGuideBar(AUIGuideBarQuickPromotionModel aUIGuideBarQuickPromotionModel) {
        return aUIGuideBarQuickPromotionModel == null || TextUtils.isEmpty(aUIGuideBarQuickPromotionModel.getGuideBarText()) || aUIGuideBarQuickPromotionModel.getCallToActionRoute() == null || aUIGuideBarQuickPromotionModel.getCallToActionTitle() == null;
    }

    @Override // com.oculus.panelapp.anytimeui.guidebar.QPGuideBarView.QPGuideBarActionHandler
    public void onGuideBarCTA() {
        this.mPanelApp.actionNavigate(this.mCurrentPromotion.getCallToActionRoute(), this.mPanelApp.getReturnComponent());
        this.mQPController.markPromotionAction(this.mCurrentPromotion.getCorePromotion(), this.mCurrentPromotion.getTrigger(), "primary", QuickPromotionSurfaceIDs.OCULUS_AUI_GUIDE_BAR_QP);
    }

    @Override // com.oculus.panelapp.anytimeui.guidebar.QPGuideBarView.QPGuideBarActionHandler
    public void onDismissGuideBar() {
        this.mQPGuideBarView.hideGuideBar();
        this.mQPController.markPromotionAction(this.mCurrentPromotion.getCorePromotion(), this.mCurrentPromotion.getTrigger(), DISMISS_ACTION, QuickPromotionSurfaceIDs.OCULUS_AUI_GUIDE_BAR_QP);
    }
}
