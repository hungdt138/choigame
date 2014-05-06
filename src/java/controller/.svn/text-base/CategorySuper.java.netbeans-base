/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.bean.Agent;
import model.bean.GameCategory;
import model.bean.GameManufact;
import model.dao.AgentDAO;
import model.dao.GameCategoryDAO;

/**
 *
 * @author Nguyen Dinh Doan
 */
//public class CategorySuper implements Controller {
public class CategorySuper {

    /**
     * Store all Game Category in TIG_GAME_CAT
     */
    protected List<GameCategory> lstCat;
    protected List<GameManufact> lstGameManufact;

    /**
     * Default constructor with GameCatDAO and Agent initializations
     */
    public CategorySuper() {
        GameCategoryDAO gameCatDAO = new GameCategoryDAO();
        try {
            lstCat = gameCatDAO.getAllGameCat();
            lstGameManufact = gameCatDAO.getAllGameManufact();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Get User-Agent from request
     * @param request request from client
     * @return a new instance of Agent class
     */
    public Agent getUserAgent(HttpServletRequest request) {
        String[] fnm = null;
        String mobileNumber = null;
        String header = null;
        String ip = null;
        try {
            AgentDAO agentDAO = new AgentDAO();

            //get ip
            ip = "";

            // Get Info
            header = request.getHeader("user-agent");
            //   header = "NokiaC3-00/5.0 (03.35) Profile/MIDP-2.1 Configuration/CLDC-1.1 Mozilla/5.0 AppleWebKit/420+ (KHTML, like Gecko) Safari/420+";
            //header = "Mozilla/5.0 (Linux; U; Android 2.3.3; en-gb; GT-S5570 Build/GINGERBREAD) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
            if (header == null) {
                System.out.println("__________[CHOIGAME] Header null____________");
                header = "";
            }
            fnm = agentDAO.getMobileManufactAndModel(header.toUpperCase());
            mobileNumber = agentDAO.getMobileNumber(request);
            Agent ag = new Agent();
            ag.setHeader(header);
            ag.setManufactor(fnm[0]);
            ag.setMobileModel(fnm[1]);
            ag.setMobileNumber(mobileNumber);
            ag.setIp(ip);
            HttpSession session = request.getSession();
            session.setAttribute("agent", ag);
            System.out.println("[CHOIGAME] tao session OK!");
              // Get Info
            header = request.getHeader("user-agent");
           
            if (header.contains("Opera")) {
                ip = request.getHeader("X-FORWARDED-FOR");
            }
            if ("".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            String[] IPload = common.Config.getString("IP171").split(",");
            for (String ip1 : IPload) {
                if (ip.startsWith(ip1)) {
                    mobileNumber = agentDAO.getMobileNumber(request);
                }
            }

            String[] IPload125 = common.Config.getString("IP125").split(",");
            for (String ip1 : IPload125) {
                if (ip.startsWith(ip1)) {
                    mobileNumber = request.getHeader("msisdn");
                }
            }
          
            //   header = "NokiaC3-00/5.0 (03.35) Profile/MIDP-2.1 Configuration/CLDC-1.1 Mozilla/5.0 AppleWebKit/420+ (KHTML, like Gecko) Safari/420+";
            //header = "Mozilla/5.0 (Linux; U; Android 2.3.3; en-gb; GT-S5570 Build/GINGERBREAD) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
            //header = "BlackBerry9000/5.0.0.411 Profile/MIDP-2.1 Configuration/CLDC-1.1 VendorID/301";
            if (header == null) {
                System.out.println("__________[CHOIGAME] Header null____________");
                header = "";
            }
            fnm = agentDAO.getMobileManufactAndModel(header.toUpperCase());

            ag = new Agent();
            ag.setHeader(header);
            ag.setManufactor(fnm[0]);
            ag.setMobileModel(fnm[1]);
            ag.setMobileNumber(mobileNumber);
            ag.setIp(ip);
            session.setAttribute("agent", ag);
            System.out.println("[CHOIGAME] tao session OK!");

        } catch (Exception e) {
            System.out.println("[CHOIGAME] Error getUserAgent" + e.toString());
        }
        return new Agent(fnm[0], fnm[1], mobileNumber, header, ip);
    }
}
