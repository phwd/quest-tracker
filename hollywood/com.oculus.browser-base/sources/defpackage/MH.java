package defpackage;

import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.widget.CheckBox;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.download.dialogs.DownloadLaterDialogView;

/* renamed from: MH  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class MH implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        DownloadLaterDialogView downloadLaterDialogView = (DownloadLaterDialogView) obj2;
        KH0 kh0 = (KH0) obj3;
        OH0 oh0 = OH.f8615a;
        if (kh0 == oh0) {
            downloadLaterDialogView.F = (QH) uh0.g(oh0);
            return;
        }
        NH0 nh0 = OH.b;
        boolean z = true;
        if (kh0 == nh0) {
            int f = uh0.f(nh0);
            Objects.requireNonNull(downloadLaterDialogView);
            if (f == 0) {
                downloadLaterDialogView.G.f(true);
            } else if (f == 1) {
                downloadLaterDialogView.H.f(true);
            } else if (f == 2) {
                downloadLaterDialogView.I.f(true);
            }
        } else {
            NH0 nh02 = OH.c;
            if (kh0 == nh02) {
                int f2 = uh0.f(nh02);
                downloadLaterDialogView.K.setVisibility(0);
                CheckBox checkBox = downloadLaterDialogView.K;
                if (f2 != 2) {
                    z = false;
                }
                checkBox.setChecked(z);
                return;
            }
            QH0 qh0 = OH.d;
            if (kh0 == qh0) {
                downloadLaterDialogView.K.setEnabled(!uh0.h(qh0));
                return;
            }
            TH0 th0 = OH.e;
            if (kh0 == th0) {
                String str = (String) uh0.g(th0);
                Objects.requireNonNull(downloadLaterDialogView);
                if (str == null) {
                    downloadLaterDialogView.L.setVisibility(8);
                    return;
                }
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                spannableStringBuilder.append((CharSequence) str);
                spannableStringBuilder.setSpan(new StyleSpan(1), 0, str.length(), 33);
                downloadLaterDialogView.L.setMovementMethod(LinkMovementMethod.getInstance());
                downloadLaterDialogView.L.setText(FY0.a(downloadLaterDialogView.getResources().getString(R.string.f51080_resource_name_obfuscated_RES_2131952425, str), new EY0("<b>", "</b>", spannableStringBuilder), new EY0("<LINK2>", "</LINK2>", new C4467qp0(downloadLaterDialogView.getResources(), new PH(downloadLaterDialogView)))));
                downloadLaterDialogView.L.setVisibility(0);
            }
        }
    }
}
