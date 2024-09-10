package com.store.controls;

import java.util.Map;

import com.store.bind.DataBinding;
import com.store.dao.MemberDAO;
import com.store.model.Member;

public class MemberUpdateController implements Controller, DataBinding {
	
	MemberDAO memberDao = null;
	
	public MemberUpdateController setMemberDao(MemberDAO memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member member = (Member)model.get("member");
		
		if(member.getEmail() == null) {
			Integer no = (Integer)model.get("id");
			Member detailInfo = memberDao.selectOne(no);
			model.put("member", detailInfo);
			return "/member/MemberUpdateForm.jsp";
			
		}else {
			memberDao.update(member);
			return "redirect:list.html";
		}
	}

	@Override
	public Object[] getDataBinders() {
		
		//Trả về thông tin về lớp sẽ được tạo 
		return new Object[] {
				"id", Integer.class,
				"member", com.store.model.Member.class
		};
	}

}

