package com.oculus.panelapp.debug;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.deviceconfigclient.DebugOnlyValueInfo;
import com.oculus.deviceconfigclient.DeviceConfigCallback;
import com.oculus.deviceconfigclient.DeviceConfigClient;
import com.oculus.panelapp.debug.DebugTabHost;
import com.oculus.panelapp.debug.DeviceConfigSettings;
import com.oculus.panelapp.debug.DeviceConfigsTab;
import com.oculus.vrshell.panels.KeyboardHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class DeviceConfigsTab extends LinearLayout implements DebugTabHost.DebugTab, TextWatcher, View.OnClickListener {
    private static final String ALL_CONFIG_PARAMS = "all_config_params";
    private static final String DEVICE_CONFIG_TAB_ID = "device_config";
    private static final String TAG = LoggingUtil.tag(DeviceConfigsTab.class);
    private final DeviceConfigsListAdapter mAdapter = new DeviceConfigsListAdapter(new OverridesListener() {
        /* class com.oculus.panelapp.debug.$$Lambda$DeviceConfigsTab$iDUhQ5KNicMIBrfvv0CVCvyKE */

        @Override // com.oculus.panelapp.debug.DeviceConfigsTab.OverridesListener
        public final void onChange(int i) {
            DeviceConfigsTab.this.lambda$new$0$DeviceConfigsTab(i);
        }
    });
    private RecyclerView mAllConfigParamsView;
    private Button mClearAllOverridesBtn;
    private DeviceConfigClient mDeviceConfigClient;
    private EditText mFilterEditText;
    private boolean mIsDeviceConfigClientInitialized = false;
    private TextView mListInfoView;
    private TextView mPendingAllConfigParamsView;
    private TextView mServiceValueColumnHeaderView;
    private final Map<String, List<DeviceConfigSettings.Setting>> mSettings = new HashMap();
    private final Timer mTimer = new Timer();

    @FunctionalInterface
    public interface OverridesListener {
        void onChange(int i);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public static class FilterEditText extends EditText implements KeyboardHandler.KeyboardListener {
        public FilterEditText(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        @Override // com.oculus.vrshell.panels.KeyboardHandler.KeyboardListener
        public void onKeyboardActionKey() {
            clearFocus();
        }
    }

    public DeviceConfigsTab(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public /* synthetic */ void lambda$new$0$DeviceConfigsTab(int i) {
        if (i > 0) {
            this.mClearAllOverridesBtn.setText(getContext().getString(R.string.debug_device_config_clear_all_overrides, Integer.valueOf(i)));
            this.mClearAllOverridesBtn.setVisibility(0);
            return;
        }
        this.mClearAllOverridesBtn.setVisibility(8);
    }

    @Override // com.oculus.panelapp.debug.DebugTabHost.DebugTab
    public void initialize(ShellDebugPanelApp shellDebugPanelApp, DebugTabHost debugTabHost) {
        debugTabHost.addTab(DEVICE_CONFIG_TAB_ID, "DeviceConfig", R.id.debug_tab_device_configs);
        debugTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            /* class com.oculus.panelapp.debug.$$Lambda$DeviceConfigsTab$NlZVLl3eb8JQu9v4gn8tIXd8kw */

            public final void onTabChanged(String str) {
                DeviceConfigsTab.this.lambda$initialize$1$DeviceConfigsTab(str);
            }
        });
        this.mFilterEditText = (EditText) findViewById(R.id.device_config_filter_edittext);
        this.mFilterEditText.addTextChangedListener(this);
        ((Button) findViewById(R.id.device_config_refresh_list_btn)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.$$Lambda$DeviceConfigsTab$aW9Qf2RbrUeSEpMuX3AqFIPoXRE */

            public final void onClick(View view) {
                DeviceConfigsTab.this.lambda$initialize$2$DeviceConfigsTab(view);
            }
        });
        ((ToggleButton) findViewById(R.id.device_config_toggle_service_value_column)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /* class com.oculus.panelapp.debug.$$Lambda$DeviceConfigsTab$uyWeKGP5JN2ltQDjfbRhbBQ1lbY */

            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                DeviceConfigsTab.this.lambda$initialize$3$DeviceConfigsTab(compoundButton, z);
            }
        });
        this.mClearAllOverridesBtn = (Button) findViewById(R.id.device_config_clear_all_overrides_btn);
        this.mClearAllOverridesBtn.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.$$Lambda$DeviceConfigsTab$e8JLTaD3ioyOgS0MGK4_FFCmfMQ */

            public final void onClick(View view) {
                DeviceConfigsTab.this.lambda$initialize$4$DeviceConfigsTab(view);
            }
        });
        this.mServiceValueColumnHeaderView = (TextView) findViewById(R.id.device_config_header_service_value);
        this.mPendingAllConfigParamsView = (TextView) findViewById(R.id.device_config_pending_config_params);
        this.mAllConfigParamsView = (RecyclerView) findViewById(R.id.debug_device_config_list);
        this.mListInfoView = (TextView) findViewById(R.id.device_config_list_info);
        ((Button) findViewById(R.id.device_config_filter_clear)).setOnClickListener(this);
    }

    public /* synthetic */ void lambda$initialize$1$DeviceConfigsTab(String str) {
        if (DEVICE_CONFIG_TAB_ID.equals(str) && !this.mIsDeviceConfigClientInitialized) {
            initDeviceConfigClient();
        }
    }

    public /* synthetic */ void lambda$initialize$2$DeviceConfigsTab(View view) {
        refreshDeviceConfigList();
    }

    public /* synthetic */ void lambda$initialize$3$DeviceConfigsTab(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.mAdapter.showServiceValueColumn();
            this.mServiceValueColumnHeaderView.setVisibility(0);
        } else {
            this.mAdapter.hideServiceValueColumn();
            this.mServiceValueColumnHeaderView.setVisibility(8);
        }
        this.mAdapter.notifyDataSetChanged();
    }

    public /* synthetic */ void lambda$initialize$4$DeviceConfigsTab(View view) {
        this.mAdapter.clearAllOverriddenValues();
        this.mAdapter.notifyDataSetChanged();
        this.mClearAllOverridesBtn.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public class FilterTimerTask extends TimerTask {
        private FilterTimerTask() {
        }

        public void run() {
            ThreadUtil.runOnUiThread(DeviceConfigsTab.this.getContext(), new Runnable(DeviceConfigsTab.this.getFilteredSettingList()) {
                /* class com.oculus.panelapp.debug.$$Lambda$DeviceConfigsTab$FilterTimerTask$xs7YEPgqtWn8J7BPn4SVIinQcvs */
                private final /* synthetic */ HashMap f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    DeviceConfigsTab.FilterTimerTask.this.lambda$run$0$DeviceConfigsTab$FilterTimerTask(this.f$1);
                }
            });
        }

        public /* synthetic */ void lambda$run$0$DeviceConfigsTab$FilterTimerTask(HashMap hashMap) {
            DeviceConfigsTab.this.mAdapter.setItems(hashMap);
            DeviceConfigsTab.this.mAdapter.notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private HashMap<String, List<DeviceConfigSettings.Setting>> getFilteredSettingList() {
        String upperCase = this.mFilterEditText.getText().toString().toUpperCase();
        HashMap<String, List<DeviceConfigSettings.Setting>> hashMap = new HashMap<>();
        for (Map.Entry<String, List<DeviceConfigSettings.Setting>> entry : this.mSettings.entrySet()) {
            ArrayList arrayList = new ArrayList();
            for (DeviceConfigSettings.Setting setting : entry.getValue()) {
                if (setting.getLabel().toUpperCase().contains(upperCase)) {
                    arrayList.add(setting);
                }
            }
            if (arrayList.size() > 0) {
                hashMap.put(entry.getKey(), arrayList);
            }
        }
        return hashMap;
    }

    private void initDeviceConfigClient() {
        Log.d(TAG, "in initDeviceConfigClient");
        if (this.mDeviceConfigClient != null) {
            Log.e(TAG, "DeviceConfigClient already created");
            return;
        }
        this.mDeviceConfigClient = new DeviceConfigClient(getContext());
        DeviceConfigSettings.setDeviceConfigClient(this.mDeviceConfigClient);
        this.mDeviceConfigClient.subscribe(new DeviceConfigCallback() {
            /* class com.oculus.panelapp.debug.DeviceConfigsTab.AnonymousClass1 */

            @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
            public boolean enableAutoPrefetch() {
                return true;
            }

            @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
            public void onPrefetched(String[] strArr) {
            }

            @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
            public void onUpdate(String str) {
            }

            @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
            public boolean supportUpdates() {
                return false;
            }

            @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
            public void onSuccess() {
                Log.d(DeviceConfigsTab.TAG, "in subscribe success callback");
                if (!DeviceConfigsTab.this.mIsDeviceConfigClientInitialized) {
                    DeviceConfigsTab.this.mIsDeviceConfigClientInitialized = true;
                    DeviceConfigsTab.this.deviceConfigCallbackSuccess();
                }
            }

            @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
            public void onFailure(String str) {
                String str2 = DeviceConfigsTab.TAG;
                Log.d(str2, "in subscribe failure callback " + str);
            }
        });
    }

    private void refreshDeviceConfigList() {
        new Thread(new Runnable() {
            /* class com.oculus.panelapp.debug.$$Lambda$DeviceConfigsTab$lliA03vuhXfIiL1uT3KxZUB04 */

            public final void run() {
                DeviceConfigsTab.this.lambda$refreshDeviceConfigList$6$DeviceConfigsTab();
            }
        }).start();
        showLoadingConfigParamsView();
    }

    public /* synthetic */ void lambda$refreshDeviceConfigList$6$DeviceConfigsTab() {
        DeviceConfigSettings.SettingType settingType;
        List<DebugOnlyValueInfo> debugOnlyFetchAllParams = this.mDeviceConfigClient.debugOnlyFetchAllParams();
        ArrayList arrayList = new ArrayList();
        for (DebugOnlyValueInfo debugOnlyValueInfo : debugOnlyFetchAllParams) {
            String configParamName = debugOnlyValueInfo.getConfigParamName();
            Object deviceWideCachedValue = debugOnlyValueInfo.getDeviceWideCachedValue();
            Object mobileConfigServiceValue = debugOnlyValueInfo.getMobileConfigServiceValue();
            Object overriddenValue = debugOnlyValueInfo.getOverriddenValue();
            int valueType = debugOnlyValueInfo.getValueType();
            if (valueType == 1) {
                settingType = DeviceConfigSettings.SettingType.BOOLEAN;
            } else if (valueType == 2) {
                settingType = DeviceConfigSettings.SettingType.LONG;
            } else if (valueType == 3) {
                settingType = DeviceConfigSettings.SettingType.STRING;
            } else if (valueType != 4) {
                settingType = DeviceConfigSettings.SettingType.INVALID;
            } else {
                settingType = DeviceConfigSettings.SettingType.DOUBLE;
            }
            DeviceConfigSettings.Setting setting = new DeviceConfigSettings.Setting(configParamName, settingType);
            setting.setCachedValue(deviceWideCachedValue);
            setting.setServiceValue(mobileConfigServiceValue);
            setting.setOverriddenValue(overriddenValue);
            arrayList.add(setting);
        }
        this.mSettings.put(ALL_CONFIG_PARAMS, arrayList);
        ThreadUtil.runOnUiThread(getContext(), new Runnable(getFilteredSettingList()) {
            /* class com.oculus.panelapp.debug.$$Lambda$DeviceConfigsTab$M8m9tlMzxLZc158v_MGnBDuUkA */
            private final /* synthetic */ HashMap f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                DeviceConfigsTab.this.lambda$refreshDeviceConfigList$5$DeviceConfigsTab(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$refreshDeviceConfigList$5$DeviceConfigsTab(HashMap hashMap) {
        this.mAllConfigParamsView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mAdapter.setItems(hashMap);
        this.mAllConfigParamsView.setAdapter(this.mAdapter);
        showAllParamsListView();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void deviceConfigCallbackSuccess() {
        Log.d(TAG, "deviceConfigCallbackSuccess called");
        refreshDeviceConfigList();
    }

    private void showLoadingConfigParamsView() {
        this.mAllConfigParamsView.setVisibility(8);
        this.mPendingAllConfigParamsView.setVisibility(0);
        this.mListInfoView.setText(getContext().getString(R.string.debug_device_config_list_info_loading));
    }

    private void showAllParamsListView() {
        this.mPendingAllConfigParamsView.setVisibility(8);
        this.mAllConfigParamsView.setVisibility(0);
        List<DeviceConfigSettings.Setting> list = this.mSettings.get(ALL_CONFIG_PARAMS);
        TextView textView = this.mListInfoView;
        Context context = getContext();
        int i = R.string.debug_device_config_list_info_loaded;
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(list != null ? list.size() : 0);
        textView.setText(context.getString(i, objArr));
    }

    public void afterTextChanged(Editable editable) {
        this.mTimer.purge();
        if (editable.toString().length() == 0) {
            new FilterTimerTask().run();
        }
        if (editable.toString().length() > 2) {
            this.mTimer.schedule(new FilterTimerTask(), AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        }
    }

    public void onClick(View view) {
        this.mFilterEditText.setText("");
    }
}
