package io.zipcoder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Showtime {
    public Theater theater;
    public String dateTime;
}
