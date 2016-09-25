package cn.com.zygx.backFramework.dao;

import java.util.List;
import cn.com.zygx.backFramework.common.base.FrameworkDao;
import cn.com.zygx.backFramework.db.mybatis.MyBatisRepository;
import cn.com.zygx.backFramework.model.Resources;
@MyBatisRepository
public interface IResourcesDao extends FrameworkDao{

	public List<Resources> listResource();
	
}
