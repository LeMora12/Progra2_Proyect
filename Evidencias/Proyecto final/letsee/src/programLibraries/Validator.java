package programLibraries;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *Valida si esta bien escrito el correo
 * 
 *@author clcardenas@unah.hn
 *@version 0.0.1
 *@date 1/05/24
 *@since 1/05/24 
 */

public class Validator {

	 private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	 private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
	
	 /*
		 *Verifica si la cadena dada es un correo valido.
		 * 
		 *@author clcardenas@unah.hn
		 *@version 0.0.1
		 *@date 1/05/24
		 *@since 1/05/24 
		 *@param email es la cadena que se va a verificar.
		 *@return true si la cadena es un correo electrónico válido o false si no es un correo electronico valido.
		 */
	 
	public static boolean isEmail(String email) {
		
		Matcher matcher = EMAIL_PATTERN.matcher(email);
        
		return matcher.find();
	}
}