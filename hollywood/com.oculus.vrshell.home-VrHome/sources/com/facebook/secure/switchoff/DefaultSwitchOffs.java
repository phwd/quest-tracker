package com.facebook.secure.switchoff;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import com.facebook.secure.intentparser.IntentParser;
import com.facebook.secure.switchoff.IntentSwitchOff;
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
        public IntentCriteria[] getIntentCriteria() {
            return DefaultSwitchOffs.currentConfig().getIntentCriteria();
        }

        @Override // com.facebook.secure.switchoff.IntentSwitchOff.Config
        public IntentMatcher[] getCustomIntentConfig() {
            return DefaultSwitchOffs.currentConfig().getCustomIntentConfig();
        }
    };
    private static final IntentSwitchOff.Config NO_OP_CONFIG = new IntentSwitchOff.Config() {
        /* class com.facebook.secure.switchoff.DefaultSwitchOffs.AnonymousClass1 */

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
    };
    @Nullable
    private static IntentSwitchOff.Config mConfig;
    private static final IntentSwitchOff<Object> mGeneralSwitchOff = new LoadingIntentSwitchOff(DELEGATE_CONFIG);

    /* access modifiers changed from: private */
    public static synchronized IntentSwitchOff.Config currentConfig() {
        IntentSwitchOff.Config config;
        synchronized (DefaultSwitchOffs.class) {
            if (mConfig == null) {
                throw new IllegalStateException();
            }
            config = mConfig;
        }
        return config;
    }

    private static class LoadingIntentSwitchOff<T> extends IntentSwitchOff<T> {
        public LoadingIntentSwitchOff(IntentSwitchOff.Config config) {
            super(config);
        }

        @Override // com.facebook.secure.switchoff.IntentSwitchOff
        public boolean check(Context context, T endpoint, Intent intent) {
            DefaultSwitchOffs.ensureConfig(context);
            return super.check(context, endpoint, intent);
        }

        @Override // com.facebook.secure.switchoff.IntentSwitchOff
        public boolean check(Context context, T endpoint, Intent intent, @Nullable IntentParser.ParsedIntent parsedIntent) {
            DefaultSwitchOffs.ensureConfig(context);
            return super.check(context, endpoint, intent, parsedIntent);
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.secure.switchoff.IntentSwitchOff
        public void kill(T t, Intent intent) {
        }
    }

    private static void updateConfig(final IntentCriteria[] intentCriteria, final IntentMatcher[] customIntentConfig, final Map<String, DeeplinkConfig> deeplinkConfig) {
        mConfig = new IntentSwitchOff.Config() {
            /* class com.facebook.secure.switchoff.DefaultSwitchOffs.AnonymousClass3 */

            @Override // com.facebook.secure.switchoff.IntentSwitchOff.Config
            public boolean shouldCheckSwitch() {
                return true;
            }

            @Override // com.facebook.secure.switchoff.IntentSwitchOff.Config
            public IntentCriteria[] getIntentCriteria() {
                return intentCriteria;
            }

            @Override // com.facebook.secure.switchoff.IntentSwitchOff.Config
            public IntentMatcher[] getCustomIntentConfig() {
                return customIntentConfig;
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
            SharedPreferences sp = context.getSharedPreferences("com.facebook.secure.switchoff", 0);
            updateConfig(IntentCriteria.parseIntentCriteria(sp.getString("last_criteria", ""), context), IntentMatcher.parseConfig(sp.getString("last_custom_config", "")), DeeplinkConfig.parseConfig(sp.getString("last_deeplink_config", "")));
        } catch (Throwable t) {
            Log.w("DefaultSwitchOffs", "Error loading last config", t);
        }
    }
}
