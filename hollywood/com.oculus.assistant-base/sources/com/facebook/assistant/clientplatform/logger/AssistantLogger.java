package com.facebook.assistant.clientplatform.logger;

import X.AnonymousClass08;
import X.AnonymousClass6D;
import X.AnonymousClass9E;
import X.AnonymousClass9F;
import X.AnonymousClass9G;
import X.AnonymousClass9H;
import X.AnonymousClass9I;
import X.AnonymousClass9J;
import X.AnonymousClass9K;
import X.AnonymousClass9L;
import X.AnonymousClass9M;
import X.AnonymousClass9N;
import X.AnonymousClass9O;
import X.AnonymousClass9P;
import X.AnonymousClass9Q;
import X.AnonymousClass9R;
import X.AnonymousClass9S;
import X.AnonymousClass9T;
import X.AnonymousClass9U;
import X.AnonymousClass9V;
import X.AnonymousClass9W;
import X.AnonymousClass9X;
import X.AnonymousClass9Y;
import X.AnonymousClass9Z;
import X.C00446t;
import X.C00829m;
import X.C0139Dd;
import X.C0667ek;
import X.C0685fM;
import X.C1383yK;
import X.EnumC0666ei;
import X.RunnableC00729a;
import X.RunnableC00739b;
import X.RunnableC00749c;
import X.RunnableC00759d;
import X.RunnableC00769e;
import X.RunnableC00779f;
import X.WG;
import X.hD;
import X.hQ;
import X.w1;
import X.yZ;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import com.facebook.assistant.oacr.OacrConstants;
import com.facebook.common.stringformat.StringFormatUtil;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;

public class AssistantLogger {
    public yZ mAnalyticsManager;
    public String mCurrentVoiceState;
    public C1383yK mDeviceContextProvider;
    public boolean mDoNotStoreInteractions = true;
    public String mFinalTranscriptionString;
    public final Set mFlags = new HashSet();
    public final Handler mHandler;
    public final HandlerThread mHandlerThread;
    public String mInteractionId = "null";
    public ArrayList mIntermediateTranscriptionList = new ArrayList();
    public boolean mIsNewSession = false;
    public SQLiteDatabase mLogDatabase;
    public String mShortwaveId;
    public final hQ mStructuredLogger = new hQ();
    public String mTurnId;
    public int mTurnIndex;
    public final hD mXAnalyticsProvider = new hD();

    private void clearAll() {
        this.mTurnIndex = 0;
        this.mTurnId = OacrConstants.AUTO_SPEECH_DOMAIN;
        if (!this.mIsNewSession) {
            this.mInteractionId = OacrConstants.AUTO_SPEECH_DOMAIN;
        }
        this.mCurrentVoiceState = OacrConstants.AUTO_SPEECH_DOMAIN;
        this.mFinalTranscriptionString = OacrConstants.AUTO_SPEECH_DOMAIN;
        this.mShortwaveId = OacrConstants.AUTO_SPEECH_DOMAIN;
        this.mIntermediateTranscriptionList.clear();
        this.mFlags.clear();
    }

