package com.oculus.panelapp.anytimeui.guidebar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryViewV2Binding;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.tablet.logging.ClickEventButtonId;

public class QPGuideBarView extends ConstraintLayout {
    private AnytimeTabletLibraryViewV2Binding mBinding;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private QPGuideBarActionHandler mQPGuideBarActionHandler;

    public interface QPGuideBarActionHandler {
        void onDismissGuideBar();

        void onGuideBarCTA();
    }

    public QPGuideBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initialize(AnytimeTabletLibraryViewV2Binding anytimeTabletLibraryViewV2Binding, QPGuideBarActionHandler qPGuideBarActionHandler, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        this.mBinding = anytimeTabletLibraryViewV2Binding;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mQPGuideBarActionHandler = qPGuideBarActionHandler;
    }

    public void configureGuideBar(String str, String str2) {
        this.mBinding.guideBarText.setText(str);
        this.mBinding.guideBarCta.setText(str2);
        this.mBinding.guideBarCta.setEventHandler(this.mPanelApp);
        this.mBinding.guideBarCta.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.guidebar.$$Lambda$QPGuideBarView$AobK36H0XqxtJGpWk3VBDcrovk */

            public final void onClick(View view) {
                QPGuideBarView.this.lambda$configureGuideBar$101$QPGuideBarView(view);
            }
        });
        this.mBinding.dismissCtaIcon.setEventHandler(this.mPanelApp);
        this.mBinding.dismissCtaIcon.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.guidebar.$$Lambda$QPGuideBarView$FhxI2QQ9YawgOQIoWHwOm2v_vw */

            public final void onClick(View view) {
                QPGuideBarView.this.lambda$configureGuideBar$102$QPGuideBarView(view);
            }
        });
    }

    public /* synthetic */ void lambda$configureGuideBar$101$QPGuideBarView(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_QP_GUIDE_BAR_CTA);
        this.mQPGuideBarActionHandler.onGuideBarCTA();
    }

    public /* synthetic */ void lambda$configureGuideBar$102$QPGuideBarView(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_QP_GUIDE_BAR_DISMISS);
        this.mQPGuideBarActionHandler.onDismissGuideBar();
    }

    public void hideGuideBar() {
        animate().alpha(0.0f).setDuration((long) getResources().getInteger(17694720)).setListener(null);
        setVisibility(8);
    }

    public void showGuideBar() {
        int integer = getResources().getInteger(17694721);
        setAlpha(0.0f);
        setVisibility(0);
        animate().alpha(1.0f).setDuration((long) integer).setListener(null);
    }
}
