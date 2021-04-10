package com.oculus.panelapp.anytimeui.v2.tablet.common;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import androidx.databinding.ViewDataBinding;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsViewBinding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletContentEmptyViewV2Binding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletInternalSettingsGeneralViewV2Binding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletLoadingViewV2Binding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletSettingsViewV2Binding;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.empty.EmptyContentView;
import com.oculus.panelapp.anytimeui.v2.tablet.internalsettings.InternalSettingsGeneralView;
import com.oculus.panelapp.anytimeui.v2.tablet.loading.LoadingView;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.AndroidSettingsView;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsView;
import com.oculus.tablet.view.BaseView;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ContentHostView extends LinearLayout {
    private static final String TAG = LoggingUtil.tag(ContentHostView.class);
    private boolean mCurrentTabletContentShown;
    private String mCurrentTabletContentUri;
    private BaseView mCurrentTabletContentView;
    private String mLayerName;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private Map<TabletContentView, BaseView> mTabletContentViews;

    /* access modifiers changed from: private */
    public enum TabletContentView {
        ANDROID_SETTINGS,
        EMPTY,
        INTERNAL_SETTINGS_GENERAL,
        LOADING,
        SETTINGS
    }

    public ContentHostView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Log.d(TAG, "Constructing ContentHostView");
    }

    public void initialize(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, String str) {
        Log.d(TAG, "Initializing ContentHostView");
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mLayerName = str;
        this.mTabletContentViews = new HashMap();
        showTablet(this.mPanelApp.getDefaultTablet(), "");
    }

    public void destroy() {
        Log.d(TAG, "Destroying ContentHostView");
        for (BaseView baseView : this.mTabletContentViews.values()) {
            if (baseView == this.mCurrentTabletContentView && this.mCurrentTabletContentShown) {
                baseView.onHide();
            }
            baseView.destroy();
            this.mPanelApp.getSystemTooltipController().removeTooltipsOnSubtree(baseView);
        }
        removeAllViews();
    }

    public void clearViewCache(long j) {
        Iterator<Map.Entry<TabletContentView, BaseView>> it = this.mTabletContentViews.entrySet().iterator();
        long currentTimeMillis = System.currentTimeMillis();
        while (it.hasNext()) {
            BaseView value = it.next().getValue();
            boolean z = true;
            boolean z2 = (currentTimeMillis - value.getLastUsedMillis()) / 1000 >= j;
            if (value != this.mCurrentTabletContentView) {
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

    /* JADX DEBUG: Multi-variable search result rejected for r1v6, resolved type: com.oculus.panelapp.anytimeui.v2.tablet.internalsettings.InternalSettingsGeneralView */
    /* JADX DEBUG: Multi-variable search result rejected for r1v8, resolved type: com.oculus.panelapp.anytimeui.v2.tablet.loading.LoadingView */
    /* JADX DEBUG: Multi-variable search result rejected for r1v10, resolved type: com.oculus.panelapp.anytimeui.v2.tablet.empty.EmptyContentView */
    /* JADX DEBUG: Multi-variable search result rejected for r1v13, resolved type: com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsView */
    /* JADX WARN: Multi-variable type inference failed */
    private BaseView ensureCreateView(TabletContentView tabletContentView) {
        AndroidSettingsView androidSettingsView;
        if (this.mTabletContentViews.containsKey(tabletContentView)) {
            return this.mTabletContentViews.get(tabletContentView);
        }
        int i = AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$common$ContentHostView$TabletContentView[tabletContentView.ordinal()];
        if (i == 1) {
            AnytimeTabletAndroidSettingsViewBinding inflate = AnytimeTabletAndroidSettingsViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
            AndroidSettingsView androidSettingsView2 = (AndroidSettingsView) inflate.getRoot();
            androidSettingsView2.initialize(this.mPanelApp, (ViewDataBinding) inflate);
            androidSettingsView = androidSettingsView2;
        } else if (i == 2) {
            AnytimeTabletInternalSettingsGeneralViewV2Binding inflate2 = AnytimeTabletInternalSettingsGeneralViewV2Binding.inflate(LayoutInflater.from(getContext()), this, true);
            InternalSettingsGeneralView internalSettingsGeneralView = (InternalSettingsGeneralView) inflate2.getRoot();
            internalSettingsGeneralView.initialize(this.mPanelApp, (ViewDataBinding) inflate2);
            androidSettingsView = internalSettingsGeneralView;
        } else if (i == 3) {
            AnytimeTabletLoadingViewV2Binding inflate3 = AnytimeTabletLoadingViewV2Binding.inflate(LayoutInflater.from(getContext()), this, true);
            LoadingView loadingView = (LoadingView) inflate3.getRoot();
            loadingView.initialize(this.mPanelApp, inflate3, ContentLayout.RIGHT_HAND_SIDE);
            androidSettingsView = loadingView;
        } else if (i == 4) {
            AnytimeTabletContentEmptyViewV2Binding inflate4 = AnytimeTabletContentEmptyViewV2Binding.inflate(LayoutInflater.from(getContext()), this, true);
            EmptyContentView emptyContentView = (EmptyContentView) inflate4.getRoot();
            emptyContentView.initialize(this.mPanelApp, inflate4);
            androidSettingsView = emptyContentView;
        } else if (i == 5) {
            AnytimeTabletSettingsViewV2Binding inflate5 = AnytimeTabletSettingsViewV2Binding.inflate(LayoutInflater.from(getContext()), this, true);
            SettingsView settingsView = (SettingsView) inflate5.getRoot();
            settingsView.initialize(this.mPanelApp, (ViewDataBinding) inflate5);
            androidSettingsView = settingsView;
        } else {
            throw new IllegalArgumentException("Unhandled tablet content view: " + tabletContentView);
        }
        this.mPanelApp.getSystemTooltipController().initializeTooltipsOnSubtree(androidSettingsView, this.mLayerName, R.id.tooltip_text, R.id.tooltip_subtext, R.id.tooltip_data);
        this.mTabletContentViews.put(tabletContentView, androidSettingsView);
        return androidSettingsView;
    }

    public void showTablet(Tablet tablet, String str) {
        this.mCurrentTabletContentShown = true;
        this.mCurrentTabletContentUri = str;
        BaseView ensureCreateView = ensureCreateView(mapTabletToContentView(tablet));
        BaseView baseView = this.mCurrentTabletContentView;
        if (baseView != ensureCreateView) {
            if (baseView != null) {
                baseView.markUsed();
                this.mCurrentTabletContentView.setVisibility(8);
                this.mCurrentTabletContentView.onHide();
            }
            ensureCreateView.onShow(str);
            ensureCreateView.setVisibility(0);
            this.mCurrentTabletContentView = ensureCreateView;
        } else if (tablet == Tablet.ANDROID_SETTINGS) {
            this.mCurrentTabletContentView.onShow(str);
        }
    }

    public void onTabletLayerShown() {
        BaseView baseView = this.mCurrentTabletContentView;
        if (baseView != null && !this.mCurrentTabletContentShown) {
            baseView.onShow(this.mCurrentTabletContentUri);
            this.mCurrentTabletContentShown = true;
        }
    }

    public void onTabletLayerHidden() {
        BaseView baseView = this.mCurrentTabletContentView;
        if (baseView != null && this.mCurrentTabletContentShown) {
            baseView.onHide();
            this.mCurrentTabletContentShown = false;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.common.ContentHostView$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$common$ContentHostView$TabletContentView = new int[TabletContentView.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(74:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|(2:71|72)|73|75|76|77|78|79|80|81|82|(3:83|84|86)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(75:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|75|76|77|78|79|80|81|82|(3:83|84|86)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(77:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|75|76|77|78|79|80|81|82|83|84|86) */
        /* JADX WARNING: Can't wrap try/catch for region: R(78:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|75|76|77|78|79|80|81|82|83|84|86) */
        /* JADX WARNING: Can't wrap try/catch for region: R(79:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|75|76|77|78|79|80|81|82|83|84|86) */
        /* JADX WARNING: Can't wrap try/catch for region: R(80:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|75|76|77|78|79|80|81|82|83|84|86) */
        /* JADX WARNING: Can't wrap try/catch for region: R(81:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|75|76|77|78|79|80|81|82|83|84|86) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x007a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0086 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0092 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x009e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00aa */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00b6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00c2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00ce */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00da */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00e6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00f2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x00fe */
        /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x010a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x0116 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:57:0x0122 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:59:0x012e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:61:0x013a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x0146 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x0152 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:67:0x015e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:69:0x016a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:71:0x0176 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:77:0x0195 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:79:0x019f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:81:0x01a9 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:83:0x01b3 */
        static {
            /*
            // Method dump skipped, instructions count: 446
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.tablet.common.ContentHostView.AnonymousClass1.<clinit>():void");
        }
    }

    private TabletContentView mapTabletToContentView(Tablet tablet) {
        switch (tablet) {
            case ANDROID_SETTINGS:
                return TabletContentView.ANDROID_SETTINGS;
            case INTERNAL_SETTINGS_GENERAL:
                return TabletContentView.INTERNAL_SETTINGS_GENERAL;
            case SETTINGS_LOADING:
                return TabletContentView.LOADING;
            case ANDROID_LIBRARY:
            case CHATS_LOADED:
            case CHATS_LOADING:
            case LIBRARY_STANDALONE_LOADED:
            case LIBRARY_STANDALONE_LOADING:
            case NONE:
            case NOTIFICATIONS:
            case PAUSE:
            case PROFILE:
            case SETTINGS_LOADED:
            case SHARING:
            case MESSENGER_LOADED:
            case MESSENGER_LOADING:
            case PARTIES_LOADED:
            case PARTIES_LOADING:
            case PEOPLE_LOADED:
            case PEOPLE_LOADING:
            case PEOPLE_FB_LOADED:
            case PEOPLE_FB_LOADING:
            case SOCIAL:
            case SOCIAL_LOADED:
            case SOCIAL_LOADING:
            case SOCIAL_REAUTH_LOADED:
            case SOCIAL_REAUTH_LOADING:
            case SOCIAL_SETTINGS_LOADED:
            case SOCIAL_SETTINGS_LOADING:
            case TABLET_LOADED:
            case TABLET_LOADING:
                return TabletContentView.EMPTY;
            case SETTINGS:
                return TabletContentView.SETTINGS;
            default:
                throw new IllegalArgumentException("Unhandled tablet: " + tablet);
        }
    }

    public String getInstantiatedViewsForLogging() {
        return TextUtils.join(",", this.mTabletContentViews.keySet());
    }
}
