package at.htlkaindorf.tankofax.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
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