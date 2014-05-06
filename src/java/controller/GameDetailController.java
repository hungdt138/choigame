/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Stringutils;
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
import model.dao.AdvertisingDAO;
import model.dao.AgentDAO;
import model.dao.GameCategoryDAO;
import model.dao.GameDAO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Do Tien Hung
 */
public class GameDetailController extends CategorySuper implements Controller  {
    private Agent agent;
    public GameDetailController() {
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ModelAndView mv = new ModelAndView("details");
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
            //get quang cao
        AdvertisingDAO adDAO = new AdvertisingDAO();
        List<Advertisement> ad = adDAO.getAd();
        mv.addObject("list", ad);
        List<Advertisement> ad1 = adDAO.getAd3();
        mv.addObject("list1", ad1);
        Map catMap = new HashMap();
        String idPara = request.getParameter("id");
        int id = (idPara !=null && !idPara.equals("")) ? Integer.parseInt(idPara) : 0 ;
        String uri = request.getQueryString() != null ? request.getRequestURI().concat("?").concat(request.getQueryString()) : request.getRequestURI();
        GameDAO gameDao = new GameDAO();
        Game game = gameDao.getDetail(id);
        //check mobile support
         
        AgentDAO agentDAO = new AgentDAO();
        boolean check = agentDAO.checkMobileByGameId(id, agent);
            catMap.put("checkmobile", check);
        List<Game> relatedCat = gameDao.getGameRelatedCat(id, agent);
        /*
         * Kiem tra game co thuoc goi khuyen mai da duoc download khong?
         */

        String msisdn = agent.getMobileNumber();
        if(msisdn == null){
            msisdn = "";
        }//phone number fix tam thoi
        String packageid = request.getParameter("pid");
        int pid = 0;
        if(packageid != null){
             pid = Integer.parseInt(packageid);
             catMap.put("pid", pid);
            boolean checkgame = Stringutils.checkAllowInLog(msisdn, "TIG_GAME_PACKAGE", pid);
            if(checkgame == false){
                catMap.put("check", checkgame);
            }else{
                catMap.put("check", checkgame);
            }
        }else{
             pid = gameDao.getpid(id);
             if(pid == 0){
                 pid = 0;
                 catMap.put("pid", pid);
             } else {
                 boolean checkgame = Stringutils.checkAllowInLog(msisdn, "TIG_GAME_PACKAGE", pid);
            if(checkgame == false){
                catMap.put("check", checkgame);
            }else{
                catMap.put("check", checkgame);
            }
             }
        }
        catMap.put("game", game);
        catMap.put("id", id);
        catMap.put("relatedCat", relatedCat);
        catMap.put("confirmurl", common.Config.getString("confirm_url"));
        catMap.put("uri", uri);
        mv.addObject("lstCat", lstCat);
        mv.addObject("lst", catMap);
        
        return mv;
    }
}
