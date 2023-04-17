package com.example.addressbook.service;

import com.example.addressbook.DAO.AddressDAO;
import com.example.addressbook.DTO.AddressDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class AddressService {

    static final int LISTCOUNT = 5;

    private static AddressService instance;

    private AddressService() {

    }

    public static AddressService getInstance() {
        if (instance == null) {
            instance = new AddressService();
        }
        return instance;
    }

    public boolean addDB(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        log.info("addDB...");

        AddressDAO dao = AddressDAO.getInstance();

        AddressDTO addr = new AddressDTO();

        addr.setAb_name(request.getParameter("ab_name"));
        addr.setAb_email(request.getParameter("ab_email"));
        addr.setAb_comdept(request.getParameter("ab_comdept"));
        addr.setAb_birth(request.getParameter("ab_birth"));
        addr.setAb_tel(request.getParameter("ab_tel"));
        addr.setAb_memo(request.getParameter("ab_memo"));

        return dao.insertDB(addr);
    }

    public List<AddressDTO> getList() throws SQLException, ClassNotFoundException {
        log.info("getList()...");

        AddressDAO dao = AddressDAO.getInstance();
        List<AddressDTO> addressList = dao.getList();

        return addressList;
    }

    public void getOne(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        log.info("getOne()...");
        AddressDAO dao = AddressDAO.getInstance();

        int ab_id = Integer.parseInt(request.getParameter("ab_id"));

        AddressDTO address = dao.getAddressOne(ab_id);

        request.setAttribute("ab_id", ab_id);
        request.setAttribute("address", address);
    }

    public boolean modifyForm(HttpServletRequest request) throws Exception {
        log.info("modifyForm...");
        AddressDAO dao = AddressDAO.getInstance();
        AddressDTO address = new AddressDTO();

        HttpSession session = request.getSession();
        address.setAb_id((int) session.getAttribute("sessionMemberId"));

        return dao.modifyAddress(address);
    }

    public boolean removeAction(HttpServletRequest request) throws SQLException, ClassNotFoundException{
        log.info("removeAction()...");
        AddressDAO addressDAO = AddressDAO.getInstance();

        int ad_id = Integer.parseInt(request.getParameter("ad_id"));
        return addressDAO.removeAction(ad_id);
    }

}
