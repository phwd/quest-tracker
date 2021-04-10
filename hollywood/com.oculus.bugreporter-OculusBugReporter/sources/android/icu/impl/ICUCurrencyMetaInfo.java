package android.icu.impl;

import android.icu.text.CurrencyMetaInfo;
import android.icu.util.Currency;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import sun.security.x509.PolicyInformation;

public class ICUCurrencyMetaInfo extends CurrencyMetaInfo {
    private static final int Currency = 2;
    private static final int Date = 4;
    private static final int Everything = Integer.MAX_VALUE;
    private static final long MASK = 4294967295L;
    private static final int Region = 1;
    private static final int Tender = 8;
    private ICUResourceBundle digitInfo;
    private ICUResourceBundle regionInfo;

    /* access modifiers changed from: private */
    public interface Collector<T> {
        void collect(String str, String str2, long j, long j2, int i, boolean z);

        int collects();

        List<T> getList();
    }

    public ICUCurrencyMetaInfo() {
        ICUResourceBundle bundle = (ICUResourceBundle) ICUResourceBundle.getBundleInstance(ICUData.ICU_CURR_BASE_NAME, "supplementalData", ICUResourceBundle.ICU_DATA_CLASS_LOADER);
        this.regionInfo = bundle.findTopLevel("CurrencyMap");
        this.digitInfo = bundle.findTopLevel("CurrencyMeta");
    }

    @Override // android.icu.text.CurrencyMetaInfo
    public List<CurrencyMetaInfo.CurrencyInfo> currencyInfo(CurrencyMetaInfo.CurrencyFilter filter) {
        return collect(new InfoCollector(), filter);
    }

    @Override // android.icu.text.CurrencyMetaInfo
    public List<String> currencies(CurrencyMetaInfo.CurrencyFilter filter) {
        return collect(new CurrencyCollector(), filter);
    }

    @Override // android.icu.text.CurrencyMetaInfo
    public List<String> regions(CurrencyMetaInfo.CurrencyFilter filter) {
        return collect(new RegionCollector(), filter);
    }

    @Override // android.icu.text.CurrencyMetaInfo
    public CurrencyMetaInfo.CurrencyDigits currencyDigits(String isoCode) {
        return currencyDigits(isoCode, Currency.CurrencyUsage.STANDARD);
    }

    @Override // android.icu.text.CurrencyMetaInfo
    public CurrencyMetaInfo.CurrencyDigits currencyDigits(String isoCode, Currency.CurrencyUsage currencyPurpose) {
        ICUResourceBundle b = this.digitInfo.findWithFallback(isoCode);
        if (b == null) {
            b = this.digitInfo.findWithFallback("DEFAULT");
        }
        int[] data = b.getIntVector();
        if (currencyPurpose == Currency.CurrencyUsage.CASH) {
            return new CurrencyMetaInfo.CurrencyDigits(data[2], data[3]);
        }
        if (currencyPurpose == Currency.CurrencyUsage.STANDARD) {
            return new CurrencyMetaInfo.CurrencyDigits(data[0], data[1]);
        }
        return new CurrencyMetaInfo.CurrencyDigits(data[0], data[1]);
    }

    private <T> List<T> collect(Collector<T> collector, CurrencyMetaInfo.CurrencyFilter filter) {
        if (filter == null) {
            filter = CurrencyMetaInfo.CurrencyFilter.all();
        }
        int needed = collector.collects();
        if (filter.region != null) {
            needed |= 1;
        }
        if (filter.currency != null) {
            needed |= 2;
        }
        if (!(filter.from == Long.MIN_VALUE && filter.to == Long.MAX_VALUE)) {
            needed |= 4;
        }
        if (filter.tenderOnly) {
            needed |= 8;
        }
        if (needed != 0) {
            if (filter.region != null) {
                ICUResourceBundle b = this.regionInfo.findWithFallback(filter.region);
                if (b != null) {
                    collectRegion(collector, filter, needed, b);
                }
            } else {
                for (int i = 0; i < this.regionInfo.getSize(); i++) {
                    collectRegion(collector, filter, needed, this.regionInfo.at(i));
                }
            }
        }
        return collector.getList();
    }

