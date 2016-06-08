package com.github.yingzhuo.springboot.side.restsec.core;

import com.github.yingzhuo.springboot.side.restsec.annotation.Logical;
import com.github.yingzhuo.springboot.side.restsec.exception.AuthenticationException;
import com.github.yingzhuo.springboot.side.restsec.exception.AuthorizationException;
import com.github.yingzhuo.springboot.side.restsec.exception.UserDetailsExpiredException;
import com.github.yingzhuo.springboot.side.restsec.exception.UserDetailsLockedException;

public class AssertUtils {

    private AssertUtils() {
        super();
    }

    private static UserLike validate(String message) {
        UserLike userDetails = RestsecHolder.getInstance().getUserLike();

        if (userDetails == null) {
            throw new AuthenticationException(message);
        }

        if (userDetails.isLocked()) {
            throw new UserDetailsLockedException(message);
        }

        if (userDetails.isExpired()) {
            throw new UserDetailsExpiredException(message);
        }

        return userDetails;
    }

    public static void assertAuthentication() {
        assertAuthentication(null);
    }

    public static void assertAuthentication(String message) {
        validate(message);
    }

    public static void assertGuest() {
        assertGuest(null);
    }

    public static void assertGuest(String message) {
        UserLike userDetails = RestsecHolder.getInstance().getUserLike();
        if (userDetails != null) {
            throw new AuthenticationException(message);
        }
    }

    public static void assertRoles(Iterable<String> roles) {
        assertRoles(roles, true);
    }

    public static void assertRoles(Iterable<String> roles, boolean all) {
        assertRoles(roles, Logical.AND, null);
    }

    public static void assertRoles(Iterable<String> roles, Logical logical, String message) {
        UserLike userLike = validate(message);

        if (logical == Logical.AND) {
            for (String r : roles) {
                if (!contains(userLike.getRoles(), r)) {
                    throw new AuthorizationException(message);
                }
            }
        } else {
            for (String role : roles) {
                if (contains(userLike.getRoles(), role)) {
                    break;
                }
            }
            throw new AuthorizationException(message);
        }
    }

    public static void assertPermissions(Iterable<String> permissions) {
        assertPermissions(permissions, true);
    }

    public static void assertPermissions(Iterable<String> permissions, boolean all) {
        assertPermissions(permissions, Logical.AND, null);
    }

    public static void assertPermissions(Iterable<String> permissions, Logical logical, String message) {
        UserLike userLike = validate(message);

        if (logical == Logical.AND) {
            for (String p : permissions) {
                if (!contains(userLike.getPermissions(), p)) {
                    throw new AuthorizationException(message);
                }
            }
        } else {
            for (String p : permissions) {
                if (contains(userLike.getPermissions(), p)) {
                    break;
                }
            }
            throw new AuthorizationException(message);
        }
    }

    private static boolean contains(Iterable<String> iterable, String content) {
        for (String s : iterable) {
            if (s.equals(content)) return true;
        }
        return false;
    }
}
