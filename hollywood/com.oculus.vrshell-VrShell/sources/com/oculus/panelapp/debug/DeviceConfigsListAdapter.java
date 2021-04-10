package com.oculus.panelapp.debug;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.InputDeviceCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.vrshellutil.R;
import com.oculus.panelapp.debug.DeviceConfigSettings;
import com.oculus.panelapp.debug.DeviceConfigsListAdapter;
import com.oculus.panelapp.debug.DeviceConfigsTab;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DeviceConfigsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = LoggingUtil.tag(ShellDebugPanelApp.class);
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SECTION_HEADER = 1;
    private DeviceConfigSettings.Setting[] mItems;
    private DeviceConfigSettings.Setting[] mListItems;
    private int mNumOfOverrides = 0;
    private DeviceConfigsTab.OverridesListener mOverridesListener;
    private boolean mShowServiceValueColumn = false;

    public class DeviceConfigsItemViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        final Button mDeviceConfigClearOverrideBtn;
        final EditText mDeviceConfigEditText;
        final TextView mDeviceConfigLabel;
        final Button mDeviceConfigOverrideBtn;
        final Button mDeviceConfigOverrideCancelBtn;
        final Button mDeviceConfigSaveBtn;
        final TextView mDeviceConfigServiceValue;

        DeviceConfigsItemViewHolder(LinearLayout linearLayout, Context context) {
            super(linearLayout);
            this.mContext = context;
            this.mDeviceConfigLabel = (TextView) linearLayout.findViewById(R.id.device_config_label);
            this.mDeviceConfigServiceValue = (TextView) linearLayout.findViewById(R.id.device_config_service_value);
            this.mDeviceConfigEditText = (EditText) linearLayout.findViewById(R.id.device_config_edittext);
            this.mDeviceConfigSaveBtn = (Button) linearLayout.findViewById(R.id.device_config_save_override);
            this.mDeviceConfigOverrideCancelBtn = (Button) linearLayout.findViewById(R.id.device_config_cancel_override);
            this.mDeviceConfigClearOverrideBtn = (Button) linearLayout.findViewById(R.id.device_config_clear_override);
            this.mDeviceConfigOverrideBtn = (Button) linearLayout.findViewById(R.id.device_config_override);
        }

        public void bindModel(DeviceConfigSettings.Setting setting, boolean z) {
            this.mDeviceConfigLabel.setText(setting.getParamLabel());
            this.mDeviceConfigServiceValue.setText(setting.getServiceValueLabel());
            this.mDeviceConfigOverrideBtn.setOnClickListener(new View.OnClickListener(setting) {
                /* class com.oculus.panelapp.debug.$$Lambda$DeviceConfigsListAdapter$DeviceConfigsItemViewHolder$XGWzcFozuNSMIkQcllSVWr4HwM */
                private final /* synthetic */ DeviceConfigSettings.Setting f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    DeviceConfigsListAdapter.DeviceConfigsItemViewHolder.this.lambda$bindModel$0$DeviceConfigsListAdapter$DeviceConfigsItemViewHolder(this.f$1, view);
                }
            });
            this.mDeviceConfigClearOverrideBtn.setOnClickListener(new View.OnClickListener(setting) {
                /* class com.oculus.panelapp.debug.$$Lambda$DeviceConfigsListAdapter$DeviceConfigsItemViewHolder$TvACALjNiBT_zTeV2Vex5IbpQc */
                private final /* synthetic */ DeviceConfigSettings.Setting f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    DeviceConfigsListAdapter.DeviceConfigsItemViewHolder.this.lambda$bindModel$1$DeviceConfigsListAdapter$DeviceConfigsItemViewHolder(this.f$1, view);
                }
            });
            showOverrideView(setting);
            if (z) {
                showServiceValueView();
            } else {
                hideServiceValueView();
            }
            int i = AnonymousClass1.$SwitchMap$com$oculus$panelapp$debug$DeviceConfigSettings$SettingType[setting.getType().ordinal()];
            if (i == 1) {
                return;
            }
            if (i == 2 || i == 3 || i == 4) {
                bindValue(setting);
                return;
            }
            throw new IllegalArgumentException();
        }

        public /* synthetic */ void lambda$bindModel$0$DeviceConfigsListAdapter$DeviceConfigsItemViewHolder(DeviceConfigSettings.Setting setting, View view) {
            showOverrideInteractiveView(setting);
        }

        public /* synthetic */ void lambda$bindModel$1$DeviceConfigsListAdapter$DeviceConfigsItemViewHolder(DeviceConfigSettings.Setting setting, View view) {
            setting.resetAndSyncOverriddenValue();
            showOverrideView(setting);
            DeviceConfigsListAdapter deviceConfigsListAdapter = DeviceConfigsListAdapter.this;
            deviceConfigsListAdapter.setNumOfOverrides(deviceConfigsListAdapter.mNumOfOverrides - 1);
        }

        private void showOverrideView(DeviceConfigSettings.Setting setting) {
            this.mDeviceConfigOverrideBtn.setText(setting.getCurrentValueLabel());
            if (setting.isOverridden()) {
                this.mDeviceConfigOverrideBtn.setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.button_danger_backing));
                this.mDeviceConfigClearOverrideBtn.setVisibility(0);
            } else {
                this.mDeviceConfigOverrideBtn.setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.button_background_primary_default));
                this.mDeviceConfigClearOverrideBtn.setVisibility(8);
            }
            this.mDeviceConfigOverrideBtn.setVisibility(0);
            hideOverrideInteractiveView();
        }

        private void showOverrideInteractiveView(DeviceConfigSettings.Setting setting) {
            this.mDeviceConfigOverrideBtn.setVisibility(8);
            this.mDeviceConfigClearOverrideBtn.setVisibility(8);
            int i = AnonymousClass1.$SwitchMap$com$oculus$panelapp$debug$DeviceConfigSettings$SettingType[setting.getType().ordinal()];
            boolean z = false;
            if (i == 1) {
                Object currentValue = setting.getCurrentValue();
                if (currentValue instanceof Boolean) {
                    if (setting.isOverridden()) {
                        Object overriddenValue = setting.getOverriddenValue();
                        if (overriddenValue instanceof Boolean) {
                            z = !((Boolean) overriddenValue).booleanValue();
                        } else {
                            Log.e(DeviceConfigsListAdapter.TAG, "Config param '" + setting.getParamName() + "' is not a boolean");
                        }
                    } else {
                        z = !((Boolean) currentValue).booleanValue();
                        DeviceConfigsListAdapter deviceConfigsListAdapter = DeviceConfigsListAdapter.this;
                        deviceConfigsListAdapter.setNumOfOverrides(deviceConfigsListAdapter.mNumOfOverrides + 1);
                    }
                    setting.setAndSyncOverriddenValue(Boolean.valueOf(z));
                    showOverrideView(setting);
                    return;
                }
                Log.e(DeviceConfigsListAdapter.TAG, "Config param '" + setting.getParamName() + "' is not a boolean");
            } else if (i == 2 || i == 3 || i == 4) {
                this.mDeviceConfigEditText.setVisibility(0);
                this.mDeviceConfigSaveBtn.setVisibility(0);
                this.mDeviceConfigOverrideCancelBtn.setVisibility(0);
            } else {
                Log.e(DeviceConfigsListAdapter.TAG, "showOverrideInteractiveView: Invalid setting type: " + setting.getType().toString());
                throw new IllegalArgumentException();
            }
        }

        private void hideOverrideInteractiveView() {
            this.mDeviceConfigEditText.setVisibility(8);
            this.mDeviceConfigSaveBtn.setVisibility(8);
            this.mDeviceConfigOverrideCancelBtn.setVisibility(8);
        }

        private void showServiceValueView() {
            this.mDeviceConfigServiceValue.setVisibility(0);
        }

        private void hideServiceValueView() {
            this.mDeviceConfigServiceValue.setVisibility(8);
        }

        private void bindValue(DeviceConfigSettings.Setting setting) {
            int i = AnonymousClass1.$SwitchMap$com$oculus$panelapp$debug$DeviceConfigSettings$SettingType[setting.getType().ordinal()];
            if (i == 2) {
                this.mDeviceConfigEditText.setInputType(1);
            } else if (i == 3) {
                this.mDeviceConfigEditText.setInputType(InputDeviceCompat.SOURCE_TOUCHSCREEN);
            } else if (i == 4) {
                this.mDeviceConfigEditText.setInputType(12290);
            } else {
                Log.e(DeviceConfigsListAdapter.TAG, String.format("Invalid type specified for setting %s: %s", setting.getLabel(), setting.getType().toString()));
                throw new IllegalArgumentException();
            }
            this.mDeviceConfigSaveBtn.setOnClickListener(new View.OnClickListener(setting) {
                /* class com.oculus.panelapp.debug.$$Lambda$DeviceConfigsListAdapter$DeviceConfigsItemViewHolder$0_u7tPlwBWGE_Ku488EoSWR50dM */
                private final /* synthetic */ DeviceConfigSettings.Setting f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    DeviceConfigsListAdapter.DeviceConfigsItemViewHolder.this.lambda$bindValue$2$DeviceConfigsListAdapter$DeviceConfigsItemViewHolder(this.f$1, view);
                }
            });
            this.mDeviceConfigOverrideCancelBtn.setOnClickListener(new View.OnClickListener(setting) {
                /* class com.oculus.panelapp.debug.$$Lambda$DeviceConfigsListAdapter$DeviceConfigsItemViewHolder$ANmbTZxOW7qq7WjlqBE9W0wtCtE */
                private final /* synthetic */ DeviceConfigSettings.Setting f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    DeviceConfigsListAdapter.DeviceConfigsItemViewHolder.this.lambda$bindValue$3$DeviceConfigsListAdapter$DeviceConfigsItemViewHolder(this.f$1, view);
                }
            });
        }

        public /* synthetic */ void lambda$bindValue$2$DeviceConfigsListAdapter$DeviceConfigsItemViewHolder(DeviceConfigSettings.Setting setting, View view) {
            if (!setting.isOverridden()) {
                DeviceConfigsListAdapter deviceConfigsListAdapter = DeviceConfigsListAdapter.this;
                deviceConfigsListAdapter.setNumOfOverrides(deviceConfigsListAdapter.mNumOfOverrides + 1);
            }
            setting.setOverriddenValueByStringInput(this.mDeviceConfigEditText.getText().toString());
            this.mDeviceConfigEditText.clearFocus();
            showOverrideView(setting);
        }

        public /* synthetic */ void lambda$bindValue$3$DeviceConfigsListAdapter$DeviceConfigsItemViewHolder(DeviceConfigSettings.Setting setting, View view) {
            this.mDeviceConfigEditText.clearFocus();
            hideOverrideInteractiveView();
            showOverrideView(setting);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.debug.DeviceConfigsListAdapter$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$debug$DeviceConfigSettings$SettingType = new int[DeviceConfigSettings.SettingType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                com.oculus.panelapp.debug.DeviceConfigSettings$SettingType[] r0 = com.oculus.panelapp.debug.DeviceConfigSettings.SettingType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.debug.DeviceConfigsListAdapter.AnonymousClass1.$SwitchMap$com$oculus$panelapp$debug$DeviceConfigSettings$SettingType = r0
                int[] r0 = com.oculus.panelapp.debug.DeviceConfigsListAdapter.AnonymousClass1.$SwitchMap$com$oculus$panelapp$debug$DeviceConfigSettings$SettingType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.panelapp.debug.DeviceConfigSettings$SettingType r1 = com.oculus.panelapp.debug.DeviceConfigSettings.SettingType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.debug.DeviceConfigsListAdapter.AnonymousClass1.$SwitchMap$com$oculus$panelapp$debug$DeviceConfigSettings$SettingType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.panelapp.debug.DeviceConfigSettings$SettingType r1 = com.oculus.panelapp.debug.DeviceConfigSettings.SettingType.STRING     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.panelapp.debug.DeviceConfigsListAdapter.AnonymousClass1.$SwitchMap$com$oculus$panelapp$debug$DeviceConfigSettings$SettingType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.panelapp.debug.DeviceConfigSettings$SettingType r1 = com.oculus.panelapp.debug.DeviceConfigSettings.SettingType.LONG     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.panelapp.debug.DeviceConfigsListAdapter.AnonymousClass1.$SwitchMap$com$oculus$panelapp$debug$DeviceConfigSettings$SettingType     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.panelapp.debug.DeviceConfigSettings$SettingType r1 = com.oculus.panelapp.debug.DeviceConfigSettings.SettingType.DOUBLE     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.debug.DeviceConfigsListAdapter.AnonymousClass1.<clinit>():void");
        }
    }

    public class DeviceConfigSectionViewHolder extends RecyclerView.ViewHolder {
        final TextView mDeviceConfigSectionHeaderLabel;

        DeviceConfigSectionViewHolder(LinearLayout linearLayout) {
            super(linearLayout);
            this.mDeviceConfigSectionHeaderLabel = (TextView) linearLayout.findViewById(R.id.device_config_section_header);
        }

        public void bindModel(DeviceConfigSettings.Setting setting) {
            this.mDeviceConfigSectionHeaderLabel.setText(setting.getConfigName());
        }
    }

    public DeviceConfigsListAdapter(DeviceConfigsTab.OverridesListener overridesListener) {
        this.mOverridesListener = overridesListener;
    }

    public void setItems(DeviceConfigSettings.Setting[] settingArr) {
        this.mItems = settingArr;
        updateNumOfOverriddenItems();
        this.mListItems = getListItems();
    }

    public void setItems(Map<String, List<DeviceConfigSettings.Setting>> map) {
        ArrayList arrayList = new ArrayList();
        for (List<DeviceConfigSettings.Setting> list : map.values()) {
            arrayList.addAll(list);
        }
        this.mItems = (DeviceConfigSettings.Setting[]) arrayList.toArray(new DeviceConfigSettings.Setting[0]);
        updateNumOfOverriddenItems();
        this.mListItems = getListItems();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setNumOfOverrides(int i) {
        this.mNumOfOverrides = i;
        this.mOverridesListener.onChange(this.mNumOfOverrides);
    }

    private void updateNumOfOverriddenItems() {
        int i = 0;
        for (DeviceConfigSettings.Setting setting : this.mItems) {
            if (setting.isOverridden()) {
                i++;
            }
        }
        setNumOfOverrides(i);
    }

    private DeviceConfigSettings.Setting[] getListItems() {
        HashMap hashMap = new HashMap();
        DeviceConfigSettings.Setting[] settingArr = this.mItems;
        int i = 0;
        for (DeviceConfigSettings.Setting setting : settingArr) {
            String configName = setting.getConfigName();
            Set set = (Set) hashMap.get(configName);
            if (set == null) {
                set = new LinkedHashSet();
                hashMap.put(configName, set);
            }
            set.add(setting);
        }
        DeviceConfigSettings.Setting[] settingArr2 = new DeviceConfigSettings.Setting[(hashMap.size() + this.mItems.length)];
        for (Map.Entry entry : hashMap.entrySet()) {
            int i2 = i + 1;
            settingArr2[i] = new DeviceConfigSettings.Setting((String) entry.getKey(), "", DeviceConfigSettings.SettingType.SECTION_HEADER);
            Iterator it = ((Set) entry.getValue()).iterator();
            while (true) {
                i = i2;
                if (it.hasNext()) {
                    i2 = i + 1;
                    settingArr2[i] = (DeviceConfigSettings.Setting) it.next();
                }
            }
        }
        return settingArr2;
    }

    public void showServiceValueColumn() {
        this.mShowServiceValueColumn = true;
    }

    public void hideServiceValueColumn() {
        this.mShowServiceValueColumn = false;
    }

    public void clearAllOverriddenValues() {
        DeviceConfigSettings.resetAndSyncOverriddenValues(this.mItems);
        setNumOfOverrides(0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new DeviceConfigSectionViewHolder((LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.debug_device_config_section_header, viewGroup, false));
        }
        return new DeviceConfigsItemViewHolder((LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.debug_device_config_item, viewGroup, false), viewGroup.getContext());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof DeviceConfigsItemViewHolder) {
            ((DeviceConfigsItemViewHolder) viewHolder).bindModel(this.mListItems[i], this.mShowServiceValueColumn);
        }
        if (viewHolder instanceof DeviceConfigSectionViewHolder) {
            ((DeviceConfigSectionViewHolder) viewHolder).bindModel(this.mListItems[i]);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        DeviceConfigSettings.Setting[] settingArr = this.mListItems;
        if (settingArr != null) {
            return settingArr.length;
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.mListItems[i].getType() == DeviceConfigSettings.SettingType.SECTION_HEADER ? 1 : 0;
    }
}