    private void logEventInFlipper(String str, String str2, boolean z, boolean z2) {
        String str3 = str2;
        C0139Dd.A0K("AssistantLogger", "%s %s %s %s", str, str3, Boolean.valueOf(z), Boolean.valueOf(z2));
        if (!z2) {
            if (z && this.mDoNotStoreInteractions) {
                str3 = "<redacted>";
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("event", str);
            contentValues.put("event_data", str3);
            contentValues.put("interaction", this.mInteractionId);
            this.mLogDatabase.insert("entries", null, contentValues);
        }
    }

    private C00446t getEventBuilder(String str) {
        yZ yZVar = this.mAnalyticsManager;
        if (yZVar == null) {
            return null;
        }
        return yZVar.A01("com.facebook.assistant", str, false);
    }

    /* access modifiers changed from: private */
    /* renamed from: startEventInteractionImpl */
    public void lambda$startEventInteraction$1$AssistantLogger(String str) {
        C00446t eventBuilder = getEventBuilder("assistant_client_interaction");
        if (eventBuilder != null) {
            C1383yK yKVar = this.mDeviceContextProvider;
            if (yKVar != null) {
                eventBuilder.A03("device_context", yKVar.toString());
            }
            eventBuilder.A03("message", str);
            createVoiceFlowEvent(eventBuilder, "proactive_interaction_started");
            logEventInAnalyticsManager(eventBuilder);
        }
    }

    /* access modifiers changed from: private */
    public void startInteractionImpl() {
        C00446t eventBuilder = getEventBuilder("assistant_client_interaction");
        if (eventBuilder != null) {
            if (this.mDoNotStoreInteractions) {
                this.mFlags.add("no_pii");
            }
            C1383yK yKVar = this.mDeviceContextProvider;
            if (yKVar != null) {
                eventBuilder.A03("device_context", yKVar.toString());
            }
            createVoiceFlowEvent(eventBuilder, "interaction_started");
            logEventInAnalyticsManager(eventBuilder);
        }
    }

    /* access modifiers changed from: private */
    public void startTranscriptionImpl() {
        C00446t eventBuilder = getEventBuilder("assistant_client_interaction");
        if (eventBuilder != null) {
            C1383yK yKVar = this.mDeviceContextProvider;
            if (yKVar != null) {
                eventBuilder.A03("device_context", yKVar.toString());
            }
            createVoiceFlowEvent(eventBuilder, "transcription_started");
            logEventInAnalyticsManager(eventBuilder);
        }
    }

    public void logTranscriptionFeedback(String str, String str2) {
        logFeedback("transcription_feedback", str, str2);
    }

    public void logVoiceCommandFeedback(String str, String str2) {
        logFeedback("voice_command_feedback", str, str2);
    }

    public void startEventInteraction(String str) {
        if (this.mInteractionId != null) {
            clearAll();
        }
        if (!this.mIsNewSession) {
            this.mInteractionId = UUID.randomUUID().toString();
        }
        this.mIsNewSession = false;
        this.mTurnId = this.mInteractionId;
        this.mTurnIndex++;
        this.mShortwaveId = OacrConstants.AUTO_SPEECH_DOMAIN;
        this.mIntermediateTranscriptionList.clear();
        if (!isLoggerThread()) {
            this.mHandler.post(new AnonymousClass9Q(this));
        } else {
            lambda$startEventInteraction$1$AssistantLogger("HelpTask");
        }
    }

    public void startInteraction() {
        if (this.mInteractionId != null) {
            clearAll();
        }
        if (!this.mIsNewSession) {
            this.mInteractionId = UUID.randomUUID().toString();
        }
        this.mIsNewSession = false;
        this.mTurnId = this.mInteractionId;
        this.mTurnIndex++;
        this.mShortwaveId = OacrConstants.AUTO_SPEECH_DOMAIN;
        this.mIntermediateTranscriptionList.clear();
        if (!isLoggerThread()) {
            this.mHandler.post(new AnonymousClass9I(this));
        } else {
            startInteractionImpl();
        }
    }

    public void startTranscription() {
        if (this.mInteractionId != null) {
            clearAll();
        }
        if (!this.mIsNewSession) {
            this.mInteractionId = UUID.randomUUID().toString();
        }
        this.mIsNewSession = false;
        this.mTurnId = this.mInteractionId;
        this.mTurnIndex++;
        this.mShortwaveId = OacrConstants.AUTO_SPEECH_DOMAIN;
        this.mIntermediateTranscriptionList.clear();
        if (!isLoggerThread()) {
            this.mHandler.post(new RunnableC00769e(this));
        } else {
            startTranscriptionImpl();
        }
    }

    public AssistantLogger() {
        HandlerThread handlerThread = new HandlerThread("AssistantLogger");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(this.mHandlerThread.getLooper());
        this.mHandler = handler;
        handler.post(new AnonymousClass9O(this));
    }

    private C00446t createVoiceFlowEvent(C00446t r5, String str) {
        String str2;
        if (r5.A05()) {
            String str3 = this.mInteractionId;
            String str4 = this.mTurnId;
            String str5 = this.mCurrentVoiceState;
            r5.A03("voice_interaction_flow_id", str3);
            r5.A03("turn_id", str4);
            r5.A03("voice_state", str5);
            r5.A02("action_received_time", Long.valueOf(System.currentTimeMillis()));
            r5.A03("shortwave_id", this.mShortwaveId);
            yZ yZVar = this.mAnalyticsManager;
            if (yZVar != null) {
                w1 w1Var = yZVar.A00;
                if (w1Var == null || w1Var.A00() == null) {
                    str2 = null;
                } else {
                    str2 = yZVar.A00.A00().mUserId;
                }
                r5.A03("user_id", str2);
            } else {
                r5.A02("user_id", 0);
            }
            r5.A03("flags", TextUtils.join(",", this.mFlags));
            r5.A03("action_type", str);
        }
        return r5;
    }

    public static String getConsoleUrlForShortwave(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split("_");
            if (split.length == 2) {
                long parseLong = Long.parseLong(split[1], 16);
                if (parseLong < 1483228800000000L || parseLong > 4102444800000000L) {
                    parseLong = System.currentTimeMillis();
                }
                StringBuilder sb = new StringBuilder("https://our.internmc.facebook.com/intern/messenger/assistant/v2/");
                sb.append("?log_id=");
                sb.append(str2);
                sb.append("_");
                sb.append(parseLong);
                return sb.toString();
            }
        }
        return "invalid shortwaveid";
    }

