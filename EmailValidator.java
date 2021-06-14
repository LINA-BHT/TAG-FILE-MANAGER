/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;
import java.util.regex.*;


/**
 *
 * @author linah
 */
public class EmailValidator {
     private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator()
    {
        pattern = Pattern.compile(EMAIL_REGEX);
    }

    /**
     * Validate hex with regular expression
     *
     * @param email email for validation
     * @return true valid email, false invalid email
     */
    public boolean validate(final String email)
    {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
}
