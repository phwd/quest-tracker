package com.android.org.bouncycastle.x509.extension;

import com.android.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.android.org.bouncycastle.asn1.ASN1OctetString;
import com.android.org.bouncycastle.asn1.ASN1Primitive;
import com.android.org.bouncycastle.asn1.ASN1String;
import com.android.org.bouncycastle.asn1.DEROctetString;
import com.android.org.bouncycastle.asn1.DERSequence;
import com.android.org.bouncycastle.asn1.x500.X500Name;
import com.android.org.bouncycastle.asn1.x509.Extension;
import com.android.org.bouncycastle.asn1.x509.GeneralName;
import com.android.org.bouncycastle.util.Integers;
import java.io.IOException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;

public class X509ExtensionUtil {
    public static ASN1Primitive fromExtensionValue(byte[] encodedValue) throws IOException {
        return ASN1Primitive.fromByteArray(((ASN1OctetString) ASN1Primitive.fromByteArray(encodedValue)).getOctets());
    }

    public static Collection getIssuerAlternativeNames(X509Certificate cert) throws CertificateParsingException {
        return getAlternativeNames(cert.getExtensionValue(Extension.issuerAlternativeName.getId()));
    }

    public static Collection getSubjectAlternativeNames(X509Certificate cert) throws CertificateParsingException {
        return getAlternativeNames(cert.getExtensionValue(Extension.subjectAlternativeName.getId()));
    }

    private static Collection getAlternativeNames(byte[] extVal) throws CertificateParsingException {
        if (extVal == null) {
            return Collections.EMPTY_LIST;
        }
        try {
            Collection temp = new ArrayList();
            Enumeration it = DERSequence.getInstance(fromExtensionValue(extVal)).getObjects();
            while (it.hasMoreElements()) {
                GeneralName genName = GeneralName.getInstance(it.nextElement());
                ArrayList arrayList = new ArrayList();
                arrayList.add(Integers.valueOf(genName.getTagNo()));
                switch (genName.getTagNo()) {
                    case 0:
                    case 3:
                    case 5:
                        arrayList.add(genName.getName().toASN1Primitive());
                        break;
                    case 1:
                    case 2:
                    case 6:
                        arrayList.add(((ASN1String) genName.getName()).getString());
                        break;
                    case 4:
                        arrayList.add(X500Name.getInstance(genName.getName()).toString());
                        break;
                    case 7:
                        arrayList.add(DEROctetString.getInstance(genName.getName()).getOctets());
                        break;
                    case 8:
                        arrayList.add(ASN1ObjectIdentifier.getInstance(genName.getName()).getId());
                        break;
                    default:
                        throw new IOException("Bad tag number: " + genName.getTagNo());
                }
                temp.add(arrayList);
            }
            return Collections.unmodifiableCollection(temp);
        } catch (Exception e) {
            throw new CertificateParsingException(e.getMessage());
        }
    }
}
