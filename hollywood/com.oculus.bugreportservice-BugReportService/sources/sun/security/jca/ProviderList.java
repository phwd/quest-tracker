package sun.security.jca;

import java.io.PrintStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.security.Security;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import sun.security.util.Debug;

public final class ProviderList {
    static final ProviderList EMPTY = new ProviderList(PC0, true);
    private static final Provider EMPTY_PROVIDER = new Provider("##Empty##", 1.0d, "initialization in progress") {
        /* class sun.security.jca.ProviderList.AnonymousClass1 */
        private static final long serialVersionUID = 1151354171352296389L;

        @Override // java.security.Provider
        public Provider.Service getService(String str, String str2) {
            return null;
        }
    };
    private static final Provider[] P0 = new Provider[0];
    private static final ProviderConfig[] PC0 = new ProviderConfig[0];
    static final Debug debug = Debug.getInstance("jca", "ProviderList");
    private volatile boolean allLoaded;
    private final ProviderConfig[] configs;
    private final List userList;

    static ProviderList fromSecurityProperties() {
        return (ProviderList) AccessController.doPrivileged(new PrivilegedAction() {
            /* class sun.security.jca.ProviderList.AnonymousClass2 */

            @Override // java.security.PrivilegedAction
            public ProviderList run() {
                return new ProviderList();
            }
        });
    }

    private ProviderList(ProviderConfig[] providerConfigArr, boolean z) {
        this.userList = new AbstractList() {
            /* class sun.security.jca.ProviderList.AnonymousClass3 */

            @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
            public int size() {
                return ProviderList.this.configs.length;
            }

            @Override // java.util.List, java.util.AbstractList
            public Provider get(int i) {
                return ProviderList.this.getProvider(i);
            }
        };
        this.configs = providerConfigArr;
        this.allLoaded = z;
    }

    private ProviderList() {
        ProviderConfig providerConfig;
        this.userList = new AbstractList() {
            /* class sun.security.jca.ProviderList.AnonymousClass3 */

            @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
            public int size() {
                return ProviderList.this.configs.length;
            }

            @Override // java.util.List, java.util.AbstractList
            public Provider get(int i) {
                return ProviderList.this.getProvider(i);
            }
        };
        ArrayList arrayList = new ArrayList();
        int i = 1;
        while (true) {
            String property = Security.getProperty("security.provider." + i);
            if (property == null) {
                break;
            }
            String trim = property.trim();
            if (trim.length() == 0) {
                PrintStream printStream = System.err;
                printStream.println("invalid entry for security.provider." + i);
                break;
            }
            int indexOf = trim.indexOf(32);
            if (indexOf == -1) {
                providerConfig = new ProviderConfig(trim);
            } else {
                providerConfig = new ProviderConfig(trim.substring(0, indexOf), trim.substring(indexOf + 1).trim());
            }
            if (!arrayList.contains(providerConfig)) {
                arrayList.add(providerConfig);
            }
            i++;
        }
        this.configs = (ProviderConfig[]) arrayList.toArray(PC0);
        Debug debug2 = debug;
        if (debug2 != null) {
            debug2.println("provider configuration: " + arrayList);
        }
    }

