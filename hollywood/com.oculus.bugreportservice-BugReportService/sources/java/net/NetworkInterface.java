package java.net;

import android.system.ErrnoException;
import android.system.StructIfaddrs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import libcore.io.Libcore;

public final class NetworkInterface {
    private static final int defaultIndex;
    private static final NetworkInterface defaultInterface = DefaultInterface.getDefault();
    private InetAddress[] addrs;
    private InterfaceAddress[] bindings;
    private List childs;
    private String displayName;
    private byte[] hardwareAddr;
    private int index;
    private String name;
    private NetworkInterface parent = null;
    private boolean virtual = false;

    static {
        NetworkInterface networkInterface = defaultInterface;
        if (networkInterface != null) {
            defaultIndex = networkInterface.getIndex();
        } else {
            defaultIndex = 0;
        }
    }

    NetworkInterface() {
    }

    NetworkInterface(String str, int i, InetAddress[] inetAddressArr) {
        this.name = str;
        this.index = i;
        this.addrs = inetAddressArr;
    }

    public String getName() {
        return this.name;
    }

    public Enumeration getInetAddresses() {
        return new Enumeration() {
            /* class java.net.NetworkInterface.AnonymousClass1checkedAddresses */
            private int count = 0;
            private int i = 0;
            private InetAddress[] local_addrs;

            {
                boolean z;
                this.local_addrs = new InetAddress[NetworkInterface.this.addrs.length];
                SecurityManager securityManager = System.getSecurityManager();
                if (securityManager == null) {
                    z = true;
                } else {
                    try {
                        securityManager.checkPermission(new NetPermission("getNetworkInformation"));
                        throw null;
                    } catch (SecurityException unused) {
                        z = false;
                    }
                }
                for (int i2 = 0; i2 < NetworkInterface.this.addrs.length; i2++) {
                    if (securityManager == null || z) {
                        InetAddress[] inetAddressArr = this.local_addrs;
                        int i3 = this.count;
                        this.count = i3 + 1;
                        inetAddressArr[i3] = NetworkInterface.this.addrs[i2];
                    } else {
                        try {
                            securityManager.checkConnect(NetworkInterface.this.addrs[i2].getHostAddress(), -1);
                            throw null;
                        } catch (SecurityException unused2) {
                            continue;
                        }
                    }
                }
            }

            @Override // java.util.Enumeration
            public InetAddress nextElement() {
                int i2 = this.i;
                if (i2 < this.count) {
                    InetAddress[] inetAddressArr = this.local_addrs;
                    this.i = i2 + 1;
                    return inetAddressArr[i2];
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.i < this.count;
            }
        };
    }

    public int getIndex() {
        return this.index;
    }

    public static NetworkInterface getByInetAddress(InetAddress inetAddress) {
        if (inetAddress == null) {
            throw new NullPointerException();
        } else if ((inetAddress instanceof Inet4Address) || (inetAddress instanceof Inet6Address)) {
            NetworkInterface[] all = getAll();
            for (NetworkInterface networkInterface : all) {
                Iterator it = Collections.list(networkInterface.getInetAddresses()).iterator();
                while (it.hasNext()) {
                    if (((InetAddress) it.next()).equals(inetAddress)) {
                        return networkInterface;
                    }
                }
            }
            return null;
        } else {
            throw new IllegalArgumentException("invalid address type");
        }
    }

    private static NetworkInterface[] getAll() {
        HashMap hashMap = new HashMap();
        try {
            StructIfaddrs[] structIfaddrsArr = Libcore.os.getifaddrs();
            for (StructIfaddrs structIfaddrs : structIfaddrsArr) {
                String str = structIfaddrs.ifa_name;
                List list = (List) hashMap.get(str);
                if (list == null) {
                    list = new ArrayList();
                    hashMap.put(str, list);
                }
                list.add(structIfaddrs);
            }
            HashMap hashMap2 = new HashMap(hashMap.size());
            for (Map.Entry entry : hashMap.entrySet()) {
                String str2 = (String) entry.getKey();
                int if_nametoindex = Libcore.os.if_nametoindex((String) entry.getKey());
                if (if_nametoindex != 0) {
                    NetworkInterface networkInterface = new NetworkInterface(str2, if_nametoindex, null);
                    networkInterface.displayName = str2;
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    for (StructIfaddrs structIfaddrs2 : (List) entry.getValue()) {
                        InetAddress inetAddress = structIfaddrs2.ifa_addr;
                        if (inetAddress != null) {
                            arrayList.add(inetAddress);
                            arrayList2.add(new InterfaceAddress(structIfaddrs2.ifa_addr, (Inet4Address) structIfaddrs2.ifa_broadaddr, structIfaddrs2.ifa_netmask));
                        }
                        byte[] bArr = structIfaddrs2.hwaddr;
                        if (bArr != null) {
                            networkInterface.hardwareAddr = bArr;
                        }
                    }
                    networkInterface.addrs = (InetAddress[]) arrayList.toArray(new InetAddress[arrayList.size()]);
                    networkInterface.bindings = (InterfaceAddress[]) arrayList2.toArray(new InterfaceAddress[arrayList2.size()]);
                    networkInterface.childs = new ArrayList(0);
                    hashMap2.put(str2, networkInterface);
                }
            }
            for (Map.Entry entry2 : hashMap2.entrySet()) {
                NetworkInterface networkInterface2 = (NetworkInterface) entry2.getValue();
                String name2 = networkInterface2.getName();
                int indexOf = name2.indexOf(58);
                if (indexOf != -1) {
                    NetworkInterface networkInterface3 = (NetworkInterface) hashMap2.get(name2.substring(0, indexOf));
                    networkInterface2.virtual = true;
                    networkInterface2.parent = networkInterface3;
                    networkInterface3.childs.add(networkInterface2);
                }
            }
            return (NetworkInterface[]) hashMap2.values().toArray(new NetworkInterface[hashMap2.size()]);
        } catch (ErrnoException e) {
            throw e.rethrowAsSocketException();
        }
    }

    public boolean equals(Object obj) {
        boolean z;
        if (!(obj instanceof NetworkInterface)) {
            return false;
        }
        NetworkInterface networkInterface = (NetworkInterface) obj;
        String str = this.name;
        if (str != null) {
            if (!str.equals(networkInterface.name)) {
                return false;
            }
        } else if (networkInterface.name != null) {
            return false;
        }
        InetAddress[] inetAddressArr = this.addrs;
        if (inetAddressArr != null) {
            InetAddress[] inetAddressArr2 = networkInterface.addrs;
            if (inetAddressArr2 == null || inetAddressArr.length != inetAddressArr2.length) {
                return false;
            }
            int length = inetAddressArr2.length;
            for (int i = 0; i < length; i++) {
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        z = false;
                        break;
                    } else if (this.addrs[i].equals(inetAddressArr2[i2])) {
                        z = true;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (!z) {
                    return false;
                }
            }
            return true;
        } else if (networkInterface.addrs == null) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        String str = this.name;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("name:");
        String str = this.name;
        if (str == null) {
            str = "null";
        }
        sb.append(str);
        String sb2 = sb.toString();
        if (this.displayName == null) {
            return sb2;
        }
        return sb2 + " (" + this.displayName + ")";
    }
}
