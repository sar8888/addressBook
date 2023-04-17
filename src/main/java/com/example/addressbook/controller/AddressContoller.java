package com.example.addressbook.controller;

import com.example.addressbook.service.AddressService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet("/address_book/*")
public class AddressContoller extends HttpServlet {

    AddressService addressService = AddressService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String RequestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        String command = RequestURI.substring(contextPath.length());
        log.info("command: " + command);

//        String viewPath = "/WEB-INF/template/address_book/";

        resp.setContentType("text/html; charset=utf-8");
        req.setCharacterEncoding("utf-8");

        switch (command) {
            case "/address_book/list":
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
                break;

            case "/address_book/addForm":
                log.info("addAddress");
                req.getRequestDispatcher("/WEB-INF/template/address_book/addrbook_form.jsp").forward(req, resp);
                break;

            case "/address_book/addAction":
                log.info("addAction");
                try {
                    addressService.addDB(req);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("/address_book/list");
                break;

            case "/address_book/modifyForm":
                log.info("modifyForm");
                req.getRequestDispatcher("/WEB-INF/template/address_book/addrbook_edit_form.jsp").forward(req, resp);
                break;

            case "/address_book/modifyAction":
                log.info("modifyAction");
                try {
                    addressService.modifyForm(req);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;


            case "/address_book/removeAction":
                log.info("removeAction");
                try {
                    if (addressService.removeAction(req)) {
                        resp.sendRedirect("/address_book/list");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
        }


//        if (command.contains("addDB.do")) {
//            req.getRequestDispatcher(viewPath + "addForm.jsp").forward(req, resp);
//        } else if (command.contains("addAction.do")) {
//            log.info("addAction.do");
//            try {
//                addressService.addDB(req);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        } else if (command.contains("list.do")) {
//            log.info("list.do");
//            try {
//                addressService.
//            }
//        }
    }
}
