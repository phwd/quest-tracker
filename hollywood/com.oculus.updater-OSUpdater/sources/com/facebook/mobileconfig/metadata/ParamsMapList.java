package com.facebook.mobileconfig.metadata;

import com.facebook.common.cache.WeakKeyWeakValueLoadingCache;
import com.facebook.infer.annotation.ThreadSafe;
import com.facebook.mobileconfig.specifier.MobileConfigKeyUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

public class ParamsMapList {
    public final List<ParamsMapEntry> entries;
    private final WeakKeyWeakValueLoadingCache<Integer, List<ParamsMapEntry>> entryMap;
    @Nullable
    private Map<Integer, Integer> keyToIndex;
    @Nullable
    private Map<String, Integer> nameToIndex;

    @ThreadSafe
    public synchronized List<ParamsMapEntry> entriesOfConfig(int i) {
        List<ParamsMapEntry> list = this.entryMap.get(Integer.valueOf(i));
        if (list != null) {
            return Collections.unmodifiableList(new ArrayList(list));
        }
        return Collections.emptyList();
    }

    public synchronized Map<String, Integer> getConfigIndexes() {
        if (this.nameToIndex == null) {
            this.nameToIndex = new HashMap();
            for (ParamsMapEntry paramsMapEntry : this.entries) {
                this.nameToIndex.put(paramsMapEntry.configName, Integer.valueOf(paramsMapEntry.configIndex));
            }
        }
        return this.nameToIndex;
    }

    public synchronized Map<Integer, Integer> getConfigIndexesFromKey() {
        if (this.keyToIndex == null) {
            this.keyToIndex = new HashMap();
            for (ParamsMapEntry paramsMapEntry : this.entries) {
                if (MobileConfigKeyUtils.validConfigKey(paramsMapEntry.configKey)) {
                    this.keyToIndex.put(Integer.valueOf(paramsMapEntry.configKey), Integer.valueOf(paramsMapEntry.configIndex));
                }
            }
        }
        return this.keyToIndex;
    }
}
