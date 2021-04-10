package com.oculus.panelapp.anytimeui.v2.tablet.settings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.common.quickpromotion.R;
import com.oculus.ocui.OCLink;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsDescriptiveTextBinding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsEnvironmentTileBinding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsInfoBoxBinding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsListItemBinding;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsNullStateBinding;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIPanelAppBase;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.SettingsLogger;
import java.util.ArrayList;
import java.util.List;

public class SettingsContentAdapter extends RecyclerView.Adapter {
    public static final int SETTINGS_DESCRIPTIVE_TEXT_TYPE = 1;
    private static final int SETTINGS_ENVIRONMENT_TILE_TYPE = 4;
    private static final int SETTINGS_INFO_BOX_TYPE = 2;
    public static final int SETTINGS_LIST_ITEM_TYPE = 0;
    private static final int SETTINGS_NULL_STATE_TYPE = 3;
    private final Context mContext;
    private List<BaseSettingsItem> mItems;
    private final OCLink.OCLinkHandler mLinkHandler;
    private final AnytimeUIPanelAppBase mPanelApp;
    private final SettingsLogger mSettingsLogger;
    private int selectedPosition = -1;

    public SettingsContentAdapter(@NonNull Context context, @NonNull AnytimeUIPanelAppBase anytimeUIPanelAppBase, OCLink.OCLinkHandler oCLinkHandler, SettingsLogger settingsLogger) {
        this.mContext = context;
        this.mItems = new ArrayList();
        this.mPanelApp = anytimeUIPanelAppBase;
        this.mLinkHandler = oCLinkHandler;
        this.mSettingsLogger = settingsLogger;
    }

    public void setSettingsItems(@NonNull List<BaseSettingsItem> list) {
        this.mItems = list;
        notifyDataSetChanged();
    }

    public void setSelectedPosition(int i) {
        this.selectedPosition = i;
        notifyItemChanged(i);
    }

