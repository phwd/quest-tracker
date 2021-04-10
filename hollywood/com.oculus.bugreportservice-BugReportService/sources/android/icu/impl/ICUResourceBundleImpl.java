package android.icu.impl;

import android.icu.impl.ICUResourceBundle;
import android.icu.impl.ICUResourceBundleReader;
import android.icu.util.UResourceBundle;
import android.icu.util.UResourceTypeMismatchException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/* access modifiers changed from: package-private */
public class ICUResourceBundleImpl extends ICUResourceBundle {
    protected int resource;

    protected ICUResourceBundleImpl(ICUResourceBundleImpl iCUResourceBundleImpl, String str, int i) {
        super(iCUResourceBundleImpl, str);
        this.resource = i;
    }

    ICUResourceBundleImpl(ICUResourceBundle.WholeBundle wholeBundle) {
        super(wholeBundle);
        this.resource = wholeBundle.reader.getRootResource();
    }

    public int getResource() {
        return this.resource;
    }

    /* access modifiers changed from: protected */
    public final ICUResourceBundle createBundleObject(String str, int i, HashMap hashMap, UResourceBundle uResourceBundle) {
        int RES_GET_TYPE = ICUResourceBundleReader.RES_GET_TYPE(i);
        if (RES_GET_TYPE == 14) {
            return new ResourceIntVector(this, str, i);
        }
        switch (RES_GET_TYPE) {
            case 0:
            case 6:
                return new ResourceString(this, str, i);
            case 1:
                return new ResourceBinary(this, str, i);
            case 2:
            case 4:
            case 5:
                return new ResourceTable(this, str, i);
            case 3:
                return ICUResourceBundle.getAliasedResource(this, null, 0, str, i, hashMap, uResourceBundle);
            case 7:
                return new ResourceInt(this, str, i);
            case 8:
            case 9:
                return new ResourceArray(this, str, i);
            default:
                throw new IllegalStateException("The resource type is unknown");
        }
    }

    /* access modifiers changed from: private */
    public static final class ResourceBinary extends ICUResourceBundleImpl {
        @Override // android.icu.util.UResourceBundle
        public int getType() {
            return 1;
        }

        @Override // android.icu.util.UResourceBundle
        public ByteBuffer getBinary() {
            return this.wholeBundle.reader.getBinary(this.resource);
        }

        @Override // android.icu.util.UResourceBundle
        public byte[] getBinary(byte[] bArr) {
            return this.wholeBundle.reader.getBinary(this.resource, bArr);
        }

        ResourceBinary(ICUResourceBundleImpl iCUResourceBundleImpl, String str, int i) {
            super(iCUResourceBundleImpl, str, i);
        }
    }

    /* access modifiers changed from: private */
    public static final class ResourceInt extends ICUResourceBundleImpl {
        @Override // android.icu.util.UResourceBundle
        public int getType() {
            return 7;
        }

        @Override // android.icu.util.UResourceBundle
        public int getInt() {
            return ICUResourceBundleReader.RES_GET_INT(this.resource);
        }

        ResourceInt(ICUResourceBundleImpl iCUResourceBundleImpl, String str, int i) {
            super(iCUResourceBundleImpl, str, i);
        }
    }

    /* access modifiers changed from: private */
    public static final class ResourceString extends ICUResourceBundleImpl {
        private String value;

        @Override // android.icu.util.UResourceBundle
        public int getType() {
            return 0;
        }

        @Override // android.icu.util.UResourceBundle
        public String getString() {
            String str = this.value;
            if (str != null) {
                return str;
            }
            return this.wholeBundle.reader.getString(this.resource);
        }

        ResourceString(ICUResourceBundleImpl iCUResourceBundleImpl, String str, int i) {
            super(iCUResourceBundleImpl, str, i);
            String string = this.wholeBundle.reader.getString(i);
            if (string.length() < 12 || CacheValue.futureInstancesWillBeStrong()) {
                this.value = string;
            }
        }
    }

    /* access modifiers changed from: private */
    public static final class ResourceIntVector extends ICUResourceBundleImpl {
        @Override // android.icu.util.UResourceBundle
        public int getType() {
            return 14;
        }

        @Override // android.icu.util.UResourceBundle
        public int[] getIntVector() {
            return this.wholeBundle.reader.getIntVector(this.resource);
        }

        ResourceIntVector(ICUResourceBundleImpl iCUResourceBundleImpl, String str, int i) {
            super(iCUResourceBundleImpl, str, i);
        }
    }

    /* access modifiers changed from: package-private */
    public static abstract class ResourceContainer extends ICUResourceBundleImpl {
        protected ICUResourceBundleReader.Container value;

        @Override // android.icu.util.UResourceBundle
        public int getSize() {
            return this.value.getSize();
        }

        @Override // android.icu.util.UResourceBundle
        public String getString(int i) {
            int containerResource = this.value.getContainerResource(this.wholeBundle.reader, i);
            if (containerResource != -1) {
                String string = this.wholeBundle.reader.getString(containerResource);
                if (string != null) {
                    return string;
                }
                return super.getString(i);
            }
            throw new IndexOutOfBoundsException();
        }

        /* access modifiers changed from: protected */
        public int getContainerResource(int i) {
            return this.value.getContainerResource(this.wholeBundle.reader, i);
        }

