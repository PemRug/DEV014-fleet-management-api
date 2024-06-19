package fleetmanagementapi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class TrajectoriesDto {
    private Integer id;
    private TaxisDto taxi;
    private String plate;
    private Date date;
    private Double latitude;
    private Double longitude;

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TaxisDto getTaxi() {
        return taxi;
    }

    public void setTaxi(TaxisDto taxi) {
        this.taxi = taxi;
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

    public String getPlate() { return plate;}

    public void setPlate(String plate) {this.plate = plate; }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

