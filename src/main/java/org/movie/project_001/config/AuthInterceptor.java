package org.movie.project_001.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Allow public pages without authentication
        String uri = request.getRequestURI();
        System.out.println(uri);
        if (uri.endsWith("/login") || uri.endsWith("/error")) {
            return true;
        }

        // Check if user session exists
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login"); // Redirect to login page if not authenticated
            return false;
        }

        return true;
    }

}
