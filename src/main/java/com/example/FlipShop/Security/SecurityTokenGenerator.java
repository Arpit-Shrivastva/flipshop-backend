package com.example.FlipShop.Security;

import com.example.FlipShop.Models.User;

public interface SecurityTokenGenerator {
    String createToken(User user);
}
