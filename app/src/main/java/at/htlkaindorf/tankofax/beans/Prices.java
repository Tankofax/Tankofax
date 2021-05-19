package at.htlkaindorf.tankofax.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Prices {
    private String fuelType;
    private double amount;
    private String label;
}

/*
"prices": [
      {
        "fuelType": "DIE",
        "amount": 1.094,
        "label": "Diesel"
      }
    ]
*/