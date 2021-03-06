package com.android.org.bouncycastle.asn1.misc;

import com.android.org.bouncycastle.asn1.DERIA5String;

public class NetscapeRevocationURL extends DERIA5String {
    public NetscapeRevocationURL(DERIA5String str) {
        super(str.getString());
    }

    @Override // com.android.org.bouncycastle.asn1.DERIA5String
    public String toString() {
        return "NetscapeRevocationURL: " + getString();
    }
}
