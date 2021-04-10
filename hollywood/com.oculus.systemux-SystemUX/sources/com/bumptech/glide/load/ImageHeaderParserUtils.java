package com.bumptech.glide.load;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.data.ParcelFileDescriptorRewinder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

public final class ImageHeaderParserUtils {
    private static final int MARK_READ_LIMIT = 5242880;

    /* access modifiers changed from: private */
    public interface OrientationReader {
        int getOrientation(ImageHeaderParser imageHeaderParser) throws IOException;
    }

    /* access modifiers changed from: private */
    public interface TypeReader {
        ImageHeaderParser.ImageType getType(ImageHeaderParser imageHeaderParser) throws IOException;
    }

    private ImageHeaderParserUtils() {
    }

    @NonNull
    public static ImageHeaderParser.ImageType getType(@NonNull List<ImageHeaderParser> list, @Nullable final InputStream inputStream, @NonNull ArrayPool arrayPool) throws IOException {
        if (inputStream == null) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        if (!inputStream.markSupported()) {
            inputStream = new RecyclableBufferedInputStream(inputStream, arrayPool);
        }
        inputStream.mark(MARK_READ_LIMIT);
        return getTypeInternal(list, new TypeReader() {
            /* class com.bumptech.glide.load.ImageHeaderParserUtils.AnonymousClass1 */

            @Override // com.bumptech.glide.load.ImageHeaderParserUtils.TypeReader
            public ImageHeaderParser.ImageType getType(ImageHeaderParser imageHeaderParser) throws IOException {
                try {
                    return imageHeaderParser.getType(inputStream);
                } finally {
                    inputStream.reset();
                }
            }
        });
    }

