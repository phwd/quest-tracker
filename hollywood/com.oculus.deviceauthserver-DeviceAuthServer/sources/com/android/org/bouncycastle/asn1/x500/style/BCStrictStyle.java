package com.android.org.bouncycastle.asn1.x500.style;

import com.android.org.bouncycastle.asn1.x500.RDN;
import com.android.org.bouncycastle.asn1.x500.X500Name;
import com.android.org.bouncycastle.asn1.x500.X500NameStyle;

public class BCStrictStyle extends BCStyle {
    public static final X500NameStyle INSTANCE = new BCStrictStyle();

    @Override // com.android.org.bouncycastle.asn1.x500.style.AbstractX500NameStyle, com.android.org.bouncycastle.asn1.x500.X500NameStyle
    public boolean areEqual(X500Name name1, X500Name name2) {
        RDN[] rdns1 = name1.getRDNs();
        RDN[] rdns2 = name2.getRDNs();
        if (rdns1.length != rdns2.length) {
            return false;
        }
        for (int i = 0; i != rdns1.length; i++) {
            if (!rdnAreEqual(rdns1[i], rdns2[i])) {
                return false;
            }
        }
        return true;
    }
}
