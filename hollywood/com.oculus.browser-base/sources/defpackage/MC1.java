package defpackage;

import com.google.android.gms.common.Feature;

/* renamed from: MC1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class MC1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Feature f8466a;
    public static final Feature b;
    public static final Feature c;
    public static final Feature d;
    public static final Feature[] e;

    static {
        Feature feature = new Feature("sms_code_autofill", 2);
        f8466a = feature;
        Feature feature2 = new Feature("sms_code_browser", 2);
        b = feature2;
        Feature feature3 = new Feature("sms_retrieve", 1);
        c = feature3;
        Feature feature4 = new Feature("user_consent", 3);
        d = feature4;
        e = new Feature[]{feature, feature2, feature3, feature4};
    }
}
