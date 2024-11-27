package com.javanc.DTO;

public class UserSession {
    private static int userId;

    // Lưu userId vào session
    public static void setUserId(int id) {
        userId = id;
    }

    // Lấy userId từ session
    public static int getUserId() {
        return userId;
    }
}
