package android.icu.impl;

import android.icu.impl.ICURWLock;
import android.icu.util.ULocale;
import android.support.v4.app.NotificationCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EventListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class ICUService extends ICUNotifier {
    private static final boolean DEBUG = ICUDebug.enabled(NotificationCompat.CATEGORY_SERVICE);
    private Map<String, CacheEntry> cache;
    private int defaultSize;
    private LocaleRef dnref;
    private final List<Factory> factories;
    private final ICURWLock factoryLock;
    private Map<String, Factory> idcache;
    protected final String name;

    public interface Factory {
        Object create(Key key, ICUService iCUService);

        String getDisplayName(String str, ULocale uLocale);

        void updateVisibleIDs(Map<String, Factory> map);
    }

    public interface ServiceListener extends EventListener {
        void serviceChanged(ICUService iCUService);
    }

    public ICUService() {
        this.factoryLock = new ICURWLock();
        this.factories = new ArrayList();
        this.defaultSize = 0;
        this.name = "";
    }

    public ICUService(String name2) {
        this.factoryLock = new ICURWLock();
        this.factories = new ArrayList();
        this.defaultSize = 0;
        this.name = name2;
    }

    public static class Key {
        private final String id;

        public Key(String id2) {
            this.id = id2;
        }

        public final String id() {
            return this.id;
        }

        public String canonicalID() {
            return this.id;
        }

        public String currentID() {
            return canonicalID();
        }

        public String currentDescriptor() {
            return "/" + currentID();
        }

        public boolean fallback() {
            return false;
        }

        public boolean isFallbackOf(String idToCheck) {
            return canonicalID().equals(idToCheck);
        }
    }

    public static class SimpleFactory implements Factory {
        protected String id;
        protected Object instance;
        protected boolean visible;

        public SimpleFactory(Object instance2, String id2) {
            this(instance2, id2, true);
        }

        public SimpleFactory(Object instance2, String id2, boolean visible2) {
            if (instance2 == null || id2 == null) {
                throw new IllegalArgumentException("Instance or id is null");
            }
            this.instance = instance2;
            this.id = id2;
            this.visible = visible2;
        }

        @Override // android.icu.impl.ICUService.Factory
        public Object create(Key key, ICUService service) {
            if (this.id.equals(key.currentID())) {
                return this.instance;
            }
            return null;
        }

        @Override // android.icu.impl.ICUService.Factory
        public void updateVisibleIDs(Map<String, Factory> result) {
            if (this.visible) {
                result.put(this.id, this);
            } else {
                result.remove(this.id);
            }
        }

        @Override // android.icu.impl.ICUService.Factory
        public String getDisplayName(String identifier, ULocale locale) {
            if (!this.visible || !this.id.equals(identifier)) {
                return null;
            }
            return identifier;
        }

        public String toString() {
            return super.toString() + ", id: " + this.id + ", visible: " + this.visible;
        }
    }

    public Object get(String descriptor) {
        return getKey(createKey(descriptor), null);
    }

    public Object get(String descriptor, String[] actualReturn) {
        if (descriptor != null) {
            return getKey(createKey(descriptor), actualReturn);
        }
        throw new NullPointerException("descriptor must not be null");
    }

    public Object getKey(Key key) {
        return getKey(key, null);
    }

    public Object getKey(Key key, String[] actualReturn) {
        return getKey(key, actualReturn, null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x01b0, code lost:
        if (r6 != null) goto L_0x01b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x01b2, code lost:
        r6 = new java.util.ArrayList<>(5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x01b9, code lost:
        r6.add(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x01c1, code lost:
        if (r17.fallback() != false) goto L_0x0277;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getKey(android.icu.impl.ICUService.Key r17, java.lang.String[] r18, android.icu.impl.ICUService.Factory r19) {
        /*
        // Method dump skipped, instructions count: 677
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.ICUService.getKey(android.icu.impl.ICUService$Key, java.lang.String[], android.icu.impl.ICUService$Factory):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final class CacheEntry {
        final String actualDescriptor;
        final Object service;

        CacheEntry(String actualDescriptor2, Object service2) {
            this.actualDescriptor = actualDescriptor2;
            this.service = service2;
        }
    }

    /* access modifiers changed from: protected */
    public Object handleDefault(Key key, String[] actualIDReturn) {
        return null;
    }

    public Set<String> getVisibleIDs() {
        return getVisibleIDs(null);
    }

    public Set<String> getVisibleIDs(String matchID) {
        Set<String> result = getVisibleIDMap().keySet();
        Key fallbackKey = createKey(matchID);
        if (fallbackKey == null) {
            return result;
        }
        Set<String> temp = new HashSet<>(result.size());
        for (String id : result) {
            if (fallbackKey.isFallbackOf(id)) {
                temp.add(id);
            }
        }
        return temp;
    }

    private Map<String, Factory> getVisibleIDMap() {
        synchronized (this) {
            if (this.idcache == null) {
                try {
                    this.factoryLock.acquireRead();
                    Map<String, Factory> mutableMap = new HashMap<>();
                    ListIterator<Factory> lIter = this.factories.listIterator(this.factories.size());
                    while (lIter.hasPrevious()) {
                        lIter.previous().updateVisibleIDs(mutableMap);
                    }
                    this.idcache = Collections.unmodifiableMap(mutableMap);
                } finally {
                    this.factoryLock.releaseRead();
                }
            }
        }
        return this.idcache;
    }

    public String getDisplayName(String id) {
        return getDisplayName(id, ULocale.getDefault(ULocale.Category.DISPLAY));
    }

    public String getDisplayName(String id, ULocale locale) {
        Map<String, Factory> m = getVisibleIDMap();
        Factory f = m.get(id);
        if (f != null) {
            return f.getDisplayName(id, locale);
        }
        Key key = createKey(id);
        while (key.fallback()) {
            Factory f2 = m.get(key.currentID());
            if (f2 != null) {
                return f2.getDisplayName(id, locale);
            }
        }
        return null;
    }

    public SortedMap<String, String> getDisplayNames() {
        return getDisplayNames(ULocale.getDefault(ULocale.Category.DISPLAY), null, null);
    }

    public SortedMap<String, String> getDisplayNames(ULocale locale) {
        return getDisplayNames(locale, null, null);
    }

    public SortedMap<String, String> getDisplayNames(ULocale locale, Comparator<Object> com2) {
        return getDisplayNames(locale, com2, null);
    }

    public SortedMap<String, String> getDisplayNames(ULocale locale, String matchID) {
        return getDisplayNames(locale, null, matchID);
    }

    public SortedMap<String, String> getDisplayNames(ULocale locale, Comparator<Object> com2, String matchID) {
        SortedMap<String, String> dncache = null;
        LocaleRef ref = this.dnref;
        if (ref != null) {
            dncache = ref.get(locale, com2);
        }
        while (dncache == null) {
            synchronized (this) {
                if (ref != this.dnref) {
                    if (this.dnref != null) {
                        ref = this.dnref;
                        dncache = ref.get(locale, com2);
                    }
                }
                SortedMap<String, String> dncache2 = new TreeMap<>(com2);
                for (Map.Entry<String, Factory> e : getVisibleIDMap().entrySet()) {
                    String id = e.getKey();
                    dncache2.put(e.getValue().getDisplayName(id, locale), id);
                }
                dncache = Collections.unmodifiableSortedMap(dncache2);
                this.dnref = new LocaleRef(dncache, locale, com2);
            }
        }
        Key matchKey = createKey(matchID);
        if (matchKey == null) {
            return dncache;
        }
        SortedMap<String, String> result = new TreeMap<>((SortedMap<String, ? extends String>) dncache);
        Iterator<Map.Entry<String, String>> iter = result.entrySet().iterator();
        while (iter.hasNext()) {
            if (!matchKey.isFallbackOf(iter.next().getValue())) {
                iter.remove();
            }
        }
        return result;
    }

    /* access modifiers changed from: private */
    public static class LocaleRef {

        /* renamed from: com  reason: collision with root package name */
        private Comparator<Object> f0com;
        private SortedMap<String, String> dnCache;
        private final ULocale locale;

        LocaleRef(SortedMap<String, String> dnCache2, ULocale locale2, Comparator<Object> com2) {
            this.locale = locale2;
            this.f0com = com2;
            this.dnCache = dnCache2;
        }

        /* access modifiers changed from: package-private */
        public SortedMap<String, String> get(ULocale loc, Comparator<Object> comp) {
            SortedMap<String, String> m = this.dnCache;
            if (m == null || !this.locale.equals(loc)) {
                return null;
            }
            Comparator<Object> comparator = this.f0com;
            if (comparator == comp || (comparator != null && comparator.equals(comp))) {
                return m;
            }
            return null;
        }
    }

    public final List<Factory> factories() {
        try {
            this.factoryLock.acquireRead();
            return new ArrayList(this.factories);
        } finally {
            this.factoryLock.releaseRead();
        }
    }

    public Factory registerObject(Object obj, String id) {
        return registerObject(obj, id, true);
    }

    public Factory registerObject(Object obj, String id, boolean visible) {
        return registerFactory(new SimpleFactory(obj, createKey(id).canonicalID(), visible));
    }

    /* JADX INFO: finally extract failed */
    public final Factory registerFactory(Factory factory) {
        if (factory != null) {
            try {
                this.factoryLock.acquireWrite();
                this.factories.add(0, factory);
                clearCaches();
                this.factoryLock.releaseWrite();
                notifyChanged();
                return factory;
            } catch (Throwable th) {
                this.factoryLock.releaseWrite();
                throw th;
            }
        } else {
            throw new NullPointerException();
        }
    }

    public final boolean unregisterFactory(Factory factory) {
        if (factory != null) {
            boolean result = false;
            try {
                this.factoryLock.acquireWrite();
                if (this.factories.remove(factory)) {
                    result = true;
                    clearCaches();
                }
                if (result) {
                    notifyChanged();
                }
                return result;
            } finally {
                this.factoryLock.releaseWrite();
            }
        } else {
            throw new NullPointerException();
        }
    }

    /* JADX INFO: finally extract failed */
    public final void reset() {
        try {
            this.factoryLock.acquireWrite();
            reInitializeFactories();
            clearCaches();
            this.factoryLock.releaseWrite();
            notifyChanged();
        } catch (Throwable th) {
            this.factoryLock.releaseWrite();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public void reInitializeFactories() {
        this.factories.clear();
    }

    public boolean isDefault() {
        return this.factories.size() == this.defaultSize;
    }

    /* access modifiers changed from: protected */
    public void markDefault() {
        this.defaultSize = this.factories.size();
    }

    public Key createKey(String id) {
        if (id == null) {
            return null;
        }
        return new Key(id);
    }

    /* access modifiers changed from: protected */
    public void clearCaches() {
        this.cache = null;
        this.idcache = null;
        this.dnref = null;
    }

    /* access modifiers changed from: protected */
    public void clearServiceCache() {
        this.cache = null;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.ICUNotifier
    public boolean acceptsListener(EventListener l) {
        return l instanceof ServiceListener;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.impl.ICUNotifier
    public void notifyListener(EventListener l) {
        ((ServiceListener) l).serviceChanged(this);
    }

    public String stats() {
        ICURWLock.Stats stats = this.factoryLock.resetStats();
        if (stats != null) {
            return stats.toString();
        }
        return "no stats";
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return super.toString() + "{" + this.name + "}";
    }
}
