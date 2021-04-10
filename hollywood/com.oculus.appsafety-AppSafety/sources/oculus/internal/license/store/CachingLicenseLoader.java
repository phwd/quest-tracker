package oculus.internal.license.store;

import android.content.ContentValues;
import android.util.LruCache;
import com.oculus.license.License;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class CachingLicenseLoader implements LicenseLoader, LicenseStoreObserver {
    private final LruCache<Long, License> cache;
    private final LicenseLoader factory;

    public static LruCache<Long, License> createCache(int maxSize) {
        return new LruCache<Long, License>(maxSize) {
            /* class oculus.internal.license.store.CachingLicenseLoader.AnonymousClass1 */

            /* access modifiers changed from: protected */
            public int sizeOf(Long key, License value) {
                return value.size();
            }
        };
    }

    public CachingLicenseLoader(LicenseLoader factory2, LruCache<Long, License> cache2) {
        this.factory = factory2;
        this.cache = cache2;
    }

    @Override // oculus.internal.license.store.LicenseLoader
    public License load(ContentValues row) throws Exception {
        Long fbid = row.getAsLong("fbid");
        if (fbid != null) {
            License license = this.cache.get(fbid);
            if (license != null) {
                return license;
            }
            License license2 = this.factory.load(row);
            this.cache.put(fbid, license2);
            return license2;
        }
        throw new IllegalArgumentException("Row does not contain FBID column");
    }

    public /* synthetic */ void lambda$onInstalled$0$CachingLicenseLoader(License l) {
        this.cache.put(Long.valueOf(l.id), l);
    }

    @Override // oculus.internal.license.store.LicenseStoreObserver
    public void onInstalled(Collection<License> licenses) {
        ((Stream) licenses.stream().sequential()).forEach(new Consumer() {
            /* class oculus.internal.license.store.$$Lambda$CachingLicenseLoader$Gn53IAhdMSvqb6ciUdO1I2wThuU */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CachingLicenseLoader.this.lambda$onInstalled$0$CachingLicenseLoader((License) obj);
            }
        });
    }

    public /* synthetic */ void lambda$onDeleted$1$CachingLicenseLoader(Long id) {
        this.cache.remove(id);
    }

    @Override // oculus.internal.license.store.LicenseStoreObserver
    public void onDeleted(Collection<Long> fbids) {
        ((Stream) fbids.stream().sequential()).forEach(new Consumer() {
            /* class oculus.internal.license.store.$$Lambda$CachingLicenseLoader$LsGLqiLXF4uwLoi_hloU5sOJqw */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CachingLicenseLoader.this.lambda$onDeleted$1$CachingLicenseLoader((Long) obj);
            }
        });
    }

    public void dumpOverview(PrintWriter pw) {
        pw.format("License cache stats: ~%d bytes; %d entries; %d inserts; %d hits; %d max size; %d evictions\n", Integer.valueOf(this.cache.size()), Integer.valueOf(this.cache.snapshot().size()), Integer.valueOf(this.cache.putCount()), Integer.valueOf(this.cache.hitCount()), Integer.valueOf(this.cache.maxSize()), Integer.valueOf(this.cache.evictionCount()));
    }

    public Map<Long, License> snapshot() {
        return this.cache.snapshot();
    }
}
