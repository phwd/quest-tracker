package com.facebook.secure.trustedapp;

import com.bumptech.glide.load.Key;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

public class FbPermissionEncoder {
    public static final byte ENTRY_CONSUMER_KEYHASH = 4;
    public static final byte ENTRY_CONSUMER_PACKAGE = 2;
    public static final byte ENTRY_CONSUMER_VERSION = 3;
    public static final byte ENTRY_PERMISSIONS = 1;
    public static final byte ENTRY_PROVIDER_PACKAGE = 5;
    private static final byte MAGIC_BYTE = -5;
    static final int MAX_ENTRY_SIZE = 255;
    static final int MAX_NUM_ENTRIES = 255;
    static final int MAX_SET_SIZE = 255;
    private static final Charset UTF_8 = Charset.forName(Key.STRING_CHARSET_NAME);
    private ByteArrayOutputStream mData = new ByteArrayOutputStream();
    private int mEntries = 0;

    public FbPermissionEncoder() {
        this.mData.write(-5);
        this.mData.write(0);
    }

    public void update(String str, byte b) throws EncoderException {
        if (this.mEntries < 255) {
            byte[] bytes = str.getBytes(UTF_8);
            if (bytes.length <= 255) {
                this.mData.write(b);
                update(bytes);
                this.mEntries++;
                return;
            }
            throw new EncoderException("String size (UTF-8 encoded) cannot exceed 255");
        }
        throw new EncoderException("Total number of entries cannot exceed 255");
    }

    public void update(Collection<String> collection, byte b) throws EncoderException {
        if (this.mEntries < 255) {
            TreeSet treeSet = new TreeSet(collection);
            if (treeSet.size() <= 255) {
                ArrayList<byte[]> arrayList = new ArrayList();
                Iterator it = treeSet.iterator();
                while (it.hasNext()) {
                    byte[] bytes = ((String) it.next()).getBytes(UTF_8);
                    if (bytes.length <= 255) {
                        arrayList.add(bytes);
                    } else {
                        throw new EncoderException("String size (UTF-8 encoded) cannot exceed 255");
                    }
                }
                this.mData.write(b);
                this.mData.write(treeSet.size() & 255);
                for (byte[] bArr : arrayList) {
                    update(bArr);
                }
                this.mEntries++;
                return;
            }
            throw new EncoderException("Collection size (duplicates removed) cannot exceed 255");
        }
        throw new EncoderException("Total number of entries cannot exceed 255");
    }

    private void update(byte[] bArr) {
        this.mData.write(bArr.length & 255);
        this.mData.write(bArr, 0, bArr.length);
    }

    public byte[] getEncoded() {
        byte[] byteArray = this.mData.toByteArray();
        byteArray[1] = (byte) (this.mEntries & 255);
        return byteArray;
    }

    public static class EncoderException extends Exception {
        public EncoderException(String str) {
            super(str);
        }
    }
}
