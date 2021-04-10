package com.oculus.panelapp.anytimeui.v2.tablet.settings;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.oculus.ocui.OCEventHandler;
import com.oculus.ocui.OCLink;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsListItemBinding;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.SettingsLogger;

public class SettingsListItem extends ConstraintLayout {
    private AnytimeTabletAndroidSettingsListItemBinding mBinding;
    private OCEventHandler mEventHandler;
    private SettingsItem mSettingsItem;

    public SettingsListItem(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initialize(@NonNull SettingsItem settingsItem, @NonNull AnytimeTabletAndroidSettingsListItemBinding anytimeTabletAndroidSettingsListItemBinding, @NonNull OCEventHandler oCEventHandler, @NonNull OCLink.OCLinkHandler oCLinkHandler, @NonNull SettingsLogger settingsLogger) {
        this.mSettingsItem = settingsItem;
        this.mEventHandler = oCEventHandler;
        this.mBinding = anytimeTabletAndroidSettingsListItemBinding;
        this.mBinding.setItem(settingsItem);
        this.mBinding.subtitle.setLinkHandler(oCLinkHandler);
        this.mBinding.subtitle.setEventHandler(oCEventHandler);
        this.mBinding.image.setImageURI(settingsItem.getImageUri());
        this.mBinding.image.setClipToOutline(true);
        if (settingsItem.hasPlaceholderImage()) {
            ((GenericDraweeHierarchy) this.mBinding.image.getHierarchy()).setPlaceholderImage(settingsItem.getPlaceholderImage());
        } else {
            ((GenericDraweeHierarchy) this.mBinding.image.getHierarchy()).setPlaceholderImage((Drawable) null);
        }
        this.mBinding.rightViewGroup.removeAllViewsInLayout();
        this.mBinding.bottomViewGroup.removeAllViewsInLayout();
        this.mBinding.bottomViewGroup.setVisibility(8);
        setOnClickListener(null);
        settingsItem.buildView(this, oCEventHandler, settingsLogger);
    }

    public ViewGroup getBottomViewGroup() {
        return this.mBinding.bottomViewGroup;
    }

    public ViewGroup getRightViewGroup() {
        return this.mBinding.rightViewGroup;
    }

    public void setOnClickListener(@Nullable View.OnClickListener onClickListener) {
        if (onClickListener == null) {
            super.setOnClickListener(null);
            super.setOnHoverListener(null);
            setTag(null);
            return;
        }
        super.setOnClickListener(new View.OnClickListener(onClickListener) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsListItem$6UufUp_bxfmDM1cPn_HBcB2sB_s */
            private final /* synthetic */ View.OnClickListener f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                SettingsListItem.this.lambda$setOnClickListener$252$SettingsListItem(this.f$1, view);
            }
        });
        super.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsListItem$84zrWx6puSY6OGRpcTYne5pvug */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return SettingsListItem.this.lambda$setOnClickListener$253$SettingsListItem(view, motionEvent);
            }
        });
        setTag(getSettingName());
    }

    public /* synthetic */ void lambda$setOnClickListener$252$SettingsListItem(@Nullable View.OnClickListener onClickListener, View view) {
        this.mEventHandler.onButtonClick();
        onClickListener.onClick(view);
    }

    public /* synthetic */ boolean lambda$setOnClickListener$253$SettingsListItem(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 9) {
            return false;
        }
        this.mEventHandler.onButtonEnter();
        return false;
    }

    public String getSettingName() {
        return this.mSettingsItem.getName();
    }
}
