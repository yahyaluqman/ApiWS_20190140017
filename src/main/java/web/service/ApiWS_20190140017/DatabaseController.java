/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service.ApiWS_20190140017;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ASUS
 */
@Controller
public class DatabaseController {
    
    @RequestMapping("/snack")
    @ResponseBody
    public List<Snack> getSnack(){
        
        List<Snack> snack = new ArrayList<>();
        
        SnackJpaController controller = new SnackJpaController();
        try {
            snack = controller.findSnackEntities();
        }
        catch (Exception ex){
            
        }
        
        return snack;
    }
}
