package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.security.cert.CertificateException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import sun.misc.HexDumpEncoder;
import sun.security.util.Debug;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class CertificateExtensions implements CertAttrSet {
    private static Class[] PARAMS = {Boolean.class, Object.class};
    private static final Debug debug = Debug.getInstance("x509");
    private Map map = Collections.synchronizedMap(new TreeMap());
    private Map unparseableExtensions;
    private boolean unsupportedCritExt = false;

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "extensions";
    }

    public CertificateExtensions() {
    }

    public CertificateExtensions(DerInputStream derInputStream) {
        init(derInputStream);
    }

    private void init(DerInputStream derInputStream) {
        DerValue[] sequence;
        for (DerValue derValue : derInputStream.getSequence(5)) {
            parseExtension(new Extension(derValue));
        }
    }

    private void parseExtension(Extension extension) {
        try {
            Class cls = OIDMap.getClass(extension.getExtensionId());
            if (cls == null) {
                if (extension.isCritical()) {
                    this.unsupportedCritExt = true;
                }
                if (this.map.put(extension.getExtensionId().toString(), extension) != null) {
                    throw new IOException("Duplicate extensions not allowed");
                }
                return;
            }
            CertAttrSet certAttrSet = (CertAttrSet) cls.getConstructor(PARAMS).newInstance(Boolean.valueOf(extension.isCritical()), extension.getExtensionValue());
            if (this.map.put(certAttrSet.getName(), (Extension) certAttrSet) != null) {
                throw new IOException("Duplicate extensions not allowed");
            }
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (!extension.isCritical()) {
                if (this.unparseableExtensions == null) {
                    this.unparseableExtensions = new TreeMap();
                }
                this.unparseableExtensions.put(extension.getExtensionId().toString(), new UnparseableExtension(extension, targetException));
                Debug debug2 = debug;
                if (debug2 != null) {
                    debug2.println("Error parsing extension: " + extension);
                    targetException.printStackTrace();
                    HexDumpEncoder hexDumpEncoder = new HexDumpEncoder();
                    PrintStream printStream = System.err;
                    hexDumpEncoder.encodeBuffer(extension.getExtensionValue());
                    throw null;
                }
            } else if (targetException instanceof IOException) {
                throw ((IOException) targetException);
            } else {
                throw new IOException(targetException);
            }
        } catch (IOException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new IOException(e3);
        }
    }

    @Override // sun.security.x509.CertAttrSet
    public void encode(OutputStream outputStream) {
        encode(outputStream, false);
    }

    public void encode(OutputStream outputStream, boolean z) {
        DerOutputStream derOutputStream = new DerOutputStream();
        Object[] array = this.map.values().toArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] instanceof CertAttrSet) {
                ((CertAttrSet) array[i]).encode(derOutputStream);
            } else if (array[i] instanceof Extension) {
                ((Extension) array[i]).encode(derOutputStream);
            } else {
                throw new CertificateException("Illegal extension object");
            }
        }
        DerOutputStream derOutputStream2 = new DerOutputStream();
        derOutputStream2.write((byte) 48, derOutputStream);
        if (!z) {
            DerOutputStream derOutputStream3 = new DerOutputStream();
            derOutputStream3.write(DerValue.createTag(Byte.MIN_VALUE, true, (byte) 3), derOutputStream2);
            derOutputStream2 = derOutputStream3;
        }
        outputStream.write(derOutputStream2.toByteArray());
    }

    public Extension get(String str) {
        Extension extension = (Extension) this.map.get(str);
        if (extension != null) {
            return extension;
        }
        throw new IOException("No extension found with name " + str);
    }

    /* access modifiers changed from: package-private */
    public Extension getExtension(String str) {
        return (Extension) this.map.get(str);
    }

    public Collection getAllExtensions() {
        return this.map.values();
    }

    public Map getUnparseableExtensions() {
        Map map2 = this.unparseableExtensions;
        return map2 == null ? Collections.emptyMap() : map2;
    }

    public boolean hasUnsupportedCriticalExtension() {
        return this.unsupportedCritExt;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CertificateExtensions)) {
            return false;
        }
        CertificateExtensions certificateExtensions = (CertificateExtensions) obj;
        Object[] array = certificateExtensions.getAllExtensions().toArray();
        int length = array.length;
        if (length != this.map.size()) {
            return false;
        }
        String str = null;
        for (int i = 0; i < length; i++) {
            if (array[i] instanceof CertAttrSet) {
                str = ((CertAttrSet) array[i]).getName();
            }
            Extension extension = (Extension) array[i];
            if (str == null) {
                str = extension.getExtensionId().toString();
            }
            Extension extension2 = (Extension) this.map.get(str);
            if (extension2 == null || !extension2.equals(extension)) {
                return false;
            }
        }
        return getUnparseableExtensions().equals(certificateExtensions.getUnparseableExtensions());
    }

    public int hashCode() {
        return this.map.hashCode() + getUnparseableExtensions().hashCode();
    }

    public String toString() {
        return this.map.toString();
    }
}
