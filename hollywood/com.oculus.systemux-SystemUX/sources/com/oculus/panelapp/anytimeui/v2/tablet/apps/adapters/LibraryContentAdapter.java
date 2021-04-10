package com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.imagepipeline.request.ImageRequest;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.library.model.App;
import com.oculus.ocui.OCDropdown;
import com.oculus.ocui.OCDropdownVisibilityCallback;
import com.oculus.ocui.OCLink;
import com.oculus.ocui.OCSpinner;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryAppTileV2Binding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryGetMoreAppsTileV2Binding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryMobileOculusGoBannerV2Binding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryNullStateBinding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibrarySystemAppsHeaderV2Binding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryUnknownSourceRowV2Binding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLibraryUnknownSourcesHeaderV2Binding;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIPanelAppBase;
import com.oculus.panelapp.anytimeui.v2.nux.OnboardingTutorial;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.LibraryViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.FrescoControllerListener;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibraryAppTileContextMenuItem;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibraryFilter;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.LibraryItem;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.models.UnknownSource;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryAppUtils;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryFakeAppUtils;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryLogger;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.LibraryUtils;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.utils.SocialRoutingUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsViewModel;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.tablet.utils.backtotop.ScrollCallback;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.DensityUtils;
import com.oculus.vrshell.util.HorizonUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class LibraryContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String LIBRARY_3DOF_LINK_URL = "https://support.oculus.com/343060616597701/";
    public static final String LIBRARY_PRIVACY_NOTE_URL = "https://developer.oculus.com/distribute/publish-content-guidelines/";
    public static final int LOADING_SPINNER_HEIGHT_DIPS = 30;
    public static final int LOADING_SPINNER_MARGIN_TOP_DIPS = 60;
    private static final String TAG = LoggingUtil.tag(LibraryContentAdapter.class);
    public static final int VIEW_TYPE_APP_TILE = 3;
    public static final int VIEW_TYPE_GET_MORE_APPS_TILE = 7;
    public static final int VIEW_TYPE_GO_GEAR_VR_BANNER = 6;
    public static final int VIEW_TYPE_HEADER_SYSTEM_APPS = 1;
    public static final int VIEW_TYPE_HEADER_UNKNOWN_SOURCES = 2;
    public static final int VIEW_TYPE_LOADING_VIEW = 5;
    public static final int VIEW_TYPE_NULL_STATE = 0;
    public static final int VIEW_TYPE_UNKNOWN_SOURCE_ROW = 4;
    private AdapterUpdatesCoalescer mAdapterUpdatesCoalescer;
    private Context mContext;
    private PipelineDraweeControllerBuilder mControllerBuilder = Fresco.newDraweeControllerBuilder();
    private OCDropdown<LibraryAppTileContextMenuItem> mDropdown;
    private OCDropdownVisibilityCallback mDropdownVisibilityCallback;
    private Drawable mGoGearVrBannerCloseIcon;
    private Drawable mGoGearVrBannerCloseIconHighlight;
    private boolean mIsInternetConnected;
    private boolean mIsOCShellAutomationEnabled;
    private boolean mIsTrackingEnabled;
    private List<LibraryItem> mItems = new ArrayList();
    private LibraryLogger mLibraryLogger;
    private final OnboardingTutorial mOnboardingTutorial;
    private AnytimeUIPanelAppBase mPanelApp;
    private ScrollCallback mScrollCallback;
    private SettingsViewModel mSettingsViewModel;
    private Drawable mUnknownSourceRowContextMenuIcon;
    private Drawable mUnknownSourceRowContextMenuIconHighlight;
    private Drawable mUnknownSourcesHeaderOpenTabIcon;
    private Drawable mUnknownSourcesHeaderOpenTabIconHighlight;
    private LibraryViewModel mViewModel;

    public void destroy() {
    }

    public LibraryContentAdapter(Context context, AnytimeUIPanelAppBase anytimeUIPanelAppBase, LibraryViewModel libraryViewModel, SettingsViewModel settingsViewModel, ScrollCallback scrollCallback, LibraryLogger libraryLogger) {
        this.mContext = context;
        this.mPanelApp = anytimeUIPanelAppBase;
        this.mViewModel = libraryViewModel;
        this.mSettingsViewModel = settingsViewModel;
        this.mIsInternetConnected = this.mViewModel.getIsInternetConnected();
        this.mIsTrackingEnabled = getIsTrackingEnabled();
        this.mScrollCallback = scrollCallback;
        this.mAdapterUpdatesCoalescer = new AdapterUpdatesCoalescer(this);
        this.mDropdown = new OCDropdown<>(this.mContext);
        this.mLibraryLogger = libraryLogger;
        this.mOnboardingTutorial = this.mPanelApp.getOnboardingTutorial();
        this.mIsOCShellAutomationEnabled = this.mPanelApp.isOCShellAutomationEnabled();
    }

    public void setItems(final List<LibraryItem> list) {
        this.mAdapterUpdatesCoalescer.setUpdate(new ILibraryUpdate() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.LibraryContentAdapter.AnonymousClass1 */
            boolean mUpdateIsInternetConnected;
            boolean mUpdateIsTrackingEnabled;

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.ILibraryUpdate
            public void onStart() {
                this.mUpdateIsInternetConnected = LibraryContentAdapter.this.mViewModel.getIsInternetConnected();
                this.mUpdateIsTrackingEnabled = LibraryContentAdapter.this.getIsTrackingEnabled();
            }

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.ILibraryUpdate
            public DiffUtil.Callback getDiffUtilCallback() {
                return new LibraryContentAdapterDiffUtilCallback(list, LibraryContentAdapter.this.mItems, this.mUpdateIsInternetConnected, LibraryContentAdapter.this.mIsInternetConnected, this.mUpdateIsTrackingEnabled, LibraryContentAdapter.this.mIsTrackingEnabled);
            }

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.ILibraryUpdate
            public void beforeDispatchUpdates() {
                LibraryContentAdapter.this.mItems.clear();
                LibraryContentAdapter.this.mItems.addAll(list);
                LibraryContentAdapter.this.mIsInternetConnected = this.mUpdateIsInternetConnected;
                LibraryContentAdapter.this.mIsTrackingEnabled = this.mUpdateIsTrackingEnabled;
            }

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.ILibraryUpdate
            public void afterDispatchUpdates() {
                LibraryContentAdapter.this.mScrollCallback.onUpdate();
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mItems.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case 0:
                return createNullStateHolder(viewGroup);
            case 1:
                return createSystemAppsHeaderHolder(viewGroup);
            case 2:
                return createUnknownSourcesHeaderHolder(viewGroup);
            case 3:
                return createAppTileHolder(viewGroup);
            case 4:
                return createUnknownSourceRowHolder(viewGroup);
            case 5:
                return createLoadingViewHolder(viewGroup);
            case 6:
                return createGoGearVrBannerHolder(viewGroup);
            case 7:
                return createGetMoreAppsTileHolder(viewGroup);
            default:
                return null;
        }
    }

    private SystemAppsHeaderHolder createSystemAppsHeaderHolder(ViewGroup viewGroup) {
        AnytimeTabletLibrarySystemAppsHeaderV2Binding inflate = AnytimeTabletLibrarySystemAppsHeaderV2Binding.inflate(LayoutInflater.from(this.mContext), viewGroup, false);
        inflate.setViewModel(this.mViewModel);
        inflate.libraryHomeButton.button.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$wzQqhonOEhsBZfdFtkHmMSIXwqU */

            public final void onClick(View view) {
                LibraryContentAdapter.this.lambda$createSystemAppsHeaderHolder$115$LibraryContentAdapter(view);
            }
        });
        inflate.libraryStoreButton.button.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$1lUm5L4QGEsgL6wJ8GeE_AZUps */

            public final void onClick(View view) {
                LibraryContentAdapter.this.lambda$createSystemAppsHeaderHolder$116$LibraryContentAdapter(view);
            }
        });
        inflate.libraryBrowserButton.button.setOnClickListener(new View.OnClickListener(inflate) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$GTW5V4q2FjsUajsTig90YlaYocc */
            private final /* synthetic */ AnytimeTabletLibrarySystemAppsHeaderV2Binding f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                LibraryContentAdapter.this.lambda$createSystemAppsHeaderHolder$117$LibraryContentAdapter(this.f$1, view);
            }
        });
        inflate.libraryBrowserButton.button.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$s6HYVQApU3z9ml6rAQfVqtsBcvg */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return LibraryContentAdapter.lambda$createSystemAppsHeaderHolder$118(AnytimeTabletLibrarySystemAppsHeaderV2Binding.this, view, motionEvent);
            }
        });
        inflate.librarySocialButton.button.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$dt457RbFX0467D8BO_qx714oSY0 */

            public final void onClick(View view) {
                LibraryContentAdapter.this.lambda$createSystemAppsHeaderHolder$119$LibraryContentAdapter(view);
            }
        });
        inflate.libraryTvButton.button.setOnClickListener(new View.OnClickListener(inflate) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$zHcQs51G4DvO7Jx_yUYmuDYDBNQ */
            private final /* synthetic */ AnytimeTabletLibrarySystemAppsHeaderV2Binding f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                LibraryContentAdapter.this.lambda$createSystemAppsHeaderHolder$120$LibraryContentAdapter(this.f$1, view);
            }
        });
        inflate.libraryTvButton.button.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$JVZo5V9CixjUHO2JUGwurHuA4s */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return LibraryContentAdapter.lambda$createSystemAppsHeaderHolder$121(AnytimeTabletLibrarySystemAppsHeaderV2Binding.this, view, motionEvent);
            }
        });
        inflate.libraryHomeButton.button.setEventHandler(this.mPanelApp);
        inflate.libraryStoreButton.button.setEventHandler(this.mPanelApp);
        inflate.libraryBrowserButton.button.setEventHandler(this.mPanelApp);
        inflate.librarySocialButton.button.setEventHandler(this.mPanelApp);
        inflate.libraryTvButton.button.setEventHandler(this.mPanelApp);
        return new SystemAppsHeaderHolder(inflate);
    }

    public /* synthetic */ void lambda$createSystemAppsHeaderHolder$115$LibraryContentAdapter(View view) {
        this.mPanelApp.actionNavigate(SystemUXRoute.HOME, "");
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_LIBRARY_HOME);
        this.mPanelApp.getOnboardingTutorial().onClickHome();
        LibraryAppUtils.maybeLogSystemAppClick(this.mPanelApp, this.mLibraryLogger, LibraryFakeAppUtils.HOME_PACKAGE_NAME, -1);
    }

    public /* synthetic */ void lambda$createSystemAppsHeaderHolder$116$LibraryContentAdapter(View view) {
        this.mPanelApp.actionNavigate(SystemUXRoute.STORE, "");
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_LIBRARY_STORE);
        LibraryAppUtils.maybeLogSystemAppClick(this.mPanelApp, this.mLibraryLogger, LibraryFakeAppUtils.STORE_PACKAGE_NAME, -1);
    }

    public /* synthetic */ void lambda$createSystemAppsHeaderHolder$117$LibraryContentAdapter(AnytimeTabletLibrarySystemAppsHeaderV2Binding anytimeTabletLibrarySystemAppsHeaderV2Binding, View view) {
        LibraryAppUtils.doStatusBasedSystemAppAction(anytimeTabletLibrarySystemAppsHeaderV2Binding.getBrowserApp(), this.mPanelApp, SystemUXRoute.DEFAULT_BROWSER, this.mIsInternetConnected);
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_LIBRARY_BROWSER);
    }

    static /* synthetic */ boolean lambda$createSystemAppsHeaderHolder$118(AnytimeTabletLibrarySystemAppsHeaderV2Binding anytimeTabletLibrarySystemAppsHeaderV2Binding, View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            anytimeTabletLibrarySystemAppsHeaderV2Binding.libraryBrowserButton.setIsHovered(true);
        } else if (actionMasked == 10) {
            anytimeTabletLibrarySystemAppsHeaderV2Binding.libraryBrowserButton.setIsHovered(false);
        }
        return false;
    }

    public /* synthetic */ void lambda$createSystemAppsHeaderHolder$119$LibraryContentAdapter(View view) {
        SocialRoutingUtil.navigateToSocial(this.mViewModel, this.mPanelApp);
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_LIBRARY_SOCIAL);
        LibraryAppUtils.maybeLogSystemAppClick(this.mPanelApp, this.mLibraryLogger, LibraryFakeAppUtils.SOCIAL_PACKAGE_NAME, -1);
    }

    public /* synthetic */ void lambda$createSystemAppsHeaderHolder$120$LibraryContentAdapter(AnytimeTabletLibrarySystemAppsHeaderV2Binding anytimeTabletLibrarySystemAppsHeaderV2Binding, View view) {
        LibraryAppUtils.doStatusBasedSystemAppAction(anytimeTabletLibrarySystemAppsHeaderV2Binding.getTvApp(), this.mPanelApp, this.mIsInternetConnected);
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_LIBRARY_TV);
    }

    static /* synthetic */ boolean lambda$createSystemAppsHeaderHolder$121(AnytimeTabletLibrarySystemAppsHeaderV2Binding anytimeTabletLibrarySystemAppsHeaderV2Binding, View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            anytimeTabletLibrarySystemAppsHeaderV2Binding.libraryTvButton.setIsHovered(true);
        } else if (actionMasked == 10) {
            anytimeTabletLibrarySystemAppsHeaderV2Binding.libraryTvButton.setIsHovered(false);
        }
        return false;
    }

    private NullStateHolder createNullStateHolder(ViewGroup viewGroup) {
        return new NullStateHolder(AnytimeTabletLibraryNullStateBinding.inflate(LayoutInflater.from(this.mContext), viewGroup, false));
    }

    private AppTileHolder createAppTileHolder(ViewGroup viewGroup) {
        AnytimeTabletLibraryAppTileV2Binding inflate = AnytimeTabletLibraryAppTileV2Binding.inflate(LayoutInflater.from(this.mContext), viewGroup, false);
        inflate.setPanelApp(this.mPanelApp);
        inflate.cardView.setCardBackgroundColor(0);
        inflate.button.setEventHandler(this.mPanelApp);
        Animation loadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.ocimagecard_inner_zoom);
        loadAnimation.reset();
        inflate.button.setOnHoverListener(new View.OnHoverListener(loadAnimation) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$3Qh899vUOu4e7e_tDvoPx4Jylw */
            private final /* synthetic */ Animation f$1;

            {
                this.f$1 = r2;
            }

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return LibraryContentAdapter.lambda$createAppTileHolder$122(AnytimeTabletLibraryAppTileV2Binding.this, this.f$1, view, motionEvent);
            }
        });
        inflate.button.setOnClickListener(new View.OnClickListener(inflate) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$oqkrWjnRxL7E1jcuMJkxL7ogDCw */
            private final /* synthetic */ AnytimeTabletLibraryAppTileV2Binding f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                LibraryContentAdapter.this.lambda$createAppTileHolder$123$LibraryContentAdapter(this.f$1, view);
            }
        });
        inflate.contextMenuButton.setEventHandler(this.mPanelApp);
        inflate.contextMenuButton.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$Z1wk2hG38zEMt0lx50oZDHpcFvw */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return LibraryContentAdapter.lambda$createAppTileHolder$124(AnytimeTabletLibraryAppTileV2Binding.this, view, motionEvent);
            }
        });
        inflate.contextMenuButton.setOnClickListener(new View.OnClickListener(inflate) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$MygYuV_dy07XhevdJ0Cz2lksJY */
            private final /* synthetic */ AnytimeTabletLibraryAppTileV2Binding f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                LibraryContentAdapter.this.lambda$createAppTileHolder$126$LibraryContentAdapter(this.f$1, view);
            }
        });
        return new AppTileHolder(inflate);
    }

    static /* synthetic */ boolean lambda$createAppTileHolder$122(AnytimeTabletLibraryAppTileV2Binding anytimeTabletLibraryAppTileV2Binding, Animation animation, View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            anytimeTabletLibraryAppTileV2Binding.setIsHovered(true);
            anytimeTabletLibraryAppTileV2Binding.title.setSelected(true);
            anytimeTabletLibraryAppTileV2Binding.subtitle.setSelected(true);
            anytimeTabletLibraryAppTileV2Binding.imageView.startAnimation(animation);
        } else if (actionMasked == 10) {
            anytimeTabletLibraryAppTileV2Binding.setIsHovered(false);
            anytimeTabletLibraryAppTileV2Binding.title.setSelected(false);
            anytimeTabletLibraryAppTileV2Binding.subtitle.setSelected(false);
            anytimeTabletLibraryAppTileV2Binding.imageView.clearAnimation();
        }
        return true;
    }

    public /* synthetic */ void lambda$createAppTileHolder$123$LibraryContentAdapter(AnytimeTabletLibraryAppTileV2Binding anytimeTabletLibraryAppTileV2Binding, View view) {
        App app = anytimeTabletLibraryAppTileV2Binding.getApp();
        int position = anytimeTabletLibraryAppTileV2Binding.getPosition();
        if (LibraryFakeAppUtils.isFakeApp(app.packageName)) {
            this.mPanelApp.actionNavigate(LibraryFakeAppUtils.getSystemUXRouteForFakeApp(app.packageName), LibraryFakeAppUtils.shouldAddReturnComponentToLaunch(app.packageName) ? this.mPanelApp.getReturnComponent() : "");
            this.mViewModel.updateFakeApp(app.packageName);
        } else {
            LibraryAppUtils.doStatusBasedAppAction(app, position, this.mPanelApp, this.mIsInternetConnected, this.mLibraryLogger);
            HorizonUtil.markIsSeen(this.mContext, app.packageName);
        }
        LibraryAppUtils.maybeLogSystemAppClick(this.mPanelApp, this.mLibraryLogger, app.packageName, position);
    }

    static /* synthetic */ boolean lambda$createAppTileHolder$124(AnytimeTabletLibraryAppTileV2Binding anytimeTabletLibraryAppTileV2Binding, View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            anytimeTabletLibraryAppTileV2Binding.setIsContextMenuButtonHovered(true);
        } else if (actionMasked == 10) {
            anytimeTabletLibraryAppTileV2Binding.setIsContextMenuButtonHovered(false);
        }
        return true;
    }

    public /* synthetic */ void lambda$createAppTileHolder$126$LibraryContentAdapter(AnytimeTabletLibraryAppTileV2Binding anytimeTabletLibraryAppTileV2Binding, View view) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(LibraryAppUtils.getAppTileContextMenuItems(anytimeTabletLibraryAppTileV2Binding.getApp(), this.mViewModel));
        this.mDropdown.setItems(arrayList);
        this.mDropdown.setTitleMap(LibraryAppTileContextMenuItem.getMap());
        this.mDropdown.setOnItemClick(new Function(anytimeTabletLibraryAppTileV2Binding) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$u3KjWd4wuvNBvFTu4MRKHWQHzz4 */
            private final /* synthetic */ AnytimeTabletLibraryAppTileV2Binding f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return LibraryContentAdapter.this.lambda$null$125$LibraryContentAdapter(this.f$1, obj);
            }
        });
        this.mDropdown.setVisibilityCallback(this.mDropdownVisibilityCallback);
        this.mDropdown.setEventHandler(this.mPanelApp);
        this.mDropdown.toggle(view);
    }

    public /* synthetic */ Object lambda$null$125$LibraryContentAdapter(AnytimeTabletLibraryAppTileV2Binding anytimeTabletLibraryAppTileV2Binding, Object obj) {
        LibraryAppUtils.onContextMenuItemClicked(this.mContext, this.mPanelApp, this.mLibraryLogger, anytimeTabletLibraryAppTileV2Binding.getApp(), (LibraryAppTileContextMenuItem) obj, anytimeTabletLibraryAppTileV2Binding.getPosition());
        return null;
    }

    private UnknownSourcesHeaderHolder createUnknownSourcesHeaderHolder(ViewGroup viewGroup) {
        AnytimeTabletLibraryUnknownSourcesHeaderV2Binding inflate = AnytimeTabletLibraryUnknownSourcesHeaderV2Binding.inflate(LayoutInflater.from(this.mContext), viewGroup, false);
        inflate.warningDescription.setEventHandler(this.mPanelApp);
        OCLink oCLink = inflate.warningDescription;
        AnytimeUIPanelAppBase anytimeUIPanelAppBase = this.mPanelApp;
        anytimeUIPanelAppBase.getClass();
        oCLink.setLinkHandler(new OCLink.OCLinkHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$F3T_DWypQfQ_v2ZMROCxujhLw */

            @Override // com.oculus.ocui.OCLink.OCLinkHandler
            public final void open(String str, String str2) {
                AnytimeUIPanelAppBase.this.actionNavigate(str, str2);
            }
        });
        inflate.warningDescription.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$zpegRsF7wr3gcVmV2825j2il6UM */

            public final void onClick(View view) {
                LibraryContentAdapter.this.lambda$createUnknownSourcesHeaderHolder$127$LibraryContentAdapter(view);
            }
        });
        inflate.openTabIcon.setImageDrawable(getUnknownSourcesHeaderOpenTabIcon(false));
        inflate.openTabButton.setEventHandler(this.mPanelApp);
        inflate.openTabButton.setOnHoverListener(new View.OnHoverListener(inflate) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$hKRtJ6PlGHMsX5kES4JzcNbPOr4 */
            private final /* synthetic */ AnytimeTabletLibraryUnknownSourcesHeaderV2Binding f$1;

            {
                this.f$1 = r2;
            }

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return LibraryContentAdapter.this.lambda$createUnknownSourcesHeaderHolder$128$LibraryContentAdapter(this.f$1, view, motionEvent);
            }
        });
        inflate.openTabButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$Kn_uiR4lC3CvHSCZ94ALy73NY */

            public final void onClick(View view) {
                LibraryContentAdapter.this.lambda$createUnknownSourcesHeaderHolder$129$LibraryContentAdapter(view);
            }
        });
        return new UnknownSourcesHeaderHolder(inflate);
    }

    public /* synthetic */ void lambda$createUnknownSourcesHeaderHolder$127$LibraryContentAdapter(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_LIBRARY_UNKNOWN_SOURCES_CONTENT_GUIDELINES);
    }

    public /* synthetic */ boolean lambda$createUnknownSourcesHeaderHolder$128$LibraryContentAdapter(AnytimeTabletLibraryUnknownSourcesHeaderV2Binding anytimeTabletLibraryUnknownSourcesHeaderV2Binding, View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            anytimeTabletLibraryUnknownSourcesHeaderV2Binding.openTabIcon.setImageDrawable(getUnknownSourcesHeaderOpenTabIcon(true));
        } else if (actionMasked == 10) {
            anytimeTabletLibraryUnknownSourcesHeaderV2Binding.openTabIcon.setImageDrawable(getUnknownSourcesHeaderOpenTabIcon(false));
        }
        return false;
    }

    public /* synthetic */ void lambda$createUnknownSourcesHeaderHolder$129$LibraryContentAdapter(View view) {
        this.mPanelApp.actionNavigate(SystemUXRoute.DEFAULT_BROWSER, String.format("ovrweb://webtask?uri=%s", Uri.encode(LIBRARY_PRIVACY_NOTE_URL)));
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_LIBRARY_UNKNOWN_SOURCES_CONTENT_GUIDELINES);
    }

    private UnknownSourceRowHolder createUnknownSourceRowHolder(ViewGroup viewGroup) {
        AnytimeTabletLibraryUnknownSourceRowV2Binding inflate = AnytimeTabletLibraryUnknownSourceRowV2Binding.inflate(LayoutInflater.from(this.mContext), viewGroup, false);
        inflate.appMenuButton.setEventHandler(this.mPanelApp);
        inflate.appRowButton.setEventHandler(this.mPanelApp);
        inflate.appMenuButton.setOnHoverListener(new View.OnHoverListener(inflate) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$_Ok9YVSnn7zXSFLbbFB1foL1R7c */
            private final /* synthetic */ AnytimeTabletLibraryUnknownSourceRowV2Binding f$1;

            {
                this.f$1 = r2;
            }

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return LibraryContentAdapter.this.lambda$createUnknownSourceRowHolder$130$LibraryContentAdapter(this.f$1, view, motionEvent);
            }
        });
        return new UnknownSourceRowHolder(inflate);
    }

    public /* synthetic */ boolean lambda$createUnknownSourceRowHolder$130$LibraryContentAdapter(AnytimeTabletLibraryUnknownSourceRowV2Binding anytimeTabletLibraryUnknownSourceRowV2Binding, View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            anytimeTabletLibraryUnknownSourceRowV2Binding.appMenuIcon.setImageDrawable(getUnknownSourceRowContextMenuIcon(true));
        } else if (actionMasked == 10) {
            anytimeTabletLibraryUnknownSourceRowV2Binding.appMenuIcon.setImageDrawable(getUnknownSourceRowContextMenuIcon(false));
        }
        return false;
    }

    private LoadingViewHolder createLoadingViewHolder(ViewGroup viewGroup) {
        OCSpinner oCSpinner = new OCSpinner(this.mContext, null);
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -2);
        marginLayoutParams.height = DensityUtils.dipToPixelsInt(30.0f, this.mContext.getResources().getDisplayMetrics());
        marginLayoutParams.setMargins(0, DensityUtils.dipToPixelsInt(60.0f, this.mContext.getResources().getDisplayMetrics()), 0, 0);
        oCSpinner.setLayoutParams(marginLayoutParams);
        return new LoadingViewHolder(oCSpinner);
    }

    private GoGearVrBannerHolder createGoGearVrBannerHolder(ViewGroup viewGroup) {
        AnytimeTabletLibraryMobileOculusGoBannerV2Binding inflate = AnytimeTabletLibraryMobileOculusGoBannerV2Binding.inflate(LayoutInflater.from(this.mContext), viewGroup, false);
        inflate.dismissIcon.setImageDrawable(getCloseBannerIcon(false));
        inflate.dismissButton.setEventHandler(this.mPanelApp);
        inflate.dismissButton.setOnHoverListener(new View.OnHoverListener(inflate) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$9W0WfFq7OAbNQyNuM5JBUH0oAP0 */
            private final /* synthetic */ AnytimeTabletLibraryMobileOculusGoBannerV2Binding f$1;

            {
                this.f$1 = r2;
            }

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return LibraryContentAdapter.this.lambda$createGoGearVrBannerHolder$131$LibraryContentAdapter(this.f$1, view, motionEvent);
            }
        });
        inflate.dismissButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$PI4bofkAjnuFm4yyQIPe8sCkamo */

            public final void onClick(View view) {
                LibraryContentAdapter.this.lambda$createGoGearVrBannerHolder$132$LibraryContentAdapter(view);
            }
        });
        inflate.learnMoreButton.setEventHandler(this.mPanelApp);
        inflate.learnMoreButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$H5sm5yWs3i7xlOu5hGEzKvTYkNY */

            public final void onClick(View view) {
                LibraryContentAdapter.this.lambda$createGoGearVrBannerHolder$133$LibraryContentAdapter(view);
            }
        });
        return new GoGearVrBannerHolder(inflate);
    }

    public /* synthetic */ boolean lambda$createGoGearVrBannerHolder$131$LibraryContentAdapter(AnytimeTabletLibraryMobileOculusGoBannerV2Binding anytimeTabletLibraryMobileOculusGoBannerV2Binding, View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            anytimeTabletLibraryMobileOculusGoBannerV2Binding.dismissIcon.setImageDrawable(getCloseBannerIcon(true));
        } else if (actionMasked == 10) {
            anytimeTabletLibraryMobileOculusGoBannerV2Binding.dismissIcon.setImageDrawable(getCloseBannerIcon(false));
        }
        return true;
    }

    public /* synthetic */ void lambda$createGoGearVrBannerHolder$132$LibraryContentAdapter(View view) {
        this.mViewModel.dismissGoGearVrBanner();
    }

    public /* synthetic */ void lambda$createGoGearVrBannerHolder$133$LibraryContentAdapter(View view) {
        LibraryUtils.openUriWithBrowser(LIBRARY_3DOF_LINK_URL, this.mPanelApp);
    }

    private GetMoreAppsTileHolder createGetMoreAppsTileHolder(ViewGroup viewGroup) {
        AnytimeTabletLibraryGetMoreAppsTileV2Binding inflate = AnytimeTabletLibraryGetMoreAppsTileV2Binding.inflate(LayoutInflater.from(this.mContext), viewGroup, false);
        inflate.cardView.setCardBackgroundColor(0);
        inflate.button.setEventHandler(this.mPanelApp);
        inflate.button.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$pgsgl6TRJkNuGIxP6E3f0k0CRrY */

            public final void onClick(View view) {
                LibraryContentAdapter.this.lambda$createGetMoreAppsTileHolder$134$LibraryContentAdapter(view);
            }
        });
        return new GetMoreAppsTileHolder(inflate);
    }

    public /* synthetic */ void lambda$createGetMoreAppsTileHolder$134$LibraryContentAdapter(View view) {
        this.mPanelApp.actionNavigate(SystemUXRoute.STORE, "");
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_LIBRARY_STORE);
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_LIBRARY_STORE_GET_MORE_APPS);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        LibraryItem libraryItem = this.mItems.get(i);
        boolean z = !this.mOnboardingTutorial.isActive();
        int i2 = AnonymousClass2.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$apps$models$LibraryItem$ItemType[libraryItem.getItemType().ordinal()];
        if (i2 == 1) {
            AnytimeTabletLibrarySystemAppsHeaderV2Binding anytimeTabletLibrarySystemAppsHeaderV2Binding = ((SystemAppsHeaderHolder) viewHolder).mBinding;
            anytimeTabletLibrarySystemAppsHeaderV2Binding.setBrowserApp(libraryItem.getBrowserApp());
            anytimeTabletLibrarySystemAppsHeaderV2Binding.setTvApp(libraryItem.getTvApp());
            anytimeTabletLibrarySystemAppsHeaderV2Binding.libraryStoreButton.button.setClickable(z);
            anytimeTabletLibrarySystemAppsHeaderV2Binding.librarySocialButton.button.setClickable(z);
            anytimeTabletLibrarySystemAppsHeaderV2Binding.libraryBrowserButton.button.setClickable(z);
            anytimeTabletLibrarySystemAppsHeaderV2Binding.libraryTvButton.button.setClickable(z);
            if (this.mViewModel.getHighlightHome()) {
                anytimeTabletLibrarySystemAppsHeaderV2Binding.libraryHomeButton.highlight.startAnimation();
            }
        } else if (i2 == 2) {
            ((NullStateHolder) viewHolder).mBinding.nullStateDescription.setText(getNullStateDescriptionForFilter(libraryItem.getFilter()));
        } else if (i2 == 3) {
            App app = libraryItem.getApp();
            AppTileHolder appTileHolder = (AppTileHolder) viewHolder;
            appTileHolder.mBinding.setApp(app);
            appTileHolder.mBinding.setIsInternetConnected(this.mIsInternetConnected);
            appTileHolder.mBinding.setIsTrackingEnabled(this.mIsTrackingEnabled);
            appTileHolder.mBinding.setHasContextMenu(LibraryAppUtils.hasContextMenu(app, this.mViewModel));
            appTileHolder.mBinding.setIsPrototype(LibraryAppUtils.isAppPrototype(app, this.mViewModel.getPrototypeOrganizationId()));
            appTileHolder.mBinding.setPosition(i);
            if (this.mIsOCShellAutomationEnabled) {
                appTileHolder.mBinding.cardView.setTag(app.packageName);
            }
            appTileHolder.mBinding.button.setClickable(z);
            appTileHolder.mBinding.contextMenuButton.setClickable(z);
            loadImage(app, appTileHolder.mBinding);
            this.mLibraryLogger.logAppVisibility(app, i);
        } else if (i2 == 4) {
            bindUnknownSourceRowHolder(viewHolder, i);
        } else if (i2 == 5) {
            ((GetMoreAppsTileHolder) viewHolder).mBinding.button.setClickable(z);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i, @NonNull List<Object> list) {
        if (this.mItems.get(i).getItemType() != LibraryItem.ItemType.APP || list.isEmpty()) {
            onBindViewHolder(viewHolder, i);
            return;
        }
        App app = ((LibraryItem) list.get(0)).getApp();
        AppTileHolder appTileHolder = (AppTileHolder) viewHolder;
        appTileHolder.mBinding.setApp(app);
        appTileHolder.mBinding.setIsInternetConnected(this.mIsInternetConnected);
        appTileHolder.mBinding.setIsTrackingEnabled(this.mIsTrackingEnabled);
        appTileHolder.mBinding.setHasContextMenu(LibraryAppUtils.hasContextMenu(app, this.mViewModel));
        appTileHolder.mBinding.setIsPrototype(LibraryAppUtils.isAppPrototype(app, this.mViewModel.getPrototypeOrganizationId()));
        appTileHolder.mBinding.setPosition(i);
        if (this.mIsOCShellAutomationEnabled) {
            appTileHolder.mBinding.cardView.setTag(app.packageName);
        }
        boolean z = !this.mOnboardingTutorial.isActive();
        appTileHolder.mBinding.button.setClickable(z);
        appTileHolder.mBinding.contextMenuButton.setClickable(z);
        this.mLibraryLogger.logAppVisibility(app, i);
    }

    private void bindUnknownSourceRowHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        UnknownSource unknownSource = this.mItems.get(i).getUnknownSource();
        UnknownSourceRowHolder unknownSourceRowHolder = (UnknownSourceRowHolder) viewHolder;
        unknownSourceRowHolder.mBinding.appTitle.setText(unknownSource.getApplicationName());
        unknownSourceRowHolder.mBinding.appPackageName.setText(unknownSource.getPackageName());
        unknownSourceRowHolder.mBinding.appRowButton.setOnClickListener(new View.OnClickListener(unknownSource) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$iXEbP8ycoCJD2ebQxOfrGgjaPzs */
            private final /* synthetic */ UnknownSource f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                LibraryContentAdapter.this.lambda$bindUnknownSourceRowHolder$135$LibraryContentAdapter(this.f$1, view);
            }
        });
        unknownSourceRowHolder.mBinding.appMenuIcon.setImageDrawable(getUnknownSourceRowContextMenuIcon(false));
        unknownSourceRowHolder.mBinding.appMenuButton.setOnClickListener(new View.OnClickListener(unknownSource) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$MKtnjZ1gMJRaepz86_z4B3l9tA8 */
            private final /* synthetic */ UnknownSource f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                LibraryContentAdapter.this.lambda$bindUnknownSourceRowHolder$137$LibraryContentAdapter(this.f$1, view);
            }
        });
    }

    public /* synthetic */ void lambda$bindUnknownSourceRowHolder$135$LibraryContentAdapter(UnknownSource unknownSource, View view) {
        Log.d(TAG, String.format("User clicked unknown app row to launch %s", unknownSource.getApplicationName()));
        this.mPanelApp.actionNavigate(unknownSource.getPackageName(), "");
    }

    public /* synthetic */ void lambda$bindUnknownSourceRowHolder$137$LibraryContentAdapter(UnknownSource unknownSource, View view) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(LibraryAppTileContextMenuItem.UNINSTALL);
        this.mDropdown.setItems(arrayList);
        this.mDropdown.setTitleMap(LibraryAppTileContextMenuItem.getMap());
        this.mDropdown.setOnItemClick(new Function(unknownSource) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$myWFvWvfuuRolQ9zBfqDhHkd5nI */
            private final /* synthetic */ UnknownSource f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return LibraryContentAdapter.this.lambda$null$136$LibraryContentAdapter(this.f$1, obj);
            }
        });
        this.mDropdown.setVisibilityCallback(this.mDropdownVisibilityCallback);
        this.mDropdown.setEventHandler(this.mPanelApp);
        this.mDropdown.toggle(view);
    }

    public /* synthetic */ Object lambda$null$136$LibraryContentAdapter(UnknownSource unknownSource, Object obj) {
        if (LibraryAppTileContextMenuItem.UNINSTALL != obj) {
            return null;
        }
        LibraryAppUtils.showUninstallDialog(this.mContext, this.mPanelApp, unknownSource.getApplicationName(), unknownSource.getPackageName());
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        switch (this.mItems.get(i).getItemType()) {
            case HEADER_SYSTEM_APPS:
                return 1;
            case NULL_STATE:
                return 0;
            case APP:
                return 3;
            case UNKNOWN_SOURCE:
                return 4;
            case GET_MORE_APPS:
                return 7;
            case HEADER_UNKNOWN_SOURCES:
                return 2;
            case LOADING_VIEW:
                return 5;
            case GO_GEAR_VR_BANNER:
                return 6;
            default:
                return -1;
        }
    }

    public static class SystemAppsHeaderHolder extends RecyclerView.ViewHolder {
        AnytimeTabletLibrarySystemAppsHeaderV2Binding mBinding;

        public SystemAppsHeaderHolder(AnytimeTabletLibrarySystemAppsHeaderV2Binding anytimeTabletLibrarySystemAppsHeaderV2Binding) {
            super(anytimeTabletLibrarySystemAppsHeaderV2Binding.getRoot());
            this.mBinding = anytimeTabletLibrarySystemAppsHeaderV2Binding;
        }
    }

    public static class UnknownSourcesHeaderHolder extends RecyclerView.ViewHolder {
        AnytimeTabletLibraryUnknownSourcesHeaderV2Binding mBinding;

        public UnknownSourcesHeaderHolder(AnytimeTabletLibraryUnknownSourcesHeaderV2Binding anytimeTabletLibraryUnknownSourcesHeaderV2Binding) {
            super(anytimeTabletLibraryUnknownSourcesHeaderV2Binding.getRoot());
            this.mBinding = anytimeTabletLibraryUnknownSourcesHeaderV2Binding;
        }
    }

    public static class NullStateHolder extends RecyclerView.ViewHolder {
        AnytimeTabletLibraryNullStateBinding mBinding;

        public NullStateHolder(AnytimeTabletLibraryNullStateBinding anytimeTabletLibraryNullStateBinding) {
            super(anytimeTabletLibraryNullStateBinding.getRoot());
            this.mBinding = anytimeTabletLibraryNullStateBinding;
        }
    }

    /* access modifiers changed from: private */
    public class AppTileHolder extends RecyclerView.ViewHolder {
        AnytimeTabletLibraryAppTileV2Binding mBinding;

        public AppTileHolder(AnytimeTabletLibraryAppTileV2Binding anytimeTabletLibraryAppTileV2Binding) {
            super(anytimeTabletLibraryAppTileV2Binding.getRoot());
            this.mBinding = anytimeTabletLibraryAppTileV2Binding;
        }
    }

    public static class UnknownSourceRowHolder extends RecyclerView.ViewHolder {
        AnytimeTabletLibraryUnknownSourceRowV2Binding mBinding;

        public UnknownSourceRowHolder(AnytimeTabletLibraryUnknownSourceRowV2Binding anytimeTabletLibraryUnknownSourceRowV2Binding) {
            super(anytimeTabletLibraryUnknownSourceRowV2Binding.getRoot());
            this.mBinding = anytimeTabletLibraryUnknownSourceRowV2Binding;
        }
    }

    public static class LoadingViewHolder extends RecyclerView.ViewHolder {
        OCSpinner mLoadingSpinner;

        public LoadingViewHolder(OCSpinner oCSpinner) {
            super(oCSpinner);
            this.mLoadingSpinner = oCSpinner;
        }
    }

    public static class GoGearVrBannerHolder extends RecyclerView.ViewHolder {
        AnytimeTabletLibraryMobileOculusGoBannerV2Binding mBinding;

        public GoGearVrBannerHolder(AnytimeTabletLibraryMobileOculusGoBannerV2Binding anytimeTabletLibraryMobileOculusGoBannerV2Binding) {
            super(anytimeTabletLibraryMobileOculusGoBannerV2Binding.getRoot());
            this.mBinding = anytimeTabletLibraryMobileOculusGoBannerV2Binding;
        }
    }

    public static class GetMoreAppsTileHolder extends RecyclerView.ViewHolder {
        AnytimeTabletLibraryGetMoreAppsTileV2Binding mBinding;

        public GetMoreAppsTileHolder(AnytimeTabletLibraryGetMoreAppsTileV2Binding anytimeTabletLibraryGetMoreAppsTileV2Binding) {
            super(anytimeTabletLibraryGetMoreAppsTileV2Binding.getRoot());
            this.mBinding = anytimeTabletLibraryGetMoreAppsTileV2Binding;
        }
    }

    public void setDropdownVisibilityCallback(OCDropdownVisibilityCallback oCDropdownVisibilityCallback) {
        this.mDropdownVisibilityCallback = oCDropdownVisibilityCallback;
    }

    public OCDropdown getDropdown() {
        return this.mDropdown;
    }

    private void loadImage(App app, AnytimeTabletLibraryAppTileV2Binding anytimeTabletLibraryAppTileV2Binding) {
        anytimeTabletLibraryAppTileV2Binding.imageView.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) this.mControllerBuilder.setImageRequest(ImageRequest.fromUri(LibraryAppUtils.getPreferredAppTileImageUri(app)))).setControllerListener(new FrescoControllerListener(new FrescoControllerListener.FrescoImageReadyCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.$$Lambda$LibraryContentAdapter$THYGfZzoOmHaYi7hluXm7BAuczU */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.apps.adapters.FrescoControllerListener.FrescoImageReadyCallback
            public final void onImageReady() {
                AnytimeTabletLibraryAppTileV2Binding.this.setImageAvailable(true);
            }
        }))).build());
    }

    private Drawable getUnknownSourcesHeaderOpenTabIcon(boolean z) {
        Drawable drawable;
        Drawable drawable2;
        if (z && (drawable2 = this.mUnknownSourcesHeaderOpenTabIconHighlight) != null) {
            return drawable2;
        }
        if (!z && (drawable = this.mUnknownSourcesHeaderOpenTabIcon) != null) {
            return drawable;
        }
        Drawable icon = getIcon(z, R.drawable.oc_icon_open_tab_filled_24_d2d2d2);
        if (z) {
            this.mUnknownSourcesHeaderOpenTabIconHighlight = icon;
        } else {
            this.mUnknownSourcesHeaderOpenTabIcon = icon;
        }
        return icon;
    }

    private Drawable getUnknownSourceRowContextMenuIcon(boolean z) {
        Drawable drawable;
        Drawable drawable2;
        if (z && (drawable2 = this.mUnknownSourceRowContextMenuIconHighlight) != null) {
            return drawable2;
        }
        if (!z && (drawable = this.mUnknownSourceRowContextMenuIcon) != null) {
            return drawable;
        }
        Drawable icon = getIcon(z, R.drawable.ic_more_vertical_default);
        if (z) {
            this.mUnknownSourceRowContextMenuIconHighlight = icon;
        } else {
            this.mUnknownSourceRowContextMenuIcon = icon;
        }
        return icon;
    }

    private Drawable getCloseBannerIcon(boolean z) {
        Drawable drawable;
        Drawable drawable2;
        if (z && (drawable2 = this.mGoGearVrBannerCloseIconHighlight) != null) {
            return drawable2;
        }
        if (!z && (drawable = this.mGoGearVrBannerCloseIcon) != null) {
            return drawable;
        }
        Drawable icon = getIcon(z, R.drawable.ic_close);
        if (z) {
            this.mGoGearVrBannerCloseIconHighlight = icon;
        } else {
            this.mGoGearVrBannerCloseIcon = icon;
        }
        return icon;
    }

    private Drawable getIcon(boolean z, int i) {
        Drawable wrap = DrawableCompat.wrap(this.mContext.getResources().getDrawable(i, null));
        DrawableCompat.setTint(wrap, this.mContext.getResources().getColor(z ? R.color.oc_white : R.color.oc_gray_30, null));
        return wrap;
    }

    private String getNullStateDescriptionForFilter(LibraryFilter libraryFilter) {
        Resources resources = this.mContext.getResources();
        switch (libraryFilter) {
            case ALL:
                return resources.getString(R.string.anytime_tablet_library_null_state_all_apps_description);
            case INSTALLED:
                return resources.getString(R.string.anytime_tablet_library_null_state_installed_apps_description);
            case DEMOS:
                return resources.getString(R.string.anytime_tablet_library_null_state_demos_description);
            case NOT_INSTALLED:
                return resources.getString(R.string.anytime_tablet_library_null_state_not_installed_apps_description);
            case OCULUS_APPS:
                return resources.getString(R.string.anytime_tablet_library_null_state_oculus_apps_description);
            case UPDATES:
                return resources.getString(R.string.anytime_tablet_library_null_state_updates_description);
            case UNKNOWN_SOURCES:
                return resources.getString(R.string.anytime_tablet_library_null_state_unknown_sources_description);
            default:
                return resources.getString(R.string.anytime_tablet_library_null_state_default_description);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean getIsTrackingEnabled() {
        return !this.mSettingsViewModel.getIsTrackingIn3DOFMode();
    }
}
