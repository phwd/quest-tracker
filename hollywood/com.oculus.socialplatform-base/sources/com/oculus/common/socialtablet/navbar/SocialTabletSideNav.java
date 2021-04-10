package com.oculus.common.socialtablet.navbar;

import X.AbstractC08911fj;
import X.AnonymousClass1gU;
import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.service.notification.StatusBarNotification;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.fbaccountsmanager.MessengerVrAccountsContentProviderClient;
import com.oculus.common.sociallogger.ClickEventButtonId;
import com.oculus.common.sociallogger.SurfaceType;
import com.oculus.common.socialtablet.databinding.SocialTabletSideNavBinding;
import com.oculus.common.socialtablet.deviceconfig.DeviceConfigSocialPlatformMC;
import com.oculus.common.socialtablet.fetchers.FBLinkingFetcher;
import com.oculus.common.socialtablet.fetchers.FBProfileFetcher;
import com.oculus.common.socialtablet.fetchers.OCProfileFetcher;
import com.oculus.common.socialtablet.notif.SocialNotificationBroadcastReceiver;
import com.oculus.common.socialtablet.notif.SocialNotificationUtils;
import com.oculus.common.socialtablet.panelapp.SocialTabletPanelApp;
import com.oculus.common.socialtablet.util.EducationTooltip;
import com.oculus.common.socialtablet.util.EducationTooltipUtil;
import com.oculus.notifications.NotificationDataSetListener;
import com.oculus.notifications.NotificationDataSetService;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCDropdown;
import com.oculus.ocui.OCTooltip;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.FrameCommandChannel;
import com.oculus.vrshell.util.ThreadExecutor;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;

public class SocialTabletSideNav extends ConstraintLayout implements NotificationDataSetListener {
    public static final String FB_UPSELL_ACTION_PARAM = "open_chat_tablet";
    public static final String FB_UPSELL_CONTAINER_PARAM = "social_tablet_side_nav";
    public static final String FB_UPSELL_ENTRYPOINT_PARAM = "social_tablet_side_nav_chat_icon";
    public static final String FB_UPSELL_MUST_INTERACT_PARAM = "true";
    public static final String FB_UPSELL_PRODUCT_PARAM = "messenger_vr";
    public static final String FB_UPSELL_SOURCE_PARAM = "social_tablet_side_nav";
    public static final String NOTIF_CHANGE_ACTION = "com.oculus.intent.action.NOTIF_CHANGE";
    public static final String TAG = "SocialTabletSideNav";
    public SocialTabletSideNavBinding mBinding;
    public ProfileItem mFBProfile;
    public FBProfileFetcher mFBProfileFetcher;
    public AnonymousClass1gU<Bitmap> mFBProfilePicTarget;
    public Optional<Boolean> mIsFBLinked;
    public Optional<Boolean> mIsMessengerAuthenticated;
    public FBLinkingFetcher.FetchLinkedStatusListener mLinkedStatusListener;
    public Drawable mNavBarProfilePic;
    public AnonymousClass1gU<Bitmap> mNavBarProfilePicTarget;
    public Set<String> mNotifKeys = new HashSet();
    public SocialNotificationBroadcastReceiver mNotificationBroadcastReceiver;
    public ProfileItem mOCProfile;
    public OCProfileFetcher mOCProfileFetcher;
    public AnonymousClass1gU<Bitmap> mOCProfilePicTarget;
    public SocialTabletPanelApp mPanelApp;
    public OCDropdown<ProfileItem> mProfileSwitcherDropdown;
    @Nullable
    public EducationTooltip mProfileSwitcherTooltip;
    public ProfileType mSelectedProfileType;
    public SocialTabletType mSelectedTabletType;
    public NotificationDataSetService mService;
    public SocialTabletSideNavViewModel mViewModel;

