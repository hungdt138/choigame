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
import model.bean.Advertisement1;
import model.bean.Agent;
import model.bean.Game;
import model.bean.GamePackage;
import model.bean.Groupon;
import model.bean.GrouponBlock;
import model.dao.AdvertisingDAO;
import model.dao.GameDAO;
import model.dao.GrouponDAO;
import model.dao.PackageDAO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Nguyen Dinh Doan
 */
public class PackageController extends CategorySuper implements Controller {

    private GameDAO gameDAO;
    private PackageDAO pkgDAO;
    private Agent agent;
    private GrouponDAO gDAO;

    public PackageController() {
        gameDAO = new GameDAO();
        pkgDAO = new PackageDAO();
        gDAO = new GrouponDAO();
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // Get Agent
        HttpSession session = request.getSession();
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

        // List Package in Home Page
        List<GamePackage> lstPackage = pkgDAO.getPackageInHome();


        // Candidate Game
        Game candidate = gameDAO.getCandidateGame(agent);

        ModelAndView mv = new ModelAndView("package");
        int level = 1;
        // Get the Candidate Game
        if (candidate == null) {
            candidate = gameDAO.getCandidateGameNotSupport(agent);
        }
        if (candidate.getDownloaded() >= 100 && candidate.getDownloaded() <= 200) {
            level = 1;
        } else if (candidate.getDownloaded() > 200 && candidate.getDownloaded() <= 400) {
            level = 2;
        } else if (candidate.getDownloaded() > 400 && candidate.getDownloaded() <= 600) {
            level = 3;
        } else if (candidate.getDownloaded() > 600 && candidate.getDownloaded() <= 800) {
            level = 4;
        } else if (candidate.getDownloaded() > 800 && candidate.getDownloaded() <= 1000) {
            level = 5;
        }

        mv.addObject("lv1", level);

        //Get Groupon
        Groupon gr = gDAO.GetGrouponGame(agent);
        if (gr == null) {
            gDAO.UpdateBlock(1);
        } else {
            gDAO.UpdateBlock(0);
            mv.addObject("gr", gr);
            mv.addObject("countnumberbuy", gDAO.CountNumberBuyFromLog(gr.getId()));
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
        }


        //Check block hien thi
        List<GrouponBlock> lstGroupon = gDAO.GetBlockDisplay();
        mv.addObject("BlockGroupon", lstGroupon);
        //get quang cao
        AdvertisingDAO adDAO = new AdvertisingDAO();
        List<Advertisement> ad = adDAO.getAd();
        mv.addObject("list", ad);
        List<Advertisement> ad1 = adDAO.getAd3();
        mv.addObject("list1", ad1);
        mv.addObject("mobileNumer", agent.getMobileNumber());
        mv.addObject("lstPackage", lstPackage);
        mv.addObject("lstCat", lstCat);
        mv.addObject("imgUrl", Config.getString("imgUrl"));
        mv.addObject("candidate", candidate);
        mv.addObject("view", candidate.getDownloaded());
        return mv;
    }
}
