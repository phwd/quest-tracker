package com.facebook.soloader;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileLock;

public final class f implements Closeable {
    private final FileOutputStream a;
    private final FileLock b;

    private f(File file) {
        this.a = new FileOutputStream(file);
        try {
            FileLock lock = this.a.getChannel().lock();
            if (lock == null) {
            }
            this.b = lock;
        } finally {
            this.a.close();
        }
    }

    public static f a(File file) {
        return new f(file);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        try {
            if (this.b != null) {
                this.b.release();
            }
        } finally {
            this.a.close();
        }
    }
}
