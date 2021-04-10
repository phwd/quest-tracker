package com.facebook.gk.store;

import android.text.TextUtils;
import com.facebook.debug.log.BLog;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class GatekeeperRepository {
    private static final String TAG = "GatekeeperRepository";
    @Nullable
    private String mCurrentHash;
    private final FileLockHelper mFileLockHelper;
    private boolean mIsLoaded;
    private final AtomicFileHelper<NamesFileContent> mNamesFileHelper;
    private String mSavedHash;
    private final AtomicFileHelper<StateFileContent> mStateFileHelper;
    private final GatekeeperStoreConfig mStoreConfig;
    private final File mWorkingDirectory;

    public GatekeeperRepository(GatekeeperStoreConfig gatekeeperStoreConfig, File file) {
        this(gatekeeperStoreConfig, new AtomicFileHelper(new StateFileSerializer(), file, "gk_state"), new AtomicFileHelper(new NamesFileSerializer(), file, "gk_names"), file);
    }

    GatekeeperRepository(GatekeeperStoreConfig gatekeeperStoreConfig, AtomicFileHelper<StateFileContent> atomicFileHelper, AtomicFileHelper<NamesFileContent> atomicFileHelper2, File file) {
        this.mStoreConfig = gatekeeperStoreConfig;
        this.mStateFileHelper = atomicFileHelper;
        this.mNamesFileHelper = atomicFileHelper2;
        this.mWorkingDirectory = file;
        this.mFileLockHelper = new FileLockHelper(new File(file, "file_lock"));
    }

    public synchronized boolean load(GatekeeperCache gatekeeperCache) {
        if (this.mIsLoaded) {
            return false;
        }
        this.mIsLoaded = true;
        if (!ensureDirectoryIsReady()) {
            return false;
        }
        if (!this.mFileLockHelper.lock()) {
            return false;
        }
        try {
            return loadLocked(gatekeeperCache);
        } finally {
            this.mFileLockHelper.unlock();
        }
    }

    public File getWorkingDirectory() {
        return this.mWorkingDirectory;
    }

    private boolean loadLocked(GatekeeperCache gatekeeperCache) {
        StateFileContent loadFromFile = this.mStateFileHelper.loadFromFile();
        if (loadFromFile == null) {
            return false;
        }
        if (!loadFromFile.hashMatches(getCurrentHash())) {
            NamesFileContent loadFromFile2 = this.mNamesFileHelper.loadFromFile();
            if (loadFromFile2 == null) {
                return false;
            }
            if (!loadFromFile.gatekeeperNamesHash.equals(loadFromFile2.gatekeeperNamesHash)) {
                BLog.e(TAG, "The hash of gatekeeper names in files doesn't match: %s and %s", loadFromFile.gatekeeperNamesHash, loadFromFile2.gatekeeperNamesHash);
                return false;
            } else if (!areNumbersOfGatekeepersEqual(loadFromFile.gatekeeperStates.length, loadFromFile2.gatekeeperNames.size())) {
                return false;
            } else {
                initFromOldState(gatekeeperCache, loadFromFile2.gatekeeperNames, loadFromFile.gatekeeperStates);
            }
        } else if (!areNumbersOfGatekeepersEqual(loadFromFile.gatekeeperStates.length, this.mStoreConfig.getNumberOfGatekeepers())) {
            return false;
        } else {
            initFromCurrentState(gatekeeperCache, loadFromFile.gatekeeperStates);
        }
        this.mSavedHash = loadFromFile.gatekeeperNamesHash;
        return true;
    }

    private boolean areNumbersOfGatekeepersEqual(int i, int i2) {
        if (i == i2) {
            return true;
        }
        BLog.e(TAG, "The number of gatekeepers in files doesn't match: %s and %s", Integer.valueOf(i), Integer.valueOf(i2));
        return false;
    }

    private void initFromCurrentState(GatekeeperCache gatekeeperCache, byte[] bArr) {
        for (int i = 0; i < this.mStoreConfig.getNumberOfGatekeepers(); i++) {
            setGatekeeperInCache(gatekeeperCache, i, bArr[i]);
        }
    }

    private void initFromOldState(final GatekeeperCache gatekeeperCache, ArrayList<String> arrayList, byte[] bArr) {
        GatekeeperIndices gatekeeperIndices = new GatekeeperIndices(this.mStoreConfig);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Integer num = gatekeeperIndices.get(arrayList.get(i));
            if (num != null) {
                setGatekeeperInCache(gatekeeperCache, num.intValue(), bArr[i]);
            }
        }
        getUpgradeExecutor().execute(new Runnable() {
            /* class com.facebook.gk.store.GatekeeperRepository.AnonymousClass1 */

            public void run() {
                GatekeeperRepository.this.saveIfNeeded(gatekeeperCache);
            }
        });
    }

    private static void setGatekeeperInCache(GatekeeperCache gatekeeperCache, int i, byte b) {
        if (RawGatekeeperStateConverter.isGatekeeperInitialized(b)) {
            gatekeeperCache.set(i, RawGatekeeperStateConverter.isGatekeeperSet(b));
        } else {
            gatekeeperCache.unset(i);
        }
        if (RawGatekeeperStateConverter.isGatekeeperOverridden(b)) {
            gatekeeperCache.setOverride(i, RawGatekeeperStateConverter.isGatekeeperOverriddenSet(b));
        } else {
            gatekeeperCache.clearOverride(i);
        }
    }

    /* access modifiers changed from: package-private */
    public Executor getUpgradeExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    public synchronized void save(GatekeeperCache gatekeeperCache) {
        if (ensureDirectoryIsReady()) {
            BLog.d(TAG, "Saving state. Saved hash: %s, current hash: %s", this.mSavedHash, getCurrentHash());
            if (this.mFileLockHelper.lock()) {
                try {
                    saveLocked(gatekeeperCache);
                } finally {
                    this.mFileLockHelper.unlock();
                }
            }
        }
    }

    private void saveLocked(GatekeeperCache gatekeeperCache) {
        StateFileContent loadFromFile;
        if (!this.mIsLoaded && (loadFromFile = this.mStateFileHelper.loadFromFile()) != null) {
            this.mSavedHash = loadFromFile.gatekeeperNamesHash;
        }
        if (needUpdate()) {
            BLog.d(TAG, "GKs are outdated, saving GKs names");
            if (this.mNamesFileHelper.saveToFile(new NamesFileContent(this.mStoreConfig.getGatekeeperNamesHash(), this.mStoreConfig.getGatekeeperNames()))) {
                this.mSavedHash = getCurrentHash();
            } else {
                return;
            }
        }
        BLog.d(TAG, "Saving GKs state");
        this.mStateFileHelper.saveToFile(new StateFileContent(getCurrentHash(), RawGatekeeperStateConverter.toRawStates(gatekeeperCache)));
        this.mIsLoaded = true;
    }

    public synchronized boolean needUpdate() {
        return !TextUtils.equals(getCurrentHash(), this.mSavedHash);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void saveIfNeeded(GatekeeperCache gatekeeperCache) {
        if (needUpdate()) {
            save(gatekeeperCache);
        }
    }

    private boolean ensureDirectoryIsReady() {
        if (this.mWorkingDirectory.exists() || this.mWorkingDirectory.mkdirs()) {
            return true;
        }
        BLog.w(TAG, "Cannot create working directory: %s", this.mWorkingDirectory.getAbsoluteFile());
        return false;
    }

    private synchronized String getCurrentHash() {
        if (this.mCurrentHash == null) {
            this.mCurrentHash = this.mStoreConfig.getGatekeeperNamesHash();
        }
        return this.mCurrentHash;
    }

    /* access modifiers changed from: private */
    public static class FileLockHelper {
        private final File mFile;
        private RandomAccessFile mFileHandle;
        private FileLock mLock;

        public FileLockHelper(File file) {
            this.mFile = file;
        }

        public boolean lock() {
            RandomAccessFile createLockFile = createLockFile(this.mFile);
            if (createLockFile == null) {
                return false;
            }
            FileLock lockFile = lockFile(this.mFile, createLockFile);
            if (lockFile == null) {
                closeLockFile(this.mFile, createLockFile);
                return false;
            }
            this.mFileHandle = createLockFile;
            this.mLock = lockFile;
            return true;
        }

        public void unlock() {
            FileLock fileLock = this.mLock;
            RandomAccessFile randomAccessFile = this.mFileHandle;
            this.mLock = null;
            this.mFileHandle = null;
            try {
                fileLock.release();
            } catch (IOException e) {
                BLog.w(GatekeeperRepository.TAG, e, "Cannot release a lock to file %s", this.mFile);
            }
            closeLockFile(this.mFile, randomAccessFile);
        }

        @Nullable
        private static RandomAccessFile createLockFile(File file) {
            try {
                return new RandomAccessFile(file, "rw");
            } catch (IOException e) {
                BLog.e(GatekeeperRepository.TAG, e, "Cannot create file %s", file);
                return null;
            }
        }

        @Nullable
        private static FileLock lockFile(File file, RandomAccessFile randomAccessFile) {
            try {
                return randomAccessFile.getChannel().lock();
            } catch (IOException e) {
                BLog.e(GatekeeperRepository.TAG, e, "Cannot acquire a lock to file %s", file);
                closeLockFile(file, randomAccessFile);
                return null;
            }
        }

        private static void closeLockFile(File file, RandomAccessFile randomAccessFile) {
            try {
                randomAccessFile.close();
            } catch (IOException e) {
                BLog.w(GatekeeperRepository.TAG, e, "Cannot close file %s", file);
            }
        }
    }
}
