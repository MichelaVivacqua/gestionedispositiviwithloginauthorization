package MichelaVivacqua.gestionedispositiviwithloginauthorization.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String propic;
    private String password;
    @JsonIgnore
    @OneToMany(mappedBy = "dipendente")
    private List<Dispositivo> dispositivi;

    public Dipendente(String username, String name, String surname, String email, String propic, String password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.propic = propic;
        this.password=password;
    }
}
