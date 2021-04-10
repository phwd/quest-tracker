package com.oculus.panelapp.debug.tabs;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.debug.DebugTabHost;
import com.oculus.panelapp.debug.FeatureFlagsListAdapter;
import com.oculus.panelapp.debug.R;
import com.oculus.panelapp.debug.ThreadUtil;
import com.oculus.panelapp.debug.settings.Setting;
import com.oculus.vrshell.panels.KeyboardHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public abstract class FilteredSettingsTab extends LinearLayout implements DebugTabHost.DebugTab, TextWatcher, View.OnClickListener {
    private static final String TAG = LoggingUtil.tag(FilteredSettingsTab.class);
    private final FeatureFlagsListAdapter mAdapter = new FeatureFlagsListAdapter();
    protected final Context mContext;
    private EditText mFilterEditText;
    protected Map<String, List<Setting>> mSettings;
    private final Timer mTimer = new Timer();

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

    public FilteredSettingsTab(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public void initializeSettings(Map<String, List<Setting>> map) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.debug_feature_flag_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mSettings = map;
        this.mAdapter.setItems(map);
        recyclerView.setAdapter(this.mAdapter);
        this.mFilterEditText = (EditText) findViewById(R.id.feature_flag_filter_edittext);
        this.mFilterEditText.addTextChangedListener(this);
        ((Button) findViewById(R.id.feature_flag_filter_clear)).setOnClickListener(this);
    }

    private class FilterTimerTask extends TimerTask {
        private FilterTimerTask() {
        }

        public void run() {
            String upperCase = FilteredSettingsTab.this.mFilterEditText.getText().toString().toUpperCase();
            Log.d(FilteredSettingsTab.TAG, String.format("Filtering to \"%s\"", upperCase));
            HashMap hashMap = new HashMap();
            for (Map.Entry<String, List<Setting>> entry : FilteredSettingsTab.this.mSettings.entrySet()) {
                ArrayList arrayList = new ArrayList();
                for (Setting setting : entry.getValue()) {
                    if (setting.getName().toUpperCase().contains(upperCase)) {
                        arrayList.add(setting);
                    }
                }
                if (arrayList.size() > 0) {
                    hashMap.put(entry.getKey(), arrayList);
                }
            }
            FilteredSettingsTab.this.mAdapter.setItems(hashMap);
            ThreadUtil.runOnUiThread(FilteredSettingsTab.this.getContext(), new Runnable() {
                /* class com.oculus.panelapp.debug.tabs.FilteredSettingsTab.FilterTimerTask.AnonymousClass1 */

                public void run() {
                    FilteredSettingsTab.this.mAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    public void afterTextChanged(Editable editable) {
        Log.d(TAG, String.format("Filter changed to \"%s\"", editable.toString()));
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
