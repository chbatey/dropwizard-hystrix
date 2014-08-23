package info.batey.examples.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Created by chbatey on 01/08/2014.
 */
public class AppConfig extends Configuration {

    @Valid
    @NotNull
    @JsonProperty
    private HttpClientConfiguration httpClient = new HttpClientConfiguration();

    @NotNull
    @JsonProperty
    private Map<String, Object> defaultHystrixConfig;

    public HttpClientConfiguration getHttpClient() {
        return httpClient;
    }

    public Map<String, Object> getDefaultHystrixConfig() {
        return defaultHystrixConfig;
    }
}
