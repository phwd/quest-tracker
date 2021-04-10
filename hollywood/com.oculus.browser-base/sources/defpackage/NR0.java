package defpackage;

import java.util.Objects;
import org.chromium.ui.base.SelectFileDialog;

/* renamed from: NR0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class NR0 implements HB0 {

    /* renamed from: a  reason: collision with root package name */
    public final SelectFileDialog f8546a;
    public final boolean b;
    public final String[] c;

    public NR0(SelectFileDialog selectFileDialog, boolean z, String[] strArr, String str) {
        this.f8546a = selectFileDialog;
        this.b = z;
        this.c = strArr;
    }

    @Override // defpackage.HB0
    public void b(String[] strArr, int[] iArr) {
        SelectFileDialog selectFileDialog = this.f8546a;
        boolean z = this.b;
        String[] strArr2 = this.c;
        Objects.requireNonNull(selectFileDialog);
        for (int i = 0; i < iArr.length; i++) {
            if (iArr[i] == -1) {
                if (selectFileDialog.h) {
                    selectFileDialog.m();
                    return;
                }
                if (z) {
                    if (strArr.length != strArr2.length) {
                        throw new RuntimeException(String.format("Permissions arrays misaligned: %d != %d", Integer.valueOf(strArr.length), Integer.valueOf(strArr2.length)));
                    } else if (!strArr[i].equals(strArr2[i])) {
                        throw new RuntimeException(String.format("Permissions arrays don't match: %s != %s", strArr[i], strArr2[i]));
                    }
                }
                if (z && strArr[i].equals("android.permission.READ_EXTERNAL_STORAGE")) {
                    selectFileDialog.m();
                    return;
                }
            }
        }
        selectFileDialog.k();
    }
}
