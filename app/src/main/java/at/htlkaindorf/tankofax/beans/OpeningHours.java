package at.htlkaindorf.tankofax.beans;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpeningHours {
    private String day;
    private String label;
    private int oder;
    private LocalTime from;
    private LocalTime to;
}

/*
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
 */