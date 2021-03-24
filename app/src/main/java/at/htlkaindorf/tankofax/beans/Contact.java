package at.htlkaindorf.tankofax.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    private String telephone;
    private String fax;
    private String mail;
    private String website;
}

/*
"contact": {
      "telephone": "43384738005016",
      "fax": "43384738005043",
      "mail": "tankstellen@rumpold.net",
      "website": "http://rumpold.net"
  }
 */