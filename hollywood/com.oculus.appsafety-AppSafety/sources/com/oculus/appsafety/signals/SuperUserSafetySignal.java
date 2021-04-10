package com.oculus.appsafety.signals;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SuperUserSafetySignal implements ISafetySignal {
    private static final boolean DEBUG = false;
    private static final String TAG = SuperUserSafetySignal.class.getSimpleName();
    public static final String TELEMETRY_KEY = "superuser_properties";
    private Context context;
    private Map<String, String> suDetectionConfigs;

    private SuperUserSafetySignal(Context context2, JsonObject properties) {
        Type mapType = new TypeToken<Map<String, String>>() {
            /* class com.oculus.appsafety.signals.SuperUserSafetySignal.AnonymousClass1 */
        }.getType();
        this.context = context2;
        this.suDetectionConfigs = (Map) new Gson().fromJson(properties, mapType);
    }

    @Override // com.oculus.appsafety.signals.ISafetySignal
    public String getTelemetryKey() {
        return TELEMETRY_KEY;
    }

    private List<String> checkPackages(List<String> packages) {
        List<String> detectedPackages = new ArrayList<>();
        PackageManager pm = this.context.getPackageManager();
        for (String packageName : packages) {
            try {
                pm.getPackageInfo(packageName, 0);
                detectedPackages.add(packageName);
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
        return detectedPackages;
    }

    private List<String> checkBinaries(List<String> binaries) {
        List<String> detectedBins = new ArrayList<>();
        for (String bin : binaries) {
            if (new File(bin).exists()) {
                detectedBins.add(bin);
            }
        }
        return detectedBins;
    }

    @Override // com.oculus.appsafety.signals.ISafetySignal
    public JsonObject collect() {
        List<String> packageList;
        List<String> binaryList;
        JsonObject extras = new JsonObject();
        if (this.suDetectionConfigs.containsKey("packages")) {
            packageList = Arrays.asList(this.suDetectionConfigs.get("packages").split(";"));
        } else {
            packageList = new ArrayList<>();
        }
        if (this.suDetectionConfigs.containsKey("binaries")) {
            binaryList = Arrays.asList(this.suDetectionConfigs.get("binaries").split(";"));
        } else {
            binaryList = new ArrayList<>();
        }
        List<String> packagesFound = checkPackages(packageList);
        List<String> binariesFound = checkBinaries(binaryList);
        extras.add("packages_found", new Gson().toJsonTree(packagesFound).getAsJsonArray());
        extras.add("binaries_found", new Gson().toJsonTree(binariesFound).getAsJsonArray());
        return extras;
    }
}
