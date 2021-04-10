package defpackage;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import org.chromium.chrome.browser.autofill.PersonalDataManager;

/* renamed from: qB  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4362qB implements TextWatcher {
    public boolean F = true;
    public boolean G;
    public boolean H;

    public static void a(Editable editable) {
        int[] iArr;
        if (PersonalDataManager.c().a(editable.toString(), false).equals("amex")) {
            iArr = new int[]{4, 11};
        } else {
            iArr = new int[]{4, 9, 14};
        }
        for (int i : iArr) {
            if (editable.length() > i) {
                editable.insert(i, " ");
            }
        }
    }

    public void afterTextChanged(Editable editable) {
        if (!this.G) {
            this.G = true;
            if (this.F) {
                int indexOf = TextUtils.indexOf(editable, " ");
                while (indexOf >= 0) {
                    int i = indexOf + 1;
                    editable.delete(indexOf, i);
                    indexOf = TextUtils.indexOf(editable, " ", i);
                }
                if (editable.length() > 16) {
                    this.H = true;
                    this.F = false;
                } else {
                    a(editable);
                }
            } else if (this.H && editable.length() <= 16) {
                this.H = false;
                this.F = true;
                a(editable);
            }
            if (editable.length() == 0) {
                this.F = true;
            }
            this.G = false;
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (!this.G && this.F && i3 > 0) {
            int i4 = i3 + i;
            if ((TextUtils.indexOf(charSequence, " ", i, i4) == -1 && TextUtils.indexOf(charSequence, "-", i, i4) == -1) ? false : true) {
                this.F = false;
            }
        }
    }
}
