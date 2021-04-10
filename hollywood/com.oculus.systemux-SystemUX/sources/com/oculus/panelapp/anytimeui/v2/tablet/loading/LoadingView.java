package com.oculus.panelapp.anytimeui.v2.tablet.loading;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLoadingViewV2Binding;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.common.ContentLayout;
import com.oculus.tablet.view.BaseView;

public class LoadingView extends BaseView {
    private static final String TAG = LoggingUtil.tag(LoadingView.class);
    private AnytimeTabletLoadingViewV2Binding mBinding;

    public LoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.d(TAG, "Constructing LoadingView");
    }

    public void initialize(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, ViewDataBinding viewDataBinding, ContentLayout contentLayout) {
        super.initialize(anytimeUIAndroidPanelAppV2, viewDataBinding);
        Log.d(TAG, "Initializing LoadingView");
        this.mBinding = (AnytimeTabletLoadingViewV2Binding) viewDataBinding;
        if (ContentLayout.ENTIRE_TABLET.equals(contentLayout)) {
            setBackgroundResource(R.drawable.anytime_tablet_background_v2);
        } else {
            setBackgroundResource(R.drawable.anytime_tablet_content_background_v2);
        }
        this.mBinding.loadingDots.container.initialize();
    }

    @Override // com.oculus.tablet.view.BaseView
    public void destroy() {
        Log.d(TAG, "Destroying LoadingView");
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onShow(String str) {
        Log.d(TAG, "Showing LoadingView");
        this.mBinding.loadingDots.container.start();
    }

    @Override // com.oculus.tablet.view.BaseView
    public void onHide() {
        Log.d(TAG, "Hiding LoadingView");
        this.mBinding.loadingDots.container.end();
    }
}