    private boolean isLoggerThread() {
        if (Looper.myLooper() == this.mHandlerThread.getLooper()) {
            return true;
        }
        return false;
    }

    private void logEventInAnalyticsManager(C00446t r2) {
        yZ yZVar;
        if (r2.A05() && (yZVar = this.mAnalyticsManager) != null) {
            yZVar.A02(r2);
        }
    }

    private void logFeedback(String str, String str2, String str3) {
        if (!isLoggerThread()) {
            this.mHandler.post(new AnonymousClass9J(this, str2, str3));
            return;
        }
        C0139Dd.A0H("AssistantLogger", "logVoiceCommandFeedback %s %s", str2, str3);
        logEventInFlipper(str, AnonymousClass08.A06("logVoiceCommandFeedback ", str2, " ", str3), false, false);
        C00446t eventBuilder = getEventBuilder("assistant_client_interaction");
        if (eventBuilder == null) {
            C0139Dd.A0C("AssistantLogger", "event builder is null, return immediately");
        } else if (eventBuilder.A05()) {
            eventBuilder.A03("message", str3);
            createVoiceFlowEvent(eventBuilder, str);
            eventBuilder.A03("voice_interaction_flow_id", str2);
            logEventInAnalyticsManager(eventBuilder);
        }
    }

    public void logAgentError(String str, String str2) {
        if (!isLoggerThread()) {
            this.mHandler.post(new AnonymousClass9Y(this, str, str2));
            return;
        }
        logEventInFlipper("AgentError", AnonymousClass08.A06("Type: ", str, " Message: ", str2), false, false);
        C00446t eventBuilder = getEventBuilder("assistant_client_interaction");
        if (eventBuilder == null) {
            C0139Dd.A0C("AssistantLogger", "event builder is null, return immediately");
        } else if (eventBuilder.A05()) {
            eventBuilder.A03("error_type", str);
            eventBuilder.A03("message", str2);
            createVoiceFlowEvent(eventBuilder, "error_agent");
            logEventInAnalyticsManager(eventBuilder);
        }
    }

    public void logAppCommmandEvent(String str, String str2) {
        if (!isLoggerThread()) {
            this.mHandler.post(new AnonymousClass9G(this, str, str2));
            return;
        }
        logEventInFlipper("AppCommand", AnonymousClass08.A06("Trigger: ", str, " Phrase: ", str2), true, false);
        C00446t eventBuilder = getEventBuilder("assistant_client_interaction");
        if (eventBuilder == null) {
            C0139Dd.A0C("AssistantLogger", "event builder is null, return immediately");
        } else if (eventBuilder.A05()) {
            eventBuilder.A03("message", AnonymousClass08.A05(str, "::", str2));
            createVoiceFlowEvent(eventBuilder, "app_command");
            logEventInAnalyticsManager(eventBuilder);
        }
    }

