package com.oculus.panelapp.anytimeui.v2.tablet.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.imagepipeline.request.ImageRequest;
import com.oculus.library.model.App;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsEnvironmentTileBinding;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIPanelAppBase;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.FrescoControllerListener;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryAppUtils;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.environment.EnvironmentUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.SettingsLogger;

public class SettingsEnvironmentTile extends ConstraintLayout {
    private Context mContext;
    private PipelineDraweeControllerBuilder mControllerBuilder = Fresco.newDraweeControllerBuilder();

    public SettingsEnvironmentTile(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    public void initialize(@NonNull SettingsEnvironment settingsEnvironment, @NonNull AnytimeTabletAndroidSettingsEnvironmentTileBinding anytimeTabletAndroidSettingsEnvironmentTileBinding, @NonNull AnytimeUIPanelAppBase anytimeUIPanelAppBase, @NonNull SettingsLogger settingsLogger) {
        anytimeTabletAndroidSettingsEnvironmentTileBinding.button.setEventHandler(anytimeUIPanelAppBase);
        Animation loadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.ocimagecard_inner_zoom);
        loadAnimation.reset();
        anytimeTabletAndroidSettingsEnvironmentTileBinding.button.setOnHoverListener(new View.OnHoverListener(loadAnimation) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsEnvironmentTile$UKwz30e0Yp6c2raRgZLJoTJWDo */
            private final /* synthetic */ Animation f$1;

            {
                this.f$1 = r2;
            }

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return SettingsEnvironmentTile.lambda$initialize$255(AnytimeTabletAndroidSettingsEnvironmentTileBinding.this, this.f$1, view, motionEvent);
            }
        });
        anytimeTabletAndroidSettingsEnvironmentTileBinding.button.setOnClickListener(new View.OnClickListener(anytimeUIPanelAppBase, settingsLogger) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsEnvironmentTile$pqZhSkgVdT47ytMB2vltzWTMyY */
            private final /* synthetic */ AnytimeUIPanelAppBase f$1;
            private final /* synthetic */ SettingsLogger f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(View view) {
                EnvironmentUtil.doStatusBasedEnvironmentAction(AnytimeTabletAndroidSettingsEnvironmentTileBinding.this.getEnvironment(), this.f$1, true, this.f$2);
            }
        });
        App environment = settingsEnvironment.getEnvironment();
        anytimeTabletAndroidSettingsEnvironmentTileBinding.setEnvironment(environment);
        anytimeTabletAndroidSettingsEnvironmentTileBinding.imageView.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) this.mControllerBuilder.setImageRequest(ImageRequest.fromUri(LibraryAppUtils.getPreferredAppTileImageUri(environment)))).setControllerListener(new FrescoControllerListener(new FrescoControllerListener.FrescoImageReadyCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsEnvironmentTile$sfV7o68wh6eR6YPk4sX2sxFJ6OM */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.FrescoControllerListener.FrescoImageReadyCallback
            public final void onImageReady() {
                AnytimeTabletAndroidSettingsEnvironmentTileBinding.this.setImageAvailable(true);
            }
        }))).build());
    }

    static /* synthetic */ boolean lambda$initialize$255(@NonNull AnytimeTabletAndroidSettingsEnvironmentTileBinding anytimeTabletAndroidSettingsEnvironmentTileBinding, Animation animation, View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            anytimeTabletAndroidSettingsEnvironmentTileBinding.setIsHovered(true);
            anytimeTabletAndroidSettingsEnvironmentTileBinding.title.setSelected(true);
            anytimeTabletAndroidSettingsEnvironmentTileBinding.subtitle.setSelected(true);
            anytimeTabletAndroidSettingsEnvironmentTileBinding.imageView.startAnimation(animation);
        } else if (actionMasked == 10) {
            anytimeTabletAndroidSettingsEnvironmentTileBinding.setIsHovered(false);
            anytimeTabletAndroidSettingsEnvironmentTileBinding.title.setSelected(false);
            anytimeTabletAndroidSettingsEnvironmentTileBinding.subtitle.setSelected(false);
            anytimeTabletAndroidSettingsEnvironmentTileBinding.imageView.clearAnimation();
        }
        return true;
    }
}
