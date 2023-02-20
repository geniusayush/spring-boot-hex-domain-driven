package com.springdemo.adapters.configuration;

import com.springdemo.Server;
import com.springdemo.adapters.event.CustomerEventPublisherAdapter;
import com.springdemo.adapters.repository.jpa.CustomerJPARepository;
import com.springdemo.adapters.repository.CustomerPersistenceMapper;
import com.springdemo.adapters.repository.jpa.SpringCustomerJPARepository;
import com.springdemo.application.service.CustomerServiceImpl;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;

@Configuration
@ComponentScan(basePackageClasses = Server.class)
public class BeanConfig {
    @Bean
    CustomerServiceImpl customerService(CustomerJPARepository repository) {
        return new CustomerServiceImpl(repository);
    }



    @Bean
    public CustomerEventPublisherAdapter customerEventPublisherAdapter(ApplicationEventPublisher applicationEventPublisher) {
        return new CustomerEventPublisherAdapter(applicationEventPublisher);
    }

    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "restHttpsTemplate")
    public RestTemplate restHttpsTemplate()
        throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
            .loadTrustMaterial(null, acceptingTrustStrategy)
            .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient httpClient = HttpClients.custom()
            .setSSLSocketFactory(csf)
            .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
            new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        return new RestTemplate(requestFactory);
    }
}
