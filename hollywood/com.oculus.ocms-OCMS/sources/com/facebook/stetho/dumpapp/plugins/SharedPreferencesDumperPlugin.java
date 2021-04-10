package com.facebook.stetho.dumpapp.plugins;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.facebook.stetho.dumpapp.DumpUsageException;
import com.facebook.stetho.dumpapp.DumperContext;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.oculus.appmanager.installer.dumper.InstallerServiceDumper;
import java.io.File;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SharedPreferencesDumperPlugin implements DumperPlugin {
    private static final String NAME = "prefs";
    private static final String XML_SUFFIX = ".xml";
    private final Context mAppContext;

    @Override // com.facebook.stetho.dumpapp.DumperPlugin
    public String getName() {
        return NAME;
    }

    public SharedPreferencesDumperPlugin(Context context) {
        this.mAppContext = context.getApplicationContext();
    }

    @Override // com.facebook.stetho.dumpapp.DumperPlugin
    public void dump(DumperContext dumperContext) throws DumpUsageException {
        PrintStream stdout = dumperContext.getStdout();
        List<String> argsAsList = dumperContext.getArgsAsList();
        String remove = argsAsList.isEmpty() ? "" : argsAsList.remove(0);
        if (remove.equals(InstallerServiceDumper.CMD_PRINT)) {
            doPrint(stdout, argsAsList);
        } else if (remove.equals("write")) {
            doWrite(argsAsList);
        } else {
            doUsage(stdout);
        }
    }

    @SuppressLint({"CommitPrefEdits"})
    private void doWrite(List<String> list) throws DumpUsageException {
        Iterator<String> it = list.iterator();
        String nextArg = nextArg(it, "Expected <path>");
        String nextArg2 = nextArg(it, "Expected <key>");
        Type of = Type.of(nextArg(it, "Expected <type>"));
        if (of != null) {
            SharedPreferences.Editor edit = getSharedPreferences(nextArg).edit();
            switch (of) {
                case BOOLEAN:
                    edit.putBoolean(nextArg2, Boolean.valueOf(nextArgValue(it)).booleanValue());
                    break;
                case INT:
                    edit.putInt(nextArg2, Integer.valueOf(nextArgValue(it)).intValue());
                    break;
                case LONG:
                    edit.putLong(nextArg2, Long.valueOf(nextArgValue(it)).longValue());
                    break;
                case FLOAT:
                    edit.putFloat(nextArg2, Float.valueOf(nextArgValue(it)).floatValue());
                    break;
                case STRING:
                    edit.putString(nextArg2, nextArgValue(it));
                    break;
                case SET:
                    putStringSet(edit, nextArg2, it);
                    break;
            }
            edit.commit();
            return;
        }
        throw new DumpUsageException(Type.appendNamesList(new StringBuilder("Usage: prefs write <path> <key> <type> <value>, where type is one of: "), ", ").toString());
    }

    @Nonnull
    private static String nextArg(Iterator<String> it, String str) throws DumpUsageException {
        if (it.hasNext()) {
            return it.next();
        }
        throw new DumpUsageException(str);
    }

    @Nonnull
    private static String nextArgValue(Iterator<String> it) throws DumpUsageException {
        return nextArg(it, "Expected <value>");
    }

    @TargetApi(11)
    private static void putStringSet(SharedPreferences.Editor editor, String str, Iterator<String> it) {
        HashSet hashSet = new HashSet();
        while (it.hasNext()) {
            hashSet.add(it.next());
        }
        editor.putStringSet(str, hashSet);
    }

    private void doPrint(PrintStream printStream, List<String> list) {
        String str;
        String str2 = this.mAppContext.getApplicationInfo().dataDir + "/shared_prefs";
        String str3 = list.isEmpty() ? "" : list.get(0);
        if (list.size() > 1) {
            str = list.get(1);
        } else {
            str = "";
        }
        printRecursive(printStream, str2, "", str3, str);
    }

    private void printRecursive(PrintStream printStream, String str, String str2, String str3, String str4) {
        String[] list;
        File file = new File(str, str2);
        if (file.isFile()) {
            if (str2.endsWith(XML_SUFFIX)) {
                printFile(printStream, str2.substring(0, str2.length() - 4), str4);
            }
        } else if (file.isDirectory() && (list = file.list()) != null) {
            for (int i = 0; i < list.length; i++) {
                String str5 = TextUtils.isEmpty(str2) ? list[i] : str2 + File.separator + list[i];
                if (str5.startsWith(str3)) {
                    printRecursive(printStream, str, str5, str3, str4);
                }
            }
        }
    }

    private void printFile(PrintStream printStream, String str, String str2) {
        printStream.println(str + ":");
        for (Map.Entry<String, ?> entry : getSharedPreferences(str).getAll().entrySet()) {
            if (entry.getKey().startsWith(str2)) {
                printStream.println("  " + entry.getKey() + " = " + entry.getValue());
            }
        }
    }

    private void doUsage(PrintStream printStream) {
        printStream.println("Usage: dumpapp prefs " + "<command> [command-options]");
        printStream.println("Usage: dumpapp prefs " + "print [pathPrefix [keyPrefix]]");
        StringBuilder sb = new StringBuilder("       dumpapp prefs ");
        sb.append("write <path> <key> <");
        StringBuilder appendNamesList = Type.appendNamesList(sb, "|");
        appendNamesList.append("> <value>");
        printStream.println(appendNamesList);
        printStream.println();
        printStream.println("dumpapp prefs print: Print all matching values from the shared preferences");
        printStream.println();
        printStream.println("dumpapp prefs write: Writes a value to the shared preferences");
    }

    private SharedPreferences getSharedPreferences(String str) {
        return this.mAppContext.getSharedPreferences(str, 4);
    }

    /* access modifiers changed from: private */
    public enum Type {
        BOOLEAN("boolean"),
        INT("int"),
        LONG("long"),
        FLOAT("float"),
        STRING("string"),
        SET("set");
        
        private final String name;

        private Type(String str) {
            this.name = str;
        }

        @Nullable
        public static Type of(String str) {
            Type[] values = values();
            for (Type type : values) {
                if (type.name.equals(str)) {
                    return type;
                }
            }
            return null;
        }

        public static StringBuilder appendNamesList(StringBuilder sb, String str) {
            Type[] values = values();
            boolean z = true;
            for (Type type : values) {
                if (z) {
                    z = false;
                } else {
                    sb.append(str);
                }
                sb.append(type.name);
            }
            return sb;
        }
    }
}
