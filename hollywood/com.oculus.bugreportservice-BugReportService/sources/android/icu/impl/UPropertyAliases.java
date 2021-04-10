package android.icu.impl;

import android.icu.impl.ICUBinary;
import android.icu.util.BytesTrie;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.MissingResourceException;

public final class UPropertyAliases {
    public static final UPropertyAliases INSTANCE;
    private static final IsAcceptable IS_ACCEPTABLE = new IsAcceptable();
    private byte[] bytesTries;
    private String nameGroups;
    private int[] valueMaps;

    private static int asciiToLowercase(int i) {
        return (65 > i || i > 90) ? i : i + 32;
    }

    /* access modifiers changed from: private */
    public static final class IsAcceptable implements ICUBinary.Authenticate {
        private IsAcceptable() {
        }

        @Override // android.icu.impl.ICUBinary.Authenticate
        public boolean isDataVersionAcceptable(byte[] bArr) {
            return bArr[0] == 2;
        }
    }

    static {
        try {
            INSTANCE = new UPropertyAliases();
        } catch (IOException e) {
            MissingResourceException missingResourceException = new MissingResourceException("Could not construct UPropertyAliases. Missing pnames.icu", "", "");
            missingResourceException.initCause(e);
            throw missingResourceException;
        }
    }

    private void load(ByteBuffer byteBuffer) {
        ICUBinary.readHeader(byteBuffer, 1886282093, IS_ACCEPTABLE);
        int i = byteBuffer.getInt() / 4;
        if (i >= 8) {
            int[] iArr = new int[i];
            iArr[0] = i * 4;
            for (int i2 = 1; i2 < i; i2++) {
                iArr[i2] = byteBuffer.getInt();
            }
            int i3 = iArr[0];
            int i4 = iArr[1];
            this.valueMaps = ICUBinary.getInts(byteBuffer, (i4 - i3) / 4, 0);
            int i5 = iArr[2];
            this.bytesTries = new byte[(i5 - i4)];
            byteBuffer.get(this.bytesTries);
            int i6 = iArr[3] - i5;
            StringBuilder sb = new StringBuilder(i6);
            for (int i7 = 0; i7 < i6; i7++) {
                sb.append((char) byteBuffer.get());
            }
            this.nameGroups = sb.toString();
            return;
        }
        throw new IOException("pnames.icu: not enough indexes");
    }

    private UPropertyAliases() {
        load(ICUBinary.getRequiredData("pnames.icu"));
    }

    private int findProperty(int i) {
        int i2 = 1;
        for (int i3 = this.valueMaps[0]; i3 > 0; i3--) {
            int[] iArr = this.valueMaps;
            int i4 = iArr[i2];
            int i5 = iArr[i2 + 1];
            int i6 = i2 + 2;
            if (i < i4) {
                break;
            } else if (i < i5) {
                return i6 + ((i - i4) * 2);
            } else {
                i2 = i6 + ((i5 - i4) * 2);
            }
        }
        return 0;
    }

    private int findPropertyValueNameGroup(int i, int i2) {
        if (i == 0) {
            return 0;
        }
        int i3 = i + 1;
        int i4 = i3 + 1;
        int i5 = this.valueMaps[i3];
        if (i5 >= 16) {
            int i6 = (i5 + i4) - 16;
            int i7 = i4;
            do {
                int[] iArr = this.valueMaps;
                int i8 = iArr[i7];
                if (i2 < i8) {
                    break;
                } else if (i2 == i8) {
                    return iArr[(i6 + i7) - i4];
                } else {
                    i7++;
                }
            } while (i7 < i6);
        } else {
            while (i5 > 0) {
                int[] iArr2 = this.valueMaps;
                int i9 = iArr2[i4];
                int i10 = iArr2[i4 + 1];
                int i11 = i4 + 2;
                if (i2 < i9) {
                    break;
                } else if (i2 < i10) {
                    return iArr2[(i11 + i2) - i9];
                } else {
                    i4 = i11 + (i10 - i9);
                    i5--;
                }
            }
        }
        return 0;
    }

    private String getName(int i, int i2) {
        int i3;
        int i4 = i + 1;
        char charAt = this.nameGroups.charAt(i);
        if (i2 < 0 || charAt <= i2) {
            throw new IllegalIcuArgumentException("Invalid property (value) name choice");
        }
        while (i2 > 0) {
            while (true) {
                i3 = i4 + 1;
                if (this.nameGroups.charAt(i4) == 0) {
                    break;
                }
                i4 = i3;
            }
            i2--;
            i4 = i3;
        }
        int i5 = i4;
        while (this.nameGroups.charAt(i5) != 0) {
            i5++;
        }
        if (i4 == i5) {
            return null;
        }
        return this.nameGroups.substring(i4, i5);
    }

    private boolean containsName(BytesTrie bytesTrie, CharSequence charSequence) {
        BytesTrie.Result result = BytesTrie.Result.NO_VALUE;
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (!(charAt == '-' || charAt == '_' || charAt == ' ' || ('\t' <= charAt && charAt <= '\r'))) {
                if (!result.hasNext()) {
                    return false;
                }
                result = bytesTrie.next(asciiToLowercase(charAt));
            }
        }
        return result.hasValue();
    }

    public String getPropertyValueName(int i, int i2, int i3) {
        int findProperty = findProperty(i);
        if (findProperty != 0) {
            int findPropertyValueNameGroup = findPropertyValueNameGroup(this.valueMaps[findProperty + 1], i2);
            if (findPropertyValueNameGroup != 0) {
                return getName(findPropertyValueNameGroup, i3);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Property ");
            sb.append(i);
            sb.append(" (0x");
            Integer.toHexString(i);
            throw null;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Invalid property enum ");
        sb2.append(i);
        sb2.append(" (0x");
        Integer.toHexString(i);
        throw null;
    }

    private int getPropertyOrValueEnum(int i, CharSequence charSequence) {
        BytesTrie bytesTrie = new BytesTrie(this.bytesTries, i);
        if (containsName(bytesTrie, charSequence)) {
            return bytesTrie.getValue();
        }
        return -1;
    }

    public int getPropertyEnum(CharSequence charSequence) {
        return getPropertyOrValueEnum(0, charSequence);
    }

    public int getPropertyValueEnum(int i, CharSequence charSequence) {
        int findProperty = findProperty(i);
        if (findProperty != 0) {
            int[] iArr = this.valueMaps;
            int i2 = iArr[findProperty + 1];
            if (i2 != 0) {
                return getPropertyOrValueEnum(iArr[i2], charSequence);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Property ");
            sb.append(i);
            sb.append(" (0x");
            Integer.toHexString(i);
            throw null;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Invalid property enum ");
        sb2.append(i);
        sb2.append(" (0x");
        Integer.toHexString(i);
        throw null;
    }

    public int getPropertyValueEnumNoThrow(int i, CharSequence charSequence) {
        int[] iArr;
        int i2;
        int findProperty = findProperty(i);
        if (findProperty == 0 || (i2 = (iArr = this.valueMaps)[findProperty + 1]) == 0) {
            return -1;
        }
        return getPropertyOrValueEnum(iArr[i2], charSequence);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0060 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int compare(java.lang.String r9, java.lang.String r10) {
        /*
        // Method dump skipped, instructions count: 130
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.UPropertyAliases.compare(java.lang.String, java.lang.String):int");
    }
}
