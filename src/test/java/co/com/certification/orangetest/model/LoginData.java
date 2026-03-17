package co.com.certification.orangetest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginData {
    private String username;
    private String password;
    private String message;
}
