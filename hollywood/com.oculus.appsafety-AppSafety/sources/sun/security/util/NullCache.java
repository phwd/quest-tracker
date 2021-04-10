package sun.security.util;

import sun.security.util.Cache;

/* compiled from: Cache */
class NullCache<K, V> extends Cache<K, V> {
    static final Cache<Object, Object> INSTANCE = new NullCache();

    private NullCache() {
    }

    @Override // sun.security.util.Cache
    public int size() {
        return 0;
    }

    @Override // sun.security.util.Cache
    public void clear() {
    }

    @Override // sun.security.util.Cache
    public void put(K k, V v) {
    }

    @Override // sun.security.util.Cache
    public V get(Object key) {
        return null;
    }

    @Override // sun.security.util.Cache
    public void remove(Object key) {
    }

    @Override // sun.security.util.Cache
    public void setCapacity(int size) {
    }

    @Override // sun.security.util.Cache
    public void setTimeout(int timeout) {
    }

    @Override // sun.security.util.Cache
    public void accept(Cache.CacheVisitor<K, V> cacheVisitor) {
    }
}
