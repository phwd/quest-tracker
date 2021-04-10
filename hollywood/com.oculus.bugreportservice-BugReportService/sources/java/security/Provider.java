package java.security;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import sun.security.util.Debug;

public abstract class Provider extends Properties {
    private static final int ALIAS_LENGTH = 10;
    private static final Debug debug = Debug.getInstance("provider", "Provider");
    private static final Map knownEngines = new HashMap();
    private static volatile ServiceKey previousKey = new ServiceKey("", "", false);
    static final long serialVersionUID = -4298000515446427739L;
    private transient Set entrySet = null;
    private transient int entrySetCallCount = ALIAS_LENGTH;
    private String info;
    private transient boolean initialized;
    private transient boolean legacyChanged;
    private transient Map legacyMap;
    private transient Map legacyStrings;
    private String name;
    private volatile boolean registered = false;
    private transient Map serviceMap;
    private transient Set serviceSet;
    private transient boolean servicesChanged;
    private double version;

    static {
        addEngine("AlgorithmParameterGenerator", false, null);
        addEngine("AlgorithmParameters", false, null);
        addEngine("KeyFactory", false, null);
        addEngine("KeyPairGenerator", false, null);
        addEngine("KeyStore", false, null);
        addEngine("MessageDigest", false, null);
        addEngine("SecureRandom", false, null);
        addEngine("Signature", true, null);
        addEngine("CertificateFactory", false, null);
        addEngine("CertPathBuilder", false, null);
        addEngine("CertPathValidator", false, null);
        addEngine("CertStore", false, "java.security.cert.CertStoreParameters");
        addEngine("Cipher", true, null);
        addEngine("ExemptionMechanism", false, null);
        addEngine("Mac", true, null);
        addEngine("KeyAgreement", true, null);
        addEngine("KeyGenerator", false, null);
        addEngine("SecretKeyFactory", false, null);
        addEngine("KeyManagerFactory", false, null);
        addEngine("SSLContext", false, null);
        addEngine("TrustManagerFactory", false, null);
        addEngine("GssApiMechanism", false, null);
        addEngine("SaslClientFactory", false, null);
        addEngine("SaslServerFactory", false, null);
        addEngine("Policy", false, "java.security.Policy$Parameters");
        addEngine("Configuration", false, "javax.security.auth.login.Configuration$Parameters");
        addEngine("XMLSignatureFactory", false, null);
        addEngine("KeyInfoFactory", false, null);
        addEngine("TransformService", false, null);
        addEngine("TerminalFactory", false, "java.lang.Object");
    }

    protected Provider(String str, double d, String str2) {
        this.name = str;
        this.version = d;
        this.info = str2;
        putId();
        this.initialized = true;
    }

    public String getName() {
        return this.name;
    }

    @Override // java.util.Hashtable
    public String toString() {
        return this.name + " version " + this.version;
    }

    @Override // java.util.Map, java.util.Hashtable
    public synchronized void clear() {
        check("clearProviderProperties." + this.name);
        if (debug != null) {
            Debug debug2 = debug;
            debug2.println("Remove " + this.name + " provider properties");
        }
        implClear();
    }

    @Override // java.util.Properties
    public synchronized void load(InputStream inputStream) {
        check("putProviderProperty." + this.name);
        if (debug != null) {
            Debug debug2 = debug;
            debug2.println("Load " + this.name + " provider properties");
        }
        Properties properties = new Properties();
        properties.load(inputStream);
        implPutAll(properties);
    }

    @Override // java.util.Map, java.util.Hashtable
    public synchronized void putAll(Map map) {
        check("putProviderProperty." + this.name);
        if (debug != null) {
            Debug debug2 = debug;
            debug2.println("Put all " + this.name + " provider properties");
        }
        implPutAll(map);
    }

    @Override // java.util.Map, java.util.Hashtable
    public synchronized Set entrySet() {
        checkInitialized();
        if (this.entrySet == null) {
            int i = this.entrySetCallCount;
            this.entrySetCallCount = i + 1;
            if (i == 0) {
                this.entrySet = Collections.unmodifiableMap(this).entrySet();
            } else {
                return super.entrySet();
            }
        }
        if (this.entrySetCallCount == 2) {
            return this.entrySet;
        }
        throw new RuntimeException("Internal error.");
    }

    @Override // java.util.Map, java.util.Hashtable
    public Set keySet() {
        checkInitialized();
        return Collections.unmodifiableSet(super.keySet());
    }

