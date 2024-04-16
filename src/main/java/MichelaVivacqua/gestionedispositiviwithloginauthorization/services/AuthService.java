package MichelaVivacqua.gestionedispositiviwithloginauthorization.services;

import MichelaVivacqua.gestionedispositiviwithloginauthorization.entities.Dipendente;
import MichelaVivacqua.gestionedispositiviwithloginauthorization.exceptions.UnauthorizedException;
import MichelaVivacqua.gestionedispositiviwithloginauthorization.payloads.DipendenteLoginDTO;
import MichelaVivacqua.gestionedispositiviwithloginauthorization.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private DipendentiService dipendentiService;
    @Autowired
    private JWTTools jwtTools;

    public String authenticateUserAndGenerateToken(DipendenteLoginDTO payload){
        // 1. Controllo le credenziali
        // 1.1 Cerco nel db tramite l'email l'utente
        Dipendente dipendente = this.dipendentiService.findByEmail(payload.email());
        // 1.2 Verifico se la password combacia con quella ricevuta nel payload
        if(dipendente.getPassword().equals(payload.password())) {
            // 2. Se è tutto OK, genero un token e lo torno
            return jwtTools.createToken(dipendente);
        } else {
            // 3. Se le credenziali invece non fossero OK --> 401 (Unauthorized)
            throw new UnauthorizedException("Credenziali non valide! Effettua di nuovo il login!");
        }


    }
}
