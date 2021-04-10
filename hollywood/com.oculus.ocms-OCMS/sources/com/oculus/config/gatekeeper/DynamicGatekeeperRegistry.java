package com.oculus.config.gatekeeper;

import android.content.SharedPreferences;
import com.facebook.debug.log.BLog;
import com.google.common.collect.ImmutableSet;
import com.oculus.config.annotations.GatekeeperSharedPrefs;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicGatekeeperRegistry implements GatekeeperRegistry {
    private static final String TAG = "DynamicGatekeeperRegistry";
    private final HashSet<String> mGatekeeperSet = new HashSet<>();
    private final SharedPreferences mSharedPreferences;

    public DynamicGatekeeperRegistry(@GatekeeperSharedPrefs SharedPreferences sharedPreferences, Set<String> set) {
        this.mSharedPreferences = sharedPreferences;
        filterKeys(set);
    }

    public void filterKeys(Set<String> set) {
        BLog.d(TAG, "In filterKeys!!");
        for (Map.Entry<String, ?> entry : this.mSharedPreferences.getAll().entrySet()) {
            String key = entry.getKey();
            String str = null;
            BLog.d(TAG, "filterKey: %s:%s", key, entry.getValue());
            Matcher matcher = Pattern.compile("^/gatekeepers/(.+)$").matcher(key);
            if (matcher.find()) {
                str = matcher.group(1);
                BLog.d(TAG, "Actual GK Key = %s", str);
            } else {
                Matcher matcher2 = Pattern.compile("^/gatekeeper_overrides/(.+)$").matcher(key);
                if (matcher2.find()) {
                    str = matcher2.group(1);
                    BLog.d(TAG, "Actual GKOverrRide Key = %s", str);
                }
            }
            if (str != null && !set.contains(str)) {
                registerGateKeeper(str);
            }
        }
    }

    @Override // com.oculus.config.gatekeeper.GatekeeperRegistry
    public ImmutableSet<String> getRegisteredGatekeepers() {
        return ImmutableSet.copyOf((Collection) this.mGatekeeperSet);
    }

    @Override // com.oculus.config.gatekeeper.GatekeeperRegistry
    public boolean isGatekeeperRegistered(String str) {
        return this.mGatekeeperSet.contains(str);
    }

    public boolean registerGateKeeper(String str) {
        BLog.i(TAG, "Adding %s to DynamicGatekeeperRegistry", str);
        return this.mGatekeeperSet.add(str);
    }

    public boolean deregisterGateKeeper(String str) {
        return this.mGatekeeperSet.remove(str);
    }
}
