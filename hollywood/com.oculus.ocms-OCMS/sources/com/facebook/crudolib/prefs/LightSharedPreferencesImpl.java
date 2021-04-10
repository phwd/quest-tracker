package com.facebook.crudolib.prefs;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.core.os.TraceCompat;
import com.facebook.common.collectlite.DirectHashSet;
import com.facebook.crudolib.prefs.LightSharedPreferences;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.NullsafeStrict;
import com.facebook.infer.annotation.PropagatesNullable;
import java.io.File;
import java.io.IOException;
import java.lang.Thread;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.concurrent.GuardedBy;

/* access modifiers changed from: package-private */
@NullsafeStrict
public class LightSharedPreferencesImpl implements LightSharedPreferences {
    private static final String TAG = "LightSharedPreferencesImpl";
    private static final Handler sMainHandler = new Handler(Looper.getMainLooper());
    private static final Object sRemovedSentinel = new Object();
    @GuardedBy("mCacheLock")
    private final Map<String, Object> mCache;
    private final Object mCacheLock = new Object();
    private final int mCommitOnMainThreadPolicy;
    private final Executor mDiskIoExecutor;
    private final Thread mInitThread;
    private final CountDownLatch mInitializedLatch;
    @Nullable
    private Throwable mLastCommitCallStack;
    @GuardedBy("this")
    private final Map<String, Map<LightSharedPreferences.OnSharedPreferenceChangeListener, Handler>> mListeners;
    private volatile boolean mLoaded = false;
    private final LightSharedPreferencesStorage mStorage;
    private final AtomicBoolean maybeOutOfSync = new AtomicBoolean(false);

    LightSharedPreferencesImpl(File file, Executor executor, int i) {
        this.mStorage = new LightSharedPreferencesStorage((File) checkNotNull(file));
        this.mCache = new HashMap();
        this.mListeners = new HashMap();
        this.mDiskIoExecutor = (Executor) checkNotNull(executor);
        this.mCommitOnMainThreadPolicy = i;
        this.mInitializedLatch = new CountDownLatch(1);
        AnonymousClass1 r5 = new Runnable() {
            /* class com.facebook.crudolib.prefs.LightSharedPreferencesImpl.AnonymousClass1 */

            public void run() {
                TraceCompat.beginSection("LightSharedPreferences.tryLoadSharedPreference");
                try {
                    synchronized (LightSharedPreferencesImpl.this.mCacheLock) {
                        LightSharedPreferencesImpl.this.mStorage.tryLoadSharedPreference(LightSharedPreferencesImpl.this.mCache);
                    }
                } finally {
                    LightSharedPreferencesImpl.this.mLoaded = true;
                    LightSharedPreferencesImpl.this.mInitializedLatch.countDown();
                    TraceCompat.endSection();
                }
            }
        };
        this.mInitThread = new Thread(r5, "LSP-" + file.getName());
        this.mInitThread.start();
    }

    private void waitIfNotLoaded() {
        if (!this.mLoaded) {
            TraceCompat.beginSection("LightSharedPreferences.waitIfNotLoaded");
            while (!this.mLoaded) {
                maybeUpdateThreadPriority();
                try {
                    this.mInitializedLatch.await();
                    break;
                } catch (InterruptedException unused) {
                }
            }
            TraceCompat.endSection();
        }
    }

    private synchronized void maybeUpdateThreadPriority() {
        int priority;
        if (this.mInitThread.getState() != Thread.State.TERMINATED && (priority = Thread.currentThread().getPriority()) > this.mInitThread.getPriority()) {
            this.mInitThread.setPriority(priority);
        }
    }

    public class EditorImpl implements LightSharedPreferences.Editor {
        private volatile boolean mClear = false;
        private volatile boolean mIsModifiedFrozen = false;
        private final Map<String, Object> mModified = new HashMap(4);

