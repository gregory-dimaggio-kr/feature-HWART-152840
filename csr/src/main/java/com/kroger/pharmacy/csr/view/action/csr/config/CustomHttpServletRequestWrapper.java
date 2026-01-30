package com.kroger.pharmacy.csr.view.action.csr.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

public class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final Map<String, String> customHeaders;

    public CustomHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.customHeaders = new HashMap<>();
    }

    public void addHeader(String name, String value) {
        this.customHeaders.put(name, value);
    }

    public void removeHeaders() {
        this.customHeaders.clear();
    }

    @Override
    public String getHeader(String name) {
        String headerValue = customHeaders.get(name);
        if (headerValue != null) {
            return headerValue;
        }
        return super.getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        Set<String> headerNames = new HashSet<>(customHeaders.keySet());
        Enumeration<String> originalHeaderNames = super.getHeaderNames();
        while (originalHeaderNames.hasMoreElements()) {
            headerNames.add(originalHeaderNames.nextElement());
        }
        return Collections.enumeration(headerNames);
    }
}
