package io.zipcoder;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShowResponse {
    public String tmsId;
    public String rootId;
    public String subType;
    public String title;
    public String releaseYear;
    public String releaseDate;
    public String[] genres;
    public String longDescription;
    public String shortDescription;

    public Showtime[] showtimes;

    @Override
    public String toString(){
        return "Value{" +
                "id=" + tmsId
                 + "}";
    }
}
