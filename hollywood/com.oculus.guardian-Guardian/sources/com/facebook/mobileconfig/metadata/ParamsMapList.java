package com.facebook.mobileconfig.metadata;

import com.facebook.common.cache.CacheLoader;
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
    private Map<Integer, Integer> keyToIndex = null;
    @Nullable
    private Map<String, Integer> nameToIndex = null;
    @Nullable
    private Map<String, Integer> specToIndex = null;

    public ParamsMapList(List<ParamsMapEntry> entryList) {
        this.entries = Collections.unmodifiableList(new ArrayList(entryList));
        this.entryMap = new WeakKeyWeakValueLoadingCache<>(new CacheLoader<Integer, List<ParamsMapEntry>>() {
            /* class com.facebook.mobileconfig.metadata.ParamsMapList.AnonymousClass1 */

            public List<ParamsMapEntry> load(Integer configId) {
                int configStartIndex = -1;
                int iterIdx = 0;
                while (iterIdx < ParamsMapList.this.entries.size()) {
                    ParamsMapEntry entry = ParamsMapList.this.entries.get(iterIdx);
                    if (configStartIndex == -1) {
                        if (entry.configIndex == configId.intValue()) {
                            configStartIndex = iterIdx;
                        }
                    } else if (entry.configIndex != configId.intValue()) {
                        break;
                    }
                    iterIdx++;
                }
                if (configStartIndex != -1) {
                    return ParamsMapList.this.entries.subList(configStartIndex, iterIdx);
                }
                return Collections.emptyList();
            }
        });
    }

    @ThreadSafe
    public synchronized List<ParamsMapEntry> entriesOfConfig(int configId) {
        List<ParamsMapEntry> params = (List) this.entryMap.get(Integer.valueOf(configId));
        if (params != null) {
            return Collections.unmodifiableList(new ArrayList(params));
        }
        return Collections.emptyList();
    }

    @Nullable
    @ThreadSafe
    public synchronized String getConfigName(int index) {
        List<ParamsMapEntry> entry = (List) this.entryMap.get(Integer.valueOf(index));
        if (entry == null || entry.size() <= 0) {
            return null;
        }
        return entry.get(0).configName;
    }

    public synchronized String getConfigSpecByIndex(int index) {
        List<ParamsMapEntry> entry = (List) this.entryMap.get(Integer.valueOf(index));
        if (entry == null || entry.size() <= 0) {
            return null;
        }
        return MobileConfigKeyUtils.getConfigSpec(entry.get(0).configKey, entry.get(0).configName);
    }

    public synchronized Map<String, Integer> getConfigIndexes() {
        if (this.nameToIndex == null) {
            this.nameToIndex = new HashMap();
            for (ParamsMapEntry entry : this.entries) {
                this.nameToIndex.put(entry.configName, Integer.valueOf(entry.configIndex));
            }
        }
        return this.nameToIndex;
    }

    public synchronized Map<Integer, Integer> getConfigIndexesFromKey() {
        if (this.keyToIndex == null) {
            this.keyToIndex = new HashMap();
            for (ParamsMapEntry entry : this.entries) {
                if (MobileConfigKeyUtils.validConfigKey(entry.configKey)) {
                    this.keyToIndex.put(Integer.valueOf(entry.configKey), Integer.valueOf(entry.configIndex));
                }
            }
        }
        return this.keyToIndex;
    }

    public synchronized Map<String, Integer> getConfigSpecToIndexMapping() {
        if (this.specToIndex == null) {
            this.specToIndex = new HashMap();
            for (ParamsMapEntry entry : this.entries) {
                this.specToIndex.put(MobileConfigKeyUtils.getConfigSpec(entry.configKey, entry.configName), Integer.valueOf(entry.configIndex));
            }
        }
        return this.specToIndex;
    }
}
