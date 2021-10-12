package cl.bci.springtest.handler;

/**
 * Excepcion para manejar que el Usuario no fue encontrado en la base de datos
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
public class MailFoundException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

    public static MailFoundException createWith(String username) {
        return new MailFoundException(username);
    }

    public MailFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
