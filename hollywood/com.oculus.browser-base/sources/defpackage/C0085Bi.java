package defpackage;

import android.graphics.drawable.Drawable;
import android.widget.TextView;
import java.util.Objects;
import org.chromium.chrome.browser.bookmarks.bottomsheet.BookmarkBottomSheetFolderRow;

/* renamed from: Bi  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0085Bi implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        BookmarkBottomSheetFolderRow bookmarkBottomSheetFolderRow = (BookmarkBottomSheetFolderRow) obj2;
        KH0 kh0 = (KH0) obj3;
        OH0 oh0 = AbstractC0390Gi.f8102a;
        if (oh0.equals(kh0)) {
            bookmarkBottomSheetFolderRow.U.setText((CharSequence) uh0.g(oh0));
            return;
        }
        OH0 oh02 = AbstractC0390Gi.b;
        if (oh02.equals(kh0)) {
            CharSequence charSequence = (CharSequence) uh0.g(oh02);
            TextView textView = bookmarkBottomSheetFolderRow.V;
            if (charSequence == null) {
                charSequence = "";
            }
            textView.setText(charSequence);
            return;
        }
        OH0 oh03 = AbstractC0390Gi.c;
        if (oh03.equals(kh0)) {
            C1754aw0 aw0 = (C1754aw0) uh0.g(oh03);
            Objects.requireNonNull(bookmarkBottomSheetFolderRow);
            bookmarkBottomSheetFolderRow.e0 = ((Integer) aw0.b).intValue();
            bookmarkBottomSheetFolderRow.l((Drawable) aw0.f9500a);
            return;
        }
        OH0 oh04 = AbstractC0390Gi.d;
        if (oh04.equals(kh0)) {
            bookmarkBottomSheetFolderRow.d0 = (Runnable) uh0.g(oh04);
        }
    }
}
