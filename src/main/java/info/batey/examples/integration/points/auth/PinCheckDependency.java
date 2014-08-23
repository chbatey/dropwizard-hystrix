package info.batey.examples.integration.points.auth;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

public class PinCheckDependency extends HystrixCommand<Boolean> {

    private HttpClient httpClient;

    public PinCheckDependency(HttpClient httpClient) {
        super(HystrixCommandGroupKey.Factory.asKey("PinCheckService"));
        this.httpClient = httpClient;
    }

    @Override
    protected Boolean run() throws Exception {
        HttpGet pinCheck = new HttpGet("http://localhost:9090/pincheck");
        HttpResponse pinCheckResponse = httpClient.execute(pinCheck);
        int statusCode = pinCheckResponse.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            throw new RuntimeException("Oh dear no device information, status code " + statusCode);
        }
        String pinCheckInfo = EntityUtils.toString(pinCheckResponse.getEntity());
        return Boolean.valueOf(pinCheckInfo);
    }

    @Override
    public Boolean getFallback() {
        return true;
    }
}
