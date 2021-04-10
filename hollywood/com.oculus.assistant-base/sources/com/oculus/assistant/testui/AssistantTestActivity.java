package com.oculus.assistant.testui;

import X.BX;
import X.C0139Dd;
import X.W0;
import X.YU;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;
import android.widget.TextView;
import com.oculus.assistant.R;
import java.util.Map;

public class AssistantTestActivity extends Activity {

    public class AssistantSettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
        private void A00(EditTextPreference editTextPreference) {
            String text;
            if (!editTextPreference.getKey().equals(getString(R.string.custom_headers)) || !editTextPreference.getText().isEmpty()) {
                text = editTextPreference.getText();
            } else {
                text = getString(R.string.custom_headers_hint);
            }
            editTextPreference.setSummary(text);
        }

        public final void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            addPreferencesFromResource(R.xml.assistant_preferences);
        }

        public final void onPause() {
            super.onPause();
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
            BX.A00().stopService(YU.A00());
        }

        public final void onResume() {
            super.onResume();
            SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
            sharedPreferences.registerOnSharedPreferenceChangeListener(this);
            for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
                if (findPreference(entry.getKey()) instanceof EditTextPreference) {
                    A00((EditTextPreference) findPreference(entry.getKey()));
                }
            }
        }

        public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
            if (findPreference(str) instanceof EditTextPreference) {
                A00((EditTextPreference) findPreference(str));
            }
        }
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.assistant_test_layout);
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            StringBuilder sb = new StringBuilder();
            sb.append(packageInfo.versionName);
            sb.append(" (");
            sb.append(packageInfo.versionCode);
            sb.append(")");
            ((TextView) findViewById(R.id.version)).setText(sb.toString());
        } catch (PackageManager.NameNotFoundException e) {
            C0139Dd.A0L("Assistant::ATA", "getAppVersion: Problem getting version.", e);
        }
        if (!W0.A00().A00.getBoolean("is_allowed_usersettings", false)) {
            finish();
        }
    }
}