    private void fetchInitialNotifications() {
        this.mService.getAllPersistentNotifications().stream().filter($$Lambda$SocialTabletSideNav$ugsMwyxdsJe12qtbhgOElKVpFM2.INSTANCE).forEach(new Consumer() {
            /* class com.oculus.common.socialtablet.navbar.$$Lambda$SocialTabletSideNav$XrTylOa22TJEGkhAXVjUhhtD7o2 */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SocialTabletSideNav.this.lambda$fetchInitialNotifications$1$SocialTabletSideNav((StatusBarNotification) obj);
            }
        });
        this.mNotifKeys.size();
    }

    private void fetchMessengerAuthState() {
        ThreadExecutor.INSTANCE.execute(new Callable() {
            /* class com.oculus.common.socialtablet.navbar.$$Lambda$SocialTabletSideNav$HuZaPY6glsihyKN1JZvmTi6632E2 */

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SocialTabletSideNav.this.lambda$fetchMessengerAuthState$9$SocialTabletSideNav();
            }
        });
    }

    private void fetchProfiles() {
        this.mLinkedStatusListener = new FBLinkingFetcher.FetchLinkedStatusListener() {
            /* class com.oculus.common.socialtablet.navbar.$$Lambda$SocialTabletSideNav$_wfVVS8i91ykWo_kcZMWc1Eyk2 */

            @Override // com.oculus.common.socialtablet.fetchers.FBLinkingFetcher.FetchLinkedStatusListener
            public final void onLinkedStatusFetched(boolean z) {
                SocialTabletSideNav.this.lambda$fetchProfiles$5$SocialTabletSideNav(z);
            }
        };
        this.mPanelApp.getFBLinkingFetcher().registerFBLinkedStatusListener(this.mLinkedStatusListener);
        this.mNavBarProfilePicTarget = getNavBarProfilePicTarget();
        this.mFBProfileFetcher.fetchFBProfile(new FBProfileFetcher.FetchCallback() {
            /* class com.oculus.common.socialtablet.navbar.$$Lambda$SocialTabletSideNav$gCj7YMZcGHVGGrlLofNr49s8saI2 */

            @Override // com.oculus.common.socialtablet.fetchers.FBProfileFetcher.FetchCallback
            public final void onFBProfileFetched(String str, String str2) {
                SocialTabletSideNav.this.lambda$fetchProfiles$6$SocialTabletSideNav(str, str2);
            }
        });
        this.mOCProfileFetcher.fetchOCProfile(new OCProfileFetcher.FetchCallback() {
            /* class com.oculus.common.socialtablet.navbar.$$Lambda$SocialTabletSideNav$piIBfBpqlQGDqfKXyYbqf47xZ42 */

            @Override // com.oculus.common.socialtablet.fetchers.OCProfileFetcher.FetchCallback
            public final void onOCProfileFetched(String str, String str2) {
                SocialTabletSideNav.this.lambda$fetchProfiles$7$SocialTabletSideNav(str, str2);
            }
        });
    }

    private String getChatPath(ProfileType profileType) {
        SystemUXRoute systemUXRoute;
        if (profileType == ProfileType.FACEBOOK) {
            systemUXRoute = SystemUXRoute.AUI_MESSENGER;
        } else {
            systemUXRoute = SystemUXRoute.AUI_CHATS;
        }
        return systemUXRoute.path();
    }

    private String getPeoplePath(ProfileType profileType) {
        SystemUXRoute systemUXRoute;
        if (profileType == ProfileType.FACEBOOK) {
            systemUXRoute = SystemUXRoute.AUI_PEOPLE_FB;
        } else {
            systemUXRoute = SystemUXRoute.AUI_PEOPLE;
        }
        return systemUXRoute.path();
    }

    private String getSocialSettingsPath(ProfileType profileType) {
        return SystemUXRoute.AUI_SOCIAL_SETTINGS.path();
    }

    private void initializeNotificationBroadcastReceiver() {
        this.mNotificationBroadcastReceiver = new SocialNotificationBroadcastReceiver();
        getContext().registerReceiver(this.mNotificationBroadcastReceiver, new IntentFilter("com.oculus.intent.action.NOTIF_CHANGE"));
    }

    private void initializeProfileSwitcher() {
        if (this.mProfileSwitcherDropdown == null) {
            Context context = getContext();
            OCDropdown<ProfileItem> oCDropdown = new OCDropdown<>(context);
            this.mProfileSwitcherDropdown = oCDropdown;
            oCDropdown.setWidth((int) context.getResources().getDimension(R.dimen.profile_switcher_dropdown_width));
            this.mProfileSwitcherDropdown.setIconSizeDp((int) context.getResources().getDimension(R.dimen.messenger_tablet_message_bubble_profile_picture_diameter));
            this.mProfileSwitcherDropdown.setContextMenuTitle((int) R.string.anytime_tablet_messenger_profile_switcher_title);
            setProfileSwitcherItems();
        }
        OCDropdown<ProfileItem> oCDropdown2 = this.mProfileSwitcherDropdown;
        oCDropdown2.setEventHandler(this.mPanelApp);
        oCDropdown2.setOnItemClick(new Function() {
            /* class com.oculus.common.socialtablet.navbar.$$Lambda$SocialTabletSideNav$gkLBx952Cj1Hcl3eKrpjULWU0Wg2 */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SocialTabletSideNav.this.lambda$initializeProfileSwitcher$10$SocialTabletSideNav((ProfileItem) obj);
            }
        });
        oCDropdown2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            /* class com.oculus.common.socialtablet.navbar.$$Lambda$SocialTabletSideNav$FQgJkPjEgjvkrenkIFHTgUnauH42 */

            public final void onDismiss() {
                SocialTabletSideNav.this.lambda$initializeProfileSwitcher$11$SocialTabletSideNav();
            }
        });
        if (DeviceConfigHelper.getBoolean(getContext(), DeviceConfigSocialPlatformMC.MESSENGER_PROFILE_SWITCHER_EDUCATION_TOOLTIP) && this.mProfileSwitcherTooltip == null) {
            this.mBinding.sideNavProfileSwitcher.post(new Runnable() {
                /* class com.oculus.common.socialtablet.navbar.$$Lambda$SocialTabletSideNav$lLAdC3UE8XGo5pIVLYXXfYbMMhI2 */

                public final void run() {
                    SocialTabletSideNav.this.lambda$initializeProfileSwitcher$12$SocialTabletSideNav();
                }
            });
        }
        setProfileSwitcherButton();
    }

    private void initializeTabletSwitcher() {
        OCButton oCButton = this.mBinding.sideNavChatButton;
        oCButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.common.socialtablet.navbar.$$Lambda$SocialTabletSideNav$ptBfjIBlCUD3neifS5Ev3iWnWik2 */

            public final void onClick(View view) {
                SocialTabletSideNav.this.lambda$initializeTabletSwitcher$2$SocialTabletSideNav(view);
            }
        });
        oCButton.mEventHandler = this.mPanelApp;
        boolean z = true;
        boolean z2 = false;
        if (this.mSelectedTabletType == SocialTabletType.CHAT) {
            z2 = true;
        }
        oCButton.setSelected(z2);
        OCButton oCButton2 = this.mBinding.sideNavPeopleButton;
        oCButton2.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.common.socialtablet.navbar.$$Lambda$SocialTabletSideNav$bMKcpLmoTdey4tYES_3DQRf1S82 */

            public final void onClick(View view) {
                SocialTabletSideNav.this.lambda$initializeTabletSwitcher$3$SocialTabletSideNav(view);
            }
        });
        oCButton2.mEventHandler = this.mPanelApp;
        boolean z3 = false;
        if (this.mSelectedTabletType == SocialTabletType.PEOPLE) {
            z3 = true;
        }
        oCButton2.setSelected(z3);
        OCButton oCButton3 = this.mBinding.sideNavSettingsButton;
        oCButton3.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.common.socialtablet.navbar.$$Lambda$SocialTabletSideNav$7ZszuuZjetEsz1WFMXYX1Ib5ec2 */

            public final void onClick(View view) {
                SocialTabletSideNav.this.lambda$initializeTabletSwitcher$4$SocialTabletSideNav(view);
            }
        });
        oCButton3.mEventHandler = this.mPanelApp;
        if (this.mSelectedTabletType != SocialTabletType.SETTINGS) {
            z = false;
        }
        oCButton3.setSelected(z);
    }

    private void loadSelectedPanelApp(ProfileType profileType) {
        String peoplePath;
        FrameCommandChannel frameCommandChannel = ((AndroidPanelApp) this.mPanelApp).mFrameCommandChannel;
        if (this.mSelectedTabletType == SocialTabletType.CHAT) {
            peoplePath = getChatPath(profileType);
        } else {
            peoplePath = getPeoplePath(profileType);
        }
        frameCommandChannel.launch(peoplePath, null);
    }

    private void setProfileSwitcherBadge(ProfileType profileType) {
        ProfileItem profileItem;
        if (this.mFBProfile != null && this.mOCProfile != null) {
            if (profileType != this.mSelectedProfileType || this.mSelectedTabletType != SocialTabletType.CHAT) {
                this.mBinding.sideNavProfileSwitcherBadge.setActivated(true);
                OCDropdown<ProfileItem> oCDropdown = this.mProfileSwitcherDropdown;
                if (profileType == ProfileType.FACEBOOK) {
                    profileItem = this.mFBProfile;
                } else {
                    profileItem = this.mOCProfile;
                }
                oCDropdown.setBadgedItem(profileItem);
            }
        }
    }

    private void setProfileSwitcherButton() {
        if (this.mFBProfile == null || !this.mIsFBLinked.isPresent() || !this.mIsFBLinked.get().booleanValue()) {
            this.mBinding.sideNavProfileSwitcher.setOnClickListener(null);
            return;
        }
        this.mBinding.sideNavProfileSwitcher.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.common.socialtablet.navbar.$$Lambda$SocialTabletSideNav$jLzFAF96DX8AF38EGWTc89zAJXk2 */

            public final void onClick(View view) {
                SocialTabletSideNav.this.lambda$setProfileSwitcherButton$14$SocialTabletSideNav(view);
            }
        });
        this.mBinding.sideNavProfileSwitcherBorder.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.common.socialtablet.navbar.$$Lambda$SocialTabletSideNav$vWYDP7NUvvLvlK3dFOI9tLwmq6M2 */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return SocialTabletSideNav.this.lambda$setProfileSwitcherButton$15$SocialTabletSideNav(view, motionEvent);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setProfileSwitcherItems() {
        ProfileItem profileItem;
        ProfileItem profileItem2;
        int i;
        ProfileItem profileItem3 = this.mFBProfile;
        if ((profileItem3 != null || this.mOCProfile != null) && this.mProfileSwitcherDropdown != null) {
            ArrayList arrayList = new ArrayList();
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            HashMap hashMap3 = new HashMap();
            HashMap hashMap4 = new HashMap();
            if (profileItem3 != null) {
                arrayList.add(profileItem3);
                hashMap.put(profileItem3, profileItem3.mUsername);
                ProfileItem profileItem4 = this.mFBProfile;
                hashMap3.put(profileItem4, profileItem4.mProfilePic);
                hashMap4.put(this.mFBProfile, Integer.valueOf((int) R.drawable.oc_icon_facebook_filled_24_d2d2d2));
                if (!this.mIsMessengerAuthenticated.isPresent()) {
                    profileItem2 = this.mFBProfile;
                    i = R.string.anytime_tablet_messenger_profile_switcher_default;
                } else if (this.mIsMessengerAuthenticated.get().booleanValue()) {
                    profileItem2 = this.mFBProfile;
                    i = profileItem2.mProfileType.getStringId();
                } else {
                    profileItem2 = this.mFBProfile;
                    i = R.string.anytime_tablet_messenger_profile_switcher_sign_in;
                }
                hashMap2.put(profileItem2, Integer.valueOf(i));
            }
            ProfileItem profileItem5 = this.mOCProfile;
            if (profileItem5 != null) {
                arrayList.add(profileItem5);
                hashMap.put(profileItem5, profileItem5.mUsername);
                ProfileItem profileItem6 = this.mOCProfile;
                hashMap2.put(profileItem6, Integer.valueOf(profileItem6.mProfileType.getStringId()));
                ProfileItem profileItem7 = this.mOCProfile;
                hashMap3.put(profileItem7, profileItem7.mProfilePic);
                hashMap4.put(this.mOCProfile, Integer.valueOf((int) R.drawable.oc_icon_oculus_filled_24_d2d2d2));
            }
            this.mProfileSwitcherDropdown.setItems(arrayList);
            this.mProfileSwitcherDropdown.setTitleMap(hashMap);
            this.mProfileSwitcherDropdown.setSubtitleMap(hashMap2);
            this.mProfileSwitcherDropdown.setIconMap(hashMap3);
            this.mProfileSwitcherDropdown.setRightIconMap(hashMap4);
            ProfileType profileType = this.mSelectedProfileType;
            if ((profileType == ProfileType.FACEBOOK && (profileItem = this.mFBProfile) != null) || (profileType == ProfileType.OCULUS && (profileItem = this.mOCProfile) != null)) {
                this.mProfileSwitcherDropdown.setSelectedItem(profileItem);
            }
            this.mBinding.sideNavProfileSwitcher.setBackground(this.mNavBarProfilePic);
        }
    }

    public void destroy() {
        this.mFBProfileFetcher.destroy();
        this.mOCProfileFetcher.destroy();
        this.mFBProfileFetcher = null;
        this.mOCProfileFetcher = null;
        this.mFBProfile = null;
        this.mOCProfile = null;
        this.mSelectedTabletType = null;
        this.mSelectedProfileType = null;
        this.mIsMessengerAuthenticated = null;
        this.mBinding.sideNavProfileSwitcher.setOnClickListener(null);
        this.mBinding.sideNavProfileSwitcherBorder.setOnHoverListener(null);
        SocialTabletSideNavBinding socialTabletSideNavBinding = this.mBinding;
        OCButton oCButton = socialTabletSideNavBinding.sideNavChatButton;
        oCButton.setOnClickListener(null);
        oCButton.mEventHandler = null;
        OCButton oCButton2 = socialTabletSideNavBinding.sideNavPeopleButton;
        oCButton2.setOnClickListener(null);
        oCButton2.mEventHandler = null;
        OCButton oCButton3 = socialTabletSideNavBinding.sideNavSettingsButton;
        oCButton3.setOnClickListener(null);
        oCButton3.mEventHandler = null;
        OCDropdown<ProfileItem> oCDropdown = this.mProfileSwitcherDropdown;
        if (oCDropdown != null) {
            oCDropdown.setIconMap(new HashMap());
            this.mProfileSwitcherDropdown.setItems(new ArrayList());
            OCDropdown<ProfileItem> oCDropdown2 = this.mProfileSwitcherDropdown;
            oCDropdown2.setEventHandler(null);
            oCDropdown2.dismiss();
            this.mProfileSwitcherDropdown = null;
        }
        this.mProfileSwitcherTooltip = null;
        this.mPanelApp.getImageHandler().unloadTarget(this.mFBProfilePicTarget);
        this.mPanelApp.getImageHandler().unloadTarget(this.mOCProfilePicTarget);
        this.mFBProfilePicTarget = null;
        this.mOCProfilePicTarget = null;
        this.mBinding.sideNavProfileSwitcher.setBackground(null);
        this.mPanelApp.getImageHandler().unloadTarget(this.mNavBarProfilePicTarget);
        this.mNavBarProfilePicTarget = null;
        this.mNavBarProfilePic = null;
        this.mService.unregisterDataSetListener(this);
        getContext().unregisterReceiver(this.mNotificationBroadcastReceiver);
        this.mNotificationBroadcastReceiver = null;
        this.mPanelApp.getFBLinkingFetcher().unregisterFBLinkedStatusListener(this.mLinkedStatusListener);
        this.mLinkedStatusListener = null;
    }

    public /* synthetic */ void lambda$fetchInitialNotifications$1$SocialTabletSideNav(StatusBarNotification statusBarNotification) {
        this.mNotifKeys.add(statusBarNotification.getKey());
    }

    public /* synthetic */ void lambda$fetchProfiles$6$SocialTabletSideNav(String str, String str2) {
        ProfileType profileType = ProfileType.FACEBOOK;
        this.mFBProfile = new ProfileItem(str, profileType, null);
        if (this.mFBProfilePicTarget == null) {
            this.mFBProfilePicTarget = getProfilePicTarget(profileType);
        }
        this.mPanelApp.getImageHandler().loadCircleCroppedToTarget(str2, this.mFBProfilePicTarget);
        if (this.mSelectedProfileType == profileType) {
            this.mPanelApp.getImageHandler().loadCircleCroppedToTarget(str2, this.mNavBarProfilePicTarget);
        }
        setProfileSwitcherButton();
        setProfileSwitcherItems();
    }

    public /* synthetic */ void lambda$fetchProfiles$7$SocialTabletSideNav(String str, String str2) {
        ProfileType profileType = ProfileType.OCULUS;
        this.mOCProfile = new ProfileItem(str, profileType, null);
        if (this.mOCProfilePicTarget == null) {
            this.mOCProfilePicTarget = getProfilePicTarget(profileType);
        }
        this.mPanelApp.getImageHandler().loadCircleCroppedToTarget(str2, this.mOCProfilePicTarget);
        if (this.mSelectedProfileType == profileType) {
            this.mPanelApp.getImageHandler().loadCircleCroppedToTarget(str2, this.mNavBarProfilePicTarget);
        }
        setProfileSwitcherItems();
    }

    public /* synthetic */ Object lambda$initializeProfileSwitcher$10$SocialTabletSideNav(ProfileItem profileItem) {
        SocialTabletPanelApp socialTabletPanelApp;
        ClickEventButtonId clickEventButtonId;
        ProfileType profileType = profileItem.mProfileType;
        if (profileType != this.mSelectedProfileType) {
            if (profileType == ProfileType.FACEBOOK) {
                socialTabletPanelApp = this.mPanelApp;
                clickEventButtonId = ClickEventButtonId.AUIV2_MESSENGER_PROFILE_SWITCHER_FB_PROFILE;
            } else {
                if (profileType == ProfileType.OCULUS) {
                    socialTabletPanelApp = this.mPanelApp;
                    clickEventButtonId = ClickEventButtonId.AUIV2_MESSENGER_PROFILE_SWITCHER_OC_PROFILE;
                }
                loadSelectedPanelApp(profileItem.mProfileType);
            }
            socialTabletPanelApp.logButtonClick(clickEventButtonId, SurfaceType.SIDE_NAV);
            loadSelectedPanelApp(profileItem.mProfileType);
        }
        return null;
    }

    public /* synthetic */ void lambda$initializeProfileSwitcher$11$SocialTabletSideNav() {
        this.mBinding.sideNavProfileSwitcherBorder.setSelected(false);
    }

    public /* synthetic */ void lambda$initializeProfileSwitcher$12$SocialTabletSideNav() {
        if (EducationTooltipUtil.shouldShowTooltip(EducationTooltipUtil.EducationTooltipType.PROFILE_SWITCHER)) {
            configureProfileSwitcherTooltip();
            this.mProfileSwitcherTooltip.show();
            if (MessengerVrAccountsContentProviderClient.hasSeenNewUserAuthenticationDialog(getContext())) {
                EducationTooltipUtil.setTooltipPrefKey(EducationTooltipUtil.EducationTooltipType.PROFILE_SWITCHER, true);
            }
        }
    }

    public /* synthetic */ void lambda$initializeTabletSwitcher$2$SocialTabletSideNav(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_SIDE_NAV_CHAT_ICON, SurfaceType.SIDE_NAV);
        if (this.mSelectedTabletType != SocialTabletType.CHAT && this.mIsFBLinked.isPresent()) {
            if (!this.mIsFBLinked.get().booleanValue()) {
                showUpsell();
            } else {
                ((AndroidPanelApp) this.mPanelApp).mFrameCommandChannel.launch(getChatPath(this.mSelectedProfileType), null);
            }
        }
    }

    public /* synthetic */ void lambda$initializeTabletSwitcher$3$SocialTabletSideNav(View view) {
        if (this.mSelectedTabletType != SocialTabletType.PEOPLE) {
            ((AndroidPanelApp) this.mPanelApp).mFrameCommandChannel.launch(getPeoplePath(this.mSelectedProfileType), null);
            this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_SIDE_NAV_PEOPLE_ICON, SurfaceType.SIDE_NAV);
        }
    }

    public /* synthetic */ void lambda$initializeTabletSwitcher$4$SocialTabletSideNav(View view) {
        SocialTabletType socialTabletType = this.mSelectedTabletType;
        SocialTabletType socialTabletType2 = SocialTabletType.SETTINGS;
        if (socialTabletType != socialTabletType2) {
            this.mSelectedTabletType = socialTabletType2;
            ((AndroidPanelApp) this.mPanelApp).mFrameCommandChannel.launch(SystemUXRoute.AUI_SOCIAL_SETTINGS.path(), null);
            this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_SIDE_NAV_SETTINGS_ICON, SurfaceType.SIDE_NAV);
        }
    }

    public /* synthetic */ void lambda$setProfileSwitcherButton$14$SocialTabletSideNav(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_MESSENGER_PROFILE_SWITCHER_BUTTON, SurfaceType.SIDE_NAV);
        if (this.mProfileSwitcherDropdown != null) {
            this.mPanelApp.onButtonClick();
            OCDropdown<ProfileItem> oCDropdown = this.mProfileSwitcherDropdown;
            Context context = getContext();
            oCDropdown.toggle(view, (int) context.getResources().getDimension(R.dimen.profile_switcher_dropdown_x_offset), (int) context.getResources().getDimension(R.dimen.profile_switcher_dropdown_y_offset));
            this.mBinding.sideNavProfileSwitcherBorder.setSelected(true);
        }
    }

    @Override // com.oculus.notifications.NotificationDataSetListener
    public void onDataSetChanged() {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        for (StatusBarNotification statusBarNotification : this.mService.getAllPersistentNotifications()) {
            if (!this.mNotifKeys.contains(statusBarNotification.getKey())) {
                if (SocialNotificationUtils.isFBMessengerNotification(statusBarNotification)) {
                    hashSet2.add(statusBarNotification.getKey());
                } else if (SocialNotificationUtils.isOculusChatsNotification(statusBarNotification)) {
                    hashSet.add(statusBarNotification.getKey());
                }
            }
        }
        if (!hashSet2.isEmpty()) {
            this.mNotifKeys.addAll(hashSet2);
            setProfileSwitcherBadge(ProfileType.FACEBOOK);
        }
        if (!hashSet.isEmpty()) {
            this.mNotifKeys.addAll(hashSet);
            setProfileSwitcherBadge(ProfileType.OCULUS);
        }
    }

    public void showUpsell() {
        ((AndroidPanelApp) this.mPanelApp).mFrameCommandChannel.launch(SystemUXRoute.SOCIAL.path(), new Uri.Builder().encodedPath("/fb-connect/").appendPath("social_tablet_side_nav").appendQueryParameter("action", FB_UPSELL_ACTION_PARAM).appendQueryParameter("container", "social_tablet_side_nav").appendQueryParameter("entrypoint", FB_UPSELL_ENTRYPOINT_PARAM).appendQueryParameter("mustInteract", "true").appendQueryParameter("product", FB_UPSELL_PRODUCT_PARAM).build().toString());
    }

    public SocialTabletSideNav(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOCProfileFetcher = new OCProfileFetcher(context);
        this.mFBProfileFetcher = new FBProfileFetcher(context);
        this.mService = NotificationDataSetService.Loader.getInstance();
        this.mIsMessengerAuthenticated = Optional.empty();
        this.mIsFBLinked = Optional.empty();
    }

    private void configureProfileSwitcherTooltip() {
        Resources resources;
        int i;
        Context context = getContext();
        EducationTooltip educationTooltip = new EducationTooltip(context);
        this.mProfileSwitcherTooltip = educationTooltip;
        ProfileType profileType = this.mSelectedProfileType;
        ProfileType profileType2 = ProfileType.FACEBOOK;
        int i2 = R.string.profile_switcher_tooltip_text_oc;
        if (profileType == profileType2) {
            i2 = R.string.profile_switcher_tooltip_text_fb;
        }
        educationTooltip.configureTooltip(context.getString(i2), OCTooltip.OCTooltipPosition.RIGHT, $$Lambda$SocialTabletSideNav$0GDizf8El7Pw3tLtN3CqL3YnJBo2.INSTANCE, this.mPanelApp, this.mBinding.sideNavProfileSwitcher);
        this.mProfileSwitcherTooltip.setDismissButtonVisibility(true);
        EducationTooltip educationTooltip2 = this.mProfileSwitcherTooltip;
        if (this.mSelectedProfileType == profileType2) {
            resources = context.getResources();
            i = R.dimen.profile_switcher_tooltip_custom_width_fb;
        } else {
            resources = context.getResources();
            i = R.dimen.profile_switcher_tooltip_custom_width_oc;
        }
        educationTooltip2.setTextViewCustomWidth(resources.getDimensionPixelSize(i));
        this.mProfileSwitcherTooltip.setTextViewCustomMaxLines(4);
    }

    private AnonymousClass1gU<Bitmap> getNavBarProfilePicTarget() {
        Context context = getContext();
        return new AnonymousClass1gU<Bitmap>((int) context.getResources().getDimension(R.dimen.anytime_tablet_common_rectangular_button_height_v2), (int) context.getResources().getDimension(R.dimen.anytime_tablet_common_rectangular_button_height_v2)) {
            /* class com.oculus.common.socialtablet.navbar.SocialTabletSideNav.AnonymousClass2 */

            private void setNavBarProfilePic(@Nullable Drawable drawable) {
                SocialTabletSideNav socialTabletSideNav = SocialTabletSideNav.this;
                socialTabletSideNav.mNavBarProfilePic = drawable;
                socialTabletSideNav.setProfileSwitcherItems();
            }

            @Override // X.AbstractC08781fH
            public void onLoadCleared(@Nullable Drawable drawable) {
                setNavBarProfilePic(drawable);
            }

            @Override // X.AbstractC08781fH, X.AnonymousClass1gU
            public void onLoadFailed(@Nullable Drawable drawable) {
                setNavBarProfilePic(drawable);
            }

            @Override // X.AbstractC08781fH, X.AnonymousClass1gU
            public void onLoadStarted(@Nullable Drawable drawable) {
                setNavBarProfilePic(drawable);
            }

            public void onResourceReady(@NonNull Bitmap bitmap, @Nullable AbstractC08911fj<? super Bitmap> r4) {
                setNavBarProfilePic(new BitmapDrawable(SocialTabletSideNav.this.getContext().getResources(), bitmap));
            }

            @Override // X.AbstractC08781fH
            public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable AbstractC08911fj r2) {
                onResourceReady((Bitmap) obj, (AbstractC08911fj<? super Bitmap>) r2);
            }
        };
    }

    private AnonymousClass1gU<Bitmap> getProfilePicTarget(final ProfileType profileType) {
        Context context = getContext();
        return new AnonymousClass1gU<Bitmap>((int) context.getResources().getDimension(R.dimen.messenger_tablet_message_bubble_profile_picture_diameter), (int) context.getResources().getDimension(R.dimen.messenger_tablet_message_bubble_profile_picture_diameter)) {
            /* class com.oculus.common.socialtablet.navbar.SocialTabletSideNav.AnonymousClass1 */

            private void setProfilePicDrawable(@Nullable Drawable drawable) {
                ProfileItem profileItem;
                ProfileType profileType = profileType;
                if ((profileType == ProfileType.FACEBOOK && (profileItem = SocialTabletSideNav.this.mFBProfile) != null) || (profileType == ProfileType.OCULUS && (profileItem = SocialTabletSideNav.this.mOCProfile) != null)) {
                    profileItem.mProfilePic = drawable;
                }
                SocialTabletSideNav.this.setProfileSwitcherItems();
            }

            @Override // X.AbstractC08781fH
            public void onLoadCleared(@Nullable Drawable drawable) {
                setProfilePicDrawable(drawable);
            }

            @Override // X.AbstractC08781fH, X.AnonymousClass1gU
            public void onLoadFailed(@Nullable Drawable drawable) {
                setProfilePicDrawable(drawable);
            }

            @Override // X.AbstractC08781fH, X.AnonymousClass1gU
            public void onLoadStarted(@Nullable Drawable drawable) {
                setProfilePicDrawable(drawable);
            }

            public void onResourceReady(@NonNull Bitmap bitmap, @Nullable AbstractC08911fj<? super Bitmap> r4) {
                setProfilePicDrawable(new BitmapDrawable(SocialTabletSideNav.this.getContext().getResources(), bitmap));
            }

            @Override // X.AbstractC08781fH
            public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable AbstractC08911fj r2) {
                onResourceReady((Bitmap) obj, (AbstractC08911fj<? super Bitmap>) r2);
            }
        };
    }

    public void initialize(SocialTabletPanelApp socialTabletPanelApp, SocialTabletType socialTabletType, ProfileType profileType) {
        Context context = getContext();
        boolean z = true;
        SocialTabletSideNavBinding inflate = SocialTabletSideNavBinding.inflate(LayoutInflater.from(context), this, true);
        this.mBinding = inflate;
        this.mPanelApp = socialTabletPanelApp;
        this.mSelectedTabletType = socialTabletType;
        this.mSelectedProfileType = profileType;
        SocialTabletSideNavViewModel socialTabletSideNavViewModel = new SocialTabletSideNavViewModel();
        this.mViewModel = socialTabletSideNavViewModel;
        inflate.setSocialTabletSideNavViewModel(socialTabletSideNavViewModel);
        this.mViewModel.setProfileType(this.mSelectedProfileType);
        SocialTabletSideNavViewModel socialTabletSideNavViewModel2 = this.mViewModel;
        if (socialTabletType != SocialTabletType.REAUTH) {
            z = false;
        }
        socialTabletSideNavViewModel2.setIsHidingNavElements(z);
        fetchProfiles();
        fetchMessengerAuthState();
        initializeProfileSwitcher();
        initializeTabletSwitcher();
        this.mService.connectToProvider(context);
        this.mService.registerDataSetListener(this);
        fetchInitialNotifications();
        initializeNotificationBroadcastReceiver();
    }

    public /* synthetic */ void lambda$fetchMessengerAuthState$8$SocialTabletSideNav(boolean z) {
        this.mIsMessengerAuthenticated = Optional.of(Boolean.valueOf(z));
        setProfileSwitcherItems();
    }

    public /* synthetic */ Object lambda$fetchMessengerAuthState$9$SocialTabletSideNav() throws Exception {
        UiThreadExecutor.getInstance().execute(new Runnable(MessengerVrAccountsContentProviderClient.isMessengerAuthenticated(getContext())) {
            /* class com.oculus.common.socialtablet.navbar.$$Lambda$SocialTabletSideNav$3VZoyaVJDd935E3rVcAQ33DXdU2 */
            public final /* synthetic */ boolean f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                SocialTabletSideNav.this.lambda$fetchMessengerAuthState$8$SocialTabletSideNav(this.f$1);
            }
        });
        return null;
    }

    public /* synthetic */ void lambda$fetchProfiles$5$SocialTabletSideNav(boolean z) {
        this.mIsFBLinked = Optional.of(Boolean.valueOf(z));
        setProfileSwitcherButton();
    }

    public /* synthetic */ boolean lambda$setProfileSwitcherButton$15$SocialTabletSideNav(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 9) {
            this.mPanelApp.onButtonEnter();
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 7 || actionMasked == 9) {
            this.mBinding.sideNavProfileSwitcher.setAlpha(0.6f);
            this.mBinding.sideNavProfileSwitcherBorder.setHovered(true);
            return false;
        }
        if (actionMasked == 10) {
            this.mBinding.sideNavProfileSwitcher.setAlpha(1.0f);
            this.mBinding.sideNavProfileSwitcherBorder.setHovered(false);
        }
        return false;
    }
}
