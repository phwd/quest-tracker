package X;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.locks.Lock;

/* renamed from: X.0cE  reason: invalid class name and case insensitive filesystem */
public final class C03400cE implements AnonymousClass0GV {
    @Nullable
    public AnonymousClass0FB A00;
    public boolean A01;
    public final int A02;
    @NonNull
    public final Context A03;
    @NonNull
    public final AnonymousClass0GV A04;
    @Nullable
    public final File A05;
    @Nullable
    public final String A06;

    @Override // X.AnonymousClass0GV, java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        this.A04.close();
        this.A01 = false;
    }

    /* JADX INFO: finally extract failed */
    private void A00(File file) throws IOException {
        ReadableByteChannel channel;
        String A09;
        String str = this.A06;
        if (str != null) {
            channel = Channels.newChannel(this.A03.getAssets().open(str));
        } else {
            File file2 = this.A05;
            if (file2 != null) {
                channel = new FileInputStream(file2).getChannel();
            } else {
                throw new IllegalStateException("copyFromAssetPath and copyFromFile == null!");
            }
        }
        File createTempFile = File.createTempFile("room-copy-helper", ".tmp", this.A03.getCacheDir());
        createTempFile.deleteOnExit();
        FileChannel channel2 = new FileOutputStream(createTempFile).getChannel();
        try {
            channel2.transferFrom(channel, 0, Long.MAX_VALUE);
            channel2.force(false);
            channel.close();
            channel2.close();
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                A09 = AnonymousClass006.A05("Failed to create directories for ", file.getAbsolutePath());
            } else if (!createTempFile.renameTo(file)) {
                A09 = AnonymousClass006.A09("Failed to move intermediate file (", createTempFile.getAbsolutePath(), ") to destination (", file.getAbsolutePath(), ").");
            } else {
                return;
            }
            throw new IOException(A09);
        } catch (Throwable th) {
            channel.close();
            channel2.close();
            throw th;
        }
    }

    @Override // X.AnonymousClass0GV
    public final String A3K() {
        return this.A04.A3K();
    }

    @Override // X.AnonymousClass0GV
    public final synchronized AnonymousClass0GQ A4y() {
        if (!this.A01) {
            String A3K = A3K();
            Context context = this.A03;
            File databasePath = context.getDatabasePath(A3K);
            boolean z = true;
            if (this.A00 != null) {
                z = false;
            }
            AnonymousClass0G6 r3 = new AnonymousClass0G6(A3K, context.getFilesDir(), z);
            try {
                Lock lock = r3.A02;
                lock.lock();
                if (r3.A03) {
                    try {
                        FileChannel channel = new FileOutputStream(r3.A01).getChannel();
                        r3.A00 = channel;
                        channel.lock();
                    } catch (IOException e) {
                        throw new IllegalStateException("Unable to grab copy lock.", e);
                    }
                }
                if (!databasePath.exists()) {
                    try {
                        A00(databasePath);
                    } catch (IOException e2) {
                        throw new RuntimeException("Unable to copy database file.", e2);
                    }
                } else if (this.A00 != null) {
                    ByteBuffer allocate = ByteBuffer.allocate(4);
                    FileChannel channel2 = new FileInputStream(databasePath).getChannel();
                    try {
                        channel2.tryLock(60, 4, true);
                        channel2.position(60L);
                        if (channel2.read(allocate) == 4) {
                            allocate.rewind();
                            int i = allocate.getInt();
                            try {
                                channel2.close();
                                if (i != this.A02 && !this.A00.A07) {
                                    if (context.deleteDatabase(A3K)) {
                                        try {
                                            A00(databasePath);
                                        } catch (IOException e3) {
                                            Log.w("ROOM", "Unable to copy database file.", e3);
                                        }
                                    } else {
                                        Log.w("ROOM", AnonymousClass006.A07("Failed to delete database file (", A3K, ") for a copy destructive migration."));
                                    }
                                }
                            } catch (IOException e4) {
                                Log.w("ROOM", "Unable to read database version.", e4);
                            }
                        } else {
                            throw new IOException("Bad database header, unable to read 4 bytes at offset 60");
                        }
                    } catch (Throwable th) {
                        if (channel2 != null) {
                            channel2.close();
                            throw th;
                        }
                        throw th;
                    }
                }
                FileChannel fileChannel = r3.A00;
                if (fileChannel != null) {
                    try {
                        fileChannel.close();
                    } catch (IOException unused) {
                    }
                }
                lock.unlock();
                this.A01 = true;
            } catch (Throwable th2) {
                FileChannel fileChannel2 = r3.A00;
                if (fileChannel2 != null) {
                    try {
                        fileChannel2.close();
                    } catch (IOException unused2) {
                    }
                }
                r3.A02.unlock();
                throw th2;
            }
        }
        return this.A04.A4y();
    }

    @Override // X.AnonymousClass0GV
    @RequiresApi(api = 16)
    public final void A8H(boolean z) {
        this.A04.A8H(z);
    }
}
