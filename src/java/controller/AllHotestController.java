/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Config;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.bean.Advertisement;
import model.bean.Advertisement1;
import model.bean.Agent;
import model.bean.Game;
import model.dao.AdvertisingDAO;
import model.dao.AgentDAO;
import model.dao.GameDAO;
import model.dao.PageCounter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Nguyen Dinh Doan
 */
public class AllHotestController extends CategorySuper implements Controller {

    private Game game;
    private GameDAO gameDAO;
    private Agent agent;

    /**
     * Default Constructor with initialize an GameDAO 
     */
    public AllHotestController() {
        gameDAO = new GameDAO();
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
           // Get Agent
        HttpSession session = request.getSession();
        agent = (Agent) session.getAttribute("agent");
        if(agent == null){
            agent = super.getUserAgent(request);
        }
       //Kiem tra dai IP cho phep
        String ip = agent.getIp();
        if("".equalsIgnoreCase(ip) && ip == null){
            ip = request.getRemoteAddr();
        }
            String[] IPload = common.Config.getString("IP").split(",");
        int temp_false = 0;
        int length = IPload.length;
        for(String ip1 : IPload){
            if(!ip.startsWith(ip1)){
                temp_false += 1;
            }
        }
        if(temp_false == length){
                System.out.println("[CHOIGAME] Redirect thanh cong!");
                response.sendRedirect("http://mobix.vn");
                return null;
        } 
        int page = 1;
        String pagePara = request.getParameter("page");
        if (pagePara != null) {
            page = Integer.parseInt(pagePara);
        }

       

        // Split page: 1 2 3 4 5...
        int num = Integer.parseInt(Config.getString("splitNum"));
        int count = new PageCounter().getPageCountHottest(agent, num);
        boolean isnext = false, isback = false;
        if(page < count){
            isnext = true;
        }
        if(page > 1){
            isback = true;
        }
        // 1 2 3 4 5
        int splitStart = 1;
        int splitEnd = 5;
        if (splitEnd > count) {
            splitEnd = count;
        }
        if (page > 3) {
            if (page > count - 2) {
                splitStart = page - 2;
                splitEnd = count;
            } else {
                splitStart = page - 2;
                splitEnd = page + 2;
            }
        } // end
        
        // All Hottest Games
        AgentDAO agentDAO = new AgentDAO();
        List<Game> lstHottest = gameDAO.getHottestGameEachCat(agent, false, page, num, count);
        boolean check = false;
            if(agent.getManufactor() == "unknown" && agent.getMobileModel() == "unknown"){
            System.out.println("da ca");
            
            for(Game game1 : lstHottest){
                game1.setCheck(false);
                check = false;
            }
        } else {
            check = true;
        }

        ModelAndView mv = new ModelAndView("allhottest");
          //get quang cao
        AdvertisingDAO adDAO = new AdvertisingDAO();
        List<Advertisement> ad = adDAO.getAd();
        mv.addObject("list", ad);
        List<Advertisement> ad1 = adDAO.getAd3();
        mv.addObject("list1", ad1);

        mv.addObject("check", check);
        mv.addObject("page1", pagePara);
        mv.addObject("count", count);
        mv.addObject("isnext", isnext);
        mv.addObject("isback", isback);
        mv.addObject("lstHottest", lstHottest);
        mv.addObject("splitStart", splitStart);
        mv.addObject("splitEnd", splitEnd);
        mv.addObject("num", num);
        mv.addObject("page", page);
        mv.addObject("lstCat", lstCat);
        mv.addObject("lstHottestSize", lstHottest.size());
        mv.addObject("imgUrl", Config.getString("imgUrl"));
        return mv;
    }
}