    private <T> void collectRegion(Collector<T> collector, CurrencyMetaInfo.CurrencyFilter filter, int needed, ICUResourceBundle b) {
        boolean z;
        int i;
        String currency;
        long to;
        long from;
        boolean z2;
        boolean tender;
        String region = b.getKey();
        boolean z3 = true;
        if (needed == 1) {
            collector.collect(b.getKey(), null, 0, 0, -1, false);
            return;
        }
        int i2 = 0;
        while (i2 < b.getSize()) {
            ICUResourceBundle r = b.at(i2);
            if (r.getSize() == 0) {
                z = z3;
                i = i2;
            } else {
                if ((needed & 2) != 0) {
                    String currency2 = r.at(PolicyInformation.ID).getString();
                    if (filter.currency == null || filter.currency.equals(currency2)) {
                        currency = currency2;
                    } else {
                        z = z3;
                        i = i2;
                    }
                } else {
                    currency = null;
                }
                boolean tender2 = false;
                if ((needed & 4) != 0) {
                    i = i2;
                    long from2 = getDate(r.at("from"), Long.MIN_VALUE, false);
                    z2 = true;
                    long to2 = getDate(r.at("to"), Long.MAX_VALUE, true);
                    if (filter.from > to2) {
                        z = true;
                    } else if (filter.to < from2) {
                        z = true;
                    } else {
                        from = from2;
                        to = to2;
                    }
                } else {
                    z2 = z3;
                    i = i2;
                    from = Long.MIN_VALUE;
                    to = Long.MAX_VALUE;
                }
                if ((needed & 8) != 0) {
                    ICUResourceBundle tenderBundle = r.at("tender");
                    if (tenderBundle == null || "true".equals(tenderBundle.getString())) {
                        tender2 = z2;
                    }
                    if (!filter.tenderOnly || tender2) {
                        tender = tender2;
                    } else {
                        z = z2;
                    }
                } else {
                    tender = true;
                }
                z = z2;
                collector.collect(region, currency, from, to, i, tender);
            }
            i2 = i + 1;
            z3 = z;
        }
    }

    private long getDate(ICUResourceBundle b, long defaultValue, boolean endOfDay) {
        if (b == null) {
            return defaultValue;
        }
        int[] values = b.getIntVector();
        return (((long) values[0]) << 32) | (((long) values[1]) & MASK);
    }

    private static class UniqueList<T> {
        private List<T> list = new ArrayList();
        private Set<T> seen = new HashSet();

        private UniqueList() {
        }

        /* access modifiers changed from: private */
        public static <T> UniqueList<T> create() {
            return new UniqueList<>();
        }

        /* access modifiers changed from: package-private */
        public void add(T value) {
            if (!this.seen.contains(value)) {
                this.list.add(value);
                this.seen.add(value);
            }
        }

        /* access modifiers changed from: package-private */
        public List<T> list() {
            return Collections.unmodifiableList(this.list);
        }
    }

    private static class InfoCollector implements Collector<CurrencyMetaInfo.CurrencyInfo> {
        private List<CurrencyMetaInfo.CurrencyInfo> result;

        private InfoCollector() {
            this.result = new ArrayList();
        }

        @Override // android.icu.impl.ICUCurrencyMetaInfo.Collector
        public void collect(String region, String currency, long from, long to, int priority, boolean tender) {
            this.result.add(new CurrencyMetaInfo.CurrencyInfo(region, currency, from, to, priority, tender));
        }

        @Override // android.icu.impl.ICUCurrencyMetaInfo.Collector
        public List<CurrencyMetaInfo.CurrencyInfo> getList() {
            return Collections.unmodifiableList(this.result);
        }

        @Override // android.icu.impl.ICUCurrencyMetaInfo.Collector
        public int collects() {
            return Integer.MAX_VALUE;
        }
    }

    private static class RegionCollector implements Collector<String> {
        private final UniqueList<String> result;

        private RegionCollector() {
            this.result = UniqueList.create();
        }

        @Override // android.icu.impl.ICUCurrencyMetaInfo.Collector
        public void collect(String region, String currency, long from, long to, int priority, boolean tender) {
            this.result.add(region);
        }

        @Override // android.icu.impl.ICUCurrencyMetaInfo.Collector
        public int collects() {
            return 1;
        }

        @Override // android.icu.impl.ICUCurrencyMetaInfo.Collector
        public List<String> getList() {
            return this.result.list();
        }
    }

    private static class CurrencyCollector implements Collector<String> {
        private final UniqueList<String> result;

        private CurrencyCollector() {
            this.result = UniqueList.create();
        }

        @Override // android.icu.impl.ICUCurrencyMetaInfo.Collector
        public void collect(String region, String currency, long from, long to, int priority, boolean tender) {
            this.result.add(currency);
        }

        @Override // android.icu.impl.ICUCurrencyMetaInfo.Collector
        public int collects() {
            return 2;
        }

        @Override // android.icu.impl.ICUCurrencyMetaInfo.Collector
        public List<String> getList() {
            return this.result.list();
        }
    }
}
