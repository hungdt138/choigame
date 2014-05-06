/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.GamePackage;
import model.dao.GameDAO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Do Tien Hung
 */
public class resultpackageController implements Controller {
    
    public resultpackageController() {
    }

    public ModelAndView handleRequest(HttpServletRequest resquest, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("resultpackage");
        Map rpackage = new HashMap();
        String packageid =  resquest.getParameter("pid");
        String status = resquest.getParameter("status");
        rpackage.put("st", status);
        int pid = (packageid != null) ? Integer.parseInt(packageid) : 0;        
        GameDAO gameDao = new GameDAO();
        GamePackage gpackage = new GamePackage();
        
        gpackage =  gameDao.getPackage(pid);
        
        rpackage.put("package", gpackage);
        
        mv.addObject("lst", rpackage);
        return mv;
    }
    
    
}
