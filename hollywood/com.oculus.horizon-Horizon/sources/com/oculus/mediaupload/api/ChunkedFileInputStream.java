package com.oculus.mediaupload.api;

import X.AnonymousClass0NO;
import com.facebook.common.stringformat.StringFormatUtil;
import com.google.common.base.Preconditions;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import javax.annotation.Nullable;

public class ChunkedFileInputStream {
    public static final String MP4_MIME = "video/mp4";
    public static final String TAG = "ChunkedFileInputStream";
    public int mChunkSize = 2000000;
    public boolean mClosed = false;
    public FileInputStream mFileInputStream;
    public String mFileName;
    public long mFileSize;
    public long mOffset = 0;

    @Nullable
    public final TypedByteArrayWithFilename A00() throws IOException {
        String str;
        FileInputStream fileInputStream = this.mFileInputStream;
        if (fileInputStream != null) {
            byte[] bArr = new byte[this.mChunkSize];
            int read = fileInputStream.read(bArr);
            if (read <= 0) {
                return null;
            }
            long[] jArr = {(long) this.mChunkSize, this.mFileSize - this.mOffset};
            Preconditions.checkArgument(true);
            long j = jArr[0];
            long j2 = jArr[1];
            if (j2 < j) {
                j = j2;
            }
            int i = (int) j;
            if (read == i) {
                if (read < bArr.length) {
                    bArr = Arrays.copyOf(bArr, read);
                }
                this.mOffset += (long) read;
                return new TypedByteArrayWithFilename(bArr, this.mFileName);
            }
            str = StringFormatUtil.formatStrLocaleSafe("expected %s bytes, got %s", Integer.valueOf(read), Integer.valueOf(i));
        } else {
            str = "File input stream not set";
        }
        throw new IOException(str);
    }

    public final void A01() {
        FileInputStream fileInputStream = this.mFileInputStream;
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                AnonymousClass0NO.A0B(TAG, "could not close the input stream", e);
            }
            this.mClosed = true;
        }
    }
}
