package cn.com.zygx.backFramework.restful.client;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import cn.com.zygx.backFramework.exception.ResourceNotFoundException;

/**
 * htttp/https请求资源异常处理
 * <p>User: wangyi
 * <p>Date: 2016-9-19
 * <p>Version: 1.0
 */
public class ClientErrorHandler implements ResponseErrorHandler {

	   @Override
	   public void handleError(ClientHttpResponse response) throws IOException 
	   {
	       if (response.getStatusCode() == HttpStatus.NOT_FOUND)
	       {
	           throw new ResourceNotFoundException("您访问的资源不存在！");
	       }
	       if (response.getStatusCode() == HttpStatus.FORBIDDEN)
	       {
	           throw new ResourceNotFoundException("拒绝访问！");
	       }
	       if (response.getStatusCode() == HttpStatus.UNAUTHORIZED)
	       {
	           throw new ResourceNotFoundException("权限不够！");
	       }
	       if (response.getStatusCode() == HttpStatus.METHOD_NOT_ALLOWED)
	       {
	           throw new ResourceNotFoundException("方法允许！");
	       }
	       if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR)
	       {
	           throw new ResourceNotFoundException("服务器不能处理的请求！");
	       }
	     
	       throw new RuntimeException("其他异常，异常码:"+response.getStatusCode());
	   }

	   @Override
	   public boolean hasError(ClientHttpResponse response) throws IOException 
	   {
	       if ( (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR)
	         || (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) )
	       {
	           return true;
	       }
	       return false;
	   }
	   
}
