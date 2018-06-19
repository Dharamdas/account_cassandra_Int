package com.test.config.cassandra;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "cassandra")
public class CassandraProperties {

    private List<String> contactpoints = new ArrayList<String>();
    private int port =9042;
    @NotBlank
    private String username;
    @NotBlank
    private String keyspace;
    private String passwrod;

    private int                         localCoreConnections                   = 1;
    private int                         localMaxConnections                    = 4;
    private int                         localMaxSimultaneousRequests           = 1000;
    @NotNull
    private ReconnectProps              reconnect                              = new ReconnectProps();
    @NotNull
    private RetryProps                  retry                                  = new RetryProps();

    // dc and load balancing
    private String                      localDc;
    private int                         usedHostsPerRemoteDc                   = 0;
    private boolean allowRemoteDCsForLocalConsistencyLevel = false;

    @Getter
    @Setter
    public static class RetryProps {
        private boolean enableDowngradingConsistencyRetryPolicy = false;
    }

    @Getter
    @Setter
    public static class ReconnectProps {
        private int baseDelay = 1000;
        private int maxDelay  = 600000;
    }

}
