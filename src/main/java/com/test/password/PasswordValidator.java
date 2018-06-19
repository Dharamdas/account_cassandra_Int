package com.test.password;
import org.springframework.util.StringUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    public static final String SYMBOLS_NOT_TO_USE=".*[<>]+.*";
    public static final String UPPERCASE_VALIDATE="(^$)|.*[A-Z]+.*";
    public static final String DIGIT_VALIDATE=".*[\\d]+.*";

    @Override
    public void initialize(Password password) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        int passwordValidCount=0;

        if(StringUtils.isEmpty(password))
            return true;
        if(password.matches(SYMBOLS_NOT_TO_USE))
            return false;
        if(password.matches(UPPERCASE_VALIDATE))
            passwordValidCount++;
        if(password.matches(DIGIT_VALIDATE))
            passwordValidCount++;
        if(passwordValidCount<1)
            return false;

        return true;
    }
}
