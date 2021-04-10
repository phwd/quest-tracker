package com.squareup.okhttp.internal.io;

import X.AbstractC04550h0;
import X.AnonymousClass0OS;
import X.AnonymousClass0OT;
import X.AnonymousClass0h1;
import X.C04540gz;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public interface FileSystem {
    public static final FileSystem SYSTEM = new FileSystem() {
        /* class com.squareup.okhttp.internal.io.FileSystem.AnonymousClass1 */

        @Override // com.squareup.okhttp.internal.io.FileSystem
        public AnonymousClass0h1 appendingSink(File file) throws FileNotFoundException {
            if (file != null) {
                try {
                    return new AnonymousClass0OT(new C04540gz(), new FileOutputStream(file, true));
                } catch (FileNotFoundException unused) {
                    file.getParentFile().mkdirs();
                    return new AnonymousClass0OT(new C04540gz(), new FileOutputStream(file, true));
                }
            } else {
                throw new IllegalArgumentException("file == null");
            }
        }

        @Override // com.squareup.okhttp.internal.io.FileSystem
        public AnonymousClass0h1 sink(File file) throws FileNotFoundException {
            if (file != null) {
                try {
                    return new AnonymousClass0OT(new C04540gz(), new FileOutputStream(file));
                } catch (FileNotFoundException unused) {
                    file.getParentFile().mkdirs();
                    return new AnonymousClass0OT(new C04540gz(), new FileOutputStream(file));
                }
            } else {
                throw new IllegalArgumentException("file == null");
            }
        }

        @Override // com.squareup.okhttp.internal.io.FileSystem
        public AbstractC04550h0 source(File file) throws FileNotFoundException {
            if (file != null) {
                return new AnonymousClass0OS(new C04540gz(), new FileInputStream(file));
            }
            throw new IllegalArgumentException("file == null");
        }

        @Override // com.squareup.okhttp.internal.io.FileSystem
        public void delete(File file) throws IOException {
            if (!file.delete() && file.exists()) {
                throw new IOException("failed to delete " + file);
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
                throw new IOException("failed to rename " + file + " to " + file2);
            }
        }

        @Override // com.squareup.okhttp.internal.io.FileSystem
        public long size(File file) {
            return file.length();
        }
    };

    AnonymousClass0h1 appendingSink(File file) throws FileNotFoundException;

    void delete(File file) throws IOException;

    void deleteContents(File file) throws IOException;

    boolean exists(File file) throws IOException;

    void rename(File file, File file2) throws IOException;

    AnonymousClass0h1 sink(File file) throws FileNotFoundException;

    long size(File file);

    AbstractC04550h0 source(File file) throws FileNotFoundException;
}
