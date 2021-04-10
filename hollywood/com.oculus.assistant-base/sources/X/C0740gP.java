package X;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.facebook.assistant.clientplatform.clientstate.AssistantErrorType;
import com.facebook.assistant.clientplatform.logger.AssistantLogger;
import com.facebook.assistant.oacr.AccountsApi;
import com.facebook.assistant.oacr.AudioReader;
import com.facebook.assistant.oacr.Oacr;
import com.facebook.assistant.oacr.OacrApi;
import com.facebook.assistant.oacr.OacrClientListener;
import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.assistant.oacr.OacrUtil;
import com.facebook.assistant.oacr.OacrVoiceInteractionListener;
import com.facebook.assistant.oacr.ResourceApi;
import com.facebook.assistant.oacr.TtsApi;
import com.facebook.assistant.oacr.config.OacrTrimSpec;
import com.facebook.common.jniexecutors.AndroidAsyncExecutorFactory;
import com.facebook.debug.tracer.Tracer;
import com.facebook.hyperthrift.HyperThriftBase;
import com.facebook.messenger.assistant.thrift.OacrInitParams;
import com.facebook.proxygen.HTTPClient;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;

/* renamed from: X.gP  reason: case insensitive filesystem */
public final class C0740gP {
    public static Handler A16 = new Handler(A17.getLooper());
    public static final HandlerThread A17;
    public Context A00;
    public g1 A01;
    public AccountsApi A02;
    public AudioReader A03;
    public Oacr A04;
    public OacrApi A05;
    public ResourceApi A06;
    public TtsApi A07;
    public BN A08 = BN.RAW;
    public BQ A09;
    public Ed A0A = null;
    public l0 A0B;
    public HTTPClient A0C;
    public String A0D = OacrConstants.AUTO_SPEECH_DOMAIN;
    public C00608k A0E;
    public final Handler A0F = new Handler(C0112Aq.A00().A02.getLooper());
    public final AnonymousClass8O A0G = new AnonymousClass8O(this);
    public final AnonymousClass8P A0H;
    public final AnonymousClass8S A0I;
    public final AnonymousClass8X A0J;
    public final C0651dw A0K;
    public final AnonymousClass8c A0L;
    public final AnonymousClass8c A0M = new C0706fm(this);
    public final C00568g A0N = new C00568g();
    public final AbstractC0791he A0O;
    public final AbstractC0105Aj A0P = new C0728gB(this);
    public final AbstractC0105Aj A0Q = new C0738gN(this);
    public final AbstractC0105Aj A0R = new C0705fl(this);
    public final AbstractC0105Aj A0S = new C0700fg(this);
    public final AbstractC0105Aj A0T = new C0698fe(this);
    public final AbstractC0105Aj A0U = new C0699ff(this);
    public final AbstractC0105Aj A0V = new C0697fd(this);
    public final AbstractC0105Aj A0W = new C0737gM(this);
    public final AbstractC0105Aj A0X = new C0704fk(this);
    public final AbstractC0105Aj A0Y = new C0707fn(this);
    public final AbstractC0105Aj A0Z = new C0734gJ(this);
    public final AbstractC0105Aj A0a = new C0696fc(this);
    public final AbstractC0105Aj A0b = new C0739gO(this);
    public final AbstractC0105Aj A0c = new C0735gK(this);
    public final AbstractC0105Aj A0d = new C0703fj(this);
    public final AbstractC0105Aj A0e = new C0701fh(this);
    public final AbstractC0105Aj A0f = new C0702fi(this);
    public final AbstractC0105Aj A0g = new C0736gL(this);
    public final C0112Aq A0h = C0112Aq.A00();
    public final Oacr.AudioPlayer A0i = new C0708fo(this);
    public final Oacr.DeviceContextProvider A0j = new C0709fp(this);
    public final Oacr.MobileConfigProvider A0k = new C0712fs(this);
    public final Oacr.ReadinessStatusCallback A0l = new C0710fq(this);
    public final Oacr.ResourceStatusCallback A0m = new C0711fr(this);
    public final Oacr.TtsVoiceConfigurationCallback A0n = new C0713ft(this);
    public final OacrClientListener A0o = new C0720g2(this);
    public final OacrVoiceInteractionListener A0p = new C0719g0(this);
    public final AndroidAsyncExecutorFactory A0q;
    public final AndroidAsyncExecutorFactory A0r;
    public final w1 A0s;
    public final C1383yK A0t;
    public final Object A0u = new Object();
    public final Object A0v = new Object();
    public final Object A0w = new Object();
    public final Object A0x = new Object();
    public final Map A0y = new HashMap();
    public final Set A0z = new HashSet();
    public final Set A10 = new HashSet();
    public final ScheduledExecutorService A11;
    public final ScheduledExecutorService A12;
    public final AtomicBoolean A13 = new AtomicBoolean(false);
    public final AtomicBoolean A14 = new AtomicBoolean(false);
    public final AtomicInteger A15 = new AtomicInteger(Math.abs(new Random().nextInt()) % 65535);

