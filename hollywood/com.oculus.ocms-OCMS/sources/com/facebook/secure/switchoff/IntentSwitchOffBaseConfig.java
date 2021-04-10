package com.facebook.secure.switchoff;

import android.content.Context;
import com.facebook.secure.switchoff.IntentSwitchOff;
import java.util.Map;
import javax.annotation.Nullable;

public abstract class IntentSwitchOffBaseConfig implements IntentSwitchOff.Config {
    protected final Context mContext;
    @Nullable
    private IntentMatcher[] mCustomConfig;
    @Nullable
    private Map<String, DeeplinkConfig> mDeeplinkConfig;
    @Nullable
    private IntentCriteria[] mIntentCriteria;

    /* access modifiers changed from: protected */
    public abstract String getCriteriaString();

    /* access modifiers changed from: protected */
    public abstract String getCustomConfigString();

    /* access modifiers changed from: protected */
    public abstract String getDeeplinkConfigString();

    public IntentSwitchOffBaseConfig(Context context) {
        this.mContext = context;
    }

    @Override // com.facebook.secure.switchoff.IntentSwitchOff.Config
    public synchronized IntentCriteria[] getIntentCriteria() {
        if (this.mIntentCriteria == null) {
            this.mIntentCriteria = IntentCriteria.parseIntentCriteria(getCriteriaString(), this.mContext);
        }
        return this.mIntentCriteria;
    }

    @Override // com.facebook.secure.switchoff.IntentSwitchOff.Config
    public synchronized IntentMatcher[] getCustomIntentConfig() {
        if (this.mCustomConfig == null) {
            this.mCustomConfig = IntentMatcher.parseConfig(getCustomConfigString());
        }
        return this.mCustomConfig;
    }

    @Override // com.facebook.secure.switchoff.IntentSwitchOff.Config
    public synchronized Map<String, DeeplinkConfig> getDeeplinkConfig() {
        if (this.mDeeplinkConfig == null) {
            this.mDeeplinkConfig = DeeplinkConfig.parseConfig(getDeeplinkConfigString());
        }
        return this.mDeeplinkConfig;
    }

    /* access modifiers changed from: protected */
    public synchronized void setIntentCriteria(String str) {
        this.mIntentCriteria = IntentCriteria.parseIntentCriteria(str, this.mContext);
    }

    /* access modifiers changed from: protected */
    public synchronized void setCustomConfigString(String str) {
        this.mCustomConfig = IntentMatcher.parseConfig(str);
    }

    /* access modifiers changed from: protected */
    public synchronized void setDeeplinkConfigString(String str) {
        this.mDeeplinkConfig = DeeplinkConfig.parseConfig(str);
    }
}
