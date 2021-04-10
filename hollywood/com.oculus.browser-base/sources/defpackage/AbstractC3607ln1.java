package defpackage;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* renamed from: ln1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3607ln1 {

    /* renamed from: a  reason: collision with root package name */
    public static AbstractC2924hn1 f10373a = new C4256pc();
    public static ThreadLocal b = new ThreadLocal();
    public static ArrayList c = new ArrayList();

    public static C4931ta a() {
        C4931ta taVar;
        WeakReference weakReference = (WeakReference) b.get();
        if (weakReference != null && (taVar = (C4931ta) weakReference.get()) != null) {
            return taVar;
        }
        C4931ta taVar2 = new C4931ta();
        b.set(new WeakReference(taVar2));
        return taVar2;
    }
}
