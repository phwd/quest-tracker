package com.facebook.secure.switchoff;

import android.content.Context;
import android.content.Intent;
import com.facebook.secure.intentparser.IntentParser;
import java.util.Map;
import javax.annotation.Nullable;

public abstract class IntentSwitchOff<EndpointType> {
    protected final Config mConfig;

    public interface Config {
        IntentMatcher[] getCustomIntentConfig();

        Map<String, DeeplinkConfig> getDeeplinkConfig();

        IntentCriteria[] getIntentCriteria();

        boolean shouldCheckDeeplinkConfig();

        boolean shouldCheckSwitch();
    }

    /* access modifiers changed from: protected */
    public abstract void kill(EndpointType endpointtype, Intent intent);

    public IntentSwitchOff(Config config) {
        this.mConfig = config;
    }

    public boolean check(Context context, EndpointType endpointtype, Intent intent, @Nullable IntentParser.ParsedIntent parsedIntent) {
        if (!this.mConfig.shouldCheckSwitch()) {
            return true;
        }
        if (IntentMatcher.matches(endpointtype, intent, context, parsedIntent, this.mConfig.getCustomIntentConfig())) {
            kill(endpointtype, intent);
            return false;
        }
        for (IntentCriteria intentCriteria : this.mConfig.getIntentCriteria()) {
            if (intentCriteria.matchesEndpointNameAndIntentFilter(endpointtype, intent)) {
                kill(endpointtype, intent);
                return false;
            }
        }
        return true;
    }

    public boolean check(Context context, EndpointType endpointtype, Intent intent) {
        return check(context, endpointtype, intent, null);
    }
}
