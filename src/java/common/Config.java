/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *
 * @author Do Tien Hung
 * Load info tu file properties
 */
public class Config {
    private static final String BUNDLE_NAME = "common.info";
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
    
    private Config(){    
    }
    
    public static String getString(String key){
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' +key+ '!';
        }
    }
}
