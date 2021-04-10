package oculus.internal.license.store;

import android.util.LruCache;
import com.oculus.license.License;
import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LicensePackageQueryCache implements LicenseStoreObserver {
    private LruCache<Long, License> licenseCache;
    private LruCache<String, Set<Long>> queryCache;

    public LicensePackageQueryCache(LruCache<Long, License> licenseCache2, int maxSize) {
        this.licenseCache = licenseCache2;
        this.queryCache = new LruCache<String, Set<Long>>(maxSize) {
            /* class oculus.internal.license.store.LicensePackageQueryCache.AnonymousClass1 */

            /* access modifiers changed from: protected */
            public int sizeOf(String key, Set<Long> value) {
                return key.length() + (value.size() * 8);
            }
        };
    }

    public Collection<License> get(String packageIdentifier) {
        Set<Long> fbids = this.queryCache.get(packageIdentifier);
        if (fbids == null) {
            return null;
        }
        Collection<License> licenses = (Collection) fbids.stream().map(new Function() {
            /* class oculus.internal.license.store.$$Lambda$LicensePackageQueryCache$cZLLg3I9sClfm39113VSh_y838 */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return LicensePackageQueryCache.this.lambda$get$0$LicensePackageQueryCache((Long) obj);
            }
        }).filter($$Lambda$SddSBZHi6vAVVICJqv16KPm_TfA.INSTANCE).collect(Collectors.toList());
        if (licenses.size() == fbids.size()) {
            return licenses;
        }
        return null;
    }

    public /* synthetic */ License lambda$get$0$LicensePackageQueryCache(Long id) {
        return this.licenseCache.get(id);
    }

    public void put(String packageIdentifier, Collection<License> licenses) {
        licenses.stream().filter(new Predicate() {
            /* class oculus.internal.license.store.$$Lambda$LicensePackageQueryCache$j_xuJLPseMpqLgCDCPBGIoOZMPE */

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return LicensePackageQueryCache.this.lambda$put$1$LicensePackageQueryCache((License) obj);
            }
        }).forEach(new Consumer() {
            /* class oculus.internal.license.store.$$Lambda$LicensePackageQueryCache$e_moMCZtJr5U7PpeqdxufaMGSFg */

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                LicensePackageQueryCache.this.lambda$put$2$LicensePackageQueryCache((License) obj);
            }
        });
        this.queryCache.put(packageIdentifier, (Set) licenses.stream().map($$Lambda$LicensePackageQueryCache$Wevicv6msl0gLyC8lWMcd4I7tqM.INSTANCE).collect(Collectors.toCollection($$Lambda$Wul933eTbCG5LciVpnY1xFYbHAc.INSTANCE)));
    }

    public /* synthetic */ boolean lambda$put$1$LicensePackageQueryCache(License l) {
        return this.licenseCache.get(new Long(l.id)) == null;
    }

    public /* synthetic */ void lambda$put$2$LicensePackageQueryCache(License l) {
        this.licenseCache.put(new Long(l.id), l);
    }

    static /* synthetic */ Long lambda$put$3(License l) {
        return new Long(l.id);
    }

    @Override // oculus.internal.license.store.LicenseStoreObserver
    public void onInstalled(Collection<License> collection) {
        this.queryCache.evictAll();
    }

    @Override // oculus.internal.license.store.LicenseStoreObserver
    public void onDeleted(Collection<Long> collection) {
        this.queryCache.evictAll();
    }
}
