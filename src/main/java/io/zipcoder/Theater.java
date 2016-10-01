package io.zipcoder;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Theater {
    public String id;
    public String name;
}
