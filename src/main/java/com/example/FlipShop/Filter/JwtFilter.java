package com.example.FlipShop.Filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JwtFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ServletOutputStream pw = response.getOutputStream();
        String authHeader = request.getHeader("Authorization");
        if (request.getMethod().equals("OPTIONS")) {
            //if the method is options the request can pass through and no validation of token is required
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
        } else if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            pw.println("Missing or Invalid Token");
        } else {
            //extract token from the header
            String token = authHeader.substring(7);
            //extract the claims
            Claims claims = Jwts.parser().setSigningKey("MYSCRET").parseClaimsJws(token).getBody();
            // set the claims in the request attribute and pass it to the next handler
            request.setAttribute("claims", claims);
            //pass the claims in the request, anyone wanting to use it
            filterChain.doFilter(request, response);
        }
    }
}
