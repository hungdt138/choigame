/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Advertisement;
import model.bean.Advertisement1;
import model.bean.Agent;
import model.bean.Game;
import model.dao.AdvertisingDAO;
import model.dao.GameDAO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Do Tien Hung
 */
public class MobilesupportController extends CategorySuper implements Controller {
    private Agent agent;
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("mobilesupport");
    
      
        Map msMap = new HashMap();
        String dataid = request.getParameter("id");
        int id = (dataid != null && !"".equals(dataid)) ? Integer.parseInt(dataid) : 0;
        GameDAO gameDao = new GameDAO();
        Game game = gameDao.getDetail(id);
        msMap.put("game", game);

        Map<String, List<String>> mobile = gameDao.getMobileSupport(id);
        Set<String> keySet = mobile.keySet();
        Iterator<String> iterator = keySet.iterator();
        List<String> temps = new ArrayList();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (mobile.get(next).isEmpty()) {
                temps.add(next);
            }
        }

        for (String temp : temps) { // for Threading 
            mobile.remove(temp);
        }
        boolean check = false;
        if(mobile.isEmpty() == true)
        {
            check = false;
            msMap.put("check", check);
        } else
        {
            check = true;
            msMap.put("check", check);
            msMap.put("mobile", mobile);
        }
              //get quang cao
        AdvertisingDAO adDAO = new AdvertisingDAO();
        List<Advertisement> ad = adDAO.getAd();
        mv.addObject("list", ad);
        List<Advertisement> ad1 = adDAO.getAd3();
        mv.addObject("list1", ad1);
        mv.addObject("lstCat", lstCat);
        mv.addObject("lst", msMap);

        return mv;
    }
}
