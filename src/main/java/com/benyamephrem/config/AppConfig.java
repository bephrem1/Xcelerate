package com.benyamephrem.config;

import com.benyamephrem.util.TimestampDeserializer;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@PropertySource("api.properties")
public class AppConfig extends WebMvcConfigurerAdapter{

    @Value("${api.timeout}")
    private int timeout;

    /* Beans to load on application startup */

    //To resolve ${} in @Value annotations
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate rt = defaultRestTemplate();

        //Setting the RestTemplate ClientHttpRequestFactory object now configured with timeout values
        rt.setRequestFactory(clientHttpRequestFactory());

        return rt;
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(timeout); //Set timeout values for read
        factory.setConnectTimeout(timeout); //Set Timeout value for connection to timeout
        return factory;
    }


    public static RestTemplate defaultRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper jacksonObjectMapper = new ObjectMapper(); //New Jackson Object Mapper

        SimpleModule timestampModule = new SimpleModule("TimestampModule",
                new Version(1,0,0,null,null,null))
                .addDeserializer(Instant.class, new TimestampDeserializer());

        jacksonObjectMapper.registerModule(timestampModule);

        //Ensure application doesn't throw JsonMappingException when an unknown property is attempted to be mapped
        jacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

        //Mapper that reads and writes JSON using Jackson's ObjectMapper. Basically parsing HTTP responses.
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setObjectMapper(jacksonObjectMapper);

        //Set rest template's http message converter to the MappingJackson2HttpMessageConverter we created
        restTemplate.setMessageConverters(Arrays.asList(jsonMessageConverter));

        return restTemplate;
    }

}
