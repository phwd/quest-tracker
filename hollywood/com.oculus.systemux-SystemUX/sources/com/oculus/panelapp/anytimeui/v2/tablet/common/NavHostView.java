package com.oculus.panelapp.anytimeui.v2.tablet.common;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.ocui.OCSideNavItem;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletInternalSettingsNavViewV2Binding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletSettingsNavViewV2Binding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletSideNavEmptyViewV2Binding;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.navigation.SideNavUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav;
import com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNavUtil;
import com.oculus.vrshell.SystemUXRoute;
import java.util.HashMap;
import java.util.Map;

public class NavHostView extends LinearLayout {
    private static final String TAG = LoggingUtil.tag(NavHostView.class);
    private View mCurrentNavView;
    private TabletNavUtil.TabletNavClickListener mInternalSettingsClickListener;
    private AnytimeTabletInternalSettingsNavViewV2Binding mInternalSettingsNavViewBinding;
    private TabletNavUtil.TabletNavSelectListener mInternalSettingsSelectListener;
    private Map<NavView, View> mNavViews;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private TabletNavUtil.TabletNavClickListener mSettingsClickListener;
    private AnytimeTabletSettingsNavViewV2Binding mSettingsNavViewBinding;
    private TabletNavUtil.TabletNavSelectListener mSettingsSelectListener;

    /* access modifiers changed from: private */
    public enum NavView {
        EMPTY,
        INTERNAL_SETTINGS,
        SETTINGS
    }

