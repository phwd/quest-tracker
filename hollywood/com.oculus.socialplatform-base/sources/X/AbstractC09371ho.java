package X;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.SparseArray;
import androidx.annotation.VisibleForTesting;
import com.facebook.infer.annotation.Nullsafe;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1ho  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC09371ho {
    public static final int EMOJI_EXPRESSION = 65039;
    public static final float EMOJI_SCALE = 1.1675f;
    public static final int FNV_OFFSET_BASIS = -2128831035;
    public static final int FNV_PRIME = 16777619;
    public static final AnonymousClass0WB<byte[]> sByteArrayPool = new AnonymousClass0WB<>(2);
    public final SparseArray<SoftReference<Bitmap>> mBitmapCache = new SparseArray<>();
    public final AtomicReference<AnonymousClass1hv> mDataRef = new AtomicReference<>();
    public volatile boolean mLoadedData;

    @Nullable
    public abstract File getFontFile();

    @Nullable
    private AnonymousClass1hv getData(File file) {
        AnonymousClass1hv r4 = this.mDataRef.get();
        if (!this.mLoadedData || !(r4 == null || r4.A00 == file)) {
            AnonymousClass1hv r3 = null;
            if (file != null) {
                try {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
                    try {
                        C09361hn r11 = new C09361hn(randomAccessFile);
                        r11.A02(4);
                        ByteBuffer byteBuffer = r11.A01;
                        byteBuffer.position(0);
                        C09361hn.A00(r11, 2);
                        int i = byteBuffer.getShort() & 65535;
                        if (i <= 100) {
                            r11.A02(6);
                            int i2 = 0;
                            while (true) {
                                if (i2 >= i) {
                                    break;
                                }
                                byteBuffer.position(0);
                                C09361hn.A00(r11, 4);
                                int i3 = byteBuffer.getInt();
                                r11.A02(4);
                                long A01 = r11.A01();
                                r11.A02(4);
                                if (1835365473 != i3) {
                                    i2++;
                                } else if (A01 != -1) {
                                    r11.A02((int) (A01 - r11.A00));
                                    r11.A02(12);
                                    long A012 = r11.A01();
                                    for (int i4 = 0; ((long) i4) < A012; i4++) {
                                        byteBuffer.position(0);
                                        C09361hn.A00(r11, 4);
                                        int i5 = byteBuffer.getInt();
                                        long A013 = r11.A01();
                                        long A014 = r11.A01();
                                        if (1114599275 == i5) {
                                            long j = ((A013 + A01) << 32) | A014;
                                            long j2 = j >> 32;
                                            byte[] bArr = new byte[((int) j)];
                                            randomAccessFile.seek(j2);
                                            randomAccessFile.read(bArr);
                                            AnonymousClass1hv r0 = new AnonymousClass1hv(file, bArr);
                                            randomAccessFile.close();
                                            r3 = r0;
                                        }
                                    }
                                    throw new IOException("Cannot find meta subtable.");
                                }
                            }
                            throw new IOException("Cannot find meta table.");
                        }
                        throw new IOException(AnonymousClass006.A04("Font tables are probably malformed! (", i, ")"));
                    } catch (Throwable unused) {
                    }
                } catch (IOException e) {
                    AnonymousClass0MD.A01(AnonymousClass1i2.class, "Unable to read tables", e);
                }
            }
            this.mDataRef.compareAndSet(r4, r3);
            this.mLoadedData = true;
        }
        return this.mDataRef.get();
        throw th;
    }

    @VisibleForTesting
    public static int fnv1aHash(CharSequence charSequence, int i, int i2) {
        int i3 = FNV_OFFSET_BASIS;
        while (i < i2) {
            int codePointAt = Character.codePointAt(charSequence, i);
            i += Character.charCount(codePointAt);
            if (codePointAt != 65039) {
                i3 = (i3 ^ codePointAt) * FNV_PRIME;
            }
        }
        return i3;
    }

    @Nullable
    public final Object getEmojiSpan(Resources resources, CharSequence charSequence, int i, int i2, int i3) {
        Bitmap bitmap = getBitmap(charSequence, i, i2);
        if (bitmap == null) {
            return null;
        }
        BitmapDrawable bitmapDrawable = new BitmapDrawable(resources, bitmap);
        float f = ((float) i3) * 1.1675f;
        bitmapDrawable.setBounds(0, 0, (int) (((17.0f * f) / 16.0f) + 0.5f), (int) (f + 0.5f));
        return new C09471i4(bitmapDrawable);
    }

    @Nullable
    public final Bitmap getBitmap(CharSequence charSequence, int i, int i2) {
        AnonymousClass1hv data;
        SoftReference<Bitmap> softReference;
        Bitmap bitmap;
        File fontFile = getFontFile();
        if (!(fontFile == null || (data = getData(fontFile)) == null)) {
            int fnv1aHash = fnv1aHash(charSequence, i, i2);
            synchronized (this.mBitmapCache) {
                softReference = this.mBitmapCache.get(fnv1aHash);
            }
            if (softReference != null && (bitmap = softReference.get()) != null) {
                return bitmap;
            }
            int binarySearch = Arrays.binarySearch(data.A01, fnv1aHash);
            if (binarySearch >= 0) {
                int[] iArr = data.A02;
                int i3 = binarySearch << 1;
                int i4 = iArr[i3];
                int i5 = iArr[i3 + 1];
                try {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(data.A00, "r");
                    try {
                        randomAccessFile.seek((long) i4);
                        byte[] A19 = sByteArrayPool.A19();
                        if (A19 == null || A19.length < i5) {
                            A19 = new byte[i5];
                        }
                        randomAccessFile.read(A19, 0, i5);
                        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(A19, 0, i5);
                        sByteArrayPool.A8z(A19);
                        SoftReference<Bitmap> softReference2 = new SoftReference<>(decodeByteArray);
                        synchronized (this.mBitmapCache) {
                            this.mBitmapCache.put(fnv1aHash, softReference2);
                        }
                        randomAccessFile.close();
                        return decodeByteArray;
                    } catch (Throwable unused) {
                    }
                } catch (IOException e) {
                    AnonymousClass0MD.A01(AbstractC09371ho.class, "Unable to create bitmap", e);
                    return null;
                }
            }
        }
        return null;
        throw th;
    }

    @Nullable
    public final Bitmap getBitmap(String str) {
        return getBitmap(str, 0, str.length());
    }
}
