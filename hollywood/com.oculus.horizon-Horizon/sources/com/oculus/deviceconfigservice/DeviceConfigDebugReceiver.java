package com.oculus.deviceconfigservice;

import X.AnonymousClass006;
import X.AnonymousClass0NO;
import X.AnonymousClass0b8;
import X.AnonymousClass0b9;
import X.C00910Hi;
import android.content.Context;
import android.content.Intent;
import com.facebook.infer.annotation.Nullsafe;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DeviceConfigDebugReceiver extends C00910Hi {
    public static final String CONFIG_PARAM_FILE_NAME = "file_name";
    public static final String CONFIG_PARAM_NAME = "config_param";
    public static final String CONFIG_PARAM_OVERRIDDEN_PAYLOAD = "config_param_value";
    public static final String DC_CLEAR_OVERRIDE = "oculus.intent.action.DC_CLEAR_OVERRIDE";
    public static final String DC_DUMP = "oculus.intent.action.DC_DUMP";
    public static final String DC_EXPORT = "oculus.intent.action.DC_EXPORT";
    public static final String DC_FORCE_FETCH = "oculus.intent.action.DC_FORCE_FETCH";
    public static final String DC_IMPORT = "oculus.intent.action.DC_IMPORT";
    public static final String DC_OVERRIDE = "oculus.intent.action.DC_OVERRIDE";
    public static final String DC_UPDATE = "oculus.intent.action.DC_UPDATE";
    public static final String TAG = "DeviceConfigDebugReceiver";

    public static class ClearOverrideAction implements AnonymousClass0b8 {
        @Override // X.AnonymousClass0b8
        public final void onReceive(Context context, Intent intent, AnonymousClass0b9 r8) {
            DeviceConfigDebugHelper A00 = DeviceConfigDebugHelper.A00();
            if (A00 != null) {
                String[] stringArrayExtra = intent.getStringArrayExtra(DeviceConfigDebugReceiver.CONFIG_PARAM_NAME);
                if (stringArrayExtra == null) {
                    synchronized (A00) {
                        HashSet hashSet = new HashSet();
                        hashSet.addAll(DeviceConfigDebugHelper.A02(A00.mOverriddenBooleanValues));
                        hashSet.addAll(DeviceConfigDebugHelper.A02(A00.mOverriddenDoubleValues));
                        hashSet.addAll(DeviceConfigDebugHelper.A02(A00.mOverriddenLongValues));
                        hashSet.addAll(DeviceConfigDebugHelper.A02(A00.mOverriddenStringValues));
                        A00.mOverriddenBooleanValues.clear();
                        A00.mOverriddenDoubleValues.clear();
                        A00.mOverriddenLongValues.clear();
                        A00.mOverriddenStringValues.clear();
                        DeviceConfigDebugHelper.A03(A00, context);
                        if (!hashSet.isEmpty()) {
                            A00.mMobileConfigAccessor.A09.onConfigChanged((String[]) hashSet.toArray(new String[hashSet.size()]));
                        }
                    }
                    return;
                }
                List<String[]> A04 = A00.A04(context, stringArrayExtra);
                if (A04.isEmpty()) {
                    AnonymousClass0NO.A0E(DeviceConfigDebugReceiver.TAG, "Failed to clear overridden value for config param payload: %s", Arrays.toString(stringArrayExtra));
                    return;
                }
                A04.size();
                DeviceConfigDebugReceiver.A00(A04);
            }
        }
    }

    public static class DumpAction implements AnonymousClass0b8 {
        @Override // X.AnonymousClass0b8
        public final void onReceive(Context context, Intent intent, AnonymousClass0b9 r11) {
            DeviceConfigDebugHelper A00 = DeviceConfigDebugHelper.A00();
            if (A00 != null) {
                String stringExtra = intent.getStringExtra(DeviceConfigDebugReceiver.CONFIG_PARAM_NAME);
                String str = null;
                if (stringExtra != null) {
                    String[] split = stringExtra.split(":", 2);
                    int length = split.length;
                    if (length == 2) {
                        stringExtra = split[0];
                        str = split[1];
                    } else if (length != 1) {
                        AnonymousClass0NO.A0E(DeviceConfigDebugReceiver.TAG, "Unexpected config param name provided: %s. It should be in format of <config name>:<param name>", stringExtra);
                        return;
                    }
                    A00.A06(stringExtra, str);
                    return;
                }
                A00.A06(null, null);
            }
        }
    }

    public static class ExportAction implements AnonymousClass0b8 {
        @Override // X.AnonymousClass0b8
        public final void onReceive(Context context, Intent intent, AnonymousClass0b9 r11) {
            DeviceConfigDebugHelper A00 = DeviceConfigDebugHelper.A00();
            if (A00 != null) {
                String stringExtra = intent.getStringExtra(DeviceConfigDebugReceiver.CONFIG_PARAM_FILE_NAME);
                synchronized (A00) {
                    if (stringExtra == null) {
                        stringExtra = DeviceConfigDebugHelper.DUMP_FILE_NAME;
                    }
                    String A01 = DeviceConfigDebugHelper.A01(A00, null, null, false);
                    try {
                        File file = new File(context.getFilesDir(), DeviceConfigDebugHelper.DEVICE_CONFIG_DEBUG_DIRECTORY);
                        file.mkdirs();
                        File file2 = new File(file, stringExtra);
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file2));
                        outputStreamWriter.write(AnonymousClass006.A05(DeviceConfigDebugHelper.DUMP_FILE_VERSION_1, "\n"));
                        if (DeviceConfigDebugHelper.DUMP_FILE_VERSION_1.equals(DeviceConfigDebugHelper.DUMP_FILE_VERSION_1)) {
                            outputStreamWriter.write(A01);
                            outputStreamWriter.close();
                        } else {
                            AnonymousClass0NO.A0E(DeviceConfigDebugHelper.TAG, "Unknown device config dump file version to write: %s", DeviceConfigDebugHelper.DUMP_FILE_VERSION_1);
                        }
                        file2.getCanonicalPath();
                    } catch (IOException e) {
                        AnonymousClass0NO.A0H(DeviceConfigDebugHelper.TAG, e, "failed to write config values to disk");
                    }
                }
            }
        }
    }

    public static class ForceFetchAction implements AnonymousClass0b8 {
        @Override // X.AnonymousClass0b8
        public final void onReceive(Context context, Intent intent, AnonymousClass0b9 r5) {
            DeviceConfigDebugHelper A00 = DeviceConfigDebugHelper.A00();
            if (A00 != null) {
                synchronized (A00) {
                    A00.mMobileConfigAccessor.A03();
                }
            }
        }
    }

    public static class ImportAction implements AnonymousClass0b8 {
        @Override // X.AnonymousClass0b8
        public final void onReceive(Context context, Intent intent, AnonymousClass0b9 r12) {
            String str;
            DeviceConfigDebugHelper A00 = DeviceConfigDebugHelper.A00();
            if (A00 != null) {
                String stringExtra = intent.getStringExtra(DeviceConfigDebugReceiver.CONFIG_PARAM_FILE_NAME);
                synchronized (A00) {
                    if (stringExtra != null) {
                        str = stringExtra;
                    } else {
                        str = DeviceConfigDebugHelper.DUMP_FILE_NAME;
                    }
                    String str2 = "";
                    try {
                        File file = new File(context.getFilesDir(), DeviceConfigDebugHelper.DEVICE_CONFIG_DEBUG_DIRECTORY);
                        file.mkdirs();
                        File file2 = new File(file, str);
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
                        String readLine = bufferedReader.readLine();
                        if (DeviceConfigDebugHelper.DUMP_FILE_VERSION_1.equals(readLine)) {
                            file2.getCanonicalPath();
                            StringBuilder sb = new StringBuilder();
                            String str3 = str2;
                            while (true) {
                                String readLine2 = bufferedReader.readLine();
                                if (readLine2 == null) {
                                    break;
                                }
                                sb.append(str3);
                                sb.append(readLine2.replace(",", "\\,"));
                                str3 = "\n";
                            }
                            str2 = sb.toString();
                        } else {
                            AnonymousClass0NO.A0E(DeviceConfigDebugHelper.TAG, "Unknown device config dump file version to read: %s", readLine);
                        }
                    } catch (IOException e) {
                        AnonymousClass0NO.A0H(DeviceConfigDebugHelper.TAG, e, "failed to read config values");
                    }
                    List<String[]> A04 = A00.A04(context, str2.split("\n"));
                    if (A04.isEmpty()) {
                        AnonymousClass0NO.A0F(DeviceConfigDebugHelper.TAG, "Nothing got overridden from the imported file: %s", stringExtra);
                    } else {
                        A04.size();
                    }
                }
            }
        }
    }

    public static class OverrideAction implements AnonymousClass0b8 {
        @Override // X.AnonymousClass0b8
        public final void onReceive(Context context, Intent intent, AnonymousClass0b9 r8) {
            DeviceConfigDebugHelper A00 = DeviceConfigDebugHelper.A00();
            if (A00 != null) {
                String[] stringArrayExtra = intent.getStringArrayExtra(DeviceConfigDebugReceiver.CONFIG_PARAM_OVERRIDDEN_PAYLOAD);
                if (stringArrayExtra == null) {
                    AnonymousClass0NO.A08(DeviceConfigDebugReceiver.TAG, "Missing \"config_param_value\" extra string array in the override action");
                    return;
                }
                List<String[]> A04 = A00.A04(context, stringArrayExtra);
                if (A04.isEmpty()) {
                    AnonymousClass0NO.A0E(DeviceConfigDebugReceiver.TAG, "Failed to override config param %s", Arrays.toString(stringArrayExtra));
                    return;
                }
                A04.size();
                DeviceConfigDebugReceiver.A00(A04);
            }
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DeviceConfigDebugReceiver() {
        /*
            r3 = this;
            com.google.common.collect.ImmutableMap$Builder r2 = new com.google.common.collect.ImmutableMap$Builder
            r2.<init>()
            com.oculus.deviceconfigservice.DeviceConfigDebugReceiver$DumpAction r1 = new com.oculus.deviceconfigservice.DeviceConfigDebugReceiver$DumpAction
            r1.<init>()
            java.lang.String r0 = "oculus.intent.action.DC_DUMP"
            r2.put(r0, r1)
            com.oculus.deviceconfigservice.DeviceConfigDebugReceiver$UpdateAction r1 = new com.oculus.deviceconfigservice.DeviceConfigDebugReceiver$UpdateAction
            r1.<init>()
            java.lang.String r0 = "oculus.intent.action.DC_UPDATE"
            r2.put(r0, r1)
            com.oculus.deviceconfigservice.DeviceConfigDebugReceiver$OverrideAction r1 = new com.oculus.deviceconfigservice.DeviceConfigDebugReceiver$OverrideAction
            r1.<init>()
            java.lang.String r0 = "oculus.intent.action.DC_OVERRIDE"
            r2.put(r0, r1)
            com.oculus.deviceconfigservice.DeviceConfigDebugReceiver$ClearOverrideAction r1 = new com.oculus.deviceconfigservice.DeviceConfigDebugReceiver$ClearOverrideAction
            r1.<init>()
            java.lang.String r0 = "oculus.intent.action.DC_CLEAR_OVERRIDE"
            r2.put(r0, r1)
            com.oculus.deviceconfigservice.DeviceConfigDebugReceiver$ExportAction r1 = new com.oculus.deviceconfigservice.DeviceConfigDebugReceiver$ExportAction
            r1.<init>()
            java.lang.String r0 = "oculus.intent.action.DC_EXPORT"
            r2.put(r0, r1)
            com.oculus.deviceconfigservice.DeviceConfigDebugReceiver$ImportAction r1 = new com.oculus.deviceconfigservice.DeviceConfigDebugReceiver$ImportAction
            r1.<init>()
            java.lang.String r0 = "oculus.intent.action.DC_IMPORT"
            r2.put(r0, r1)
            com.oculus.deviceconfigservice.DeviceConfigDebugReceiver$ForceFetchAction r1 = new com.oculus.deviceconfigservice.DeviceConfigDebugReceiver$ForceFetchAction
            r1.<init>()
            java.lang.String r0 = "oculus.intent.action.DC_FORCE_FETCH"
            r2.put(r0, r1)
            com.google.common.collect.ImmutableMap r0 = r2.build()
            com.google.common.collect.ImmutableSet r0 = r0.entrySet()
            X.0s1 r0 = r0.iterator()
            r3.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.deviceconfigservice.DeviceConfigDebugReceiver.<init>():void");
    }

    /* JADX WARN: Incorrect return type in method signature: (Ljava/util/List<[Ljava/lang/String;>;)Ljava/lang/String; */
    public static void A00(List list) {
        StringBuilder sb = new StringBuilder();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            sb.append(Arrays.toString((Object[]) it.next()));
        }
        sb.toString();
    }

    public static class UpdateAction implements AnonymousClass0b8 {
        @Override // X.AnonymousClass0b8
        public final void onReceive(Context context, Intent intent, AnonymousClass0b9 r3) {
        }
    }
}
