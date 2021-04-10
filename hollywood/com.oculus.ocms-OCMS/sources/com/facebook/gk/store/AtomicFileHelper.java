package com.facebook.gk.store;

import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import java.io.File;
import java.io.IOException;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
class AtomicFileHelper<T> {
    private static final String TAG = "AtomicFileHelper";
    private final FileSerializer<T> mFileSerializer;
    private final File mOldFile;
    private final File mRegularFile;
    private final File mTemporaryFile;

    public interface FileSerializer<T> {
        T read(File file) throws IOException;

        void write(File file, T t) throws IOException;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected AtomicFileHelper(com.facebook.gk.store.AtomicFileHelper.FileSerializer<T> r5, java.io.File r6, java.lang.String r7) {
        /*
            r4 = this;
            java.io.File r0 = new java.io.File
            r0.<init>(r6, r7)
            java.io.File r1 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r7)
            java.lang.String r3 = ".tmp"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r6, r2)
            java.io.File r2 = new java.io.File
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r7)
            java.lang.String r7 = ".old"
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            r2.<init>(r6, r7)
            r4.<init>(r5, r0, r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.gk.store.AtomicFileHelper.<init>(com.facebook.gk.store.AtomicFileHelper$FileSerializer, java.io.File, java.lang.String):void");
    }

    protected AtomicFileHelper(FileSerializer<T> fileSerializer, File file, File file2, File file3) {
        this.mFileSerializer = fileSerializer;
        this.mRegularFile = file;
        this.mTemporaryFile = file2;
        this.mOldFile = file3;
    }

    public boolean saveToFile(T t) {
        try {
            this.mFileSerializer.write(this.mTemporaryFile, t);
            if (this.mOldFile.exists() && !this.mOldFile.delete()) {
                this.mTemporaryFile.delete();
                return false;
            } else if (this.mRegularFile.exists() && !this.mRegularFile.renameTo(this.mOldFile)) {
                this.mTemporaryFile.delete();
                return false;
            } else if (this.mTemporaryFile.renameTo(this.mRegularFile)) {
                return true;
            } else {
                this.mTemporaryFile.delete();
                this.mOldFile.renameTo(this.mRegularFile);
                return false;
            }
        } catch (IOException e) {
            BLog.w(TAG, "Cannot write data to file", e);
            this.mTemporaryFile.delete();
            return false;
        }
    }

    @Nullable
    public T loadFromFile() {
        if (this.mRegularFile.exists()) {
            try {
                return this.mFileSerializer.read(this.mRegularFile);
            } catch (IOException e) {
                BLog.w(TAG, "Cannot read file", e);
            }
        }
        if (!this.mOldFile.exists()) {
            return null;
        }
        try {
            return this.mFileSerializer.read(this.mOldFile);
        } catch (IOException e2) {
            BLog.w(TAG, "Cannot read file", e2);
            return null;
        }
    }
}
