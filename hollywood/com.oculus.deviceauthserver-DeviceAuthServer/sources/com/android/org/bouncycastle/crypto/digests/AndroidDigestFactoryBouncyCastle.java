package com.android.org.bouncycastle.crypto.digests;

import com.android.org.bouncycastle.crypto.Digest;

public class AndroidDigestFactoryBouncyCastle implements AndroidDigestFactoryInterface {
    @Override // com.android.org.bouncycastle.crypto.digests.AndroidDigestFactoryInterface
    public Digest getMD5() {
        return new MD5Digest();
    }

    @Override // com.android.org.bouncycastle.crypto.digests.AndroidDigestFactoryInterface
    public Digest getSHA1() {
        return new SHA1Digest();
    }

    @Override // com.android.org.bouncycastle.crypto.digests.AndroidDigestFactoryInterface
    public Digest getSHA224() {
        return new SHA224Digest();
    }

    @Override // com.android.org.bouncycastle.crypto.digests.AndroidDigestFactoryInterface
    public Digest getSHA256() {
        return new SHA256Digest();
    }

    @Override // com.android.org.bouncycastle.crypto.digests.AndroidDigestFactoryInterface
    public Digest getSHA384() {
        return new SHA384Digest();
    }

    @Override // com.android.org.bouncycastle.crypto.digests.AndroidDigestFactoryInterface
    public Digest getSHA512() {
        return new SHA512Digest();
    }
}
