package at.htlkaindorf.tankofax.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferInformation {
    private boolean service;
    private boolean selvService;
    private boolean unattended;
}

/*
"offerInformation": {
      "service": false,
      "selfService": true,
      "unattended": true
    }
 */