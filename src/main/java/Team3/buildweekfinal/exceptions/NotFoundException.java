package Team3.buildweekfinal.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException{
    public NotFoundException(UUID id){
        super("elemento id"+id+" non trovato");
    }
    public NotFoundException(String message){
        super(message);
    }
}
