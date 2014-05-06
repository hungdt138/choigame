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
import javax.servlet.http.HttpSession;
import model.bean.Advertisement;
import model.bean.Advertisement1;
import model.bean.Agent;
import model.bean.Game;
import model.bean.GamePackage;
import model.bean.Groupon;
import model.dao.AdvertisingDAO;
import model.dao.GameDAO;
import model.dao.GrouponDAO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Do Tien Hung
 */
public class ConfirmController extends CategorySuper implements Controller {

    private Agent agent;
    private  GameDAO gameDao;
    private GrouponDAO gDAO;

    public ConfirmController() {
        gameDao = new GameDAO();
        gDAO = new GrouponDAO();
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("confirm");


        //get quang cao
        AdvertisingDAO adDAO = new AdvertisingDAO();
        List<Advertisement> ad = adDAO.getAd();
        mv.addObject("list", ad);
        List<Advertisement> ad1 = adDAO.getAd3();
        mv.addObject("list1", ad1);
        Map cfMap = new HashMap();
        String pr = request.getParameter("pr");
        if (pr == null || "".equals(pr)) {
            pr = "0";
        }
        
           // Get Agent
        HttpSession session = request.getSession();
        agent = (Agent) session.getAttribute("agent");
        if(agent == null){
            agent = super.getUserAgent(request);
        }
        String type = "";
        type = request.getParameter("type");

        String chargeUrl = common.Config.getString("chargeurl");
        String url = request.getParameter("url");
        System.out.println("[Redirect Charge!]" + chargeUrl);

        String reqStr = request.getQueryString();
        if (reqStr == null) {
            reqStr = "";
        }

        String furl = chargeUrl + "?" + reqStr;
        //lay id game
        String idPara = request.getParameter("id");
        int id = (idPara != null && !idPara.equals("")) ? Integer.parseInt(idPara) : 0;
        if("game".equalsIgnoreCase(type)){
            Game game = gameDao.getDetail(id);
            cfMap.put("game", game);
        } else if ("g".equalsIgnoreCase(type)){
            Groupon group = gDAO.GetGrouponGameById(agent, id);
            cfMap.put("group", group);
        }

        //lay id package
        String idPackage = request.getParameter("pId");
        int pid = (idPackage != null && !idPackage.equals("")) ? Integer.parseInt(idPackage) : 0;
        GamePackage gamepkg = gameDao.getPackage(pid);
        cfMap.put("gamepkg", gamepkg);
        cfMap.put("status", idPackage);
        String homeUrl = "";
        System.out.println("confirm: " + furl);
        cfMap.put("chargeurl", furl);
        cfMap.put("homeurl", homeUrl);
        //cfMap.put("charge_url", chargeUrl);
        cfMap.put("pr", pr);
        cfMap.put("type", type);
        mv.addObject("lstCat", lstCat);

        mv.addObject("lst", cfMap);
        return mv;
    }
}
