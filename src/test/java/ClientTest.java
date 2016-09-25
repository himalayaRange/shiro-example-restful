import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import cn.com.zygx.backFramework.common.Constants;
import cn.com.zygx.backFramework.restful.client.ClientErrorHandler;
import cn.com.zygx.backFramework.restful.codec.HmacSHA256Utils;


public class ClientTest {

	private static Server server;
	
	private static RestTemplate restTemplate = new RestTemplate();
	
	 
	@BeforeClass
    public static void beforeClass() throws Exception {
		restTemplate.setErrorHandler(new ClientErrorHandler());
        //创建一个server
        server = new Server(8080);
        WebAppContext context = new WebAppContext();
        String webapp = "src/main/webapp";
        context.setDescriptor(webapp + "/WEB-INF/web.xml");  //指定web.xml配置文件
        context.setResourceBase(webapp);  //指定webapp目录
        context.setContextPath("/");
        context.setParentLoaderPriority(true);

        server.setHandler(context);
        server.start();
    }
	 
	@SuppressWarnings("rawtypes")
	@Test
    public void testServiceHelloSuccess() {
        String username = "wy";
        String param11 = "param11";
        String param12 = "param12";
        String param2 = "param2";
        String key = Constants.SECURITY_KEY;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add(Constants.PARAM_USERNAME, username);
        params.add("param1", param11);
        params.add("param1", param12);
        params.add("param2", param2);
        params.add(Constants.PARAM_DIGEST, HmacSHA256Utils.digest(key, params));

        String url = UriComponentsBuilder
	                .fromHttpUrl("http://localhost:8080/hello")
	                .queryParams(params).build().toUriString();
        try {
        	 ResponseEntity responseEntity = restTemplate.getForEntity(url, String.class);
             System.out.println(responseEntity.getBody());
		} catch (Exception e) {
			 System.out.println("请求数据失败：错误消息："+e.getMessage());
		}
       
    }
	
    @AfterClass
    public static void afterClass() throws Exception {
        server.stop(); //当测试结束时停止服务器
    }
}
