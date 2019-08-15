package com.example.eyepetizer.bean;

import java.util.Map;

public class HttpBean {
    private String url;
    private String method;
    private Map<String, String> map;

    public HttpBean(String url, String method, Map<String, String> map) {
        this.url = url;
        this.method = method;
        this.map = map;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public String getMethod() {
        return method;
    }
        public static class Builder{
        private String url;
        private String method;
        private Map<String,String> map;

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setMethod(String method){
            this.method = method;
            return this;
        }
        public Builder setMap(Map<String,String> map){
            this.map = map;
            return this;
        }
        public HttpBean build(){
            return new HttpBean(url,method,map);
        }
    }
}
