package at.htlkaindorf.tankofax.bl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Map_Search {
    private String formattedAddress;
    private double lat;
    private double lng;

    @Override
    public String toString() {
        return String.format("%s: %lf;%lf", formattedAddress, lat, lng);
    }
}