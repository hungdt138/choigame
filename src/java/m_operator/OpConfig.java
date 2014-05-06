/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package m_operator;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *
 * @author Do Tien Hung
 */
public class OpConfig {
    private static final String BUNDLE_NAME = "m_operator.op";
    
    private static final ResourceBundle RECSOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
    
    private OpConfig(){
    }
    
    public static String getString(String key){
    
        try {
            return RECSOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return null;
        }
    }
}
