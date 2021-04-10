package com.oculus.android.exoplayer2.upstream.cache;

import android.util.SparseArray;
import com.oculus.android.exoplayer2.upstream.cache.Cache;
import com.oculus.android.exoplayer2.util.Assertions;
import com.oculus.android.exoplayer2.util.AtomicFile;
import com.oculus.android.exoplayer2.util.ReusableBufferedOutputStream;
import com.oculus.android.exoplayer2.util.Util;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* access modifiers changed from: package-private */
public class CachedContentIndex {
    public static final String FILE_NAME = "cached_content_index.exi";
    private static final int FLAG_ENCRYPTED_INDEX = 1;
    private static final String TAG = "CachedContentIndex";
    private static final int VERSION = 1;
    private final AtomicFile atomicFile;
    private ReusableBufferedOutputStream bufferedOutputStream;
    private boolean changed;
    private final Cipher cipher;
    private final boolean encrypt;
    private final SparseArray<String> idToKey;
    private final HashMap<String, CachedContent> keyToContent;
    private final SecretKeySpec secretKeySpec;

    public CachedContentIndex(File file) {
        this(file, null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CachedContentIndex(File file, byte[] bArr) {
        this(file, bArr, bArr != null);
    }

    public CachedContentIndex(File file, byte[] bArr, boolean z) {
        this.encrypt = z;
        boolean z2 = true;
        if (bArr != null) {
            Assertions.checkArgument(bArr.length != 16 ? false : z2);
            try {
                this.cipher = getCipher();
                this.secretKeySpec = new SecretKeySpec(bArr, "AES");
            } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
                throw new IllegalStateException(e);
            }
        } else {
            Assertions.checkState(!z);
            this.cipher = null;
            this.secretKeySpec = null;
        }
        this.keyToContent = new HashMap<>();
        this.idToKey = new SparseArray<>();
        this.atomicFile = new AtomicFile(new File(file, FILE_NAME));
    }

    public void load() {
        Assertions.checkState(!this.changed);
        if (!readFile()) {
            this.atomicFile.delete();
            this.keyToContent.clear();
            this.idToKey.clear();
        }
    }

    public void store() throws Cache.CacheException {
        if (this.changed) {
            writeFile();
            this.changed = false;
        }
    }

    public CachedContent getOrAdd(String str) {
        CachedContent cachedContent = this.keyToContent.get(str);
        return cachedContent == null ? addNew(str, -1) : cachedContent;
    }

    public CachedContent get(String str) {
        return this.keyToContent.get(str);
    }

    public Collection<CachedContent> getAll() {
        return this.keyToContent.values();
    }

    public int assignIdForKey(String str) {
        return getOrAdd(str).id;
    }

    public String getKeyForId(int i) {
        return this.idToKey.get(i);
    }

    public void maybeRemove(String str) {
        CachedContent cachedContent = this.keyToContent.get(str);
        if (cachedContent != null && cachedContent.isEmpty() && !cachedContent.isLocked()) {
            this.keyToContent.remove(str);
            this.idToKey.remove(cachedContent.id);
            this.changed = true;
        }
    }

    public void removeEmpty() {
        String[] strArr = new String[this.keyToContent.size()];
        this.keyToContent.keySet().toArray(strArr);
        for (String str : strArr) {
            maybeRemove(str);
        }
    }

    public Set<String> getKeys() {
        return this.keyToContent.keySet();
    }

    public void setContentLength(String str, long j) {
        CachedContent cachedContent = get(str);
        if (cachedContent == null) {
            addNew(str, j);
        } else if (cachedContent.getLength() != j) {
            cachedContent.setLength(j);
            this.changed = true;
        }
    }

    public long getContentLength(String str) {
        CachedContent cachedContent = get(str);
        if (cachedContent == null) {
            return -1;
        }
        return cachedContent.getLength();
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00a2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean readFile() {
        /*
        // Method dump skipped, instructions count: 166
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.android.exoplayer2.upstream.cache.CachedContentIndex.readFile():boolean");
    }

    private void writeFile() throws Cache.CacheException {
        DataOutputStream dataOutputStream;
        Throwable th;
        IOException e;
        try {
            OutputStream startWrite = this.atomicFile.startWrite();
            if (this.bufferedOutputStream == null) {
                this.bufferedOutputStream = new ReusableBufferedOutputStream(startWrite);
            } else {
                this.bufferedOutputStream.reset(startWrite);
            }
            dataOutputStream = new DataOutputStream(this.bufferedOutputStream);
            try {
                dataOutputStream.writeInt(1);
                int i = 0;
                dataOutputStream.writeInt(this.encrypt ? 1 : 0);
                if (this.encrypt) {
                    byte[] bArr = new byte[16];
                    new Random().nextBytes(bArr);
                    dataOutputStream.write(bArr);
                    try {
                        this.cipher.init(1, this.secretKeySpec, new IvParameterSpec(bArr));
                        dataOutputStream.flush();
                        dataOutputStream = new DataOutputStream(new CipherOutputStream(this.bufferedOutputStream, this.cipher));
                    } catch (InvalidAlgorithmParameterException | InvalidKeyException e2) {
                        throw new IllegalStateException(e2);
                    }
                }
                dataOutputStream.writeInt(this.keyToContent.size());
                for (CachedContent cachedContent : this.keyToContent.values()) {
                    cachedContent.writeToStream(dataOutputStream);
                    i += cachedContent.headerHashCode();
                }
                dataOutputStream.writeInt(i);
                this.atomicFile.endWrite(dataOutputStream);
                Util.closeQuietly((Closeable) null);
            } catch (IOException e3) {
                e = e3;
                try {
                    throw new Cache.CacheException(e);
                } catch (Throwable th2) {
                    th = th2;
                    Util.closeQuietly(dataOutputStream);
                    throw th;
                }
            }
        } catch (IOException e4) {
            dataOutputStream = null;
            e = e4;
            throw new Cache.CacheException(e);
        } catch (Throwable th3) {
            dataOutputStream = null;
            th = th3;
            Util.closeQuietly(dataOutputStream);
            throw th;
        }
    }

    private void add(CachedContent cachedContent) {
        this.keyToContent.put(cachedContent.key, cachedContent);
        this.idToKey.put(cachedContent.id, cachedContent.key);
    }

    /* access modifiers changed from: package-private */
    public void addNew(CachedContent cachedContent) {
        add(cachedContent);
        this.changed = true;
    }

    private CachedContent addNew(String str, long j) {
        CachedContent cachedContent = new CachedContent(getNewId(this.idToKey), str, j);
        addNew(cachedContent);
        return cachedContent;
    }

    private static Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        if (Util.SDK_INT == 18) {
            try {
                return Cipher.getInstance("AES/CBC/PKCS5PADDING", "BC");
            } catch (Throwable unused) {
            }
        }
        return Cipher.getInstance("AES/CBC/PKCS5PADDING");
    }

    public static int getNewId(SparseArray<String> sparseArray) {
        int i;
        int size = sparseArray.size();
        if (size == 0) {
            i = 0;
        } else {
            i = sparseArray.keyAt(size - 1) + 1;
        }
        if (i < 0) {
            i = 0;
            while (i < size && i == sparseArray.keyAt(i)) {
                i++;
            }
        }
        return i;
    }
}
