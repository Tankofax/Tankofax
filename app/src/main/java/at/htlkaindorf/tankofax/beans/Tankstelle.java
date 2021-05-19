package at.htlkaindorf.tankofax.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tankstelle {
    private int id;
    private String name;
    private Location location;
    private Contact contact;
    private OpeningHours openingHours;
    private OfferInformation offerInformation;
    private PaymentMethods paymentMethods;
    private int position;
    private boolean open;
    private double distance;
    private Prices prices;
}

/*
Example
[
  {
    "id": 766271,
    "name": "Rumpold",
    "position": 1,
    "open": true,
    "distance": 2.095049818377507,
  },
 */