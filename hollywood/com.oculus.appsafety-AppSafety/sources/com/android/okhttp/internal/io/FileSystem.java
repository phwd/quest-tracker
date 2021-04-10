package com.android.okhttp.internal.io;

import com.android.okhttp.okio.Okio;
import com.android.okhttp.okio.Sink;
import com.android.okhttp.okio.Source;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileSystem {
    public static final FileSystem SYSTEM = new FileSystem() {
        /* class com.android.okhttp.internal.io.FileSystem.AnonymousClass1 */

        @Override // com.android.okhttp.internal.io.FileSystem
        public Source source(File file) throws FileNotFoundException {
            return Okio.source(file);
        }

        @Override // com.android.okhttp.internal.io.FileSystem
        public Sink sink(File file) throws FileNotFoundException {
            try {
                return Okio.sink(file);
            } catch (FileNotFoundException e) {
                file.getParentFile().mkdirs();
                return Okio.sink(file);
            }
        }

        @Override // com.android.okhttp.internal.io.FileSystem
        public Sink appendingSink(File file) throws FileNotFoundException {
            try {
                return Okio.appendingSink(file);
            } catch (FileNotFoundException e) {
                file.getParentFile().mkdirs();
                return Okio.appendingSink(file);
            }
        }

        @Override // com.android.okhttp.internal.io.FileSystem
        public void delete(File file) throws IOException {
            if (!file.delete() && file.exists()) {
                throw new IOException("failed to delete " + ((Object) file));
            }
        }

        @Override // com.android.okhttp.internal.io.FileSystem
        public boolean exists(File file) throws IOException {
            return file.exists();
        }

        @Override // com.android.okhttp.internal.io.FileSystem
        public long size(File file) {
            return file.length();
        }

        @Override // com.android.okhttp.internal.io.FileSystem
        public void rename(File from, File to) throws IOException {
            delete(to);
            if (!from.renameTo(to)) {
                throw new IOException("failed to rename " + ((Object) from) + " to " + ((Object) to));
            }
        }

        @Override // com.android.okhttp.internal.io.FileSystem
        public void deleteContents(File directory) throws IOException {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteContents(file);
                    }
                    if (!file.delete()) {
                        throw new IOException("failed to delete " + ((Object) file));
                    }
                }
                return;
            }
            throw new IOException("not a readable directory: " + ((Object) directory));
        }
    };

    Sink appendingSink(File file) throws FileNotFoundException;

    void delete(File file) throws IOException;

    void deleteContents(File file) throws IOException;

    boolean exists(File file) throws IOException;

    void rename(File file, File file2) throws IOException;

    Sink sink(File file) throws FileNotFoundException;

    long size(File file);

    Source source(File file) throws FileNotFoundException;
}