    public void logAttentionSystem(String str) {
        if (!isLoggerThread()) {
            this.mHandler.post(new AnonymousClass9W(this, str));
            return;
        }
        logEventInFlipper("attention_system", str, false, false);
        C00446t eventBuilder = getEventBuilder("assistant_client_interaction");
        if (eventBuilder == null) {
            C0139Dd.A0C("AssistantLogger", "event builder is null, return immediately");
        } else if (eventBuilder.A05()) {
            eventBuilder.A03("message", str);
            createVoiceFlowEvent(eventBuilder, "attention_system");
            logEventInAnalyticsManager(eventBuilder);
        }
    }

    public void logDeviceContext(String str) {
        if (!isLoggerThread()) {
            this.mHandler.post(new AnonymousClass9Z(this, str));
            return;
        }
        logEventInFlipper("DeviceContext", str, false, false);
        C00446t eventBuilder = getEventBuilder("assistant_client_interaction");
        if (eventBuilder == null) {
            C0139Dd.A0C("AssistantLogger", "event builder is null, return immediately");
        } else if (eventBuilder.A05()) {
            eventBuilder.A03("device_context", str);
            createVoiceFlowEvent(eventBuilder, "get_device_context");
            logEventInAnalyticsManager(eventBuilder);
        }
    }

    public void logException(String str, Exception exc) {
        if (!isLoggerThread()) {
            this.mHandler.post(new RunnableC00749c(this, str, exc));
            return;
        }
        logEventInFlipper("Error", str, false, false);
        C00446t eventBuilder = getEventBuilder("assistant_client_interaction");
        if (eventBuilder == null) {
            C0139Dd.A0C("AssistantLogger", "event builder is null, return immediately");
        } else if (eventBuilder.A05()) {
            StringWriter stringWriter = new StringWriter();
            exc.printStackTrace(new PrintWriter(stringWriter));
            eventBuilder.A03("error_type", str);
            eventBuilder.A03("message", stringWriter.toString());
            createVoiceFlowEvent(eventBuilder, "exception");
            logEventInAnalyticsManager(eventBuilder);
        }
    }

    public void logFinalTranscription(String str) {
        if (!isLoggerThread()) {
            this.mHandler.post(new AnonymousClass9V(this, str));
            return;
        }
        logEventInFlipper("Transcription", AnonymousClass08.A04(str, " (final)"), true, false);
        if (str == null) {
            str = OacrConstants.AUTO_SPEECH_DOMAIN;
        }
        this.mFinalTranscriptionString = str;
        C00446t eventBuilder = getEventBuilder("assistant_client_interaction");
        if (eventBuilder == null) {
            C0139Dd.A0C("AssistantLogger", "event builder is null, return immediately");
        } else if (eventBuilder.A05()) {
            if (!this.mDoNotStoreInteractions) {
                eventBuilder.A03("final_transcription", this.mFinalTranscriptionString);
                eventBuilder.A03("intermediate_transcription", new JSONArray((Collection) this.mIntermediateTranscriptionList).toString());
            }
            eventBuilder.A02("turn_index", Integer.valueOf(this.mTurnIndex));
            createVoiceFlowEvent(eventBuilder, "asr_transcription_result");
            logEventInAnalyticsManager(eventBuilder);
        }
    }

