package com.avizhen.security;

import com.avizhen.entity.Advert;
import com.avizhen.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 22.11.2016.
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new ArrayList<Advert>());
        } else {
            ((List) session.getAttribute("cart")).clear();
        }
        User authUser =  (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("username", authUser.getUserName());
        session.setAttribute("authorities", authentication.getAuthorities());
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        httpServletResponse.sendRedirect("/");
    }
}