    @Override // java.util.Map, java.util.Hashtable
    public Collection values() {
        checkInitialized();
        return Collections.unmodifiableCollection(super.values());
    }

    @Override // java.util.Map, java.util.Hashtable
    public synchronized Object put(Object obj, Object obj2) {
        check("putProviderProperty." + this.name);
        if (debug != null) {
            Debug debug2 = debug;
            debug2.println("Set " + this.name + " provider property [" + obj + "/" + obj2 + "]");
        }
        return implPut(obj, obj2);
    }

    @Override // java.util.Map, java.util.Hashtable
    public synchronized Object putIfAbsent(Object obj, Object obj2) {
        check("putProviderProperty." + this.name);
        if (debug != null) {
            Debug debug2 = debug;
            debug2.println("Set " + this.name + " provider property [" + obj + "/" + obj2 + "]");
        }
        return implPutIfAbsent(obj, obj2);
    }

    @Override // java.util.Map, java.util.Hashtable
    public synchronized Object remove(Object obj) {
        check("removeProviderProperty." + this.name);
        if (debug != null) {
            Debug debug2 = debug;
            debug2.println("Remove " + this.name + " provider property " + obj);
        }
        return implRemove(obj);
    }

    @Override // java.util.Map, java.util.Hashtable
    public synchronized boolean remove(Object obj, Object obj2) {
        check("removeProviderProperty." + this.name);
        if (debug != null) {
            Debug debug2 = debug;
            debug2.println("Remove " + this.name + " provider property " + obj);
        }
        return implRemove(obj, obj2);
    }

    @Override // java.util.Map, java.util.Hashtable
    public Object get(Object obj) {
        checkInitialized();
        return super.get(obj);
    }

    @Override // java.util.Hashtable
    public Enumeration keys() {
        checkInitialized();
        return super.keys();
    }

    @Override // java.util.Properties
    public String getProperty(String str) {
        checkInitialized();
        return super.getProperty(str);
    }

    private void checkInitialized() {
        if (!this.initialized) {
            throw new IllegalStateException();
        }
    }

