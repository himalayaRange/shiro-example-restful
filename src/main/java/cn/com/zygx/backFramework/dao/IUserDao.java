package cn.com.zygx.backFramework.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.com.zygx.backFramework.common.base.FrameworkDao;
import cn.com.zygx.backFramework.db.mybatis.MyBatisRepository;
import cn.com.zygx.backFramework.model.Resources;
import cn.com.zygx.backFramework.model.User;

@MyBatisRepository
public interface IUserDao extends FrameworkDao{

	public List<User> listUser();
	
	public List<User> listByRole(int id);
	
	public User loadByUsername(@Param("username")String usename);
	
	public List<Resources> listAllResources(@Param("uid")int uid);
	
	public List<String> listRoleSNByUser(@Param("uid")int uid);
}
