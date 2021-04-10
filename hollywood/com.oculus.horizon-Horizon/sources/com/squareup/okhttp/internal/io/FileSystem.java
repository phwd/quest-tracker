package com.squareup.okhttp.internal.io;

import X.AbstractC07630v6;
import X.AbstractC07640v7;
import X.AnonymousClass0Lo;
import X.AnonymousClass0Lp;
import X.C07620v5;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public interface FileSystem {
    public static final FileSystem SYSTEM = new FileSystem() {
        /* class com.squareup.okhttp.internal.io.FileSystem.AnonymousClass1 */

        @Override // com.squareup.okhttp.internal.io.FileSystem
        public AbstractC07640v7 appendingSink(File file) throws FileNotFoundException {
            if (file != null) {
                try {
                    return new AnonymousClass0Lp(new C07620v5(), new FileOutputStream(file, true));
                } catch (FileNotFoundException unused) {
                    file.getParentFile().mkdirs();
                    return new AnonymousClass0Lp(new C07620v5(), new FileOutputStream(file, true));
                }
            } else {
                throw new IllegalArgumentException("file == null");
            }
        }

        @Override // com.squareup.okhttp.internal.io.FileSystem
        public AbstractC07640v7 sink(File file) throws FileNotFoundException {
            if (file != null) {
                try {
                    return new AnonymousClass0Lp(new C07620v5(), new FileOutputStream(file));
                } catch (FileNotFoundException unused) {
                    file.getParentFile().mkdirs();
                    return new AnonymousClass0Lp(new C07620v5(), new FileOutputStream(file));
                }
            } else {
                throw new IllegalArgumentException("file == null");
            }
        }

        @Override // com.squareup.okhttp.internal.io.FileSystem
        public AbstractC07630v6 source(File file) throws FileNotFoundException {
            if (file != null) {
                return new AnonymousClass0Lo(new C07620v5(), new FileInputStream(file));
            }
            throw new IllegalArgumentException("file == null");
        }

        @Override // com.squareup.okhttp.internal.io.FileSystem
        public void delete(File file) throws IOException {
            if (!file.delete() && file.exists()) {
                StringBuilder sb = new StringBuilder("failed to delete ");
                sb.append(file);
                throw new IOException(sb.toString());
            }
        }

        @Override // com.squareup.okhttp.internal.io.FileSystem
        public void deleteContents(File file) throws IOException {
            StringBuilder sb;
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.isDirectory()) {
                        deleteContents(file2);
                    }
                    if (!file2.delete()) {
                        sb = new StringBuilder("failed to delete ");
                        sb.append(file2);
                    }
                }
                return;
            }
            sb = new StringBuilder("not a readable directory: ");
            sb.append(file);
            throw new IOException(sb.toString());
        }

        @Override // com.squareup.okhttp.internal.io.FileSystem
        public boolean exists(File file) throws IOException {
            return file.exists();
        }

        @Override // com.squareup.okhttp.internal.io.FileSystem
        public void rename(File file, File file2) throws IOException {
            delete(file2);
            if (!file.renameTo(file2)) {
                StringBuilder sb = new StringBuilder("failed to rename ");
                sb.append(file);
                sb.append(" to ");
                sb.append(file2);
                throw new IOException(sb.toString());
            }
        }

        @Override // com.squareup.okhttp.internal.io.FileSystem
        public long size(File file) {
            return file.length();
        }
    };

    AbstractC07640v7 appendingSink(File file) throws FileNotFoundException;

    void delete(File file) throws IOException;

    void deleteContents(File file) throws IOException;

    boolean exists(File file) throws IOException;

    void rename(File file, File file2) throws IOException;

    AbstractC07640v7 sink(File file) throws FileNotFoundException;

    long size(File file);

    AbstractC07630v6 source(File file) throws FileNotFoundException;
}
