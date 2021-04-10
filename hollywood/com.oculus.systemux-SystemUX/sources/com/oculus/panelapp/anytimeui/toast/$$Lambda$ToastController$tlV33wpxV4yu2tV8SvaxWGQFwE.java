package com.oculus.panelapp.anytimeui.toast;

import com.oculus.panelapp.anytimeui.toast.policies.IPerNotifPassFailPolicy;
import java.util.function.Consumer;

/* renamed from: com.oculus.panelapp.anytimeui.toast.-$$Lambda$ToastController$tlV33wpxV4yu2tV8SvaxWG-QFwE  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$ToastController$tlV33wpxV4yu2tV8SvaxWGQFwE implements Consumer {
    public static final /* synthetic */ $$Lambda$ToastController$tlV33wpxV4yu2tV8SvaxWGQFwE INSTANCE = new $$Lambda$ToastController$tlV33wpxV4yu2tV8SvaxWGQFwE();

    private /* synthetic */ $$Lambda$ToastController$tlV33wpxV4yu2tV8SvaxWGQFwE() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((IPerNotifPassFailPolicy) obj).destroy();
    }
}
