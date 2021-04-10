package com.oculus.panelapp.anytimeui.v2.tablet.settings;

import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import com.oculus.ocui.OCDropdownVisibilityCallback;
import com.oculus.ocui.OCEventHandler;
import com.oculus.ocui.OCSelect;
import com.oculus.panelapp.anytimeui.databinding.SettingsDropdownActionViewBinding;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.SettingsLogger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class SettingsDropdownActionType<T> extends BaseSettingsActionType {
    @Nullable
    private final Map<T, Integer> mIconMap;
    @NonNull
    private final List<T> mItems;
    @NonNull
    private final OnSelectHandler<T> mOnSelectHandler;
    @NonNull
    private T mSelectedItem;
    private final SettingsSelectorVisibilityHandler mSelectorVisibilityHandler;
    @NonNull
    private final Map<T, Integer> mTitleMap;

    public interface OnSelectHandler<T> {
        boolean onSelect(T t);
    }

    private SettingsDropdownActionType(@NonNull List<T> list, @NonNull Map<T, Integer> map, @Nullable Map<T, Integer> map2, @NonNull T t, @NonNull OnSelectHandler<T> onSelectHandler, @Nullable SettingsSelectorVisibilityHandler settingsSelectorVisibilityHandler) {
        this.mItems = list;
        this.mTitleMap = map;
        this.mIconMap = map2;
        this.mSelectedItem = t;
        this.mOnSelectHandler = onSelectHandler;
        this.mSelectorVisibilityHandler = settingsSelectorVisibilityHandler;
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsActionType
    public void buildView(SettingsListItem settingsListItem, OCEventHandler oCEventHandler, SettingsLogger settingsLogger) {
        SettingsDropdownActionViewBinding inflate = SettingsDropdownActionViewBinding.inflate(LayoutInflater.from(settingsListItem.getContext()), settingsListItem.getRightViewGroup(), true);
        inflate.setDropdownAction(this);
        final OCSelect oCSelect = inflate.dropdown;
        oCSelect.setItems(getItems());
        oCSelect.setTitleMap(getTitleMap());
        Map<T, Integer> iconMap = getIconMap();
        if (iconMap != null) {
            oCSelect.setIconMap(iconMap);
        }
        oCSelect.setOnItemClick(new Function(settingsLogger, settingsListItem, inflate, oCSelect) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsDropdownActionType$5JNPx2sVpgatDmaAioF45ToDwMU */
            private final /* synthetic */ SettingsLogger f$1;
            private final /* synthetic */ SettingsListItem f$2;
            private final /* synthetic */ SettingsDropdownActionViewBinding f$3;
            private final /* synthetic */ OCSelect f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SettingsDropdownActionType.this.lambda$buildView$259$SettingsDropdownActionType(this.f$1, this.f$2, this.f$3, this.f$4, obj);
            }
        });
        oCSelect.setEventHandler(oCEventHandler);
        oCSelect.setVisibilityCallback(new OCDropdownVisibilityCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDropdownActionType.AnonymousClass1 */

            @Override // com.oculus.ocui.OCDropdownVisibilityCallback
            public void onShow() {
                if (SettingsDropdownActionType.this.mSelectorVisibilityHandler != null) {
                    SettingsDropdownActionType.this.mSelectorVisibilityHandler.setOpenSelector(oCSelect);
                }
            }

            @Override // com.oculus.ocui.OCDropdownVisibilityCallback
            public void onHide() {
                if (SettingsDropdownActionType.this.mSelectorVisibilityHandler != null) {
                    SettingsDropdownActionType.this.mSelectorVisibilityHandler.setOpenSelector(null);
                }
            }
        });
    }

    public /* synthetic */ Object lambda$buildView$259$SettingsDropdownActionType(SettingsLogger settingsLogger, SettingsListItem settingsListItem, SettingsDropdownActionViewBinding settingsDropdownActionViewBinding, OCSelect oCSelect, Object obj) {
        settingsLogger.logDropdownChange(settingsListItem.getSettingName(), settingsDropdownActionViewBinding.dropdown.getSelectedItem(), obj);
        if (!this.mOnSelectHandler.onSelect(obj)) {
            return null;
        }
        oCSelect.setSelectedItem(obj);
        return null;
    }

    @NonNull
    public List<T> getItems() {
        return this.mItems;
    }

    @NonNull
    public Map<T, Integer> getTitleMap() {
        return this.mTitleMap;
    }

    @Nullable
    public Map<T, Integer> getIconMap() {
        return this.mIconMap;
    }

    @Bindable
    public T getCurrentItem() {
        return this.mSelectedItem;
    }

    public void setCurrentItem(T t) {
        this.mSelectedItem = t;
        notifyPropertyChanged(BR.currentItem);
    }

    public static class Builder<T> extends BaseSettingsActionType.Builder {
        private Map<T, Integer> mIconMap;
        private List<T> mItems;
        private OnSelectHandler<T> mOnSelectHandler;
        private T mSelectedItem;
        private SettingsSelectorVisibilityHandler mSelectorVisibilityHandler;
        private Map<T, Integer> mTitleMap;

        public Builder<T> withItems(List<T> list) {
            this.mItems = list;
            return this;
        }

        public Builder<T> withItems(T[] tArr) {
            this.mItems = Arrays.asList(tArr);
            return this;
        }

        public Builder<T> withTitleMap(Map<T, Integer> map) {
            this.mTitleMap = map;
            return this;
        }

        public Builder<T> withIconMap(Map<T, Integer> map) {
            this.mIconMap = map;
            return this;
        }

        public Builder<T> withSelectedItem(T t) {
            this.mSelectedItem = t;
            return this;
        }

        public Builder<T> withOnSelectHandler(OnSelectHandler<T> onSelectHandler) {
            this.mOnSelectHandler = onSelectHandler;
            return this;
        }

        public Builder<T> withSelectorVisibilityHandler(SettingsSelectorVisibilityHandler settingsSelectorVisibilityHandler) {
            this.mSelectorVisibilityHandler = settingsSelectorVisibilityHandler;
            return this;
        }

        @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsActionType.Builder
        public BaseSettingsActionType build() {
            return new SettingsDropdownActionType(this.mItems, this.mTitleMap, this.mIconMap, this.mSelectedItem, this.mOnSelectHandler, this.mSelectorVisibilityHandler);
        }
    }
}
