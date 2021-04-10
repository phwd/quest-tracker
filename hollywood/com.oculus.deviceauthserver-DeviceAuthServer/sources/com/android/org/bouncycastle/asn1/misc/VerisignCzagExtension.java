package com.android.org.bouncycastle.asn1.misc;

import com.android.org.bouncycastle.asn1.DERIA5String;

public class VerisignCzagExtension extends DERIA5String {
    public VerisignCzagExtension(DERIA5String str) {
        super(str.getString());
    }

    @Override // com.android.org.bouncycastle.asn1.DERIA5String
    public String toString() {
        return "VerisignCzagExtension: " + getString();
    }
}
