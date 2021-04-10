package X;

public final class L8 {
    public int A00 = -1;
    public short[] A01 = new short[15];

    public final void A00(short s) {
        short[] sArr = this.A01;
        short[] sArr2 = sArr;
        int length = sArr.length;
        if (length == this.A00 + 1) {
            sArr2 = new short[(length << 1)];
            System.arraycopy(sArr, 0, sArr2, 0, length);
            this.A01 = sArr2;
        }
        int i = this.A00 + 1;
        this.A00 = i;
        sArr2[i] = s;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("<ShortStack vector:[");
        int i = 0;
        while (true) {
            short[] sArr = this.A01;
            if (i < sArr.length) {
                if (i != 0) {
                    sb.append(" ");
                }
                int i2 = this.A00;
                if (i == i2) {
                    sb.append(">>");
                }
                sb.append((int) sArr[i]);
                if (i == i2) {
                    sb.append("<<");
                }
                i++;
            } else {
                sb.append("]>");
                return sb.toString();
            }
        }
    }
}
