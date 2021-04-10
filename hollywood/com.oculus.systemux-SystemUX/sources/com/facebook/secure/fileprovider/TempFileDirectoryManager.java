package com.facebook.secure.fileprovider;

import com.facebook.secure.logger.LocalReporter;
import com.facebook.secure.logger.Reporter;
import java.io.File;
import java.io.IOException;
import javax.annotation.Nullable;

public class TempFileDirectoryManager {
    private static final String TAG = "TempFileDirectoryManager";
    private final Reporter mReporter;
    private final File mTempDir;

    public TempFileDirectoryManager(File file) throws IOException {
        this(file.getCanonicalFile(), new LocalReporter());
    }

    public TempFileDirectoryManager(File file, Reporter reporter) throws IOException {
        this.mTempDir = file.getCanonicalFile();
        this.mReporter = reporter;
    }

    public File getManagedDirectory() throws IOException {
        if (this.mTempDir.exists()) {
            if (!this.mTempDir.isDirectory()) {
                throw new IOException("Temporary folder is not a directory.");
            } else if (!this.mTempDir.canRead()) {
                throw new IOException("No read permissions for temporary directory.");
            } else if (!this.mTempDir.canWrite()) {
                throw new IOException("No write permissions for temporary directory.");
            }
        } else if (!this.mTempDir.mkdirs()) {
            this.mReporter.report(TAG, String.format("Could not create temporary directory. %s", this.mTempDir.getCanonicalPath()), null);
        }
        return this.mTempDir;
    }

    public File getNewTemporaryFile(String str, @Nullable String str2) throws IOException {
        if (str2 != null && !str2.startsWith(".")) {
            str2 = "." + str2;
        }
        return File.createTempFile(str, str2, getManagedDirectory());
    }

    @Nullable
    public File getNewTemporaryFileCopy(File file, @Nullable String str) throws IOException {
        String baseName = FileUtil.getBaseName(file);
        if (str == null) {
            str = FileUtil.getExtension(file);
        }
        File newTemporaryFile = getNewTemporaryFile(baseName, str);
        FileUtil.copy(file, newTemporaryFile);
        return newTemporaryFile;
    }

    public boolean deleteTemporaryFile(File file) throws IOException {
        if (containsTempFile(file)) {
            return file.delete();
        }
        this.mReporter.report(TAG, String.format("Attempt to delete file %s that is out of scope.", file.getAbsolutePath()), null);
        return false;
    }

    public boolean containsTempFile(File file) throws IOException {
        File managedDirectory = getManagedDirectory();
        return managedDirectory.exists() && file != null && file.exists() && file.getParentFile().getCanonicalPath().equals(managedDirectory.getCanonicalPath());
    }
}
