package sun.security.util;

import java.security.AlgorithmParameters;
import java.security.Key;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DisabledAlgorithmConstraints extends AbstractAlgorithmConstraints {
    private static final Debug debug = Debug.getInstance("certpath");
    private final Constraints algorithmConstraints;
    private final String[] disabledAlgorithms;

    public DisabledAlgorithmConstraints(String str) {
        this(str, new AlgorithmDecomposer());
    }

    public DisabledAlgorithmConstraints(String str, AlgorithmDecomposer algorithmDecomposer) {
        super(algorithmDecomposer);
        this.disabledAlgorithms = AbstractAlgorithmConstraints.getAlgorithms(str);
        this.algorithmConstraints = new Constraints(this.disabledAlgorithms);
    }

    public final boolean permits(Set set, String str, AlgorithmParameters algorithmParameters) {
        if (set != null && !set.isEmpty()) {
            return AbstractAlgorithmConstraints.checkAlgorithm(this.disabledAlgorithms, str, this.decomposer);
        }
        throw new IllegalArgumentException("No cryptographic primitive specified");
    }

    public final boolean permits(Set set, Key key) {
        return checkConstraints(set, "", key, null);
    }

    private boolean checkConstraints(Set set, String str, Key key, AlgorithmParameters algorithmParameters) {
        if (key == null) {
            throw new IllegalArgumentException("The key cannot be null");
        } else if ((str == null || str.length() == 0 || permits(set, str, algorithmParameters)) && permits(set, key.getAlgorithm(), null)) {
            return this.algorithmConstraints.permits(key);
        } else {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static class Constraints {
        private static final Pattern keySizePattern = Pattern.compile("keySize\\s*(<=|<|==|!=|>|>=)\\s*(\\d+)");
        private Map constraintsMap = new HashMap();

        public Constraints(String[] strArr) {
            Constraint constraint;
            int length = strArr.length;
            int i = 0;
            int i2 = 0;
            while (i2 < length) {
                String str = strArr[i2];
                if (str != null && !str.isEmpty()) {
                    String trim = str.trim();
                    if (DisabledAlgorithmConstraints.debug != null) {
                        DisabledAlgorithmConstraints.debug.println("Constraints: " + trim);
                    }
                    int indexOf = trim.indexOf(32);
                    if (indexOf > 0) {
                        String hashName = AlgorithmDecomposer.hashName(trim.substring(i, indexOf).toUpperCase(Locale.ENGLISH));
                        String[] split = trim.substring(indexOf + 1).split("&");
                        int length2 = split.length;
                        int i3 = i;
                        Constraint constraint2 = null;
                        Constraint constraint3 = null;
                        int i4 = i3;
                        while (i4 < length2) {
                            String trim2 = split[i4].trim();
                            Matcher matcher = keySizePattern.matcher(trim2);
                            if (matcher.matches()) {
                                if (DisabledAlgorithmConstraints.debug != null) {
                                    DisabledAlgorithmConstraints.debug.println("Constraints set to keySize: " + trim2);
                                }
                                constraint = new KeySizeConstraint(hashName, Constraint.Operator.of(matcher.group(1)), Integer.parseInt(matcher.group(2)));
                            } else if (trim2.equalsIgnoreCase("jdkCA")) {
                                if (DisabledAlgorithmConstraints.debug != null) {
                                    DisabledAlgorithmConstraints.debug.println("Constraints set to jdkCA.");
                                }
                                if (i3 == 0) {
                                    constraint = new jdkCAConstraint(hashName);
                                    i3 = 1;
                                } else {
                                    throw new IllegalArgumentException("Only one jdkCA entry allowed in property. Constraint: " + trim);
                                }
                            } else {
                                constraint = constraint2;
                            }
                            if (constraint3 == null) {
                                if (!this.constraintsMap.containsKey(hashName)) {
                                    this.constraintsMap.putIfAbsent(hashName, new HashSet());
                                }
                                if (constraint != null) {
                                    ((Set) this.constraintsMap.get(hashName)).add(constraint);
                                }
                            } else {
                                constraint3.nextConstraint = constraint;
                            }
                            i4++;
                            constraint2 = constraint;
                            constraint3 = constraint2;
                        }
                        continue;
                    } else {
                        this.constraintsMap.putIfAbsent(trim.toUpperCase(Locale.ENGLISH), new HashSet());
                    }
                }
                i2++;
                i = 0;
            }
        }

        private Set getConstraints(String str) {
            return (Set) this.constraintsMap.get(str);
        }

        public boolean permits(Key key) {
            Set<Constraint> constraints = getConstraints(key.getAlgorithm());
            if (constraints == null) {
                return true;
            }
            for (Constraint constraint : constraints) {
                if (!constraint.permits(key)) {
                    if (DisabledAlgorithmConstraints.debug == null) {
                        return false;
                    }
                    Debug debug = DisabledAlgorithmConstraints.debug;
                    debug.println("keySizeConstraint: failed key constraint check " + KeyUtil.getKeySize(key));
                    return false;
                }
            }
            return true;
        }
    }

    /* access modifiers changed from: private */
    public static abstract class Constraint {
        String algorithm;
        Constraint nextConstraint;

        public boolean permits(Key key) {
            return true;
        }

        private Constraint() {
            this.nextConstraint = null;
        }

        /* synthetic */ Constraint(AnonymousClass1 r1) {
            this();
        }

        enum Operator {
            EQ,
            NE,
            LT,
            LE,
            GT,
            GE;

            /* JADX WARNING: Removed duplicated region for block: B:32:0x0061  */
            /* JADX WARNING: Removed duplicated region for block: B:49:0x0096  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            static sun.security.util.DisabledAlgorithmConstraints.Constraint.Operator of(java.lang.String r7) {
                /*
                // Method dump skipped, instructions count: 153
                */
                throw new UnsupportedOperationException("Method not decompiled: sun.security.util.DisabledAlgorithmConstraints.Constraint.Operator.of(java.lang.String):sun.security.util.DisabledAlgorithmConstraints$Constraint$Operator");
            }
        }
    }

    private static class jdkCAConstraint extends Constraint {
        jdkCAConstraint(String str) {
            super(null);
            this.algorithm = str;
        }
    }

    private static class KeySizeConstraint extends Constraint {
        private int maxSize;
        private int minSize;
        private int prohibitedSize = -1;

        public KeySizeConstraint(String str, Constraint.Operator operator, int i) {
            super(null);
            this.algorithm = str;
            int i2 = 0;
            switch (AnonymousClass1.$SwitchMap$sun$security$util$DisabledAlgorithmConstraints$Constraint$Operator[operator.ordinal()]) {
                case 1:
                    this.minSize = 0;
                    this.maxSize = Integer.MAX_VALUE;
                    this.prohibitedSize = i;
                    return;
                case 2:
                    this.minSize = i;
                    this.maxSize = i;
                    return;
                case 3:
                    this.minSize = i;
                    this.maxSize = Integer.MAX_VALUE;
                    return;
                case 4:
                    this.minSize = i + 1;
                    this.maxSize = Integer.MAX_VALUE;
                    return;
                case 5:
                    this.minSize = 0;
                    this.maxSize = i;
                    return;
                case 6:
                    this.minSize = 0;
                    this.maxSize = i > 1 ? i - 1 : i2;
                    return;
                default:
                    this.minSize = Integer.MAX_VALUE;
                    this.maxSize = -1;
                    return;
            }
        }

        @Override // sun.security.util.DisabledAlgorithmConstraints.Constraint
        public boolean permits(Key key) {
            Constraint constraint = this.nextConstraint;
            if (constraint != null && constraint.permits(key)) {
                return true;
            }
            if (DisabledAlgorithmConstraints.debug != null) {
                Debug debug = DisabledAlgorithmConstraints.debug;
                debug.println("KeySizeConstraints.permits(): " + this.algorithm);
            }
            return permitsImpl(key);
        }

        private boolean permitsImpl(Key key) {
            if (this.algorithm.compareToIgnoreCase(key.getAlgorithm()) != 0) {
                return true;
            }
            int keySize = KeyUtil.getKeySize(key);
            if (keySize == 0) {
                return false;
            }
            if (keySize <= 0) {
                return true;
            }
            if (keySize < this.minSize || keySize > this.maxSize || this.prohibitedSize == keySize) {
                return false;
            }
            return true;
        }
    }

    /* renamed from: sun.security.util.DisabledAlgorithmConstraints$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$sun$security$util$DisabledAlgorithmConstraints$Constraint$Operator = new int[Constraint.Operator.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|(3:11|12|14)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                sun.security.util.DisabledAlgorithmConstraints$Constraint$Operator[] r0 = sun.security.util.DisabledAlgorithmConstraints.Constraint.Operator.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                sun.security.util.DisabledAlgorithmConstraints.AnonymousClass1.$SwitchMap$sun$security$util$DisabledAlgorithmConstraints$Constraint$Operator = r0
                int[] r0 = sun.security.util.DisabledAlgorithmConstraints.AnonymousClass1.$SwitchMap$sun$security$util$DisabledAlgorithmConstraints$Constraint$Operator     // Catch:{ NoSuchFieldError -> 0x0014 }
                sun.security.util.DisabledAlgorithmConstraints$Constraint$Operator r1 = sun.security.util.DisabledAlgorithmConstraints.Constraint.Operator.EQ     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = sun.security.util.DisabledAlgorithmConstraints.AnonymousClass1.$SwitchMap$sun$security$util$DisabledAlgorithmConstraints$Constraint$Operator     // Catch:{ NoSuchFieldError -> 0x001f }
                sun.security.util.DisabledAlgorithmConstraints$Constraint$Operator r1 = sun.security.util.DisabledAlgorithmConstraints.Constraint.Operator.NE     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = sun.security.util.DisabledAlgorithmConstraints.AnonymousClass1.$SwitchMap$sun$security$util$DisabledAlgorithmConstraints$Constraint$Operator     // Catch:{ NoSuchFieldError -> 0x002a }
                sun.security.util.DisabledAlgorithmConstraints$Constraint$Operator r1 = sun.security.util.DisabledAlgorithmConstraints.Constraint.Operator.LT     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = sun.security.util.DisabledAlgorithmConstraints.AnonymousClass1.$SwitchMap$sun$security$util$DisabledAlgorithmConstraints$Constraint$Operator     // Catch:{ NoSuchFieldError -> 0x0035 }
                sun.security.util.DisabledAlgorithmConstraints$Constraint$Operator r1 = sun.security.util.DisabledAlgorithmConstraints.Constraint.Operator.LE     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = sun.security.util.DisabledAlgorithmConstraints.AnonymousClass1.$SwitchMap$sun$security$util$DisabledAlgorithmConstraints$Constraint$Operator     // Catch:{ NoSuchFieldError -> 0x0040 }
                sun.security.util.DisabledAlgorithmConstraints$Constraint$Operator r1 = sun.security.util.DisabledAlgorithmConstraints.Constraint.Operator.GT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = sun.security.util.DisabledAlgorithmConstraints.AnonymousClass1.$SwitchMap$sun$security$util$DisabledAlgorithmConstraints$Constraint$Operator     // Catch:{ NoSuchFieldError -> 0x004b }
                sun.security.util.DisabledAlgorithmConstraints$Constraint$Operator r1 = sun.security.util.DisabledAlgorithmConstraints.Constraint.Operator.GE     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: sun.security.util.DisabledAlgorithmConstraints.AnonymousClass1.<clinit>():void");
        }
    }
}
