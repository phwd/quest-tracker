package defpackage;

import java.io.File;
import java.util.Objects;
import org.chromium.base.ContextUtils;

/* renamed from: Rd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1047Rd1 extends AbstractC0500Ie {
    public final /* synthetic */ boolean i;
    public final /* synthetic */ boolean j;
    public final /* synthetic */ C1169Td1 k;

    public C1047Rd1(C1169Td1 td1, boolean z, boolean z2) {
        this.k = td1;
        this.i = z;
        this.j = z2;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        if (!this.i) {
            C1169Td1 td1 = this.k;
            Objects.requireNonNull(td1);
            AbstractC1220Ua0.f("tabmodel", "Starting to perform legacy migration.", new Object[0]);
            File a2 = AbstractC1102Sb1.a();
            File[] listFiles = a2.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                File filesDir = ContextUtils.getApplicationContext().getFilesDir();
                File file = new File(filesDir, "tab_state");
                if (file.exists() && !file.renameTo(new File(a2, td1.b()))) {
                    AbstractC1220Ua0.a("tabmodel", AbstractC2531fV.e("Failed to rename file: ", file), new Object[0]);
                }
                File[] listFiles2 = filesDir.listFiles();
                if (listFiles2 != null) {
                    for (File file2 : listFiles2) {
                        if (AbstractC1224Ub1.c(file2.getName()) != null && !file2.renameTo(new File(a2, file2.getName()))) {
                            AbstractC1220Ua0.a("tabmodel", AbstractC2531fV.e("Failed to rename file: ", file2), new Object[0]);
                        }
                    }
                }
            }
            NU0.f8549a.m("org.chromium.chrome.browser.tabmodel.TabPersistentStore.HAS_RUN_FILE_MIGRATION", true);
            AbstractC1220Ua0.f("tabmodel", "Finished performing legacy migration.", new Object[0]);
        }
        if (this.j) {
            return null;
        }
        C1169Td1 td12 = this.k;
        Objects.requireNonNull(td12);
        AbstractC1220Ua0.f("tabmodel", "Starting to perform multi-instance migration.", new Object[0]);
        File a3 = AbstractC1102Sb1.a();
        File file3 = new File(a3, td12.b());
        File file4 = new File(a3, "tab_state");
        if (file3.exists()) {
            AbstractC1220Ua0.a("tabmodel", "New metadata file already exists", new Object[0]);
        } else if (file4.exists() && !file4.renameTo(file3)) {
            AbstractC1220Ua0.a("tabmodel", AbstractC2531fV.e("Failed to rename file: ", file4), new Object[0]);
        }
        int i2 = -1;
        int i3 = -1;
        while (i2 < 3) {
            if (i2 != 0) {
                File file5 = new File(AbstractC1041Rb1.f8842a, Integer.toString(i2));
                if (file5.exists()) {
                    File file6 = new File(file5, "tab_state");
                    if (file6.exists() && !file6.renameTo(new File(a3, C1169Td1.d(i2)))) {
                        AbstractC1220Ua0.a("tabmodel", AbstractC2531fV.e("Failed to rename file: ", file6), new Object[0]);
                    }
                    File[] listFiles3 = file5.listFiles();
                    if (listFiles3 != null) {
                        int length = listFiles3.length;
                        int i4 = 0;
                        while (i4 < length) {
                            File file7 = listFiles3[i4];
                            if (AbstractC1224Ub1.c(file7.getName()) != null) {
                                if (i2 != i3) {
                                    File file8 = new File(a3, file7.getName());
                                    if (!file8.exists() || file8.lastModified() <= file7.lastModified()) {
                                        if (!file7.renameTo(file8)) {
                                            AbstractC1220Ua0.a("tabmodel", AbstractC2531fV.e("Failed to rename file: ", file7), new Object[0]);
                                        }
                                    } else if (!file7.delete()) {
                                        AbstractC1220Ua0.a("tabmodel", AbstractC2531fV.e("Failed to delete file: ", file7), new Object[0]);
                                    }
                                } else if (!file7.delete()) {
                                    AbstractC1220Ua0.a("tabmodel", AbstractC2531fV.e("Failed to delete file: ", file7), new Object[0]);
                                }
                            }
                            i4++;
                            i3 = -1;
                        }
                    }
                    if (!file5.delete()) {
                        AbstractC1220Ua0.a("tabmodel", AbstractC2531fV.e("Failed to delete directory: ", file5), new Object[0]);
                    }
                }
            }
            i2++;
            i3 = -1;
        }
        NU0.f8549a.m("org.chromium.chrome.browser.tabmodel.TabPersistentStore.HAS_RUN_MULTI_INSTANCE_FILE_MIGRATION", true);
        AbstractC1220Ua0.f("tabmodel", "Finished performing multi-instance migration.", new Object[0]);
        return null;
    }
}
