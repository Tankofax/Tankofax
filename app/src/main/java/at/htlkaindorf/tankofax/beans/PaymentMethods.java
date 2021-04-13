package at.htlkaindorf.tankofax.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethods {
    private boolean cash;
    private boolean debitcard;
    private boolean creditCard;
    private String others;
}

/*
"paymentMethods": {
      "cash": false,
      "debitCard": true,
      "creditCard": true,
      "others": "Stammkundentankschlüssel, Stammkundenkarte IQ"
    },
 */