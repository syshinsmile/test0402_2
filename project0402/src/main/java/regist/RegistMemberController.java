package regist;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({ "/s_regist/controll" })
public class RegistMemberController
{
    @Autowired
    private RegistService serv;
    public void setServ(RegistService serv) {
        this.serv = serv;
    }
    
    @Autowired
    private MemberInfoValidator vali;
    public void setVali(MemberInfoValidator vali) {
        this.vali = vali;
    }
    
    private String formViewName;
    
    public RegistMemberController() {
        this.formViewName = "student/s_regist/memberForm";
    }
    
    @ModelAttribute
    protected Object formBackingObject() throws Exception {
        return new MemberInfo();
    }
    
    @RequestMapping(method = { RequestMethod.GET })
    public String form(final Model model) {
        return this.formViewName;
    }
    
    @RequestMapping(method = { RequestMethod.POST })
    public String submit(@ModelAttribute final MemberInfo memberInfo, final BindingResult result, final Model model,HttpServletRequest req) {
        this.vali.validate(memberInfo, (Errors)result);
        if (result.hasErrors()) {
            return this.formViewName;
        }
        this.serv.insert(memberInfo);
        
        return "student/s_regist/success";
    }
    
    
    
}