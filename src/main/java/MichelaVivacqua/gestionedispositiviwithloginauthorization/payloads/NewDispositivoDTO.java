package MichelaVivacqua.gestionedispositiviwithloginauthorization.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewDispositivoDTO(
        @NotEmpty(message = "È obbligatoria la tipologia")
        @Size(min = 2, max = 30, message = "La tipologia deve essere compresa tra i 2 e i 30 caratteri")
        String tipologia,
        @NotNull(message = "Lo stato è obbligatorio")
        String stato,
        Integer dipendenteId
) {
}
