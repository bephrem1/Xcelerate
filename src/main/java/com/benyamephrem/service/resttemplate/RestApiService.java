package com.benyamephrem.service.resttemplate;

import com.benyamephrem.service.dto.Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

import static com.benyamephrem.util.WebUtils.uriEncode;

public abstract class RestApiService<T extends Dto> {

    //Inject RestTemplate into this variable. RestTemplate configured in AppConfig.
    @Autowired
    private RestTemplate restTemplate;

    //When called in our implementation will return the full request to be executed
    protected RequestBuilder get(String uriTemplate) {
        return new RequestBuilder(uriTemplate);
    }

    public abstract String getHost();
    public abstract String getApiKey();
    public abstract Class<T> getDtoClass();

    public class RequestBuilder {
        private String uriTemplate; //Template to fill in with params we populate the params map with
        private Map<String, String> params = new HashMap<>(); //Map of params we want to append to the final request URL
        private boolean secure = true;

        //Builder constructor
        protected RequestBuilder(String uriTemplate) {
            this.uriTemplate = uriTemplate;
        }

        /* Builder methods to build out our request*/
        public RequestBuilder param(String key, Object value) {
            params.put(key,uriEncode("" + value)); //uriEncode() helper method encodes our value so it is request ready to be put in a URL
            return this;
        }

        public RequestBuilder withHttps() {
            secure = true;
            return this;
        }

        public RequestBuilder withHttp() {
            secure = false;
            return this;
        }

        //Final method in the fetching process. This executes the request with the built URL. JSON response will
        //be parsed into DTO class returned by getDtoClass() using Jackson (we configured that in AppConfig).
        public T execute() {
            UriComponents uriComponents = UriComponentsBuilder.newInstance()
                    .scheme(secure ? "https" : "http")
                    .host(getHost())
                    .path(uriTemplate)
                    .build()
                    .expand(params);
            String url = uriComponents.toUriString();
            System.out.println(url);
            return restTemplate.getForObject(url, getDtoClass());
        }
    }
}