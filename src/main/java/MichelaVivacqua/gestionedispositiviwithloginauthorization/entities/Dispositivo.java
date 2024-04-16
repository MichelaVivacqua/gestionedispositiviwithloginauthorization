package MichelaVivacqua.gestionedispositiviwithloginauthorization.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tipologia;
//    @Enumerated(EnumType.STRING)
    private String stato;
    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

    public Dispositivo(String tipologia, String stato) {
        this.tipologia = tipologia;
        this.stato = stato;
    }
}
