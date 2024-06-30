package fleetmanagementapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name= "taxis")  // Mapea esta clase a la tabla "taxis"
public class Taxis {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "plate")
    private String plate;
    @OneToMany(mappedBy = "taxi", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Trajectories> trajectories;


    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public List<Trajectories> getTrajectories() {
        return trajectories;
    }

    public void setTrajectories(List<Trajectories> trajectories) {
        this.trajectories = trajectories;
    }
}
