package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.security.cert.CRLException;
import java.security.cert.CertificateException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class CRLExtensions {
    private static final Class[] PARAMS = {Boolean.class, Object.class};
    private Map map = Collections.synchronizedMap(new TreeMap());
    private boolean unsupportedCritExt = false;

    public CRLExtensions() {
    }

    public CRLExtensions(DerInputStream derInputStream) {
        init(derInputStream);
    }

    private void init(DerInputStream derInputStream) {
        DerValue[] sequence;
        try {
            byte peekByte = (byte) derInputStream.peekByte();
            if ((peekByte & 192) == 128 && (peekByte & 31) == 0) {
                derInputStream = derInputStream.getDerValue().data;
            }
            for (DerValue derValue : derInputStream.getSequence(5)) {
                parseExtension(new Extension(derValue));
            }
        } catch (IOException e) {
            throw new CRLException("Parsing error: " + e.toString());
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
                    throw new CRLException("Duplicate extensions not allowed");
                }
                return;
            }
            CertAttrSet certAttrSet = (CertAttrSet) cls.getConstructor(PARAMS).newInstance(Boolean.valueOf(extension.isCritical()), extension.getExtensionValue());
            if (this.map.put(certAttrSet.getName(), (Extension) certAttrSet) != null) {
                throw new CRLException("Duplicate extensions not allowed");
            }
        } catch (InvocationTargetException e) {
            throw new CRLException(e.getTargetException().getMessage());
        } catch (Exception e2) {
            throw new CRLException(e2.toString());
        }
    }

    public void encode(OutputStream outputStream, boolean z) {
        try {
            DerOutputStream derOutputStream = new DerOutputStream();
            Object[] array = this.map.values().toArray();
            for (int i = 0; i < array.length; i++) {
                if (array[i] instanceof CertAttrSet) {
                    ((CertAttrSet) array[i]).encode(derOutputStream);
                } else if (array[i] instanceof Extension) {
                    ((Extension) array[i]).encode(derOutputStream);
                } else {
                    throw new CRLException("Illegal extension object");
                }
            }
            DerOutputStream derOutputStream2 = new DerOutputStream();
            derOutputStream2.write((byte) 48, derOutputStream);
            DerOutputStream derOutputStream3 = new DerOutputStream();
            if (z) {
                derOutputStream3.write(DerValue.createTag(Byte.MIN_VALUE, true, (byte) 0), derOutputStream2);
                derOutputStream2 = derOutputStream3;
            }
            outputStream.write(derOutputStream2.toByteArray());
        } catch (IOException e) {
            throw new CRLException("Encoding error: " + e.toString());
        } catch (CertificateException e2) {
            throw new CRLException("Encoding error: " + e2.toString());
        }
    }

    public Extension get(String str) {
        if (new X509AttributeName(str).getPrefix().equalsIgnoreCase("x509")) {
            str = str.substring(str.lastIndexOf(".") + 1);
        }
        return (Extension) this.map.get(str);
    }

    public Collection getAllExtensions() {
        return this.map.values();
    }

    public boolean equals(Object obj) {
        Object[] array;
        int length;
        if (this == obj) {
            return true;
        }
        if (!((obj instanceof CRLExtensions) && (length = (array = ((CRLExtensions) obj).getAllExtensions().toArray()).length) == this.map.size())) {
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
        return true;
    }

    public int hashCode() {
        return this.map.hashCode();
    }

    public String toString() {
        return this.map.toString();
    }
}