    /* access modifiers changed from: package-private */
    public ProviderList getJarList(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            ProviderConfig providerConfig = new ProviderConfig(str);
            ProviderConfig[] providerConfigArr = this.configs;
            int length = providerConfigArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                ProviderConfig providerConfig2 = providerConfigArr[i];
                if (providerConfig2.equals(providerConfig)) {
                    providerConfig = providerConfig2;
                    break;
                }
                i++;
            }
            arrayList.add(providerConfig);
        }
        return new ProviderList((ProviderConfig[]) arrayList.toArray(PC0), false);
    }

    public int size() {
        return this.configs.length;
    }

    /* access modifiers changed from: package-private */
    public Provider getProvider(int i) {
        Provider provider = this.configs[i].getProvider();
        return provider != null ? provider : EMPTY_PROVIDER;
    }

    public List providers() {
        return this.userList;
    }

    private ProviderConfig getProviderConfig(String str) {
        int index = getIndex(str);
        if (index != -1) {
            return this.configs[index];
        }
        return null;
    }

    public Provider getProvider(String str) {
        ProviderConfig providerConfig = getProviderConfig(str);
        if (providerConfig == null) {
            return null;
        }
        return providerConfig.getProvider();
    }

    public int getIndex(String str) {
        for (int i = 0; i < this.configs.length; i++) {
            if (getProvider(i).getName().equals(str)) {
                return i;
            }
        }
        return -1;
    }

    private int loadAll() {
        ProviderConfig[] providerConfigArr;
        if (this.allLoaded) {
            return this.configs.length;
        }
        Debug debug2 = debug;
        if (debug2 != null) {
            debug2.println("Loading all providers");
            new Exception("Call trace").printStackTrace();
        }
        int i = 0;
        int i2 = 0;
        while (true) {
            providerConfigArr = this.configs;
            if (i >= providerConfigArr.length) {
                break;
            }
            if (providerConfigArr[i].getProvider() != null) {
                i2++;
            }
            i++;
        }
        if (i2 == providerConfigArr.length) {
            this.allLoaded = true;
        }
        return i2;
    }

    /* access modifiers changed from: package-private */
    public ProviderList removeInvalid() {
        int loadAll = loadAll();
        if (loadAll == this.configs.length) {
            return this;
        }
        ProviderConfig[] providerConfigArr = new ProviderConfig[loadAll];
        int i = 0;
        int i2 = 0;
        while (true) {
            ProviderConfig[] providerConfigArr2 = this.configs;
            if (i >= providerConfigArr2.length) {
                return new ProviderList(providerConfigArr, true);
            }
            ProviderConfig providerConfig = providerConfigArr2[i];
            if (providerConfig.isLoaded()) {
                providerConfigArr[i2] = providerConfig;
                i2++;
            }
            i++;
        }
    }

    public Provider[] toArray() {
        return (Provider[]) providers().toArray(P0);
    }

    public String toString() {
        return Arrays.asList(this.configs).toString();
    }

    public Provider.Service getService(String str, String str2) {
        for (int i = 0; i < this.configs.length; i++) {
            Provider.Service service = getProvider(i).getService(str, str2);
            if (service != null) {
                return service;
            }
        }
        return null;
    }

    public List getServices(String str, String str2) {
        return new ServiceList(str, str2);
    }

    public List getServices(List list) {
        return new ServiceList(list);
    }

    /* access modifiers changed from: private */
    public final class ServiceList extends AbstractList {
        private final String algorithm;
        private Provider.Service firstService;
        private final List ids;
        private int providerIndex;
        private List services;
        private final String type;

        ServiceList(String str, String str2) {
            this.type = str;
            this.algorithm = str2;
            this.ids = null;
        }

        ServiceList(List list) {
            this.type = null;
            this.algorithm = null;
            this.ids = list;
        }

        private void addService(Provider.Service service) {
            if (this.firstService == null) {
                this.firstService = service;
                return;
            }
            if (this.services == null) {
                this.services = new ArrayList(4);
                this.services.add(this.firstService);
            }
            this.services.add(service);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private Provider.Service tryGet(int i) {
            while (true) {
                if (i == 0) {
                    Provider.Service service = this.firstService;
                    if (service != null) {
                        return service;
                    }
                }
                List list = this.services;
                if (list != null && list.size() > i) {
                    return (Provider.Service) this.services.get(i);
                }
                if (this.providerIndex >= ProviderList.this.configs.length) {
                    return null;
                }
                ProviderList providerList = ProviderList.this;
                int i2 = this.providerIndex;
                this.providerIndex = i2 + 1;
                Provider provider = providerList.getProvider(i2);
                String str = this.type;
                if (str != null) {
                    Provider.Service service2 = provider.getService(str, this.algorithm);
                    if (service2 != null) {
                        addService(service2);
                    }
                } else {
                    for (ServiceId serviceId : this.ids) {
                        Provider.Service service3 = provider.getService(serviceId.type, serviceId.algorithm);
                        if (service3 != null) {
                            addService(service3);
                        }
                    }
                }
            }
        }

        @Override // java.util.List, java.util.AbstractList
        public Provider.Service get(int i) {
            Provider.Service tryGet = tryGet(i);
            if (tryGet != null) {
                return tryGet;
            }
            throw new IndexOutOfBoundsException();
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public int size() {
            int i;
            List list = this.services;
            if (list != null) {
                i = list.size();
            } else {
                i = this.firstService != null ? 1 : 0;
            }
            while (tryGet(i) != null) {
                i++;
            }
            return i;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public boolean isEmpty() {
            return tryGet(0) == null;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.lang.Iterable
        public Iterator iterator() {
            return new Iterator() {
                /* class sun.security.jca.ProviderList.ServiceList.AnonymousClass1 */
                int index;

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return ServiceList.this.tryGet(this.index) != null;
                }

                @Override // java.util.Iterator
                public Provider.Service next() {
                    Provider.Service tryGet = ServiceList.this.tryGet(this.index);
                    if (tryGet != null) {
                        this.index++;
                        return tryGet;
                    }
                    throw new NoSuchElementException();
                }

                @Override // java.util.Iterator
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
}
