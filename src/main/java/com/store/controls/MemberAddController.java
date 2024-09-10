package com.store.controls;

import java.util.Map;

import com.store.bind.DataBinding;
import com.store.dao.MemberDAO;
import com.store.model.Member;

public class MemberAddController implements Controller, DataBinding {
	
	MemberDAO memberDao = null;
	
	public MemberAddController setMemberDao(MemberDAO memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		
		// Tên giá trị khóa, loại lớp sẽ được tạo tự động
		return new Object[] {
				"member", com.store.model.Member.class
		};
		
	}
	

	@Override
	public String execute(Map<String, Object> model) throws Exception {

		
		
		Member member = (Member)model.get("member");
		
		if(member.getEmail() == null) {
			return "/member/MemberForm.jsp";
		}else {
			memberDao.insert(member);
			
			return "redirect:list.html";
		}
	}



}