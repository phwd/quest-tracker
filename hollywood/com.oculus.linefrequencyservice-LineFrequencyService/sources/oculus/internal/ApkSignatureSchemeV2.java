package oculus.internal;

import android.util.apk.ApkSignatureSchemeV2Verifier;
import android.util.apk.ApkSigningBlockUtils;
import android.util.apk.SignatureNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import oculus.internal.AbstractApkSignatureSchemeV2;

public final class ApkSignatureSchemeV2 extends AbstractApkSignatureSchemeV2 {
    @Override // oculus.internal.AbstractApkSignatureSchemeV2
    public ByteBuffer getSignatureBlock(RandomAccessFile apk) throws IOException, AbstractApkSignatureSchemeV2.NoSignatureFoundException {
        try {
            return ApkSignatureSchemeV2Verifier.findSignature(apk).getSignatureBlock();
        } catch (SignatureNotFoundException e) {
            throw new AbstractApkSignatureSchemeV2.NoSignatureFoundException(e.getMessage(), e);
        }
    }

    @Override // oculus.internal.AbstractApkSignatureSchemeV2
    public ByteBuffer getLengthPrefixedSlice(ByteBuffer block) throws IOException {
        return ApkSigningBlockUtils.getLengthPrefixedSlice(block);
    }

    @Override // oculus.internal.AbstractApkSignatureSchemeV2
    public byte[] readLengthPrefixedByteArray(ByteBuffer digest) throws IOException {
        return ApkSigningBlockUtils.readLengthPrefixedByteArray(digest);
    }
}
