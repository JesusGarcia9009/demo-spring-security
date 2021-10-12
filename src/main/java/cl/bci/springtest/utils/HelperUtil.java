/**
 * 
 */
package cl.bci.springtest.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.extern.slf4j.Slf4j;
import static cl.bci.springtest.utils.ConstantUtil.*;

/**
 * HelperUtil - helper class
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Slf4j
public class HelperUtil {

	private HelperUtil() {
	}

	public static List<GrantedAuthority> getAuthList() {
		log.info("[getAuthList] :: method star");
		List<String> listadoPermisos = new ArrayList<>();
		listadoPermisos.add(AUTHORITY_ADMIN);
		List<GrantedAuthority> grantList = listadoPermisos.stream().map(o -> new SimpleGrantedAuthority(o.toString())).collect(Collectors.toList());
		log.info("[getAuthList] :: method end");
		return grantList;
	}

	
}
