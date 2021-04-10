package sun.security.jca;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.util.Iterator;
import java.util.List;

public class GetInstance {

    public static final class Instance {
        public final Object impl;
        public final Provider provider;

        private Instance(Provider provider2, Object obj) {
            this.provider = provider2;
            this.impl = obj;
        }

        public Object[] toArray() {
            return new Object[]{this.impl, this.provider};
        }
    }

    public static Provider.Service getService(String str, String str2, String str3) {
        if (str3 == null || str3.length() == 0) {
            throw new IllegalArgumentException("missing provider");
        }
        Provider provider = Providers.getProviderList().getProvider(str3);
        if (provider != null) {
            Provider.Service service = provider.getService(str, str2);
            if (service != null) {
                return service;
            }
            throw new NoSuchAlgorithmException("no such algorithm: " + str2 + " for provider " + str3);
        }
        throw new NoSuchProviderException("no such provider: " + str3);
    }

    public static Provider.Service getService(String str, String str2, Provider provider) {
        if (provider != null) {
            Provider.Service service = provider.getService(str, str2);
            if (service != null) {
                return service;
            }
            throw new NoSuchAlgorithmException("no such algorithm: " + str2 + " for provider " + provider.getName());
        }
        throw new IllegalArgumentException("missing provider");
    }

    public static List getServices(String str, String str2) {
        return Providers.getProviderList().getServices(str, str2);
    }

    public static List getServices(List list) {
        return Providers.getProviderList().getServices(list);
    }

    public static Instance getInstance(String str, Class cls, String str2) {
        ProviderList providerList = Providers.getProviderList();
        Provider.Service service = providerList.getService(str, str2);
        if (service != null) {
            try {
                return getInstance(service, cls);
            } catch (NoSuchAlgorithmException e) {
                e = e;
                Iterator it = providerList.getServices(str, str2).iterator();
                while (it.hasNext()) {
                    Provider.Service service2 = (Provider.Service) it.next();
                    if (service2 != service) {
                        try {
                            return getInstance(service2, cls);
                        } catch (NoSuchAlgorithmException e2) {
                            e = e2;
                        }
                    }
                }
                throw e;
            }
        } else {
            throw new NoSuchAlgorithmException(str2 + " " + str + " not available");
        }
    }

    public static Instance getInstance(String str, Class cls, String str2, String str3) {
        return getInstance(getService(str, str2, str3), cls);
    }

    public static Instance getInstance(String str, Class cls, String str2, Provider provider) {
        return getInstance(getService(str, str2, provider), cls);
    }

    public static Instance getInstance(Provider.Service service, Class cls) {
        Object newInstance = service.newInstance(null);
        checkSuperClass(service, newInstance.getClass(), cls);
        return new Instance(service.getProvider(), newInstance);
    }

    public static void checkSuperClass(Provider.Service service, Class cls, Class cls2) {
        if (cls2 != null && !cls2.isAssignableFrom(cls)) {
            throw new NoSuchAlgorithmException("class configured for " + service.getType() + ": " + service.getClassName() + " not a " + service.getType());
        }
    }
}
