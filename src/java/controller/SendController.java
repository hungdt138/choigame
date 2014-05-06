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
import model.dao.GameDAO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Do Tien Hung
 */
public class SendController extends CategorySuper implements Controller {
    
    private Agent agent;
    public SendController() {
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("send");
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
        String idPara = request.getParameter("id");
        int id = (idPara !=null && !idPara.equals("")) ? Integer.parseInt(idPara) : 0 ;
        String uri = request.getQueryString() != null ? request.getRequestURI().concat("?").concat(request.getQueryString()) : request.getRequestURI();
        GameDAO gameDao = new GameDAO();
        Game game = gameDao.getDetail(id);
        
        List<Game> relatedCat = gameDao.getGameRelatedCat(id, agent);
       
        Map catMap1 = new HashMap();
        
        catMap1.put("game", game);
        catMap1.put("id", id);
        catMap1.put("relatedCat", relatedCat);
        catMap1.put("confirmurl", common.Config.getString("confirm_url"));
        catMap1.put("uri", uri);
        
        // when user click back button --> need catid, page, type
        String url = request.getParameter("url");
        String price = request.getParameter("pr");
        String cat = request.getParameter("catid");
        String phone = request.getParameter("phone");
        String warning = "";
        String uri1 = request.getParameter("uri");
        if(phone == null || phone.equals("")){
            warning = "";
        }else if (!Stringutils.isPhoneNumber(phone)){
            warning = "SDT bạn nhập chưa đúng, bạn vui lòng nhập lại!";
        }else{
            String directURL = common.Config.getString("confirm_url") + "?url="
                        + url +"&id="+ id +"&pr=" + price + "&catid=" + cat + "&pn=" + phone;
            response.sendRedirect(directURL);
        }
        catMap1.put("url", url);
        catMap1.put("uri1", uri1);
        catMap1.put("pr", price);
        catMap1.put("cat", cat);
        catMap1.put("warning", warning);
        
        catMap1.put("game", game);
        catMap1.put("id", id);
        catMap1.put("relatedCat", relatedCat);
        catMap1.put("confirmurl", common.Config.getString("confirm_url"));
        catMap1.put("uri", uri);
        mv.addObject("lstCat", lstCat);
        mv.addObject("lst", catMap1);
        
        return mv;
    }
    

}
