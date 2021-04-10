package java.time.zone;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class ZoneRulesProvider {
    private static final CopyOnWriteArrayList PROVIDERS = new CopyOnWriteArrayList();
    private static final ConcurrentMap ZONES = new ConcurrentHashMap(512, 0.75f, 2);

    /* access modifiers changed from: protected */
    public abstract ZoneRules provideRules(String str, boolean z);

    /* access modifiers changed from: protected */
    public abstract Set provideZoneIds();

    static {
        registerProvider(new IcuZoneRulesProvider());
    }

    public static ZoneRules getRules(String str, boolean z) {
        Objects.requireNonNull(str, "zoneId");
        return getProvider(str).provideRules(str, z);
    }

    private static ZoneRulesProvider getProvider(String str) {
        ZoneRulesProvider zoneRulesProvider = (ZoneRulesProvider) ZONES.get(str);
        if (zoneRulesProvider != null) {
            return zoneRulesProvider;
        }
        if (ZONES.isEmpty()) {
            throw new ZoneRulesException("No time-zone data files registered");
        }
        throw new ZoneRulesException("Unknown time-zone ID: " + str);
    }

    public static void registerProvider(ZoneRulesProvider zoneRulesProvider) {
        Objects.requireNonNull(zoneRulesProvider, "provider");
        registerProvider0(zoneRulesProvider);
        PROVIDERS.add(zoneRulesProvider);
    }

    private static void registerProvider0(ZoneRulesProvider zoneRulesProvider) {
        for (String str : zoneRulesProvider.provideZoneIds()) {
            Objects.requireNonNull(str, "zoneId");
            if (((ZoneRulesProvider) ZONES.putIfAbsent(str, zoneRulesProvider)) != null) {
                throw new ZoneRulesException("Unable to register zone as one already registered with that ID: " + str + ", currently loading from provider: " + zoneRulesProvider);
            }
        }
    }

    protected ZoneRulesProvider() {
    }
}
