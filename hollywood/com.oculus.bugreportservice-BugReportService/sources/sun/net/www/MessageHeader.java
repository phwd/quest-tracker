package sun.net.www;

public class MessageHeader {
    private String[] keys;
    private int nkeys;
    private String[] values;

    public MessageHeader() {
        grow();
    }

    public synchronized String findValue(String str) {
        if (str == null) {
            int i = this.nkeys;
            do {
                i--;
                if (i >= 0) {
                }
            } while (this.keys[i] != null);
            return this.values[i];
        }
        int i2 = this.nkeys;
        do {
            i2--;
            if (i2 >= 0) {
            }
        } while (!str.equalsIgnoreCase(this.keys[i2]));
        return this.values[i2];
        return null;
    }

    public synchronized void add(String str, String str2) {
        grow();
        this.keys[this.nkeys] = str;
        this.values[this.nkeys] = str2;
        this.nkeys++;
    }

    private void grow() {
        String[] strArr = this.keys;
        if (strArr == null || this.nkeys >= strArr.length) {
            int i = this.nkeys;
            String[] strArr2 = new String[(i + 4)];
            String[] strArr3 = new String[(i + 4)];
            String[] strArr4 = this.keys;
            if (strArr4 != null) {
                System.arraycopy(strArr4, 0, strArr2, 0, i);
            }
            String[] strArr5 = this.values;
            if (strArr5 != null) {
                System.arraycopy(strArr5, 0, strArr3, 0, this.nkeys);
            }
            this.keys = strArr2;
            this.values = strArr3;
        }
    }

    public synchronized String toString() {
        new StringBuilder();
        super.toString();
        throw null;
    }
}
