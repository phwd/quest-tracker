package com.android.org.bouncycastle.asn1.x509;

import com.android.org.bouncycastle.asn1.ASN1OctetString;
import com.android.org.bouncycastle.asn1.ASN1Sequence;
import com.android.org.bouncycastle.asn1.DERIA5String;
import com.android.org.bouncycastle.asn1.x500.X500Name;
import com.android.org.bouncycastle.util.Arrays;
import com.android.org.bouncycastle.util.Integers;
import com.android.org.bouncycastle.util.Strings;
import com.android.org.bouncycastle.util.encoders.Hex;
import com.google.common.primitives.UnsignedBytes;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PKIXNameConstraintValidator implements NameConstraintValidator {
    private Set excludedSubtreesDN = new HashSet();
    private Set excludedSubtreesDNS = new HashSet();
    private Set excludedSubtreesEmail = new HashSet();
    private Set excludedSubtreesIP = new HashSet();
    private Set excludedSubtreesOtherName = new HashSet();
    private Set excludedSubtreesURI = new HashSet();
    private Set permittedSubtreesDN;
    private Set permittedSubtreesDNS;
    private Set permittedSubtreesEmail;
    private Set permittedSubtreesIP;
    private Set permittedSubtreesOtherName;
    private Set permittedSubtreesURI;

    @Override // com.android.org.bouncycastle.asn1.x509.NameConstraintValidator
    public void checkPermitted(GeneralName name) throws NameConstraintValidatorException {
        int tagNo = name.getTagNo();
        if (tagNo == 0) {
            checkPermittedOtherName(this.permittedSubtreesOtherName, OtherName.getInstance(name.getName()));
        } else if (tagNo == 1) {
            checkPermittedEmail(this.permittedSubtreesEmail, extractNameAsString(name));
        } else if (tagNo == 2) {
            checkPermittedDNS(this.permittedSubtreesDNS, DERIA5String.getInstance(name.getName()).getString());
        } else if (tagNo == 4) {
            checkPermittedDN(X500Name.getInstance(name.getName()));
        } else if (tagNo == 6) {
            checkPermittedURI(this.permittedSubtreesURI, DERIA5String.getInstance(name.getName()).getString());
        } else if (tagNo == 7) {
            checkPermittedIP(this.permittedSubtreesIP, ASN1OctetString.getInstance(name.getName()).getOctets());
        } else {
            throw new IllegalStateException("Unknown tag encountered: " + name.getTagNo());
        }
    }

    @Override // com.android.org.bouncycastle.asn1.x509.NameConstraintValidator
    public void checkExcluded(GeneralName name) throws NameConstraintValidatorException {
        int tagNo = name.getTagNo();
        if (tagNo == 0) {
            checkExcludedOtherName(this.excludedSubtreesOtherName, OtherName.getInstance(name.getName()));
        } else if (tagNo == 1) {
            checkExcludedEmail(this.excludedSubtreesEmail, extractNameAsString(name));
        } else if (tagNo == 2) {
            checkExcludedDNS(this.excludedSubtreesDNS, DERIA5String.getInstance(name.getName()).getString());
        } else if (tagNo == 4) {
            checkExcludedDN(X500Name.getInstance(name.getName()));
        } else if (tagNo == 6) {
            checkExcludedURI(this.excludedSubtreesURI, DERIA5String.getInstance(name.getName()).getString());
        } else if (tagNo == 7) {
            checkExcludedIP(this.excludedSubtreesIP, ASN1OctetString.getInstance(name.getName()).getOctets());
        } else {
            throw new IllegalStateException("Unknown tag encountered: " + name.getTagNo());
        }
    }

    @Override // com.android.org.bouncycastle.asn1.x509.NameConstraintValidator
    public void intersectPermittedSubtree(GeneralSubtree permitted) {
        intersectPermittedSubtree(new GeneralSubtree[]{permitted});
    }

    @Override // com.android.org.bouncycastle.asn1.x509.NameConstraintValidator
    public void intersectPermittedSubtree(GeneralSubtree[] permitted) {
        Map subtreesMap = new HashMap();
        for (int i = 0; i != permitted.length; i++) {
            GeneralSubtree subtree = permitted[i];
            Integer tagNo = Integers.valueOf(subtree.getBase().getTagNo());
            if (subtreesMap.get(tagNo) == null) {
                subtreesMap.put(tagNo, new HashSet());
            }
            ((Set) subtreesMap.get(tagNo)).add(subtree);
        }
        for (Map.Entry entry : subtreesMap.entrySet()) {
            int nameType = ((Integer) entry.getKey()).intValue();
            if (nameType == 0) {
                this.permittedSubtreesOtherName = intersectOtherName(this.permittedSubtreesOtherName, (Set) entry.getValue());
            } else if (nameType == 1) {
                this.permittedSubtreesEmail = intersectEmail(this.permittedSubtreesEmail, (Set) entry.getValue());
            } else if (nameType == 2) {
                this.permittedSubtreesDNS = intersectDNS(this.permittedSubtreesDNS, (Set) entry.getValue());
            } else if (nameType == 4) {
                this.permittedSubtreesDN = intersectDN(this.permittedSubtreesDN, (Set) entry.getValue());
            } else if (nameType == 6) {
                this.permittedSubtreesURI = intersectURI(this.permittedSubtreesURI, (Set) entry.getValue());
            } else if (nameType == 7) {
                this.permittedSubtreesIP = intersectIP(this.permittedSubtreesIP, (Set) entry.getValue());
            } else {
                throw new IllegalStateException("Unknown tag encountered: " + nameType);
            }
        }
    }

    @Override // com.android.org.bouncycastle.asn1.x509.NameConstraintValidator
    public void intersectEmptyPermittedSubtree(int nameType) {
        if (nameType == 0) {
            this.permittedSubtreesOtherName = new HashSet();
        } else if (nameType == 1) {
            this.permittedSubtreesEmail = new HashSet();
        } else if (nameType == 2) {
            this.permittedSubtreesDNS = new HashSet();
        } else if (nameType == 4) {
            this.permittedSubtreesDN = new HashSet();
        } else if (nameType == 6) {
            this.permittedSubtreesURI = new HashSet();
        } else if (nameType == 7) {
            this.permittedSubtreesIP = new HashSet();
        } else {
            throw new IllegalStateException("Unknown tag encountered: " + nameType);
        }
    }

    @Override // com.android.org.bouncycastle.asn1.x509.NameConstraintValidator
    public void addExcludedSubtree(GeneralSubtree subtree) {
        GeneralName base = subtree.getBase();
        int tagNo = base.getTagNo();
        if (tagNo == 0) {
            this.excludedSubtreesOtherName = unionOtherName(this.excludedSubtreesOtherName, OtherName.getInstance(base.getName()));
        } else if (tagNo == 1) {
            this.excludedSubtreesEmail = unionEmail(this.excludedSubtreesEmail, extractNameAsString(base));
        } else if (tagNo == 2) {
            this.excludedSubtreesDNS = unionDNS(this.excludedSubtreesDNS, extractNameAsString(base));
        } else if (tagNo == 4) {
            this.excludedSubtreesDN = unionDN(this.excludedSubtreesDN, (ASN1Sequence) base.getName().toASN1Primitive());
        } else if (tagNo == 6) {
            this.excludedSubtreesURI = unionURI(this.excludedSubtreesURI, extractNameAsString(base));
        } else if (tagNo == 7) {
            this.excludedSubtreesIP = unionIP(this.excludedSubtreesIP, ASN1OctetString.getInstance(base.getName()).getOctets());
        } else {
            throw new IllegalStateException("Unknown tag encountered: " + base.getTagNo());
        }
    }

    public int hashCode() {
        return hashCollection(this.excludedSubtreesDN) + hashCollection(this.excludedSubtreesDNS) + hashCollection(this.excludedSubtreesEmail) + hashCollection(this.excludedSubtreesIP) + hashCollection(this.excludedSubtreesURI) + hashCollection(this.excludedSubtreesOtherName) + hashCollection(this.permittedSubtreesDN) + hashCollection(this.permittedSubtreesDNS) + hashCollection(this.permittedSubtreesEmail) + hashCollection(this.permittedSubtreesIP) + hashCollection(this.permittedSubtreesURI) + hashCollection(this.permittedSubtreesOtherName);
    }

    public boolean equals(Object o) {
        if (!(o instanceof PKIXNameConstraintValidator)) {
            return false;
        }
        PKIXNameConstraintValidator constraintValidator = (PKIXNameConstraintValidator) o;
        if (!collectionsAreEqual(constraintValidator.excludedSubtreesDN, this.excludedSubtreesDN) || !collectionsAreEqual(constraintValidator.excludedSubtreesDNS, this.excludedSubtreesDNS) || !collectionsAreEqual(constraintValidator.excludedSubtreesEmail, this.excludedSubtreesEmail) || !collectionsAreEqual(constraintValidator.excludedSubtreesIP, this.excludedSubtreesIP) || !collectionsAreEqual(constraintValidator.excludedSubtreesURI, this.excludedSubtreesURI) || !collectionsAreEqual(constraintValidator.excludedSubtreesOtherName, this.excludedSubtreesOtherName) || !collectionsAreEqual(constraintValidator.permittedSubtreesDN, this.permittedSubtreesDN) || !collectionsAreEqual(constraintValidator.permittedSubtreesDNS, this.permittedSubtreesDNS) || !collectionsAreEqual(constraintValidator.permittedSubtreesEmail, this.permittedSubtreesEmail) || !collectionsAreEqual(constraintValidator.permittedSubtreesIP, this.permittedSubtreesIP) || !collectionsAreEqual(constraintValidator.permittedSubtreesURI, this.permittedSubtreesURI) || !collectionsAreEqual(constraintValidator.permittedSubtreesOtherName, this.permittedSubtreesOtherName)) {
            return false;
        }
        return true;
    }

    public String toString() {
        String temp = "" + "permitted:\n";
        if (this.permittedSubtreesDN != null) {
            temp = (temp + "DN:\n") + this.permittedSubtreesDN.toString() + "\n";
        }
        if (this.permittedSubtreesDNS != null) {
            temp = (temp + "DNS:\n") + this.permittedSubtreesDNS.toString() + "\n";
        }
        if (this.permittedSubtreesEmail != null) {
            temp = (temp + "Email:\n") + this.permittedSubtreesEmail.toString() + "\n";
        }
        if (this.permittedSubtreesURI != null) {
            temp = (temp + "URI:\n") + this.permittedSubtreesURI.toString() + "\n";
        }
        if (this.permittedSubtreesIP != null) {
            temp = (temp + "IP:\n") + stringifyIPCollection(this.permittedSubtreesIP) + "\n";
        }
        if (this.permittedSubtreesOtherName != null) {
            temp = (temp + "OtherName:\n") + stringifyOtherNameCollection(this.permittedSubtreesOtherName) + "\n";
        }
        String temp2 = temp + "excluded:\n";
        if (!this.excludedSubtreesDN.isEmpty()) {
            temp2 = (temp2 + "DN:\n") + this.excludedSubtreesDN.toString() + "\n";
        }
        if (!this.excludedSubtreesDNS.isEmpty()) {
            temp2 = (temp2 + "DNS:\n") + this.excludedSubtreesDNS.toString() + "\n";
        }
        if (!this.excludedSubtreesEmail.isEmpty()) {
            temp2 = (temp2 + "Email:\n") + this.excludedSubtreesEmail.toString() + "\n";
        }
        if (!this.excludedSubtreesURI.isEmpty()) {
            temp2 = (temp2 + "URI:\n") + this.excludedSubtreesURI.toString() + "\n";
        }
        if (!this.excludedSubtreesIP.isEmpty()) {
            temp2 = (temp2 + "IP:\n") + stringifyIPCollection(this.excludedSubtreesIP) + "\n";
        }
        if (this.excludedSubtreesOtherName.isEmpty()) {
            return temp2;
        }
        return (temp2 + "OtherName:\n") + stringifyOtherNameCollection(this.excludedSubtreesOtherName) + "\n";
    }

    private void checkPermittedDN(X500Name dns) throws NameConstraintValidatorException {
        checkPermittedDN(this.permittedSubtreesDN, ASN1Sequence.getInstance(dns.toASN1Primitive()));
    }

    private void checkExcludedDN(X500Name dns) throws NameConstraintValidatorException {
        checkExcludedDN(this.excludedSubtreesDN, ASN1Sequence.getInstance(dns));
    }

    private static boolean withinDNSubtree(ASN1Sequence dns, ASN1Sequence subtree) {
        if (subtree.size() < 1 || subtree.size() > dns.size()) {
            return false;
        }
        for (int j = subtree.size() - 1; j >= 0; j--) {
            if (!subtree.getObjectAt(j).equals(dns.getObjectAt(j))) {
                return false;
            }
        }
        return true;
    }

    private void checkPermittedDN(Set permitted, ASN1Sequence dns) throws NameConstraintValidatorException {
        if (permitted != null) {
            if (!permitted.isEmpty() || dns.size() != 0) {
                Iterator it = permitted.iterator();
                while (it.hasNext()) {
                    if (withinDNSubtree(dns, (ASN1Sequence) it.next())) {
                        return;
                    }
                }
                throw new NameConstraintValidatorException("Subject distinguished name is not from a permitted subtree");
            }
        }
    }

    private void checkExcludedDN(Set excluded, ASN1Sequence dns) throws NameConstraintValidatorException {
        if (!excluded.isEmpty()) {
            Iterator it = excluded.iterator();
            while (it.hasNext()) {
                if (withinDNSubtree(dns, (ASN1Sequence) it.next())) {
                    throw new NameConstraintValidatorException("Subject distinguished name is from an excluded subtree");
                }
            }
        }
    }

    private Set intersectDN(Set permitted, Set dns) {
        Set intersect = new HashSet();
        Iterator it = dns.iterator();
        while (it.hasNext()) {
            ASN1Sequence dn = ASN1Sequence.getInstance(((GeneralSubtree) it.next()).getBase().getName().toASN1Primitive());
            if (permitted != null) {
                Iterator _iter = permitted.iterator();
                while (_iter.hasNext()) {
                    ASN1Sequence subtree = (ASN1Sequence) _iter.next();
                    if (withinDNSubtree(dn, subtree)) {
                        intersect.add(dn);
                    } else if (withinDNSubtree(subtree, dn)) {
                        intersect.add(subtree);
                    }
                }
            } else if (dn != null) {
                intersect.add(dn);
            }
        }
        return intersect;
    }

    private Set unionDN(Set excluded, ASN1Sequence dn) {
        if (!excluded.isEmpty()) {
            Set intersect = new HashSet();
            Iterator it = excluded.iterator();
            while (it.hasNext()) {
                ASN1Sequence subtree = (ASN1Sequence) it.next();
                if (withinDNSubtree(dn, subtree)) {
                    intersect.add(subtree);
                } else if (withinDNSubtree(subtree, dn)) {
                    intersect.add(dn);
                } else {
                    intersect.add(subtree);
                    intersect.add(dn);
                }
            }
            return intersect;
        } else if (dn == null) {
            return excluded;
        } else {
            excluded.add(dn);
            return excluded;
        }
    }

    private Set intersectOtherName(Set permitted, Set otherNames) {
        Set intersect = new HashSet(permitted);
        intersect.retainAll(otherNames);
        return intersect;
    }

    private Set unionOtherName(Set permitted, OtherName otherName) {
        Set union = new HashSet(permitted);
        union.add(otherName);
        return union;
    }

    private Set intersectEmail(Set permitted, Set emails) {
        Set intersect = new HashSet();
        Iterator it = emails.iterator();
        while (it.hasNext()) {
            String email = extractNameAsString(((GeneralSubtree) it.next()).getBase());
            if (permitted != null) {
                Iterator it2 = permitted.iterator();
                while (it2.hasNext()) {
                    intersectEmail(email, (String) it2.next(), intersect);
                }
            } else if (email != null) {
                intersect.add(email);
            }
        }
        return intersect;
    }

    private Set unionEmail(Set excluded, String email) {
        if (!excluded.isEmpty()) {
            Set union = new HashSet();
            Iterator it = excluded.iterator();
            while (it.hasNext()) {
                unionEmail((String) it.next(), email, union);
            }
            return union;
        } else if (email == null) {
            return excluded;
        } else {
            excluded.add(email);
            return excluded;
        }
    }

    private Set intersectIP(Set permitted, Set ips) {
        Set intersect = new HashSet();
        Iterator it = ips.iterator();
        while (it.hasNext()) {
            byte[] ip = ASN1OctetString.getInstance(((GeneralSubtree) it.next()).getBase().getName()).getOctets();
            if (permitted != null) {
                Iterator it2 = permitted.iterator();
                while (it2.hasNext()) {
                    intersect.addAll(intersectIPRange((byte[]) it2.next(), ip));
                }
            } else if (ip != null) {
                intersect.add(ip);
            }
        }
        return intersect;
    }

    private Set unionIP(Set excluded, byte[] ip) {
        if (!excluded.isEmpty()) {
            Set union = new HashSet();
            Iterator it = excluded.iterator();
            while (it.hasNext()) {
                union.addAll(unionIPRange((byte[]) it.next(), ip));
            }
            return union;
        } else if (ip == null) {
            return excluded;
        } else {
            excluded.add(ip);
            return excluded;
        }
    }

    private Set unionIPRange(byte[] ipWithSubmask1, byte[] ipWithSubmask2) {
        Set set = new HashSet();
        if (Arrays.areEqual(ipWithSubmask1, ipWithSubmask2)) {
            set.add(ipWithSubmask1);
        } else {
            set.add(ipWithSubmask1);
            set.add(ipWithSubmask2);
        }
        return set;
    }

    private Set intersectIPRange(byte[] ipWithSubmask1, byte[] ipWithSubmask2) {
        if (ipWithSubmask1.length != ipWithSubmask2.length) {
            return Collections.EMPTY_SET;
        }
        byte[][] temp = extractIPsAndSubnetMasks(ipWithSubmask1, ipWithSubmask2);
        byte[] ip1 = temp[0];
        byte[] subnetmask1 = temp[1];
        byte[] ip2 = temp[2];
        byte[] subnetmask2 = temp[3];
        byte[][] minMax = minMaxIPs(ip1, subnetmask1, ip2, subnetmask2);
        if (compareTo(max(minMax[0], minMax[2]), min(minMax[1], minMax[3])) == 1) {
            return Collections.EMPTY_SET;
        }
        return Collections.singleton(ipWithSubnetMask(or(minMax[0], minMax[2]), or(subnetmask1, subnetmask2)));
    }

    private byte[] ipWithSubnetMask(byte[] ip, byte[] subnetMask) {
        int ipLength = ip.length;
        byte[] temp = new byte[(ipLength * 2)];
        System.arraycopy(ip, 0, temp, 0, ipLength);
        System.arraycopy(subnetMask, 0, temp, ipLength, ipLength);
        return temp;
    }

    private byte[][] extractIPsAndSubnetMasks(byte[] ipWithSubmask1, byte[] ipWithSubmask2) {
        int ipLength = ipWithSubmask1.length / 2;
        byte[] ip1 = new byte[ipLength];
        byte[] subnetmask1 = new byte[ipLength];
        System.arraycopy(ipWithSubmask1, 0, ip1, 0, ipLength);
        System.arraycopy(ipWithSubmask1, ipLength, subnetmask1, 0, ipLength);
        byte[] ip2 = new byte[ipLength];
        byte[] subnetmask2 = new byte[ipLength];
        System.arraycopy(ipWithSubmask2, 0, ip2, 0, ipLength);
        System.arraycopy(ipWithSubmask2, ipLength, subnetmask2, 0, ipLength);
        return new byte[][]{ip1, subnetmask1, ip2, subnetmask2};
    }

    private byte[][] minMaxIPs(byte[] ip1, byte[] subnetmask1, byte[] ip2, byte[] subnetmask2) {
        int ipLength = ip1.length;
        byte[] min1 = new byte[ipLength];
        byte[] max1 = new byte[ipLength];
        byte[] min2 = new byte[ipLength];
        byte[] max2 = new byte[ipLength];
        for (int i = 0; i < ipLength; i++) {
            min1[i] = (byte) (ip1[i] & subnetmask1[i]);
            max1[i] = (byte) ((ip1[i] & subnetmask1[i]) | (~subnetmask1[i]));
            min2[i] = (byte) (ip2[i] & subnetmask2[i]);
            max2[i] = (byte) ((ip2[i] & subnetmask2[i]) | (~subnetmask2[i]));
        }
        return new byte[][]{min1, max1, min2, max2};
    }

    private void checkPermittedEmail(Set permitted, String email) throws NameConstraintValidatorException {
        if (permitted != null) {
            Iterator it = permitted.iterator();
            while (it.hasNext()) {
                if (emailIsConstrained(email, (String) it.next())) {
                    return;
                }
            }
            if (email.length() != 0 || permitted.size() != 0) {
                throw new NameConstraintValidatorException("Subject email address is not from a permitted subtree.");
            }
        }
    }

    private void checkPermittedOtherName(Set permitted, OtherName name) throws NameConstraintValidatorException {
        if (permitted != null) {
            Iterator it = permitted.iterator();
            while (it.hasNext()) {
                if (otherNameIsConstrained(name, (OtherName) it.next())) {
                    return;
                }
            }
            throw new NameConstraintValidatorException("Subject OtherName is not from a permitted subtree.");
        }
    }

    private void checkExcludedOtherName(Set excluded, OtherName name) throws NameConstraintValidatorException {
        if (!excluded.isEmpty()) {
            for (Object obj : excluded) {
                if (otherNameIsConstrained(name, OtherName.getInstance(obj))) {
                    throw new NameConstraintValidatorException("OtherName is from an excluded subtree.");
                }
            }
        }
    }

    private void checkExcludedEmail(Set excluded, String email) throws NameConstraintValidatorException {
        if (!excluded.isEmpty()) {
            Iterator it = excluded.iterator();
            while (it.hasNext()) {
                if (emailIsConstrained(email, (String) it.next())) {
                    throw new NameConstraintValidatorException("Email address is from an excluded subtree.");
                }
            }
        }
    }

    private void checkPermittedIP(Set permitted, byte[] ip) throws NameConstraintValidatorException {
        if (permitted != null) {
            Iterator it = permitted.iterator();
            while (it.hasNext()) {
                if (isIPConstrained(ip, (byte[]) it.next())) {
                    return;
                }
            }
            if (ip.length != 0 || permitted.size() != 0) {
                throw new NameConstraintValidatorException("IP is not from a permitted subtree.");
            }
        }
    }

    private void checkExcludedIP(Set excluded, byte[] ip) throws NameConstraintValidatorException {
        if (!excluded.isEmpty()) {
            Iterator it = excluded.iterator();
            while (it.hasNext()) {
                if (isIPConstrained(ip, (byte[]) it.next())) {
                    throw new NameConstraintValidatorException("IP is from an excluded subtree.");
                }
            }
        }
    }

    private boolean isIPConstrained(byte[] ip, byte[] constraint) {
        int ipLength = ip.length;
        if (ipLength != constraint.length / 2) {
            return false;
        }
        byte[] subnetMask = new byte[ipLength];
        System.arraycopy(constraint, ipLength, subnetMask, 0, ipLength);
        byte[] permittedSubnetAddress = new byte[ipLength];
        byte[] ipSubnetAddress = new byte[ipLength];
        for (int i = 0; i < ipLength; i++) {
            permittedSubnetAddress[i] = (byte) (constraint[i] & subnetMask[i]);
            ipSubnetAddress[i] = (byte) (ip[i] & subnetMask[i]);
        }
        return Arrays.areEqual(permittedSubnetAddress, ipSubnetAddress);
    }

    private boolean otherNameIsConstrained(OtherName name, OtherName constraint) {
        if (constraint.equals(name)) {
            return true;
        }
        return false;
    }

    private boolean emailIsConstrained(String email, String constraint) {
        String sub = email.substring(email.indexOf(64) + 1);
        if (constraint.indexOf(64) != -1) {
            if (email.equalsIgnoreCase(constraint)) {
                return true;
            }
        } else if (constraint.charAt(0) != '.') {
            if (sub.equalsIgnoreCase(constraint)) {
                return true;
            }
        } else if (withinDomain(sub, constraint)) {
            return true;
        }
        return false;
    }

    private boolean withinDomain(String testDomain, String domain) {
        String tempDomain = domain;
        if (tempDomain.startsWith(".")) {
            tempDomain = tempDomain.substring(1);
        }
        String[] domainParts = Strings.split(tempDomain, '.');
        String[] testDomainParts = Strings.split(testDomain, '.');
        if (testDomainParts.length <= domainParts.length) {
            return false;
        }
        int d = testDomainParts.length - domainParts.length;
        for (int i = -1; i < domainParts.length; i++) {
            if (i == -1) {
                if (testDomainParts[i + d].equals("")) {
                    return false;
                }
            } else if (!domainParts[i].equalsIgnoreCase(testDomainParts[i + d])) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:5:0x000d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void checkPermittedDNS(java.util.Set r4, java.lang.String r5) throws com.android.org.bouncycastle.asn1.x509.NameConstraintValidatorException {
        /*
            r3 = this;
            if (r4 != 0) goto L_0x0003
            return
        L_0x0003:
            java.util.Iterator r0 = r4.iterator()
        L_0x0007:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0022
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            boolean r2 = r3.withinDomain(r5, r1)
            if (r2 != 0) goto L_0x0021
            boolean r2 = r5.equalsIgnoreCase(r1)
            if (r2 == 0) goto L_0x0020
            goto L_0x0021
        L_0x0020:
            goto L_0x0007
        L_0x0021:
            return
        L_0x0022:
            int r1 = r5.length()
            if (r1 != 0) goto L_0x002f
            int r1 = r4.size()
            if (r1 != 0) goto L_0x002f
            return
        L_0x002f:
            com.android.org.bouncycastle.asn1.x509.NameConstraintValidatorException r1 = new com.android.org.bouncycastle.asn1.x509.NameConstraintValidatorException
            java.lang.String r2 = "DNS is not from a permitted subtree."
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.org.bouncycastle.asn1.x509.PKIXNameConstraintValidator.checkPermittedDNS(java.util.Set, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0011  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void checkExcludedDNS(java.util.Set r5, java.lang.String r6) throws com.android.org.bouncycastle.asn1.x509.NameConstraintValidatorException {
        /*
            r4 = this;
            boolean r0 = r5.isEmpty()
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            java.util.Iterator r0 = r5.iterator()
        L_0x000b:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x002c
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            boolean r2 = r4.withinDomain(r6, r1)
            if (r2 != 0) goto L_0x0024
            boolean r2 = r6.equalsIgnoreCase(r1)
            if (r2 != 0) goto L_0x0024
            goto L_0x000b
        L_0x0024:
            com.android.org.bouncycastle.asn1.x509.NameConstraintValidatorException r2 = new com.android.org.bouncycastle.asn1.x509.NameConstraintValidatorException
            java.lang.String r3 = "DNS is from an excluded subtree."
            r2.<init>(r3)
            throw r2
        L_0x002c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.org.bouncycastle.asn1.x509.PKIXNameConstraintValidator.checkExcludedDNS(java.util.Set, java.lang.String):void");
    }

    private void unionEmail(String email1, String email2, Set union) {
        if (email1.indexOf(64) != -1) {
            String _sub = email1.substring(email1.indexOf(64) + 1);
            if (email2.indexOf(64) != -1) {
                if (email1.equalsIgnoreCase(email2)) {
                    union.add(email1);
                    return;
                }
                union.add(email1);
                union.add(email2);
            } else if (email2.startsWith(".")) {
                if (withinDomain(_sub, email2)) {
                    union.add(email2);
                    return;
                }
                union.add(email1);
                union.add(email2);
            } else if (_sub.equalsIgnoreCase(email2)) {
                union.add(email2);
            } else {
                union.add(email1);
                union.add(email2);
            }
        } else if (email1.startsWith(".")) {
            if (email2.indexOf(64) != -1) {
                if (withinDomain(email2.substring(email1.indexOf(64) + 1), email1)) {
                    union.add(email1);
                    return;
                }
                union.add(email1);
                union.add(email2);
            } else if (email2.startsWith(".")) {
                if (withinDomain(email1, email2) || email1.equalsIgnoreCase(email2)) {
                    union.add(email2);
                } else if (withinDomain(email2, email1)) {
                    union.add(email1);
                } else {
                    union.add(email1);
                    union.add(email2);
                }
            } else if (withinDomain(email2, email1)) {
                union.add(email1);
            } else {
                union.add(email1);
                union.add(email2);
            }
        } else if (email2.indexOf(64) != -1) {
            if (email2.substring(email1.indexOf(64) + 1).equalsIgnoreCase(email1)) {
                union.add(email1);
                return;
            }
            union.add(email1);
            union.add(email2);
        } else if (email2.startsWith(".")) {
            if (withinDomain(email1, email2)) {
                union.add(email2);
                return;
            }
            union.add(email1);
            union.add(email2);
        } else if (email1.equalsIgnoreCase(email2)) {
            union.add(email1);
        } else {
            union.add(email1);
            union.add(email2);
        }
    }

    private void unionURI(String email1, String email2, Set union) {
        if (email1.indexOf(64) != -1) {
            String _sub = email1.substring(email1.indexOf(64) + 1);
            if (email2.indexOf(64) != -1) {
                if (email1.equalsIgnoreCase(email2)) {
                    union.add(email1);
                    return;
                }
                union.add(email1);
                union.add(email2);
            } else if (email2.startsWith(".")) {
                if (withinDomain(_sub, email2)) {
                    union.add(email2);
                    return;
                }
                union.add(email1);
                union.add(email2);
            } else if (_sub.equalsIgnoreCase(email2)) {
                union.add(email2);
            } else {
                union.add(email1);
                union.add(email2);
            }
        } else if (email1.startsWith(".")) {
            if (email2.indexOf(64) != -1) {
                if (withinDomain(email2.substring(email1.indexOf(64) + 1), email1)) {
                    union.add(email1);
                    return;
                }
                union.add(email1);
                union.add(email2);
            } else if (email2.startsWith(".")) {
                if (withinDomain(email1, email2) || email1.equalsIgnoreCase(email2)) {
                    union.add(email2);
                } else if (withinDomain(email2, email1)) {
                    union.add(email1);
                } else {
                    union.add(email1);
                    union.add(email2);
                }
            } else if (withinDomain(email2, email1)) {
                union.add(email1);
            } else {
                union.add(email1);
                union.add(email2);
            }
        } else if (email2.indexOf(64) != -1) {
            if (email2.substring(email1.indexOf(64) + 1).equalsIgnoreCase(email1)) {
                union.add(email1);
                return;
            }
            union.add(email1);
            union.add(email2);
        } else if (email2.startsWith(".")) {
            if (withinDomain(email1, email2)) {
                union.add(email2);
                return;
            }
            union.add(email1);
            union.add(email2);
        } else if (email1.equalsIgnoreCase(email2)) {
            union.add(email1);
        } else {
            union.add(email1);
            union.add(email2);
        }
    }

    private Set intersectDNS(Set permitted, Set dnss) {
        Set intersect = new HashSet();
        Iterator it = dnss.iterator();
        while (it.hasNext()) {
            String dns = extractNameAsString(((GeneralSubtree) it.next()).getBase());
            if (permitted != null) {
                Iterator _iter = permitted.iterator();
                while (_iter.hasNext()) {
                    String _permitted = (String) _iter.next();
                    if (withinDomain(_permitted, dns)) {
                        intersect.add(_permitted);
                    } else if (withinDomain(dns, _permitted)) {
                        intersect.add(dns);
                    }
                }
            } else if (dns != null) {
                intersect.add(dns);
            }
        }
        return intersect;
    }

    private Set unionDNS(Set excluded, String dns) {
        if (!excluded.isEmpty()) {
            Set union = new HashSet();
            Iterator _iter = excluded.iterator();
            while (_iter.hasNext()) {
                String _permitted = (String) _iter.next();
                if (withinDomain(_permitted, dns)) {
                    union.add(dns);
                } else if (withinDomain(dns, _permitted)) {
                    union.add(_permitted);
                } else {
                    union.add(_permitted);
                    union.add(dns);
                }
            }
            return union;
        } else if (dns == null) {
            return excluded;
        } else {
            excluded.add(dns);
            return excluded;
        }
    }

    private void intersectEmail(String email1, String email2, Set intersect) {
        if (email1.indexOf(64) != -1) {
            String _sub = email1.substring(email1.indexOf(64) + 1);
            if (email2.indexOf(64) != -1) {
                if (email1.equalsIgnoreCase(email2)) {
                    intersect.add(email1);
                }
            } else if (email2.startsWith(".")) {
                if (withinDomain(_sub, email2)) {
                    intersect.add(email1);
                }
            } else if (_sub.equalsIgnoreCase(email2)) {
                intersect.add(email1);
            }
        } else if (email1.startsWith(".")) {
            if (email2.indexOf(64) != -1) {
                if (withinDomain(email2.substring(email1.indexOf(64) + 1), email1)) {
                    intersect.add(email2);
                }
            } else if (email2.startsWith(".")) {
                if (withinDomain(email1, email2) || email1.equalsIgnoreCase(email2)) {
                    intersect.add(email1);
                } else if (withinDomain(email2, email1)) {
                    intersect.add(email2);
                }
            } else if (withinDomain(email2, email1)) {
                intersect.add(email2);
            }
        } else if (email2.indexOf(64) != -1) {
            if (email2.substring(email2.indexOf(64) + 1).equalsIgnoreCase(email1)) {
                intersect.add(email2);
            }
        } else if (email2.startsWith(".")) {
            if (withinDomain(email1, email2)) {
                intersect.add(email1);
            }
        } else if (email1.equalsIgnoreCase(email2)) {
            intersect.add(email1);
        }
    }

    private void checkExcludedURI(Set excluded, String uri) throws NameConstraintValidatorException {
        if (!excluded.isEmpty()) {
            Iterator it = excluded.iterator();
            while (it.hasNext()) {
                if (isUriConstrained(uri, (String) it.next())) {
                    throw new NameConstraintValidatorException("URI is from an excluded subtree.");
                }
            }
        }
    }

    private Set intersectURI(Set permitted, Set uris) {
        Set intersect = new HashSet();
        Iterator it = uris.iterator();
        while (it.hasNext()) {
            String uri = extractNameAsString(((GeneralSubtree) it.next()).getBase());
            if (permitted != null) {
                Iterator _iter = permitted.iterator();
                while (_iter.hasNext()) {
                    intersectURI((String) _iter.next(), uri, intersect);
                }
            } else if (uri != null) {
                intersect.add(uri);
            }
        }
        return intersect;
    }

    private Set unionURI(Set excluded, String uri) {
        if (!excluded.isEmpty()) {
            Set union = new HashSet();
            Iterator _iter = excluded.iterator();
            while (_iter.hasNext()) {
                unionURI((String) _iter.next(), uri, union);
            }
            return union;
        } else if (uri == null) {
            return excluded;
        } else {
            excluded.add(uri);
            return excluded;
        }
    }

    private void intersectURI(String email1, String email2, Set intersect) {
        if (email1.indexOf(64) != -1) {
            String _sub = email1.substring(email1.indexOf(64) + 1);
            if (email2.indexOf(64) != -1) {
                if (email1.equalsIgnoreCase(email2)) {
                    intersect.add(email1);
                }
            } else if (email2.startsWith(".")) {
                if (withinDomain(_sub, email2)) {
                    intersect.add(email1);
                }
            } else if (_sub.equalsIgnoreCase(email2)) {
                intersect.add(email1);
            }
        } else if (email1.startsWith(".")) {
            if (email2.indexOf(64) != -1) {
                if (withinDomain(email2.substring(email1.indexOf(64) + 1), email1)) {
                    intersect.add(email2);
                }
            } else if (email2.startsWith(".")) {
                if (withinDomain(email1, email2) || email1.equalsIgnoreCase(email2)) {
                    intersect.add(email1);
                } else if (withinDomain(email2, email1)) {
                    intersect.add(email2);
                }
            } else if (withinDomain(email2, email1)) {
                intersect.add(email2);
            }
        } else if (email2.indexOf(64) != -1) {
            if (email2.substring(email2.indexOf(64) + 1).equalsIgnoreCase(email1)) {
                intersect.add(email2);
            }
        } else if (email2.startsWith(".")) {
            if (withinDomain(email1, email2)) {
                intersect.add(email1);
            }
        } else if (email1.equalsIgnoreCase(email2)) {
            intersect.add(email1);
        }
    }

    private void checkPermittedURI(Set permitted, String uri) throws NameConstraintValidatorException {
        if (permitted != null) {
            Iterator it = permitted.iterator();
            while (it.hasNext()) {
                if (isUriConstrained(uri, (String) it.next())) {
                    return;
                }
            }
            if (uri.length() != 0 || permitted.size() != 0) {
                throw new NameConstraintValidatorException("URI is not from a permitted subtree.");
            }
        }
    }

    private boolean isUriConstrained(String uri, String constraint) {
        String host = extractHostFromURL(uri);
        if (!constraint.startsWith(".")) {
            if (host.equalsIgnoreCase(constraint)) {
                return true;
            }
            return false;
        } else if (withinDomain(host, constraint)) {
            return true;
        } else {
            return false;
        }
    }

    private static String extractHostFromURL(String url) {
        String sub = url.substring(url.indexOf(58) + 1);
        if (sub.indexOf("//") != -1) {
            sub = sub.substring(sub.indexOf("//") + 2);
        }
        if (sub.lastIndexOf(58) != -1) {
            sub = sub.substring(0, sub.lastIndexOf(58));
        }
        String sub2 = sub.substring(sub.indexOf(58) + 1);
        String sub3 = sub2.substring(sub2.indexOf(64) + 1);
        if (sub3.indexOf(47) != -1) {
            return sub3.substring(0, sub3.indexOf(47));
        }
        return sub3;
    }

    private String extractNameAsString(GeneralName name) {
        return DERIA5String.getInstance(name.getName()).getString();
    }

    private static byte[] max(byte[] ip1, byte[] ip2) {
        for (int i = 0; i < ip1.length; i++) {
            if ((ip1[i] & UnsignedBytes.MAX_VALUE) > (65535 & ip2[i])) {
                return ip1;
            }
        }
        return ip2;
    }

    private static byte[] min(byte[] ip1, byte[] ip2) {
        for (int i = 0; i < ip1.length; i++) {
            if ((ip1[i] & UnsignedBytes.MAX_VALUE) < (65535 & ip2[i])) {
                return ip1;
            }
        }
        return ip2;
    }

    private static int compareTo(byte[] ip1, byte[] ip2) {
        if (Arrays.areEqual(ip1, ip2)) {
            return 0;
        }
        if (Arrays.areEqual(max(ip1, ip2), ip1)) {
            return 1;
        }
        return -1;
    }

    private static byte[] or(byte[] ip1, byte[] ip2) {
        byte[] temp = new byte[ip1.length];
        for (int i = 0; i < ip1.length; i++) {
            temp[i] = (byte) (ip1[i] | ip2[i]);
        }
        return temp;
    }

    private int hashCollection(Collection coll) {
        if (coll == null) {
            return 0;
        }
        int hash = 0;
        for (Object o : coll) {
            if (o instanceof byte[]) {
                hash += Arrays.hashCode((byte[]) o);
            } else {
                hash += o.hashCode();
            }
        }
        return hash;
    }

    private boolean collectionsAreEqual(Collection coll1, Collection coll2) {
        if (coll1 == coll2) {
            return true;
        }
        if (coll1 == null || coll2 == null || coll1.size() != coll2.size()) {
            return false;
        }
        for (Object a : coll1) {
            Iterator it2 = coll2.iterator();
            boolean found = false;
            while (true) {
                if (it2.hasNext()) {
                    if (equals(a, it2.next())) {
                        found = true;
                        continue;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    private boolean equals(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        if (o1 == null || o2 == null) {
            return false;
        }
        if (!(o1 instanceof byte[]) || !(o2 instanceof byte[])) {
            return o1.equals(o2);
        }
        return Arrays.areEqual((byte[]) o1, (byte[]) o2);
    }

    private String stringifyIP(byte[] ip) {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < ip.length / 2; i++) {
            if (temp.length() > 0) {
                temp.append(".");
            }
            temp.append(Integer.toString(ip[i] & UnsignedBytes.MAX_VALUE));
        }
        temp.append("/");
        boolean first = true;
        for (int i2 = ip.length / 2; i2 < ip.length; i2++) {
            if (first) {
                first = false;
            } else {
                temp.append(".");
            }
            temp.append(Integer.toString(ip[i2] & UnsignedBytes.MAX_VALUE));
        }
        return temp.toString();
    }

    private String stringifyIPCollection(Set ips) {
        StringBuilder temp = new StringBuilder();
        temp.append("[");
        Iterator it = ips.iterator();
        while (it.hasNext()) {
            if (temp.length() > 1) {
                temp.append(",");
            }
            temp.append(stringifyIP((byte[]) it.next()));
        }
        temp.append("]");
        return temp.toString();
    }

    private String stringifyOtherNameCollection(Set otherNames) {
        StringBuilder temp = new StringBuilder();
        temp.append("[");
        for (Object obj : otherNames) {
            if (temp.length() > 1) {
                temp.append(",");
            }
            OtherName name = OtherName.getInstance(obj);
            temp.append(name.getTypeID().getId());
            temp.append(":");
            try {
                temp.append(Hex.toHexString(name.getValue().toASN1Primitive().getEncoded()));
            } catch (IOException e) {
                temp.append(e.toString());
            }
        }
        temp.append("]");
        return temp.toString();
    }
}
