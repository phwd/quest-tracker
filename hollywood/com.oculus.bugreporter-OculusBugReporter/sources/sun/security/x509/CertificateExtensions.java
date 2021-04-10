package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.security.cert.CertificateException;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;
import sun.misc.HexDumpEncoder;
import sun.security.util.Debug;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;

public class CertificateExtensions implements CertAttrSet<Extension> {
    public static final String IDENT = "x509.info.extensions";
    public static final String NAME = "extensions";
    private static Class[] PARAMS = {Boolean.class, Object.class};
    private static final Debug debug = Debug.getInstance(X509CertImpl.NAME);
    private Map<String, Extension> map = Collections.synchronizedMap(new TreeMap());
    private Map<String, Extension> unparseableExtensions;
    private boolean unsupportedCritExt = false;

    public CertificateExtensions() {
    }

    public CertificateExtensions(DerInputStream in) throws IOException {
        init(in);
    }

    private void init(DerInputStream in) throws IOException {
        DerValue[] exts;
        for (DerValue derValue : in.getSequence(5)) {
            parseExtension(new Extension(derValue));
        }
    }

    private void parseExtension(Extension ext) throws IOException {
        try {
            Class<?> extClass = OIDMap.getClass(ext.getExtensionId());
            if (extClass == null) {
                if (ext.isCritical()) {
                    this.unsupportedCritExt = true;
                }
                if (this.map.put(ext.getExtensionId().toString(), ext) != null) {
                    throw new IOException("Duplicate extensions not allowed");
                }
                return;
            }
            CertAttrSet<?> certExt = (CertAttrSet) extClass.getConstructor(PARAMS).newInstance(Boolean.valueOf(ext.isCritical()), ext.getExtensionValue());
            if (this.map.put(certExt.getName(), (Extension) certExt) != null) {
                throw new IOException("Duplicate extensions not allowed");
            }
        } catch (InvocationTargetException invk) {
            Throwable e = invk.getTargetException();
            if (!ext.isCritical()) {
                if (this.unparseableExtensions == null) {
                    this.unparseableExtensions = new TreeMap();
                }
                this.unparseableExtensions.put(ext.getExtensionId().toString(), new UnparseableExtension(ext, e));
                Debug debug2 = debug;
                if (debug2 != null) {
                    debug2.println("Error parsing extension: " + ((Object) ext));
                    e.printStackTrace();
                    System.err.println(new HexDumpEncoder().encodeBuffer(ext.getExtensionValue()));
                }
            } else if (e instanceof IOException) {
                throw ((IOException) e);
            } else {
                throw new IOException(e);
            }
        } catch (IOException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new IOException(e3);
        }
    }

    @Override // sun.security.x509.CertAttrSet
    public void encode(OutputStream out) throws CertificateException, IOException {
        encode(out, false);
    }

    public void encode(OutputStream out, boolean isCertReq) throws CertificateException, IOException {
        DerOutputStream tmp;
        DerOutputStream extOut = new DerOutputStream();
        Object[] objs = this.map.values().toArray();
        for (int i = 0; i < objs.length; i++) {
            if (objs[i] instanceof CertAttrSet) {
                ((CertAttrSet) objs[i]).encode(extOut);
            } else if (objs[i] instanceof Extension) {
                ((Extension) objs[i]).encode(extOut);
            } else {
                throw new CertificateException("Illegal extension object");
            }
        }
        DerOutputStream seq = new DerOutputStream();
        seq.write((byte) 48, extOut);
        if (!isCertReq) {
            tmp = new DerOutputStream();
            tmp.write(DerValue.createTag(Byte.MIN_VALUE, true, (byte) 3), seq);
        } else {
            tmp = seq;
        }
        out.write(tmp.toByteArray());
    }

    @Override // sun.security.x509.CertAttrSet
    public void set(String name, Object obj) throws IOException {
        if (obj instanceof Extension) {
            this.map.put(name, (Extension) obj);
            return;
        }
        throw new IOException("Unknown extension type.");
    }

    @Override // sun.security.x509.CertAttrSet
    public Extension get(String name) throws IOException {
        Extension obj = this.map.get(name);
        if (obj != null) {
            return obj;
        }
        throw new IOException("No extension found with name " + name);
    }

    /* access modifiers changed from: package-private */
    public Extension getExtension(String name) {
        return this.map.get(name);
    }

    @Override // sun.security.x509.CertAttrSet
    public void delete(String name) throws IOException {
        if (this.map.get(name) != null) {
            this.map.remove(name);
            return;
        }
        throw new IOException("No extension found with name " + name);
    }

    public String getNameByOid(ObjectIdentifier oid) throws IOException {
        for (String name : this.map.keySet()) {
            if (this.map.get(name).getExtensionId().equals((Object) oid)) {
                return name;
            }
        }
        return null;
    }

    @Override // sun.security.x509.CertAttrSet
    public Enumeration<Extension> getElements() {
        return Collections.enumeration(this.map.values());
    }

    public Collection<Extension> getAllExtensions() {
        return this.map.values();
    }

    public Map<String, Extension> getUnparseableExtensions() {
        Map<String, Extension> map2 = this.unparseableExtensions;
        if (map2 == null) {
            return Collections.emptyMap();
        }
        return map2;
    }

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "extensions";
    }

    public boolean hasUnsupportedCriticalExtension() {
        return this.unsupportedCritExt;
    }

    public boolean equals(Object other) {
        Object[] objs;
        int len;
        if (this == other) {
            return true;
        }
        if (!((other instanceof CertificateExtensions) && (len = (objs = ((CertificateExtensions) other).getAllExtensions().toArray()).length) == this.map.size())) {
            return false;
        }
        String key = null;
        for (int i = 0; i < len; i++) {
            if (objs[i] instanceof CertAttrSet) {
                key = ((CertAttrSet) objs[i]).getName();
            }
            Extension otherExt = (Extension) objs[i];
            if (key == null) {
                key = otherExt.getExtensionId().toString();
            }
            Extension thisExt = this.map.get(key);
            if (thisExt == null || !thisExt.equals(otherExt)) {
                return false;
            }
        }
        return getUnparseableExtensions().equals(((CertificateExtensions) other).getUnparseableExtensions());
    }

    public int hashCode() {
        return this.map.hashCode() + getUnparseableExtensions().hashCode();
    }

    @Override // sun.security.x509.CertAttrSet
    public String toString() {
        return this.map.toString();
    }
}
