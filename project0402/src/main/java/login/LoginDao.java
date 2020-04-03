package login;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class LoginDao extends SqlSessionDaoSupport {
	public List<String> getEmail(){ return getSqlSession().selectList("regist.s_email"); }
	public String getPassword(String s_email) { return getSqlSession().selectOne("regist.s_login",s_email); }
}
