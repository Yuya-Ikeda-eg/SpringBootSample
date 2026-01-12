package jp.co.nagatake.domain.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.nagatake.domain.user.model.MUser;
import jp.co.nagatake.domain.user.service.UserService;
import jp.co.nagatake.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper mapper;
	
	/* ユーザー登録 */
	@Override
	public void signup(MUser user) {
		user.setDepartmentId(1);//部署
		user.setRole("ROLE_GENERAL");//ロール
		mapper.insertOne(user);
	}
	
}