    static {
        HandlerThread handlerThread = new HandlerThread("AssistantClientPlatform");
        A17 = handlerThread;
        handlerThread.start();
    }

    private void A00() {
        Tracer.A01("initialize connection");
        C0783hH hHVar = C00919v.A00;
        hHVar.A06("sdk");
        try {
            C0139Dd.A09("AssistantClientPlatform", "Initialize Connection");
            this.A0D = C7.A00().toString();
            AnonymousClass8P r2 = this.A0H;
            OacrTrimSpec oacrTrimSpec = r2.A04;
            hHVar.A03(EnumC00899t.OACR_TRIM_NAME, (String) oacrTrimSpec.A00(0));
            try {
                String canonicalPath = this.A00.getFilesDir().getCanonicalPath();
                String str = File.separator;
                if (!canonicalPath.endsWith(str)) {
                    canonicalPath = AnonymousClass08.A04(canonicalPath, str);
                }
                C0139Dd.A0B("AssistantClientPlatform", AnonymousClass08.A05("using ", canonicalPath, " for storing data"));
                URI create = URI.create(r2.A0D);
                boolean equals = create.getScheme().equals("https");
                int port = create.getPort();
                if (port == -1) {
                    port = 80;
                    if (equals) {
                        port = 443;
                    }
                }
                String str2 = (String) this.A0t.A01().A04().A00(32);
                C0876mo moVar = new C0876mo();
                moVar.A02(9, this.A0s.A00().mAuthToken);
                moVar.A02(10, r2.A0B);
                String host = create.getHost();
                Matcher matcher = C0116Av.A00.matcher(host);
                if (matcher.find()) {
                    host = matcher.group(1);
                }
                moVar.A02(7, host);
                moVar.A02(8, Integer.valueOf(port));
                moVar.A02(11, Boolean.valueOf(equals));
                moVar.A02(3, r2.A0G);
                moVar.A02(0, r2.A0F);
                moVar.A02(5, r2.A0C);
                moVar.A02(2, r2.A0A);
                moVar.A02(4, OacrConstants.DEVICE_NAME);
                moVar.A02(1, str2);
                moVar.A02(6, canonicalPath);
                moVar.A02(12, OacrUtil.getInitParamsCustomHeaders(r2.A0I));
                moVar.A02(13, Boolean.valueOf(BL.A00.contains(create.toString())));
                moVar.A02(15, false);
                moVar.A02(14, false);
                moVar.A02(16, AnonymousClass8Q.A00(r2, this.A00));
                moVar.A02(17, new AnonymousClass8K(this));
                Object[] A032 = moVar.A03();
                HyperThriftBase.Builder.A01(A032, 12);
                HyperThriftBase.Builder.A01(A032, 17);
                OacrInitParams oacrInitParams = new OacrInitParams();
                oacrInitParams.A02("com.facebook.messenger.assistant.thrift.OacrInitParams", A032);
                A16.post(new C0721g3(this, oacrTrimSpec, str2, OacrUtil.serialize("com.facebook.messenger.assistant.thrift.OacrInitParams", oacrInitParams)));
                AnonymousClass8X r3 = this.A0J;
                r3.A02.post(new AnonymousClass8W(r3, null));
                this.A13.set(true);
            } catch (IOException unused) {
                C0139Dd.A0B("AssistantClientPlatform", "couldn't get a canonical path for context.getFilesDir()");
                throw new InvalidParameterException("couldn't get a canonical path for context.getFilesDir()");
            }
        } finally {
            Tracer.A00();
        }
    }

