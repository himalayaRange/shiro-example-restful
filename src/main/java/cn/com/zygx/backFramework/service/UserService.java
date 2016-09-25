package cn.com.zygx.backFramework.service;

import java.util.List;
import java.util.Map;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.zygx.backFramework.common.Constants;
import cn.com.zygx.backFramework.common.base.BaseService;
import cn.com.zygx.backFramework.common.base.DAOException;
import cn.com.zygx.backFramework.common.util.LoggerUtil;
import cn.com.zygx.backFramework.dao.IRoleDao;
import cn.com.zygx.backFramework.dao.IUserDao;
import cn.com.zygx.backFramework.model.Resources;
import cn.com.zygx.backFramework.model.User;
import com.google.common.collect.Lists;

@Service
public class UserService implements BaseService<User>{

	private static final String classDesc="用户表";
	
	@Autowired
	private IUserDao userDao;

	@Autowired
	private IRoleDao iroleDao;
	
	@Autowired
	private PasswordHelper passwordHelper;
	
	@Override
	public Integer deleteWL(Integer id) {
		try {
			return this.userDao.deleteWL(id);
		} catch (Exception e) {
			LoggerUtil.info(UserService.class, new StringBuffer().append(classDesc).append("物理删除异常").toString(), e);
			return Constants.DB_ERROR;
		}
		
	}

	@Override
	public Integer delete(User entity) {
		try {
			return this.userDao.delete(entity);
		} catch (Exception e) {
			LoggerUtil.info(UserService.class, new StringBuffer().append(classDesc).append("状态删除异常").toString(), e);
			return Constants.DB_ERROR;
		}
	}

	/**
	 * 添加用户，对密码进行加密
	 */
	@Override
	public Integer insert(User user) {
		try {
			if(PasswordHelper.isEmpty(user.getUsername())||PasswordHelper.isEmpty(user.getPassword())){
				throw new RuntimeException("用户名或者密码不能为空！");
			}
			user.setPassword(passwordHelper.md5(user.getPassword(), user.getUsername()));
			return this.userDao.insert(user);
		} catch (Exception e) {
			LoggerUtil.info(UserService.class, new StringBuffer().append("用户注册异常").toString(), e);
			return Constants.DB_ERROR;
		}
	}
	
	@Override
	public Integer insertSelective(User entity) {
		try {
			return this.userDao.insertSelective(entity);
		} catch (Exception e) {
			LoggerUtil.info(UserService.class, new StringBuffer().append("插入用户异常").toString(), e);
			return Constants.DB_ERROR;
		}
	}

	@Override
	public Integer update(User entity) {
		try {
			return this.userDao.update(entity);
		} catch (Exception e) {
			LoggerUtil.info(UserService.class, new StringBuffer().append(classDesc).append("更新异常").toString(), e);
			return Constants.DB_ERROR;
		}
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		try {
			return (User) this.userDao.selectByPrimaryKey(id);
		} catch (Exception e) {
			LoggerUtil.info(UserService.class, new StringBuffer().append(classDesc).append("主键查询异常").toString(), e);
			return null;
		}
	}

	@Override
	public List<User> query(User entity) {
		try {
			return this.userDao.query(entity);
		} catch (Exception e) {
			LoggerUtil.info(UserService.class, new StringBuffer().append(classDesc).append("list查询异常").toString(), e);
			return null;
		}
	}

	@Override
	public Map<String, Object> queryPage(User entity) {
		return null;
	}
	

	/**************************************************/
	public List<User> listUser() {
		return this.userDao.listUser();
	}

	public List<User> listByRole(int id) {
		return this.userDao.listByRole(id);
	}
	
	
	public User loadByUsername(String username) {
		return this.userDao.loadByUsername(username);
	}
	
	/**
	 * 用户登录
	 */
	public User login(String username,String password) {
		User user=loadByUsername(username);
		if(user==null)throw new UnknownAccountException("用户名或密码出错！");
		if(!user.getPassword().equals(passwordHelper.md5(password, username)))throw new IncorrectCredentialsException("用户名或密码出错！");
		if(user.getStatus()==0)throw new LockedAccountException("用户账号已停用！");
		
		return user;
	}

	public List<Resources> listAllResources(int uid) {
		return this.userDao.listAllResources(uid);
	}
	
	/**
	 * 添加用户同时添加用户角色
	 * @param user
	 * @param rids
	 */
	public void add(User user,List<Integer> rids){
		insert(user);
		for(Integer rid:rids ){
			this.iroleDao.addUserRole(user.getId(), rid);
		}
	}

	
	public List<String> listRoleSNByUser(int uid){
		return this.userDao.listRoleSNByUser(uid);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor=DAOException.class)
	public void updateUserInfo(User user,List<Integer> selRoles,List<Integer> oldRoles) throws DAOException{
		try {
			//1.更新user信息
			userDao.update(user); 
			/*
			 * 2.更新角色信息
			 * old: A B C
			 * sel: A B | A C D | A B C | A B C D
			 */
			List<Integer> deleteEle=Lists.newArrayList();
			List<Integer> addEle=Lists.newArrayList();
			deleteEle.addAll(oldRoles);
			addEle.addAll(selRoles);
			deleteEle.removeAll(selRoles);
			addEle.removeAll(oldRoles);
			System.out.println("删除的角色："+deleteEle);
			System.out.println("添加的角色："+addEle);
			for(Integer del_rid : deleteEle){
				iroleDao.deleteUserRole(user.getId(), del_rid);
			}
			for(Integer add_rid : addEle){
				iroleDao.addUserRole(user.getId(), add_rid);
			}
		} catch (Exception e) {
			LoggerUtil.info(UserService.class, new StringBuffer().append(classDesc).append("更新用户角色信息异常").toString(), e);
			throw new DAOException(e);
		}
	}

}
