package com.github.yingzhuo.springboot.side.encoder;


import java.security.SecureRandom;
import java.util.regex.Pattern;

public class BCryptEncoder implements Encoder {

    public static final Encoder INSTANCE = new BCryptEncoder();

    private Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

    private final int strength;

    private final SecureRandom random;

    public BCryptEncoder() {
        this(-1);
    }

    public BCryptEncoder(int strength) {
        this(strength, null);
    }

    public BCryptEncoder(int strength, SecureRandom random) {
        this.strength = strength;
        this.random = random;
    }

    @Override
    public String encode(CharSequence rawText) {
        String salt;
        if (strength > 0) {
            if (random != null) {
                salt = BCrypt.gensalt(strength, random);
            } else {
                salt = BCrypt.gensalt(strength);
            }
        } else {
            salt = BCrypt.gensalt();
        }
        return BCrypt.hashpw(rawText.toString(), salt);
    }

    public int getStrength() {
        return strength;
    }

    public SecureRandom getRandom() {
        return random;
    }
}
