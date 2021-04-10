package com.facebook.papaya.fb.client.transport;

import X.C0175Gg;
import X.C0176Gh;
import X.C0547bk;
import X.C0548bl;
import X.C0899oO;
import X.KJ;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.common.jniexecutors.AndroidAsyncExecutorFactory;
import com.facebook.papaya.client.transport.ITransport;
import com.facebook.tigon.iface.TigonServiceHolder;
import com.facebook.tigon.oktigon.OkTigonService;
import com.facebook.tigon.oktigon.OkTigonServiceHolder;
import com.google.common.base.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class FBTransport extends ITransport {
    public static final long TIMEOUT = TimeUnit.SECONDS.toMillis(30);

    private native void initHybrid(TigonServiceHolder tigonServiceHolder, AndroidAsyncExecutorFactory androidAsyncExecutorFactory, String str, String str2, String str3, String str4);

    static {
        KJ.A05("papaya-fb-transport", 0);
    }

    public FBTransport(Context context, Bundle bundle) {
        super(context);
        Preconditions.checkArgument(bundle.containsKey("access_token"));
        Preconditions.checkArgument(bundle.containsKey("user_agent"));
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        String string = bundle.getString("user_agent");
        C0547bk bkVar = new C0547bk();
        bkVar.A0E = C0175Gg.A00(Build.TIME);
        bkVar.A0G.add(new C0899oO(C0176Gh.A00));
        long j = TIMEOUT;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        bkVar.A0C = C0547bk.A00(j, timeUnit);
        bkVar.A0D = C0547bk.A00(j, timeUnit);
        bkVar.A0B = C0547bk.A00(j, timeUnit);
        initHybrid(new OkTigonServiceHolder(new OkTigonService(new C0548bl(bkVar), null, string)), new AndroidAsyncExecutorFactory(newScheduledThreadPool), context.getFilesDir().getAbsolutePath(), bundle.getString("access_token"), PreferenceManager.getDefaultSharedPreferences(context).getString("fbtransport_tier", "PROD"), bundle.getString("base_url_override", OacrConstants.AUTO_SPEECH_DOMAIN));
    }
}
