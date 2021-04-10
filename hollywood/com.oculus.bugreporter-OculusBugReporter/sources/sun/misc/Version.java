package sun.misc;

import java.io.PrintStream;

public class Version {
    private static final String java_profile_name = "";
    private static final String java_runtime_name = "Android Runtime";
    private static final String java_runtime_version = "0.9";
    private static final String java_version = "0";
    private static int jdk_build_number = 0;
    private static int jdk_major_version = 0;
    private static int jdk_micro_version = 0;
    private static int jdk_minor_version = 0;
    private static String jdk_special_version = null;
    private static int jdk_update_version = 0;
    private static boolean jvmVersionInfoAvailable = false;
    private static int jvm_build_number = 0;
    private static int jvm_major_version = 0;
    private static int jvm_micro_version = 0;
    private static int jvm_minor_version = 0;
    private static String jvm_special_version = null;
    private static int jvm_update_version = 0;
    private static final String launcher_name = "";
    private static boolean versionsInitialized = false;

    public static native String getJdkSpecialVersion();

    private static native void getJdkVersionInfo();

    public static native String getJvmSpecialVersion();

    private static native boolean getJvmVersionInfo();

    public static void initSystemProperties() {
        System.setUnchangeableSystemProperty("java.version", "0");
        System.setUnchangeableSystemProperty("java.runtime.version", java_runtime_version);
        System.setUnchangeableSystemProperty("java.runtime.name", java_runtime_name);
    }

    public static void print() {
        print(System.err);
    }

    public static void println() {
        print(System.err);
        System.err.println();
    }

    public static void print(PrintStream ps) {
        boolean isHeadless = false;
        String headless = System.getProperty("java.awt.headless");
        if (headless != null && headless.equalsIgnoreCase("true")) {
            isHeadless = true;
        }
        ps.println(" version \"0\"");
        ps.print("Android Runtime (build 0.9");
        if ("".length() > 0) {
            ps.print(", profile ");
        }
        if (java_runtime_name.indexOf("Embedded") != -1 && isHeadless) {
            ps.print(", headless");
        }
        ps.println(')');
        String java_vm_name = System.getProperty("java.vm.name");
        String java_vm_version = System.getProperty("java.vm.version");
        String java_vm_info = System.getProperty("java.vm.info");
        ps.println(java_vm_name + " (build " + java_vm_version + ", " + java_vm_info + ")");
    }

    public static synchronized int jvmMajorVersion() {
        int i;
        synchronized (Version.class) {
            if (!versionsInitialized) {
                initVersions();
            }
            i = jvm_major_version;
        }
        return i;
    }

    public static synchronized int jvmMinorVersion() {
        int i;
        synchronized (Version.class) {
            if (!versionsInitialized) {
                initVersions();
            }
            i = jvm_minor_version;
        }
        return i;
    }

    public static synchronized int jvmMicroVersion() {
        int i;
        synchronized (Version.class) {
            if (!versionsInitialized) {
                initVersions();
            }
            i = jvm_micro_version;
        }
        return i;
    }

    public static synchronized int jvmUpdateVersion() {
        int i;
        synchronized (Version.class) {
            if (!versionsInitialized) {
                initVersions();
            }
            i = jvm_update_version;
        }
        return i;
    }

    public static synchronized String jvmSpecialVersion() {
        String str;
        synchronized (Version.class) {
            if (!versionsInitialized) {
                initVersions();
            }
            if (jvm_special_version == null) {
                jvm_special_version = getJvmSpecialVersion();
            }
            str = jvm_special_version;
        }
        return str;
    }

    public static synchronized int jvmBuildNumber() {
        int i;
        synchronized (Version.class) {
            if (!versionsInitialized) {
                initVersions();
            }
            i = jvm_build_number;
        }
        return i;
    }

    public static synchronized int jdkMajorVersion() {
        int i;
        synchronized (Version.class) {
            if (!versionsInitialized) {
                initVersions();
            }
            i = jdk_major_version;
        }
        return i;
    }

    public static synchronized int jdkMinorVersion() {
        int i;
        synchronized (Version.class) {
            if (!versionsInitialized) {
                initVersions();
            }
            i = jdk_minor_version;
        }
        return i;
    }

    public static synchronized int jdkMicroVersion() {
        int i;
        synchronized (Version.class) {
            if (!versionsInitialized) {
                initVersions();
            }
            i = jdk_micro_version;
        }
        return i;
    }

    public static synchronized int jdkUpdateVersion() {
        int i;
        synchronized (Version.class) {
            if (!versionsInitialized) {
                initVersions();
            }
            i = jdk_update_version;
        }
        return i;
    }

    public static synchronized String jdkSpecialVersion() {
        String str;
        synchronized (Version.class) {
            if (!versionsInitialized) {
                initVersions();
            }
            if (jdk_special_version == null) {
                jdk_special_version = getJdkSpecialVersion();
            }
            str = jdk_special_version;
        }
        return str;
    }

    public static synchronized int jdkBuildNumber() {
        int i;
        synchronized (Version.class) {
            if (!versionsInitialized) {
                initVersions();
            }
            i = jdk_build_number;
        }
        return i;
    }

    private static synchronized void initVersions() {
        char c;
        synchronized (Version.class) {
            if (!versionsInitialized) {
                jvmVersionInfoAvailable = getJvmVersionInfo();
                if (!jvmVersionInfoAvailable) {
                    CharSequence cs = System.getProperty("java.vm.version");
                    if (cs.length() >= 5 && Character.isDigit(cs.charAt(0)) && cs.charAt(1) == '.' && Character.isDigit(cs.charAt(2)) && cs.charAt(3) == '.' && Character.isDigit(cs.charAt(4))) {
                        jvm_major_version = Character.digit(cs.charAt(0), 10);
                        jvm_minor_version = Character.digit(cs.charAt(2), 10);
                        jvm_micro_version = Character.digit(cs.charAt(4), 10);
                        CharSequence cs2 = cs.subSequence(5, cs.length());
                        if (cs2.charAt(0) == '_' && cs2.length() >= 3) {
                            int nextChar = 0;
                            if (Character.isDigit(cs2.charAt(1)) && Character.isDigit(cs2.charAt(2)) && Character.isDigit(cs2.charAt(3))) {
                                nextChar = 4;
                            } else if (Character.isDigit(cs2.charAt(1)) && Character.isDigit(cs2.charAt(2))) {
                                nextChar = 3;
                            }
                            try {
                                jvm_update_version = Integer.valueOf(cs2.subSequence(1, nextChar).toString()).intValue();
                                if (cs2.length() >= nextChar + 1 && (c = cs2.charAt(nextChar)) >= 'a' && c <= 'z') {
                                    jvm_special_version = Character.toString(c);
                                    nextChar++;
                                }
                                cs2 = cs2.subSequence(nextChar, cs2.length());
                            } catch (NumberFormatException e) {
                                return;
                            }
                        }
                        if (cs2.charAt(0) == 45) {
                            String[] res = cs2.subSequence(1, cs2.length()).toString().split("-");
                            int length = res.length;
                            int i = 0;
                            while (true) {
                                if (i >= length) {
                                    break;
                                }
                                String s = res[i];
                                if (s.charAt(0) == 'b' && s.length() == 3 && Character.isDigit(s.charAt(1)) && Character.isDigit(s.charAt(2))) {
                                    jvm_build_number = Integer.valueOf(s.substring(1, 3)).intValue();
                                    break;
                                }
                                i++;
                            }
                        }
                    }
                }
                getJdkVersionInfo();
                versionsInitialized = true;
            }
        }
    }
}
