/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Advertisement;
import model.bean.Advertisement1;
import model.dao.AdvertisingDAO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Do Tien Hung
 */
public class SendresultController extends CategorySuper implements Controller {
    
    public SendresultController() {
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("sendresult");
            //get quang cao
        AdvertisingDAO adDAO = new AdvertisingDAO();
        List<Advertisement> ad = adDAO.getAd();
        mv.addObject("list", ad);
        List<Advertisement> ad1 = adDAO.getAd3();
        mv.addObject("list1", ad1);
        String pn = request.getParameter("pn");
        String st = request.getParameter("status");
        
        Map model = new HashMap();
        
        model.put("pn", pn);
        model.put("status", st);
        
        mv.addObject("lstcat", lstCat);
        mv.addObject("lst", model);
        return mv;
    }
    
   
}