    public static void A01(C0740gP gPVar) {
        AtomicBoolean atomicBoolean = gPVar.A14;
        if (atomicBoolean.get()) {
            atomicBoolean.set(false);
        }
        AudioReader audioReader = gPVar.A03;
        if (audioReader != null) {
            if (!audioReader.mStop) {
                gPVar.A03.mStop = true;
            }
            gPVar.A03 = null;
        }
    }

    public static void A02(C0740gP gPVar, AssistantErrorType assistantErrorType, String str) {
        C00879r.A00.A07(assistantErrorType);
        gPVar.A0h.A01(new C0805ht(assistantErrorType));
        for (C0741gQ gQVar : gPVar.A10) {
            gQVar.A00.logError(assistantErrorType.name(), str);
        }
    }

    public static void A03(C0740gP gPVar, EnumC00528b r3) {
        gPVar.A0h.A01(new C0809hx(r3));
        for (C0741gQ gQVar : gPVar.A10) {
            gQVar.A00.logStateChanged(r3.toString());
        }
    }

    public static void A04(C0740gP gPVar, C0813iG iGVar) {
        AssistantErrorType assistantErrorType = AssistantErrorType.UNKNOWN;
        switch (iGVar.A00.intValue()) {
            case 0:
                assistantErrorType = AssistantErrorType.MIC_NOT_AVAILABLE;
                break;
            case 2:
                assistantErrorType = AssistantErrorType.SERVER_ERROR;
                break;
            case 3:
                assistantErrorType = AssistantErrorType.CONNECTION_NOT_AVAILABLE;
                break;
            case 5:
                assistantErrorType = AssistantErrorType.RUNTIME;
                break;
        }
        A02(gPVar, assistantErrorType, iGVar.A01);
        A08(gPVar, false);
    }

