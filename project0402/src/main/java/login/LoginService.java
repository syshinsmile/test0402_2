package login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import login.LoginCommand;
import regist.MemberInfo;
import regist.RegistDao;

@Service
public class LoginService {
	
	@Autowired
	private LoginDao lDao;
	public void setlDao(LoginDao lDao) { this.lDao = lDao; }

	private List<String> e;
	    
	    public List<String> getEmail() { return lDao.getEmail(); }
	    public String getPassword(String s_email) { return lDao.getPassword(s_email);}
	    
	    public Errors checkEmail(final Object target, final Errors errors) {
	    	LoginCommand memberInfo= (LoginCommand)target;
	        
	        e = getEmail();
	        String s_email = memberInfo.getS_email();
	        String password = memberInfo.getPassword();
	        if (s_email.equals("")) { 
	        	errors.rejectValue("s_email", "required");
	        	if(password.equals("")) { errors.rejectValue("password", "required"); }
	        }else { 
	        	int x = 0;
	            for (int i = 0; i < this.e.size(); ++i) {
	                if (this.e.get(i).equals(s_email)) { 
	                	x = 1; 
				        String s_compare = getPassword(s_email);
				        if(!password.equals(s_compare)) { errors.rejectValue("password", "notSame"); }
				    }
	            }
	            if (x == 0) {
	                errors.rejectValue("s_email", "unique");
	            }else {         
	    	
	            }	
	        }
	
	        
	        return errors;
	    }
	}