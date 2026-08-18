package com.user.login.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.user.login.entity.User;
import com.user.login.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2LoginSuccessHandler
        extends SimpleUrlAuthenticationSuccessHandler {

    private final UserRepository userRepository;

    public OAuth2LoginSuccessHandler(
            UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        OAuth2User oauth2User =
                (OAuth2User) authentication.getPrincipal();

        // Get Google user information
        String email =
                oauth2User.getAttribute("email");

        String googleName =
                oauth2User.getAttribute("name");

        System.out.println("================================");
        System.out.println("Google Login Successful");
        System.out.println("Email : " + email);
        System.out.println("Name  : " + googleName);
        System.out.println("================================");

        // Find existing NanoKart user
        User user = userRepository
                .findByEmail(email)
                .orElse(null);

        if (user == null) {

            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
            );

            response.getWriter().write(
                    "Google account is not registered in NanoKart."
            );

            return;
        }

        // Generate your existing NanoKart JWT
        String token =
                JwtUtil.generateToken(user.getEmail(), user.getId());

        System.out.println("NanoKart JWT generated successfully");

        /*
         * Temporary redirect to React.
         *
         * We will handle this URL in React in the next step.
         */
        String redirectUrl =
                "http://localhost:5173/oauth2/callback"
                + "?token=" + token
                + "&userId=" + user.getId()
                + "&name=" + user.getName()
                + "&email=" + user.getEmail()
                + "&role=" + user.getRole();

        getRedirectStrategy().sendRedirect(
                request,
                response,
                redirectUrl
        );
    }
}