    public void resetSelectedPosition(int i) {
        this.selectedPosition = -1;
        notifyItemChanged(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(this.mContext);
        if (i == 1) {
            return new SettingsDescriptiveTextViewHolder(AnytimeTabletAndroidSettingsDescriptiveTextBinding.inflate(from, viewGroup, false));
        }
        if (i == 2) {
            return new SettingsInfoBoxViewHolder(AnytimeTabletAndroidSettingsInfoBoxBinding.inflate(from, viewGroup, false));
        }
        if (i == 3) {
            return new SettingsNullStateViewHolder(AnytimeTabletAndroidSettingsNullStateBinding.inflate(from, viewGroup, false));
        }
        if (i != 4) {
            return new SettingsItemViewHolder(AnytimeTabletAndroidSettingsListItemBinding.inflate(from, viewGroup, false));
        }
        return new SettingsEnvironmentTileViewHolder(AnytimeTabletAndroidSettingsEnvironmentTileBinding.inflate(from, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        BaseSettingsItem baseSettingsItem = this.mItems.get(i);
        if (baseSettingsItem instanceof SettingsDescriptiveText) {
            return 1;
        }
        if (baseSettingsItem instanceof SettingsEnvironment) {
            return 4;
        }
        if (baseSettingsItem instanceof SettingsInfoBox) {
            return 2;
        }
        return baseSettingsItem instanceof SettingsNullState ? 3 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);
        int color = this.mContext.getResources().getColor(R.color.oc_gray_70);
        int color2 = this.mContext.getResources().getColor(R.color.oc_gray_80);
        if (this.selectedPosition == i) {
            viewHolder.itemView.setBackgroundColor(color);
        } else {
            viewHolder.itemView.setBackgroundColor(color2);
        }
        if (itemViewType == 0) {
            AnytimeTabletAndroidSettingsListItemBinding anytimeTabletAndroidSettingsListItemBinding = ((SettingsItemViewHolder) viewHolder).mBinding;
            ((SettingsListItem) anytimeTabletAndroidSettingsListItemBinding.getRoot()).initialize((SettingsItem) this.mItems.get(i), anytimeTabletAndroidSettingsListItemBinding, this.mPanelApp, this.mLinkHandler, this.mSettingsLogger);
        } else if (itemViewType == 1) {
            AnytimeTabletAndroidSettingsDescriptiveTextBinding anytimeTabletAndroidSettingsDescriptiveTextBinding = ((SettingsDescriptiveTextViewHolder) viewHolder).mBinding;
            ((SettingsDescriptiveTextItem) anytimeTabletAndroidSettingsDescriptiveTextBinding.getRoot()).initialize((SettingsDescriptiveText) this.mItems.get(i), anytimeTabletAndroidSettingsDescriptiveTextBinding, this.mPanelApp, this.mLinkHandler, this.mSettingsLogger);
        } else if (itemViewType == 2) {
            ((SettingsInfoBoxViewHolder) viewHolder).mBinding.setInfoBox((SettingsInfoBox) this.mItems.get(i));
        } else if (itemViewType == 3) {
            ((SettingsNullStateViewHolder) viewHolder).mBinding.setNullState((SettingsNullState) this.mItems.get(i));
        } else if (itemViewType == 4) {
            AnytimeTabletAndroidSettingsEnvironmentTileBinding anytimeTabletAndroidSettingsEnvironmentTileBinding = ((SettingsEnvironmentTileViewHolder) viewHolder).mBinding;
            ((SettingsEnvironmentTile) anytimeTabletAndroidSettingsEnvironmentTileBinding.getRoot()).initialize((SettingsEnvironment) this.mItems.get(i), anytimeTabletAndroidSettingsEnvironmentTileBinding, this.mPanelApp, this.mSettingsLogger);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mItems.size();
    }

    public static class SettingsItemViewHolder extends RecyclerView.ViewHolder {
        private final AnytimeTabletAndroidSettingsListItemBinding mBinding;

        public SettingsItemViewHolder(AnytimeTabletAndroidSettingsListItemBinding anytimeTabletAndroidSettingsListItemBinding) {
            super(anytimeTabletAndroidSettingsListItemBinding.getRoot());
            this.mBinding = anytimeTabletAndroidSettingsListItemBinding;
        }
    }

    public static class SettingsDescriptiveTextViewHolder extends RecyclerView.ViewHolder {
        private final AnytimeTabletAndroidSettingsDescriptiveTextBinding mBinding;

        public SettingsDescriptiveTextViewHolder(AnytimeTabletAndroidSettingsDescriptiveTextBinding anytimeTabletAndroidSettingsDescriptiveTextBinding) {
            super(anytimeTabletAndroidSettingsDescriptiveTextBinding.getRoot());
            this.mBinding = anytimeTabletAndroidSettingsDescriptiveTextBinding;
        }
    }

    public static class SettingsEnvironmentTileViewHolder extends RecyclerView.ViewHolder {
        private final AnytimeTabletAndroidSettingsEnvironmentTileBinding mBinding;

        public SettingsEnvironmentTileViewHolder(AnytimeTabletAndroidSettingsEnvironmentTileBinding anytimeTabletAndroidSettingsEnvironmentTileBinding) {
            super(anytimeTabletAndroidSettingsEnvironmentTileBinding.getRoot());
            this.mBinding = anytimeTabletAndroidSettingsEnvironmentTileBinding;
        }
    }

    public static class SettingsInfoBoxViewHolder extends RecyclerView.ViewHolder {
        private final AnytimeTabletAndroidSettingsInfoBoxBinding mBinding;

        public SettingsInfoBoxViewHolder(AnytimeTabletAndroidSettingsInfoBoxBinding anytimeTabletAndroidSettingsInfoBoxBinding) {
            super(anytimeTabletAndroidSettingsInfoBoxBinding.getRoot());
            this.mBinding = anytimeTabletAndroidSettingsInfoBoxBinding;
        }
    }

    private static class SettingsNullStateViewHolder extends RecyclerView.ViewHolder {
        private final AnytimeTabletAndroidSettingsNullStateBinding mBinding;

        public SettingsNullStateViewHolder(AnytimeTabletAndroidSettingsNullStateBinding anytimeTabletAndroidSettingsNullStateBinding) {
            super(anytimeTabletAndroidSettingsNullStateBinding.getRoot());
            this.mBinding = anytimeTabletAndroidSettingsNullStateBinding;
        }
    }
}
