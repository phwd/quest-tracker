package android.icu.impl;

import android.icu.text.CurrencyMetaInfo;
import android.icu.util.Currency;
import android.icu.util.UResourceBundle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ICUCurrencyMetaInfo extends CurrencyMetaInfo {
    private ICUResourceBundle digitInfo;
    private ICUResourceBundle regionInfo;

    /* access modifiers changed from: private */
    public interface Collector {
        void collect(String str, String str2, long j, long j2, int i, boolean z);

        int collects();

        List getList();
    }

    public ICUCurrencyMetaInfo() {
        ICUResourceBundle iCUResourceBundle = (ICUResourceBundle) UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b/curr", "supplementalData", ICUResourceBundle.ICU_DATA_CLASS_LOADER);
        this.regionInfo = iCUResourceBundle.findTopLevel("CurrencyMap");
        this.digitInfo = iCUResourceBundle.findTopLevel("CurrencyMeta");
    }

    @Override // android.icu.text.CurrencyMetaInfo
    public List currencies(CurrencyMetaInfo.CurrencyFilter currencyFilter) {
        return collect(new CurrencyCollector(), currencyFilter);
    }

    @Override // android.icu.text.CurrencyMetaInfo
    public CurrencyMetaInfo.CurrencyDigits currencyDigits(String str, Currency.CurrencyUsage currencyUsage) {
        ICUResourceBundle findWithFallback = this.digitInfo.findWithFallback(str);
        if (findWithFallback == null) {
            findWithFallback = this.digitInfo.findWithFallback("DEFAULT");
        }
        int[] intVector = findWithFallback.getIntVector();
        if (currencyUsage == Currency.CurrencyUsage.CASH) {
            return new CurrencyMetaInfo.CurrencyDigits(intVector[2], intVector[3]);
        }
        if (currencyUsage == Currency.CurrencyUsage.STANDARD) {
            return new CurrencyMetaInfo.CurrencyDigits(intVector[0], intVector[1]);
        }
        return new CurrencyMetaInfo.CurrencyDigits(intVector[0], intVector[1]);
    }

    private List collect(Collector collector, CurrencyMetaInfo.CurrencyFilter currencyFilter) {
        if (currencyFilter == null) {
            currencyFilter = CurrencyMetaInfo.CurrencyFilter.all();
        }
        int collects = collector.collects();
        if (currencyFilter.region != null) {
            collects |= 1;
        }
        if (currencyFilter.currency != null) {
            collects |= 2;
        }
        if (!(currencyFilter.from == Long.MIN_VALUE && currencyFilter.to == Long.MAX_VALUE)) {
            collects |= 4;
        }
        if (currencyFilter.tenderOnly) {
            collects |= 8;
        }
        if (collects != 0) {
            String str = currencyFilter.region;
            if (str != null) {
                ICUResourceBundle findWithFallback = this.regionInfo.findWithFallback(str);
                if (findWithFallback != null) {
                    collectRegion(collector, currencyFilter, collects, findWithFallback);
                }
            } else {
                for (int i = 0; i < this.regionInfo.getSize(); i++) {
                    collectRegion(collector, currencyFilter, collects, this.regionInfo.at(i));
                }
            }
        }
        return collector.getList();
    }

    private void collectRegion(Collector collector, CurrencyMetaInfo.CurrencyFilter currencyFilter, int i, ICUResourceBundle iCUResourceBundle) {
        boolean z;
        String key = iCUResourceBundle.getKey();
        boolean z2 = true;
        if (i == 1) {
            collector.collect(iCUResourceBundle.getKey(), null, 0, 0, -1, false);
            return;
        }
        boolean z3 = false;
        int i2 = 0;
        while (i2 < iCUResourceBundle.getSize()) {
            ICUResourceBundle at = iCUResourceBundle.at(i2);
            if (at.getSize() != 0) {
                String str = null;
                if ((i & 2) != 0) {
                    str = at.at("id").getString();
                    String str2 = currencyFilter.currency;
                    if (str2 != null && !str2.equals(str)) {
                    }
                }
                long j = Long.MAX_VALUE;
                long j2 = Long.MIN_VALUE;
                if ((i & 4) != 0) {
                    j2 = getDate(at.at("from"), Long.MIN_VALUE, z3);
                    j = getDate(at.at("to"), Long.MAX_VALUE, z2);
                    if (currencyFilter.from <= j) {
                        if (currencyFilter.to < j2) {
                        }
                    }
                }
                if ((i & 8) != 0) {
                    ICUResourceBundle at2 = at.at("tender");
                    boolean z4 = at2 == null || "true".equals(at2.getString());
                    if (!currencyFilter.tenderOnly || z4) {
                        z = z4;
                    }
                } else {
                    z = true;
                }
                collector.collect(key, str, j2, j, i2, z);
            }
            i2++;
            z2 = true;
            z3 = false;
        }
    }

    private long getDate(ICUResourceBundle iCUResourceBundle, long j, boolean z) {
        if (iCUResourceBundle == null) {
            return j;
        }
        int[] intVector = iCUResourceBundle.getIntVector();
        return (((long) intVector[0]) << 32) | (((long) intVector[1]) & 4294967295L);
    }

    private static class UniqueList {
        private List list = new ArrayList();
        private Set seen = new HashSet();

        private UniqueList() {
        }

        /* access modifiers changed from: private */
        public static UniqueList create() {
            return new UniqueList();
        }

        /* access modifiers changed from: package-private */
        public void add(Object obj) {
            if (!this.seen.contains(obj)) {
                this.list.add(obj);
                this.seen.add(obj);
            }
        }

        /* access modifiers changed from: package-private */
        public List list() {
            return Collections.unmodifiableList(this.list);
        }
    }

    private static class CurrencyCollector implements Collector {
        private final UniqueList result;

        @Override // android.icu.impl.ICUCurrencyMetaInfo.Collector
        public int collects() {
            return 2;
        }

        private CurrencyCollector() {
            this.result = UniqueList.create();
        }

        @Override // android.icu.impl.ICUCurrencyMetaInfo.Collector
        public void collect(String str, String str2, long j, long j2, int i, boolean z) {
            this.result.add(str2);
        }

        @Override // android.icu.impl.ICUCurrencyMetaInfo.Collector
        public List getList() {
            return this.result.list();
        }
    }
}
