/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Config;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.bean.Advertisement;
import model.bean.Agent;
import model.bean.Groupon;
import model.bean.GrouponLog;
import model.dao.AdvertisingDAO;
import model.dao.GrouponDAO;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author noname
 */
public class GrouponDetailController extends CategorySuper implements Controller {

    private GrouponDAO gDAO = null;
    private Agent agent;

    public GrouponDetailController() {
        gDAO = new GrouponDAO();
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("groupondetail");

        // Get Agent
        HttpSession session = request.getSession();
        agent = (Agent) session.getAttribute("agent");
        if (agent == null) {
            agent = super.getUserAgent(request);
        }
        //Kiem tra dai IP cho phep
//        String ip = agent.getIp();
//        if("".equalsIgnoreCase(ip) && ip == null){
//            ip = request.getRemoteAddr();
//        }
//            String[] IPload = common.Config.getString("IP").split(",");
//        int temp_false = 0;
//        int length = IPload.length;
//        for(String ip1 : IPload){
//            if(!ip.startsWith(ip1)){
//                temp_false += 1;
//            }
//        }
//        if(temp_false == length){
//                System.out.println("[CHOIGAME] Redirect thanh cong!");
//                response.sendRedirect("http://mobix.vn");
//                return null;
//        }
        //get quang cao
        AdvertisingDAO adDAO = new AdvertisingDAO();
        List<Advertisement> ad = adDAO.getAd();
        mv.addObject("list", ad);
        List<Advertisement> ad1 = adDAO.getAd3();
        mv.addObject("list1", ad1);

        String idtemp = request.getParameter("id");
        int id = (!"".equalsIgnoreCase(idtemp) && idtemp != null) ? Integer.parseInt(idtemp) : 0;
        Groupon gr = gDAO.GetGrouponGameById(agent, id);
         //get thoi gian
        Calendar c = Calendar.getInstance();
        long enddate = gr.getEnddate1().getTime();
        c.setTimeInMillis(enddate);
        mv.addObject("year", c.get(Calendar.YEAR)); 
        mv.addObject("month", c.get(Calendar.MONTH));
        mv.addObject("day", c.get(Calendar.DAY_OF_MONTH));
        mv.addObject("hour", c.get(Calendar.HOUR_OF_DAY));
        mv.addObject("minute", c.get(Calendar.MINUTE));
        mv.addObject("second", c.get(Calendar.SECOND));
        
        
        //end get thoi gian
        

        String t = request.getParameter("t");
        String tab = "tab1";
        if ("1".equalsIgnoreCase(t)) {
            tab = "tab1";
        } else if ("2".equalsIgnoreCase(t)) {
            tab = "tab2";
        } else if ("3".equalsIgnoreCase(t)) {
            tab = "tab3";
        }

        String pagePara = request.getParameter("page");
        int page = 1;
        if (pagePara != null) {
            page = Integer.parseInt(pagePara);
        }


        // Split page: 1 2 3 4 5...
        int num = Integer.parseInt(Config.getString("splitNum"));
        int count = gDAO.CountListMsisdnBuy(num, id);
        boolean isnext = false, isback = false;
        if (page < count) {
            isnext = true;
        }
        if (page > 1) {
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

        List<GrouponLog> lst = gDAO.GetListMsisdnBuy(id, page, num);

        mv.addObject("countnumberbuy", gDAO.CountNumberBuyFromLog(gr.getId())) ;
        mv.addObject("gr", gr);
        mv.addObject("id", id);
        mv.addObject("tab", tab);
        mv.addObject("lst", lst);
        mv.addObject("splitStart", splitStart);
        mv.addObject("splitEnd", splitEnd);
        mv.addObject("num", num);   // so game tren 1 trang
        mv.addObject("page", page);
        mv.addObject("page1", pagePara);
        mv.addObject("isnext", isnext);
        mv.addObject("isback", isback);
        mv.addObject("count", count);
        mv.addObject("confirmurl", common.Config.getString("confirm_url"));

        return mv;
    }
}
