package android.icu.util;

import java.io.Serializable;

/* access modifiers changed from: package-private */
public final class STZInfo implements Serializable {
    private static final long serialVersionUID = -7849612037842370168L;
    boolean ea;
    int edm;
    int edw;
    int edwm;
    int em = -1;
    int et;
    boolean sa;
    int sdm;
    int sdw;
    int sdwm;
    int sm = -1;
    int st;
    int sy = -1;

    STZInfo() {
    }

    /* access modifiers changed from: package-private */
    public void setStart(int sm2, int sdwm2, int sdw2, int st2, int sdm2, boolean sa2) {
        this.sm = sm2;
        this.sdwm = sdwm2;
        this.sdw = sdw2;
        this.st = st2;
        this.sdm = sdm2;
        this.sa = sa2;
    }

    /* access modifiers changed from: package-private */
    public void setEnd(int em2, int edwm2, int edw2, int et2, int edm2, boolean ea2) {
        this.em = em2;
        this.edwm = edwm2;
        this.edw = edw2;
        this.et = et2;
        this.edm = edm2;
        this.ea = ea2;
    }

    /* access modifiers changed from: package-private */
    public void applyTo(SimpleTimeZone stz) {
        int i = this.sy;
        if (i != -1) {
            stz.setStartYear(i);
        }
        int i2 = this.sm;
        if (i2 != -1) {
            int i3 = this.sdm;
            if (i3 == -1) {
                stz.setStartRule(i2, this.sdwm, this.sdw, this.st);
            } else {
                int i4 = this.sdw;
                if (i4 == -1) {
                    stz.setStartRule(i2, i3, this.st);
                } else {
                    stz.setStartRule(i2, i3, i4, this.st, this.sa);
                }
            }
        }
        int i5 = this.em;
        if (i5 != -1) {
            int i6 = this.edm;
            if (i6 == -1) {
                stz.setEndRule(i5, this.edwm, this.edw, this.et);
                return;
            }
            int i7 = this.edw;
            if (i7 == -1) {
                stz.setEndRule(i5, i6, this.et);
            } else {
                stz.setEndRule(i5, i6, i7, this.et, this.ea);
            }
        }
    }
}