    private void check(String str) {
        checkInitialized();
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkSecurityAccess(str);
            throw null;
        }
    }

    private void putId() {
        super.put("Provider.id name", String.valueOf(this.name));
        super.put("Provider.id version", String.valueOf(this.version));
        super.put("Provider.id info", String.valueOf(this.info));
        super.put("Provider.id className", getClass().getName());
    }

    private void readObject(ObjectInputStream objectInputStream) {
        this.registered = false;
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : super.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue());
        }
        this.defaults = null;
        objectInputStream.defaultReadObject();
        throw null;
    }

    private boolean checkLegacy(Object obj) {
        if (this.registered) {
            Security.increaseVersion();
        }
        if (((String) obj).startsWith("Provider.")) {
            return false;
        }
        this.legacyChanged = true;
        if (this.legacyStrings == null) {
            this.legacyStrings = new LinkedHashMap();
        }
        return true;
    }

    private void implPutAll(Map map) {
        for (Map.Entry entry : map.entrySet()) {
            implPut(entry.getKey(), entry.getValue());
        }
        if (this.registered) {
            Security.increaseVersion();
        }
    }

    private Object implRemove(Object obj) {
        if (obj instanceof String) {
            if (!checkLegacy(obj)) {
                return null;
            }
            this.legacyStrings.remove((String) obj);
        }
        return super.remove(obj);
    }

    private boolean implRemove(Object obj, Object obj2) {
        if ((obj instanceof String) && (obj2 instanceof String)) {
            if (!checkLegacy(obj)) {
                return false;
            }
            this.legacyStrings.remove((String) obj, obj2);
        }
        return super.remove(obj, obj2);
    }

    private Object implPut(Object obj, Object obj2) {
        if ((obj instanceof String) && (obj2 instanceof String)) {
            if (!checkLegacy(obj)) {
                return null;
            }
            this.legacyStrings.put((String) obj, (String) obj2);
        }
        return super.put(obj, obj2);
    }

    private Object implPutIfAbsent(Object obj, Object obj2) {
        if ((obj instanceof String) && (obj2 instanceof String)) {
            if (!checkLegacy(obj)) {
                return null;
            }
            this.legacyStrings.putIfAbsent((String) obj, (String) obj2);
        }
        return super.putIfAbsent(obj, obj2);
    }

    private void implClear() {
        Map map = this.legacyStrings;
        if (map != null) {
            map.clear();
        }
        Map map2 = this.legacyMap;
        if (map2 != null) {
            map2.clear();
        }
        Map map3 = this.serviceMap;
        if (map3 != null) {
            map3.clear();
        }
        this.legacyChanged = false;
        this.servicesChanged = false;
        this.serviceSet = null;
        super.clear();
        putId();
        if (this.registered) {
            Security.increaseVersion();
        }
    }

    /* access modifiers changed from: private */
    public static class ServiceKey {
        private final String algorithm;
        private final String originalAlgorithm;
        private final String type;

        private ServiceKey(String str, String str2, boolean z) {
            this.type = str;
            this.originalAlgorithm = str2;
            String upperCase = str2.toUpperCase(Locale.ENGLISH);
            this.algorithm = z ? upperCase.intern() : upperCase;
        }

        public int hashCode() {
            return this.type.hashCode() + this.algorithm.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ServiceKey)) {
                return false;
            }
            ServiceKey serviceKey = (ServiceKey) obj;
            return this.type.equals(serviceKey.type) && this.algorithm.equals(serviceKey.algorithm);
        }

        /* access modifiers changed from: package-private */
        public boolean matches(String str, String str2) {
            return this.type == str && this.originalAlgorithm == str2;
        }
    }

    private void ensureLegacyParsed() {
        if (this.legacyChanged && this.legacyStrings != null) {
            this.serviceSet = null;
            Map map = this.legacyMap;
            if (map == null) {
                this.legacyMap = new LinkedHashMap();
            } else {
                map.clear();
            }
            for (Map.Entry entry : this.legacyStrings.entrySet()) {
                parseLegacyPut((String) entry.getKey(), (String) entry.getValue());
            }
            removeInvalidServices(this.legacyMap);
            this.legacyChanged = false;
        }
    }

    private void removeInvalidServices(Map map) {
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            if (!((Service) ((Map.Entry) it.next()).getValue()).isValid()) {
                it.remove();
            }
        }
    }

    private String[] getTypeAndAlgorithm(String str) {
        int indexOf = str.indexOf(".");
        if (indexOf < 1) {
            Debug debug2 = debug;
            if (debug2 == null) {
                return null;
            }
            debug2.println("Ignoring invalid entry in provider " + this.name + ":" + str);
            return null;
        }
        return new String[]{str.substring(ALIAS_LENGTH, indexOf), str.substring(indexOf + 1)};
    }

    private void parseLegacyPut(String str, String str2) {
        if (str.toLowerCase(Locale.ENGLISH).startsWith("alg.alias.")) {
            String[] typeAndAlgorithm = getTypeAndAlgorithm(str.substring(ALIAS_LENGTH));
            if (typeAndAlgorithm != null) {
                String engineName = getEngineName(typeAndAlgorithm[ALIAS_LENGTH]);
                String intern = typeAndAlgorithm[1].intern();
                ServiceKey serviceKey = new ServiceKey(engineName, str2, true);
                Service service = (Service) this.legacyMap.get(serviceKey);
                if (service == null) {
                    service = new Service();
                    service.type = engineName;
                    service.algorithm = str2;
                    this.legacyMap.put(serviceKey, service);
                }
                this.legacyMap.put(new ServiceKey(engineName, intern, true), service);
                service.addAlias(intern);
                return;
            }
            return;
        }
        String[] typeAndAlgorithm2 = getTypeAndAlgorithm(str);
        if (typeAndAlgorithm2 != null) {
            int indexOf = typeAndAlgorithm2[1].indexOf(32);
            if (indexOf == -1) {
                String engineName2 = getEngineName(typeAndAlgorithm2[ALIAS_LENGTH]);
                String intern2 = typeAndAlgorithm2[1].intern();
                ServiceKey serviceKey2 = new ServiceKey(engineName2, intern2, true);
                Service service2 = (Service) this.legacyMap.get(serviceKey2);
                if (service2 == null) {
                    service2 = new Service();
                    service2.type = engineName2;
                    service2.algorithm = intern2;
                    this.legacyMap.put(serviceKey2, service2);
                }
                service2.className = str2;
                return;
            }
            String engineName3 = getEngineName(typeAndAlgorithm2[ALIAS_LENGTH]);
            String str3 = typeAndAlgorithm2[1];
            String intern3 = str3.substring(ALIAS_LENGTH, indexOf).intern();
            String substring = str3.substring(indexOf + 1);
            while (substring.startsWith(" ")) {
                substring = substring.substring(1);
            }
            String intern4 = substring.intern();
            ServiceKey serviceKey3 = new ServiceKey(engineName3, intern3, true);
            Service service3 = (Service) this.legacyMap.get(serviceKey3);
            if (service3 == null) {
                service3 = new Service();
                service3.type = engineName3;
                service3.algorithm = intern3;
                this.legacyMap.put(serviceKey3, service3);
            }
            service3.addAttribute(intern4, str2);
        }
    }

    public synchronized Service getService(String str, String str2) {
        Service service;
        checkInitialized();
        ServiceKey serviceKey = previousKey;
        Service service2 = null;
        if (!serviceKey.matches(str, str2)) {
            serviceKey = new ServiceKey(str, str2, false);
            previousKey = serviceKey;
        }
        if (this.serviceMap != null && (service = (Service) this.serviceMap.get(serviceKey)) != null) {
            return service;
        }
        ensureLegacyParsed();
        if (this.legacyMap != null) {
            service2 = (Service) this.legacyMap.get(serviceKey);
        }
        return service2;
    }

    public synchronized Set getServices() {
        checkInitialized();
        if (this.legacyChanged || this.servicesChanged) {
            this.serviceSet = null;
        }
        if (this.serviceSet == null) {
            ensureLegacyParsed();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            if (this.serviceMap != null) {
                linkedHashSet.addAll(this.serviceMap.values());
            }
            if (this.legacyMap != null) {
                linkedHashSet.addAll(this.legacyMap.values());
            }
            this.serviceSet = Collections.unmodifiableSet(linkedHashSet);
            this.servicesChanged = false;
        }
        return this.serviceSet;
    }

    /* access modifiers changed from: private */
    public static class UString {
        final String lowerString;
        final String string;

        UString(String str) {
            this.string = str;
            this.lowerString = str.toLowerCase(Locale.ENGLISH);
        }

        public int hashCode() {
            return this.lowerString.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof UString)) {
                return false;
            }
            return this.lowerString.equals(((UString) obj).lowerString);
        }

        public String toString() {
            return this.string;
        }
    }

    /* access modifiers changed from: private */
    public static class EngineDescription {
        private volatile Class constructorParameterClass;
        final String constructorParameterClassName;
        final String name;
        final boolean supportsParameter;

        EngineDescription(String str, boolean z, String str2) {
            this.name = str;
            this.supportsParameter = z;
            this.constructorParameterClassName = str2;
        }

        /* access modifiers changed from: package-private */
        public Class getConstructorParameterClass() {
            Class cls = this.constructorParameterClass;
            if (cls != null) {
                return cls;
            }
            Class cls2 = Class.forName(this.constructorParameterClassName);
            this.constructorParameterClass = cls2;
            return cls2;
        }
    }

    private static void addEngine(String str, boolean z, String str2) {
        EngineDescription engineDescription = new EngineDescription(str, z, str2);
        knownEngines.put(str.toLowerCase(Locale.ENGLISH), engineDescription);
        knownEngines.put(str, engineDescription);
    }

    private static String getEngineName(String str) {
        EngineDescription engineDescription = (EngineDescription) knownEngines.get(str);
        if (engineDescription == null) {
            engineDescription = (EngineDescription) knownEngines.get(str.toLowerCase(Locale.ENGLISH));
        }
        return engineDescription == null ? str : engineDescription.name;
    }

    public static class Service {
        private static final Class[] CLASS0 = new Class[Provider.ALIAS_LENGTH];
        private String algorithm;
        private List aliases;
        private Map attributes;
        private String className;
        private volatile Reference classRef;
        private volatile Boolean hasKeyAttributes;
        private final Provider provider;
        private boolean registered;
        private Class[] supportedClasses;
        private String[] supportedFormats;
        private String type;

        private Service(Provider provider2) {
            this.provider = provider2;
            this.aliases = Collections.emptyList();
            this.attributes = Collections.emptyMap();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private boolean isValid() {
            return (this.type == null || this.algorithm == null || this.className == null) ? false : true;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void addAlias(String str) {
            if (this.aliases.isEmpty()) {
                this.aliases = new ArrayList(2);
            }
            this.aliases.add(str);
        }

        /* access modifiers changed from: package-private */
        public void addAttribute(String str, String str2) {
            if (this.attributes.isEmpty()) {
                this.attributes = new HashMap(8);
            }
            this.attributes.put(new UString(str), str2);
        }

        public final String getType() {
            return this.type;
        }

        public final String getAlgorithm() {
            return this.algorithm;
        }

        public final Provider getProvider() {
            return this.provider;
        }

        public final String getClassName() {
            return this.className;
        }

        public final String getAttribute(String str) {
            if (str != null) {
                return (String) this.attributes.get(new UString(str));
            }
            throw new NullPointerException();
        }

        public Object newInstance(Object obj) {
            if (!this.registered) {
                if (this.provider.getService(this.type, this.algorithm) == this) {
                    this.registered = true;
                } else {
                    throw new NoSuchAlgorithmException("Service not registered with Provider " + this.provider.getName() + ": " + this);
                }
            }
            try {
                EngineDescription engineDescription = (EngineDescription) Provider.knownEngines.get(this.type);
                if (engineDescription == null) {
                    return newInstanceGeneric(obj);
                }
                if (engineDescription.constructorParameterClassName != null) {
                    Class constructorParameterClass = engineDescription.getConstructorParameterClass();
                    if (obj != null) {
                        if (!constructorParameterClass.isAssignableFrom(obj.getClass())) {
                            throw new InvalidParameterException("constructorParameter must be instanceof " + engineDescription.constructorParameterClassName.replace('$', '.') + " for engine type " + this.type);
                        }
                    }
                    return getImplClass().getConstructor(constructorParameterClass).newInstance(obj);
                } else if (obj == null) {
                    return getImplClass().getConstructor(new Class[Provider.ALIAS_LENGTH]).newInstance(new Object[Provider.ALIAS_LENGTH]);
                } else {
                    throw new InvalidParameterException("constructorParameter not used with " + this.type + " engines");
                }
            } catch (NoSuchAlgorithmException e) {
                throw e;
            } catch (InvocationTargetException e2) {
                throw new NoSuchAlgorithmException("Error constructing implementation (algorithm: " + this.algorithm + ", provider: " + this.provider.getName() + ", class: " + this.className + ")", e2.getCause());
            } catch (Exception e3) {
                throw new NoSuchAlgorithmException("Error constructing implementation (algorithm: " + this.algorithm + ", provider: " + this.provider.getName() + ", class: " + this.className + ")", e3);
            }
        }

        private Class getImplClass() {
            Class cls;
            try {
                Reference reference = this.classRef;
                if (reference == null) {
                    cls = null;
                } else {
                    cls = (Class) reference.get();
                }
                if (cls == null) {
                    ClassLoader classLoader = this.provider.getClass().getClassLoader();
                    if (classLoader == null) {
                        cls = Class.forName(this.className);
                    } else {
                        cls = classLoader.loadClass(this.className);
                    }
                    if (Modifier.isPublic(cls.getModifiers())) {
                        this.classRef = new WeakReference(cls);
                    } else {
                        throw new NoSuchAlgorithmException("class configured for " + this.type + " (provider: " + this.provider.getName() + ") is not public.");
                    }
                }
                return cls;
            } catch (ClassNotFoundException e) {
                throw new NoSuchAlgorithmException("class configured for " + this.type + " (provider: " + this.provider.getName() + ") cannot be found.", e);
            }
        }

        private Object newInstanceGeneric(Object obj) {
            Class implClass = getImplClass();
            if (obj == null) {
                try {
                    return implClass.getConstructor(new Class[Provider.ALIAS_LENGTH]).newInstance(new Object[Provider.ALIAS_LENGTH]);
                } catch (NoSuchMethodException unused) {
                    throw new NoSuchAlgorithmException("No public no-arg constructor found in class " + this.className);
                }
            } else {
                Class cls = obj.getClass();
                Constructor[] constructors = implClass.getConstructors();
                int length = constructors.length;
                for (int i = Provider.ALIAS_LENGTH; i < length; i++) {
                    Constructor constructor = constructors[i];
                    Class[] parameterTypes = constructor.getParameterTypes();
                    if (parameterTypes.length == 1 && parameterTypes[Provider.ALIAS_LENGTH].isAssignableFrom(cls)) {
                        return constructor.newInstance(obj);
                    }
                }
                throw new NoSuchAlgorithmException("No public constructor matching " + cls.getName() + " found in class " + this.className);
            }
        }

        public boolean supportsParameter(Object obj) {
            EngineDescription engineDescription = (EngineDescription) Provider.knownEngines.get(this.type);
            if (engineDescription == null) {
                return true;
            }
            if (!engineDescription.supportsParameter) {
                throw new InvalidParameterException("supportsParameter() not used with " + this.type + " engines");
            } else if (obj != null && !(obj instanceof Key)) {
                throw new InvalidParameterException("Parameter must be instanceof Key for engine " + this.type);
            } else if (!hasKeyAttributes()) {
                return true;
            } else {
                if (obj == null) {
                    return false;
                }
                Key key = (Key) obj;
                if (!supportsKeyFormat(key) && !supportsKeyClass(key)) {
                    return false;
                }
                return true;
            }
        }

        private boolean hasKeyAttributes() {
            Boolean bool = this.hasKeyAttributes;
            if (bool == null) {
                synchronized (this) {
                    String attribute = getAttribute("SupportedKeyFormats");
                    if (attribute != null) {
                        this.supportedFormats = attribute.split("\\|");
                    }
                    String attribute2 = getAttribute("SupportedKeyClasses");
                    boolean z = false;
                    if (attribute2 != null) {
                        String[] split = attribute2.split("\\|");
                        ArrayList arrayList = new ArrayList(split.length);
                        int length = split.length;
                        for (int i = Provider.ALIAS_LENGTH; i < length; i++) {
                            Class keyClass = getKeyClass(split[i]);
                            if (keyClass != null) {
                                arrayList.add(keyClass);
                            }
                        }
                        this.supportedClasses = (Class[]) arrayList.toArray(CLASS0);
                    }
                    if (!(this.supportedFormats == null && this.supportedClasses == null)) {
                        z = true;
                    }
                    bool = Boolean.valueOf(z);
                    this.hasKeyAttributes = bool;
                }
            }
            return bool.booleanValue();
        }

        /* JADX DEBUG: Failed to insert an additional move for type inference into block B:0:0x0000 */
        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.security.Provider$Service */
        /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: java.security.Provider$Service */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Class] */
        /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
            return null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:4:?, code lost:
            r0 = r0.provider.getClass().getClassLoader();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:5:0x000f, code lost:
            if (r0 != null) goto L_0x0011;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0015, code lost:
            return r0.loadClass(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
            return null;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0005 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.Class getKeyClass(java.lang.String r1) {
            /*
                r0 = this;
                java.lang.Class r0 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException -> 0x0005 }
                return r0
            L_0x0005:
                java.security.Provider r0 = r0.provider     // Catch:{ ClassNotFoundException -> 0x0016 }
                java.lang.Class r0 = r0.getClass()     // Catch:{ ClassNotFoundException -> 0x0016 }
                java.lang.ClassLoader r0 = r0.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x0016 }
                if (r0 == 0) goto L_0x0016
                java.lang.Class r0 = r0.loadClass(r1)     // Catch:{ ClassNotFoundException -> 0x0016 }
                return r0
            L_0x0016:
                r0 = 0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: java.security.Provider.Service.getKeyClass(java.lang.String):java.lang.Class");
        }

        private boolean supportsKeyFormat(Key key) {
            String format;
            if (this.supportedFormats == null || (format = key.getFormat()) == null) {
                return false;
            }
            String[] strArr = this.supportedFormats;
            int length = strArr.length;
            for (int i = Provider.ALIAS_LENGTH; i < length; i++) {
                if (strArr[i].equals(format)) {
                    return true;
                }
            }
            return false;
        }

        private boolean supportsKeyClass(Key key) {
            if (this.supportedClasses == null) {
                return false;
            }
            Class cls = key.getClass();
            Class[] clsArr = this.supportedClasses;
            int length = clsArr.length;
            for (int i = Provider.ALIAS_LENGTH; i < length; i++) {
                if (clsArr[i].isAssignableFrom(cls)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            String str;
            String str2 = "";
            if (this.aliases.isEmpty()) {
                str = str2;
            } else {
                str = "\r\n  aliases: " + this.aliases.toString();
            }
            if (!this.attributes.isEmpty()) {
                str2 = "\r\n  attributes: " + this.attributes.toString();
            }
            return this.provider.getName() + ": " + this.type + "." + this.algorithm + " -> " + this.className + str + str2 + "\r\n";
        }
    }
}
