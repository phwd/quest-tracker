package java.net;

import dalvik.system.VMRuntime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class InMemoryCookieStore implements CookieStore {
    private final boolean applyMCompatibility;
    private ReentrantLock lock;
    private Map<URI, List<HttpCookie>> uriIndex;

    public InMemoryCookieStore() {
        this(VMRuntime.getRuntime().getTargetSdkVersion());
    }

    public InMemoryCookieStore(int targetSdkVersion) {
        this.uriIndex = null;
        this.lock = null;
        this.uriIndex = new HashMap();
        boolean z = false;
        this.lock = new ReentrantLock(false);
        this.applyMCompatibility = targetSdkVersion <= 23 ? true : z;
    }

    @Override // java.net.CookieStore
    public void add(URI uri, HttpCookie cookie) {
        if (cookie != null) {
            this.lock.lock();
            try {
                addIndex(this.uriIndex, getEffectiveURI(uri), cookie);
            } finally {
                this.lock.unlock();
            }
        } else {
            throw new NullPointerException("cookie is null");
        }
    }

    @Override // java.net.CookieStore
    public List<HttpCookie> get(URI uri) {
        if (uri != null) {
            List<HttpCookie> cookies = new ArrayList<>();
            this.lock.lock();
            try {
                getInternal1(cookies, this.uriIndex, uri.getHost());
                getInternal2(cookies, this.uriIndex, getEffectiveURI(uri));
                return cookies;
            } finally {
                this.lock.unlock();
            }
        } else {
            throw new NullPointerException("uri is null");
        }
    }

    @Override // java.net.CookieStore
    public List<HttpCookie> getCookies() {
        List<HttpCookie> rt = new ArrayList<>();
        this.lock.lock();
        try {
            for (List<HttpCookie> list : this.uriIndex.values()) {
                Iterator<HttpCookie> it = list.iterator();
                while (it.hasNext()) {
                    HttpCookie cookie = it.next();
                    if (cookie.hasExpired()) {
                        it.remove();
                    } else if (!rt.contains(cookie)) {
                        rt.add(cookie);
                    }
                }
            }
            return Collections.unmodifiableList(rt);
        } finally {
            Collections.unmodifiableList(rt);
            this.lock.unlock();
        }
    }

    @Override // java.net.CookieStore
    public List<URI> getURIs() {
        this.lock.lock();
        try {
            List<URI> result = new ArrayList<>(this.uriIndex.keySet());
            result.remove((Object) null);
            return Collections.unmodifiableList(result);
        } finally {
            this.lock.unlock();
        }
    }

    @Override // java.net.CookieStore
    public boolean remove(URI uri, HttpCookie ck) {
        if (ck != null) {
            this.lock.lock();
            try {
                URI uri2 = getEffectiveURI(uri);
                if (this.uriIndex.get(uri2) == null) {
                    return false;
                }
                List<HttpCookie> cookies = this.uriIndex.get(uri2);
                if (cookies != null) {
                    boolean remove = cookies.remove(ck);
                    this.lock.unlock();
                    return remove;
                }
                this.lock.unlock();
                return false;
            } finally {
                this.lock.unlock();
            }
        } else {
            throw new NullPointerException("cookie is null");
        }
    }

    @Override // java.net.CookieStore
    public boolean removeAll() {
        this.lock.lock();
        try {
            boolean result = !this.uriIndex.isEmpty();
            this.uriIndex.clear();
            return result;
        } finally {
            this.lock.unlock();
        }
    }

    private boolean netscapeDomainMatches(String domain, String host) {
        if (domain == null || host == null) {
            return false;
        }
        boolean isLocalDomain = ".local".equalsIgnoreCase(domain);
        int embeddedDotInDomain = domain.indexOf(46);
        if (embeddedDotInDomain == 0) {
            embeddedDotInDomain = domain.indexOf(46, 1);
        }
        if (!isLocalDomain && (embeddedDotInDomain == -1 || embeddedDotInDomain == domain.length() - 1)) {
            return false;
        }
        if (host.indexOf(46) == -1 && isLocalDomain) {
            return true;
        }
        int lengthDiff = host.length() - domain.length();
        if (lengthDiff == 0) {
            return host.equalsIgnoreCase(domain);
        }
        if (lengthDiff > 0) {
            String D = host.substring(lengthDiff);
            if (!this.applyMCompatibility || domain.startsWith(".")) {
                return D.equalsIgnoreCase(domain);
            }
            return false;
        } else if (lengthDiff == -1 && domain.charAt(0) == '.' && host.equalsIgnoreCase(domain.substring(1))) {
            return true;
        } else {
            return false;
        }
    }

    private void getInternal1(List<HttpCookie> cookies, Map<URI, List<HttpCookie>> cookieIndex, String host) {
        ArrayList<HttpCookie> toRemove = new ArrayList<>();
        for (Map.Entry<URI, List<HttpCookie>> entry : cookieIndex.entrySet()) {
            List<HttpCookie> lst = entry.getValue();
            for (HttpCookie c : lst) {
                String domain = c.getDomain();
                if ((c.getVersion() == 0 && netscapeDomainMatches(domain, host)) || (c.getVersion() == 1 && HttpCookie.domainMatches(domain, host))) {
                    if (c.hasExpired()) {
                        toRemove.add(c);
                    } else if (!cookies.contains(c)) {
                        cookies.add(c);
                    }
                }
            }
            Iterator<HttpCookie> it = toRemove.iterator();
            while (it.hasNext()) {
                lst.remove(it.next());
            }
            toRemove.clear();
        }
    }

    private <T extends Comparable<T>> void getInternal2(List<HttpCookie> cookies, Map<T, List<HttpCookie>> cookieIndex, T comparator) {
        List<HttpCookie> indexedCookies;
        for (T index : cookieIndex.keySet()) {
            if ((index == comparator || (index != null && comparator.compareTo(index) == 0)) && (indexedCookies = cookieIndex.get(index)) != null) {
                Iterator<HttpCookie> it = indexedCookies.iterator();
                while (it.hasNext()) {
                    HttpCookie ck = it.next();
                    if (ck.hasExpired()) {
                        it.remove();
                    } else if (!cookies.contains(ck)) {
                        cookies.add(ck);
                    }
                }
            }
        }
    }

    private <T> void addIndex(Map<T, List<HttpCookie>> indexStore, T index, HttpCookie cookie) {
        List<HttpCookie> cookies = indexStore.get(index);
        if (cookies != null) {
            cookies.remove(cookie);
            cookies.add(cookie);
            return;
        }
        List<HttpCookie> cookies2 = new ArrayList<>();
        cookies2.add(cookie);
        indexStore.put(index, cookies2);
    }

    private URI getEffectiveURI(URI uri) {
        if (uri == null) {
            return null;
        }
        try {
            return new URI("http", uri.getHost(), null, null, null);
        } catch (URISyntaxException e) {
            return uri;
        }
    }
}
