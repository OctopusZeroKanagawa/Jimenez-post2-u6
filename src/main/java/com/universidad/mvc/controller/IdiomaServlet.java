package com.universidad.mvc.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Locale;

@WebServlet("/idioma")
public class IdiomaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        String lang =
                request.getParameter("lang");

        if (lang != null &&
                (lang.equals("es")
                        || lang.equals("en"))) {

            request.getSession(true)
                    .setAttribute(
                            "locale",
                            new Locale(lang)
                    );
        }

        String referer =
                request.getHeader("Referer");

        response.sendRedirect(
                referer != null
                        ? referer
                        : request.getContextPath()
                          + "/productos"
        );
    }
}