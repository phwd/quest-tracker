package android.icu.text;

import android.icu.impl.StandardPlural;
import android.icu.util.Freezable;
import java.util.Arrays;

public final class PluralRanges implements Freezable, Comparable {
    private boolean[] explicit = new boolean[StandardPlural.COUNT];
    private volatile boolean isFrozen;
    private Matrix matrix = new Matrix();

    /* access modifiers changed from: private */
    public static final class Matrix implements Comparable, Cloneable {
        private byte[] data;

        Matrix() {
            int i = StandardPlural.COUNT;
            this.data = new byte[(i * i)];
            int i2 = 0;
            while (true) {
                byte[] bArr = this.data;
                if (i2 < bArr.length) {
                    bArr[i2] = -1;
                    i2++;
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void setIfNew(StandardPlural standardPlural, StandardPlural standardPlural2, StandardPlural standardPlural3) {
            byte b;
            byte b2 = this.data[(standardPlural.ordinal() * StandardPlural.COUNT) + standardPlural2.ordinal()];
            if (b2 < 0) {
                byte[] bArr = this.data;
                int ordinal = (standardPlural.ordinal() * StandardPlural.COUNT) + standardPlural2.ordinal();
                if (standardPlural3 == null) {
                    b = -1;
                } else {
                    b = (byte) standardPlural3.ordinal();
                }
                bArr[ordinal] = b;
                return;
            }
            throw new IllegalArgumentException("Previously set value for <" + standardPlural + ", " + standardPlural2 + ", " + StandardPlural.VALUES.get(b2) + ">");
        }

        /* access modifiers changed from: package-private */
        public StandardPlural get(StandardPlural standardPlural, StandardPlural standardPlural2) {
            byte b = this.data[(standardPlural.ordinal() * StandardPlural.COUNT) + standardPlural2.ordinal()];
            if (b < 0) {
                return null;
            }
            return (StandardPlural) StandardPlural.VALUES.get(b);
        }

        public int hashCode() {
            int i = 0;
            int i2 = 0;
            while (true) {
                byte[] bArr = this.data;
                if (i >= bArr.length) {
                    return i2;
                }
                i2 = (i2 * 37) + bArr[i];
                i++;
            }
        }

        public boolean equals(Object obj) {
            if ((obj instanceof Matrix) && compareTo((Matrix) obj) == 0) {
                return true;
            }
            return false;
        }

        public int compareTo(Matrix matrix) {
            int i = 0;
            while (true) {
                byte[] bArr = this.data;
                if (i >= bArr.length) {
                    return 0;
                }
                int i2 = bArr[i] - matrix.data[i];
                if (i2 != 0) {
                    return i2;
                }
                i++;
            }
        }

        public Matrix clone() {
            Matrix matrix = new Matrix();
            matrix.data = (byte[]) this.data.clone();
            return matrix;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            StandardPlural[] values = StandardPlural.values();
            for (StandardPlural standardPlural : values) {
                StandardPlural[] values2 = StandardPlural.values();
                for (StandardPlural standardPlural2 : values2) {
                    StandardPlural standardPlural3 = get(standardPlural, standardPlural2);
                    if (standardPlural3 != null) {
                        sb.append(standardPlural + " & " + standardPlural2 + " → " + standardPlural3 + ";\n");
                    }
                }
            }
            return sb.toString();
        }
    }

    public void add(StandardPlural standardPlural, StandardPlural standardPlural2, StandardPlural standardPlural3) {
        if (!this.isFrozen) {
            this.explicit[standardPlural3.ordinal()] = true;
            if (standardPlural == null) {
                StandardPlural[] values = StandardPlural.values();
                for (StandardPlural standardPlural4 : values) {
                    if (standardPlural2 == null) {
                        for (StandardPlural standardPlural5 : StandardPlural.values()) {
                            this.matrix.setIfNew(standardPlural4, standardPlural5, standardPlural3);
                        }
                    } else {
                        this.explicit[standardPlural2.ordinal()] = true;
                        this.matrix.setIfNew(standardPlural4, standardPlural2, standardPlural3);
                    }
                }
            } else if (standardPlural2 == null) {
                this.explicit[standardPlural.ordinal()] = true;
                for (StandardPlural standardPlural6 : StandardPlural.values()) {
                    this.matrix.setIfNew(standardPlural, standardPlural6, standardPlural3);
                }
            } else {
                this.explicit[standardPlural.ordinal()] = true;
                this.explicit[standardPlural2.ordinal()] = true;
                this.matrix.setIfNew(standardPlural, standardPlural2, standardPlural3);
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PluralRanges)) {
            return false;
        }
        PluralRanges pluralRanges = (PluralRanges) obj;
        return this.matrix.equals(pluralRanges.matrix) && Arrays.equals(this.explicit, pluralRanges.explicit);
    }

    public int hashCode() {
        return this.matrix.hashCode();
    }

    public int compareTo(PluralRanges pluralRanges) {
        return this.matrix.compareTo(pluralRanges.matrix);
    }

    public PluralRanges freeze() {
        this.isFrozen = true;
        return this;
    }

    public String toString() {
        return this.matrix.toString();
    }
}
