package oculus.internal.license;

import android.os.PatternMatcher;
import com.oculus.license.EvaluationResult;
import com.oculus.license.FilterConfig;
import com.oculus.license.License;
import com.oculus.license.PackageFilter;
import com.oculus.license.Rule;
import com.oculus.license.Signer;
import com.oculus.license.UserAction;
import com.oculus.os.PackageMetadata;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import oculus.internal.functional.Pair;
import oculus.internal.functional.Try;
import oculus.internal.license.filter.Condition;
import oculus.internal.license.filter.ConditionFactory;
import oculus.internal.license.filter.UnrecognizedConditionException;

public final class Evaluator {
    public static final int LICENSE_EVALUATION_IGNORE_EXPIRY = 1;
    private final MessageDigest certificateFingerprinter;

    /* access modifiers changed from: package-private */
    public enum EvaluatedRuleDisposition {
        GrantCapabilities,
        DenyCapabilities,
        SkipRule
    }

    /* access modifiers changed from: package-private */
    public enum RuleJudgement {
        GrantAction,
        DenyAction,
        NoDecision
    }

    public Evaluator() {
        try {
            this.certificateFingerprinter = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("unable to initialize evaluator", e);
        }
    }

    private static EvaluatedRuleDisposition computeRuleDisposition(Rule.Disposition disposition, boolean allConditionsMatched) {
        int i = AnonymousClass1.$SwitchMap$com$oculus$license$Rule$Disposition[disposition.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    throw new IllegalArgumentException("Unrecognized rule disposition");
                } else if (allConditionsMatched) {
                    return EvaluatedRuleDisposition.GrantCapabilities;
                } else {
                    return EvaluatedRuleDisposition.DenyCapabilities;
                }
            } else if (allConditionsMatched) {
                return EvaluatedRuleDisposition.DenyCapabilities;
            } else {
                return EvaluatedRuleDisposition.SkipRule;
            }
        } else if (allConditionsMatched) {
            return EvaluatedRuleDisposition.GrantCapabilities;
        } else {
            return EvaluatedRuleDisposition.SkipRule;
        }
    }

    private static RuleJudgement computeRuleJudgement(EvaluatedRuleDisposition disposition, List<String> capabilities, UserAction userAction) {
        if (disposition == EvaluatedRuleDisposition.SkipRule) {
            return RuleJudgement.NoDecision;
        }
        String value = userAction.getValue();
        Objects.requireNonNull(value);
        if (!((Stream) capabilities.stream().parallel()).anyMatch(new Predicate() {
            /* class oculus.internal.license.$$Lambda$8c3iNYrshFV_gM0oAqYDaGX1FA */

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return String.this.equalsIgnoreCase((String) obj);
            }
        })) {
            return RuleJudgement.NoDecision;
        }
        if (disposition == EvaluatedRuleDisposition.GrantCapabilities) {
            return RuleJudgement.GrantAction;
        }
        return RuleJudgement.DenyAction;
    }

    private static boolean allConditionsMatchContext(Condition[] conditions, EvaluationContext evaluationContext) {
        return conditions.length > 0 && ((Stream) Arrays.stream(conditions).parallel()).allMatch(new Predicate() {
            /* class oculus.internal.license.$$Lambda$Evaluator$ESrZhz1elVdoKfrXcQB04y3VCM */

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((Condition) obj).test(EvaluationContext.this);
            }
        });
    }

    /* access modifiers changed from: private */
    public static RuleJudgement evaluateRuleConditions(EvaluationContext evaluationContext, Pair<Rule, Condition[]> pair, UserAction userAction) {
        Rule rule = (Rule) pair.left();
        return computeRuleJudgement(computeRuleDisposition(rule.disposition, allConditionsMatchContext((Condition[]) pair.right(), evaluationContext)), rule.capabilities, userAction);
    }

    /* access modifiers changed from: private */
    public static RuleJudgement reduceJudgements(RuleJudgement first, RuleJudgement second) {
        if (first == RuleJudgement.NoDecision) {
            return second;
        }
        if (second == RuleJudgement.NoDecision) {
            return first;
        }
        if (first == RuleJudgement.DenyAction || second == RuleJudgement.DenyAction) {
            return RuleJudgement.DenyAction;
        }
        return RuleJudgement.GrantAction;
    }

    /* access modifiers changed from: private */
    public static boolean signerMatches(Pair<Signer, byte[]> signer, PackageMetadata packageMetadata) {
        List<PackageMetadata.Signature> signatures = (List) Arrays.stream(packageMetadata.signatures).filter(new Predicate(signer) {
            /* class oculus.internal.license.$$Lambda$Evaluator$ZgW3wnMHUb80S1uTU0ZQb3RpAMw */
            private final /* synthetic */ Pair f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Arrays.equals(((PackageMetadata.Signature) obj).fingerprint, (byte[]) this.f$0.right);
            }
        }).collect(Collectors.toList());
        if (((Signer) signer.left).digests.size() == 0) {
            return signatures.size() != 0;
        }
        return ((Set) signatures.stream().map($$Lambda$Evaluator$0347ooCOX3iomDnRkO3e0VWzts8.INSTANCE).collect(Collectors.toSet())).containsAll(((Signer) signer.left).digests);
    }

    static /* synthetic */ Signer.Digest lambda$signerMatches$2(PackageMetadata.Signature s) {
        return new Signer.Digest(PackageMetadata.Signature.Algorithm.extractDigestName(s.algorithmName), s.digest);
    }

    /* access modifiers changed from: private */
    /* renamed from: packageFilterMatches */
    public boolean lambda$packageFiltersMatch$7$Evaluator(PackageFilter packageFilter, PackageMetadata packageMetadata) {
        if (packageFilter.identifierPattern != null && !new PatternMatcher(packageFilter.identifierPattern, 2).match(packageMetadata.packageIdentifier)) {
            return false;
        }
        if (packageFilter.signers.size() == 0) {
            return true;
        }
        HashSet<Pair<Signer, byte[]>> filterFingerprints = (HashSet) packageFilter.signers.stream().map(new Function() {
            /* class oculus.internal.license.$$Lambda$Evaluator$qcWPfsohKDdXeWrvAMBZmdtaJg */

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Evaluator.this.lambda$packageFilterMatches$4$Evaluator((Signer) obj);
            }
        }).map($$Lambda$Evaluator$o6N5SBFVKLurhwZGg5pyziJ63I.INSTANCE).filter($$Lambda$fd4H8A6xA0KSkp8NqmJbEBR8tWM.INSTANCE).collect(Collectors.toCollection($$Lambda$1IrdwagwK5Z4XLGsWWC25XLtr5Q.INSTANCE));
        if (filterFingerprints.size() == packageFilter.signers.size()) {
            return filterFingerprints.stream().anyMatch(new Predicate(packageMetadata) {
                /* class oculus.internal.license.$$Lambda$Evaluator$aeJ6fiT9WdDWmqkRWX5uiJGDuD8 */
                private final /* synthetic */ PackageMetadata f$0;

                {
                    this.f$0 = r1;
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Evaluator.signerMatches((Pair) obj, this.f$0);
                }
            });
        }
        throw new RuntimeException("Failed to compute certificate fingerprints");
    }

    public /* synthetic */ Try lambda$packageFilterMatches$4$Evaluator(Signer s) {
        return Try.Try(new Try.F0(s) {
            /* class oculus.internal.license.$$Lambda$Evaluator$6Xdq8T4pQTHZ4FdSbaiPCOapxHM */
            private final /* synthetic */ Signer f$1;

            {
                this.f$1 = r2;
            }

            public final Object get() {
                return Evaluator.this.lambda$packageFilterMatches$3$Evaluator(this.f$1);
            }
        });
    }

    public /* synthetic */ Pair lambda$packageFilterMatches$3$Evaluator(Signer s) throws Exception {
        return new Pair(s, ((MessageDigest) this.certificateFingerprinter.clone()).digest(s.certificate.getEncoded()));
    }

    static /* synthetic */ Pair lambda$packageFilterMatches$5(Try t) {
        return (Pair) t.orElse((Object) null);
    }

    /* access modifiers changed from: private */
    /* renamed from: packageFiltersMatch */
    public boolean lambda$evaluate$9$Evaluator(License license, EvaluationContext evaluationContext) {
        return license.packageFilters.stream().anyMatch(new Predicate(evaluationContext.packageMetadata) {
            /* class oculus.internal.license.$$Lambda$Evaluator$lUVbGmWIcJeKXbGznlhaIYBb9k */
            private final /* synthetic */ PackageMetadata f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Evaluator.this.lambda$packageFiltersMatch$7$Evaluator(this.f$1, (PackageFilter) obj);
            }
        });
    }

    public EvaluationResult evaluate(EvaluationContext evaluationContext, License license, UserAction userAction) throws LicenseEvaluationException {
        return evaluate(evaluationContext, license, userAction, 0);
    }

    public EvaluationResult evaluate(EvaluationContext evaluationContext, License license, UserAction userAction, int flags) throws LicenseEvaluationException {
        return evaluate(evaluationContext, Arrays.asList(license), userAction, flags);
    }

    public EvaluationResult evaluate(EvaluationContext evaluationContext, Collection<License> licenses, UserAction userAction) throws LicenseEvaluationException {
        return evaluate(evaluationContext, licenses, userAction, 0);
    }

    public EvaluationResult evaluate(EvaluationContext evaluationContext, Collection<License> licenses, UserAction userAction, int flags) throws LicenseEvaluationException {
        long j;
        Stream<License> stream = licenses.stream();
        if ((flags & 1) == 0) {
            if (evaluationContext.asOfEpochSeconds != null) {
                j = evaluationContext.asOfEpochSeconds.longValue();
            } else {
                j = System.currentTimeMillis() / 1000;
            }
            stream = stream.filter(new Predicate() {
                /* class oculus.internal.license.$$Lambda$Evaluator$kcBtlCtRpxwJhfrfvui8Nxo6Pns */

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Evaluator.lambda$evaluate$8(Long.this, (License) obj);
                }
            });
        }
        return evaluateRules(stream.filter(new Predicate(evaluationContext) {
            /* class oculus.internal.license.$$Lambda$Evaluator$y8JlN1D9b_UWKGOJWPX68oGe1ho */
            private final /* synthetic */ EvaluationContext f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Evaluator.this.lambda$evaluate$9$Evaluator(this.f$1, (License) obj);
            }
        }).flatMap($$Lambda$Evaluator$jVBDtRmzBeU_BAdWO8yTwzrle6E.INSTANCE), evaluationContext, userAction);
    }

    static /* synthetic */ boolean lambda$evaluate$8(Long asOf, License l) {
        return !l.isExpired(asOf.longValue());
    }

    public static Stream<License> filterByEvaluationContext(Stream<License> licenses, EvaluationContext evaluationContext) {
        return licenses.filter(new Predicate(evaluationContext) {
            /* class oculus.internal.license.$$Lambda$Evaluator$SsVunZX5uzDBlIVeeQEs9SOm3E */
            private final /* synthetic */ EvaluationContext f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((License) obj).rules.stream().anyMatch(new Predicate(this.f$1) {
                    /* class oculus.internal.license.$$Lambda$Evaluator$_Ezg5c1kkULVsNSyPsQrKvh27Ds */
                    private final /* synthetic */ EvaluationContext f$1;

                    {
                        this.f$1 = r2;
                    }

                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Evaluator.lambda$filterByEvaluationContext$11(ConditionFactory.this, this.f$1, (Rule) obj);
                    }
                });
            }
        });
    }

    static /* synthetic */ boolean lambda$filterByEvaluationContext$11(ConditionFactory conditionFactory, EvaluationContext evaluationContext, Rule r) {
        try {
            return allConditionsMatchContext(conditionFactory.fromFilterConfigs((FilterConfig[]) r.conditions.toArray(new FilterConfig[0])), evaluationContext);
        } catch (UnrecognizedConditionException e) {
            return false;
        }
    }

    static EvaluationResult evaluateRules(Stream<Rule> rules, EvaluationContext evaluationContext, UserAction userAction) throws LicenseEvaluationException {
        try {
            int i = AnonymousClass1.$SwitchMap$oculus$internal$license$Evaluator$RuleJudgement[((RuleJudgement) ((Try) ((Stream) rules.parallel()).map(new Function() {
                /* class oculus.internal.license.$$Lambda$Evaluator$gBhZSYnI15a8pdVlLkTP_4ohBL0 */

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Try.Try(new Try.F0((Rule) obj, ConditionFactory.this) {
                        /* class oculus.internal.license.$$Lambda$Evaluator$Wbp8f2ybenwPWveKofmWoP7b2hk */
                        private final /* synthetic */ Rule f$0;
                        private final /* synthetic */ ConditionFactory f$1;

                        {
                            this.f$0 = r1;
                            this.f$1 = r2;
                        }

                        public final Object get() {
                            return Evaluator.lambda$evaluateRules$13(this.f$0, this.f$1);
                        }
                    });
                }
            }).map(new Function(userAction) {
                /* class oculus.internal.license.$$Lambda$Evaluator$7M0HPG4wvgVlJ7RdXqLH3QVOY */
                private final /* synthetic */ UserAction f$1;

                {
                    this.f$1 = r2;
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((Try) obj).map(new Function(this.f$1) {
                        /* class oculus.internal.license.$$Lambda$Evaluator$Z3KPXrI2kG3r1woZfOySUWQR14 */
                        private final /* synthetic */ UserAction f$1;

                        {
                            this.f$1 = r2;
                        }

                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return Evaluator.evaluateRuleConditions(EvaluationContext.this, (Pair) obj, this.f$1);
                        }
                    });
                }
            }).collect(Try.fold(Collectors.reducing(RuleJudgement.NoDecision, $$Lambda$Evaluator$i7tbuWfASGBl1vufMktgIf_eBVM.INSTANCE)))).orElseThrow()).ordinal()];
            if (i == 1) {
                return EvaluationResult.USER_ACTION_GRANTED;
            }
            if (i == 2) {
                return EvaluationResult.USER_ACTION_DENIED;
            }
            if (i != 3) {
                return EvaluationResult.USER_ACTION_DENIED;
            }
            return EvaluationResult.NO_LICENSE_APPLIED;
        } catch (Exception e) {
            throw new LicenseEvaluationException(e);
        }
    }

    static /* synthetic */ Pair lambda$evaluateRules$13(Rule rule, ConditionFactory conditionFactory) throws Exception {
        return new Pair(rule, conditionFactory.fromFilterConfigs((FilterConfig[]) rule.conditions.toArray(new FilterConfig[0])));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: oculus.internal.license.Evaluator$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$license$Rule$Disposition = new int[Rule.Disposition.values().length];
        static final /* synthetic */ int[] $SwitchMap$oculus$internal$license$Evaluator$RuleJudgement = new int[RuleJudgement.values().length];

        static {
            try {
                $SwitchMap$oculus$internal$license$Evaluator$RuleJudgement[RuleJudgement.GrantAction.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$oculus$internal$license$Evaluator$RuleJudgement[RuleJudgement.DenyAction.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$oculus$internal$license$Evaluator$RuleJudgement[RuleJudgement.NoDecision.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$oculus$license$Rule$Disposition[Rule.Disposition.grant_or_skip.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$oculus$license$Rule$Disposition[Rule.Disposition.deny_or_skip.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$oculus$license$Rule$Disposition[Rule.Disposition.grant_or_deny.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
        }
    }
}
