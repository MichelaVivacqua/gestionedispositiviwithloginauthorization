package MichelaVivacqua.gestionedispositiviwithloginauthorization.payloads;

import java.time.LocalDateTime;

public record ErrorsResponseDTO(String message, LocalDateTime timestamp) {
}
