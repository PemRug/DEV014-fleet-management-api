package fleetmanagementapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;


@Entity
    @Table(name= "trajectories")  // Mapea esta clase a la tabla "trajectories"
    public class Trajectories {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @ManyToOne
        @JsonBackReference
        @JoinColumn(name = "taxi_id", referencedColumnName = "id", nullable = false)
        private Taxis taxi;
        @Column(name = "date")
        private Date date;
        @Column(name = "latitude")
        private Double latitude;
        @Column(name = "longitude")
        private Double longitude;
        // Getters and setters
        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }

        public Taxis getTaxi() {
            return taxi;
        }

        public void setTaxi(Taxis taxi) {
            this.taxi = taxi;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
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


