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

public final class X2 {
    public int A00;
    public int A01;
    public List<InetSocketAddress> A02 = Collections.emptyList();
    public List<Proxy> A03 = Collections.emptyList();
    public InetSocketAddress A04;
    public Proxy A05;
    public final List<XI> A06 = new ArrayList();
    public final C0190Xp A07;
    public final X4 A08;

    public final XI A00() throws IOException {
        StringBuilder sb;
        Object obj;
        boolean contains;
        String str;
        int i;
        if (this.A00 >= this.A02.size()) {
            if (this.A01 >= this.A03.size()) {
                List<XI> list = this.A06;
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
                    XT xt = this.A07.A0A;
                    str = xt.A02;
                    i = xt.A00;
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
            XI xi = new XI(this.A07, this.A05, inetSocketAddress2);
            X4 x4 = this.A08;
            synchronized (x4) {
                contains = x4.A00.contains(xi);
            }
            if (!contains) {
                return xi;
            }
            this.A06.add(xi);
            return A00();
        }
        sb = new StringBuilder("No route to ");
        sb.append(this.A07.A0A.A02);
        sb.append("; exhausted inet socket addresses: ");
        obj = this.A02;
        sb.append(obj);
        throw new SocketException(sb.toString());
    }

    public X2(C0190Xp xp, X4 x4) {
        List<Proxy> A052;
        this.A07 = xp;
        this.A08 = x4;
        XT xt = xp.A0A;
        Proxy proxy = xp.A00;
        if (proxy != null) {
            A052 = Collections.singletonList(proxy);
        } else {
            List<Proxy> select = xp.A01.select(xt.A0A());
            if (select == null || select.isEmpty()) {
                A052 = XD.A05(Proxy.NO_PROXY);
            } else {
                A052 = Collections.unmodifiableList(new ArrayList(select));
            }
        }
        this.A03 = A052;
        this.A01 = 0;
    }
}