    public void logKeyboardFederatedLearningEvent(WG wg) {
        EnumC0666ei eiVar;
        int hashCode;
        if (!isLoggerThread()) {
            this.mHandler.post(new AnonymousClass9R(this, wg));
            return;
        }
        C0667ek ekVar = new C0667ek(this.mStructuredLogger.A00("assistant_keyboard_federated_learning"));
        AnonymousClass6D r4 = ekVar.A00;
        if (!r4.isSampled()) {
            C0139Dd.A09("AssistantLogger", "FL event not sampled.");
            return;
        }
        String str = wg.A00.A0D;
        if (!(str == null || (hashCode = str.hashCode()) == -1022109879)) {
            if (hashCode != -982480788) {
                if (hashCode == -730112679 && str.equals("wearable")) {
                    eiVar = EnumC0666ei.WEARABLE;
                    r4.A1A("device_type", eiVar);
                    ekVar.A00.A1B("num_rows_total", wg.A00.A00);
                    ekVar.A00.A1E("words_per_row_total", wg.A00.A0C);
                    ekVar.A00.A1E("unks_per_row_total", wg.A00.A0B);
                    ekVar.A00.A1E("trained_row_idxs", wg.A00.A0A);
                    ekVar.A00.A1B("papaya_submit", wg.A00.A05);
                    ekVar.A00.A1B("papaya_download_data", wg.A00.A01);
                    ekVar.A00.A1B("papaya_download_task", wg.A00.A02);
                    ekVar.A00.A1B("papaya_execute", wg.A00.A03);
                    ekVar.A00.A1B("papaya_upload", wg.A00.A06);
                    ekVar.A00.A1B("papaya_finish", wg.A00.A04);
                    ekVar.A00.A1D("exception_type", wg.A00.A09);
                    ekVar.A00.A1D("exception_message", wg.A00.A07);
                    ekVar.A00.A1D("exception_stacktrace", wg.A00.A08);
                    ekVar.A00();
                }
            } else if (str.equals("portal")) {
                eiVar = EnumC0666ei.PORTAL;
                r4.A1A("device_type", eiVar);
                ekVar.A00.A1B("num_rows_total", wg.A00.A00);
                ekVar.A00.A1E("words_per_row_total", wg.A00.A0C);
                ekVar.A00.A1E("unks_per_row_total", wg.A00.A0B);
                ekVar.A00.A1E("trained_row_idxs", wg.A00.A0A);
                ekVar.A00.A1B("papaya_submit", wg.A00.A05);
                ekVar.A00.A1B("papaya_download_data", wg.A00.A01);
                ekVar.A00.A1B("papaya_download_task", wg.A00.A02);
                ekVar.A00.A1B("papaya_execute", wg.A00.A03);
                ekVar.A00.A1B("papaya_upload", wg.A00.A06);
                ekVar.A00.A1B("papaya_finish", wg.A00.A04);
                ekVar.A00.A1D("exception_type", wg.A00.A09);
                ekVar.A00.A1D("exception_message", wg.A00.A07);
                ekVar.A00.A1D("exception_stacktrace", wg.A00.A08);
                ekVar.A00();
            }
        }
        eiVar = EnumC0666ei.OCULUS;
        r4.A1A("device_type", eiVar);
        ekVar.A00.A1B("num_rows_total", wg.A00.A00);
        ekVar.A00.A1E("words_per_row_total", wg.A00.A0C);
        ekVar.A00.A1E("unks_per_row_total", wg.A00.A0B);
        ekVar.A00.A1E("trained_row_idxs", wg.A00.A0A);
        ekVar.A00.A1B("papaya_submit", wg.A00.A05);
        ekVar.A00.A1B("papaya_download_data", wg.A00.A01);
        ekVar.A00.A1B("papaya_download_task", wg.A00.A02);
        ekVar.A00.A1B("papaya_execute", wg.A00.A03);
        ekVar.A00.A1B("papaya_upload", wg.A00.A06);
        ekVar.A00.A1B("papaya_finish", wg.A00.A04);
        ekVar.A00.A1D("exception_type", wg.A00.A09);
        ekVar.A00.A1D("exception_message", wg.A00.A07);
        ekVar.A00.A1D("exception_stacktrace", wg.A00.A08);
        ekVar.A00();
    }

    public void logKeyboardTranscription(String str) {
        if (!isLoggerThread()) {
            this.mHandler.post(new AnonymousClass9P(this, str));
            return;
        }
        logEventInFlipper("Keyboard Transcription", AnonymousClass08.A04(str, " (final)"), true, false);
        if (str == null) {
            str = OacrConstants.AUTO_SPEECH_DOMAIN;
        }
        this.mFinalTranscriptionString = str;
        C00446t eventBuilder = getEventBuilder("assistant_client_interaction");
        if (eventBuilder == null) {
            C0139Dd.A0C("AssistantLogger", "event builder is null, return immediately");
        } else if (eventBuilder.A05()) {
            eventBuilder.A02("final_transcription", Integer.valueOf(this.mFinalTranscriptionString.length()));
            eventBuilder.A02("turn_index", Integer.valueOf(this.mTurnIndex));
            createVoiceFlowEvent(eventBuilder, "dictation_asr_result");
            logEventInAnalyticsManager(eventBuilder);
        }
    }

