package pck.tfip.ssfrevision01.ssfrevision01.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserInfoModel {
    
    @NotEmpty(message = "Please input a username!")
    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")
    private String username;

    @Pattern(regexp = "[Mm|Ff]{1}", message = "Invalid Gender")
    private String gender;

    // Explanation:
    // ^ Asserts the start of the line
    // [89] Matches either 8 or 9
    // \\d{7} Matches exactly 7 digits (0-9)
    // $ Asserts the end of the line
    @Pattern(regexp = "^[89]\\d{7}$")
    private String telephone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
}