    @NonNull
    public static ImageHeaderParser.ImageType getType(@NonNull List<ImageHeaderParser> list, @Nullable final ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer == null) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        return getTypeInternal(list, new TypeReader() {
            /* class com.bumptech.glide.load.ImageHeaderParserUtils.AnonymousClass2 */

            @Override // com.bumptech.glide.load.ImageHeaderParserUtils.TypeReader
            public ImageHeaderParser.ImageType getType(ImageHeaderParser imageHeaderParser) throws IOException {
                return imageHeaderParser.getType(byteBuffer);
            }
        });
    }

    @NonNull
    @RequiresApi(21)
    public static ImageHeaderParser.ImageType getType(@NonNull List<ImageHeaderParser> list, @NonNull final ParcelFileDescriptorRewinder parcelFileDescriptorRewinder, @NonNull final ArrayPool arrayPool) throws IOException {
        return getTypeInternal(list, new TypeReader() {
            /* class com.bumptech.glide.load.ImageHeaderParserUtils.AnonymousClass3 */

            /* JADX WARNING: Removed duplicated region for block: B:14:0x002a A[SYNTHETIC, Splitter:B:14:0x002a] */
            @Override // com.bumptech.glide.load.ImageHeaderParserUtils.TypeReader
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.bumptech.glide.load.ImageHeaderParser.ImageType getType(com.bumptech.glide.load.ImageHeaderParser r5) throws java.io.IOException {
                /*
                    r4 = this;
                    r0 = 0
                    com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream r1 = new com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream     // Catch:{ all -> 0x0027 }
                    java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x0027 }
                    com.bumptech.glide.load.data.ParcelFileDescriptorRewinder r3 = com.bumptech.glide.load.data.ParcelFileDescriptorRewinder.this     // Catch:{ all -> 0x0027 }
                    android.os.ParcelFileDescriptor r3 = r3.rewindAndGet()     // Catch:{ all -> 0x0027 }
                    java.io.FileDescriptor r3 = r3.getFileDescriptor()     // Catch:{ all -> 0x0027 }
                    r2.<init>(r3)     // Catch:{ all -> 0x0027 }
                    com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r3 = r3     // Catch:{ all -> 0x0027 }
                    r1.<init>(r2, r3)     // Catch:{ all -> 0x0027 }
                    com.bumptech.glide.load.ImageHeaderParser$ImageType r5 = r5.getType(r1)     // Catch:{ all -> 0x0024 }
                    r1.close()     // Catch:{ IOException -> 0x001e }
                L_0x001e:
                    com.bumptech.glide.load.data.ParcelFileDescriptorRewinder r0 = com.bumptech.glide.load.data.ParcelFileDescriptorRewinder.this
                    r0.rewindAndGet()
                    return r5
                L_0x0024:
                    r5 = move-exception
                    r0 = r1
                    goto L_0x0028
                L_0x0027:
                    r5 = move-exception
                L_0x0028:
                    if (r0 == 0) goto L_0x002d
                    r0.close()     // Catch:{ IOException -> 0x002d }
                L_0x002d:
                    com.bumptech.glide.load.data.ParcelFileDescriptorRewinder r0 = com.bumptech.glide.load.data.ParcelFileDescriptorRewinder.this
                    r0.rewindAndGet()
                    throw r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.ImageHeaderParserUtils.AnonymousClass3.getType(com.bumptech.glide.load.ImageHeaderParser):com.bumptech.glide.load.ImageHeaderParser$ImageType");
            }
        });
    }

    @NonNull
    private static ImageHeaderParser.ImageType getTypeInternal(@NonNull List<ImageHeaderParser> list, TypeReader typeReader) throws IOException {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ImageHeaderParser.ImageType type = typeReader.getType(list.get(i));
            if (type != ImageHeaderParser.ImageType.UNKNOWN) {
                return type;
            }
        }
        return ImageHeaderParser.ImageType.UNKNOWN;
    }

    public static int getOrientation(@NonNull List<ImageHeaderParser> list, @Nullable final InputStream inputStream, @NonNull final ArrayPool arrayPool) throws IOException {
        if (inputStream == null) {
            return -1;
        }
        if (!inputStream.markSupported()) {
            inputStream = new RecyclableBufferedInputStream(inputStream, arrayPool);
        }
        inputStream.mark(MARK_READ_LIMIT);
        return getOrientationInternal(list, new OrientationReader() {
            /* class com.bumptech.glide.load.ImageHeaderParserUtils.AnonymousClass4 */

            @Override // com.bumptech.glide.load.ImageHeaderParserUtils.OrientationReader
            public int getOrientation(ImageHeaderParser imageHeaderParser) throws IOException {
                try {
                    return imageHeaderParser.getOrientation(inputStream, arrayPool);
                } finally {
                    inputStream.reset();
                }
            }
        });
    }

    @RequiresApi(21)
    public static int getOrientation(@NonNull List<ImageHeaderParser> list, @NonNull final ParcelFileDescriptorRewinder parcelFileDescriptorRewinder, @NonNull final ArrayPool arrayPool) throws IOException {
        return getOrientationInternal(list, new OrientationReader() {
            /* class com.bumptech.glide.load.ImageHeaderParserUtils.AnonymousClass5 */

            /* JADX WARNING: Removed duplicated region for block: B:14:0x002c A[SYNTHETIC, Splitter:B:14:0x002c] */
            @Override // com.bumptech.glide.load.ImageHeaderParserUtils.OrientationReader
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public int getOrientation(com.bumptech.glide.load.ImageHeaderParser r5) throws java.io.IOException {
                /*
                    r4 = this;
                    r0 = 0
                    com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream r1 = new com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream     // Catch:{ all -> 0x0029 }
                    java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x0029 }
                    com.bumptech.glide.load.data.ParcelFileDescriptorRewinder r3 = com.bumptech.glide.load.data.ParcelFileDescriptorRewinder.this     // Catch:{ all -> 0x0029 }
                    android.os.ParcelFileDescriptor r3 = r3.rewindAndGet()     // Catch:{ all -> 0x0029 }
                    java.io.FileDescriptor r3 = r3.getFileDescriptor()     // Catch:{ all -> 0x0029 }
                    r2.<init>(r3)     // Catch:{ all -> 0x0029 }
                    com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r3 = r3     // Catch:{ all -> 0x0029 }
                    r1.<init>(r2, r3)     // Catch:{ all -> 0x0029 }
                    com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r0 = r3     // Catch:{ all -> 0x0026 }
                    int r5 = r5.getOrientation(r1, r0)     // Catch:{ all -> 0x0026 }
                    r1.close()     // Catch:{ IOException -> 0x0020 }
                L_0x0020:
                    com.bumptech.glide.load.data.ParcelFileDescriptorRewinder r0 = com.bumptech.glide.load.data.ParcelFileDescriptorRewinder.this
                    r0.rewindAndGet()
                    return r5
                L_0x0026:
                    r5 = move-exception
                    r0 = r1
                    goto L_0x002a
                L_0x0029:
                    r5 = move-exception
                L_0x002a:
                    if (r0 == 0) goto L_0x002f
                    r0.close()     // Catch:{ IOException -> 0x002f }
                L_0x002f:
                    com.bumptech.glide.load.data.ParcelFileDescriptorRewinder r0 = com.bumptech.glide.load.data.ParcelFileDescriptorRewinder.this
                    r0.rewindAndGet()
                    throw r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.ImageHeaderParserUtils.AnonymousClass5.getOrientation(com.bumptech.glide.load.ImageHeaderParser):int");
            }
        });
    }

    private static int getOrientationInternal(@NonNull List<ImageHeaderParser> list, OrientationReader orientationReader) throws IOException {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int orientation = orientationReader.getOrientation(list.get(i));
            if (orientation != -1) {
                return orientation;
            }
        }
        return -1;
    }
}