    public void logLocalEvent(String str, String str2) {
        if (!isLoggerThread()) {
            this.mHandler.post(new AnonymousClass9N(this, str, str2));
        } else {
            logEventInFlipper(str, str2, false, true);
        }
    }

    public void logNuxEvent(String str) {
        if (!isLoggerThread()) {
            this.mHandler.post(new AnonymousClass9T(this, str));
            return;
        }
        C00446t eventBuilder = getEventBuilder("assistant_client_interaction");
        if (eventBuilder != null) {
            eventBuilder.A03("message", str);
            createVoiceFlowEvent(eventBuilder, "nux_event");
            logEventInAnalyticsManager(eventBuilder);
            logEventInFlipper("nuxevent", str, false, false);
        }
    }

    public void logOACREvent(String str, String str2, Map map) {
        String str3;
        if (!isLoggerThread()) {
            this.mHandler.post(new AnonymousClass9M(this, str, str2, map));
        } else if (this.mAnalyticsManager != null) {
            if (C0139Dd.A01.A3Y(4)) {
                C0139Dd.A0B("AssistantLogger", StringFormatUtil.formatStrLocaleSafe("Logging an OACR event %s, module %s, fields: %s.", str, str2, map));
            }
            C00446t A01 = this.mAnalyticsManager.A01(str2, str, false);
            if (A01.A05()) {
                if (!(A01 instanceof C0685fM) && map != null) {
                    for (Map.Entry entry : map.entrySet()) {
                        A01.A03((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                yZ yZVar = this.mAnalyticsManager;
                if (yZVar != null) {
                    yZVar.A02(A01);
                }
                str3 = "Logging succeeded.";
            } else {
                str3 = "Logging skipped: event is not sampled.";
            }
            C0139Dd.A0B("AssistantLogger", str3);
            logEventInFlipper(str, map.toString(), false, false);
        }
    }

    public void logPartialTranscription(String str) {
        if (!isLoggerThread()) {
            this.mHandler.post(new AnonymousClass9F(this, str));
            return;
        }
        logEventInFlipper("Transcription", str, true, false);
        this.mIntermediateTranscriptionList.add(str);
    }

    public void logRecording(String str) {
        if (!isLoggerThread()) {
            this.mHandler.post(new AnonymousClass9S(this, str));
        } else {
            logEventInFlipper("Recording", str, true, true);
        }
    }

    public void logServiceEvent(String str) {
        if (!isLoggerThread()) {
            this.mHandler.post(new RunnableC00759d(this, str));
            return;
        }
        C00446t eventBuilder = getEventBuilder("assistant_client_interaction");
        if (eventBuilder != null) {
            eventBuilder.A03("message", str);
            createVoiceFlowEvent(eventBuilder, "service_event");
            logEventInAnalyticsManager(eventBuilder);
            logEventInFlipper("service_event", str, false, false);
        }
    }

    public void logStateChanged(String str) {
        if (!isLoggerThread()) {
            this.mHandler.post(new RunnableC00729a(this, str));
            return;
        }
        logEventInFlipper("AssistantState", str, false, false);
        C00446t eventBuilder = getEventBuilder("assistant_client_interaction");
        if (eventBuilder == null) {
            C0139Dd.A0C("AssistantLogger", "event builder is null, return immediately");
            return;
        }
        this.mCurrentVoiceState = str;
        eventBuilder.A03("voice_state", str);
        createVoiceFlowEvent(eventBuilder, "voice_state_changed");
        logEventInAnalyticsManager(eventBuilder);
    }

    public void logTranscriptionAnalytics(String str, C00829m r5) {
        if (!isLoggerThread()) {
            this.mHandler.post(new RunnableC00739b(this, str, r5));
            return;
        }
        logEventInFlipper("TranscriptionAnalytics", AnonymousClass08.A05(str, " ", r5.toString()), false, false);
        C00446t eventBuilder = getEventBuilder("assistant_client_interaction");
        if (eventBuilder == null) {
            C0139Dd.A0C("AssistantLogger", "event builder is null, return immediately");
            return;
        }
        eventBuilder.A03("message", r5.toString());
        createVoiceFlowEvent(eventBuilder, str);
        logEventInAnalyticsManager(eventBuilder);
    }

    public void setFlags(String str) {
        if (!isLoggerThread()) {
            this.mHandler.post(new AnonymousClass9L(this, str));
        } else {
            this.mFlags.add(str);
        }
    }

    public void stopInteraction() {
        if (!isLoggerThread()) {
            this.mHandler.post(new AnonymousClass9E(this));
            return;
        }
        if (this.mInteractionId == null) {
            C0139Dd.A0A("AssistantLogger", "No active interaction id");
        }
        C00446t eventBuilder = getEventBuilder("assistant_client_interaction");
        if (eventBuilder == null) {
            C0139Dd.A0C("AssistantLogger", "event builder is null, return immediately");
            return;
        }
        createVoiceFlowEvent(eventBuilder, "interaction_finished");
        logEventInAnalyticsManager(eventBuilder);
    }

    public void stopTranscription() {
        if (!isLoggerThread()) {
            this.mHandler.post(new RunnableC00779f(this));
            return;
        }
        if (this.mInteractionId == null) {
            C0139Dd.A0A("AssistantLogger", "No active interaction id");
        }
        C00446t eventBuilder = getEventBuilder("assistant_client_interaction");
        if (eventBuilder == null) {
            C0139Dd.A0C("AssistantLogger", "event builder is null, return immediately");
            return;
        }
        createVoiceFlowEvent(eventBuilder, "transcription_finished");
        logEventInAnalyticsManager(eventBuilder);
    }

    public void logAssistantResponse(String str, String str2, boolean z, String str3, boolean z2, boolean z3, boolean z4) {
        String str4;
        String consoleUrlForShortwave;
        if (!isLoggerThread()) {
            this.mHandler.post(new AnonymousClass9X(this, str, str2, z, str3, z2, z3, z4));
            return;
        }
        Boolean valueOf = Boolean.valueOf(z);
        logEventInFlipper("AssistantResponse", StringFormatUtil.formatStrLocaleSafe("responseAction %s  responseText: %s  isAnswerRequired: %b  finalTranscription: %s  intermediateTranscription: %s", str, str2, valueOf, this.mFinalTranscriptionString, new JSONArray((Collection) this.mIntermediateTranscriptionList).toString()), z4, false);
        C00446t eventBuilder = getEventBuilder("assistant_client_interaction");
        if (eventBuilder == null) {
            C0139Dd.A0C("AssistantLogger", "event builder is null, return immediately");
        } else if (eventBuilder.A05()) {
            if (!this.mDoNotStoreInteractions && !z4) {
                eventBuilder.A03("final_transcription", this.mFinalTranscriptionString);
                eventBuilder.A03("intermediate_transcription", new JSONArray((Collection) this.mIntermediateTranscriptionList).toString());
                eventBuilder.A03("assistant_text_response", str2);
            }
            eventBuilder.A01("is_sensitive_interaction", Boolean.valueOf(z4));
            eventBuilder.A03("response_action", str);
            eventBuilder.A01("requires_response", valueOf);
            eventBuilder.A03("shortwave_id", this.mShortwaveId);
            eventBuilder.A02("turn_index", Integer.valueOf(this.mTurnIndex));
            String str5 = this.mShortwaveId;
            yZ yZVar = this.mAnalyticsManager;
            if (yZVar == null) {
                consoleUrlForShortwave = OacrConstants.AUTO_SPEECH_DOMAIN;
            } else {
                w1 w1Var = yZVar.A00;
                if (w1Var == null || w1Var.A00() == null) {
                    str4 = null;
                } else {
                    str4 = yZVar.A00.A00().mUserId;
                }
                consoleUrlForShortwave = getConsoleUrlForShortwave(str5, str4);
            }
            eventBuilder.A03("console_log", consoleUrlForShortwave);
            eventBuilder.A01("has_tts", Boolean.valueOf(z2));
            eventBuilder.A01("has_native_template", Boolean.valueOf(z3));
            createVoiceFlowEvent(eventBuilder, "assistant_response");
            logEventInAnalyticsManager(eventBuilder);
        }
    }

    public void logError(String str) {
        logError(str, OacrConstants.AUTO_SPEECH_DOMAIN);
    }

    public void logError(String str, String str2) {
        if (!isLoggerThread()) {
            this.mHandler.post(new AnonymousClass9U(this, str, str2));
            return;
        }
        logEventInFlipper("Error", str, false, false);
        C00446t eventBuilder = getEventBuilder("assistant_client_interaction");
        if (eventBuilder == null) {
            C0139Dd.A0C("AssistantLogger", "event builder is null, return immediately");
        } else if (eventBuilder.A05()) {
            eventBuilder.A03("error_type", str);
            eventBuilder.A03("message", str2);
            createVoiceFlowEvent(eventBuilder, "error_interaction");
            logEventInAnalyticsManager(eventBuilder);
        }
    }

    public void logFulfillment(String str) {
        logFulfillment(str, OacrConstants.AUTO_SPEECH_DOMAIN);
    }

    public void logFulfillment(String str, String str2) {
        if (!isLoggerThread()) {
            this.mHandler.post(new AnonymousClass9K(this, str, str2));
            return;
        }
        logEventInFlipper("Fulfillment", AnonymousClass08.A04(str, str2), true, false);
        C00446t eventBuilder = getEventBuilder("assistant_client_interaction");
        if (eventBuilder == null) {
            C0139Dd.A0C("AssistantLogger", "event builder is null, return immediately");
        } else if (eventBuilder.A05()) {
            if (!this.mDoNotStoreInteractions) {
                eventBuilder.A03("message", str);
            }
            createVoiceFlowEvent(eventBuilder, "fulfillment");
            logEventInAnalyticsManager(eventBuilder);
        }
    }

    public void logSmartKeyboardInteractionsOneSession(String str, String str2, String str3, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, ArrayList arrayList) {
        double d;
        if (!isLoggerThread()) {
            this.mHandler.post(new AnonymousClass9H(this, str, str2, str3, i, i2, i3, i4, i5, i6, i7, i8, arrayList));
            return;
        }
        C00446t eventBuilder = getEventBuilder("assistant_smart_keyboard_interaction");
        if (eventBuilder == null) {
            C0139Dd.A0C("AssistantLogger", "event builder is null, return immediately");
            return;
        }
        double d2 = 0.0d;
        if (!arrayList.isEmpty()) {
            long j = 0;
            int i9 = 0;
            while (true) {
                d = (double) j;
                if (i9 >= arrayList.size()) {
                    break;
                }
                j = (long) (d + ((double) TimeUnit.NANOSECONDS.toMillis(((Number) arrayList.get(i9)).longValue())));
                i9++;
            }
            d2 = (d * 1.0d) / ((double) arrayList.size());
        }
        eventBuilder.A03("model_name", str);
        eventBuilder.A03("surface", str2);
        eventBuilder.A03("foreground_app", str3);
        eventBuilder.A02("model_version", Integer.valueOf(i));
        eventBuilder.A02("suggestion_cnt", Integer.valueOf(i5));
        eventBuilder.A02("supplemental_cnt", Integer.valueOf(i6));
        eventBuilder.A02("empty_suggestion_cnt", Integer.valueOf(i7));
        eventBuilder.A02("suggestion_likely_matched", Integer.valueOf(i8));
        eventBuilder.A02("suggestion_clicked", Integer.valueOf(i2));
        eventBuilder.A02("supplemental_clicked", Integer.valueOf(i3));
        eventBuilder.A02("keystrokes_saved", Integer.valueOf(i4));
        eventBuilder.A02("average_latency", Integer.valueOf((int) d2));
        logEventInAnalyticsManager(eventBuilder);
    }
}
