package MichelaVivacqua.gestionedispositiviwithloginauthorization.exceptions;


public class NotFoundException extends RuntimeException {
    public NotFoundException(int id){
        super("Elemento con id " + id + " non trovato!");
    }
    public NotFoundException(String message){ super(message);}
}