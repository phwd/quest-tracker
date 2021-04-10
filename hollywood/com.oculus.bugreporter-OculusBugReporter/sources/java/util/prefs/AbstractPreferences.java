package java.util.prefs;

import java.io.IOException;
import java.io.OutputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public abstract class AbstractPreferences extends Preferences {
    private static final AbstractPreferences[] EMPTY_ABSTRACT_PREFS_ARRAY = new AbstractPreferences[0];
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static Thread eventDispatchThread = null;
    private static final List<EventObject> eventQueue = new LinkedList();
    private final String absolutePath;
    private Map<String, AbstractPreferences> kidCache = new HashMap();
    protected final Object lock = new Object();
    private final String name;
    protected boolean newNode = false;
    private final ArrayList<NodeChangeListener> nodeListeners = new ArrayList<>();
    final AbstractPreferences parent;
    private final ArrayList<PreferenceChangeListener> prefListeners = new ArrayList<>();
    private boolean removed = false;
    private final AbstractPreferences root;

    /* access modifiers changed from: protected */
    public abstract AbstractPreferences childSpi(String str);

    /* access modifiers changed from: protected */
    public abstract String[] childrenNamesSpi() throws BackingStoreException;

    /* access modifiers changed from: protected */
    public abstract void flushSpi() throws BackingStoreException;

    /* access modifiers changed from: protected */
    public abstract String getSpi(String str);

    /* access modifiers changed from: protected */
    public abstract String[] keysSpi() throws BackingStoreException;

    /* access modifiers changed from: protected */
    public abstract void putSpi(String str, String str2);

    /* access modifiers changed from: protected */
    public abstract void removeNodeSpi() throws BackingStoreException;

    /* access modifiers changed from: protected */
    public abstract void removeSpi(String str);

    /* access modifiers changed from: protected */
    public abstract void syncSpi() throws BackingStoreException;

    protected AbstractPreferences(AbstractPreferences parent2, String name2) {
        String str;
        if (parent2 == null) {
            if (name2.equals("")) {
                this.absolutePath = "/";
                this.root = this;
            } else {
                throw new IllegalArgumentException("Root name '" + name2 + "' must be \"\"");
            }
        } else if (name2.indexOf(47) != -1) {
            throw new IllegalArgumentException("Name '" + name2 + "' contains '/'");
        } else if (!name2.equals("")) {
            this.root = parent2.root;
            if (parent2 == this.root) {
                str = "/" + name2;
            } else {
                str = parent2.absolutePath() + "/" + name2;
            }
            this.absolutePath = str;
        } else {
            throw new IllegalArgumentException("Illegal name: empty string");
        }
        this.name = name2;
        this.parent = parent2;
    }

    @Override // java.util.prefs.Preferences
    public void put(String key, String value) {
        if (key == null || value == null) {
            throw new NullPointerException();
        } else if (key.length() > 80) {
            throw new IllegalArgumentException("Key too long: " + key);
        } else if (value.length() <= 8192) {
            synchronized (this.lock) {
                if (!this.removed) {
                    putSpi(key, value);
                    enqueuePreferenceChangeEvent(key, value);
                } else {
                    throw new IllegalStateException("Node has been removed.");
                }
            }
        } else {
            throw new IllegalArgumentException("Value too long: " + value);
        }
    }

    @Override // java.util.prefs.Preferences
    public String get(String key, String def) {
        String str;
        if (key != null) {
            synchronized (this.lock) {
                if (!this.removed) {
                    String result = null;
                    try {
                        result = getSpi(key);
                    } catch (Exception e) {
                    }
                    str = result == null ? def : result;
                } else {
                    throw new IllegalStateException("Node has been removed.");
                }
            }
            return str;
        }
        throw new NullPointerException("Null key");
    }

    @Override // java.util.prefs.Preferences
    public void remove(String key) {
        Objects.requireNonNull(key, "Specified key cannot be null");
        synchronized (this.lock) {
            if (!this.removed) {
                removeSpi(key);
                enqueuePreferenceChangeEvent(key, null);
            } else {
                throw new IllegalStateException("Node has been removed.");
            }
        }
    }

    @Override // java.util.prefs.Preferences
    public void clear() throws BackingStoreException {
        String[] keys;
        synchronized (this.lock) {
            for (String str : keys()) {
                remove(str);
            }
        }
    }

    @Override // java.util.prefs.Preferences
    public void putInt(String key, int value) {
        put(key, Integer.toString(value));
    }

    @Override // java.util.prefs.Preferences
    public int getInt(String key, int def) {
        try {
            String value = get(key, null);
            if (value != null) {
                return Integer.parseInt(value);
            }
            return def;
        } catch (NumberFormatException e) {
            return def;
        }
    }

    @Override // java.util.prefs.Preferences
    public void putLong(String key, long value) {
        put(key, Long.toString(value));
    }

    @Override // java.util.prefs.Preferences
    public long getLong(String key, long def) {
        try {
            String value = get(key, null);
            if (value != null) {
                return Long.parseLong(value);
            }
            return def;
        } catch (NumberFormatException e) {
            return def;
        }
    }

    @Override // java.util.prefs.Preferences
    public void putBoolean(String key, boolean value) {
        put(key, String.valueOf(value));
    }

    @Override // java.util.prefs.Preferences
    public boolean getBoolean(String key, boolean def) {
        String value = get(key, null);
        if (value == null) {
            return def;
        }
        if (value.equalsIgnoreCase("true")) {
            return true;
        }
        if (value.equalsIgnoreCase("false")) {
            return false;
        }
        return def;
    }

    @Override // java.util.prefs.Preferences
    public void putFloat(String key, float value) {
        put(key, Float.toString(value));
    }

    @Override // java.util.prefs.Preferences
    public float getFloat(String key, float def) {
        try {
            String value = get(key, null);
            if (value != null) {
                return Float.parseFloat(value);
            }
            return def;
        } catch (NumberFormatException e) {
            return def;
        }
    }

    @Override // java.util.prefs.Preferences
    public void putDouble(String key, double value) {
        put(key, Double.toString(value));
    }

    @Override // java.util.prefs.Preferences
    public double getDouble(String key, double def) {
        try {
            String value = get(key, null);
            if (value != null) {
                return Double.parseDouble(value);
            }
            return def;
        } catch (NumberFormatException e) {
            return def;
        }
    }

    @Override // java.util.prefs.Preferences
    public void putByteArray(String key, byte[] value) {
        put(key, Base64.byteArrayToBase64(value));
    }

    @Override // java.util.prefs.Preferences
    public byte[] getByteArray(String key, byte[] def) {
        String value = get(key, null);
        if (value == null) {
            return def;
        }
        try {
            return Base64.base64ToByteArray(value);
        } catch (RuntimeException e) {
            return def;
        }
    }

    @Override // java.util.prefs.Preferences
    public String[] keys() throws BackingStoreException {
        String[] keysSpi;
        synchronized (this.lock) {
            if (!this.removed) {
                keysSpi = keysSpi();
            } else {
                throw new IllegalStateException("Node has been removed.");
            }
        }
        return keysSpi;
    }

    @Override // java.util.prefs.Preferences
    public String[] childrenNames() throws BackingStoreException {
        String[] strArr;
        synchronized (this.lock) {
            if (!this.removed) {
                Set<String> s = new TreeSet<>(this.kidCache.keySet());
                for (String kid : childrenNamesSpi()) {
                    s.add(kid);
                }
                strArr = (String[]) s.toArray(EMPTY_STRING_ARRAY);
            } else {
                throw new IllegalStateException("Node has been removed.");
            }
        }
        return strArr;
    }

    /* access modifiers changed from: protected */
    public final AbstractPreferences[] cachedChildren() {
        return (AbstractPreferences[]) this.kidCache.values().toArray(EMPTY_ABSTRACT_PREFS_ARRAY);
    }

    @Override // java.util.prefs.Preferences
    public Preferences parent() {
        AbstractPreferences abstractPreferences;
        synchronized (this.lock) {
            if (!this.removed) {
                abstractPreferences = this.parent;
            } else {
                throw new IllegalStateException("Node has been removed.");
            }
        }
        return abstractPreferences;
    }

    @Override // java.util.prefs.Preferences
    public Preferences node(String path) {
        synchronized (this.lock) {
            if (this.removed) {
                throw new IllegalStateException("Node has been removed.");
            } else if (path.equals("")) {
                return this;
            } else {
                if (path.equals("/")) {
                    return this.root;
                } else if (path.charAt(0) == '/') {
                    return this.root.node(new StringTokenizer(path.substring(1), "/", true));
                } else {
                    return node(new StringTokenizer(path, "/", true));
                }
            }
        }
    }

    private Preferences node(StringTokenizer path) {
        String token = path.nextToken();
        if (!token.equals("/")) {
            synchronized (this.lock) {
                AbstractPreferences child = this.kidCache.get(token);
                if (child == null) {
                    if (token.length() <= 80) {
                        child = childSpi(token);
                        if (child.newNode) {
                            enqueueNodeAddedEvent(child);
                        }
                        this.kidCache.put(token, child);
                    } else {
                        throw new IllegalArgumentException("Node name " + token + " too long");
                    }
                }
                if (!path.hasMoreTokens()) {
                    return child;
                }
                path.nextToken();
                if (path.hasMoreTokens()) {
                    return child.node(path);
                }
                throw new IllegalArgumentException("Path ends with slash");
            }
        }
        throw new IllegalArgumentException("Consecutive slashes in path");
    }

    @Override // java.util.prefs.Preferences
    public boolean nodeExists(String path) throws BackingStoreException {
        synchronized (this.lock) {
            boolean z = false;
            if (path.equals("")) {
                if (!this.removed) {
                    z = true;
                }
                return z;
            } else if (this.removed) {
                throw new IllegalStateException("Node has been removed.");
            } else if (path.equals("/")) {
                return true;
            } else {
                if (path.charAt(0) == '/') {
                    return this.root.nodeExists(new StringTokenizer(path.substring(1), "/", true));
                }
                return nodeExists(new StringTokenizer(path, "/", true));
            }
        }
    }

    private boolean nodeExists(StringTokenizer path) throws BackingStoreException {
        String token = path.nextToken();
        if (!token.equals("/")) {
            synchronized (this.lock) {
                AbstractPreferences child = this.kidCache.get(token);
                if (child == null) {
                    child = getChild(token);
                }
                if (child == null) {
                    return false;
                }
                if (!path.hasMoreTokens()) {
                    return true;
                }
                path.nextToken();
                if (path.hasMoreTokens()) {
                    return child.nodeExists(path);
                }
                throw new IllegalArgumentException("Path ends with slash");
            }
        }
        throw new IllegalArgumentException("Consecutive slashes in path");
    }

    @Override // java.util.prefs.Preferences
    public void removeNode() throws BackingStoreException {
        if (this != this.root) {
            synchronized (this.parent.lock) {
                removeNode2();
                this.parent.kidCache.remove(this.name);
            }
            return;
        }
        throw new UnsupportedOperationException("Can't remove the root!");
    }

    private void removeNode2() throws BackingStoreException {
        synchronized (this.lock) {
            if (!this.removed) {
                String[] kidNames = childrenNamesSpi();
                for (int i = 0; i < kidNames.length; i++) {
                    if (!this.kidCache.containsKey(kidNames[i])) {
                        this.kidCache.put(kidNames[i], childSpi(kidNames[i]));
                    }
                }
                Iterator<AbstractPreferences> i2 = this.kidCache.values().iterator();
                while (i2.hasNext()) {
                    try {
                        i2.next().removeNode2();
                        i2.remove();
                    } catch (BackingStoreException e) {
                    }
                }
                removeNodeSpi();
                this.removed = true;
                this.parent.enqueueNodeRemovedEvent(this);
            } else {
                throw new IllegalStateException("Node already removed.");
            }
        }
    }

    @Override // java.util.prefs.Preferences
    public String name() {
        return this.name;
    }

    @Override // java.util.prefs.Preferences
    public String absolutePath() {
        return this.absolutePath;
    }

    @Override // java.util.prefs.Preferences
    public boolean isUserNode() {
        return ((Boolean) AccessController.doPrivileged(new PrivilegedAction<Boolean>() {
            /* class java.util.prefs.AbstractPreferences.AnonymousClass1 */

            @Override // java.security.PrivilegedAction
            public Boolean run() {
                return Boolean.valueOf(AbstractPreferences.this.root == Preferences.userRoot());
            }
        })).booleanValue();
    }

    @Override // java.util.prefs.Preferences
    public void addPreferenceChangeListener(PreferenceChangeListener pcl) {
        if (pcl != null) {
            synchronized (this.lock) {
                if (!this.removed) {
                    this.prefListeners.add(pcl);
                } else {
                    throw new IllegalStateException("Node has been removed.");
                }
            }
            startEventDispatchThreadIfNecessary();
            return;
        }
        throw new NullPointerException("Change listener is null.");
    }

    @Override // java.util.prefs.Preferences
    public void removePreferenceChangeListener(PreferenceChangeListener pcl) {
        synchronized (this.lock) {
            if (this.removed) {
                throw new IllegalStateException("Node has been removed.");
            } else if (this.prefListeners.contains(pcl)) {
                this.prefListeners.remove(pcl);
            } else {
                throw new IllegalArgumentException("Listener not registered.");
            }
        }
    }

    @Override // java.util.prefs.Preferences
    public void addNodeChangeListener(NodeChangeListener ncl) {
        if (ncl != null) {
            synchronized (this.lock) {
                if (!this.removed) {
                    this.nodeListeners.add(ncl);
                } else {
                    throw new IllegalStateException("Node has been removed.");
                }
            }
            startEventDispatchThreadIfNecessary();
            return;
        }
        throw new NullPointerException("Change listener is null.");
    }

    @Override // java.util.prefs.Preferences
    public void removeNodeChangeListener(NodeChangeListener ncl) {
        synchronized (this.lock) {
            if (this.removed) {
                throw new IllegalStateException("Node has been removed.");
            } else if (this.nodeListeners.contains(ncl)) {
                this.nodeListeners.remove(ncl);
            } else {
                throw new IllegalArgumentException("Listener not registered.");
            }
        }
    }

    /* access modifiers changed from: protected */
    public AbstractPreferences getChild(String nodeName) throws BackingStoreException {
        synchronized (this.lock) {
            String[] kidNames = childrenNames();
            for (int i = 0; i < kidNames.length; i++) {
                if (kidNames[i].equals(nodeName)) {
                    return childSpi(kidNames[i]);
                }
            }
            return null;
        }
    }

    @Override // java.util.prefs.Preferences
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(isUserNode() ? "User" : "System");
        sb.append(" Preference Node: ");
        sb.append(absolutePath());
        return sb.toString();
    }

    @Override // java.util.prefs.Preferences
    public void sync() throws BackingStoreException {
        sync2();
    }

    private void sync2() throws BackingStoreException {
        AbstractPreferences[] cachedKids;
        synchronized (this.lock) {
            if (!this.removed) {
                syncSpi();
                cachedKids = cachedChildren();
            } else {
                throw new IllegalStateException("Node has been removed");
            }
        }
        for (AbstractPreferences abstractPreferences : cachedKids) {
            abstractPreferences.sync2();
        }
    }

    @Override // java.util.prefs.Preferences
    public void flush() throws BackingStoreException {
        flush2();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0013, code lost:
        if (r0 >= r1.length) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0015, code lost:
        r1[r0].flush2();
        r0 = r0 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0011, code lost:
        r0 = 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void flush2() throws java.util.prefs.BackingStoreException {
        /*
            r3 = this;
            java.lang.Object r0 = r3.lock
            monitor-enter(r0)
            r3.flushSpi()     // Catch:{ all -> 0x001e }
            boolean r1 = r3.removed     // Catch:{ all -> 0x001e }
            if (r1 == 0) goto L_0x000c
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            return
        L_0x000c:
            java.util.prefs.AbstractPreferences[] r1 = r3.cachedChildren()     // Catch:{ all -> 0x001e }
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            r0 = 0
        L_0x0012:
            int r2 = r1.length
            if (r0 >= r2) goto L_0x001d
            r2 = r1[r0]
            r2.flush2()
            int r0 = r0 + 1
            goto L_0x0012
        L_0x001d:
            return
        L_0x001e:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.prefs.AbstractPreferences.flush2():void");
    }

    /* access modifiers changed from: protected */
    public boolean isRemoved() {
        boolean z;
        synchronized (this.lock) {
            z = this.removed;
        }
        return z;
    }

    /* access modifiers changed from: private */
    public class NodeAddedEvent extends NodeChangeEvent {
        private static final long serialVersionUID = -6743557530157328528L;

        NodeAddedEvent(Preferences parent, Preferences child) {
            super(parent, child);
        }
    }

    /* access modifiers changed from: private */
    public class NodeRemovedEvent extends NodeChangeEvent {
        private static final long serialVersionUID = 8735497392918824837L;

        NodeRemovedEvent(Preferences parent, Preferences child) {
            super(parent, child);
        }
    }

    /* access modifiers changed from: private */
    public static class EventDispatchThread extends Thread {
        private EventDispatchThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            EventObject event;
            PreferenceChangeListener[] listeners;
            while (true) {
                synchronized (AbstractPreferences.eventQueue) {
                    while (AbstractPreferences.eventQueue.isEmpty()) {
                        try {
                            AbstractPreferences.eventQueue.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    event = (EventObject) AbstractPreferences.eventQueue.remove(0);
                }
                AbstractPreferences src = (AbstractPreferences) event.getSource();
                if (event instanceof PreferenceChangeEvent) {
                    PreferenceChangeEvent pce = (PreferenceChangeEvent) event;
                    for (PreferenceChangeListener preferenceChangeListener : src.prefListeners()) {
                        preferenceChangeListener.preferenceChange(pce);
                    }
                } else {
                    NodeChangeEvent nce = (NodeChangeEvent) event;
                    NodeChangeListener[] listeners2 = src.nodeListeners();
                    if (nce instanceof NodeAddedEvent) {
                        for (NodeChangeListener nodeChangeListener : listeners2) {
                            nodeChangeListener.childAdded(nce);
                        }
                    } else {
                        for (NodeChangeListener nodeChangeListener2 : listeners2) {
                            nodeChangeListener2.childRemoved(nce);
                        }
                    }
                }
            }
        }
    }

    private static synchronized void startEventDispatchThreadIfNecessary() {
        synchronized (AbstractPreferences.class) {
            if (eventDispatchThread == null) {
                eventDispatchThread = new EventDispatchThread();
                eventDispatchThread.setDaemon(true);
                eventDispatchThread.start();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public PreferenceChangeListener[] prefListeners() {
        PreferenceChangeListener[] preferenceChangeListenerArr;
        synchronized (this.lock) {
            preferenceChangeListenerArr = (PreferenceChangeListener[]) this.prefListeners.toArray(new PreferenceChangeListener[this.prefListeners.size()]);
        }
        return preferenceChangeListenerArr;
    }

    /* access modifiers changed from: package-private */
    public NodeChangeListener[] nodeListeners() {
        NodeChangeListener[] nodeChangeListenerArr;
        synchronized (this.lock) {
            nodeChangeListenerArr = (NodeChangeListener[]) this.nodeListeners.toArray(new NodeChangeListener[this.nodeListeners.size()]);
        }
        return nodeChangeListenerArr;
    }

    private void enqueuePreferenceChangeEvent(String key, String newValue) {
        if (!this.prefListeners.isEmpty()) {
            synchronized (eventQueue) {
                eventQueue.add(new PreferenceChangeEvent(this, key, newValue));
                eventQueue.notify();
            }
        }
    }

    private void enqueueNodeAddedEvent(Preferences child) {
        if (!this.nodeListeners.isEmpty()) {
            synchronized (eventQueue) {
                eventQueue.add(new NodeAddedEvent(this, child));
                eventQueue.notify();
            }
        }
    }

    private void enqueueNodeRemovedEvent(Preferences child) {
        if (!this.nodeListeners.isEmpty()) {
            synchronized (eventQueue) {
                eventQueue.add(new NodeRemovedEvent(this, child));
                eventQueue.notify();
            }
        }
    }

    @Override // java.util.prefs.Preferences
    public void exportNode(OutputStream os) throws IOException, BackingStoreException {
        XmlSupport.export(os, this, false);
    }

    @Override // java.util.prefs.Preferences
    public void exportSubtree(OutputStream os) throws IOException, BackingStoreException {
        XmlSupport.export(os, this, true);
    }
}
