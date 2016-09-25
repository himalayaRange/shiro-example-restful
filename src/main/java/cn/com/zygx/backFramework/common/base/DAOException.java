package cn.com.zygx.backFramework.common.base;

public class DAOException  extends Exception{

	private static final long serialVersionUID = 1L;
	
	public DAOException(){
		
	}
	
	
	public DAOException(String message){
			super(message);
	}
	
	public DAOException(Exception e){
		super(new StringBuffer().append("DAO异常，异常信息:").append(e.getMessage()).toString());
}

}
