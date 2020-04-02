package regist;

import org.springframework.validation.Errors;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "/s_regist/controll" })
public class RegistMemberController
{
    @Autowired
    private RegistService serv;
    @Autowired
    private MemberInfoValidator vali;
    private String formViewName;
    
    public RegistMemberController() {
        this.formViewName = "student/s_regist/memberForm";
    }
    
    public void setServ(final RegistService serv) {
        this.serv = serv;
    }
    
    public void setVali(final MemberInfoValidator vali) {
        this.vali = vali;
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
    public String submit(@ModelAttribute final MemberInfo memberInfo, final BindingResult result, final Model model) {
        this.vali.validate(memberInfo, (Errors)result);
        if (result.hasErrors()) {
            return this.formViewName;
        }
        this.serv.insert(memberInfo);
        return "student/s_regist/success";
    }
}