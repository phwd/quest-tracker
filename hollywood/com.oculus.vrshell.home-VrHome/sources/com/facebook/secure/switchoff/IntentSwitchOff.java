package com.facebook.secure.switchoff;

import android.content.Context;
import android.content.Intent;
import com.facebook.secure.intentparser.IntentParser;
import javax.annotation.Nullable;

public abstract class IntentSwitchOff<EndpointType> {
    protected final Config mConfig;

    public interface Config {
        IntentMatcher[] getCustomIntentConfig();

        IntentCriteria[] getIntentCriteria();

        boolean shouldCheckSwitch();
    }

    /* access modifiers changed from: protected */
    public abstract void kill(EndpointType endpointtype, Intent intent);

    public IntentSwitchOff(Config config) {
        this.mConfig = config;
    }

    public boolean check(Context context, EndpointType endpoint, Intent intent, @Nullable IntentParser.ParsedIntent parsedIntent) {
        if (this.mConfig.shouldCheckSwitch()) {
            if (IntentMatcher.matches(endpoint, intent, context, parsedIntent, this.mConfig.getCustomIntentConfig())) {
                kill(endpoint, intent);
                return false;
            }
            for (IntentCriteria criteria : this.mConfig.getIntentCriteria()) {
                if (criteria.matchesEndpointNameAndIntentFilter(endpoint, intent)) {
                    kill(endpoint, intent);
                    return false;
                }
            }
        }
        return true;
    }

    public boolean check(Context context, EndpointType endpoint, Intent intent) {
        return check(context, endpoint, intent, null);
    }
}
