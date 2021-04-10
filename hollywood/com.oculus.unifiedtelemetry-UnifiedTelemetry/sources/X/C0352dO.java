package X;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/* renamed from: X.dO  reason: case insensitive filesystem */
public final class C0352dO {
    public int A00;
    public int A01;
    public List<InetSocketAddress> A02 = Collections.emptyList();
    public List<Proxy> A03 = Collections.emptyList();
    public InetSocketAddress A04;
    public Proxy A05;
    public final List<C0357de> A06 = new ArrayList();
    public final eB A07;
    public final dQ A08;

    public final C0357de A00() throws IOException {
        StringBuilder sb;
        Object obj;
        boolean contains;
        String str;
        int i;
        if (this.A00 >= this.A02.size()) {
            if (this.A01 >= this.A03.size()) {
                List<C0357de> list = this.A06;
                if (!list.isEmpty()) {
                    return list.remove(0);
                }
                throw new NoSuchElementException();
            } else if (this.A01 < this.A03.size()) {
                List<Proxy> list2 = this.A03;
                int i2 = this.A01;
                this.A01 = i2 + 1;
                Proxy proxy = list2.get(i2);
                this.A02 = new ArrayList();
                if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.SOCKS) {
                    C0367dp dpVar = this.A07.A0A;
                    str = dpVar.A02;
                    i = dpVar.A00;
                } else {
                    SocketAddress address = proxy.address();
                    if (address instanceof InetSocketAddress) {
                        InetSocketAddress inetSocketAddress = (InetSocketAddress) address;
                        InetAddress address2 = inetSocketAddress.getAddress();
                        if (address2 == null) {
                            str = inetSocketAddress.getHostName();
                        } else {
                            str = address2.getHostAddress();
                        }
                        i = inetSocketAddress.getPort();
                    } else {
                        StringBuilder sb2 = new StringBuilder("Proxy.address() is not an InetSocketAddress: ");
                        sb2.append(address.getClass());
                        throw new IllegalArgumentException(sb2.toString());
                    }
                }
                if (i < 1 || i > 65535) {
                    sb = new StringBuilder("No route to ");
                    sb.append(str);
                    sb.append(":");
                    sb.append(i);
                    sb.append("; port is out of range");
                    throw new SocketException(sb.toString());
                }
                if (proxy.type() == Proxy.Type.SOCKS) {
                    this.A02.add(InetSocketAddress.createUnresolved(str, i));
                } else {
                    List<InetAddress> lookup = this.A07.A09.lookup(str);
                    int size = lookup.size();
                    for (int i3 = 0; i3 < size; i3++) {
                        this.A02.add(new InetSocketAddress(lookup.get(i3), i));
                    }
                }
                this.A00 = 0;
                this.A05 = proxy;
            } else {
                sb = new StringBuilder("No route to ");
                sb.append(this.A07.A0A.A02);
                sb.append("; exhausted proxy configurations: ");
                obj = this.A03;
                sb.append(obj);
                throw new SocketException(sb.toString());
            }
        }
        if (this.A00 < this.A02.size()) {
            List<InetSocketAddress> list3 = this.A02;
            int i4 = this.A00;
            this.A00 = i4 + 1;
            InetSocketAddress inetSocketAddress2 = list3.get(i4);
            this.A04 = inetSocketAddress2;
            C0357de deVar = new C0357de(this.A07, this.A05, inetSocketAddress2);
            dQ dQVar = this.A08;
            synchronized (dQVar) {
                contains = dQVar.A00.contains(deVar);
            }
            if (!contains) {
                return deVar;
            }
            this.A06.add(deVar);
            return A00();
        }
        sb = new StringBuilder("No route to ");
        sb.append(this.A07.A0A.A02);
        sb.append("; exhausted inet socket addresses: ");
        obj = this.A02;
        sb.append(obj);
        throw new SocketException(sb.toString());
    }

    public C0352dO(eB eBVar, dQ dQVar) {
        List<Proxy> A052;
        this.A07 = eBVar;
        this.A08 = dQVar;
        C0367dp dpVar = eBVar.A0A;
        Proxy proxy = eBVar.A00;
        if (proxy != null) {
            A052 = Collections.singletonList(proxy);
        } else {
            List<Proxy> select = eBVar.A01.select(dpVar.A0B());
            if (select == null || select.isEmpty()) {
                A052 = dZ.A05(Proxy.NO_PROXY);
            } else {
                A052 = Collections.unmodifiableList(new ArrayList(select));
            }
        }
        this.A03 = A052;
        this.A01 = 0;
    }
}
