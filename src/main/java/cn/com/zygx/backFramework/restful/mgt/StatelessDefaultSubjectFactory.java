package cn.com.zygx.backFramework.restful.mgt;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * 创建无状态的会话
 * <p>User: wangyi
 * <p>Date: 2016-9-20
 * <p>Version: 1.0
 */
public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {
 
	@Override
    public Subject createSubject(SubjectContext context) {
        //不创建session
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}
