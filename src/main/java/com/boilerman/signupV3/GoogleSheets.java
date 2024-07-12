package com.boilerman.signupV3;


import com.boilerman.signupV3.DTO.AgeGroup;
import com.boilerman.signupV3.DTO.Collegiate;
import com.boilerman.signupV3.DTO.Relay;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.*;
import java.time.LocalDate;

public class GoogleSheets {

    public static final String APPLICATION_NAME = "Practice";
    public static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    public static final String TOKENS_DIRECTORY_PATH = "tokens";
    public static final List<String> SCOPES =
            Arrays.asList(SheetsScopes.SPREADSHEETS);

    public static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    private static final String sheet_id = "1XELS5XaBgrHPmQqZu9d-i2YOK6lXXUNryCIgnkWqP8s"; // testing
    //private static final String sheet_id = ""; // need to be manually updated every year or just overwrite the same sheet every year


    /**
     * returns credentials used for google sheets API
     * @param HTTP_TRANSPORT
     * @return
     * @throws IOException
     */
    public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {

        // Load client secrets.
        InputStream in = SignupV3Application.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");


    }

    /**
     * checks if the sheet with the given name exists
     * @param name
     * @return
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public static boolean checkSheetExists(String name) throws GeneralSecurityException, IOException {

        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        // gets sheets
        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, GoogleSheets.JSON_FACTORY, GoogleSheets.getCredentials(HTTP_TRANSPORT))
                .setApplicationName(GoogleSheets.APPLICATION_NAME)
                .build();

        // gets the spread sheet
        Spreadsheet sp = service.spreadsheets().get(sheet_id).execute();

        List<Sheet> sheets = sp.getSheets();

        for (Sheet sheet : sheets) {
            String sheetName = sheet.getProperties().getTitle();
            if (sheetName.equals(name)) {
                return true;
            }
        }

        return false;
    }



    /**
     * creates a sheet within a spreadsheet with the given name
     * @return
     * @throws GeneralSecurityException
     * @throws IOException
     */
    private static Boolean createSheet(String name, ValueRange body) throws GeneralSecurityException, IOException {

        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();


        // assume spread sheet exists

        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, GoogleSheets.JSON_FACTORY, GoogleSheets.getCredentials(HTTP_TRANSPORT))
                .setApplicationName(GoogleSheets.APPLICATION_NAME)
                .build();


        // a request to create a sheet with given title
        Request r = new Request().setAddSheet(new AddSheetRequest().setProperties(new SheetProperties().setTitle(name)));

