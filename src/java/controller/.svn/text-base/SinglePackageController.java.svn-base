/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Config;
import common.Stringutils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.bean.Advertisement;
import model.bean.Advertisement1;
import model.bean.Agent;
import model.bean.Game;
import model.bean.GamePackage;
import model.dao.AdvertisingDAO;
import model.dao.AgentDAO;
import model.dao.GameDAO;
import model.dao.PackageDAO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Nguyen Dinh Doan
 */
public class SinglePackageController extends CategorySuper implements Controller {

    private GameDAO gameDAO;
    private PackageDAO packageDAO;
    private Agent agent;

    public SinglePackageController() {
        gameDAO = new GameDAO();
        packageDAO = new PackageDAO();
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
        // Get pack id
        ModelAndView mv = new ModelAndView("singlepackage");
        String packId = request.getParameter("pId");
        int packIdInt = 0;
        if (packId != null) {
            try {
                packIdInt = Integer.parseInt(packId);
            } catch (Exception e) {
            }
        }
  
          
        
        // Get Games in Package
        List<Game> lstGamesInPack = gameDAO.getGamesInPack(agent, packIdInt);
        //check mobile support
        boolean checkmobile = false;
        AgentDAO agentDAO = new AgentDAO();
             if("unknown".equalsIgnoreCase(agent.getManufactor()) && "unknown".equalsIgnoreCase(agent.getMobileModel())){
            for(Game game1 : lstGamesInPack){
                game1.setCheck(false);
                checkmobile = false;
            }
        } else {
            checkmobile = true;
        }

        // Get Package by ID
        GamePackage pkg = packageDAO.getPackageById(packIdInt);
        
        // Get Related Packages
        List<GamePackage> lstRelatedPkg = packageDAO.getRelatedPackages(packIdInt);
        
        /*
         * Kiem tra khach hang da tai goi game nay chua?
         */
        String confirm_url = common.Config.getString("confirm_url");
        //String phonenumber = (String) request.getSession().getAttribute("msisdn");
        String phonenumber = agent.getMobileNumber();
        if(phonenumber == null){
            phonenumber = "";
        }
        boolean check = Stringutils.checkAllowInLog(phonenumber, "TIG_GAME_PACKAGE", packIdInt);
        String check1 = request.getParameter("catid");
        if(check == false){
            mv.addObject("check", check);
            //luu vao db va thuc hien charge price
           // String msisdn = (String) request.getSession().getAttribute("msisdn");
            if(check1 != null){
            String msisdn = agent.getMobileNumber();
            if(msisdn == null){
                msisdn = "";
            }
            int value = 1; //gia tri thoi gian cho phep download
            Stringutils.addUserInfoLog(msisdn, "TIG_GAME_PACKAGE", packIdInt, value);
            //luu xong => check = true;
            check = true;
            
            String host = "http://" + request.getServerName() + ":" + request.getLocalPort() + request.getContextPath();

            //pr="+ pkg.getPackagePrice()
            String url = confirm_url + "?pId="+packIdInt+"&catid=2&url=" +host+ "/singlepackage.html?pId=" +packIdInt+"&pr=" +pkg.getPackagePrice() ;
            
            response.sendRedirect(url);
            }
        } else {
            mv.addObject("check", check);
            
        }
        
            //get quang cao
        AdvertisingDAO adDAO = new AdvertisingDAO();
        List<Advertisement> ad = adDAO.getAd();
        mv.addObject("list", ad);
        List<Advertisement> ad1 = adDAO.getAd3();
        mv.addObject("list1", ad1);
        mv.addObject("checkmobile", checkmobile);
        mv.addObject("lstGamesInPack", lstGamesInPack);
        mv.addObject("size", lstGamesInPack.size());
        mv.addObject("lstRelatedPkg", lstRelatedPkg);
        mv.addObject("pkg", pkg);
        mv.addObject("lstCat", lstCat);
        mv.addObject("imgUrl", Config.getString("imgUrl"));
        return mv;
    }
}
