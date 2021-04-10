package com.oculus.panelapp.anytimeui.v2.tablet.common;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletDestinationUiViewV2Binding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletEmptyViewV2Binding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletEnterpriseProfileViewV2Binding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryViewV2Binding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLoadingViewV2Binding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletManagedLauncherViewV2Binding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletNotificationsViewV2Binding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletProfileViewBinding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletSharingViewV2Binding;
import com.oculus.panelapp.anytimeui.quickpromotion.QPTooltipController;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIPanelAppBase;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryView;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.ManagedLauncherView;
import com.oculus.panelapp.anytimeui.v2.tablet.destinationui.DestinationUIView;
import com.oculus.panelapp.anytimeui.v2.tablet.empty.EmptyView;
import com.oculus.panelapp.anytimeui.v2.tablet.loading.LoadingView;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.NotificationsView;
import com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileView;
import com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileView;
import com.oculus.panelapp.anytimeui.v2.tablet.sharing.SharingView;
import com.oculus.panelapp.social.SocialPanelApp;
import com.oculus.panelapp.social.SocialView;
import com.oculus.panelapp.social.databinding.AnytimeTabletSocialViewV2Binding;
import com.oculus.tablet.view.BaseView;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HostView extends LinearLayout {
    private static final String TAG = LoggingUtil.tag(HostView.class);
    private boolean mCurrentTabletShown;
    private String mCurrentTabletUri;
    private BaseView mCurrentTabletView;
    private String mLayerName;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private Map<TabletView, BaseView> mTabletViews;

    /* access modifiers changed from: private */
    public enum TabletView {
        EMPTY,
        LIBRARY,
        LOADING,
        NOTIFICATIONS,
        PAUSE,
        PROFILE,
        SHARING,
        SOCIAL
    }

    public HostView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.d(TAG, "Constructing HostView");
    }

    public void initialize(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, String str) {
        Log.d(TAG, "Initializing HostView");
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mLayerName = str;
        this.mTabletViews = new HashMap();
        showTablet(this.mPanelApp.getDefaultTablet(), "");
    }

    public void destroy() {
        Log.d(TAG, "Destroying HostView");
        for (BaseView baseView : this.mTabletViews.values()) {
            if (baseView == this.mCurrentTabletView && this.mCurrentTabletShown) {
                baseView.onHide();
            }
            this.mPanelApp.getSystemTooltipController().removeTooltipsOnSubtree(baseView);
            baseView.destroy();
        }
        removeAllViews();
    }

    public void clearViewCache(long j) {
        Iterator<Map.Entry<TabletView, BaseView>> it = this.mTabletViews.entrySet().iterator();
        long currentTimeMillis = System.currentTimeMillis();
        while (it.hasNext()) {
            BaseView value = it.next().getValue();
            boolean z = true;
            boolean z2 = (currentTimeMillis - value.getLastUsedMillis()) / 1000 >= j;
            if (value != this.mCurrentTabletView) {
                z = false;
            }
            if (!z && z2) {
                this.mPanelApp.getSystemTooltipController().removeTooltipsOnSubtree(value);
                removeView(value);
                value.destroy();
                it.remove();
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v6, resolved type: com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryView */
    /* JADX DEBUG: Multi-variable search result rejected for r1v8, resolved type: com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.ManagedLauncherView */
    /* JADX DEBUG: Multi-variable search result rejected for r1v10, resolved type: com.oculus.panelapp.anytimeui.v2.tablet.loading.LoadingView */
    /* JADX DEBUG: Multi-variable search result rejected for r1v12, resolved type: com.oculus.panelapp.anytimeui.v2.tablet.notifications.NotificationsView */
    /* JADX DEBUG: Multi-variable search result rejected for r1v14, resolved type: com.oculus.panelapp.anytimeui.v2.tablet.destinationui.DestinationUIView */
    /* JADX DEBUG: Multi-variable search result rejected for r1v16, resolved type: com.oculus.panelapp.anytimeui.v2.tablet.profile.ProfileView */
    /* JADX DEBUG: Multi-variable search result rejected for r1v18, resolved type: com.oculus.panelapp.anytimeui.v2.tablet.profile.enterprise.EnterpriseProfileView */
    /* JADX DEBUG: Multi-variable search result rejected for r1v20, resolved type: com.oculus.panelapp.anytimeui.v2.tablet.sharing.SharingView */
    /* JADX DEBUG: Multi-variable search result rejected for r1v22, resolved type: com.oculus.panelapp.social.SocialView */
    /* JADX WARN: Multi-variable type inference failed */
    private BaseView ensureCreateView(TabletView tabletView) {
        EmptyView emptyView;
        if (this.mTabletViews.containsKey(tabletView)) {
            return this.mTabletViews.get(tabletView);
        }
        switch (tabletView) {
            case EMPTY:
                AnytimeTabletEmptyViewV2Binding inflate = AnytimeTabletEmptyViewV2Binding.inflate(LayoutInflater.from(getContext()), this, true);
                EmptyView emptyView2 = (EmptyView) inflate.getRoot();
                emptyView2.initialize(this.mPanelApp, inflate);
                emptyView = emptyView2;
                break;
            case LIBRARY:
                if (this.mPanelApp.getSystemUXConfig().isEnterpriseMode) {
                    AnytimeTabletManagedLauncherViewV2Binding inflate2 = AnytimeTabletManagedLauncherViewV2Binding.inflate(LayoutInflater.from(getContext()), this, true);
                    ManagedLauncherView managedLauncherView = (ManagedLauncherView) inflate2.getRoot();
                    managedLauncherView.initialize(this.mPanelApp, (ViewDataBinding) inflate2);
                    emptyView = managedLauncherView;
                    break;
                } else if (!this.mPanelApp.isGKEnabled(Gatekeeper.AUI_STANDALONE_APPS)) {
                    AnytimeTabletLibraryViewV2Binding inflate3 = AnytimeTabletLibraryViewV2Binding.inflate(LayoutInflater.from(getContext()), this, true);
                    LibraryView libraryView = (LibraryView) inflate3.getRoot();
                    libraryView.initialize((AnytimeUIPanelAppBase) this.mPanelApp, (ViewDataBinding) inflate3);
                    emptyView = libraryView;
                    break;
                } else {
                    throw new RuntimeException("Refusing to instantiate Library in AUIPanelApp.");
                }
            case LOADING:
                AnytimeTabletLoadingViewV2Binding inflate4 = AnytimeTabletLoadingViewV2Binding.inflate(LayoutInflater.from(getContext()), this, true);
                LoadingView loadingView = (LoadingView) inflate4.getRoot();
                loadingView.initialize(this.mPanelApp, inflate4, ContentLayout.ENTIRE_TABLET);
                emptyView = loadingView;
                break;
            case NOTIFICATIONS:
                AnytimeTabletNotificationsViewV2Binding inflate5 = AnytimeTabletNotificationsViewV2Binding.inflate(LayoutInflater.from(getContext()), this, true);
                NotificationsView notificationsView = (NotificationsView) inflate5.getRoot();
                notificationsView.initialize(this.mPanelApp, (ViewDataBinding) inflate5);
                emptyView = notificationsView;
                break;
            case PAUSE:
                AnytimeTabletDestinationUiViewV2Binding inflate6 = AnytimeTabletDestinationUiViewV2Binding.inflate(LayoutInflater.from(getContext()), this, true);
                DestinationUIView destinationUIView = (DestinationUIView) inflate6.getRoot();
                destinationUIView.initialize(this.mPanelApp, (ViewDataBinding) inflate6);
                emptyView = destinationUIView;
                break;
            case PROFILE:
                if (!this.mPanelApp.getSystemUXConfig().isEnterpriseMode) {
                    AnytimeTabletProfileViewBinding inflate7 = AnytimeTabletProfileViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
                    ProfileView profileView = (ProfileView) inflate7.getRoot();
                    profileView.initialize(this.mPanelApp, (ViewDataBinding) inflate7);
                    emptyView = profileView;
                    break;
                } else {
                    AnytimeTabletEnterpriseProfileViewV2Binding inflate8 = AnytimeTabletEnterpriseProfileViewV2Binding.inflate(LayoutInflater.from(getContext()), this, true);
                    EnterpriseProfileView enterpriseProfileView = (EnterpriseProfileView) inflate8.getRoot();
                    enterpriseProfileView.initialize(this.mPanelApp, (ViewDataBinding) inflate8);
                    emptyView = enterpriseProfileView;
                    break;
                }
            case SHARING:
                AnytimeTabletSharingViewV2Binding inflate9 = AnytimeTabletSharingViewV2Binding.inflate(LayoutInflater.from(getContext()), this, true);
                SharingView sharingView = (SharingView) inflate9.getRoot();
                sharingView.initialize(this.mPanelApp, (ViewDataBinding) inflate9);
                new QPTooltipController(inflate9, this.mPanelApp, Tablet.SHARING, getContext());
                emptyView = sharingView;
                break;
            case SOCIAL:
                AnytimeTabletSocialViewV2Binding inflate10 = AnytimeTabletSocialViewV2Binding.inflate(LayoutInflater.from(getContext()), this, true);
                SocialView socialView = (SocialView) inflate10.getRoot();
                socialView.initialize((SocialPanelApp) this.mPanelApp, (ViewDataBinding) inflate10);
                emptyView = socialView;
                break;
            default:
                throw new IllegalArgumentException("Unhandled tablet view: " + tabletView);
        }
        this.mPanelApp.getSystemTooltipController().initializeTooltipsOnSubtree(emptyView, this.mLayerName, R.id.tooltip_text, R.id.tooltip_subtext, R.id.tooltip_data);
        this.mTabletViews.put(tabletView, emptyView);
        return emptyView;
    }

    public void showTablet(Tablet tablet, String str) {
        this.mCurrentTabletShown = true;
        this.mCurrentTabletUri = str;
        BaseView ensureCreateView = ensureCreateView(mapTabletToView(tablet));
        BaseView baseView = this.mCurrentTabletView;
        if (baseView != ensureCreateView) {
            if (baseView != null) {
                baseView.markUsed();
                this.mCurrentTabletView.setVisibility(8);
                this.mCurrentTabletView.onHide();
            }
            ensureCreateView.onShow(str);
            ensureCreateView.setVisibility(0);
            this.mCurrentTabletView = ensureCreateView;
        } else if (tablet.equals(Tablet.SOCIAL)) {
            ensureCreateView.onShow(str);
        }
    }

    public void onTabletLayerShown() {
        BaseView baseView = this.mCurrentTabletView;
        if (baseView != null && !this.mCurrentTabletShown) {
            baseView.onShow(this.mCurrentTabletUri);
            this.mCurrentTabletShown = true;
        }
    }

    public void onTabletLayerHidden() {
        BaseView baseView = this.mCurrentTabletView;
        if (baseView != null && this.mCurrentTabletShown) {
            baseView.onHide();
            this.mCurrentTabletShown = false;
        }
    }

    private TabletView mapTabletToView(Tablet tablet) {
        switch (tablet) {
            case ANDROID_LIBRARY:
                return TabletView.LIBRARY;
            case ANDROID_SETTINGS:
            case INTERNAL_SETTINGS_GENERAL:
            case CHATS_LOADED:
            case LIBRARY_STANDALONE_LOADED:
            case MESSENGER_LOADED:
            case NONE:
            case PARTIES_LOADED:
            case PEOPLE_LOADED:
            case PEOPLE_FB_LOADED:
            case SETTINGS:
            case SETTINGS_LOADED:
            case SETTINGS_LOADING:
            case SOCIAL_LOADED:
            case SOCIAL_REAUTH_LOADED:
            case SOCIAL_SETTINGS_LOADED:
            case TABLET_LOADED:
                return TabletView.EMPTY;
            case CHATS_LOADING:
            case LIBRARY_STANDALONE_LOADING:
            case MESSENGER_LOADING:
            case PARTIES_LOADING:
            case PEOPLE_LOADING:
            case PEOPLE_FB_LOADING:
            case SOCIAL_LOADING:
            case SOCIAL_REAUTH_LOADING:
            case SOCIAL_SETTINGS_LOADING:
            case TABLET_LOADING:
                return TabletView.LOADING;
            case NOTIFICATIONS:
                return TabletView.NOTIFICATIONS;
            case PAUSE:
                return TabletView.PAUSE;
            case PROFILE:
                return TabletView.PROFILE;
            case SHARING:
                return TabletView.SHARING;
            case SOCIAL:
                return TabletView.SOCIAL;
            default:
                throw new IllegalArgumentException("Unhandled tablet: " + tablet);
        }
    }

    public String getInstantiatedViewsForLogging() {
        return TextUtils.join(",", this.mTabletViews.keySet());
    }
}
