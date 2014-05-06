/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Config;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.bean.Advertisement;
import model.bean.Agent;
import model.bean.Game;
import model.bean.GameCategory;
import model.dao.AdvertisingDAO;
import model.dao.GameDAO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import unicode.Unicode;

/**
 *
 * @author Doan
 */
public class SearchResultController extends CategorySuper implements Controller {

    private GameDAO gameDAO;
    private Agent agent;
    private Unicode u;

    public SearchResultController() {
        gameDAO = new GameDAO();
        u = new Unicode();
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModelAndView mv = new ModelAndView("searchresults");
        // Get Agent
        HttpSession session = request.getSession();
        String radio = request.getParameter("radio");
        String content = request.getParameter("key");
        String[] key = null;
        agent = (Agent) session.getAttribute("agent");
        if (agent == null) {
            agent = super.getUserAgent(request);
        }
        //Kiem tra dai IP cho phep
        String ip = agent.getIp();
        if ("".equalsIgnoreCase(ip) && ip == null) {
            ip = request.getRemoteAddr();
        }
        String[] IPload = common.Config.getString("IP").split(",");
        int temp_false = 0;
        int length = IPload.length;
        for (String ip1 : IPload) {
            if (!ip.startsWith(ip1)) {
                temp_false += 1;
            }
        }
        if (temp_false == length) {
            System.out.println("[CHOIGAME] Redirect thanh cong!");
            response.sendRedirect("http://mobix.vn");
            return null;
        }
        request.setCharacterEncoding("utf-8");
        String catId = request.getParameter("catId");
        String searchQuery = request.getParameter("txtSearch").toLowerCase();

        int page = 1;
        String pagePara = request.getParameter("page");
        if (pagePara != null) {
            try {
                page = Integer.parseInt(pagePara);
            } catch (Exception e) {
                return null;
            }
        }



        // How many items display in one page?
        int num = Integer.parseInt(Config.getString("splitNum"));

        List<Game> lstSearchResults = new ArrayList<Game>();
        if ("0".equals(radio)) {
            lstSearchResults = gameDAO.getSearchResults(agent, searchQuery, catId);
        } else if ("1".equals(radio)) {
            lstSearchResults = gameDAO.getSearchByManufact(agent, searchQuery, catId);
        }

        boolean check = false;
        if ("unknown".equalsIgnoreCase(agent.getManufactor()) && "unknown".equalsIgnoreCase(agent.getMobileModel())) {
            for (Game game1 : lstSearchResults) {
                game1.setCheck(false);
                check = false;
            }
        } else {
            check = true;
        }

        int resultSize = lstSearchResults.size();
        int count = 1;
        if (resultSize % num == 0) {
            count = resultSize / num;
        } else {
            count = resultSize / num + 1;
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
        }

        boolean isnext = false, isback = false;
        if (page < count) {
            isnext = true;
        }
        if (page > 1) {
            isback = true;
        }
        // end

        if (lstSearchResults == null) {
            return null;
        }

        if (resultSize > num) {
            int remain = resultSize - ((page - 1) * num);   // đến trang tiếp theo thì còn mấy cái
            if (remain < num && page != 1) {
                lstSearchResults = lstSearchResults.subList((page - 1) * num, (page - 1) * num + remain);
            } else {
                lstSearchResults = lstSearchResults.subList((page - 1) * num, page * num);
            }
        }


        // Get Category name by ID
        String catName = "";
        if (catId != null) {
            int catIdInt = 0;
            try {
                catIdInt = Integer.parseInt(catId);
            } catch (Exception e) {
            }
            for (GameCategory cat : lstCat) {
                if (cat.getCatId() == catIdInt) {
                    catName = cat.getName();
                    break;
                }
            }
        }


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
        mv.addObject("searchQuery", searchQuery);
        mv.addObject("lstGames", lstSearchResults);
        mv.addObject("resultSize", resultSize);
        mv.addObject("splitStart", splitStart);
        mv.addObject("splitEnd", splitEnd);
        mv.addObject("catName", catName);
        mv.addObject("page", page);
        mv.addObject("searchQuery", searchQuery);
        mv.addObject("catId", catId);
        mv.addObject("lstCat", lstCat);
        mv.addObject("imgUrl", Config.getString("imgUrl"));
        mv.addObject("radio", radio);
        return mv;
    }
}
