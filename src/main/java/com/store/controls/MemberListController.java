package com.store.controls;

import java.util.Map;

import com.store.dao.MemberDAO;

public class MemberListController implements Controller {
	/*
	 * Lý do thay đổi thành DI (Dependency_Injection) 
	 * 1) Để giảm sự phụ thuộc giữa các lớp 
	 * 2) Bằng cách khai báo giao diện MemberDao và thực hiện kế thừa Để tạo
	 * điều kiện chuyển đổi sang DBMS khác 
	 * 3) Hãy thử sử dụng nó cho một tác vụ tự
	 * động mà sau này bạn sẽ thay đổi
	 */

	MemberDAO memberDao = null;

	public MemberListController setMemberDao(MemberDAO memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {

		// Chèn đối tượng thành viên chứa thông tin thành viên vào mô hình
		model.put("members", memberDao.selectList());

		return "/member/MemberList.jsp";
	}

}