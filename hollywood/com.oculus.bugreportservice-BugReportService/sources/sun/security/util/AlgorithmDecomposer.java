package sun.security.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class AlgorithmDecomposer {
    private static final Pattern pattern = Pattern.compile("with|and", 2);
    private static final Pattern transPattern = Pattern.compile("/");

    private static Set decomposeImpl(String str) {
        String[] split = transPattern.split(str);
        HashSet hashSet = new HashSet();
        for (String str2 : split) {
            if (!(str2 == null || str2.length() == 0)) {
                String[] split2 = pattern.split(str2);
                for (String str3 : split2) {
                    if (!(str3 == null || str3.length() == 0)) {
                        hashSet.add(str3);
                    }
                }
            }
        }
        return hashSet;
    }

    public Set decompose(String str) {
        if (str == null || str.length() == 0) {
            return new HashSet();
        }
        Set decomposeImpl = decomposeImpl(str);
        if (decomposeImpl.contains("SHA1") && !decomposeImpl.contains("SHA-1")) {
            decomposeImpl.add("SHA-1");
        }
        if (decomposeImpl.contains("SHA-1") && !decomposeImpl.contains("SHA1")) {
            decomposeImpl.add("SHA1");
        }
        if (decomposeImpl.contains("SHA224") && !decomposeImpl.contains("SHA-224")) {
            decomposeImpl.add("SHA-224");
        }
        if (decomposeImpl.contains("SHA-224") && !decomposeImpl.contains("SHA224")) {
            decomposeImpl.add("SHA224");
        }
        if (decomposeImpl.contains("SHA256") && !decomposeImpl.contains("SHA-256")) {
            decomposeImpl.add("SHA-256");
        }
        if (decomposeImpl.contains("SHA-256") && !decomposeImpl.contains("SHA256")) {
            decomposeImpl.add("SHA256");
        }
        if (decomposeImpl.contains("SHA384") && !decomposeImpl.contains("SHA-384")) {
            decomposeImpl.add("SHA-384");
        }
        if (decomposeImpl.contains("SHA-384") && !decomposeImpl.contains("SHA384")) {
            decomposeImpl.add("SHA384");
        }
        if (decomposeImpl.contains("SHA512") && !decomposeImpl.contains("SHA-512")) {
            decomposeImpl.add("SHA-512");
        }
        if (decomposeImpl.contains("SHA-512") && !decomposeImpl.contains("SHA512")) {
            decomposeImpl.add("SHA512");
        }
        return decomposeImpl;
    }

    public static String hashName(String str) {
        return str.replace("-", "");
    }
}
