package com.facebook.secure.switchoff;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import com.facebook.secure.intentparser.IntentParser;
import com.facebook.secure.switchoff.IntentSwitchOff;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

@SuppressLint({"BadMethodUse-android.util.Log.w"})
public class DefaultSwitchOffs {
    private static final IntentSwitchOff.Config DELEGATE_CONFIG = new IntentSwitchOff.Config() {
        /* class com.facebook.secure.switchoff.DefaultSwitchOffs.AnonymousClass2 */

        @Override // com.facebook.secure.switchoff.IntentSwitchOff.Config
        public boolean shouldCheckSwitch() {
            return DefaultSwitchOffs.currentConfig().shouldCheckSwitch();
        }

        @Override // com.facebook.secure.switchoff.IntentSwitchOff.Config
        public boolean shouldCheckDeeplinkConfig() {
            return DefaultSwitchOffs.currentConfig().shouldCheckDeeplinkConfig();
        }

        @Override // com.facebook.secure.switchoff.IntentSwitchOff.Config
        public IntentCriteria[] getIntentCriteria() {
            return DefaultSwitchOffs.currentConfig().getIntentCriteria();
        }

        @Override // com.facebook.secure.switchoff.IntentSwitchOff.Config
        public IntentMatcher[] getCustomIntentConfig() {
            return DefaultSwitchOffs.currentConfig().getCustomIntentConfig();
        }

        @Override // com.facebook.secure.switchoff.IntentSwitchOff.Config
        public Map<String, DeeplinkConfig> getDeeplinkConfig() {
            return DefaultSwitchOffs.currentConfig().getDeeplinkConfig();
        }
    };
    private static final IntentSwitchOff.Config NO_OP_CONFIG = new IntentSwitchOff.Config() {
        /* class com.facebook.secure.switchoff.DefaultSwitchOffs.AnonymousClass1 */

        @Override // com.facebook.secure.switchoff.IntentSwitchOff.Config
        public boolean shouldCheckDeeplinkConfig() {
            return false;
        }

        @Override // com.facebook.secure.switchoff.IntentSwitchOff.Config
        public boolean shouldCheckSwitch() {
            return false;
        }

        @Override // com.facebook.secure.switchoff.IntentSwitchOff.Config
        public IntentCriteria[] getIntentCriteria() {
            return new IntentCriteria[0];
        }

        @Override // com.facebook.secure.switchoff.IntentSwitchOff.Config
        public IntentMatcher[] getCustomIntentConfig() {
            return new IntentMatcher[0];
        }

        @Override // com.facebook.secure.switchoff.IntentSwitchOff.Config
        public Map<String, DeeplinkConfig> getDeeplinkConfig() {
            return new HashMap();
        }
    };
    private static final String PREFERENCES_CRITERIA_KEY = "last_criteria";
    private static final String PREFERENCES_CUSTOM_CONFIG_KEY = "last_custom_config";
    private static final String PREFERENCES_DEEPLINK_CONFIG_KEY = "last_deeplink_config";
    private static final String PREFERENCES_NAME = "com.facebook.secure.switchoff";
    private static final String TAG = "DefaultSwitchOffs";
    @Nullable
    private static IntentSwitchOff.Config mConfig;
    private static final IntentSwitchOff<Object> mGeneralSwitchOff = new LoadingIntentSwitchOff(DELEGATE_CONFIG);

    public static synchronized void resetConfig() {
        synchronized (DefaultSwitchOffs.class) {
            mConfig = null;
        }
    }

    /* access modifiers changed from: private */
    public static synchronized IntentSwitchOff.Config currentConfig() {
        IntentSwitchOff.Config config;
        synchronized (DefaultSwitchOffs.class) {
            if (mConfig != null) {
                config = mConfig;
            } else {
                throw new IllegalStateException();
            }
        }
        return config;
    }

    private static class LoadingIntentSwitchOff<T> extends IntentSwitchOff<T> {
        /* access modifiers changed from: protected */
        @Override // com.facebook.secure.switchoff.IntentSwitchOff
        public void kill(T t, Intent intent) {
        }

