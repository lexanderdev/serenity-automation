package co.com.certification.orangetest.config;

import co.com.certification.orangetest.model.EmployeeData;
import co.com.certification.orangetest.model.LoginData;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class FirebaseClient {

    private static FirebaseClient instance;
    private DatabaseReference testDataRef;

    private FirebaseClient() {
        initialize();
    }

    public static synchronized FirebaseClient getInstance() {
        if (instance == null) {
            instance = new FirebaseClient();
        }
        return instance;
    }

    private void initialize() {
        try {
            if (FirebaseApp.getApps().isEmpty()) {
                String projectId   = System.getenv("FIREBASE_PROJECT_ID");
                String privateKey  = System.getenv("FIREBASE_PRIVATE_KEY").replace("\\n", "\n");
                String clientEmail = System.getenv("FIREBASE_CLIENT_EMAIL");
                String databaseUrl = System.getenv("FIREBASE_DATABASE_URL");

                String serviceAccountJson = String.format(
                    "{\"type\":\"service_account\",\"project_id\":\"%s\",\"private_key_id\":\"key\"," +
                    "\"private_key\":\"%s\",\"client_email\":\"%s\",\"client_id\":\"\"," +
                    "\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\"," +
                    "\"token_uri\":\"https://oauth2.googleapis.com/token\"}",
                    projectId, privateKey.replace("\n", "\\n"), clientEmail
                );

                GoogleCredentials credentials = GoogleCredentials
                    .fromStream(new ByteArrayInputStream(serviceAccountJson.getBytes(StandardCharsets.UTF_8)))
                    .createScoped("https://www.googleapis.com/auth/firebase");

                FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .setDatabaseUrl(databaseUrl)
                    .build();

                FirebaseApp.initializeApp(options);
            }

            testDataRef = FirebaseDatabase.getInstance().getReference("test-data");

        } catch (IOException e) {
            throw new RuntimeException("Error al inicializar Firebase", e);
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> readData(String path) {
        CountDownLatch latch = new CountDownLatch(1);
        Object[] result = {null};

        testDataRef.child(path).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                result[0] = snapshot.getValue();
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                latch.countDown();
            }
        });

        try {
            latch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return (Map<String, Object>) result[0];
    }

    public LoginData getValidLoginCredentials() {
        Map<String, Object> data = readData("login/valid");
        LoginData loginData = new LoginData();
        loginData.setUsername((String) data.get("username"));
        loginData.setPassword((String) data.get("password"));
        return loginData;
    }

    public EmployeeData getEmployeeAddData() {
        Map<String, Object> data = readData("employees/add");
        EmployeeData employeeData = new EmployeeData();
        employeeData.setFirstName((String) data.get("firstName"));
        employeeData.setLastName((String) data.get("lastName"));
        return employeeData;
    }

    public String getEmployeeSearchName() {
        Map<String, Object> data = readData("employees/search");
        return (String) data.get("name");
    }

    @SuppressWarnings("unchecked")
    public List<LoginData> getInvalidLoginCredentials() {
        CountDownLatch latch = new CountDownLatch(1);
        Object[] result = {null};

        testDataRef.child("login/invalid").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                result[0] = snapshot.getValue();
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                latch.countDown();
            }
        });

        try {
            latch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        List<LoginData> invalidCredentials = new ArrayList<>();
        List<Object> items = (List<Object>) result[0];
        for (Object item : items) {
            Map<String, Object> entry = (Map<String, Object>) item;
            LoginData loginData = new LoginData();
            loginData.setUsername((String) entry.get("username"));
            loginData.setPassword((String) entry.get("password"));
            loginData.setMessage((String) entry.get("message"));
            invalidCredentials.add(loginData);
        }
        return invalidCredentials;
    }
}
