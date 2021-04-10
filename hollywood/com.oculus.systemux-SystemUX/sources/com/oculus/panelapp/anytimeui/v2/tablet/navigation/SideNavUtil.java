package com.oculus.panelapp.anytimeui.v2.tablet.navigation;

import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import com.oculus.ocui.OCSideNav;
import com.oculus.ocui.OCSideNavAdapter;
import com.oculus.ocui.OCSideNavItem;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.tooltip.TooltipOnHoverListener;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNavUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsViewModel;
import com.oculus.vrshell.panels.systemtooltip.TooltipColor;
import com.oculus.vrshell.panels.systemtooltip.TooltipDefinition;
import com.oculus.vrshell.panels.systemtooltip.TooltipPosition;
import java.util.function.Function;

public class SideNavUtil {
    private static final String GUARDIAN_DISABLED_TOOLTIP_ID = "settings_side_nav_guardian_disabled";
    private final AnytimeUIAndroidPanelAppV2 mPanelApp;
    private final Resources mResources;
    private SettingsViewModel.SettingObserver mSettingObserver;
    private final SettingsViewModel mSettingsViewModel = this.mPanelApp.acquireSettingsViewModel();

    public SideNavUtil(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Resources resources) {
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mResources = resources;
    }

    public void initializeSideNav(TabletNav tabletNav, final OCSideNav oCSideNav) {
        oCSideNav.setTitle(this.mResources.getString(TabletNav.getTitleForTabletNav(tabletNav)));
        oCSideNav.setItems(this.mPanelApp.getTabletNavUtil().getNavItems(tabletNav));
        oCSideNav.setSelectedItem(this.mPanelApp.getTabletNavUtil().getSelectedNavItem(tabletNav));
        oCSideNav.setEventHandler(this.mPanelApp);
        oCSideNav.setOnItemClick(new Function(tabletNav) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.navigation.$$Lambda$SideNavUtil$7LW4Mxb9FEZwa3pIPwzrzKsJTak */
            private final /* synthetic */ TabletNav f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SideNavUtil.this.lambda$initializeSideNav$69$SideNavUtil(this.f$1, (OCSideNavItem) obj);
            }
        });
        if (tabletNav == TabletNav.SETTINGS) {
            oCSideNav.setOnDisabledItemHover(new OCSideNavAdapter.DisabledItemHoverCallback() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.navigation.$$Lambda$SideNavUtil$wsC63VeoWKcQfYUoFUjpqoPgSwI */

                @Override // com.oculus.ocui.OCSideNavAdapter.DisabledItemHoverCallback
                public final void onDisabledItemHover(OCSideNavItem oCSideNavItem, View view, MotionEvent motionEvent) {
                    SideNavUtil.this.lambda$initializeSideNav$70$SideNavUtil(oCSideNavItem, view, motionEvent);
                }
            });
            this.mPanelApp.getTabletNavUtil().addUpdateListener(tabletNav, new TabletNavUtil.TabletNavItemsUpdateListener(oCSideNav, tabletNav) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.navigation.$$Lambda$SideNavUtil$6vCxaBMUy5xGDN3WVjbNb5Da5eE */
                private final /* synthetic */ OCSideNav f$1;
                private final /* synthetic */ TabletNav f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNavUtil.TabletNavItemsUpdateListener
                public final void onUpdate() {
                    SideNavUtil.this.lambda$initializeSideNav$71$SideNavUtil(this.f$1, this.f$2);
                }
            });
            this.mSettingObserver = new SettingsViewModel.SettingObserver() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.navigation.SideNavUtil.AnonymousClass1 */

                @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsViewModel.SettingObserver
                public void onGuardianStateChange(boolean z, boolean z2) {
                }

                @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsViewModel.SettingObserver
                public void onTrackingStatusChanged(boolean z) {
                    for (OCSideNavItem oCSideNavItem : oCSideNav.getItems()) {
                        if (oCSideNavItem.getViewId().intValue() == R.id.side_nav_settings_guardian) {
                            oCSideNavItem.setEnabled(z);
                            if (!z && oCSideNav.getSelectedItem().getViewId().intValue() == R.id.side_nav_settings_guardian) {
                                oCSideNav.setSelectedItemByID(R.id.side_nav_settings_device);
                                return;
                            }
                            return;
                        }
                    }
                }
            };
            this.mSettingsViewModel.addSettingObserver(this.mSettingObserver);
            this.mSettingObserver.onTrackingStatusChanged(this.mSettingsViewModel.getGuardianTabEnabled());
        }
    }

    public /* synthetic */ Object lambda$initializeSideNav$69$SideNavUtil(TabletNav tabletNav, OCSideNavItem oCSideNavItem) {
        TabletNavUtil tabletNavUtil = this.mPanelApp.getTabletNavUtil();
        tabletNavUtil.clickSideNavItem(tabletNav, oCSideNavItem);
        tabletNavUtil.selectSideNavItem(tabletNav, oCSideNavItem.getViewId().intValue());
        return null;
    }

    public /* synthetic */ void lambda$initializeSideNav$70$SideNavUtil(OCSideNavItem oCSideNavItem, View view, MotionEvent motionEvent) {
        if (oCSideNavItem.getViewId().intValue() == R.id.side_nav_settings_guardian) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 9) {
                this.mPanelApp.getSystemTooltipController().showTooltip(GetGuardianDisabledTooltipDefinition(), TooltipOnHoverListener.getTooltipUVCoordinatesForView(view, TooltipPosition.Top));
            } else if (actionMasked == 10) {
                this.mPanelApp.getSystemTooltipController().hideTooltip(GUARDIAN_DISABLED_TOOLTIP_ID);
            }
        }
    }

    public /* synthetic */ void lambda$initializeSideNav$71$SideNavUtil(OCSideNav oCSideNav, TabletNav tabletNav) {
        oCSideNav.setItems(this.mPanelApp.getTabletNavUtil().reloadNavItems(tabletNav));
    }

    private TooltipDefinition GetGuardianDisabledTooltipDefinition() {
        return new TooltipDefinition(AnytimeUIAndroidPanelAppV2.TABLET_NAV_LAYER, this.mResources.getString(R.string.anytime_settings_tooltip_disabled_guardian), "", TooltipPosition.Top, TooltipColor.Gray, GUARDIAN_DISABLED_TOOLTIP_ID);
    }

    public void destroy() {
        SettingsViewModel.SettingObserver settingObserver = this.mSettingObserver;
        if (settingObserver != null) {
            this.mSettingsViewModel.removeSettingObserver(settingObserver);
        }
        this.mPanelApp.releaseSettingsViewModel();
    }
}
