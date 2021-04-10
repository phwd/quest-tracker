package com.oculus.appsafety.signals;

import android.content.Context;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.oculus.appsafety.LoggingHelper;
import com.oculus.os.UnifiedTelemetryLogger;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SafetySignalCollectorConfiguration {
    private static final boolean DEBUG = false;
    private static final String TAG = SafetySignalCollectorConfiguration.class.getSimpleName();
    private static final Map<String, Class> nameToClass = new HashMap<String, Class>() {
        /* class com.oculus.appsafety.signals.SafetySignalCollectorConfiguration.AnonymousClass1 */

        {
            put(ShellPropsSafetySignal.TELEMETRY_KEY, ShellPropsSafetySignal.class);
            put(SuperUserSafetySignal.TELEMETRY_KEY, SuperUserSafetySignal.class);
        }
    };
    private List<SafetySignalSettings> signalsSettings;

    private SafetySignalCollectorConfiguration() {
    }

    public static List<ISafetySignal> getSignals(Context context, UnifiedTelemetryLogger unifiedTelemetryLogger, SafetySignalCollectorConfigurationFetcher fetcher) throws IOException {
        SafetySignalCollectorConfiguration configuration = fetcher.fetch();
        if (configuration != null) {
            List<SafetySignalSettings> list = configuration.signalsSettings;
            if (list == null) {
                return Collections.emptyList();
            }
            return (List) ((Stream) list.stream().sequential()).map(new Function(context, unifiedTelemetryLogger) {
                /* class com.oculus.appsafety.signals.$$Lambda$SafetySignalCollectorConfiguration$t830yHIRQ_a8nnHSpWx95NxyNEQ */
                private final /* synthetic */ Context f$0;
                private final /* synthetic */ UnifiedTelemetryLogger f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return SafetySignalCollectorConfiguration.lambda$getSignals$0(this.f$0, this.f$1, (SafetySignalSettings) obj);
                }
            }).filter($$Lambda$SafetySignalCollectorConfiguration$FVfs2DFFWjILSyqpbFnWyXdQ8EU.INSTANCE).collect(Collectors.toList());
        }
        throw new RuntimeException("Failed to process fetched configuration, value cannot be null");
    }

    static /* synthetic */ ISafetySignal lambda$getSignals$0(Context context, UnifiedTelemetryLogger unifiedTelemetryLogger, SafetySignalSettings signalSettings) {
        String name = signalSettings.name;
        JsonObject properties = signalSettings.properties;
        try {
            Constructor signalConstructor = nameToClass.get(name).getDeclaredConstructor(Context.class, JsonObject.class);
            signalConstructor.setAccessible(true);
            return (ISafetySignal) signalConstructor.newInstance(context, properties);
        } catch (Exception e) {
            unifiedTelemetryLogger.reportEvent(LoggingHelper.getSafetySignalEvent("job_failed_to_instantiate_safety_signal_collector", e), false);
            return null;
        }
    }

    static /* synthetic */ boolean lambda$getSignals$1(ISafetySignal signal) {
        return signal != null;
    }

    public static SafetySignalCollectorConfiguration fromJson(String json) {
        String configuration = (String) Optional.ofNullable(new JsonParser().parse(json)).map($$Lambda$SafetySignalCollectorConfiguration$X4jxfqmm_svnDjqLO0VlBmXy1lA.INSTANCE).map($$Lambda$SafetySignalCollectorConfiguration$EUwKBAWHM4_3majVBKYJz4gRJ4.INSTANCE).map($$Lambda$SafetySignalCollectorConfiguration$IBPVHj22aKrjg9KUdeyFtvnQPwk.INSTANCE).map($$Lambda$SafetySignalCollectorConfiguration$euoTcY4oqSTlz3k4lcoQrJrV0U.INSTANCE).map($$Lambda$SafetySignalCollectorConfiguration$1FJsH2ubPEZGduKlnhTcqHQz1g.INSTANCE).map($$Lambda$SafetySignalCollectorConfiguration$0IVoHNi6Ib09q25O4KGpYhEhy8k.INSTANCE).orElse(null);
        if (configuration != null) {
            GsonBuilder builder = new GsonBuilder();
            builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
            return (SafetySignalCollectorConfiguration) builder.create().fromJson(configuration, SafetySignalCollectorConfiguration.class);
        }
        throw new RuntimeException("Failed to parse the configuration");
    }
}
