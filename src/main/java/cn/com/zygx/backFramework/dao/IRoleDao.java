package cn.com.zygx.backFramework.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.com.zygx.backFramework.common.base.FrameworkDao;
import cn.com.zygx.backFramework.db.mybatis.MyBatisRepository;
import cn.com.zygx.backFramework.model.Resources;
import cn.com.zygx.backFramework.model.Role;
import cn.com.zygx.backFramework.model.RoleResources;
import cn.com.zygx.backFramework.model.UserRole;

@MyBatisRepository
public interface IRoleDao extends FrameworkDao {

	public List<Role> listRole();
	
	public List<Role> listByUseRole(@Param("uid")int uid); //获取用户的所有角色
	
	public UserRole loadUserRole(@Param("uid")int uid,@Param("roleId")int roleId);
	
	public void addUserRole(@Param("uid")int uid,@Param("roleId")int roleId);
	
	public void deleteUserRole(@Param("uid")int uid,@Param("roleId")int roleId);
	
	public void deleteUserAllRole(@Param("uid")int uid);//删除用户的所有角色
	
	public List<Resources> listRoleResources(@Param("roleId")int roleId);//根据角色获取所有能访问的资源
	
	public void addRoleResource(@Param("roleId")int roleId,@Param("resId")int resId); //添加角色资源
	
	public void deleteRoleResource(@Param("roleId")int roleId,@Param("resId")int resId); //删除角色资源
	
	public RoleResources loadResourceRole(@Param("roleId")int roleId,@Param("resId")int resId); //获取角色资源
	
	
}
