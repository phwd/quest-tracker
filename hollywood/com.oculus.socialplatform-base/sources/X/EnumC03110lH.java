package X;

import com.facebook.xzdecoder.XzInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.0lH  reason: invalid class name and case insensitive filesystem */
public enum EnumC03110lH {
    XZ("assets/lib/libs.xzs", "lib-xzs", ".xzs"),
    ZSTD("assets/lib/libs.zstd", "lib-zstd", ".zstd"),
    SUPERPACK_XZ("assets/lib/libs.spk.xz", "lib-superpack-xz", ".spk.xz"),
    SUPERPACK_ZSTD("assets/lib/libs.spk.zst", "lib-superpack-zstd", ".spk.zst"),
    SUPERPACK_BR("assets/lib/libs.spk.br", "lib-superpack-br", ".spk.br");
    
    public final String mAssetPath;
    public final String mExtension;
    public final String mOutputDirectoryName;

    /* access modifiers changed from: public */
    EnumC03110lH(String str, String str2, String str3) {
        this.mAssetPath = str;
        this.mOutputDirectoryName = str2;
        this.mExtension = str3;
    }

    public String getAssetPath() {
        return this.mAssetPath;
    }

    public InputStream getCompressedInputStream(InputStream inputStream) throws IOException {
        String str;
        switch (ordinal()) {
            case 0:
                return new XzInputStream(inputStream);
            case 1:
                try {
                    return (InputStream) Class.forName("com.facebook.zstd.ZstdInputStream").getConstructor(InputStream.class).newInstance(inputStream);
                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }
            case 2:
            case 3:
            case 4:
                if (this == SUPERPACK_XZ) {
                    str = "xz";
                } else if (this == SUPERPACK_BR) {
                    str = "br";
                } else {
                    str = "zst";
                }
                try {
                    return (InputStream) Class.forName("com.facebook.superpack.SuperpackFileInputStream").getMethod("createFromSingletonArchiveInputStream", InputStream.class, String.class).invoke(null, inputStream, str);
                } catch (Exception e2) {
                    throw new RuntimeException("Could not access Superpack archive", e2);
                }
            default:
                throw new IllegalStateException("Unknown compression algorithm");
        }
    }

    public String getExtension() {
        return this.mExtension;
    }

    public String getOutputDirectoryName() {
        return this.mOutputDirectoryName;
    }
}
