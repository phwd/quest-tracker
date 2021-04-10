package com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters;

import androidx.recyclerview.widget.DiffUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.ManagedLauncherItem;
import java.util.List;

public class ManagedLauncherContentAdapterDiffUtilCallback extends DiffUtil.Callback {
    private final List<ManagedLauncherItem> mNewList;
    private final List<ManagedLauncherItem> mOldList;

    public ManagedLauncherContentAdapterDiffUtilCallback(List<ManagedLauncherItem> list, List<ManagedLauncherItem> list2) {
        this.mNewList = list;
        this.mOldList = list2;
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public int getOldListSize() {
        return this.mOldList.size();
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public int getNewListSize() {
        return this.mNewList.size();
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public boolean areItemsTheSame(int i, int i2) {
        ManagedLauncherItem managedLauncherItem = this.mOldList.get(i);
        ManagedLauncherItem managedLauncherItem2 = this.mNewList.get(i2);
        if (managedLauncherItem2.getItemType() != managedLauncherItem.getItemType()) {
            return false;
        }
        int i3 = AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$apps$enterprise$models$ManagedLauncherItem$ItemType[managedLauncherItem2.getItemType().ordinal()];
        if (i3 == 1) {
            return managedLauncherItem2.getPackageName().equals(managedLauncherItem.getPackageName());
        }
        if (i3 == 2) {
            return managedLauncherItem2.getIngestedApp().packageName.equals(managedLauncherItem.getIngestedApp().packageName);
        }
        if (i3 != 3) {
            return false;
        }
        return managedLauncherItem2.getRoute().equals(managedLauncherItem.getRoute());
    }

    /* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.ManagedLauncherContentAdapterDiffUtilCallback$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$apps$enterprise$models$ManagedLauncherItem$ItemType = new int[ManagedLauncherItem.ItemType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.ManagedLauncherItem$ItemType[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.ManagedLauncherItem.ItemType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.ManagedLauncherContentAdapterDiffUtilCallback.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$apps$enterprise$models$ManagedLauncherItem$ItemType = r0
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.ManagedLauncherContentAdapterDiffUtilCallback.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$apps$enterprise$models$ManagedLauncherItem$ItemType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.ManagedLauncherItem$ItemType r1 = com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.ManagedLauncherItem.ItemType.APP     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.ManagedLauncherContentAdapterDiffUtilCallback.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$apps$enterprise$models$ManagedLauncherItem$ItemType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.ManagedLauncherItem$ItemType r1 = com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.ManagedLauncherItem.ItemType.INGESTED_APP     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.ManagedLauncherContentAdapterDiffUtilCallback.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$apps$enterprise$models$ManagedLauncherItem$ItemType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.ManagedLauncherItem$ItemType r1 = com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.models.ManagedLauncherItem.ItemType.SYSTEM_UX_ROUTE_APP     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.tablet.apps.enterprise.adapters.ManagedLauncherContentAdapterDiffUtilCallback.AnonymousClass1.<clinit>():void");
        }
    }

    @Override // androidx.recyclerview.widget.DiffUtil.Callback
    public boolean areContentsTheSame(int i, int i2) {
        ManagedLauncherItem managedLauncherItem = this.mOldList.get(i);
        ManagedLauncherItem managedLauncherItem2 = this.mNewList.get(i2);
        int i3 = AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$apps$enterprise$models$ManagedLauncherItem$ItemType[managedLauncherItem2.getItemType().ordinal()];
        if (i3 == 1) {
            return managedLauncherItem2.getDisplayName().equals(managedLauncherItem.getDisplayName());
        }
        if (i3 != 2) {
            return i3 == 3;
        }
        return managedLauncherItem2.getIngestedApp().displayName.equals(managedLauncherItem.getIngestedApp().displayName);
    }
}
