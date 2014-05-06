/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.bean.Advertisement;
import model.bean.Agent;
import model.bean.Groupon;
import model.dao.AdvertisingDAO;
import model.dao.GameDAO;
import model.dao.GrouponDAO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author noname
 */
public class GrouponReturnController extends CategorySuper implements Controller {
     private Agent agent;
    private  GameDAO gameDao;
    private GrouponDAO gDAO;
    
    public GrouponReturnController() {
         gameDao = new GameDAO();
        gDAO = new GrouponDAO();
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();
          // Get Agent
        HttpSession session = request.getSession();
        agent = (Agent) session.getAttribute("agent");
        if(agent == null){
            agent = super.getUserAgent(request);
        }
         AdvertisingDAO adDAO = new AdvertisingDAO();
        List<Advertisement> ad = adDAO.getAd();
        mv.addObject("list", ad);
        List<Advertisement> ad1 = adDAO.getAd3();
        mv.addObject("list1", ad1);
        
        String act = request.getParameter("act");
        String idtemp = request.getParameter("id");
        String msisdn = request.getParameter("msisdn");
        
        int id = (idtemp != null && !"".equalsIgnoreCase(idtemp)) ? Integer.parseInt(idtemp) : 0;
        Groupon group = gDAO.GetGrouponGameById(agent, id);
        
        mv.addObject("act", act);
        mv.addObject("id", id);
        mv.addObject("msisdn", msisdn);
        mv.addObject("gr", group);
        mv.addObject("lstcat", lstCat);
        return mv;
    }
    
    
}
