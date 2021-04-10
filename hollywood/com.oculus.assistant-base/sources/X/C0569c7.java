package X;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/* renamed from: X.c7  reason: case insensitive filesystem */
public final class C0569c7 {
    public int A00;
    public int A01;
    public List A02 = Collections.emptyList();
    public List A03 = Collections.emptyList();
    public InetSocketAddress A04;
    public Proxy A05;
    public final List A06 = new ArrayList();
    public final C0523bM A07;
    public final c5 A08;

    public final C0556bt A00() {
        boolean contains;
        String str;
        int i;
        if (this.A00 >= this.A02.size()) {
            if (this.A01 >= this.A03.size()) {
                List list = this.A06;
                if (!list.isEmpty()) {
                    return (C0556bt) list.remove(0);
                }
                throw new NoSuchElementException();
            } else if (this.A01 < this.A03.size()) {
                List list2 = this.A03;
                int i2 = this.A01;
                this.A01 = i2 + 1;
                Proxy proxy = (Proxy) list2.get(i2);
                this.A02 = new ArrayList();
                if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.SOCKS) {
                    C0544bh bhVar = this.A07.A09;
                    str = bhVar.A02;
                    i = bhVar.A00;
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
                        StringBuilder sb = new StringBuilder("Proxy.address() is not an InetSocketAddress: ");
                        sb.append(address.getClass());
                        throw new IllegalArgumentException(sb.toString());
                    }
                }
                if (i < 1 || i > 65535) {
                    StringBuilder sb2 = new StringBuilder("No route to ");
                    sb2.append(str);
                    sb2.append(":");
                    sb2.append(i);
                    sb2.append("; port is out of range");
                    throw new SocketException(sb2.toString());
                }
                if (proxy.type() == Proxy.Type.SOCKS) {
                    this.A02.add(InetSocketAddress.createUnresolved(str, i));
                } else {
                    List A3m = this.A07.A08.A3m(str);
                    int size = A3m.size();
                    for (int i3 = 0; i3 < size; i3++) {
                        this.A02.add(new InetSocketAddress((InetAddress) A3m.get(i3), i));
                    }
                }
                this.A00 = 0;
                this.A05 = proxy;
            } else {
                StringBuilder sb3 = new StringBuilder("No route to ");
                sb3.append(this.A07.A09.A02);
                sb3.append("; exhausted proxy configurations: ");
                sb3.append(this.A03);
                throw new SocketException(sb3.toString());
            }
        }
        if (this.A00 < this.A02.size()) {
            List list3 = this.A02;
            int i4 = this.A00;
            this.A00 = i4 + 1;
            InetSocketAddress inetSocketAddress2 = (InetSocketAddress) list3.get(i4);
            this.A04 = inetSocketAddress2;
            C0556bt btVar = new C0556bt(this.A07, this.A05, inetSocketAddress2);
            c5 c5Var = this.A08;
            synchronized (c5Var) {
                contains = c5Var.A00.contains(btVar);
            }
            if (!contains) {
                return btVar;
            }
            this.A06.add(btVar);
            return A00();
        }
        StringBuilder sb4 = new StringBuilder("No route to ");
        sb4.append(this.A07.A09.A02);
        sb4.append("; exhausted inet socket addresses: ");
        sb4.append(this.A02);
        throw new SocketException(sb4.toString());
    }

    public C0569c7(C0523bM bMVar, c5 c5Var) {
        List A052;
        this.A07 = bMVar;
        this.A08 = c5Var;
        List<Proxy> select = bMVar.A00.select(bMVar.A09.A0A());
        if (select == null || select.isEmpty()) {
            A052 = C0561by.A05(Proxy.NO_PROXY);
        } else {
            A052 = Collections.unmodifiableList(new ArrayList(select));
        }
        this.A03 = A052;
        this.A01 = 0;
    }
}
