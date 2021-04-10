package com.oculus.config.gatekeeper;

import android.content.SharedPreferences;
import com.google.common.collect.ImmutableSet;
import com.oculus.config.annotations.GatekeeperSharedPrefs;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicGatekeeperRegistry implements GatekeeperRegistry {
    public static final String TAG = "DynamicGatekeeperRegistry";
    public final HashSet<String> mGatekeeperSet = new HashSet<>();
    public final SharedPreferences mSharedPreferences;

    public boolean deregisterGateKeeper(String str) {
        return this.mGatekeeperSet.remove(str);
    }

    public void filterKeys(Set<String> set) {
        for (Map.Entry<String, ?> entry : this.mSharedPreferences.getAll().entrySet()) {
            String key = entry.getKey();
            entry.getValue();
            Matcher matcher = Pattern.compile("^/gatekeepers/(.+)$").matcher(key);
            if (!matcher.find()) {
                matcher = Pattern.compile("^/gatekeeper_overrides/(.+)$").matcher(key);
                if (!matcher.find()) {
                }
            }
            String group = matcher.group(1);
            if (group != null && !set.contains(group)) {
                this.mGatekeeperSet.add(group);
            }
        }
    }

    @Override // com.oculus.config.gatekeeper.GatekeeperRegistry
    public ImmutableSet<String> getRegisteredGatekeepers() {
        return ImmutableSet.A08(this.mGatekeeperSet);
    }

    @Override // com.oculus.config.gatekeeper.GatekeeperRegistry
    public boolean isGatekeeperRegistered(String str) {
        return this.mGatekeeperSet.contains(str);
    }

    public boolean registerGateKeeper(String str) {
        return this.mGatekeeperSet.add(str);
    }

    public DynamicGatekeeperRegistry(@GatekeeperSharedPrefs SharedPreferences sharedPreferences, Set<String> set) {
        this.mSharedPreferences = sharedPreferences;
        filterKeys(set);
    }
}
