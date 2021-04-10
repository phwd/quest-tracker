package org.chromium.components.infobars;

import J.N;
import android.graphics.Bitmap;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class InfoBar implements C10 {
    public boolean F;
    public boolean G = true;
    public long H;

    public InfoBar(int i, int i2, CharSequence charSequence, Bitmap bitmap) {
    }

    @Override // defpackage.C10
    public CharSequence b() {
        return "";
    }

    public final boolean closeInfoBar() {
        if (this.F) {
            return false;
        }
        this.F = true;
        throw null;
    }

    @Override // defpackage.C10
    public boolean d() {
        return this.G;
    }

    public int h() {
        long j = this.H;
        if (j == 0) {
            return -1;
        }
        return N.MIZvQmze(j, this);
    }

    public void i(int i) {
        long j = this.H;
        if (j != 0) {
            N.MQGsrOhB(j, this, i);
        }
    }

    public void j() {
        long j = this.H;
        if (j != 0 && !this.F) {
            N.MKozrBH2(j, this);
        }
    }

    public void resetNativeInfoBar() {
        this.H = 0;
    }

    public final void setNativeInfoBar(long j) {
        this.H = j;
    }
}
