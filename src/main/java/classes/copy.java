package classes;

import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.lang.reflect.Array;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class copy {
	// TODO:  list items
	public static void list () throws IOException, GeneralSecurityException {
		ValueRange rawList = googleUtl.getData("Sheet1!A2:C100");

		if ( rawList != null ) {
			System.out.println("Item ile count");
			for (int r = 0; r < rawList.getValues().toArray().length; r++) {

				if (rawList.getValues().toArray()[r] == null) System.out.println("no");
				System.out.println(rawList.getValues().toArray()[r].getClass().toString());
				ArrayList test = ( ArrayList ) rawList.getValues().toArray()[r];
				System.out.println(test.toArray()[2]);
				//String[] testt= test.toArray();
			}
			//System.out.println( rawList.getValues().toArray().length);
		}
	}
	// TODO:  add items
	// TODO:  remove items
	// TODO:  list items by ial
	// TODO:  complete items
}
