package com.x.weather.adapters.api;

import com.x.repository.RequestsStorePort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
@WebFilter(filterName = "WeatherEndpointRequestFilter", urlPatterns = "/api/v1/weather/*")
public class WeatherEndpointRequestFilter  extends OncePerRequestFilter {

        private final static Logger logger = LoggerFactory.getLogger(WeatherEndpointRequestFilter.class);

       private final RequestsStorePort requestsStorePort;
       public WeatherEndpointRequestFilter(RequestsStorePort requestsStorePort){
           this.requestsStorePort=requestsStorePort;
       }

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                        FilterChain filterChain) throws ServletException, IOException {
            requestsStorePort.write(Instant.now(), request.getMethod(),request.getRemoteAddr(),request.getPathInfo());
            logger.info(String.format("REQUEST DATA: %s", request.getRemoteAddr() ));
            filterChain.doFilter(request, response);
        }
}
