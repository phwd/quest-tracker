package java.net;

import android.system.OsConstants;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.util.Arrays;
import libcore.io.Libcore;
import sun.misc.Unsafe;

public final class Inet6Address extends InetAddress {
    public static final InetAddress ANY = new Inet6Address("::", new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 0);
    private static final long FIELDS_OFFSET;
    public static final InetAddress LOOPBACK = new Inet6Address("ip6-localhost", new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 0);
    private static final Unsafe UNSAFE;
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("ipaddress", byte[].class), new ObjectStreamField("scope_id", Integer.TYPE), new ObjectStreamField("scope_id_set", Boolean.TYPE), new ObjectStreamField("scope_ifname_set", Boolean.TYPE), new ObjectStreamField("ifname", String.class)};
    private static final long serialVersionUID = 6880410070516793377L;
    private final transient Inet6AddressHolder holder6 = new Inet6AddressHolder();

    static {
        try {
            Unsafe unsafe = Unsafe.getUnsafe();
            FIELDS_OFFSET = unsafe.objectFieldOffset(Inet6Address.class.getDeclaredField("holder6"));
            UNSAFE = unsafe;
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    /* access modifiers changed from: private */
    public class Inet6AddressHolder {
        byte[] ipaddress;
        int scope_id;
        boolean scope_id_set;
        NetworkInterface scope_ifname;
        boolean scope_ifname_set;

        private Inet6AddressHolder() {
            this.ipaddress = new byte[16];
        }

        /* access modifiers changed from: package-private */
        public void setAddr(byte[] bArr) {
            if (bArr.length == 16) {
                System.arraycopy(bArr, 0, this.ipaddress, 0, 16);
            }
        }

        /* access modifiers changed from: package-private */
        public void init(byte[] bArr, int i) {
            setAddr(bArr);
            if (i > 0) {
                this.scope_id = i;
                this.scope_id_set = true;
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Inet6AddressHolder)) {
                return false;
            }
            return Arrays.equals(this.ipaddress, ((Inet6AddressHolder) obj).ipaddress);
        }

        public int hashCode() {
            int i;
            if (this.ipaddress == null) {
                return 0;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < 16; i3 = i) {
                i = i3;
                int i4 = 0;
                int i5 = 0;
                while (i4 < 4 && i < 16) {
                    i5 = (i5 << 8) + this.ipaddress[i];
                    i4++;
                    i++;
                }
                i2 += i5;
            }
            return i2;
        }

        /* access modifiers changed from: package-private */
        public boolean isAnyLocalAddress() {
            byte b = 0;
            for (int i = 0; i < 16; i++) {
                b = (byte) (b | this.ipaddress[i]);
            }
            return b == 0;
        }

        /* access modifiers changed from: package-private */
        public boolean isLinkLocalAddress() {
            byte[] bArr = this.ipaddress;
            return (bArr[0] & 255) == 254 && (bArr[1] & 192) == 128;
        }
    }

    Inet6Address() {
        this.holder.init(null, OsConstants.AF_INET6);
    }

    Inet6Address(String str, byte[] bArr, int i) {
        this.holder.init(str, OsConstants.AF_INET6);
        this.holder6.init(bArr, i);
    }

    public static Inet6Address getByAddress(String str, byte[] bArr, int i) {
        if (str != null && str.length() > 0 && str.charAt(0) == '[' && str.charAt(str.length() - 1) == ']') {
            str = str.substring(1, str.length() - 1);
        }
        if (bArr != null && bArr.length == 16) {
            return new Inet6Address(str, bArr, i);
        }
        throw new UnknownHostException("addr is of illegal length");
    }

    private void readObject(ObjectInputStream objectInputStream) {
        if (Inet6Address.class.getClassLoader() != Class.class.getClassLoader()) {
            throw new SecurityException("invalid address type");
        }
        objectInputStream.readFields();
        throw null;
    }

    private synchronized void writeObject(ObjectOutputStream objectOutputStream) {
        if (this.holder6.scope_ifname != null) {
            this.holder6.scope_ifname.getName();
            this.holder6.scope_ifname_set = true;
        }
        objectOutputStream.putFields();
        throw null;
    }

    @Override // java.net.InetAddress
    public boolean isAnyLocalAddress() {
        return this.holder6.isAnyLocalAddress();
    }

    public boolean isLinkLocalAddress() {
        return this.holder6.isLinkLocalAddress();
    }

    @Override // java.net.InetAddress
    public byte[] getAddress() {
        return (byte[]) this.holder6.ipaddress.clone();
    }

    public int getScopeId() {
        return this.holder6.scope_id;
    }

    @Override // java.net.InetAddress
    public String getHostAddress() {
        return Libcore.os.getnameinfo(this, OsConstants.NI_NUMERICHOST);
    }

    @Override // java.net.InetAddress
    public int hashCode() {
        return this.holder6.hashCode();
    }

    @Override // java.net.InetAddress
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Inet6Address)) {
            return false;
        }
        return this.holder6.equals(((Inet6Address) obj).holder6);
    }
}
