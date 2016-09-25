package cn.com.zygx.backFramework.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Lists;
import cn.com.zygx.backFramework.common.Constants;
import cn.com.zygx.backFramework.common.base.BaseService;
import cn.com.zygx.backFramework.common.base.DAOException;
import cn.com.zygx.backFramework.common.util.LoggerUtil;
import cn.com.zygx.backFramework.dao.IRoleDao;
import cn.com.zygx.backFramework.model.Resources;
import cn.com.zygx.backFramework.model.Role;
import cn.com.zygx.backFramework.model.RoleResources;
import cn.com.zygx.backFramework.model.UserRole;

@Service
public class RoleService implements BaseService<Role>{

	private static final String classDesc="角色表";
	
	@Autowired
	private IRoleDao RoleDao;

	
	@Override
	public Integer deleteWL(Integer id) {
		try {
			return this.RoleDao.deleteWL(id);
		} catch (Exception e) {
			LoggerUtil.info(RoleService.class, new StringBuffer().append(classDesc).append("物理删除异常").toString(), e);
			return Constants.DB_ERROR;
		}
		
	}

	@Override
	public Integer delete(Role entity) {
		try {
			return this.RoleDao.delete(entity);
		} catch (Exception e) {
			LoggerUtil.info(RoleService.class, new StringBuffer().append(classDesc).append("状态删除异常").toString(), e);
			return Constants.DB_ERROR;
		}
	}

	@Override
	public Integer insert(Role entity) {
		try {
			return this.RoleDao.insert(entity);
		} catch (Exception e) {
			LoggerUtil.info(RoleService.class, new StringBuffer().append(classDesc).append("插入异常").toString(), e);
			return Constants.DB_ERROR;
		}
	}
	
	@Override
	public Integer insertSelective(Role entity) {
		try {
			return this.RoleDao.insertSelective(entity);
		} catch (Exception e) {
			LoggerUtil.info(RoleService.class, new StringBuffer().append(classDesc).append("插入角色异常").toString(), e);
			return Constants.DB_ERROR;
		}
	}

	@Override
	public Integer update(Role entity) {
		try {
			return this.RoleDao.update(entity);
		} catch (Exception e) {
			LoggerUtil.info(RoleService.class, new StringBuffer().append(classDesc).append("更新异常").toString(), e);
			return Constants.DB_ERROR;
		}
	}

	@Override
	public Role selectByPrimaryKey(Integer id) {
		try {
			return (Role) this.RoleDao.selectByPrimaryKey(id);
		} catch (Exception e) {
			LoggerUtil.info(RoleService.class, new StringBuffer().append(classDesc).append("主键查询异常").toString(), e);
			return null;
		}
	}

	@Override
	public List<Role> query(Role entity) {
		try {
			return this.RoleDao.query(entity);
		} catch (Exception e) {
			LoggerUtil.info(RoleService.class, new StringBuffer().append(classDesc).append("list查询异常").toString(), e);
			return null;
		}
	}

	@Override
	public Map<String, Object> queryPage(Role entity) {
		return null;
	}
	
	/**********************************************/
	public List<Role> listRole() {
		return this.RoleDao.listRole();
	}
	
	public List<Role> listByUseRole( int uid) {
		return this.RoleDao.listByUseRole(uid);
	}
	
	public UserRole loadUserRole(int uid, int roleId) {
		return this.RoleDao.loadUserRole(uid, roleId);
	}
	
	public void addUserRole( int uid, int roleId) {
		this.RoleDao.addUserRole(uid, roleId);
	}
	
	public void deleteUserRole( int uid,int roleId) {
		this.RoleDao.deleteUserRole(uid, roleId);
	}
	
	public void deleteUserAllRole(int uid) {
		this.RoleDao.deleteUserAllRole(uid);
	}
	
	public List<Resources> listRoleResources(int roleId) {
		return this.RoleDao.listRoleResources(roleId);
	}
	
	public void addRoleResource( int roleId, int resId) {
		this.RoleDao.addRoleResource(roleId, resId);
	}
	
	public void deleteRoleResource(int roleId,int resId) {
		this.RoleDao.deleteRoleResource(roleId, resId);
	}
	
	public RoleResources loadResourceRole(int roleId,int resId) {
		return this.RoleDao.loadResourceRole(roleId, resId);
	}

	
	//更新角色资源事务
	@Transactional(rollbackFor=DAOException.class)
	public void updateRes(String roleId,List<Integer> selRoles,List<Integer> oldRoles) throws DAOException{
		try {
			/*
			 * 更新角色资源信息
			 * old: A B C
			 * sel: A B | A C D | A B C | A B C D
			 */
			List<Integer> deleteEle=Lists.newArrayList();
			List<Integer> addEle=Lists.newArrayList();
			deleteEle.addAll(oldRoles);
			addEle.addAll(selRoles);
			deleteEle.removeAll(selRoles);
			addEle.removeAll(oldRoles);
			System.out.println("删除的资源："+deleteEle);
			System.out.println("添加的资源："+addEle);
			for(Integer del_rid : deleteEle){
				RoleDao.deleteRoleResource(Integer.valueOf(roleId), del_rid);
			}
			for(Integer add_rid : addEle){
				RoleDao.addRoleResource(Integer.valueOf(roleId), add_rid);
			}
		} catch (Exception e) {
			LoggerUtil.info(RoleService.class, new StringBuffer().append(classDesc).append("更新角色资源异常").toString(), e);
			throw new DAOException(e);
		}
	}

	
}
