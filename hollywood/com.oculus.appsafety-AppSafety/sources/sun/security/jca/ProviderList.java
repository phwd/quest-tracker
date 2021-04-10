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
        public Provider.Service getService(String type, String algorithm) {
            return null;
        }
    };
    private static final Provider[] P0 = new Provider[0];
    private static final ProviderConfig[] PC0 = new ProviderConfig[0];
    static final Debug debug = Debug.getInstance("jca", "ProviderList");
    private volatile boolean allLoaded;
    private final ProviderConfig[] configs;
    private final List<Provider> userList;

    static ProviderList fromSecurityProperties() {
        return (ProviderList) AccessController.doPrivileged(new PrivilegedAction<ProviderList>() {
            /* class sun.security.jca.ProviderList.AnonymousClass2 */

            @Override // java.security.PrivilegedAction
            public ProviderList run() {
                return new ProviderList();
            }
        });
    }

    public static ProviderList add(ProviderList providerList, Provider p) {
        return insertAt(providerList, p, -1);
    }

    public static ProviderList insertAt(ProviderList providerList, Provider p, int position) {
        if (providerList.getProvider(p.getName()) != null) {
            return providerList;
        }
        List<ProviderConfig> list = new ArrayList<>(Arrays.asList(providerList.configs));
        int n = list.size();
        if (position < 0 || position > n) {
            position = n;
        }
        list.add(position, new ProviderConfig(p));
        return new ProviderList((ProviderConfig[]) list.toArray(PC0), true);
    }

    public static ProviderList remove(ProviderList providerList, String name) {
        if (providerList.getProvider(name) == null) {
            return providerList;
        }
        ProviderConfig[] configs2 = new ProviderConfig[(providerList.size() - 1)];
        int j = 0;
        ProviderConfig[] providerConfigArr = providerList.configs;
        for (ProviderConfig config : providerConfigArr) {
            if (!config.getProvider().getName().equals(name)) {
                configs2[j] = config;
                j++;
            }
        }
        return new ProviderList(configs2, true);
    }

    public static ProviderList newList(Provider... providers) {
        ProviderConfig[] configs2 = new ProviderConfig[providers.length];
        for (int i = 0; i < providers.length; i++) {
            configs2[i] = new ProviderConfig(providers[i]);
        }
        return new ProviderList(configs2, true);
    }

    private ProviderList(ProviderConfig[] configs2, boolean allLoaded2) {
        this.userList = new AbstractList<Provider>() {
            /* class sun.security.jca.ProviderList.AnonymousClass3 */

            @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
            public int size() {
                return ProviderList.this.configs.length;
            }

            @Override // java.util.List, java.util.AbstractList
            public Provider get(int index) {
                return ProviderList.this.getProvider(index);
            }
        };
        this.configs = configs2;
        this.allLoaded = allLoaded2;
    }

    private ProviderList() {
        ProviderConfig config;
        this.userList = new AbstractList<Provider>() {
            /* class sun.security.jca.ProviderList.AnonymousClass3 */

            @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
            public int size() {
                return ProviderList.this.configs.length;
            }

            @Override // java.util.List, java.util.AbstractList
            public Provider get(int index) {
                return ProviderList.this.getProvider(index);
            }
        };
        List<ProviderConfig> configList = new ArrayList<>();
        int i = 1;
        while (true) {
            String entry = Security.getProperty("security.provider." + i);
            if (entry == null) {
                break;
            }
            String entry2 = entry.trim();
            if (entry2.length() == 0) {
                PrintStream printStream = System.err;
                printStream.println("invalid entry for security.provider." + i);
                break;
            }
            int k = entry2.indexOf(32);
            if (k == -1) {
                config = new ProviderConfig(entry2);
            } else {
                config = new ProviderConfig(entry2.substring(0, k), entry2.substring(k + 1).trim());
            }
            if (!configList.contains(config)) {
                configList.add(config);
            }
            i++;
        }
        this.configs = (ProviderConfig[]) configList.toArray(PC0);
        Debug debug2 = debug;
        if (debug2 != null) {
            debug2.println("provider configuration: " + ((Object) configList));
        }
    }

    /* access modifiers changed from: package-private */
    public ProviderList getJarList(String[] jarClassNames) {
        List<ProviderConfig> newConfigs = new ArrayList<>();
        for (String className : jarClassNames) {
            ProviderConfig newConfig = new ProviderConfig(className);
            ProviderConfig[] providerConfigArr = this.configs;
            int length = providerConfigArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                ProviderConfig config = providerConfigArr[i];
                if (config.equals(newConfig)) {
                    newConfig = config;
                    break;
                }
                i++;
            }
            newConfigs.add(newConfig);
        }
        return new ProviderList((ProviderConfig[]) newConfigs.toArray(PC0), false);
    }

    public int size() {
        return this.configs.length;
    }

    /* access modifiers changed from: package-private */
    public Provider getProvider(int index) {
        Provider p = this.configs[index].getProvider();
        return p != null ? p : EMPTY_PROVIDER;
    }

    public List<Provider> providers() {
        return this.userList;
    }

    private ProviderConfig getProviderConfig(String name) {
        int index = getIndex(name);
        if (index != -1) {
            return this.configs[index];
        }
        return null;
    }

    public Provider getProvider(String name) {
        ProviderConfig config = getProviderConfig(name);
        if (config == null) {
            return null;
        }
        return config.getProvider();
    }

    public int getIndex(String name) {
        for (int i = 0; i < this.configs.length; i++) {
            if (getProvider(i).getName().equals(name)) {
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
        int n = 0;
        int i = 0;
        while (true) {
            providerConfigArr = this.configs;
            if (i >= providerConfigArr.length) {
                break;
            }
            if (providerConfigArr[i].getProvider() != null) {
                n++;
            }
            i++;
        }
        if (n == providerConfigArr.length) {
            this.allLoaded = true;
        }
        return n;
    }

    /* access modifiers changed from: package-private */
    public ProviderList removeInvalid() {
        int n = loadAll();
        if (n == this.configs.length) {
            return this;
        }
        ProviderConfig[] newConfigs = new ProviderConfig[n];
        int i = 0;
        int j = 0;
        while (true) {
            ProviderConfig[] providerConfigArr = this.configs;
            if (i >= providerConfigArr.length) {
                return new ProviderList(newConfigs, true);
            }
            ProviderConfig config = providerConfigArr[i];
            if (config.isLoaded()) {
                newConfigs[j] = config;
                j++;
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

    public Provider.Service getService(String type, String name) {
        for (int i = 0; i < this.configs.length; i++) {
            Provider.Service s = getProvider(i).getService(type, name);
            if (s != null) {
                return s;
            }
        }
        return null;
    }

    public List<Provider.Service> getServices(String type, String algorithm) {
        return new ServiceList(type, algorithm);
    }

    @Deprecated
    public List<Provider.Service> getServices(String type, List<String> algorithms) {
        List<ServiceId> ids = new ArrayList<>();
        for (String alg : algorithms) {
            ids.add(new ServiceId(type, alg));
        }
        return getServices(ids);
    }

    public List<Provider.Service> getServices(List<ServiceId> ids) {
        return new ServiceList(ids);
    }

    /* access modifiers changed from: private */
    public final class ServiceList extends AbstractList<Provider.Service> {
        private final String algorithm;
        private Provider.Service firstService;
        private final List<ServiceId> ids;
        private int providerIndex;
        private List<Provider.Service> services;
        private final String type;

        ServiceList(String type2, String algorithm2) {
            this.type = type2;
            this.algorithm = algorithm2;
            this.ids = null;
        }

        ServiceList(List<ServiceId> ids2) {
            this.type = null;
            this.algorithm = null;
            this.ids = ids2;
        }

        private void addService(Provider.Service s) {
            if (this.firstService == null) {
                this.firstService = s;
                return;
            }
            if (this.services == null) {
                this.services = new ArrayList(4);
                this.services.add(this.firstService);
            }
            this.services.add(s);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private Provider.Service tryGet(int index) {
            while (true) {
                if (index == 0) {
                    Provider.Service service = this.firstService;
                    if (service != null) {
                        return service;
                    }
                }
                List<Provider.Service> list = this.services;
                if (list != null && list.size() > index) {
                    return this.services.get(index);
                }
                if (this.providerIndex >= ProviderList.this.configs.length) {
                    return null;
                }
                ProviderList providerList = ProviderList.this;
                int i = this.providerIndex;
                this.providerIndex = i + 1;
                Provider p = providerList.getProvider(i);
                String str = this.type;
                if (str != null) {
                    Provider.Service s = p.getService(str, this.algorithm);
                    if (s != null) {
                        addService(s);
                    }
                } else {
                    for (ServiceId id : this.ids) {
                        Provider.Service s2 = p.getService(id.type, id.algorithm);
                        if (s2 != null) {
                            addService(s2);
                        }
                    }
                }
            }
        }

        @Override // java.util.List, java.util.AbstractList
        public Provider.Service get(int index) {
            Provider.Service s = tryGet(index);
            if (s != null) {
                return s;
            }
            throw new IndexOutOfBoundsException();
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public int size() {
            int n;
            List<Provider.Service> list = this.services;
            if (list != null) {
                n = list.size();
            } else {
                n = this.firstService != null ? 1 : 0;
            }
            while (tryGet(n) != null) {
                n++;
            }
            return n;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection
        public boolean isEmpty() {
            return tryGet(0) == null;
        }

        @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.lang.Iterable
        public Iterator<Provider.Service> iterator() {
            return new Iterator<Provider.Service>() {
                /* class sun.security.jca.ProviderList.ServiceList.AnonymousClass1 */
                int index;

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return ServiceList.this.tryGet(this.index) != null;
                }

                @Override // java.util.Iterator
                public Provider.Service next() {
                    Provider.Service s = ServiceList.this.tryGet(this.index);
                    if (s != null) {
                        this.index++;
                        return s;
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