    public static void A05(C0740gP gPVar, C0814iH iHVar) {
        String str;
        int intValue = iHVar.A00.intValue();
        switch (intValue) {
            case 0:
                C0139Dd.A0B("AssistantClientPlatform", "OacrKeywordVerificationMessage on keyword found");
                break;
            case 1:
                C0139Dd.A0B("AssistantClientPlatform", "OacrKeywordVerificationMessage on keyword NOT found");
                synchronized (gPVar.A0I) {
                }
                A08(gPVar, false);
                C00879r.A00.A08(AnonymousClass09.A00);
                break;
        }
        C0112Aq aq = gPVar.A0h;
        if (1 - intValue != 0) {
            str = "FOUND";
        } else {
            str = "NOT_FOUND";
        }
        if (str.equals("FOUND") || str.equals("NOT_FOUND")) {
            aq.A01(new i3());
            return;
        }
        throw new IllegalArgumentException(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:133:0x02c6, code lost:
        if (r1 == false) goto L_0x02c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x02ce, code lost:
        if (r3.mIsSensitiveTranscription != false) goto L_0x02d0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0216  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0224  */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x024c  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0266  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x02c1  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x02cb  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x02d8  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x0300  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0137  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x013d  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0140  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A06(X.C0740gP r17, X.iK r18) {
        /*
        // Method dump skipped, instructions count: 918
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0740gP.A06(X.gP, X.iK):void");
    }

    public static void A07(C0740gP gPVar, C0817iM iMVar) {
        if (!iMVar.A07) {
            C00608k r0 = gPVar.A0E;
            if (r0 != null) {
                r0.A0C.A47(null, iMVar);
                return;
            }
            return;
        }
        String str = iMVar.A04;
        boolean z = iMVar.A06;
        if (z || !str.isEmpty()) {
            C1005qY qYVar = new C1005qY();
            qYVar.mTranscription = str;
            qYVar.mAudioDurationMs = iMVar.A00;
            qYVar.mEndpointingLagMs = iMVar.A01;
            String str2 = iMVar.A03;
            qYVar.mShortwaveId = str2;
            boolean z2 = !z;
            String str3 = iMVar.A02;
            if (z2) {
                gPVar.A0h.A01(new C0811hz(str));
            } else {
                w1 w1Var = gPVar.A0s;
                if (w1Var.A00() != null) {
                    AssistantLogger.getConsoleUrlForShortwave(str2, w1Var.A00().mUserId);
                    gPVar.A0h.A01(new C0806hu(str, str3, null));
                }
            }
            for (C0741gQ gQVar : gPVar.A10) {
                AssistantLogger assistantLogger = gQVar.A00;
                if (z2) {
                    assistantLogger.logPartialTranscription(str);
                } else {
                    assistantLogger.logFinalTranscription(str);
                }
            }
            if (gPVar.A0s.A00() != null) {
                gPVar.A0h.A01(new C0804hs());
            }
            if (z) {
                A03(gPVar, EnumC00528b.PROCESSING);
                Integer num = qYVar.mEndpointingLagMs;
                if (num != null) {
                    C00839n.A00.A00((long) qYVar.mAudioDurationMs, (long) num.intValue());
                    hG hGVar = C00879r.A00;
                    hGVar.A03(EnumC00899t.OACR_MODULE, (String) gPVar.A0H.A04.A00(4));
                    hGVar.A03(EnumC00899t.PROTOCOL, OacrConstants.OACR_PROTOCOL_NAME);
                }
            }
        }
    }

    public static void A08(C0740gP gPVar, boolean z) {
        String str;
        synchronized (gPVar.A0I) {
        }
        A03(gPVar, EnumC00528b.INACTIVE);
        gPVar.A0h.A01(new C0807hv(z));
        for (C0741gQ gQVar : gPVar.A10) {
            AssistantLogger assistantLogger = gQVar.A00;
            if (z) {
                str = "Successful";
            } else {
                str = "Failure";
            }
            assistantLogger.logLocalEvent("Complete", str);
        }
        C00799i.A00.stopInteraction();
        A01(gPVar);
        gPVar.A0N.A00();
    }

    public final AnonymousClass8J A09(String str, String str2) {
        AnonymousClass8J r0;
        synchronized (this.A0u) {
            Map map = this.A0y;
            if (!map.containsKey(str)) {
                map.put(str, new AnonymousClass8J(this.A0M, str, str2));
                ((AnonymousClass8J) map.get(str)).A00 = new C0723g5(this);
            }
            r0 = (AnonymousClass8J) map.get(str);
        }
        return r0;
    }

    public final C00608k A0A() {
        C00608k r5;
        synchronized (this.A0v) {
            r5 = this.A0E;
            if (r5 == null) {
                C00568g r4 = this.A0N;
                FS fs = new FS();
                fs.A08 = this.A0s.A00().mAuthToken;
                AnonymousClass8P r52 = this.A0H;
                fs.A09 = r52.A0F;
                fs.A05 = r52.A0B;
                fs.A07 = r52.A0C;
                fs.A04 = 0L;
                fs.A02 = this.A0F;
                fs.A00 = r52.A01;
                fs.A0D = true;
                fs.A01 = r52.A02;
                if (!TextUtils.isEmpty(r52.A0H)) {
                    fs.A0B = r52.A0H;
                }
                r5 = new C00608k(r4, fs, r52.A01, r52.A00);
                this.A0E = r5;
            }
        }
        return r5;
    }

    public final void A0B() {
        this.A0O.A3g();
        C0112Aq aq = this.A0h;
        aq.A04(C0800ho.class, this.A0Q);
        aq.A04(C0799hn.class, this.A0P);
        aq.A04(i1.class, this.A0g);
        aq.A04(C0801hp.class, this.A0W);
        aq.A04(hS.class, this.A0Y);
        aq.A04(hU.class, this.A0R);
        aq.A04(C0787ha.class, this.A0V);
        aq.A04(hY.class, this.A0T);
        aq.A04(hZ.class, this.A0U);
        aq.A04(hX.class, this.A0S);
        aq.A04(hT.class, this.A0X);
        aq.A04(iF.class, this.A0Z);
        aq.A04(C0824iU.class, this.A0c);
        this.A0K.A3g();
    }

    public final void A0C() {
        C0139Dd.A09("AssistantClientPlatform", "reInitializeConnection");
        AtomicBoolean atomicBoolean = this.A13;
        if (atomicBoolean.get()) {
            A0D();
        }
        try {
            A00();
            atomicBoolean.set(true);
        } catch (Exception e) {
            C00799i.A00.logException("Error initializing connection to Assistant", e);
        }
    }

    public final void A0D() {
        C0139Dd.A09("AssistantClientPlatform", "shutdown AssistantClientPlatform");
        if (this.A14.get()) {
            synchronized (this.A0I) {
            }
            A08(this, false);
        }
        A0B();
        A16.removeCallbacksAndMessages(null);
        A16.post(new C0722g4(this));
        this.A13.set(false);
        this.A10.clear();
    }

    public C0740gP(Application application, w1 w1Var, AnonymousClass8P r6, C1383yK yKVar, AnonymousClass8c r8, AnonymousClass8R r9) {
        Tracer.A01("AssistantClientPlatform constructor");
        C00879r.A00.A00.currentMonotonicTimestamp();
        try {
            this.A0s = w1Var;
            this.A0H = r6;
            this.A00 = application;
            this.A0O = r9.A00;
            this.A0C = null;
            ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
            this.A11 = newSingleThreadScheduledExecutor;
            this.A0q = new AndroidAsyncExecutorFactory(newSingleThreadScheduledExecutor);
            ScheduledExecutorService newSingleThreadScheduledExecutor2 = Executors.newSingleThreadScheduledExecutor();
            this.A12 = newSingleThreadScheduledExecutor2;
            this.A0r = new AndroidAsyncExecutorFactory(newSingleThreadScheduledExecutor2);
            this.A0K = new C0651dw(A16, this);
            Context context = this.A00;
            if (AnonymousClass8X.A05 == null) {
                synchronized (AnonymousClass8X.class) {
                    if (AnonymousClass8X.A05 == null) {
                        AnonymousClass8X.A05 = new AnonymousClass8X(context);
                    }
                }
            }
            AnonymousClass8X r2 = AnonymousClass8X.A05;
            this.A0J = r2;
            r2.A03.add(this);
            r2.A02.post(new AnonymousClass8W(r2, this));
            try {
                if (BX.A00 == null) {
                    BX.A00 = application;
                    this.A0A = Ep.A00();
                    this.A0L = r8;
                    this.A0t = yKVar;
                    this.A10.add(new C0741gQ());
                    this.A01 = new g1(this);
                    if (this.A0H.A0J) {
                        A00();
                    } else {
                        C0139Dd.A09("AssistantClientPlatform", "'Not initialize on startup' is in config, skipping initialization now, will initialize on activation");
                    }
                    this.A0I = new AnonymousClass8S(this);
                    return;
                }
                throw new IllegalStateException("ApplicationHolder#set previously called");
            } catch (IllegalStateException unused) {
            }
        } finally {
            Tracer.A00();
        }
    }
}
