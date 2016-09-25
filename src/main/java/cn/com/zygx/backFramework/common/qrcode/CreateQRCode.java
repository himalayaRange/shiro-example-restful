package cn.com.zygx.backFramework.common.qrcode;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import com.google.common.collect.Maps;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 生成二维码
 * @author wangyi
   @date 2016-8-9
 *
 */
public class CreateQRCode {
	public static void main(String[] args) {
		int width=300;
		int height=300;
		String format="png";
		String content="www.imooc.com";
		
		//定义二维码参数
		HashMap<Object, Object> map=Maps.newHashMap();
		map.put(EncodeHintType.CHARACTER_SET, "utf-8"); //编码支持中文
		map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);//容错等级
		map.put(EncodeHintType.MARGIN, 2);//边距
		
		//生成二维码
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height);
			Path path=new File("D:/qrcode/qrcode.png").toPath();
			MatrixToImageWriter.writeToPath(bitMatrix, format, path);
		} catch (Exception e) {
		}
		
	}
}