    public NavHostView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.d(TAG, "Constructing NavHostView");
    }

    public void initialize(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        Log.d(TAG, "Initializing NavHostView");
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mNavViews = new HashMap();
        initializeTabletNavClickListeners();
        initializeTabletNavSelectListeners();
        showTablet(this.mPanelApp.getDefaultTablet(), this.mPanelApp.getDefaultUri());
    }

    public void destroy() {
        Log.d(TAG, "Destroying NavHostView");
        TabletNavUtil tabletNavUtil = this.mPanelApp.getTabletNavUtil();
        tabletNavUtil.removeClickListener(this.mInternalSettingsClickListener);
        tabletNavUtil.removeClickListener(this.mSettingsClickListener);
        tabletNavUtil.removeSelectListener(this.mInternalSettingsSelectListener);
        tabletNavUtil.removeSelectListener(this.mSettingsSelectListener);
        removeAllViews();
    }

    private View ensureCreateView(NavView navView) {
        View view;
        if (this.mNavViews.containsKey(navView)) {
            return this.mNavViews.get(navView);
        }
        SideNavUtil sideNavUtil = this.mPanelApp.getSideNavUtil();
        int i = AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$common$NavHostView$NavView[navView.ordinal()];
        if (i == 1) {
            this.mInternalSettingsNavViewBinding = AnytimeTabletInternalSettingsNavViewV2Binding.inflate(LayoutInflater.from(getContext()), this, true);
            sideNavUtil.initializeSideNav(TabletNav.INTERNAL_SETTINGS, this.mInternalSettingsNavViewBinding.sideNav);
            view = this.mInternalSettingsNavViewBinding.getRoot();
        } else if (i == 2) {
            this.mSettingsNavViewBinding = AnytimeTabletSettingsNavViewV2Binding.inflate(LayoutInflater.from(getContext()), this, true);
            sideNavUtil.initializeSideNav(TabletNav.SETTINGS, this.mSettingsNavViewBinding.sideNav);
            view = this.mSettingsNavViewBinding.getRoot();
        } else if (i == 3) {
            view = AnytimeTabletSideNavEmptyViewV2Binding.inflate(LayoutInflater.from(getContext()), this, true).getRoot();
        } else {
            throw new IllegalArgumentException("Unhandled nav view: " + navView);
        }
        this.mNavViews.put(navView, view);
        return view;
    }

    public void showTablet(Tablet tablet, String str) {
        TabletNavUtil tabletNavUtil = this.mPanelApp.getTabletNavUtil();
        View ensureCreateView = ensureCreateView(mapTabletToNavView(tablet));
        tabletNavUtil.onDeeplinkUri(str);
        View view = this.mCurrentNavView;
        if (view != ensureCreateView) {
            if (view != null) {
                view.setVisibility(8);
            }
            ensureCreateView.setVisibility(0);
            this.mCurrentNavView = ensureCreateView;
        }
    }

    private void initializeTabletNavClickListeners() {
        this.mInternalSettingsClickListener = new TabletNavUtil.TabletNavClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.common.$$Lambda$NavHostView$K0GUnbFdmplGwSKj0TUzDxo_2lM */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNavUtil.TabletNavClickListener
            public final void onClick(OCSideNavItem oCSideNavItem) {
                NavHostView.this.lambda$initializeTabletNavClickListeners$58$NavHostView(oCSideNavItem);
            }
        };
        this.mSettingsClickListener = new TabletNavUtil.TabletNavClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.common.$$Lambda$NavHostView$xsBPlWA6seYKEXYOXRVqMDl9pag */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNavUtil.TabletNavClickListener
            public final void onClick(OCSideNavItem oCSideNavItem) {
                NavHostView.this.lambda$initializeTabletNavClickListeners$59$NavHostView(oCSideNavItem);
            }
        };
        TabletNavUtil tabletNavUtil = this.mPanelApp.getTabletNavUtil();
        tabletNavUtil.addClickListener(TabletNav.INTERNAL_SETTINGS, this.mInternalSettingsClickListener);
        tabletNavUtil.addClickListener(TabletNav.SETTINGS, this.mSettingsClickListener);
    }

    public /* synthetic */ void lambda$initializeTabletNavClickListeners$58$NavHostView(OCSideNavItem oCSideNavItem) {
        this.mPanelApp.actionNavigate(SystemUXRoute.AUI_INTERNAL_SETTINGS.path(), oCSideNavItem.getUri());
    }

    public /* synthetic */ void lambda$initializeTabletNavClickListeners$59$NavHostView(OCSideNavItem oCSideNavItem) {
        this.mPanelApp.actionNavigate(SystemUXRoute.SETTINGS.path(), oCSideNavItem.getUri());
    }

    private void initializeTabletNavSelectListeners() {
        this.mInternalSettingsSelectListener = new TabletNavUtil.TabletNavSelectListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.common.$$Lambda$NavHostView$dBcpQXyQj0nBdt6xugD2agJrhU8 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNavUtil.TabletNavSelectListener
            public final void onSelect(OCSideNavItem oCSideNavItem) {
                NavHostView.this.lambda$initializeTabletNavSelectListeners$60$NavHostView(oCSideNavItem);
            }
        };
        this.mSettingsSelectListener = new TabletNavUtil.TabletNavSelectListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.common.$$Lambda$NavHostView$w21z_ZuUwaHoYKmxem_AOTFpnY */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNavUtil.TabletNavSelectListener
            public final void onSelect(OCSideNavItem oCSideNavItem) {
                NavHostView.this.lambda$initializeTabletNavSelectListeners$61$NavHostView(oCSideNavItem);
            }
        };
        TabletNavUtil tabletNavUtil = this.mPanelApp.getTabletNavUtil();
        tabletNavUtil.addSelectListener(TabletNav.INTERNAL_SETTINGS, this.mInternalSettingsSelectListener);
        tabletNavUtil.addSelectListener(TabletNav.SETTINGS, this.mSettingsSelectListener);
    }

    public /* synthetic */ void lambda$initializeTabletNavSelectListeners$60$NavHostView(OCSideNavItem oCSideNavItem) {
        AnytimeTabletInternalSettingsNavViewV2Binding anytimeTabletInternalSettingsNavViewV2Binding = this.mInternalSettingsNavViewBinding;
        if (anytimeTabletInternalSettingsNavViewV2Binding != null) {
            anytimeTabletInternalSettingsNavViewV2Binding.sideNav.setSelectedItem(oCSideNavItem);
        }
    }

    public /* synthetic */ void lambda$initializeTabletNavSelectListeners$61$NavHostView(OCSideNavItem oCSideNavItem) {
        AnytimeTabletSettingsNavViewV2Binding anytimeTabletSettingsNavViewV2Binding = this.mSettingsNavViewBinding;
        if (anytimeTabletSettingsNavViewV2Binding != null) {
            anytimeTabletSettingsNavViewV2Binding.sideNav.setSelectedItem(oCSideNavItem);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.common.NavHostView$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$common$NavHostView$NavView = new int[NavView.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(70:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|(2:67|68)|69|71|72|73|74|(3:75|76|78)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(71:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|71|72|73|74|(3:75|76|78)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(73:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|71|72|73|74|75|76|78) */
        /* JADX WARNING: Can't wrap try/catch for region: R(74:0|(2:1|2)|3|5|6|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|71|72|73|74|75|76|78) */
        /* JADX WARNING: Can't wrap try/catch for region: R(75:0|1|2|3|5|6|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|71|72|73|74|75|76|78) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0035 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x007a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0086 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0092 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x009e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00aa */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00b6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00c2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00ce */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00da */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00e6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00f2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00fe */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x010a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x0116 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x0122 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x012e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:57:0x013a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:59:0x0146 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:61:0x0152 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x015e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x016a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:67:0x0176 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:73:0x0195 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:75:0x019f */
        static {
            /*
            // Method dump skipped, instructions count: 426
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.tablet.common.NavHostView.AnonymousClass1.<clinit>():void");
        }
    }

    private NavView mapTabletToNavView(Tablet tablet) {
        switch (tablet) {
            case INTERNAL_SETTINGS_GENERAL:
                return NavView.INTERNAL_SETTINGS;
            case ANDROID_SETTINGS:
            case SETTINGS_LOADED:
            case SETTINGS_LOADING:
            case SETTINGS:
                return NavView.SETTINGS;
            case ANDROID_LIBRARY:
            case CHATS_LOADED:
            case CHATS_LOADING:
            case LIBRARY_STANDALONE_LOADED:
            case LIBRARY_STANDALONE_LOADING:
            case MESSENGER_LOADED:
            case MESSENGER_LOADING:
            case NONE:
            case NOTIFICATIONS:
            case PARTIES_LOADED:
            case PARTIES_LOADING:
            case PAUSE:
            case PEOPLE_LOADED:
            case PEOPLE_LOADING:
            case PEOPLE_FB_LOADED:
            case PEOPLE_FB_LOADING:
            case PROFILE:
            case SHARING:
            case SOCIAL:
            case SOCIAL_LOADED:
            case SOCIAL_LOADING:
            case SOCIAL_REAUTH_LOADED:
            case SOCIAL_REAUTH_LOADING:
            case SOCIAL_SETTINGS_LOADED:
            case SOCIAL_SETTINGS_LOADING:
            case TABLET_LOADED:
            case TABLET_LOADING:
                return NavView.EMPTY;
            default:
                throw new IllegalArgumentException("Unhandled tablet: " + tablet);
        }
    }
}
