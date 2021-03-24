package at.htlkaindorf.tankofax.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private String address;
    private int postalCode;
    private String city;
    private double latitude;
    private double longtitude;
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