        public LoadingIntentSwitchOff(IntentSwitchOff.Config config) {
            super(config);
        }

        @Override // com.facebook.secure.switchoff.IntentSwitchOff
        public boolean check(Context context, T t, Intent intent) {
            DefaultSwitchOffs.ensureConfig(context);
            return super.check(context, t, intent);
        }

        @Override // com.facebook.secure.switchoff.IntentSwitchOff
        public boolean check(Context context, T t, Intent intent, @Nullable IntentParser.ParsedIntent parsedIntent) {
            DefaultSwitchOffs.ensureConfig(context);
            return super.check(context, t, intent, parsedIntent);
        }
    }

    private DefaultSwitchOffs() {
    }

    public static synchronized void configure(Context context, String str, IntentCriteria[] intentCriteriaArr, String str2, IntentMatcher[] intentMatcherArr, String str3, Map<String, DeeplinkConfig> map) {
        synchronized (DefaultSwitchOffs.class) {
            updateConfig(intentCriteriaArr, intentMatcherArr, map);
            writeConfigToFile(context, str, str2, str3);
        }
    }

    private static void updateConfig(final IntentCriteria[] intentCriteriaArr, final IntentMatcher[] intentMatcherArr, final Map<String, DeeplinkConfig> map) {
        mConfig = new IntentSwitchOff.Config() {
            /* class com.facebook.secure.switchoff.DefaultSwitchOffs.AnonymousClass3 */

            @Override // com.facebook.secure.switchoff.IntentSwitchOff.Config
            public boolean shouldCheckDeeplinkConfig() {
                return true;
            }

            @Override // com.facebook.secure.switchoff.IntentSwitchOff.Config
            public boolean shouldCheckSwitch() {
                return true;
            }

            @Override // com.facebook.secure.switchoff.IntentSwitchOff.Config
            public IntentCriteria[] getIntentCriteria() {
                return intentCriteriaArr;
            }

            @Override // com.facebook.secure.switchoff.IntentSwitchOff.Config
            public IntentMatcher[] getCustomIntentConfig() {
                return intentMatcherArr;
            }

            @Override // com.facebook.secure.switchoff.IntentSwitchOff.Config
            public Map<String, DeeplinkConfig> getDeeplinkConfig() {
                return map;
            }
        };
    }

    public static synchronized IntentSwitchOff<Object> general() {
        IntentSwitchOff<Object> intentSwitchOff;
        synchronized (DefaultSwitchOffs.class) {
            intentSwitchOff = mGeneralSwitchOff;
        }
        return intentSwitchOff;
    }

    /* access modifiers changed from: private */
    public static synchronized void ensureConfig(Context context) {
        synchronized (DefaultSwitchOffs.class) {
            if (mConfig == null) {
                loadConfigFromFile(context);
                if (mConfig == null) {
                    mConfig = NO_OP_CONFIG;
                }
            }
        }
    }

    private static void loadConfigFromFile(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, 0);
            updateConfig(IntentCriteria.parseIntentCriteria(sharedPreferences.getString(PREFERENCES_CRITERIA_KEY, ""), context), IntentMatcher.parseConfig(sharedPreferences.getString(PREFERENCES_CUSTOM_CONFIG_KEY, "")), DeeplinkConfig.parseConfig(sharedPreferences.getString(PREFERENCES_DEEPLINK_CONFIG_KEY, "")));
        } catch (Throwable th) {
            Log.w(TAG, "Error loading last config", th);
        }
    }

    private static void writeConfigToFile(Context context, String str, String str2, String str3) {
        context.getSharedPreferences(PREFERENCES_NAME, 0).edit().putString(PREFERENCES_CRITERIA_KEY, str).putString(PREFERENCES_CUSTOM_CONFIG_KEY, str2).putString(PREFERENCES_DEEPLINK_CONFIG_KEY, str3).apply();
    }
}
