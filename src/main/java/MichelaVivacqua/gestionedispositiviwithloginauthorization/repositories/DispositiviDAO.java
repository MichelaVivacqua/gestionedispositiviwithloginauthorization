package MichelaVivacqua.gestionedispositiviwithloginauthorization.repositories;

import MichelaVivacqua.gestionedispositiviwithloginauthorization.entities.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DispositiviDAO extends JpaRepository<Dispositivo, Integer> {
    boolean existsByTipologia(String tipologia);
    Optional<Dispositivo> findBytipologia(String tipologia);

}
