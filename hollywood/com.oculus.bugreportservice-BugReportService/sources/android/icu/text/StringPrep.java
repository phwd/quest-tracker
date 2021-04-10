package android.icu.text;

import android.icu.impl.CharTrie;
import android.icu.impl.ICUBinary;
import android.icu.impl.StringPrepDataReader;
import android.icu.impl.UBiDiProps;
import android.icu.lang.UCharacter;
import android.icu.util.ICUUncheckedIOException;
import android.icu.util.VersionInfo;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;

public final class StringPrep {
    private static final WeakReference[] CACHE = new WeakReference[14];
    private static final String[] PROFILE_NAMES = {"rfc3491", "rfc3530cs", "rfc3530csci", "rfc3491", "rfc3530mixp", "rfc3491", "rfc3722", "rfc3920node", "rfc3920res", "rfc4011", "rfc4013", "rfc4505", "rfc4518", "rfc4518ci"};
    private UBiDiProps bdp;
    private boolean checkBiDi;
    private boolean doNFKC;
    private int[] indexes;
    private char[] mappingData;
    private VersionInfo normCorrVer;
    private CharTrie sprepTrie;
    private VersionInfo sprepUniVer;

    private static VersionInfo getVersionInfo(int i) {
        int i2 = i & 255;
        int i3 = (i >> 16) & 255;
        return VersionInfo.getInstance((i >> 24) & 255, i3, (i >> 8) & 255, i2);
    }

    private static VersionInfo getVersionInfo(byte[] bArr) {
        if (bArr.length != 4) {
            return null;
        }
        return VersionInfo.getInstance(bArr[0], bArr[1], bArr[2], bArr[3]);
    }

    private StringPrep(ByteBuffer byteBuffer) {
        StringPrepDataReader stringPrepDataReader = new StringPrepDataReader(byteBuffer);
        this.indexes = stringPrepDataReader.readIndexes(16);
        this.sprepTrie = new CharTrie(byteBuffer, null);
        this.mappingData = stringPrepDataReader.read(this.indexes[1] / 2);
        boolean z = false;
        this.doNFKC = (this.indexes[7] & 1) > 0;
        this.checkBiDi = (this.indexes[7] & 2) > 0 ? true : z;
        this.sprepUniVer = getVersionInfo(stringPrepDataReader.getUnicodeVersion());
        this.normCorrVer = getVersionInfo(this.indexes[2]);
        VersionInfo unicodeVersion = UCharacter.getUnicodeVersion();
        if (unicodeVersion.compareTo(this.sprepUniVer) < 0 && unicodeVersion.compareTo(this.normCorrVer) < 0 && (this.indexes[7] & 1) > 0) {
            throw new IOException("Normalization Correction version not supported");
        } else if (this.checkBiDi) {
            this.bdp = UBiDiProps.INSTANCE;
        }
    }

    public static StringPrep getInstance(int i) {
        if (i < 0 || i > 13) {
            throw new IllegalArgumentException("Bad profile type");
        }
        StringPrep stringPrep = null;
        synchronized (CACHE) {
            WeakReference weakReference = CACHE[i];
            if (weakReference != null) {
                stringPrep = (StringPrep) weakReference.get();
            }
            if (stringPrep == null) {
                ByteBuffer requiredData = ICUBinary.getRequiredData(PROFILE_NAMES[i] + ".spp");
                if (requiredData != null) {
                    try {
                        stringPrep = new StringPrep(requiredData);
                    } catch (IOException e) {
                        throw new ICUUncheckedIOException(e);
                    }
                }
                if (stringPrep != null) {
                    CACHE[i] = new WeakReference(stringPrep);
                }
            }
        }
        return stringPrep;
    }
}
