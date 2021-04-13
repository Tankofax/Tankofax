package at.htlkaindorf.tankofax.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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