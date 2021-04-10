package com.oculus.panelapp.debug;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TextView;
import androidx.core.view.InputDeviceCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.debug.settings.CategoryHeader;
import com.oculus.panelapp.debug.settings.Setting;
import com.oculus.panelapp.debug.settings.SettingType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

public class FeatureFlagsListAdapter extends RecyclerView.Adapter<FeatureFlagsViewHolder> {
    private static final String TAG = LoggingUtil.tag(ShellDebugPanelApp.class);
    private Context mContext = null;
    private Setting[] mItems = new Setting[0];
    private boolean mShouldRenderHeaders = true;

    private static boolean isNumeric(Setting setting) {
        return setting.getType() == SettingType.INTEGER || setting.getType() == SettingType.FLOAT;
    }

    public class FeatureFlagsViewHolder extends RecyclerView.ViewHolder {
        private final Button mActionButton;
        private final TextView mCategoryHeader;
        private final EditText mEditText;
        private final TextView mLabel;
        private final Button mSaveText;
        private Setting mSetting;
        private final Spinner mSpinner;
        private final SpinnerListener mSpinnerListener;
        private final Switch mSwitch;
        private final Timer mTimer = new Timer();

        public FeatureFlagsViewHolder(LinearLayout linearLayout, Context context) {
            super(linearLayout);
            FeatureFlagsListAdapter.this.mContext = context;
            this.mCategoryHeader = (TextView) linearLayout.findViewById(R.id.category_label);
            this.mLabel = (TextView) linearLayout.findViewById(R.id.feature_flag_label);
            this.mSwitch = (Switch) linearLayout.findViewById(R.id.feature_flag_switch);
            this.mEditText = (EditText) linearLayout.findViewById(R.id.feature_flag_edittext);
            this.mSaveText = (Button) linearLayout.findViewById(R.id.feature_flag_savetext);
            this.mSpinner = (Spinner) linearLayout.findViewById(R.id.feature_flag_spinner);
            this.mActionButton = (Button) linearLayout.findViewById(R.id.feature_flag_action_button);
            this.mSpinnerListener = new SpinnerListener();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void bindModel(Setting setting) {
            if (this.mSetting != null) {
                Log.w(FeatureFlagsListAdapter.TAG, String.format("Setting %s not unbound before binding new setting", setting.getName()));
            }
            hideAllWidgets();
            if (setting.getType() == SettingType.CATEGORY_HEADER) {
                bindCategoryHeader(setting.getName());
                return;
            }
            this.mLabel.setText(setting.getName());
            this.mLabel.setVisibility(0);
            if (setting.getType() == SettingType.BOOLEAN) {
                bindBoolean(setting);
            } else if (setting.getType() == SettingType.ACTION) {
                bindAction(setting);
            } else {
                bindValue(setting);
            }
        }

        private void bindCategoryHeader(String str) {
            this.mCategoryHeader.setText(str);
            this.mCategoryHeader.setVisibility(0);
        }

        private void bindAction(final Setting setting) {
            this.mActionButton.setOnTouchListener(new View.OnTouchListener() {
                /* class com.oculus.panelapp.debug.FeatureFlagsListAdapter.FeatureFlagsViewHolder.AnonymousClass1 */

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (FeatureFlagsViewHolder.this.getAdapterPosition() == -1) {
                        return false;
                    }
                    setting.setSetting("0");
                    FeatureFlagsViewHolder.this.mActionButton.setEnabled(false);
                    FeatureFlagsViewHolder.this.mTimer.schedule(new TimerTask() {
                        /* class com.oculus.panelapp.debug.FeatureFlagsListAdapter.FeatureFlagsViewHolder.AnonymousClass1.AnonymousClass1 */

                        public void run() {
                            ThreadUtil.runOnUiThread(FeatureFlagsListAdapter.this.mContext, new Runnable() {
                                /* class com.oculus.panelapp.debug.FeatureFlagsListAdapter.FeatureFlagsViewHolder.AnonymousClass1.AnonymousClass1.AnonymousClass1 */

                                public void run() {
                                    FeatureFlagsViewHolder.this.mActionButton.setEnabled(true);
                                }
                            });
                        }
                    }, 50);
                    return true;
                }
            });
            this.mActionButton.setText(setting.getName());
            this.mLabel.setText("(Action)");
            this.mActionButton.setVisibility(0);
        }

