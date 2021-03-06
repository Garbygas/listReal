package classes;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class googleUtl {
	private static final String APPLICATION_NAME = "List app";
	private static final String  SPREADSHEET_ID = "1G6Tqf9IZW6jLmvZSbbUPLLF_PCTOOR4vDjRiO2coMpo";
	private static Credential authorize() throws IOException, GeneralSecurityException {
		InputStream in = googleUtl.class.getResourceAsStream("/credentials.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
				JacksonFactory.getDefaultInstance(), new InputStreamReader(in)
		);
		List <String> scopes = Collections.singletonList( SheetsScopes.SPREADSHEETS );

		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				GoogleNetHttpTransport.newTrustedTransport(),JacksonFactory.getDefaultInstance(),
				clientSecrets, scopes)
				.setDataStoreFactory(new FileDataStoreFactory( new java.io.File( "tokens" )))
				.setAccessType("offline")
				.build();
		return new AuthorizationCodeInstalledApp(
				flow, new LocalServerReceiver())
				.authorize("user");


	}
	public static Sheets getSheetsService() throws IOException, GeneralSecurityException {
		Credential credential = authorize();
		return new Sheets.Builder( GoogleNetHttpTransport.newTrustedTransport(),
				JacksonFactory.getDefaultInstance(),credential)
				.setApplicationName(APPLICATION_NAME)
				.build();
	}
	public static ValueRange getData(String range) throws IOException, GeneralSecurityException {
		Sheets sheetsService = getSheetsService( );
		//range = "Sheet1!A1:E3";
		ValueRange response = sheetsService.spreadsheets().values()
				.get(SPREADSHEET_ID, range)
				.execute();
		List<List< Object >> values = response.getValues();
		//Map <String, Integer > test;
		if (values == null || values.isEmpty()) {
			return null;
		}else{
			//System.out.println(values.toArray()[0].toString());
			return response;
		}

	}

}