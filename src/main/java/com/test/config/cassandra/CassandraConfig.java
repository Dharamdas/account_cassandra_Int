package com.test.config.cassandra;

import com.datastax.driver.core.*;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
import com.datastax.driver.core.policies.TokenAwarePolicy;
import com.datastax.driver.mapping.MappingManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ComponentScan(basePackages = "com.test.config.cassandra")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CassandraConfig {

    private final CassandraProperties cassandraProperties;

    @Bean
    protected Cluster cluster() throws Exception{

        Builder builder= Cluster.builder();
        DCAwareRoundRobinPolicy.Builder childBuilder = DCAwareRoundRobinPolicy.builder().withUsedHostsPerRemoteDc(cassandraProperties.getUsedHostsPerRemoteDc());
        if (cassandraProperties.isAllowRemoteDCsForLocalConsistencyLevel()) {
            log.info("Allow remote DCs for local consistency level is true");
            childBuilder.allowRemoteDCsForLocalConsistencyLevel();
        }

        String localDc = cassandraProperties.getLocalDc();
        if (StringUtils.isNotBlank(localDc)) {
            log.info("connecting to cassandra with local dc: {}", localDc);
            childBuilder.withLocalDc(localDc);
        }

        List<String> contactPoints = cassandraProperties.getContactpoints();
        log.info("connecting to cassandra with contact points: {}", contactPoints);
        contactPoints.forEach(builder::addContactPoint);

        TokenAwarePolicy lbPolicy = new TokenAwarePolicy(childBuilder.build());
        builder.withLoadBalancingPolicy(lbPolicy);
        builder.withPoolingOptions(poolingOptions());
        builder.withPort(cassandraProperties.getPort());
        AuthProvider authProvider =
                new PlainTextAuthProvider(cassandraProperties.getUsername(),cassandraProperties.getPasswrod());
        builder.withAuthProvider(authProvider);

        return builder.build();

    }



    @Bean
    protected PoolingOptions poolingOptions() {
        PoolingOptions options = new PoolingOptions();
        int localCoreConnections = cassandraProperties.getLocalCoreConnections();
        int localMaxConnections = cassandraProperties.getLocalMaxConnections();
        int localMaxSimultaneousRequests = cassandraProperties.getLocalMaxSimultaneousRequests();
        log.info("core connections per LOCAL host: {}", localCoreConnections);
        options.setCoreConnectionsPerHost(HostDistance.LOCAL, localCoreConnections);
        log.info("max connections per LOCAL host: {}", localMaxConnections);
        options.setMaxConnectionsPerHost(HostDistance.LOCAL, localMaxConnections);
        log.info("max request per connection for LOCAL host: {}", localMaxSimultaneousRequests);
        options.setMaxRequestsPerConnection(HostDistance.LOCAL, localMaxSimultaneousRequests);
        return options;
    }


    @Bean
    protected Session session() throws Exception {
        return cluster().connect(cassandraProperties.getKeyspace());
    }

    @Bean
    public MappingManager mappingManager() throws Exception {
        return new MappingManager(session());
    }

}