        private void bindBoolean(final Setting setting) {
            setting.bind(new Consumer<String>() {
                /* class com.oculus.panelapp.debug.FeatureFlagsListAdapter.FeatureFlagsViewHolder.AnonymousClass2 */

                public void accept(String str) {
                    FeatureFlagsViewHolder.this.mSwitch.setChecked(str.equals("true"));
                }
            });
            setting.getSetting();
            this.mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                /* class com.oculus.panelapp.debug.FeatureFlagsListAdapter.FeatureFlagsViewHolder.AnonymousClass3 */

                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (FeatureFlagsViewHolder.this.getAdapterPosition() != -1) {
                        setting.setSetting(z ? "true" : "false");
                        FeatureFlagsViewHolder.this.mLabel.setText(setting.getName());
                    }
                }
            });
            this.mSwitch.setVisibility(0);
        }

        private void bindValue(Setting setting) {
            List<String> presets = setting.getPresets();
            if (presets == null || presets.isEmpty()) {
                bindEditText(setting);
            } else {
                bindSpinner(setting);
            }
        }

        private void bindEditText(final Setting setting) {
            AnonymousClass4 r0 = new Consumer<String>() {
                /* class com.oculus.panelapp.debug.FeatureFlagsListAdapter.FeatureFlagsViewHolder.AnonymousClass4 */

                public void accept(String str) {
                    FeatureFlagsViewHolder.this.mEditText.setText(str);
                    FeatureFlagsViewHolder.this.mSaveText.setEnabled(false);
                }
            };
            if (setting.getType() == SettingType.INTEGER) {
                this.mEditText.setInputType(InputDeviceCompat.SOURCE_TOUCHSCREEN);
            } else if (setting.getType() == SettingType.FLOAT) {
                this.mEditText.setInputType(12290);
            } else if (setting.getType() == SettingType.STRING) {
                this.mEditText.setInputType(1);
            } else {
                Log.e(FeatureFlagsListAdapter.TAG, String.format("Invalid type specified for setting %s: %s", setting.getName(), setting.getType()));
                throw new IllegalArgumentException();
            }
            setting.bind(r0);
            setting.getSetting();
            this.mSaveText.setEnabled(false);
            this.mEditText.addTextChangedListener(new TextWatcher() {
                /* class com.oculus.panelapp.debug.FeatureFlagsListAdapter.FeatureFlagsViewHolder.AnonymousClass5 */

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void afterTextChanged(Editable editable) {
                    FeatureFlagsViewHolder.this.mSaveText.setEnabled(true);
                }
            });
            this.mSaveText.setOnTouchListener(new View.OnTouchListener() {
                /* class com.oculus.panelapp.debug.FeatureFlagsListAdapter.FeatureFlagsViewHolder.AnonymousClass6 */

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() != 1) {
                        return false;
                    }
                    setting.setSetting(FeatureFlagsViewHolder.this.mEditText.getText().toString());
                    FeatureFlagsViewHolder.this.mSaveText.setEnabled(false);
                    FeatureFlagsViewHolder.this.mEditText.clearFocus();
                    return true;
                }
            });
            this.mEditText.setVisibility(0);
            this.mSaveText.setVisibility(0);
        }

        private void bindSpinner(Setting setting) {
            ArrayAdapter arrayAdapter = new ArrayAdapter(FeatureFlagsListAdapter.this.mContext, 17367048, setting.getPresets());
            arrayAdapter.setDropDownViewResource(17367049);
            this.mSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
            this.mSpinnerListener.reset();
            this.mSpinnerListener.setAdapter(arrayAdapter);
            this.mSpinnerListener.setSetting(setting);
            setting.bind(this.mSpinnerListener);
            setting.getSetting();
            this.mSpinner.setOnItemSelectedListener(this.mSpinnerListener);
            this.mSpinner.setVisibility(0);
        }

        private void hideAllWidgets() {
            this.mCategoryHeader.setVisibility(8);
            this.mLabel.setVisibility(8);
            this.mSwitch.setVisibility(8);
            this.mSwitch.setOnCheckedChangeListener(null);
            this.mEditText.setVisibility(8);
            this.mSaveText.setVisibility(8);
            this.mSpinner.setVisibility(8);
            this.mActionButton.setVisibility(8);
        }

        /* access modifiers changed from: private */
        public class SpinnerListener implements AdapterView.OnItemSelectedListener, Consumer<String> {
            private SpinnerAdapter mAdapter;
            private Setting mSetting;
            private String mSpinnerValue;

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            private SpinnerListener() {
            }

            public synchronized void reset() {
                this.mSpinnerValue = null;
            }

            public void setAdapter(SpinnerAdapter spinnerAdapter) {
                this.mAdapter = spinnerAdapter;
            }

            public void setSetting(Setting setting) {
                this.mSetting = setting;
            }

            public synchronized void accept(String str) {
                this.mSpinnerValue = str;
                int i = 0;
                while (true) {
                    if (i >= this.mAdapter.getCount()) {
                        break;
                    } else if (str.equals(this.mAdapter.getItem(i))) {
                        FeatureFlagsViewHolder.this.mSpinner.setSelection(i);
                        break;
                    } else {
                        i++;
                    }
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public synchronized void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                String obj = adapterView.getItemAtPosition(i).toString();
                if (this.mSpinnerValue != null && !this.mSpinnerValue.equals(obj)) {
                    this.mSetting.setSetting(obj);
                }
                this.mSpinnerValue = obj;
            }
        }
    }

    public void shouldRenderHeaders(boolean z) {
        this.mShouldRenderHeaders = z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public FeatureFlagsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new FeatureFlagsViewHolder((LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.debug_feature_flag_item, viewGroup, false), viewGroup.getContext());
    }

    public void onBindViewHolder(FeatureFlagsViewHolder featureFlagsViewHolder, int i) {
        featureFlagsViewHolder.bindModel(this.mItems[i]);
    }

    public void onViewRecycled(FeatureFlagsViewHolder featureFlagsViewHolder) {
        int adapterPosition = featureFlagsViewHolder.getAdapterPosition();
        if (adapterPosition != -1) {
            this.mItems[adapterPosition].unbind();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        Setting[] settingArr = this.mItems;
        if (settingArr == null) {
            return 0;
        }
        return settingArr.length;
    }

    public void setItems(Setting[] settingArr) {
        this.mItems = settingArr;
    }

    public void setItems(Map<String, List<Setting>> map) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, List<Setting>> entry : map.entrySet()) {
            if (this.mShouldRenderHeaders) {
                arrayList.add(new CategoryHeader(entry.getKey()));
            }
            arrayList.addAll(entry.getValue());
        }
        this.mItems = (Setting[]) arrayList.toArray(new Setting[0]);
    }
}
