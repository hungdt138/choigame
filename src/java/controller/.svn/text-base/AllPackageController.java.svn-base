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
import model.bean.GamePackage;
import model.dao.AdvertisingDAO;
import model.dao.PackageDAO;
import model.dao.PageCounter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Nguyen Dinh Doan
 */
public class AllPackageController extends CategorySuper implements Controller {

    private PackageDAO packageDAO;
    private Agent agent;

    /**
     * Default Constructor with initialize an GameDAO 
     */
    public AllPackageController() {
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
        int page = 1;
        String pagePara = request.getParameter("page");
        if (pagePara != null) {
            try {
                page = Integer.parseInt(pagePara);
            } catch (Exception e) {
            }
        }

         

        // Split page: 1 2 3 4 5...
        int num = Integer.parseInt(Config.getString("splitNum"));
        int count = new PageCounter().getPageCountAllPack(agent, num);
        boolean isnext = false, isback = false;
        if(page < count){
            isnext = true;
        }
        if(page > 1){
            isback = true;
        }
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

        // List to display when click "xem tất cả"
        List<GamePackage> lstPackage = packageDAO.getPackagesEachPage(agent, page, num, count);

        ModelAndView mv = new ModelAndView("allpackage");
            //get quang cao
        AdvertisingDAO adDAO = new AdvertisingDAO();
        List<Advertisement> ad = adDAO.getAd();
        mv.addObject("list", ad);
        List<Advertisement> ad1 = adDAO.getAd3();
        mv.addObject("list1", ad1);
         mv.addObject("page1", pagePara);
        mv.addObject("count", count);
        mv.addObject("isnext", isnext);
        mv.addObject("isback", isback);
        mv.addObject("lstPackage", lstPackage);
        mv.addObject("splitStart", splitStart);
        mv.addObject("splitEnd", splitEnd);
        mv.addObject("page", page);
        mv.addObject("imgUrl", Config.getString("imgUrl"));
        mv.addObject("lstCat", lstCat);
        return mv;
    }
}
