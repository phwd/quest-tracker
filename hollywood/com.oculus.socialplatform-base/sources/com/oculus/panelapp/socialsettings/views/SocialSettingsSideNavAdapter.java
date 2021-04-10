package com.oculus.panelapp.socialsettings.views;

import X.AnonymousClass1Aj;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.socialsettings.SocialSettingsTabletPanelApp;
import com.oculus.panelapp.socialsettings.databinding.SocialSettingsSideNavItemBinding;
import java.util.ArrayList;
import java.util.List;

public class SocialSettingsSideNavAdapter extends AnonymousClass1Aj<SocialSettingsSideNavViewHolder> {
    public static final String TAG = LoggingUtil.tag(SocialSettingsSideNavAdapter.class);
    public SocialSettingsTabletPanelApp mPanelApp;
    public int mSelectedItemPosition;
    public List<SocialSettingsSideNavItem> mSideNavItems;

    /* renamed from: com.oculus.panelapp.socialsettings.views.SocialSettingsSideNavAdapter$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$socialsettings$views$SocialSettingsSideNavItem;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        static {
            /*
                com.oculus.panelapp.socialsettings.views.SocialSettingsSideNavItem[] r0 = com.oculus.panelapp.socialsettings.views.SocialSettingsSideNavItem.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.panelapp.socialsettings.views.SocialSettingsSideNavAdapter.AnonymousClass2.$SwitchMap$com$oculus$panelapp$socialsettings$views$SocialSettingsSideNavItem = r2
                com.oculus.panelapp.socialsettings.views.SocialSettingsSideNavItem r0 = com.oculus.panelapp.socialsettings.views.SocialSettingsSideNavItem.ACTIVE_STATUS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.panelapp.socialsettings.views.SocialSettingsSideNavItem r0 = com.oculus.panelapp.socialsettings.views.SocialSettingsSideNavItem.NOTIFICATIONS     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.panelapp.socialsettings.views.SocialSettingsSideNavItem r0 = com.oculus.panelapp.socialsettings.views.SocialSettingsSideNavItem.MESSENGER_ACCOUNT     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.socialsettings.views.SocialSettingsSideNavAdapter.AnonymousClass2.<clinit>():void");
        }
    }

    private List<SocialSettingsSideNavItem> getSideNavItems() {
        return new ArrayList<SocialSettingsSideNavItem>() {
            /* class com.oculus.panelapp.socialsettings.views.SocialSettingsSideNavAdapter.AnonymousClass1 */

            {
                add(SocialSettingsSideNavItem.ACTIVE_STATUS);
                add(SocialSettingsSideNavItem.NOTIFICATIONS);
                add(SocialSettingsSideNavItem.MESSENGER_ACCOUNT);
            }
        };
    }

    @Override // X.AnonymousClass1Aj
    public int getItemCount() {
        return this.mSideNavItems.size();
    }

    public void initialize(SocialSettingsTabletPanelApp socialSettingsTabletPanelApp) {
        this.mPanelApp = socialSettingsTabletPanelApp;
        this.mSideNavItems = new ArrayList<SocialSettingsSideNavItem>() {
            /* class com.oculus.panelapp.socialsettings.views.SocialSettingsSideNavAdapter.AnonymousClass1 */

            {
                add(SocialSettingsSideNavItem.ACTIVE_STATUS);
                add(SocialSettingsSideNavItem.NOTIFICATIONS);
                add(SocialSettingsSideNavItem.MESSENGER_ACCOUNT);
            }
        };
        this.mSelectedItemPosition = 0;
        updateSelectedSetting(SocialSettingsSideNavItem.ACTIVE_STATUS);
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$SocialSettingsSideNavAdapter(SocialSettingsSideNavViewHolder socialSettingsSideNavViewHolder, View view) {
        if (this.mSelectedItemPosition != socialSettingsSideNavViewHolder.getBindingAdapterPosition()) {
            select(this.mSideNavItems.get(socialSettingsSideNavViewHolder.getBindingAdapterPosition()));
        }
    }

    public void select(SocialSettingsSideNavItem socialSettingsSideNavItem) {
        int indexOf = this.mSideNavItems.indexOf(socialSettingsSideNavItem);
        if (indexOf < 0) {
            Log.e(TAG, "select called with invalid SideNavItem.");
            return;
        }
        int i = this.mSelectedItemPosition;
        this.mSelectedItemPosition = indexOf;
        notifyItemChanged(i);
        notifyItemChanged(this.mSelectedItemPosition);
        updateSelectedSetting(socialSettingsSideNavItem);
    }

    private void updateSelectedSetting(SocialSettingsSideNavItem socialSettingsSideNavItem) {
        SocialSettingsTabletPanelApp socialSettingsTabletPanelApp;
        SocialSettingsViewType socialSettingsViewType;
        switch (socialSettingsSideNavItem.ordinal()) {
            case 0:
                socialSettingsTabletPanelApp = this.mPanelApp;
                socialSettingsViewType = SocialSettingsViewType.ACTIVE_STATUS;
                break;
            case 1:
                socialSettingsTabletPanelApp = this.mPanelApp;
                socialSettingsViewType = SocialSettingsViewType.NOTIFICATIONS;
                break;
            case 2:
                socialSettingsTabletPanelApp = this.mPanelApp;
                socialSettingsViewType = SocialSettingsViewType.MESSENGER_ACCOUNT;
                break;
            default:
                return;
        }
        socialSettingsTabletPanelApp.updateSocialSettingsViewType(socialSettingsViewType);
    }

    public SocialSettingsSideNavAdapter(Context context) {
    }

    public void onBindViewHolder(SocialSettingsSideNavViewHolder socialSettingsSideNavViewHolder, int i) {
        SocialSettingsSideNavItem socialSettingsSideNavItem = this.mSideNavItems.get(socialSettingsSideNavViewHolder.getBindingAdapterPosition());
        boolean z = false;
        if (this.mSelectedItemPosition == socialSettingsSideNavViewHolder.getBindingAdapterPosition()) {
            z = true;
        }
        socialSettingsSideNavViewHolder.bind(socialSettingsSideNavItem, z);
        socialSettingsSideNavViewHolder.setOnClickListener(new View.OnClickListener(socialSettingsSideNavViewHolder) {
            /* class com.oculus.panelapp.socialsettings.views.$$Lambda$SocialSettingsSideNavAdapter$NpEQZNXyKd_O9wEiXSuIJ_DRzAo2 */
            public final /* synthetic */ SocialSettingsSideNavViewHolder f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                SocialSettingsSideNavAdapter.this.lambda$onBindViewHolder$0$SocialSettingsSideNavAdapter(this.f$1, view);
            }
        });
    }

    @Override // X.AnonymousClass1Aj
    public SocialSettingsSideNavViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SocialSettingsSideNavViewHolder(this.mPanelApp, SocialSettingsSideNavItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), null));
    }
}
