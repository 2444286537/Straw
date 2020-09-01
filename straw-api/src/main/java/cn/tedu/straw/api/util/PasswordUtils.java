package cn.tedu.straw.api.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtils {
    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public static String encode(String rawPassword){
        return "{bcrypt}"+passwordEncoder.encode(rawPassword);
    }
}
