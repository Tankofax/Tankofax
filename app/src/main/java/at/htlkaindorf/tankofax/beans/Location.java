package at.htlkaindorf.tankofax.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    private String address;
    private int postalCode;
    private String city;
    private double latitude;
    private double longitude;

    @Override
    public String toString() {
        return String.format("%s\n%d, %s\nGPS: %f, %f", address, postalCode, city, latitude, longitude);
    }
}

/*
"location": {
      "address": "Kärntner Straße 338",
      "postalCode": "8054",
      "city": "Graz",
      "latitude": 47.029741,
      "longitude": 15.403209
    }
 */