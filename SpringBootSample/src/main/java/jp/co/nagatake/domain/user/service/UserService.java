package jp.co.nagatake.domain.user.service;

import java.util.List;

import jp.co.nagatake.domain.user.model.MUser;

public interface UserService {
	
	/* ユーザー登録 */
	public void signup(MUser user);
	
	/* ユーザー取得 */
	public List<MUser> getUsers();

}
