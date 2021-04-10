package sun.security.x509;

import java.util.HashMap;
import java.util.Map;
import sun.security.util.ObjectIdentifier;

public class OIDMap {
    private static final int[] NetscapeCertType_data = {2, 16, 840, 1, 113730, 1, 1};
    private static final Map nameMap = new HashMap();
    private static final Map oidMap = new HashMap();

    static {
        addInternal("x509.info.extensions.SubjectKeyIdentifier", PKIXExtensions.SubjectKey_Id, SubjectKeyIdentifierExtension.class);
        addInternal("x509.info.extensions.KeyUsage", PKIXExtensions.KeyUsage_Id, KeyUsageExtension.class);
        addInternal("x509.info.extensions.PrivateKeyUsage", PKIXExtensions.PrivateKeyUsage_Id, PrivateKeyUsageExtension.class);
        addInternal("x509.info.extensions.SubjectAlternativeName", PKIXExtensions.SubjectAlternativeName_Id, SubjectAlternativeNameExtension.class);
        addInternal("x509.info.extensions.IssuerAlternativeName", PKIXExtensions.IssuerAlternativeName_Id, IssuerAlternativeNameExtension.class);
        addInternal("x509.info.extensions.BasicConstraints", PKIXExtensions.BasicConstraints_Id, BasicConstraintsExtension.class);
        addInternal("x509.info.extensions.CRLNumber", PKIXExtensions.CRLNumber_Id, CRLNumberExtension.class);
        addInternal("x509.info.extensions.CRLReasonCode", PKIXExtensions.ReasonCode_Id, CRLReasonCodeExtension.class);
        addInternal("x509.info.extensions.NameConstraints", PKIXExtensions.NameConstraints_Id, NameConstraintsExtension.class);
        addInternal("x509.info.extensions.PolicyMappings", PKIXExtensions.PolicyMappings_Id, PolicyMappingsExtension.class);
        addInternal("x509.info.extensions.AuthorityKeyIdentifier", PKIXExtensions.AuthorityKey_Id, AuthorityKeyIdentifierExtension.class);
        addInternal("x509.info.extensions.PolicyConstraints", PKIXExtensions.PolicyConstraints_Id, PolicyConstraintsExtension.class);
        addInternal("x509.info.extensions.NetscapeCertType", ObjectIdentifier.newInternal(new int[]{2, 16, 840, 1, 113730, 1, 1}), NetscapeCertTypeExtension.class);
        addInternal("x509.info.extensions.CertificatePolicies", PKIXExtensions.CertificatePolicies_Id, CertificatePoliciesExtension.class);
        addInternal("x509.info.extensions.ExtendedKeyUsage", PKIXExtensions.ExtendedKeyUsage_Id, ExtendedKeyUsageExtension.class);
        addInternal("x509.info.extensions.InhibitAnyPolicy", PKIXExtensions.InhibitAnyPolicy_Id, InhibitAnyPolicyExtension.class);
        addInternal("x509.info.extensions.CRLDistributionPoints", PKIXExtensions.CRLDistributionPoints_Id, CRLDistributionPointsExtension.class);
        addInternal("x509.info.extensions.CertificateIssuer", PKIXExtensions.CertificateIssuer_Id, CertificateIssuerExtension.class);
        addInternal("x509.info.extensions.SubjectInfoAccess", PKIXExtensions.SubjectInfoAccess_Id, SubjectInfoAccessExtension.class);
        addInternal("x509.info.extensions.AuthorityInfoAccess", PKIXExtensions.AuthInfoAccess_Id, AuthorityInfoAccessExtension.class);
        addInternal("x509.info.extensions.IssuingDistributionPoint", PKIXExtensions.IssuingDistributionPoint_Id, IssuingDistributionPointExtension.class);
        addInternal("x509.info.extensions.DeltaCRLIndicator", PKIXExtensions.DeltaCRLIndicator_Id, DeltaCRLIndicatorExtension.class);
        addInternal("x509.info.extensions.FreshestCRL", PKIXExtensions.FreshestCRL_Id, FreshestCRLExtension.class);
        addInternal("x509.info.extensions.OCSPNoCheck", PKIXExtensions.OCSPNoCheck_Id, OCSPNoCheckExtension.class);
    }

    private static void addInternal(String str, ObjectIdentifier objectIdentifier, Class cls) {
        OIDInfo oIDInfo = new OIDInfo(str, objectIdentifier, cls);
        oidMap.put(objectIdentifier, oIDInfo);
        nameMap.put(str, oIDInfo);
    }

    /* access modifiers changed from: private */
    public static class OIDInfo {
        private volatile Class clazz;
        final String name;
        final ObjectIdentifier oid;

        OIDInfo(String str, ObjectIdentifier objectIdentifier, Class cls) {
            this.name = str;
            this.oid = objectIdentifier;
            this.clazz = cls;
        }

        /* access modifiers changed from: package-private */
        public Class getClazz() {
            return this.clazz;
        }
    }

    public static String getName(ObjectIdentifier objectIdentifier) {
        OIDInfo oIDInfo = (OIDInfo) oidMap.get(objectIdentifier);
        if (oIDInfo == null) {
            return null;
        }
        return oIDInfo.name;
    }

    public static Class getClass(ObjectIdentifier objectIdentifier) {
        OIDInfo oIDInfo = (OIDInfo) oidMap.get(objectIdentifier);
        if (oIDInfo == null) {
            return null;
        }
        return oIDInfo.getClazz();
    }
}
