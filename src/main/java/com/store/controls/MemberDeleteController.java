package com.store.controls;

import java.util.Map;

import com.store.bind.DataBinding;
import com.store.dao.MemberDAO;

public class MemberDeleteController implements Controller, DataBinding {
	
	MemberDAO memberDao = null;
	
	public MemberDeleteController setMemberDao(MemberDAO memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		Integer no = (Integer)model.get("id");
		memberDao.delete(no);
		
		return "redirect:list.html";
	}

	//Trả về thông tin về lớp sẽ được tạo
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
				"id", Integer.class
		};
	}

}
