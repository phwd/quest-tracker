package defpackage;

import J.N;
import org.chromium.base.ThreadUtils;
import org.chromium.components.search_engines.TemplateUrlService;

/* renamed from: Hf1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0444Hf1 {

    /* renamed from: a  reason: collision with root package name */
    public static TemplateUrlService f8171a;

    public static TemplateUrlService a() {
        Object obj = ThreadUtils.f10596a;
        if (f8171a == null) {
            f8171a = (TemplateUrlService) N.MSnR7M2J();
        }
        return f8171a;
    }
}
