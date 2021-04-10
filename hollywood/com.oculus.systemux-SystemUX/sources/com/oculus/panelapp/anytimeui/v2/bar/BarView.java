package com.oculus.panelapp.anytimeui.v2.bar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.horizoncontent.social.SocialParty;
import com.oculus.horizoncontent.social.SocialUser;
import com.oculus.ocui.OCButton;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.databinding.AnytimeBarDebugViewV2Binding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeBarNavigationButtonV2Binding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeBarViewV2Binding;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.bar.BarViewModel;
import com.oculus.panelapp.anytimeui.v2.bar.status.ControllerBatteryState;
import com.oculus.panelapp.anytimeui.v2.bar.status.StatusView;
import com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel;
import com.oculus.panelapp.anytimeui.v2.bar.status.WifiState;
import com.oculus.panelapp.anytimeui.v2.nux.OnboardingTutorial;
import com.oculus.panelapp.anytimeui.v2.tablet.common.Tablet;
import com.oculus.panelapp.anytimeui.v2.tablet.destinationui.DestinationUIViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.NotificationsHelper;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.AbstractVRNotification;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.INotificationProxyListener;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.NativeNotification;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.NotificationDatasetProxy;
import com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileDataObserver;
import com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsViewModel;
import com.oculus.panelapp.social.SocialDataObserver;
import com.oculus.panelapp.social.SocialPartyObserver;
import com.oculus.panelapp.social.SocialViewModel;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.notifications.NotificationsType;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.DensityUtils;
import com.oculus.vrshell.panels.InputFrame;
import com.oculus.vrshell.util.ThreadExecutor;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public final class BarView extends ConstraintLayout implements AndroidPanelApp.PanelFrameCallback, INotificationProxyListener {
    private static final float NAVIGATION_BUTTON_SELECTED_ALPHA = 1.0f;
    private static final float NAVIGATION_BUTTON_UNSELECTED_ALPHA = 0.6f;
    private static final String TAG = LoggingUtil.tag(BarView.class);
    private ActiveCallBarFull mActiveCallBarFull;
    private ActiveCallBarSimple mActiveCallBarSimple;
    private BarViewModel mBarViewModel;
    private AnytimeBarViewV2Binding mBinding;
    private Tablet mCurrentTablet;
    private boolean mDebugBarPinned;
    private BarViewModel.DebugBarPinnedChangeListener mDebugBarPinnedChangeListener;
    private DebugBarView mDebugBarView;
    private DestinationUIViewModel mDestinationUIViewModel;
    private String mLayerName;
    private NotificationDatasetProxy mNotificationsProxy;
    @Nullable
    private OnboardingTutorial mOnboardingTutorial;
    @Nullable
    private OnboardingTutorial.OnboardingTutorialChangeListener mOnboardingTutorialChangeListener;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private String mProfilePhotoURL = "";
    private ProfileViewModel mProfileViewModel;
    private OCButton mSelectedButton;
    private ImageView mSelectedIcon;
    private OCTextView mSelectedLabel;
    private SettingsViewModel mSettingsViewModel;
    private SocialViewModel mSocialViewModel;
    private StatusView mStatusView;
    private StatusViewModel mStatusViewModel;
    private StatusViewModel.WifiStateListener mWifiStateListener;

    public void applyActiveState(View view, boolean z) {
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.INotificationProxyListener
    public void onNotificationRemoved(Class<? extends IBaseVRNotification> cls, IBaseVRNotification iBaseVRNotification) {
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.INotificationProxyListener
    public void onNotificationUpdated(Class<? extends IBaseVRNotification> cls, IBaseVRNotification iBaseVRNotification) {
    }

    public BarView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initialize(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, String str) {
        Log.d(TAG, "Initializing BarView");
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mPanelApp.addPanelFrameCallback(this);
        this.mLayerName = str;
        this.mStatusView = this.mBinding.statusBarV2;
        this.mStatusView.initialize(this.mPanelApp);
        this.mBarViewModel = anytimeUIAndroidPanelAppV2.acquireBarViewModel();
        this.mSettingsViewModel = anytimeUIAndroidPanelAppV2.acquireSettingsViewModel();
        this.mProfileViewModel = anytimeUIAndroidPanelAppV2.acquireProfileViewModel();
        this.mSocialViewModel = anytimeUIAndroidPanelAppV2.acquireSocialViewModel();
        this.mDestinationUIViewModel = anytimeUIAndroidPanelAppV2.acquireDestinationUIViewModel();
        this.mStatusViewModel = anytimeUIAndroidPanelAppV2.acquireStatusViewModel();
        initializeOnboardingTutorial();
        initializeWifiStateListener();
        this.mBinding.setBarViewModel(this.mBarViewModel);
        this.mBinding.setDestinationUIViewModel(this.mDestinationUIViewModel);
        this.mBinding.setSocialViewModel(this.mSocialViewModel);
        if (this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_ACTIVE_CALL_BAR)) {
            initializeActiveCallBar();
        }
        this.mProfileViewModel.registerObserver(new ProfileDataObserver() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$BarView$E3sXqKwtB_guUwCKgdJKyFf1TCg */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileDataObserver
            public final void updateProfile() {
                BarView.this.lambda$initialize$7$BarView();
            }
        });
        this.mSocialViewModel.registerObserver(new SocialDataObserver() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.BarView.AnonymousClass1 */

            @Override // com.oculus.panelapp.social.SocialDataObserver
            public void onEnoughDataFetched() {
            }

            @Override // com.oculus.panelapp.social.SocialDataObserver
            public void updateFriendsList(List<SocialUser> list, List<SocialUser> list2, List<SocialParty> list3, SocialParty socialParty) {
            }

            @Override // com.oculus.panelapp.social.SocialDataObserver
            public void updateSocialViewer() {
                BarView.this.lambda$initialize$7$BarView();
            }
        });
        initializeDebugBar();
        initializeProfileNavigationButton();
        initializeAppsNavigationButton();
        initializeMessengerNavigationButton();
        initializeNotificationsNavigationButton();
        initializeSharingNavigationButton();
        initializeSettingsNavigationButton();
        initializeDestinationUINavigationButton();
        initializeSocialNavigationButton();
        initializeStoreNavigationButton();
        onTabletShown(this.mPanelApp.getDefaultTablet());
        setWillNotDraw(false);
    }

    public void onShow() {
        this.mStatusView.onShow();
        ActiveCallBarSimple activeCallBarSimple = this.mActiveCallBarSimple;
        if (activeCallBarSimple != null) {
            activeCallBarSimple.onShow();
        }
        ActiveCallBarFull activeCallBarFull = this.mActiveCallBarFull;
        if (activeCallBarFull != null) {
            activeCallBarFull.onShow();
        }
    }

    public void onHide() {
        this.mStatusView.onHide();
        ActiveCallBarSimple activeCallBarSimple = this.mActiveCallBarSimple;
        if (activeCallBarSimple != null) {
            activeCallBarSimple.onHide();
        }
        ActiveCallBarFull activeCallBarFull = this.mActiveCallBarFull;
        if (activeCallBarFull != null) {
            activeCallBarFull.onHide();
        }
    }

    private void initializeActiveCallBar() {
        Log.d(TAG, "Initializing active call bar");
        if (this.mPanelApp.isGKEnabled(Gatekeeper.AUI_V2_ACTIVE_CALL_BAR_ENHANCED)) {
            Log.d(TAG, "Using full active call bar");
            this.mActiveCallBarFull = this.mBinding.activeCallBarFull;
            this.mActiveCallBarFull.initialize(this.mPanelApp);
            this.mSocialViewModel.registerPartyObserver(new SocialPartyObserver() {
                /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$BarView$7anp0jV8wtvSVXx8PBscFxjvjk */

                @Override // com.oculus.panelapp.social.SocialPartyObserver
                public final void onUpdateParty(SocialParty socialParty) {
                    BarView.this.lambda$initializeActiveCallBar$8$BarView(socialParty);
                }
            });
        } else {
            Log.d(TAG, "Using simple active call bar");
            this.mActiveCallBarSimple = this.mBinding.activeCallBarSimple;
            this.mActiveCallBarSimple.initialize(this.mPanelApp, this.mBinding.activeCallBarSimpleExpandedTapTarget);
            this.mSocialViewModel.registerPartyObserver(new SocialPartyObserver() {
                /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$BarView$4kb324JGMGR1yif4JxIJ6PZuflo */

                @Override // com.oculus.panelapp.social.SocialPartyObserver
                public final void onUpdateParty(SocialParty socialParty) {
                    BarView.this.lambda$initializeActiveCallBar$9$BarView(socialParty);
                }
            });
        }
        Log.d(TAG, "Initialized active call bar");
    }

    public /* synthetic */ void lambda$initializeActiveCallBar$8$BarView(SocialParty socialParty) {
        this.mActiveCallBarFull.updateUI();
    }

    public /* synthetic */ void lambda$initializeActiveCallBar$9$BarView(SocialParty socialParty) {
        this.mActiveCallBarSimple.updateUI();
    }

    private void initializeOnboardingTutorial() {
        OnboardingTutorial onboardingTutorial = this.mPanelApp.getOnboardingTutorial();
        if (onboardingTutorial.isActive()) {
            this.mOnboardingTutorial = onboardingTutorial;
            this.mOnboardingTutorialChangeListener = new OnboardingTutorial.OnboardingTutorialChangeListener() {
                /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$BarView$EsINwdQxtPu6NKy8oe7oqtS5Ffc */

                @Override // com.oculus.panelapp.anytimeui.v2.nux.OnboardingTutorial.OnboardingTutorialChangeListener
                public final void onStepChanged(OnboardingTutorial.Step step) {
                    BarView.this.lambda$initializeOnboardingTutorial$10$BarView(step);
                }
            };
            this.mOnboardingTutorial.addOnChangeListener(this.mOnboardingTutorialChangeListener);
            this.mOnboardingTutorialChangeListener.onStepChanged(this.mOnboardingTutorial.getCurrentStep());
        }
    }

    public /* synthetic */ void lambda$initializeOnboardingTutorial$10$BarView(OnboardingTutorial.Step step) {
        this.mBarViewModel.setHighlightLibrary(step == OnboardingTutorial.Step.BAR || step == OnboardingTutorial.Step.HIGHLIGHT_APPS);
        disableBarViewForTutorial();
    }

    private void disableBarViewForTutorial() {
        OnboardingTutorial onboardingTutorial = this.mOnboardingTutorial;
        if (onboardingTutorial != null) {
            boolean z = !onboardingTutorial.isActive();
            this.mBinding.navigationButtonProfile.setClickable(z);
            this.mBinding.navigationButtonMessenger.button.setClickable(z);
            this.mBinding.navigationButtonSharing.button.setClickable(z);
            this.mBinding.navigationButtonNotifications.button.setClickable(z);
            this.mBinding.navigationButtonSettings.button.setClickable(z);
            this.mBinding.navigationButtonSocial.button.setClickable(z);
            this.mBinding.navigationButtonStoreLeft.button.setClickable(z);
            this.mBinding.navigationButtonStoreRight.button.setClickable(z);
            this.mBinding.navigationButtonDestinationUi.button.setClickable(z);
        }
    }

    private void initializeDebugBar() {
        Log.d(TAG, "Initializing debug bar");
        if (this.mPanelApp.isDebugBarEnabled()) {
            int dipToPixelsInt = DensityUtils.dipToPixelsInt(getContext().getResources().getDimension(R.dimen.anytime_bar_debug_bar_content_width), getContext().getResources().getDisplayMetrics());
            ((ViewGroup.MarginLayoutParams) this.mBinding.leftEdge.getLayoutParams()).leftMargin = dipToPixelsInt;
            ((ViewGroup.MarginLayoutParams) this.mBinding.rightEdge.getLayoutParams()).rightMargin = dipToPixelsInt;
            this.mBinding.leftEdge.setOnHoverListener(new View.OnHoverListener() {
                /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$BarView$MmMk4TrG1fhbQ5ywUPygAD1ipjI */

                public final boolean onHover(View view, MotionEvent motionEvent) {
                    return BarView.this.lambda$initializeDebugBar$11$BarView(view, motionEvent);
                }
            });
            this.mDebugBarPinnedChangeListener = new BarViewModel.DebugBarPinnedChangeListener() {
                /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$BarView$ZAUSi5V82kYd2kB5x2RaiFx_w */

                @Override // com.oculus.panelapp.anytimeui.v2.bar.BarViewModel.DebugBarPinnedChangeListener
                public final void onDebugBarPinnedChanged(boolean z) {
                    BarView.this.lambda$initializeDebugBar$12$BarView(z);
                }
            };
            this.mBarViewModel.addDebugBarPinnedChangeListener(this.mDebugBarPinnedChangeListener);
            if (this.mBarViewModel.getDebugBarPinned()) {
                this.mDebugBarPinnedChangeListener.onDebugBarPinnedChanged(true);
            }
        }
        Log.d(TAG, "Initialized debug bar");
    }

    public /* synthetic */ boolean lambda$initializeDebugBar$11$BarView(View view, MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() != 9) {
            return false;
        }
        addDebugBar();
        return false;
    }

    public /* synthetic */ void lambda$initializeDebugBar$12$BarView(boolean z) {
        if (z) {
            addDebugBar();
            this.mDebugBarPinned = true;
            return;
        }
        animateDebugBarSlideOut();
        this.mDebugBarPinned = false;
    }

    private void addDebugBar() {
        if (this.mDebugBarView == null) {
            AnytimeBarDebugViewV2Binding inflate = AnytimeBarDebugViewV2Binding.inflate(LayoutInflater.from(getContext()), this, true);
            this.mDebugBarView = (DebugBarView) inflate.getRoot();
            this.mDebugBarView.initialize(this.mPanelApp, inflate);
            this.mPanelApp.getSystemTooltipController().initializeTooltipsOnSubtree(this.mDebugBarView, this.mLayerName, R.id.tooltip_text, R.id.tooltip_subtext, R.id.tooltip_data);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(this);
            constraintSet.connect(this.mDebugBarView.getId(), 7, this.mBinding.statusBarV2.getId(), 6);
            constraintSet.connect(this.mDebugBarView.getId(), 3, 0, 3);
            constraintSet.applyTo(this);
            animateDebugBarSlideIn();
        }
    }

    private void animateDebugBarSlideIn() {
        if (this.mDebugBarView != null) {
            this.mDebugBarView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.anytime_bar_debug_slide_in));
        }
    }

    private void animateDebugBarSlideOut() {
        DebugBarView debugBarView = this.mDebugBarView;
        if (debugBarView != null && debugBarView.getAnimation() == null) {
            Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.anytime_bar_debug_slide_out);
            loadAnimation.setAnimationListener(new Animation.AnimationListener() {
                /* class com.oculus.panelapp.anytimeui.v2.bar.BarView.AnonymousClass2 */

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    BarView.this.removeDebugBar();
                }
            });
            this.mDebugBarView.startAnimation(loadAnimation);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void removeDebugBar() {
        if (this.mDebugBarView != null) {
            this.mPanelApp.getSystemTooltipController().removeTooltipsOnSubtree(this.mDebugBarView);
            this.mDebugBarView.destroy();
            removeView(this.mDebugBarView);
            this.mDebugBarView = null;
        }
    }

    public void setBinding(AnytimeBarViewV2Binding anytimeBarViewV2Binding) {
        this.mBinding = anytimeBarViewV2Binding;
    }

    private void onClickNavigationButton(AnytimeBarNavigationButtonV2Binding anytimeBarNavigationButtonV2Binding, ClickEventButtonId clickEventButtonId) {
        this.mPanelApp.logButtonClick(clickEventButtonId);
        if (anytimeBarNavigationButtonV2Binding.button.isSelected()) {
            this.mPanelApp.actionNavigate(SystemUXRoute.AUI_TABLET_NONE, "");
            clearSelectedNavigationButton();
            return;
        }
        SystemUXRoute route = anytimeBarNavigationButtonV2Binding.getRoute();
        if (route != null) {
            this.mPanelApp.actionNavigate(route, "");
        }
        if (route != SystemUXRoute.STORE) {
            selectNavigationButton(anytimeBarNavigationButtonV2Binding.button, anytimeBarNavigationButtonV2Binding.buttonIcon, anytimeBarNavigationButtonV2Binding.buttonLabel);
            removeBadge(anytimeBarNavigationButtonV2Binding.badge);
        }
    }

    private void applyBadge(View view) {
        view.setActivated(true);
    }

    private void removeBadge(View view) {
        view.setActivated(false);
    }

    private void initializeProfileNavigationButton() {
        Log.d(TAG, "Initializing profile navigation button");
        this.mBinding.navigationButtonProfile.setEventHandler(this.mPanelApp);
        this.mBinding.navigationButtonProfile.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$BarView$xcxpDixGLgdn2E7oZGqHNCk6c */

            public final void onClick(View view) {
                BarView.this.lambda$initializeProfileNavigationButton$13$BarView(view);
            }
        });
        this.mBinding.profileImageView.setOutlineProvider(new ViewOutlineProvider() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.BarView.AnonymousClass3 */

            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), (float) (view.getWidth() / 2));
            }
        });
        this.mBinding.profileImageView.setClipToOutline(true);
        lambda$initialize$7$BarView();
        Log.d(TAG, "Initialized profile navigation button");
    }

    public /* synthetic */ void lambda$initializeProfileNavigationButton$13$BarView(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_BAR_PROFILE);
        if (this.mBinding.navigationButtonProfile.isSelected()) {
            this.mPanelApp.actionNavigate(SystemUXRoute.AUI_TABLET_NONE, "");
            clearSelectedNavigationButton();
            return;
        }
        this.mPanelApp.actionNavigate(SystemUXRoute.AUI_PROFILE, new Uri.Builder().encodedPath(TabletDeepLinkingUriUtil.AUI_SELF_VR_PROFILE_URI).appendQueryParameter("entrypoint", "aui_bar").build().toString());
        selectNavigationButton(this.mBinding.navigationButtonProfile, null, null);
    }

    private void initializeMessengerNavigationButton() {
        Log.d(TAG, "Initializing messenger navigation button");
        this.mBinding.navigationButtonMessenger.button.setEventHandler(this.mPanelApp);
        this.mBinding.navigationButtonMessenger.button.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$BarView$ugyQMhstMz0JJxfjgUhjwQJ0zY */

            public final void onClick(View view) {
                BarView.this.lambda$initializeMessengerNavigationButton$14$BarView(view);
            }
        });
        if (this.mBarViewModel.messengerNavigationButtonVisible() && this.mBarViewModel.isBarButtonsWithLabelsEnabled()) {
            new NavigationButtonLabelAnimation(this.mPanelApp, ClickEventButtonId.AUIV2_BAR_MESSENGER, this.mBinding.navigationButtonMessenger.button, this.mBinding.navigationButtonMessenger.buttonLabel);
        }
        Log.d(TAG, "Initialized messenger navigation button");
    }

    public /* synthetic */ void lambda$initializeMessengerNavigationButton$14$BarView(View view) {
        onClickNavigationButton(this.mBinding.navigationButtonMessenger, ClickEventButtonId.AUIV2_BAR_MESSENGER);
    }

    private void initializeAppsNavigationButton() {
        Log.d(TAG, "Initializing apps navigation button");
        this.mBinding.navigationButtonLibrary.button.setEventHandler(this.mPanelApp);
        this.mBinding.navigationButtonLibrary.button.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$BarView$_jPn0czfCbAEvixCC0cscp2QMk */

            public final void onClick(View view) {
                BarView.this.lambda$initializeAppsNavigationButton$15$BarView(view);
            }
        });
        if (this.mBarViewModel.libraryNavigationButtonVisible() && this.mBarViewModel.isBarButtonsWithLabelsEnabled()) {
            new NavigationButtonLabelAnimation(this.mPanelApp, ClickEventButtonId.AUIV2_BAR_LIBRARY, this.mBinding.navigationButtonLibrary.button, this.mBinding.navigationButtonLibrary.buttonLabel);
        }
        Log.d(TAG, "Initialized apps navigation button");
    }

    public /* synthetic */ void lambda$initializeAppsNavigationButton$15$BarView(View view) {
        onClickNavigationButton(this.mBinding.navigationButtonLibrary, ClickEventButtonId.AUIV2_BAR_LIBRARY);
    }

    private void initializeSocialNavigationButton() {
        Log.d(TAG, "Initializing social navigation button");
        this.mBinding.navigationButtonSocial.button.setEventHandler(this.mPanelApp);
        this.mBinding.navigationButtonSocial.button.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$BarView$j9e7pqDZrL5_8KyNFGAMYUZjz8I */

            public final void onClick(View view) {
                BarView.this.lambda$initializeSocialNavigationButton$16$BarView(view);
            }
        });
        if (this.mBarViewModel.socialNavigationButtonVisible() && this.mBarViewModel.isBarButtonsWithLabelsEnabled()) {
            new NavigationButtonLabelAnimation(this.mPanelApp, ClickEventButtonId.AUIV2_BAR_SOCIAL, this.mBinding.navigationButtonSocial.button, this.mBinding.navigationButtonSocial.buttonLabel);
        }
        Log.d(TAG, "Initialized social navigation button");
    }

    public /* synthetic */ void lambda$initializeSocialNavigationButton$16$BarView(View view) {
        onClickNavigationButton(this.mBinding.navigationButtonSocial, ClickEventButtonId.AUIV2_BAR_SOCIAL);
    }

    private void initializeNotificationsNavigationButton() {
        Log.d(TAG, "Initializing notifications navigation button");
        this.mBinding.navigationButtonNotifications.button.setEventHandler(this.mPanelApp);
        this.mBinding.navigationButtonNotifications.button.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$BarView$RxNZKZKJiQyGd294cxWfOuqMtNI */

            public final void onClick(View view) {
                BarView.this.lambda$initializeNotificationsNavigationButton$17$BarView(view);
            }
        });
        if (this.mBarViewModel.notificationsNavigationButtonVisible() && this.mBarViewModel.isBarButtonsWithLabelsEnabled()) {
            new NavigationButtonLabelAnimation(this.mPanelApp, ClickEventButtonId.AUIV2_BAR_NOTIFICATIONS, this.mBinding.navigationButtonNotifications.button, this.mBinding.navigationButtonNotifications.buttonLabel);
        }
        initializeNotificationsBadge();
        Log.d(TAG, "Initialized notifications navigation button");
    }

    public /* synthetic */ void lambda$initializeNotificationsNavigationButton$17$BarView(View view) {
        onClickNavigationButton(this.mBinding.navigationButtonNotifications, ClickEventButtonId.AUIV2_BAR_NOTIFICATIONS);
    }

    public void initializeNotificationsBadge() {
        if (NotificationDatasetProxy.isInitialized()) {
            this.mNotificationsProxy = NotificationDatasetProxy.getInstance(this.mPanelApp.getContext(), null, null);
            List<IBaseVRNotification> list = this.mNotificationsProxy.listen(this, Collections.emptyList()).get(NotificationsType.ALL);
            if (countUnreadNotifs(list) > 0) {
                applyNotifsBadge();
            }
            if (countUnreadMessageNotifs(list) > 0) {
                applyBadge(this.mBinding.navigationButtonMessenger.badge);
            }
        }
    }

    private void applyNotifsBadge() {
        applyBadge(this.mBinding.navigationButtonNotifications.badge);
    }

    public void removeNotifsBadge() {
        removeBadge(this.mBinding.navigationButtonNotifications.badge);
    }

    public void removeMessengerBadge() {
        if (this.mBinding.navigationButtonMessenger != null) {
            removeBadge(this.mBinding.navigationButtonMessenger.badge);
        }
    }

    private void initializeSharingNavigationButton() {
        Log.d(TAG, "Initializing sharing navigation button");
        this.mBinding.navigationButtonSharing.button.setEventHandler(this.mPanelApp);
        this.mBinding.navigationButtonSharing.button.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$BarView$z2_Bk84etTSQkOHr6oU6eYykdg */

            public final void onClick(View view) {
                BarView.this.lambda$initializeSharingNavigationButton$18$BarView(view);
            }
        });
        if (this.mBarViewModel.isBarButtonsWithLabelsEnabled()) {
            new NavigationButtonLabelAnimation(this.mPanelApp, ClickEventButtonId.AUIV2_BAR_SHARING, this.mBinding.navigationButtonSharing.button, this.mBinding.navigationButtonSharing.buttonLabel);
        }
        Log.d(TAG, "Initialized sharing navigation button");
    }

    public /* synthetic */ void lambda$initializeSharingNavigationButton$18$BarView(View view) {
        onClickNavigationButton(this.mBinding.navigationButtonSharing, ClickEventButtonId.AUIV2_BAR_SHARING);
    }

    private void initializeSettingsNavigationButton() {
        Log.d(TAG, "Initializing settings navigation button");
        this.mBinding.navigationButtonSettings.button.setEventHandler(this.mPanelApp);
        this.mBinding.navigationButtonSettings.button.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$BarView$xRDHWvuAkApWrHr0ds9xbNJYGUA */

            public final void onClick(View view) {
                BarView.this.lambda$initializeSettingsNavigationButton$19$BarView(view);
            }
        });
        if (this.mBarViewModel.isBarButtonsWithLabelsEnabled()) {
            new NavigationButtonLabelAnimation(this.mPanelApp, ClickEventButtonId.AUIV2_BAR_SETTINGS, this.mBinding.navigationButtonSettings.button, this.mBinding.navigationButtonSettings.buttonLabel);
        }
        Log.d(TAG, "Initialized settings navigation button");
    }

    public /* synthetic */ void lambda$initializeSettingsNavigationButton$19$BarView(View view) {
        onClickNavigationButton(this.mBinding.navigationButtonSettings, ClickEventButtonId.AUIV2_BAR_SETTINGS);
    }

    private void initializeStoreNavigationButton() {
        Log.d(TAG, "Initializing store navigation button");
        this.mBinding.navigationButtonStoreRight.button.setEventHandler(this.mPanelApp);
        this.mBinding.navigationButtonStoreRight.button.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$BarView$QSyzA7FsnW4R2Qb19s2bEbMEamY */

            public final void onClick(View view) {
                BarView.this.lambda$initializeStoreNavigationButton$20$BarView(view);
            }
        });
        this.mBinding.navigationButtonStoreLeft.button.setEventHandler(this.mPanelApp);
        this.mBinding.navigationButtonStoreLeft.button.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$BarView$akdHPCve8jWI49zMluIrWaU_qHk */

            public final void onClick(View view) {
                BarView.this.lambda$initializeStoreNavigationButton$21$BarView(view);
            }
        });
        if (this.mBarViewModel.isBarButtonsWithLabelsEnabled()) {
            new NavigationButtonLabelAnimation(this.mPanelApp, ClickEventButtonId.AUIV2_BAR_STORE_RIGHT, this.mBinding.navigationButtonStoreRight.button, this.mBinding.navigationButtonStoreRight.buttonLabel);
            new NavigationButtonLabelAnimation(this.mPanelApp, ClickEventButtonId.AUIV2_BAR_STORE_LEFT, this.mBinding.navigationButtonStoreLeft.button, this.mBinding.navigationButtonStoreLeft.buttonLabel);
        }
        Log.d(TAG, "Initialized store navigation button");
    }

    public /* synthetic */ void lambda$initializeStoreNavigationButton$20$BarView(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_BAR_STORE);
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_BAR_STORE_RIGHT);
        this.mPanelApp.actionNavigate(SystemUXRoute.STORE, "");
        clearSelectedNavigationButton();
    }

    public /* synthetic */ void lambda$initializeStoreNavigationButton$21$BarView(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_BAR_STORE);
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_BAR_STORE_LEFT);
        this.mPanelApp.actionNavigate(SystemUXRoute.STORE, "");
        clearSelectedNavigationButton();
    }

    private void initializeDestinationUINavigationButton() {
        Log.d(TAG, "Initializing destination ui navigation button");
        this.mBinding.navigationButtonDestinationUi.button.setEventHandler(this.mPanelApp);
        this.mBinding.navigationButtonDestinationUi.button.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$BarView$n6ycrqzLXFgMlsg7BGSSBryoZXE */

            public final void onClick(View view) {
                BarView.this.lambda$initializeDestinationUINavigationButton$22$BarView(view);
            }
        });
        AnonymousClass4 r0 = new ViewOutlineProvider() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.BarView.AnonymousClass4 */

            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), (float) DensityUtils.dipToPixelsInt(8.0f, BarView.this.getContext().getResources().getDisplayMetrics()));
            }
        };
        this.mBinding.navigationButtonDestinationUi.appImage.setOutlineProvider(r0);
        this.mBinding.navigationButtonDestinationUi.appImage.setClipToOutline(true);
        this.mBinding.navigationButtonDestinationUi.appScreenshot.setOutlineProvider(r0);
        this.mBinding.navigationButtonDestinationUi.appScreenshot.setClipToOutline(true);
        this.mBinding.navigationButtonDestinationUi.getRoot().setVisibility(0);
        Log.d(TAG, "Initialized destination ui navigation button");
    }

    public /* synthetic */ void lambda$initializeDestinationUINavigationButton$22$BarView(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_BAR_DESTINATION_UI);
        if (this.mBinding.navigationButtonDestinationUi.button.isSelected()) {
            this.mPanelApp.actionNavigate(SystemUXRoute.AUI_TABLET_NONE, "");
            clearSelectedNavigationButton();
            return;
        }
        this.mPanelApp.actionNavigate(SystemUXRoute.PAUSE, "");
        selectNavigationButton(this.mBinding.navigationButtonDestinationUi.button, null, null);
    }

    private void initializeWifiStateListener() {
        if (this.mWifiStateListener == null) {
            this.mWifiStateListener = new StatusViewModel.WifiStateListener() {
                /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$BarView$rO42f_RBtBVdQPcMCIIk4LXG1Y */

                @Override // com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel.WifiStateListener
                public final void onWifiStateChanged() {
                    BarView.this.lambda$initializeWifiStateListener$23$BarView();
                }
            };
            this.mStatusViewModel.addWifiStateListener(this.mWifiStateListener);
        }
    }

    public /* synthetic */ void lambda$initializeWifiStateListener$23$BarView() {
        if (!this.mPanelApp.getSystemUXConfig().isEnterpriseMode) {
            this.mBarViewModel.setOnlinePresenceDotVisible(this.mStatusViewModel.getWifiState().equals(WifiState.CONNECTED));
        }
    }

    private void selectNavigationButton(OCButton oCButton, @Nullable ImageView imageView, @Nullable OCTextView oCTextView) {
        OCButton oCButton2 = this.mSelectedButton;
        if (oCButton2 != null) {
            oCButton2.setSelected(false);
        }
        ImageView imageView2 = this.mSelectedIcon;
        if (imageView2 != null) {
            imageView2.setAlpha(NAVIGATION_BUTTON_UNSELECTED_ALPHA);
        }
        OCTextView oCTextView2 = this.mSelectedLabel;
        if (oCTextView2 != null) {
            oCTextView2.setAlpha(0.0f);
        }
        if (oCButton != null) {
            oCButton.setSelected(true);
        }
        if (imageView != null) {
            imageView.setAlpha(1.0f);
        }
        if (oCTextView != null) {
            if (!this.mBarViewModel.isBarButtonsWithLabelsEnabled() || !oCButton.isHovered()) {
                oCTextView.setAlpha(0.0f);
            } else {
                oCTextView.setAlpha(1.0f);
            }
        }
        this.mSelectedButton = oCButton;
        this.mSelectedIcon = imageView;
        this.mSelectedLabel = oCTextView;
    }

    private void clearSelectedNavigationButton() {
        if (this.mSelectedLabel != null) {
            OCButton oCButton = this.mSelectedButton;
            if (oCButton == null || !oCButton.isHovered() || !this.mBarViewModel.isBarButtonsWithLabelsEnabled()) {
                this.mSelectedLabel.setAlpha(0.0f);
            } else {
                this.mSelectedLabel.setAlpha(NAVIGATION_BUTTON_UNSELECTED_ALPHA);
            }
            this.mSelectedLabel = null;
        }
        OCButton oCButton2 = this.mSelectedButton;
        if (oCButton2 != null) {
            oCButton2.setSelected(false);
            this.mSelectedButton = null;
        }
        ImageView imageView = this.mSelectedIcon;
        if (imageView != null) {
            imageView.setAlpha(NAVIGATION_BUTTON_UNSELECTED_ALPHA);
            this.mSelectedIcon = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* renamed from: updateProfilePhoto */
    public void lambda$initialize$7$BarView() {
        if (this.mPanelApp.getSystemUXConfig().isEnterpriseMode) {
            this.mBinding.profileImageView.setBackgroundColor(-1);
            ((GenericDraweeHierarchy) this.mBinding.profileImageView.getHierarchy()).setPlaceholderImage(R.drawable.enterprise_profile_company_logo);
            this.mBinding.profileOnlinePresenceDotEnterprise.setVisibility(this.mPanelApp.getSystemUXConfig().isEnterpriseAdminModeEnabled ? 0 : 8);
        } else if (shouldUpdateProfilePhoto(this.mSocialViewModel.getProfilePhotoUrl())) {
            this.mProfilePhotoURL = this.mSocialViewModel.getProfilePhotoUrl();
            this.mBinding.profileImageView.setImageURI(this.mProfilePhotoURL);
        }
    }

    public void destroy() {
        OnboardingTutorial.OnboardingTutorialChangeListener onboardingTutorialChangeListener;
        Log.d(TAG, "Destroying BarView");
        this.mStatusView.destroy();
        BarViewModel.DebugBarPinnedChangeListener debugBarPinnedChangeListener = this.mDebugBarPinnedChangeListener;
        if (debugBarPinnedChangeListener != null) {
            this.mBarViewModel.removeDebugBarPinnedChangeListener(debugBarPinnedChangeListener);
        }
        removeDebugBar();
        this.mPanelApp.releaseBarViewModel();
        this.mPanelApp.releaseSettingsViewModel();
        this.mPanelApp.releaseProfileViewModel();
        this.mPanelApp.releaseDestinationUIViewModel();
        this.mPanelApp.releaseSocialViewModel();
        this.mPanelApp.removePanelFrameCallback(this);
        NotificationDatasetProxy notificationDatasetProxy = this.mNotificationsProxy;
        if (notificationDatasetProxy != null) {
            notificationDatasetProxy.removeListener(this);
            this.mNotificationsProxy = null;
        }
        OnboardingTutorial onboardingTutorial = this.mOnboardingTutorial;
        if (!(onboardingTutorial == null || (onboardingTutorialChangeListener = this.mOnboardingTutorialChangeListener) == null)) {
            onboardingTutorial.removeOnChangeListener(onboardingTutorialChangeListener);
        }
        StatusViewModel.WifiStateListener wifiStateListener = this.mWifiStateListener;
        if (wifiStateListener != null) {
            this.mStatusViewModel.removeWifiStateListener(wifiStateListener);
        }
        this.mPanelApp.releaseStatusViewModel();
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.INotificationProxyListener
    public void onNotificationAdded(Class<? extends IBaseVRNotification> cls, IBaseVRNotification iBaseVRNotification) {
        NotificationsType mapFromUri;
        if (!DeviceConfigHelper.getBoolean(getContext(), Gatekeeper.AUI_V2_MESSENGER) || !NotificationsHelper.isMessagingVRNotification(iBaseVRNotification)) {
            if (!(this.mCurrentTablet == Tablet.NOTIFICATIONS && ((mapFromUri = NotificationsType.mapFromUri(this.mPanelApp.getTabletNavUtil().getSelectedNavItem(TabletNav.NOTIFICATIONS).getUri())) == NotificationsType.ALL || mapFromUri == iBaseVRNotification.getCategory())) && iBaseVRNotification.getSeenState() == AbstractVRNotification.NotificationSeenState.UNSEEN_AND_UNREAD) {
                applyNotifsBadge();
            }
        } else if (!this.mPanelApp.isCurrentTabletMessengerVrApp()) {
            applyBadge(this.mBinding.navigationButtonMessenger.badge);
        }
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp.PanelFrameCallback
    public void onBeginFrame(InputFrame inputFrame) {
        DebugBarView debugBarView;
        if (!this.mDebugBarPinned && (debugBarView = this.mDebugBarView) != null && debugBarView.isRequestingDismiss()) {
            animateDebugBarSlideOut();
        }
        this.mStatusView.frame(inputFrame);
    }

    public void updateGuardianStatus(boolean z, int i, boolean z2, boolean z3) {
        this.mSettingsViewModel.setRoomscaleEnabled(z);
        this.mSettingsViewModel.setIsGuardianOn(z3);
        this.mSettingsViewModel.setIsTrackingIn3DOFMode(z2);
    }

    public void updateRealityTunerStatus(boolean z, int i) {
        this.mSettingsViewModel.setRealityTunerEnabled(z);
        this.mSettingsViewModel.initRealityTunerValue(i);
    }

    public void updateControllerBattery(ControllerBatteryState controllerBatteryState) {
        this.mStatusView.updateControllerBattery(controllerBatteryState);
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public void onTabletShown(Tablet tablet) {
        if (tablet == null) {
            Log.e(TAG, "Tablet should not be null.");
            return;
        }
        this.mCurrentTablet = tablet;
        switch (tablet) {
            case ANDROID_LIBRARY:
            case LIBRARY_STANDALONE_LOADED:
            case LIBRARY_STANDALONE_LOADING:
                selectNavigationButton(this.mBinding.navigationButtonLibrary.button, this.mBinding.navigationButtonLibrary.buttonIcon, this.mBinding.navigationButtonLibrary.buttonLabel);
                return;
            case INTERNAL_SETTINGS_GENERAL:
            case NONE:
            case TABLET_LOADED:
            case TABLET_LOADING:
                clearSelectedNavigationButton();
                return;
            case NOTIFICATIONS:
                selectNavigationButton(this.mBinding.navigationButtonNotifications.button, this.mBinding.navigationButtonNotifications.buttonIcon, this.mBinding.navigationButtonNotifications.buttonLabel);
                return;
            case PAUSE:
                selectNavigationButton(this.mBinding.navigationButtonDestinationUi.button, null, null);
                return;
            case PROFILE:
                selectNavigationButton(this.mBinding.navigationButtonProfile, null, null);
                return;
            case SETTINGS:
            case SETTINGS_LOADED:
            case SETTINGS_LOADING:
                selectNavigationButton(this.mBinding.navigationButtonSettings.button, this.mBinding.navigationButtonSettings.buttonIcon, this.mBinding.navigationButtonSettings.buttonLabel);
                return;
            case SHARING:
                selectNavigationButton(this.mBinding.navigationButtonSharing.button, this.mBinding.navigationButtonSharing.buttonIcon, this.mBinding.navigationButtonSharing.buttonLabel);
                return;
            case SOCIAL:
            case SOCIAL_LOADED:
            case SOCIAL_LOADING:
                selectNavigationButton(this.mBinding.navigationButtonSocial.button, this.mBinding.navigationButtonSocial.buttonIcon, this.mBinding.navigationButtonSocial.buttonLabel);
                return;
            case MESSENGER_LOADED:
            case MESSENGER_LOADING:
                markMessagingNotifsAsSeen();
                break;
            case PARTIES_LOADED:
            case PARTIES_LOADING:
            case CHATS_LOADED:
            case CHATS_LOADING:
            case PEOPLE_LOADED:
            case PEOPLE_LOADING:
            case PEOPLE_FB_LOADED:
            case PEOPLE_FB_LOADING:
            case SOCIAL_SETTINGS_LOADED:
            case SOCIAL_SETTINGS_LOADING:
                break;
            default:
                Log.e(TAG, "Unhandled tablet.");
                return;
        }
        selectNavigationButton(this.mBinding.navigationButtonMessenger.button, this.mBinding.navigationButtonMessenger.buttonIcon, this.mBinding.navigationButtonMessenger.buttonLabel);
    }

    private void markMessagingNotifsAsSeen() {
        ThreadExecutor.getInstance().execute(new Callable() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$BarView$MDzUaiBkeJ_jFFxt55NTF2fQKHA */

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return BarView.this.lambda$markMessagingNotifsAsSeen$25$BarView();
            }
        });
    }

    public /* synthetic */ Object lambda$markMessagingNotifsAsSeen$25$BarView() throws Exception {
        this.mNotificationsProxy.getNotificationsByType(NativeNotification.MESSENGER_NOTIFICATION_TYPE).forEach(new Consumer() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$BarView$7U7wy6CNEEw9XJZnl7HkXkh1Wls */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                BarView.this.lambda$null$24$BarView((IBaseVRNotification) obj);
            }
        });
        return null;
    }

    public /* synthetic */ void lambda$null$24$BarView(IBaseVRNotification iBaseVRNotification) {
        iBaseVRNotification.setSeenState(AbstractVRNotification.NotificationSeenState.SEEN_BUT_UNREAD);
        this.mPanelApp.logNotificationEvent(NotificationsHelper.NOTIF_FEED_IMPRESSION, iBaseVRNotification);
    }

    private int countUnreadNotifs(List<IBaseVRNotification> list) {
        int i = 0;
        for (IBaseVRNotification iBaseVRNotification : list) {
            if ((!NotificationsHelper.isMessagingVRNotification(iBaseVRNotification) || !DeviceConfigHelper.getBoolean(getContext(), Gatekeeper.AUI_V2_MESSENGER)) && iBaseVRNotification.getSeenState().equals(AbstractVRNotification.NotificationSeenState.UNSEEN_AND_UNREAD)) {
                i++;
            }
        }
        return i;
    }

    private int countUnreadMessageNotifs(List<IBaseVRNotification> list) {
        int i = 0;
        for (IBaseVRNotification iBaseVRNotification : list) {
            if (NotificationsHelper.isMessagingVRNotification(iBaseVRNotification) && DeviceConfigHelper.getBoolean(getContext(), Gatekeeper.AUI_V2_MESSENGER) && iBaseVRNotification.getSeenState().equals(AbstractVRNotification.NotificationSeenState.UNSEEN_AND_UNREAD)) {
                i++;
            }
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.mPanelApp.onAUIBarFirstDraw();
        setWillNotDraw(true);
    }

    private boolean shouldUpdateProfilePhoto(String str) {
        String str2;
        return (this.mProfilePhotoURL == null && str != null) || ((str2 = this.mProfilePhotoURL) != null && !str2.equals(this.mSocialViewModel.getProfilePhotoUrl()));
    }
}
