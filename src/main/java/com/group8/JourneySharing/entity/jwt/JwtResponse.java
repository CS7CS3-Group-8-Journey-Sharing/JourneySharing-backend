package com.group8.JourneySharing.entity.jwt;

import com.group8.JourneySharing.vo.UserDetailsVo;

public class JwtResponse {
    private String jwtToken;
    private UserDetailsVo user;

    public JwtResponse(String jwtToken, UserDetailsVo user) {
        this.jwtToken = jwtToken;
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public UserDetailsVo getUser() {
        return user;
    }

    public void setUser(UserDetailsVo user) {
        this.user = user;
    }
}
