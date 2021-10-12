/**
 * 
 */
package cl.bci.springtest.handler;

import lombok.Getter;

/**
 * ApiError Formato de excepcion de API
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Getter
public class ApiError {

	private String mensaje;
	

	private ApiError() {
	}
	
	public ApiError(String mensaje){
		this();
		this.mensaje = mensaje;
	}

}