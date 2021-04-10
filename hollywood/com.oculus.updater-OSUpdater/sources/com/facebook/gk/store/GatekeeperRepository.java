package com.facebook.gk.store;

import java.io.File;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class GatekeeperRepository {
    private static final String TAG = "GatekeeperRepository";
    private final FileLockHelper mFileLockHelper;
    private final AtomicFileHelper<Object> mNamesFileHelper;
    private final AtomicFileHelper<Object> mStateFileHelper;
    private final GatekeeperStoreConfig mStoreConfig;
    private final File mWorkingDirectory;

    public GatekeeperRepository(GatekeeperStoreConfig gatekeeperStoreConfig, File file) {
        this(gatekeeperStoreConfig, new AtomicFileHelper(new StateFileSerializer(), file, "gk_state"), new AtomicFileHelper(new NamesFileSerializer(), file, "gk_names"), file);
    }

    GatekeeperRepository(GatekeeperStoreConfig gatekeeperStoreConfig, AtomicFileHelper<Object> atomicFileHelper, AtomicFileHelper<Object> atomicFileHelper2, File file) {
        this.mStoreConfig = gatekeeperStoreConfig;
        this.mStateFileHelper = atomicFileHelper;
        this.mNamesFileHelper = atomicFileHelper2;
        this.mWorkingDirectory = file;
        this.mFileLockHelper = new FileLockHelper(new File(file, "file_lock"));
    }

    private static class FileLockHelper {
        private final File mFile;

        public FileLockHelper(File file) {
            this.mFile = file;
        }
    }
}
