package fleetmanagementapi.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

    @Entity
    @Table(name= "trajectories")  // Mapea esta clase a la tabla "trajectories"
    public class Trajectories {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "taxi_id")
        private Integer taxiId;

        @Column(name = "date")
        private Timestamp date;

        @Column(name = "latitude")
        private Double latitude;

        @Column(name = "longitude")
        private Double longitude;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getTaxiId() {
            return taxiId;
        }

        public void setTaxiId(Integer taxiId) {
            this.taxiId = taxiId;
        }
        public Timestamp getDate() {
            return date;
        }
        public  void setDate(Timestamp date) {
            this.date = date;
        }
        public Double getLatitude() {
            return latitude;
        }
        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }
        public Double getLongitude() {
            return longitude;
        }
        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }
    }


