package info.batey.examples.integration;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.contrib.codahalemetricspublisher.HystrixCodaHaleMetricsPublisher;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import com.netflix.hystrix.strategy.HystrixPlugins;
import info.batey.examples.integration.resources.IntegrationResource;
import io.dropwizard.Application;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.commons.configuration.MapConfiguration;
import org.apache.http.client.HttpClient;

public class IntegrationApplication extends Application<AppConfig> {
    @Override
    public void initialize(Bootstrap<AppConfig> appConfigBootstrap) {
        HystrixPlugins.getInstance().registerMetricsPublisher(new HystrixCodaHaleMetricsPublisher(appConfigBootstrap.getMetricRegistry()));
    }

    @Override
    public void run(AppConfig appConfig, Environment environment) throws Exception {
        ConfigurationManager.install(new MapConfiguration(appConfig.getDefaultHystrixConfig()));

        final HttpClient httpClient = new HttpClientBuilder(environment).using(appConfig.getHttpClient()).build("dropwizard-http-client");

        environment.getApplicationContext().addServlet(HystrixMetricsStreamServlet.class, "/hystrix.stream");

        environment.jersey().register(new IntegrationResource(httpClient));
    }

    public static void main(String[] args) throws Exception {
        new IntegrationApplication().run(args);
    }
}
