package com.oculus.alpenglow.config;

import X.AbstractC01940Ou;
import com.facebook.graphql.modelutil.GeneratedGraphQL;
import com.facebook.infer.annotation.Nullsafe;
import com.google.common.collect.ImmutableList;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMDayOfWeek;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMNetworkType;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMOculusAUICapability;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMOculusGuardianMode;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMOculusHomeButtonBehavior;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMOculusLockScreenPinType;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMOculusOSConfigTelemetryState;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMOculusOSConfigUpdateRule;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMOculusOSCrashReportingState;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMOculusOSDeviceAdmin;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMOculusOSHandTrackingState;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMUnknownSourcesSetting;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@GeneratedGraphQL
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public interface Device extends AbstractC01940Ou {

    @GeneratedGraphQL
    @ThreadSafe
    public interface ManagementInfo extends AbstractC01940Ou {

        @GeneratedGraphQL
        @ThreadSafe
        public interface DeviceConfig extends AbstractC01940Ou {

            @GeneratedGraphQL
            @ThreadSafe
            public interface DeviceApps extends AbstractC01940Ou {

                @GeneratedGraphQL
                @ThreadSafe
                public interface Nodes extends AbstractC01940Ou {
                    @Nullable
                    LocalApp A17();
                }

                ImmutableList<? extends Nodes> A4C();
            }

            @GeneratedGraphQL
            @ThreadSafe
            public interface DeviceRemoteApps extends AbstractC01940Ou {

                @GeneratedGraphQL
                @ThreadSafe
                public interface Nodes extends AbstractC01940Ou {

                    @GeneratedGraphQL
                    @ThreadSafe
                    public interface ApplicationBinary extends AbstractC01940Ou {
                        RemoteResourceAsset A1B();

                        long A3q();
                    }

                    @GeneratedGraphQL
                    @ThreadSafe
                    public interface ApplicationMetadata extends AbstractC01940Ou {
                    }

                    @GeneratedGraphQL
                    @ThreadSafe
                    public interface ExternalResourceFiles extends AbstractC01940Ou {

                        @GeneratedGraphQL
                        @ThreadSafe
                        public interface ExternalResourceFilesNodes extends AbstractC01940Ou {
                            RemoteResourceAsset A1B();

                            long A3q();

                            @Nullable
                            String getId();
                        }

                        ImmutableList<? extends ExternalResourceFilesNodes> A4C();
                    }

                    @Nullable
                    ApplicationBinary A2x();

                    @Nullable
                    ExternalResourceFiles A3X();

                    long A3q();

                    @Nullable
                    String getId();
                }

                ImmutableList<? extends Nodes> A4C();
            }

            @GeneratedGraphQL
            @ThreadSafe
            public interface NetworkConfig extends AbstractC01940Ou {

                @GeneratedGraphQL
                @ThreadSafe
                public interface WifiNetworks extends AbstractC01940Ou {

                    @GeneratedGraphQL
                    @ThreadSafe
                    public interface Nodes extends AbstractC01940Ou {
                        @Nullable
                        String A3H();

                        @Nullable
                        GraphQLHWMNetworkType A4B();

                        @Nullable
                        String A4X();
                    }

                    ImmutableList<? extends Nodes> A4C();
                }

                @Nullable
                WifiNetworks A4v();
            }

            @GeneratedGraphQL
            @ThreadSafe
            public interface OsConfig extends AbstractC01940Ou {

                @GeneratedGraphQL
                @ThreadSafe
                public interface LockScreen extends AbstractC01940Ou {
                    @Nullable
                    String A4l();

                    @Nullable
                    GraphQLHWMOculusLockScreenPinType A4m();
                }

                @GeneratedGraphQL
                @ThreadSafe
                public interface UpdateTimeWindows extends AbstractC01940Ou {
                    ImmutableList<GraphQLHWMDayOfWeek> A3L();

                    int A4w();

                    int A4x();
                }

                boolean A32();

                @Nullable
                GraphQLHWMOculusOSCrashReportingState A3G();

                @Nullable
                GraphQLHWMOculusOSDeviceAdmin A3O();

                @Nullable
                GraphQLHWMOculusOSHandTrackingState A3c();

                @Nullable
                String A3v();

                @Nullable
                LockScreen A3w();

                @Nullable
                GraphQLHWMOculusOSConfigTelemetryState A4d();

                @Nullable
                String A4e();

                @Nullable
                String A4j();

                @Nullable
                GraphQLHWMUnknownSourcesSetting A4k();

                @Nullable
                GraphQLHWMOculusOSConfigUpdateRule A4o();

                ImmutableList<? extends UpdateTimeWindows> A4p();
            }

            @GeneratedGraphQL
            @ThreadSafe
            public interface ShellModes extends AbstractC01940Ou {

                @GeneratedGraphQL
                @ThreadSafe
                public interface Nodes extends AbstractC01940Ou {
                    ImmutableList<GraphQLHWMOculusAUICapability> A31();

                    @Nullable
                    String A3M();

                    @Nullable
                    GraphQLHWMOculusHomeButtonBehavior A3f();

                    ImmutableList<String> A3t();

                    @Nullable
                    String A4l();

                    @Nullable
                    String getName();
                }

                ImmutableList<? extends Nodes> A4C();
            }

            int A3C();

            @Nullable
            DeviceApps A3P();

            @Nullable
            DeviceRemoteApps A3R();

            boolean A3U();

            @Nullable
            GraphQLHWMOculusGuardianMode A3b();

            long A3q();

            @Nullable
            NetworkConfig A4A();

            @Nullable
            OsConfig A4D();

            @Nullable
            ShellModes A4U();
        }

        @GeneratedGraphQL
        @ThreadSafe
        public interface Subscription extends AbstractC01940Ou {
            long A3W();
        }

        @Nullable
        DeviceConfig A3Q();

        @Nullable
        Subscription A4a();
    }

    PersistedDevice A19();

    boolean A3p();

    @Nullable
    ManagementInfo A3y();

    @Nullable
    String getOwnerName();
}
