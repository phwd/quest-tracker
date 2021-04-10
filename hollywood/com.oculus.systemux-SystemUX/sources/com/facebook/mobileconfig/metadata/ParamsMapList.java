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

    public ParamsMapList(List<ParamsMapEntry> list) {
        this.entries = Collections.unmodifiableList(new ArrayList(list));
        this.entryMap = new WeakKeyWeakValueLoadingCache<>(new CacheLoader<Integer, List<ParamsMapEntry>>() {
            /* class com.facebook.mobileconfig.metadata.ParamsMapList.AnonymousClass1 */

            public List<ParamsMapEntry> load(Integer num) {
                int i = 0;
                int i2 = -1;
                while (i < ParamsMapList.this.entries.size()) {
                    ParamsMapEntry paramsMapEntry = ParamsMapList.this.entries.get(i);
                    if (i2 == -1) {
                        if (paramsMapEntry.configIndex == num.intValue()) {
                            i2 = i;
                        }
                    } else if (paramsMapEntry.configIndex != num.intValue()) {
                        break;
                    }
                    i++;
                }
                if (i2 != -1) {
                    return ParamsMapList.this.entries.subList(i2, i);
                }
                return Collections.emptyList();
            }
        });
    }

    @ThreadSafe
    public synchronized List<ParamsMapEntry> entriesOfConfig(int i) {
        List list = (List) this.entryMap.get(Integer.valueOf(i));
        if (list != null) {
            return Collections.unmodifiableList(new ArrayList(list));
        }
        return Collections.emptyList();
    }

    @Nullable
    @ThreadSafe
    public synchronized String getConfigName(int i) {
        List list = (List) this.entryMap.get(Integer.valueOf(i));
        if (list == null || list.size() <= 0) {
            return null;
        }
        return ((ParamsMapEntry) list.get(0)).configName;
    }

    public synchronized String getConfigSpecByIndex(int i) {
        List list = (List) this.entryMap.get(Integer.valueOf(i));
        if (list == null || list.size() <= 0) {
            return null;
        }
        return MobileConfigKeyUtils.getConfigSpec(((ParamsMapEntry) list.get(0)).configKey, ((ParamsMapEntry) list.get(0)).configName);
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

    public synchronized Map<String, Integer> getConfigSpecToIndexMapping() {
        if (this.specToIndex == null) {
            this.specToIndex = new HashMap();
            for (ParamsMapEntry paramsMapEntry : this.entries) {
                this.specToIndex.put(MobileConfigKeyUtils.getConfigSpec(paramsMapEntry.configKey, paramsMapEntry.configName), Integer.valueOf(paramsMapEntry.configIndex));
            }
        }
        return this.specToIndex;
    }
}