        try {
            // executes request
            service.spreadsheets().batchUpdate(sheet_id, new BatchUpdateSpreadsheetRequest().setRequests(Arrays.asList(r))).execute();


            service.spreadsheets().values().append( sheet_id, name, body).setValueInputOption("RAW").execute();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    /**
     * creates sheet that stores age group submissions
     * @return
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public static boolean createAgeGroupSheet() throws GeneralSecurityException, IOException {

        String sheetName = LocalDate.now().getYear() + "_AgeGroup";

        return createSheet(sheetName, ageGroupColumns());

    }

    /**
     * gets a row to write to the google sheets with strings that represents the column names for a age group submission
     * order:
     * Lastname | first name | usat number | paymet number | email | city | state | phone number | shirt_size | age | sex
     * @return
     */
    public static ValueRange ageGroupColumns() {

        List<String> col_names = new ArrayList<>();

        col_names.add("Last_Name");
        col_names.add("First_Name");
        col_names.add("USAT_Number");
        col_names.add("Payment_Number");
        col_names.add("Email");
        col_names.add("City");
        col_names.add("State");
        col_names.add("Phone_Number");
        col_names.add("Shirt_Size");
        col_names.add("DOB");
        col_names.add("Sex");

        List<Object> columnList = new ArrayList<>(col_names);
        return new ValueRange().setValues(Arrays.asList(columnList));
    }

    /**
     * creates sheet that stores age group submissions
     * @return
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public static boolean createCollegiateSheet() throws GeneralSecurityException, IOException {

        String sheetName = LocalDate.now().getYear() + "_Collegiate";

        return createSheet(sheetName, collegiateColumns());

    }

    /**
     * gets a row to write to the google sheets with strings that represents the column names for a colegiate submission
     * order:
     * Lastname | first name | usat number | paymet number | college | email | college email | phone number | shirt size | age | sex
     * @return
     */
    public static ValueRange collegiateColumns() {

        List<String> col_names = new ArrayList<>();

        col_names.add("Last_Name");
        col_names.add("First_Name");
        col_names.add("USAT_Number");
        col_names.add("Payment_Number");
        col_names.add("College");
        col_names.add("Email");
        col_names.add("College Email");
        col_names.add("Phone_Number");
        col_names.add("Shirt_Size");
        col_names.add("DOB");
        col_names.add("Sex");

        List<Object> columnList = new ArrayList<>(col_names);
        return new ValueRange().setValues(Arrays.asList(columnList));
    }


    /**
     * creates sheet that stores age group submissions
     * @return
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public static boolean createRelaySheet() throws GeneralSecurityException, IOException {

        String sheetName = LocalDate.now().getYear() + "_Relay";

        return createSheet(sheetName, relayColumns());

    }

    /**
     * gets a row to write to the google sheets with strings that represents the column names for a relay submission
     * order:
     * members | payment number | team name | state | city | phone number |
     * oneFirstName | oneLastName | oneUSATNum | oneEmail | oneSex | oneShirtSize | oneAge |
     * twoFirstName | twoLastName | twoUSATNum | twoEmail | twoSex | twoShirtSize | twoAge |
     * thirdFirstName | thirdastName | thirdUSATNum | thirdEmail | thirdSex | thirdShirtSize | thirdAge |
     * @return
     */
    public static ValueRange relayColumns() {

        List<String> col_names = new ArrayList<>();

        // common info
        col_names.add("Members");
        col_names.add("Payment_Number");
        col_names.add("Team_Name");
        col_names.add("State");
        col_names.add("City");
        col_names.add("Phone_Number");

        // first member
        col_names.add("One_First_Name");
        col_names.add("One_Last_Name");
        col_names.add("One_USAT_Number");
        col_names.add("One_Email");
        col_names.add("One_Sex");
        col_names.add("One_Shirt_Size");
        col_names.add("One_DOB");

        // second member
        col_names.add("Two_First_Name");
        col_names.add("Two_Last_Name");
        col_names.add("Two_USAT_Number");
        col_names.add("Two_Email");
        col_names.add("Two_Sex");
        col_names.add("Two_Shirt_Size");
        col_names.add("Two_DOB");

        // third member
        col_names.add("Third_First_Name");
        col_names.add("Third_Last_Name");
        col_names.add("Third_USAT_Number");
        col_names.add("Third_Email");
        col_names.add("Third_Sex");
        col_names.add("Third_Shirt_Size");
        col_names.add("Third_DOB");

        List<Object> columnList = new ArrayList<>(col_names);
        return new ValueRange().setValues(Arrays.asList(columnList));
    }


    /**
     * posts an age group submission to the age group sheet
     * @param submission
     * @return
     */
    public static Boolean postAgeGroup(AgeGroup submission) {

        try {

            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Sheets service = new Sheets.Builder(HTTP_TRANSPORT, GoogleSheets.JSON_FACTORY, GoogleSheets.getCredentials(HTTP_TRANSPORT))
                    .setApplicationName(GoogleSheets.APPLICATION_NAME)
                    .build();

            List<String> subRow = submission.getValues();

            List<Object>toWrite = new ArrayList<>(subRow);

            ValueRange body = new ValueRange().setValues(Arrays.asList(toWrite));

            String sheetName = LocalDate.now().getYear() + "_AgeGroup";

            service.spreadsheets().values().append( sheet_id, sheetName, body).setValueInputOption("RAW").execute();

        } catch (GeneralSecurityException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    /**
     * posts a relay submission to the relay sheet
     * @param submission
     * @return
     */
    public static Boolean postRelay(Relay submission) {

        try {

            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Sheets service = new Sheets.Builder(HTTP_TRANSPORT, GoogleSheets.JSON_FACTORY, GoogleSheets.getCredentials(HTTP_TRANSPORT))
                    .setApplicationName(GoogleSheets.APPLICATION_NAME)
                    .build();

            List<String> subRow = submission.getValues();

            List<Object>toWrite = new ArrayList<>(subRow);

            ValueRange body = new ValueRange().setValues(Arrays.asList(toWrite));

            String sheetName = LocalDate.now().getYear() + "_Relay";

            service.spreadsheets().values().append( sheet_id, sheetName, body).setValueInputOption("RAW").execute();

        } catch (GeneralSecurityException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

        return true;

    }

    /**
     * posts a submission to the collegiate sheet
     * @param submission
     * @return
     */
    public static Boolean postCollegiate(Collegiate submission) {

        try {

            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Sheets service = new Sheets.Builder(HTTP_TRANSPORT, GoogleSheets.JSON_FACTORY, GoogleSheets.getCredentials(HTTP_TRANSPORT))
                    .setApplicationName(GoogleSheets.APPLICATION_NAME)
                    .build();

            List<String> subRow = submission.getValues();

            List<Object>toWrite = new ArrayList<>(subRow);

            ValueRange body = new ValueRange().setValues(Arrays.asList(toWrite));

            String sheetName = LocalDate.now().getYear() + "_Collegiate";

            service.spreadsheets().values().append( sheet_id, sheetName, body).setValueInputOption("RAW").execute();

        } catch (GeneralSecurityException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

        return true;

    }






}
