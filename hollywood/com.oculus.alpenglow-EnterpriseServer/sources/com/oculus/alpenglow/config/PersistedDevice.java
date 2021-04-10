package com.oculus.alpenglow.config;

import X.AbstractC01940Ou;
import com.facebook.graphql.modelutil.GeneratedGraphQL;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.ThreadSafe;

@GeneratedGraphQL
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public interface PersistedDevice extends AbstractC01940Ou {

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
                }
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
                        }
                    }
                }
            }

            @GeneratedGraphQL
            @ThreadSafe
            public interface OsConfig extends AbstractC01940Ou {

                @GeneratedGraphQL
                @ThreadSafe
                public interface UpdateTimeWindows extends AbstractC01940Ou {
                }
            }

            @GeneratedGraphQL
            @ThreadSafe
            public interface ShellModes extends AbstractC01940Ou {

                @GeneratedGraphQL
                @ThreadSafe
                public interface Nodes extends AbstractC01940Ou {
                }
            }
        }

        @GeneratedGraphQL
        @ThreadSafe
        public interface Subscription extends AbstractC01940Ou {
        }
    }
}
