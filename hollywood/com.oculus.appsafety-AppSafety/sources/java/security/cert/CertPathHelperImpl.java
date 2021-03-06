package java.security.cert;

import java.util.Date;
import java.util.Set;
import sun.security.provider.certpath.CertPathHelper;
import sun.security.x509.GeneralNameInterface;

class CertPathHelperImpl extends CertPathHelper {
    private CertPathHelperImpl() {
    }

    static synchronized void initialize() {
        synchronized (CertPathHelperImpl.class) {
            if (CertPathHelper.instance == null) {
                CertPathHelper.instance = new CertPathHelperImpl();
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // sun.security.provider.certpath.CertPathHelper
    public void implSetPathToNames(X509CertSelector sel, Set<GeneralNameInterface> names) {
        sel.setPathToNamesInternal(names);
    }

    /* access modifiers changed from: protected */
    @Override // sun.security.provider.certpath.CertPathHelper
    public void implSetDateAndTime(X509CRLSelector sel, Date date, long skew) {
        sel.setDateAndTime(date, skew);
    }
}
