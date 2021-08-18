package classes;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class start {
	public static void main (String[] args) throws IOException, GeneralSecurityException {
		System.out.println("command dlist" );
		String str = System.console().readLine();
		System.out.println("you said "+str);
		if (str.equalsIgnoreCase( "add")) {

		}else if (str.equalsIgnoreCase("dlist")){
			copy.list();
		/*}else if (str.equalsIgnoreCase("dlistall")){
			copy.list();
		}else if (str.equalsIgnoreCase("dlist")){
			copy.list();
		}else if (str.equalsIgnoreCase("dlist")){
			copy.list();
		}else if (str.equalsIgnoreCase("dlist")){
			copy.list();

		*/}else if (str.equalsIgnoreCase("remove")) {

		}


	}

}
