package android.icu.impl;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Map;

public class ICUService extends ICUNotifier {
    private static final boolean DEBUG = ICUDebug.enabled("service");
    private Map cache;
    private int defaultSize = 0;
    private LocaleRef dnref;
    private final List factories = new ArrayList();
    private final ICURWLock factoryLock = new ICURWLock();
    private Map idcache;
    protected final String name;

    public interface Factory {
        Object create(Key key, ICUService iCUService);
    }

    /* access modifiers changed from: private */
    public static class LocaleRef {
    }

    public interface ServiceListener extends EventListener {
        void serviceChanged(ICUService iCUService);
    }

    /* access modifiers changed from: protected */
    public Object handleDefault(Key key, String[] strArr) {
        return null;
    }

    public ICUService(String str) {
        this.name = str;
    }

    public static class Key {
        private final String id;

        public String canonicalID() {
            throw null;
        }

        public String currentDescriptor() {
            throw null;
        }

        public String currentID() {
            throw null;
        }

        public boolean fallback() {
            throw null;
        }

        public Key(String str) {
            this.id = str;
        }
    }

    public Object getKey(Key key) {
        return getKey(key, null);
    }

    public Object getKey(Key key, String[] strArr) {
        return getKey(key, strArr, null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:56:0x01a9, code lost:
        if (r9 != null) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x01ab, code lost:
        r9 = new java.util.ArrayList(5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x01b2, code lost:
        r9.add(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x01b9, code lost:
        if (r17.fallback() != false) goto L_0x026e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getKey(android.icu.impl.ICUService.Key r17, java.lang.String[] r18, android.icu.impl.ICUService.Factory r19) {
        /*
        // Method dump skipped, instructions count: 665
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.ICUService.getKey(android.icu.impl.ICUService$Key, java.lang.String[], android.icu.impl.ICUService$Factory):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final class CacheEntry {
        final String actualDescriptor;
        final Object service;

        CacheEntry(String str, Object obj) {
            this.actualDescriptor = str;
            this.service = obj;
        }
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

    public boolean isDefault() {
        return this.factories.size() == this.defaultSize;
    }

    /* access modifiers changed from: protected */
    public void markDefault() {
        this.defaultSize = this.factories.size();
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
    public void notifyListener(EventListener eventListener) {
        ((ServiceListener) eventListener).serviceChanged(this);
    }

    public String toString() {
        new StringBuilder();
        super.toString();
        throw null;
    }
}
