package com.oculus.panelapp.anytimeui.quickpromotion;

import android.content.Context;
import android.util.Log;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.quickpromotion.QuickPromotionController;
import com.oculus.common.quickpromotion.QuickPromotionSurfaceIDs;
import com.oculus.common.quickpromotion.tooltip.AUITooltipQuickPromotionModel;
import com.oculus.common.quickpromotion.tooltip.QPTooltipView;
import com.oculus.common.quickpromotion.tooltip.QuickPromotionTooltipAnchorLabel;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletSharingViewV2Binding;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.common.Tablet;

public class QPTooltipController implements QPTooltipView.QPTooltipDismissButtonHandler {
    private static final String DISMISS_ACTION = "dismiss";
    private static final String TAG = LoggingUtil.tag(QPTooltipView.class);
    private AUITooltipQuickPromotionModel mCurrentPromotion;
    private QuickPromotionController mQPController;
    private QPTooltipView mQPTooltipView;

    public QPTooltipController(ViewDataBinding viewDataBinding, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Tablet tablet, Context context) {
        this.mQPController = anytimeUIAndroidPanelAppV2.getQuickPromotionController();
        QuickPromotionController quickPromotionController = this.mQPController;
        if (quickPromotionController != null) {
            this.mCurrentPromotion = quickPromotionController.getAUITooltipQP();
            if (!isInValidQPTooltip(this.mCurrentPromotion) && AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$common$Tablet[tablet.ordinal()] == 1) {
                configureSharingTabletTooltips(anytimeUIAndroidPanelAppV2, (AnytimeTabletSharingViewV2Binding) viewDataBinding, context, this.mCurrentPromotion.getTooltipAnchorLabel(), this.mCurrentPromotion.getTooltipText());
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.anytimeui.quickpromotion.QPTooltipController$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$common$quickpromotion$tooltip$QuickPromotionTooltipAnchorLabel = new int[QuickPromotionTooltipAnchorLabel.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$common$Tablet = new int[Tablet.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|7|8|10) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.common.quickpromotion.tooltip.QuickPromotionTooltipAnchorLabel[] r0 = com.oculus.common.quickpromotion.tooltip.QuickPromotionTooltipAnchorLabel.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.anytimeui.quickpromotion.QPTooltipController.AnonymousClass1.$SwitchMap$com$oculus$common$quickpromotion$tooltip$QuickPromotionTooltipAnchorLabel = r0
                r0 = 1
                int[] r1 = com.oculus.panelapp.anytimeui.quickpromotion.QPTooltipController.AnonymousClass1.$SwitchMap$com$oculus$common$quickpromotion$tooltip$QuickPromotionTooltipAnchorLabel     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.common.quickpromotion.tooltip.QuickPromotionTooltipAnchorLabel r2 = com.oculus.common.quickpromotion.tooltip.QuickPromotionTooltipAnchorLabel.SHARING_CAMERA_ROLL_BUTTON     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r1 = com.oculus.panelapp.anytimeui.quickpromotion.QPTooltipController.AnonymousClass1.$SwitchMap$com$oculus$common$quickpromotion$tooltip$QuickPromotionTooltipAnchorLabel     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.common.quickpromotion.tooltip.QuickPromotionTooltipAnchorLabel r2 = com.oculus.common.quickpromotion.tooltip.QuickPromotionTooltipAnchorLabel.SHARING_CAST_BUTTON     // Catch:{ NoSuchFieldError -> 0x001f }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r3 = 2
                r1[r2] = r3     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                com.oculus.panelapp.anytimeui.v2.tablet.common.Tablet[] r1 = com.oculus.panelapp.anytimeui.v2.tablet.common.Tablet.values()
                int r1 = r1.length
                int[] r1 = new int[r1]
                com.oculus.panelapp.anytimeui.quickpromotion.QPTooltipController.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$common$Tablet = r1
                int[] r1 = com.oculus.panelapp.anytimeui.quickpromotion.QPTooltipController.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$common$Tablet     // Catch:{ NoSuchFieldError -> 0x0032 }
                com.oculus.panelapp.anytimeui.v2.tablet.common.Tablet r2 = com.oculus.panelapp.anytimeui.v2.tablet.common.Tablet.SHARING     // Catch:{ NoSuchFieldError -> 0x0032 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0032 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0032 }
            L_0x0032:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.quickpromotion.QPTooltipController.AnonymousClass1.<clinit>():void");
        }
    }

    private void configureSharingTabletTooltips(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, AnytimeTabletSharingViewV2Binding anytimeTabletSharingViewV2Binding, Context context, QuickPromotionTooltipAnchorLabel quickPromotionTooltipAnchorLabel, String str) {
        int i = AnonymousClass1.$SwitchMap$com$oculus$common$quickpromotion$tooltip$QuickPromotionTooltipAnchorLabel[quickPromotionTooltipAnchorLabel.ordinal()];
        if (i == 1) {
            this.mQPTooltipView = (QPTooltipView) anytimeTabletSharingViewV2Binding.cameraRollQpTooltip.getRoot();
            this.mQPTooltipView.initialize(this, anytimeUIAndroidPanelAppV2, anytimeTabletSharingViewV2Binding.cameraRollQpTooltip);
            this.mQPTooltipView.configureTooltip(str, QPTooltipView.QPTooltipPosition.TopRight, (int) context.getResources().getDimension(R.dimen.anytime_tablet_sharing_recent_button_size));
            exposeAndMaybeShowQPTooltip();
        } else if (i == 2) {
            this.mQPTooltipView = (QPTooltipView) anytimeTabletSharingViewV2Binding.castingQpTooltip.getRoot();
            this.mQPTooltipView.initialize(this, anytimeUIAndroidPanelAppV2, anytimeTabletSharingViewV2Binding.castingQpTooltip);
            this.mQPTooltipView.configureTooltip(str, QPTooltipView.QPTooltipPosition.BottomRight, (int) context.getResources().getDimension(R.dimen.anytime_tablet_sharing_tile_button_width_v2));
            exposeAndMaybeShowQPTooltip();
        }
    }

    private boolean isInValidQPTooltip(AUITooltipQuickPromotionModel aUITooltipQuickPromotionModel) {
        if (aUITooltipQuickPromotionModel == null) {
            return true;
        }
        QuickPromotionTooltipAnchorLabel tooltipAnchorLabel = aUITooltipQuickPromotionModel.getTooltipAnchorLabel();
        String tooltipText = aUITooltipQuickPromotionModel.getTooltipText();
        if (tooltipAnchorLabel != null && tooltipText != null) {
            return false;
        }
        Log.e(TAG, "An anchor label and text is needed to properly display tooltips");
        return true;
    }

    private void exposeAndMaybeShowQPTooltip() {
        QuickPromotionController.QPPromotion corePromotion = this.mCurrentPromotion.getCorePromotion();
        this.mQPController.markPromotionExposed(corePromotion, this.mCurrentPromotion.getTrigger(), QuickPromotionSurfaceIDs.OCULUS_AUI_TOOLTIP_QP);
        if (!corePromotion.is_holdout) {
            this.mQPTooltipView.showTooltip();
            this.mQPController.markPromotionShown(corePromotion, this.mCurrentPromotion.getTrigger(), QuickPromotionSurfaceIDs.OCULUS_AUI_TOOLTIP_QP);
        }
        this.mQPController.refetchAllSurfaces();
    }

    @Override // com.oculus.common.quickpromotion.tooltip.QPTooltipView.QPTooltipDismissButtonHandler
    public void onDismissQPTooltip() {
        this.mQPTooltipView.hideTooltip();
        this.mQPController.markPromotionAction(this.mCurrentPromotion.getCorePromotion(), this.mCurrentPromotion.getTrigger(), DISMISS_ACTION, QuickPromotionSurfaceIDs.OCULUS_AUI_TOOLTIP_QP);
    }
}
