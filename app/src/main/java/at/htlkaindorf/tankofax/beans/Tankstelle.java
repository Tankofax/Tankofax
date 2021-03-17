package at.htlkaindorf.tankofax.beans;

public class Tankstelle {
    private int id;
    private String name;
    //private Location location;
    //private Contact contact;
    //private OpeningHours openingHours;
    //private OfferInformation offerInformation;
    //private PaymentMethods paymentMethods;
    private int position;
    private boolean open;
    private double distance;
    //private Prices prices;
}

/*
Example
[
  {
    "id": 766271,
    "name": "Rumpold",
    "location": {
      "address": "Kärntner Straße 338",
      "postalCode": "8054",
      "city": "Graz",
      "latitude": 47.029741,
      "longitude": 15.403209
    },
    "contact": {
      "telephone": "43384738005016",
      "fax": "43384738005043",
      "mail": "tankstellen@rumpold.net",
      "website": "http://rumpold.net"
    },
    "openingHours": [
      {
        "day": "MO",
        "label": "Montag",
        "order": 1,
        "from": "06:00",
        "to": "20:00"
      },
      {
        "day": "DI",
        "label": "Dienstag",
        "order": 2,
        "from": "06:00",
        "to": "20:00"
      },
      {
        "day": "MI",
        "label": "Mittwoch",
        "order": 3,
        "from": "06:00",
        "to": "20:00"
      },
      {
        "day": "DO",
        "label": "Donnerstag",
        "order": 4,
        "from": "06:00",
        "to": "20:00"
      },
      {
        "day": "FR",
        "label": "Freitag",
        "order": 5,
        "from": "06:00",
        "to": "20:00"
      },
      {
        "day": "SA",
        "label": "Samstag",
        "order": 6,
        "from": "06:00",
        "to": "20:00"
      },
      {
        "day": "SO",
        "label": "Sonntag",
        "order": 7,
        "from": "07:00",
        "to": "20:00"
      },
      {
        "day": "FE",
        "label": "Feiertag",
        "order": 8,
        "from": "07:00",
        "to": "20:00"
      }
    ],
    "offerInformation": {
      "service": false,
      "selfService": true,
      "unattended": true
    },
    "paymentMethods": {
      "cash": false,
      "debitCard": true,
      "creditCard": true,
      "others": "Stammkundentankschlüssel, Stammkundenkarte IQ"
    },
    "paymentArrangements": {
      "cooperative": false,
      "clubCard": false
    },
    "position": 1,
    "open": true,
    "distance": 2.095049818377507,
    "prices": [
      {
        "fuelType": "DIE",
        "amount": 1.094,
        "label": "Diesel"
      }
    ]
  },
 */