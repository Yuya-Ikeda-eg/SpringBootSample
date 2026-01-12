package jp.co.nagatake.repository;

import org.apache.ibatis.annotations.Mapper;

import jp.co.nagatake.domain.user.model.MUser;

@Mapper
public interface UserMapper {

	/* ユーザー登録 */
	public int insertOne(MUser user);
}
