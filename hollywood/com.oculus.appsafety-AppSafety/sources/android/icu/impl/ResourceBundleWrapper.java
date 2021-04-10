package android.icu.impl;

import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public final class ResourceBundleWrapper extends UResourceBundle {
    private static CacheBase<String, ResourceBundleWrapper, Loader> BUNDLE_CACHE = new SoftCache<String, ResourceBundleWrapper, Loader>() {
        /* class android.icu.impl.ResourceBundleWrapper.AnonymousClass1 */

        /* access modifiers changed from: protected */
        public ResourceBundleWrapper createInstance(String unusedKey, Loader loader) {
            return loader.load();
        }
    };
    private static final boolean DEBUG = ICUDebug.enabled("resourceBundleWrapper");
    private String baseName;
    private ResourceBundle bundle;
    private List<String> keys;
    private String localeID;

    /* access modifiers changed from: private */
    public static abstract class Loader {
        /* access modifiers changed from: package-private */
        public abstract ResourceBundleWrapper load();

        private Loader() {
        }
    }

    private ResourceBundleWrapper(ResourceBundle bundle2) {
        this.bundle = null;
        this.localeID = null;
        this.baseName = null;
        this.keys = null;
        this.bundle = bundle2;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.UResourceBundle, java.util.ResourceBundle
    public Object handleGetObject(String aKey) {
        ResourceBundleWrapper current = this;
        Object obj = null;
        while (true) {
            if (current == null) {
                break;
            }
            try {
                obj = current.bundle.getObject(aKey);
                break;
            } catch (MissingResourceException e) {
                current = (ResourceBundleWrapper) current.getParent();
            }
        }
        if (obj != null) {
            return obj;
        }
        throw new MissingResourceException("Can't find resource for bundle " + this.baseName + ", key " + aKey, getClass().getName(), aKey);
    }

    @Override // android.icu.util.UResourceBundle, java.util.ResourceBundle
    public Enumeration<String> getKeys() {
        return Collections.enumeration(this.keys);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void initKeysVector() {
        this.keys = new ArrayList();
        for (ResourceBundleWrapper current = this; current != null; current = (ResourceBundleWrapper) current.getParent()) {
            Enumeration<String> e = current.bundle.getKeys();
            while (e.hasMoreElements()) {
                String elem = e.nextElement();
                if (!this.keys.contains(elem)) {
                    this.keys.add(elem);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.UResourceBundle
    public String getLocaleID() {
        return this.localeID;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.UResourceBundle
    public String getBaseName() {
        return this.bundle.getClass().getName().replace('.', '/');
    }

    @Override // android.icu.util.UResourceBundle
    public ULocale getULocale() {
        return new ULocale(this.localeID);
    }

    @Override // android.icu.util.UResourceBundle
    public UResourceBundle getParent() {
        return (UResourceBundle) this.parent;
    }

    public static ResourceBundleWrapper getBundleInstance(String baseName2, String localeID2, ClassLoader root, boolean disableFallback) {
        ResourceBundleWrapper b;
        if (root == null) {
            root = ClassLoaderUtil.getClassLoader();
        }
        if (disableFallback) {
            b = instantiateBundle(baseName2, localeID2, null, root, disableFallback);
        } else {
            b = instantiateBundle(baseName2, localeID2, ULocale.getDefault().getBaseName(), root, disableFallback);
        }
        if (b != null) {
            return b;
        }
        String separator = "_";
        if (baseName2.indexOf(47) >= 0) {
            separator = "/";
        }
        throw new MissingResourceException("Could not find the bundle " + baseName2 + separator + localeID2, "", "");
    }

    /* access modifiers changed from: private */
    public static boolean localeIDStartsWithLangSubtag(String localeID2, String lang) {
        return localeID2.startsWith(lang) && (localeID2.length() == lang.length() || localeID2.charAt(lang.length()) == '_');
    }

    /* access modifiers changed from: private */
    public static ResourceBundleWrapper instantiateBundle(final String baseName2, final String localeID2, final String defaultID, final ClassLoader root, final boolean disableFallback) {
        final String name;
        String cacheKey;
        if (localeID2.isEmpty()) {
            name = baseName2;
        } else {
            name = baseName2 + '_' + localeID2;
        }
        if (disableFallback) {
            cacheKey = name;
        } else {
            cacheKey = name + '#' + defaultID;
        }
        return BUNDLE_CACHE.getInstance(cacheKey, new Loader() {
            /* class android.icu.impl.ResourceBundleWrapper.AnonymousClass2 */

            @Override // android.icu.impl.ResourceBundleWrapper.Loader
            public ResourceBundleWrapper load() {
                ResourceBundleWrapper parent = null;
                int i = String.this.lastIndexOf(95);
                boolean loadFromProperties = false;
                boolean parentIsRoot = false;
                if (i != -1) {
                    parent = ResourceBundleWrapper.instantiateBundle(baseName2, String.this.substring(0, i), defaultID, root, disableFallback);
                } else if (!String.this.isEmpty()) {
                    parent = ResourceBundleWrapper.instantiateBundle(baseName2, "", defaultID, root, disableFallback);
                    parentIsRoot = true;
                }
                ResourceBundleWrapper b = null;
                try {
                    b = new ResourceBundleWrapper((ResourceBundle) root.loadClass(name).asSubclass(ResourceBundle.class).newInstance());
                    if (parent != null) {
                        b.setParent(parent);
                    }
                    b.baseName = baseName2;
                    b.localeID = String.this;
                } catch (ClassNotFoundException e) {
                    loadFromProperties = true;
                } catch (NoClassDefFoundError e2) {
                    loadFromProperties = true;
                } catch (Exception e3) {
                    if (ResourceBundleWrapper.DEBUG) {
                        System.out.println("failure");
                    }
                    if (ResourceBundleWrapper.DEBUG) {
                        System.out.println(e3);
                    }
                }
                if (loadFromProperties) {
                    try {
                        final String resName = name.replace('.', '/') + ".properties";
                        InputStream stream = (InputStream) AccessController.doPrivileged(new PrivilegedAction<InputStream>() {
                            /* class android.icu.impl.ResourceBundleWrapper.AnonymousClass2.AnonymousClass1 */

                            @Override // java.security.PrivilegedAction
                            public InputStream run() {
                                return root.getResourceAsStream(resName);
                            }
                        });
                        if (stream != null) {
                            InputStream stream2 = new BufferedInputStream(stream);
                            try {
                                b = new ResourceBundleWrapper(new PropertyResourceBundle(stream2));
                                if (parent != null) {
                                    b.setParent(parent);
                                }
                                b.baseName = baseName2;
                                b.localeID = String.this;
                                try {
                                    stream2.close();
                                } catch (Exception e4) {
                                }
                            } catch (Exception e5) {
                                stream2.close();
                            } catch (Throwable th) {
                                try {
                                    stream2.close();
                                } catch (Exception e6) {
                                }
                                throw th;
                            }
                        }
                        if (b == null && !disableFallback && !String.this.isEmpty() && String.this.indexOf(95) < 0 && !ResourceBundleWrapper.localeIDStartsWithLangSubtag(defaultID, String.this)) {
                            b = ResourceBundleWrapper.instantiateBundle(baseName2, defaultID, defaultID, root, disableFallback);
                        }
                        if (b == null && (!parentIsRoot || !disableFallback)) {
                            b = parent;
                        }
                    } catch (Exception e7) {
                        if (ResourceBundleWrapper.DEBUG) {
                            System.out.println("failure");
                        }
                        if (ResourceBundleWrapper.DEBUG) {
                            System.out.println(e7);
                        }
                    }
                }
                if (b != null) {
                    b.initKeysVector();
                } else if (ResourceBundleWrapper.DEBUG) {
                    System.out.println("Returning null for " + baseName2 + "_" + String.this);
                }
                return b;
            }
        });
    }
}
