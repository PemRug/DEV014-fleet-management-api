package fleetmanagementapi.entity;

import jakarta.persistence.*;
@Entity
@Table (name= "taxis")  // Mapea esta clase a la tabla "taxis"
public class Taxis {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    @Column(name = "plate")
    private String plate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
