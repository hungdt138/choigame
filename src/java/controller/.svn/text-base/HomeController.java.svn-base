/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import common.Config;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.bean.Advertisement;
import model.bean.Agent;
import model.bean.ChargePDU;
import model.bean.Game;
import model.bean.GameManufact;
import model.bean.Groupon;
import model.bean.GrouponBlock;
import model.dao.AdvertisingDAO;
import model.dao.AgentDAO;
import model.dao.ChargeLogDAO;
import model.dao.GameCategoryDAO;
import model.dao.GameDAO;
import model.dao.GrouponDAO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Nguyen Dinh Doan
 */
public class HomeController extends CategorySuper implements Controller {

    private Game game;
    private GameDAO gameDAO;
    private GameCategoryDAO gameCatDAO;
    private GrouponDAO gDAO;
    private Agent agent;
    ///private List<GameManufact> lstGameManufact;

    /**
     * Default Constructor with initialize an GameDAO 
     */
    public HomeController() {
        gameDAO = new GameDAO();
        gDAO = new GrouponDAO();
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setCharacterEncoding("utf-8");
        //String user_agent = request.getHeader("user-agent");
        ModelAndView mv = new ModelAndView("home");
        //lstGameManufact = gameCatDAO.getAllGameManufact();

        // Get Agent
        HttpSession session = request.getSession();
        agent = (Agent) session.getAttribute("agent");
        if (agent == null) {
            agent = super.getUserAgent(request);
        }
        System.out.println("[CHOIGAME] Header: " + agent.getHeader());

        //Kiem tra dai IP cho phep
        String ip = agent.getIp();
        if ("".equalsIgnoreCase(ip) && ip == null) {
            ip = request.getRemoteAddr();
        }
        System.out.println("[CHOIGAME] IP " + ip);
//        String[] IPload = common.Config.getString("IP").split(",");
//        int temp_false = 0;
//        int length = IPload.length;
//        for (String ip1 : IPload) {
//            if (!ip.startsWith(ip1)) {
//                temp_false += 1;
//            }
//        }
//        if (temp_false == length) {
//            System.out.println("[CHOIGAME] Redirect thanh cong!");
//            response.sendRedirect("http://mobix.vn");
//            return null;
//        }

        //Hien thi thong tin quang cao
        AdvertisingDAO adDAO = new AdvertisingDAO();
        List<Advertisement> ad = adDAO.getAd();
        mv.addObject("list", ad);
        List<Advertisement> ad1 = adDAO.getAd3();
        mv.addObject("list1", ad1);

       

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



        // Check Type: New or Hot or Khuyen Mai
        String type = request.getParameter("type");

        if (type == null) {
            type = "newest";
        }
        int level = 1;
        // Get the Candidate Game
        Game candidate = gameDAO.getCandidateGame(agent);
        //Truong hop game de cu khong ho tro dien thoai nao
        if (candidate == null) {
            candidate = gameDAO.getCandidateGameNotSupport(agent);
        }
        // rate dang sao phan game de cu
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
        //Game candidate = null;
//         int count = 0;
//         AgentDAO b = new AgentDAO();
//        count = b.countGame(agent);
//        mv.addObject("count", count);
        System.out.println(agent.getHeader());
        String a = "";
//        if (user_agent.indexOf("Android") != -1) {
//             a = "Android";
//        }
        String msisdn = agent.getMobileNumber();
        String phone_number = msisdn;
        if (phone_number == null) {
            phone_number = "";
        }
        //Luu log truy cap => Su dung de thong ke luot truy cap, thong ke cac nguon vao cua khach hang
        if (phone_number != null && !"".equalsIgnoreCase(phone_number)) {
            phone_number = msisdn;
            if (msisdn.startsWith("0")) {
                phone_number = msisdn.substring(1);
            } else if (msisdn.startsWith("84")) {
                phone_number = msisdn.substring(2);
            } else if (msisdn.startsWith("+84")) {
                phone_number = msisdn.substring(3);
            }
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            java.sql.Timestamp timeStamp = new Timestamp(now.getTime());
            String currentTime = common.LogFile.getYYYYMMDDHHMMSSString(timeStamp);
            if (currentTime == null || "".equals(currentTime)) {
                currentTime = "YYYYMMDDHHMMSSS";
            }
            //Log File
            // common.LogFile.SaveFile(phone_number, "", "TEKCIZ", "", "LOGIN", "", "Login wap", "", "", "", "0", currentTime, "TEKCIZ", "");
            //Log DB.
            ChargeLogDAO log = new ChargeLogDAO();
            java.lang.Integer requestId = Integer.valueOf(1);
            java.sql.Timestamp ts = new Timestamp(System.currentTimeMillis());
            java.lang.String providerid = "DATACP";
            java.lang.String serviceId = "TEKCIZ";
            java.lang.String cmd = "LOGIN";
            java.lang.String contents = "LOGIN WAP";
            ChargePDU chPDU = new ChargePDU();
            chPDU.setMsisdn(msisdn);
            chPDU.setCharging("0");
            chPDU.setUsername("tekciz");
            chPDU.setPassword("tekcis20may");
            chPDU.setRegTime(ts);
            chPDU.setProviderid(providerid);
            chPDU.setServiceid(serviceId);
            chPDU.setCmd(cmd);
            chPDU.setContents(contents);
            chPDU.setRequesid(requestId);
            chPDU.setReturn_values("");
            chPDU.setPcode("");
            log.insertRow(chPDU);

            // Insert user info to log
            //String user_agent = request.getHeader("user-agent");
            AgentDAO agDao = new AgentDAO();
            agDao.addUserInfoLog(phone_number, agent.getManufactor(), agent.getMobileModel(), agent.getHeader());
        }
        // Create ModelAndView object

//         mv.addObject("count", count);
        mv.addObject("android", a);
        mv.addObject("mobileNumer", msisdn);
        mv.addObject("candidate", candidate);
        mv.addObject("imgUrl", Config.getString("imgUrl"));
        System.out.println("[ChoiGame] MSISDN: " + msisdn);
        System.out.println("[ChoiGame] MOBILE: " + agent.getManufactor() + " " + agent.getMobileModel());
        mv.addObject("mobiManu", agent.getManufactor());
        mv.addObject("mobileModel", agent.getMobileModel());
        mv.addObject("view", candidate.getDownloaded());
        mv.addObject("confirmurl", common.Config.getString("confirm_url"));
        String abc = "0";
        mv.addObject("aa", abc);
        /*
         * Check and display 
         */
        AgentDAO agentDAO = new AgentDAO();
        if (type.equals("newest")) {
            // Newest Games
            List<Game> lst = gameDAO.getNewestGameEachCat(agent, true, 1, 1, 1); // null,1, 1 ko co y nghia o day
            boolean check = false;
            if ("unknown".equalsIgnoreCase(agent.getManufactor()) && "unknown".equalsIgnoreCase(agent.getMobileModel())) {
                for (Game game1 : lst) {
                    game1.setCheck(false);
                    check = false;
                }
            } else {
                check = true;
            }
            mv.addObject("check", check);
            mv.addObject("lst", lst);
            mv.addObject("size", lst.size());
            mv.addObject("lstCat", lstCat);
            mv.addObject("lstGameManufact", lstGameManufact);
            mv.addObject("typeOfView", "newest");
            return mv;
        } else {
            // Hottest Games
            List<Game> lst = gameDAO.getHottestGameEachCat(agent, true, 1, 1, 1); // null,1, 1 ko co y nghia o day
            boolean check = false;

            if ("unknown".equalsIgnoreCase(agent.getManufactor()) && "unknown".equalsIgnoreCase(agent.getMobileModel())) {
                for (Game game1 : lst) {
                    game1.setCheck(false);
                    check = false;
                }
            } else {
                check = true;
            }
            mv.addObject("check", check);
            mv.addObject("lst", lst);
            mv.addObject("size", lst.size());
            mv.addObject("lstCat", lstCat);
            mv.addObject("lstGameManufact", lstGameManufact);
            mv.addObject("typeOfView", "hottest");
            
            return mv;
        }
    }
}
