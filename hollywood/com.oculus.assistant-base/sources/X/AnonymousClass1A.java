package X;

import android.app.PendingIntent;
import android.os.Bundle;
import androidx.core.graphics.drawable.IconCompat;
import com.facebook.assistant.oacr.OacrConstants;
import com.oculus.assistant.R;

/* renamed from: X.1A  reason: invalid class name */
public final class AnonymousClass1A {
    public int A00;
    public PendingIntent A01;
    public IconCompat A02;
    public CharSequence A03;
    public boolean A04;
    public boolean A05 = true;
    public final Bundle A06;

    public AnonymousClass1A(CharSequence charSequence, PendingIntent pendingIntent) {
        IconCompat iconCompat = new IconCompat(2);
        iconCompat.A00 = R.drawable.common_full_open_on_phone;
        iconCompat.A06 = OacrConstants.AUTO_SPEECH_DOMAIN;
        Bundle bundle = new Bundle();
        this.A02 = iconCompat;
        if (iconCompat.A02() == 2) {
            this.A00 = iconCompat.A01();
        }
        this.A03 = AnonymousClass1C.A00(charSequence);
        this.A01 = pendingIntent;
        this.A06 = bundle;
        this.A04 = true;
        this.A05 = true;
    }
}
