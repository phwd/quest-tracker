package android.icu.text;

final class RBNFChinesePostProcessor implements RBNFPostProcessor {
    private static final String[] rulesetNames = {"%traditional", "%simplified", "%accounting", "%time"};
    private int format;
    private boolean longForm;

    RBNFChinesePostProcessor() {
    }

    @Override // android.icu.text.RBNFPostProcessor
    public void init(RuleBasedNumberFormat formatter, String rules) {
    }

    @Override // android.icu.text.RBNFPostProcessor
    public void process(StringBuilder buf, NFRuleSet ruleSet) {
        int s;
        int s2;
        String name = ruleSet.getName();
        int i = 0;
        while (true) {
            String[] strArr = rulesetNames;
            if (i >= strArr.length) {
                break;
            } else if (strArr[i].equals(name)) {
                this.format = i;
                this.longForm = i == 1 || i == 3;
            } else {
                i++;
            }
        }
        if (this.longForm) {
            int i2 = buf.indexOf("*");
            while (i2 != -1) {
                buf.delete(i2, i2 + 1);
                i2 = buf.indexOf("*", i2);
            }
            return;
        }
        String[][] markers = {new String[]{"萬", "億", "兆", "〇"}, new String[]{"万", "亿", "兆", "〇"}, new String[]{"萬", "億", "兆", "零"}};
        String[] m = markers[this.format];
        for (int i3 = 0; i3 < m.length - 1; i3++) {
            int n = buf.indexOf(m[i3]);
            if (n != -1) {
                buf.insert(m[i3].length() + n, '|');
            }
        }
        int x = buf.indexOf("點");
        if (x == -1) {
            x = buf.length();
        }
        int n2 = 0;
        int n3 = -1;
        String ling = markers[this.format][3];
        while (x >= 0) {
            int m2 = buf.lastIndexOf("|", x);
            int nn = buf.lastIndexOf(ling, x);
            int ns = 0;
            if (nn > m2) {
                ns = (nn <= 0 || buf.charAt(nn + -1) == '*') ? 1 : 2;
            }
            x = m2 - 1;
            switch ((n2 * 3) + ns) {
                case 0:
                    s2 = ns;
                    s = -1;
                    break;
                case 1:
                    s2 = ns;
                    s = nn;
                    break;
                case 2:
                    s2 = ns;
                    s = -1;
                    break;
                case 3:
                    s2 = ns;
                    s = -1;
                    break;
                case 4:
                    buf.delete(nn - 1, ling.length() + nn);
                    s2 = 0;
                    s = -1;
                    break;
                case 5:
                    buf.delete(n3 - 1, ling.length() + n3);
                    s2 = ns;
                    s = -1;
                    break;
                case 6:
                    s2 = ns;
                    s = -1;
                    break;
                case 7:
                    buf.delete(nn - 1, ling.length() + nn);
                    s2 = 0;
                    s = -1;
                    break;
                case 8:
                    s2 = ns;
                    s = -1;
                    break;
                default:
                    throw new IllegalStateException();
            }
            n3 = s;
            n2 = s2;
        }
        int i4 = buf.length();
        while (true) {
            i4--;
            if (i4 >= 0) {
                char c = buf.charAt(i4);
                if (c == '*' || c == '|') {
                    buf.delete(i4, i4 + 1);
                }
            } else {
                return;
            }
        }
    }
}
