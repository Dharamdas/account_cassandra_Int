package com.test.config.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableMetrics
public class SpringMetricsConfigurations extends MetricsConfigurerAdapter{

    @Value("${metrics.console.enabled:true}")
    private boolean consoleEnabled;

    @Override
    public void configureReporters(MetricRegistry metricRegistry)
    {
        if(consoleEnabled)
        {
            registerReporter(getConsoleReporter(metricRegistry));
        }
        registerReporter(getJmxReporter(metricRegistry));
    }

    private ConsoleReporter getConsoleReporter(MetricRegistry metricRegistry){
        ConsoleReporter reporter=ConsoleReporter.forRegistry(metricRegistry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1,TimeUnit.MINUTES);
        return reporter;
    }

    private JmxReporter getJmxReporter(MetricRegistry metricRegistry){
        JmxReporter jmxReporter=JmxReporter.forRegistry(metricRegistry).build();
        jmxReporter.start();
        return jmxReporter;
    }

}