        /* access modifiers changed from: protected */
        public UResourceBundle createBundleObject(int i, String str, HashMap hashMap, UResourceBundle uResourceBundle) {
            int containerResource = getContainerResource(i);
            if (containerResource != -1) {
                return createBundleObject(str, containerResource, hashMap, uResourceBundle);
            }
            throw new IndexOutOfBoundsException();
        }

        ResourceContainer(ICUResourceBundleImpl iCUResourceBundleImpl, String str, int i) {
            super(iCUResourceBundleImpl, str, i);
        }

        ResourceContainer(ICUResourceBundle.WholeBundle wholeBundle) {
            super(wholeBundle);
        }
    }

    /* access modifiers changed from: package-private */
    public static class ResourceArray extends ResourceContainer {
        @Override // android.icu.util.UResourceBundle
        public int getType() {
            return 8;
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.util.UResourceBundle
        public String[] handleGetStringArray() {
            ICUResourceBundleReader iCUResourceBundleReader = this.wholeBundle.reader;
            int size = this.value.getSize();
            String[] strArr = new String[size];
            for (int i = 0; i < size; i++) {
                String string = iCUResourceBundleReader.getString(this.value.getContainerResource(iCUResourceBundleReader, i));
                if (string != null) {
                    strArr[i] = string;
                } else {
                    throw new UResourceTypeMismatchException("");
                }
            }
            return strArr;
        }

        @Override // android.icu.util.UResourceBundle
        public String[] getStringArray() {
            return handleGetStringArray();
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.util.UResourceBundle
        public UResourceBundle handleGet(String str, HashMap hashMap, UResourceBundle uResourceBundle) {
            return createBundleObject(Integer.parseInt(str), str, hashMap, uResourceBundle);
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.util.UResourceBundle
        public UResourceBundle handleGet(int i, HashMap hashMap, UResourceBundle uResourceBundle) {
            return createBundleObject(i, Integer.toString(i), hashMap, uResourceBundle);
        }

        ResourceArray(ICUResourceBundleImpl iCUResourceBundleImpl, String str, int i) {
            super(iCUResourceBundleImpl, str, i);
            this.value = this.wholeBundle.reader.getArray(i);
        }
    }

    /* access modifiers changed from: package-private */
    public static class ResourceTable extends ResourceContainer {
        @Override // android.icu.util.UResourceBundle
        public int getType() {
            return 2;
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.util.UResourceBundle
        public Set handleKeySet() {
            ICUResourceBundleReader iCUResourceBundleReader = this.wholeBundle.reader;
            TreeSet treeSet = new TreeSet();
            ICUResourceBundleReader.Table table = (ICUResourceBundleReader.Table) this.value;
            for (int i = 0; i < table.getSize(); i++) {
                treeSet.add(table.getKey(iCUResourceBundleReader, i));
            }
            return treeSet;
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.util.UResourceBundle
        public UResourceBundle handleGet(String str, HashMap hashMap, UResourceBundle uResourceBundle) {
            int findTableItem = ((ICUResourceBundleReader.Table) this.value).findTableItem(this.wholeBundle.reader, str);
            if (findTableItem < 0) {
                return null;
            }
            return createBundleObject(str, getContainerResource(findTableItem), hashMap, uResourceBundle);
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.util.UResourceBundle
        public UResourceBundle handleGet(int i, HashMap hashMap, UResourceBundle uResourceBundle) {
            String key = ((ICUResourceBundleReader.Table) this.value).getKey(this.wholeBundle.reader, i);
            if (key != null) {
                return createBundleObject(key, getContainerResource(i), hashMap, uResourceBundle);
            }
            throw new IndexOutOfBoundsException();
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.util.UResourceBundle, java.util.ResourceBundle
        public Object handleGetObject(String str) {
            ICUResourceBundleReader iCUResourceBundleReader = this.wholeBundle.reader;
            int findTableItem = ((ICUResourceBundleReader.Table) this.value).findTableItem(iCUResourceBundleReader, str);
            if (findTableItem >= 0) {
                int containerResource = this.value.getContainerResource(iCUResourceBundleReader, findTableItem);
                String string = iCUResourceBundleReader.getString(containerResource);
                if (string != null) {
                    return string;
                }
                ICUResourceBundleReader.Array array = iCUResourceBundleReader.getArray(containerResource);
                if (array != null) {
                    int size = array.getSize();
                    String[] strArr = new String[size];
                    for (int i = 0; i != size; i++) {
                        String string2 = iCUResourceBundleReader.getString(array.getContainerResource(iCUResourceBundleReader, i));
                        if (string2 != null) {
                            strArr[i] = string2;
                        }
                    }
                    return strArr;
                }
            }
            return super.handleGetObject(str);
        }

        /* access modifiers changed from: package-private */
        public String findString(String str) {
            ICUResourceBundleReader iCUResourceBundleReader = this.wholeBundle.reader;
            int findTableItem = ((ICUResourceBundleReader.Table) this.value).findTableItem(iCUResourceBundleReader, str);
            if (findTableItem < 0) {
                return null;
            }
            return iCUResourceBundleReader.getString(this.value.getContainerResource(iCUResourceBundleReader, findTableItem));
        }

        ResourceTable(ICUResourceBundleImpl iCUResourceBundleImpl, String str, int i) {
            super(iCUResourceBundleImpl, str, i);
            this.value = this.wholeBundle.reader.getTable(i);
        }

        ResourceTable(ICUResourceBundle.WholeBundle wholeBundle, int i) {
            super(wholeBundle);
            this.value = wholeBundle.reader.getTable(i);
        }
    }
}
