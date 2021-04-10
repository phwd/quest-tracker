package com.oculus.panelapp.anytimeui.v2.bar.status;

import android.content.Context;
import android.graphics.Outline;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.annotation.VisibleForTesting;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.panelapp.anytimeui.databinding.AnytimeBarSystemStatusBinding;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.common.Tablet;
import com.oculus.panelapp.social.SocialDataObserver;
import com.oculus.panelapp.social.SocialViewModel;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.vrshell.SystemUXRoute;
import java.util.List;

public class SystemStatusView extends ConstraintLayout {
    private static final String TAG = LoggingUtil.tag(SystemStatusView.class);
    private AnytimeBarSystemStatusBinding mBinding;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private String mProfilePhotoUrl;
    private SocialDataObserver mSocialDataObserver;
    private SocialViewModel mSocialViewModel;

    public SystemStatusView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initialize(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        Log.d(TAG, "Initializing System Status");
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mBinding = AnytimeBarSystemStatusBinding.inflate(LayoutInflater.from(getContext()), this, true);
        this.mSocialViewModel = this.mPanelApp.acquireSocialViewModel();
        initializeNotificationsButton();
        initializeProfilePhoto();
        initializeProfileButton();
        initializeQuickActionsButton();
        Log.d(TAG, "Initialized System Status");
    }

    private void initializeNotificationsButton() {
        this.mBinding.notificationsButton.setEventHandler(this.mPanelApp);
        this.mBinding.notificationsButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.status.$$Lambda$SystemStatusView$4dWfH5hvZQOtN2tylorN8cAvBCk */

            public final void onClick(View view) {
                SystemStatusView.this.lambda$initializeNotificationsButton$106$SystemStatusView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeNotificationsButton$106$SystemStatusView(View view) {
        if (this.mPanelApp.getCurrentTablet() == Tablet.NOTIFICATIONS) {
            this.mPanelApp.actionNavigate(SystemUXRoute.AUI_TABLET_NONE, "");
        } else {
            this.mPanelApp.actionNavigate(SystemUXRoute.NOTIFICATIONS, "");
        }
    }

    private void initializeProfilePhoto() {
        this.mSocialDataObserver = new SocialDataObserver() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.status.SystemStatusView.AnonymousClass1 */

            @Override // com.oculus.panelapp.social.SocialDataObserver
            public void onEnoughDataFetched() {
            }

            @Override // com.oculus.panelapp.social.SocialDataObserver
            public void updateFriendsList(List<SocialUser> list, List<SocialUser> list2, List<SocialParty> list3, SocialParty socialParty) {
            }

            @Override // com.oculus.panelapp.social.SocialDataObserver
            public void updateSocialViewer() {
                SystemStatusView.this.updateProfilePhoto();
            }
        };
        this.mSocialViewModel.registerObserver(this.mSocialDataObserver);
        this.mBinding.profilePhoto.setOutlineProvider(new ViewOutlineProvider() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.status.SystemStatusView.AnonymousClass2 */

            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), (float) (view.getWidth() / 2));
            }
        });
        this.mBinding.profilePhoto.setClipToOutline(true);
        updateProfilePhoto();
    }

    private void initializeQuickActionsButton() {
        this.mBinding.quickActionsButton.setEventHandler(this.mPanelApp);
    }

    private void initializeProfileButton() {
        this.mBinding.profileButton.setEventHandler(this.mPanelApp);
        this.mBinding.profileButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.status.$$Lambda$SystemStatusView$XXdRS4XZpcjpFJoKkHRJcUbWBuc */

            public final void onClick(View view) {
                SystemStatusView.this.lambda$initializeProfileButton$107$SystemStatusView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initializeProfileButton$107$SystemStatusView(View view) {
        if (this.mPanelApp.getCurrentTablet() == Tablet.PROFILE) {
            this.mPanelApp.actionNavigate(SystemUXRoute.AUI_TABLET_NONE, "");
            return;
        }
        this.mPanelApp.actionNavigate(SystemUXRoute.AUI_PROFILE, new Uri.Builder().encodedPath(TabletDeepLinkingUriUtil.AUI_SELF_VR_PROFILE_URI).appendQueryParameter("entrypoint", "aui_bar").build().toString());
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateProfilePhoto() {
        String profilePhotoUrl = this.mSocialViewModel.getProfilePhotoUrl();
        setProfilePhoto(this.mBinding.profilePhoto, this.mProfilePhotoUrl, profilePhotoUrl);
        this.mProfilePhotoUrl = profilePhotoUrl;
    }

    @VisibleForTesting
    public static void setProfilePhoto(SimpleDraweeView simpleDraweeView, String str, String str2) {
        if ((TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) || (!TextUtils.isEmpty(str) && !str.equals(str2))) {
            simpleDraweeView.setImageURI(str2);
        }
    }

    public void destroy() {
        this.mSocialViewModel.removeObserver(this.mSocialDataObserver);
        this.mPanelApp.releaseSocialViewModel();
    }
}
