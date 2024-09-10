package com.store.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.store.bind.DataBinding;
import com.store.dao.MemberDAO;
import com.store.model.Member;

public class LogInController implements Controller, DataBinding {
	
	MemberDAO memberDao = null;
	
	public LogInController setMemberDao(MemberDAO memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member loginInfo = (Member)model.get("loginInfo");
		if(loginInfo.getEmail() == null) {
			return "/auth/LoginForm.jsp";
		}else {
			
			Member member = memberDao.exist(loginInfo.getEmail(), 
											loginInfo.getPassword());
			if(member != null) {
				HttpSession session = (HttpSession)model.get("session");
				session.setAttribute("member", member);
				return "redirect:../member/list.html";
			}else {
				return "/auth/LoginFail.jsp";
			}
		}
	}

	//Trả về thông tin về lớp sẽ được tạo
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
				"loginInfo", com.store.model.Member.class
		};
	}

}