        public EditorImpl() {
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.Map<java.lang.String, java.lang.Object> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.crudolib.prefs.LightSharedPreferences.Editor
        public LightSharedPreferences.Editor putBoolean(String str, boolean z) {
            throwIfModifiedIsFrozen();
            this.mModified.put(LightSharedPreferencesImpl.checkNotNull(str), Boolean.valueOf(z));
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.Map<java.lang.String, java.lang.Object> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.crudolib.prefs.LightSharedPreferences.Editor
        public LightSharedPreferences.Editor putInt(String str, int i) {
            throwIfModifiedIsFrozen();
            this.mModified.put(LightSharedPreferencesImpl.checkNotNull(str), Integer.valueOf(i));
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.Map<java.lang.String, java.lang.Object> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.crudolib.prefs.LightSharedPreferences.Editor
        public LightSharedPreferences.Editor putLong(String str, long j) {
            throwIfModifiedIsFrozen();
            this.mModified.put(LightSharedPreferencesImpl.checkNotNull(str), Long.valueOf(j));
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.Map<java.lang.String, java.lang.Object> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.crudolib.prefs.LightSharedPreferences.Editor
        public LightSharedPreferences.Editor putFloat(String str, float f) {
            throwIfModifiedIsFrozen();
            this.mModified.put(LightSharedPreferencesImpl.checkNotNull(str), Float.valueOf(f));
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.Map<java.lang.String, java.lang.Object> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.crudolib.prefs.LightSharedPreferences.Editor
        public LightSharedPreferences.Editor putDouble(String str, double d) {
            throwIfModifiedIsFrozen();
            this.mModified.put(LightSharedPreferencesImpl.checkNotNull(str), Double.valueOf(d));
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.Map<java.lang.String, java.lang.Object> */
        /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: java.util.Map<java.lang.String, java.lang.Object> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.crudolib.prefs.LightSharedPreferences.Editor
        public LightSharedPreferences.Editor putString(String str, @Nullable String str2) {
            throwIfModifiedIsFrozen();
            if (str2 == null) {
                this.mModified.put(LightSharedPreferencesImpl.checkNotNull(str), LightSharedPreferencesImpl.sRemovedSentinel);
            } else {
                this.mModified.put(LightSharedPreferencesImpl.checkNotNull(str), str2);
            }
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.Map<java.lang.String, java.lang.Object> */
        /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: java.util.Map<java.lang.String, java.lang.Object> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.crudolib.prefs.LightSharedPreferences.Editor
        public LightSharedPreferences.Editor putStringSet(String str, @Nullable Set<String> set) {
            throwIfModifiedIsFrozen();
            if (set == null) {
                this.mModified.put(LightSharedPreferencesImpl.checkNotNull(str), LightSharedPreferencesImpl.sRemovedSentinel);
            } else {
                this.mModified.put(LightSharedPreferencesImpl.checkNotNull(str), set);
            }
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.Map<java.lang.String, java.lang.Object> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.facebook.crudolib.prefs.LightSharedPreferences.Editor
        public LightSharedPreferences.Editor remove(String str) {
            throwIfModifiedIsFrozen();
            this.mModified.put(LightSharedPreferencesImpl.checkNotNull(str), LightSharedPreferencesImpl.sRemovedSentinel);
            return this;
        }

        @Override // com.facebook.crudolib.prefs.LightSharedPreferences.Editor
        public LightSharedPreferences.Editor clear() {
            throwIfModifiedIsFrozen();
            this.mClear = true;
            return this;
        }

        @Override // com.facebook.crudolib.prefs.LightSharedPreferences.Editor
        public boolean commit() {
            return commit(LightSharedPreferencesImpl.this.mCommitOnMainThreadPolicy);
        }

        @Override // com.facebook.crudolib.prefs.LightSharedPreferences.Editor
        public boolean commit(int i) {
            if (i != 0 && Looper.myLooper() == Looper.getMainLooper()) {
                if (i == 1) {
                    BLog.wtf(LightSharedPreferencesImpl.TAG, "commit is called on the main thread.");
                } else {
                    throw new IllegalStateException("commit is called on the main thread.");
                }
            }
            try {
                Set<String> commitToMemory = commitToMemory(freezeModified());
                if (!commitToMemory.isEmpty()) {
                    LightSharedPreferencesImpl.this.notifyListeners(commitToMemory);
                    return synchronizeDiskWithCache();
                }
                thawModified();
                return true;
            } finally {
                thawModified();
            }
        }

        @Override // com.facebook.crudolib.prefs.LightSharedPreferences.Editor
        public void apply() {
            try {
                Set<String> commitToMemory = commitToMemory(freezeModified());
                if (!commitToMemory.isEmpty()) {
                    LightSharedPreferencesImpl.this.notifyListeners(commitToMemory);
                    LightSharedPreferencesImpl.this.mDiskIoExecutor.execute(new Runnable() {
                        /* class com.facebook.crudolib.prefs.LightSharedPreferencesImpl.EditorImpl.AnonymousClass1 */

                        public void run() {
                            EditorImpl.this.synchronizeDiskWithCache();
                        }
                    });
                }
            } finally {
                thawModified();
            }
        }

        private Set<String> commitToMemory(Map<String, Object> map) {
            DirectHashSet directHashSet = new DirectHashSet();
            synchronized (LightSharedPreferencesImpl.this.mCacheLock) {
                if (this.mClear) {
                    directHashSet.addAll(LightSharedPreferencesImpl.this.mCache.keySet());
                    LightSharedPreferencesImpl.this.mCache.clear();
                }
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (value == LightSharedPreferencesImpl.sRemovedSentinel) {
                        LightSharedPreferencesImpl.this.mCache.remove(key);
                    } else if (!LightSharedPreferencesImpl.checkNotNull(value).equals(LightSharedPreferencesImpl.this.mCache.get(key))) {
                        LightSharedPreferencesImpl.this.mCache.put(key, value);
                    }
                    directHashSet.add(key);
                }
                LightSharedPreferencesImpl.this.maybeOutOfSync.compareAndSet(false, !directHashSet.isEmpty());
            }
            this.mClear = false;
            map.clear();
            return directHashSet;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private boolean synchronizeDiskWithCache() {
            HashMap hashMap;
            if (!LightSharedPreferencesImpl.this.maybeOutOfSync.get()) {
                return true;
            }
            synchronized (LightSharedPreferencesImpl.this.mCacheLock) {
                LightSharedPreferencesImpl.this.maybeOutOfSync.set(false);
                hashMap = new HashMap(LightSharedPreferencesImpl.this.mCache);
            }
            return commitToDisk(hashMap);
        }

        /* access modifiers changed from: package-private */
        public boolean commitToDisk(Map<String, Object> map) {
            try {
                LightSharedPreferencesImpl.this.mStorage.saveSharedPreference((Map) LightSharedPreferencesImpl.checkNotNull(map));
                return true;
            } catch (IOException e) {
                BLog.w(LightSharedPreferencesImpl.TAG, "Commit to disk failed.", e);
                return false;
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized Map<String, Object> freezeModified() {
            if (!this.mIsModifiedFrozen) {
                this.mIsModifiedFrozen = true;
            } else {
                throw new RuntimeException("Trying to freeze an editor that is already frozen!");
            }
            return this.mModified;
        }

        private void throwIfModifiedIsFrozen() {
            if (this.mIsModifiedFrozen) {
                throw new ConcurrentModificationException("Editors shouldn't be modified during commit!");
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized void thawModified() {
            this.mIsModifiedFrozen = false;
        }
    }

    @Override // com.facebook.crudolib.prefs.LightSharedPreferences
    public Map<String, ?> getAll() {
        HashMap hashMap;
        waitIfNotLoaded();
        synchronized (this.mCacheLock) {
            hashMap = new HashMap(this.mCache);
        }
        return hashMap;
    }

    @Override // com.facebook.crudolib.prefs.LightSharedPreferences
    public int getSize() {
        int size;
        waitIfNotLoaded();
        synchronized (this.mCacheLock) {
            size = this.mCache.size();
        }
        return size;
    }

    @Override // com.facebook.crudolib.prefs.LightSharedPreferences
    public boolean getBoolean(String str, boolean z) {
        waitIfNotLoaded();
        synchronized (this.mCacheLock) {
            try {
                Boolean bool = (Boolean) this.mCache.get(str);
                if (bool != null) {
                    z = bool.booleanValue();
                }
            } catch (ClassCastException e) {
                throw createRuntimeExceptionWithRawData(e, str);
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    @Override // com.facebook.crudolib.prefs.LightSharedPreferences
    public int getInt(String str, int i) {
        waitIfNotLoaded();
        synchronized (this.mCacheLock) {
            try {
                Integer num = (Integer) this.mCache.get(str);
                if (num != null) {
                    i = num.intValue();
                }
            } catch (ClassCastException e) {
                throw createRuntimeExceptionWithRawData(e, str);
            } catch (Throwable th) {
                throw th;
            }
        }
        return i;
    }

    @Override // com.facebook.crudolib.prefs.LightSharedPreferences
    public long getLong(String str, long j) {
        waitIfNotLoaded();
        synchronized (this.mCacheLock) {
            try {
                Long l = (Long) this.mCache.get(str);
                if (l != null) {
                    j = l.longValue();
                }
            } catch (ClassCastException e) {
                throw createRuntimeExceptionWithRawData(e, str);
            } catch (Throwable th) {
                throw th;
            }
        }
        return j;
    }

    @Override // com.facebook.crudolib.prefs.LightSharedPreferences
    public float getFloat(String str, float f) {
        waitIfNotLoaded();
        synchronized (this.mCacheLock) {
            try {
                Float f2 = (Float) this.mCache.get(str);
                if (f2 != null) {
                    f = f2.floatValue();
                }
            } catch (ClassCastException e) {
                throw createRuntimeExceptionWithRawData(e, str);
            } catch (Throwable th) {
                throw th;
            }
        }
        return f;
    }

    @Override // com.facebook.crudolib.prefs.LightSharedPreferences
    public double getDouble(String str, double d) {
        waitIfNotLoaded();
        synchronized (this.mCacheLock) {
            try {
                Double d2 = (Double) this.mCache.get(str);
                if (d2 != null) {
                    d = d2.doubleValue();
                }
            } catch (ClassCastException e) {
                throw createRuntimeExceptionWithRawData(e, str);
            } catch (Throwable th) {
                throw th;
            }
        }
        return d;
    }

    @Override // com.facebook.crudolib.prefs.LightSharedPreferences
    public String getString(String str, @PropagatesNullable String str2) {
        waitIfNotLoaded();
        synchronized (this.mCacheLock) {
            try {
                String str3 = (String) this.mCache.get(str);
                if (str3 != null) {
                    str2 = str3;
                }
            } catch (ClassCastException e) {
                throw createRuntimeExceptionWithRawData(e, str);
            } catch (Throwable th) {
                throw th;
            }
        }
        return str2;
    }

    @Override // com.facebook.crudolib.prefs.LightSharedPreferences
    public Set<String> getStringSet(String str, @PropagatesNullable Set<String> set) {
        waitIfNotLoaded();
        synchronized (this.mCacheLock) {
            try {
                Set<String> set2 = (Set) this.mCache.get(str);
                if (set2 != null) {
                    set = set2;
                }
            } catch (ClassCastException e) {
                throw createRuntimeExceptionWithRawData(e, str);
            } catch (Throwable th) {
                throw th;
            }
        }
        return set;
    }

    @Override // com.facebook.crudolib.prefs.LightSharedPreferences
    public boolean contains(String str) {
        boolean containsKey;
        waitIfNotLoaded();
        synchronized (this.mCacheLock) {
            containsKey = this.mCache.containsKey(str);
        }
        return containsKey;
    }

    @Override // com.facebook.crudolib.prefs.LightSharedPreferences
    public LightSharedPreferences.Editor edit() {
        waitIfNotLoaded();
        return new EditorImpl();
    }

    @Override // com.facebook.crudolib.prefs.LightSharedPreferences
    public synchronized void registerOnSharedPreferenceChangeListener(String str, LightSharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        registerOnSharedPreferenceChangeListener(str, sMainHandler, onSharedPreferenceChangeListener);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.Map] */
    @Override // com.facebook.crudolib.prefs.LightSharedPreferences
    public synchronized void registerOnSharedPreferenceChangeListener(String str, Handler handler, LightSharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        Map<LightSharedPreferences.OnSharedPreferenceChangeListener, Handler> map = this.mListeners.get(checkNotNull(str));
        if (map == null) {
            map = new HashMap<>();
            this.mListeners.put(str, map);
        }
        map.put(checkNotNull(onSharedPreferenceChangeListener), checkNotNull(handler));
    }

    @Override // com.facebook.crudolib.prefs.LightSharedPreferences
    public synchronized void unregisterOnSharedPreferenceChangeListener(String str, LightSharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        Map<LightSharedPreferences.OnSharedPreferenceChangeListener, Handler> map = this.mListeners.get(checkNotNull(str));
        if (map != null) {
            map.remove(checkNotNull(onSharedPreferenceChangeListener));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void notifyListeners(Set<String> set) {
        final Throwable th = new Throwable("commit stack");
        for (final String str : set) {
            Map<LightSharedPreferences.OnSharedPreferenceChangeListener, Handler> map = this.mListeners.get(str);
            if (map != null) {
                for (Map.Entry<LightSharedPreferences.OnSharedPreferenceChangeListener, Handler> entry : map.entrySet()) {
                    final LightSharedPreferences.OnSharedPreferenceChangeListener key = entry.getKey();
                    entry.getValue().post(new Runnable() {
                        /* class com.facebook.crudolib.prefs.LightSharedPreferencesImpl.AnonymousClass2 */

                        public void run() {
                            LightSharedPreferencesImpl.this.mLastCommitCallStack = th;
                            key.onSharedPreferenceChanged(LightSharedPreferencesImpl.this, str);
                        }
                    });
                }
            }
        }
        this.mLastCommitCallStack = null;
    }

    @Override // com.facebook.crudolib.prefs.LightSharedPreferences
    @Nullable
    public Throwable getLastCommitCallStack() {
        return this.mLastCommitCallStack;
    }

    /* access modifiers changed from: private */
    public static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    private RuntimeException createRuntimeExceptionWithRawData(Exception exc, String str) {
        return new RuntimeException("LightSharedPreferences threw an exception for Key: " + str + "; Raw file: " + this.mStorage.tryReadRawFile(), exc);
    }
}
