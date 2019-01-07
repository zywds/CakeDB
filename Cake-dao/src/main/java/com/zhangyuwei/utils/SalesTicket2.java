package com.zhangyuwei.utils;


import com.zhangyuwei.cake.entities.CakeInformation;
import com.zhangyuwei.cake.entities.OrderingDesc;
import com.zhangyuwei.cake.entities.Ording;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


//实现Printable接口 用于创建打印内容
public class SalesTicket2 implements Printable {
	private ArrayList<OrderingDesc> list;
	private String cashier;// 收银员编号
	private Font font;//字体
	private int sale_num;// 商品总数
	private BigDecimal sale_sum;// 总金额
	private BigDecimal practical;// 实收
	private BigDecimal changes;// 找零
	private String orders;//订单号
	private String rName;//客户姓名
	private String rPhone;//客户电话号码
	private String rAddress;//客户地址
	private Date oPTime;//配送时间

	// 构造函数
	public SalesTicket2(ArrayList<OrderingDesc> list, String cashier, String orders, int sale_num, BigDecimal sale_sum,
						BigDecimal practical, BigDecimal changes,String rName,String rPhone,String rAddress,Date oPTime) {
		this.list = list;
		// 收银员编号
		this.cashier = cashier;
		// 订单标号
		this.orders = orders;
		// 商品总数
		this.sale_num = sale_num;
		// 总金额
		this.sale_sum = sale_sum;
		// 实收
		this.practical = practical;
		// 找零
		this.changes = changes;
		//客户姓名
		this.rName=rName;
		//客户电话号码
		this.rPhone=rPhone;
		//客户地址
		this.rAddress=rAddress;
		//配送时间
		this.oPTime=oPTime;
	}
 
	/**
	 *  Graphic指明打印的图形环境
	 * PageFormat指明打印页格式（页面大小以点为计量单位，1点为1英才的1/72，1英寸为25.4毫米。A4纸大致为595×
	 *            842点）
	 * pageIndex指明页号
	 **/
	/*@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		Component c = null;
		// 转换成Graphics2D 拿到画笔
		Graphics2D g2 = (Graphics2D) graphics;
		// 设置打印颜色为黑色
		g2.setColor(Color.black);
 
		// 打印起点坐标
		double x = pageFormat.getImageableX();
		double y = pageFormat.getImageableY();
 
		// 虚线
		float[] dash1 = { 4.0f };
		// width - 此 BasicStroke 的宽度。此宽度必须大于或等于 0.0f。如果将宽度设置为
		// 0.0f，则将笔划呈现为可用于目标设备和抗锯齿提示设置的最细线条。
		// cap - BasicStroke 端点的装饰
		// join - 应用在路径线段交汇处的装饰
		// miterlimit - 斜接处的剪裁限制。miterlimit 必须大于或等于 1.0f。
		// dash - 表示虚线模式的数组
		// dash_phase - 开始虚线模式的偏移量
 
		// 设置画虚线
		g2.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 4.0f, dash1, 0.0f));
 
		// 设置打印字体（字体名称、样式和点大小）（字体名称可以是物理或者逻辑名称）
		font = new Font("宋体", Font.PLAIN, 11);
		g2.setFont(font);// 设置字体
		float heigth = font.getSize2D();// 字体高度
		// 标题
		g2.drawString("-----21Cake后台详细订单-----", (float) x, (float) y + heigth);
		float line = 2 * heigth;
 
		font = new Font("宋体", Font.PLAIN, 8);
		g2.setFont(font);// 设置字体
		heigth = font.getSize2D();// 字体高度
 
		// 显示收银员
		g2.drawString("收银员:" + cashier, (float) x, (float) y + line);
		// 显示订单号
		g2.drawString("订单号:" + orders, (float) x + 100, (float) y + line);

		line += heigth;
		// 显示标题
		g2.drawString("名称", (float) x + 20, (float) y + line);
		g2.drawString("单价", (float) x + 60, (float) y + line);
		g2.drawString("磅数", (float) x + 85, (float) y + line);
		g2.drawString("商品数量", (float) x + 115, (float) y + line);
		g2.drawString("餐具数量", (float) x + 115, (float) y + line);
		g2.drawString("总价", (float) x + 115, (float) y + line);
		line += heigth;
		g2.drawLine((int) x, (int) (y + line), (int) x + 158, (int) (y + line));

		// 第4行
		line += heigth;
 
		// 显示内容
		for (int i = 0; i < list.size(); i++) {
 
			OrderingDesc commodity = list.get(i);
			g2.drawString(commodity.getCakeInformation().getcName(), (float) x + 30, (float) y + line);
			g2.drawString(commodity.getOdPrice().toString(), (float) x + 60, (float) y + line);
			g2.drawString(commodity.getOdPoundage()+"", (float) x + 90, (float) y + line);
			g2.drawString(commodity.getOdNumber()+"", (float) x + 120, (float) y + line);
			g2.drawString(commodity.getOdTableWare()+"", (float) x + 150, (float) y + line);
			g2.drawString(commodity.getOdSumPrice().toString()+"", (float) x + 180, (float) y + line);
			line += heigth;
 
		}
		line += heigth;
 
		g2.drawLine((int) x, (int) (y + line), (int) x + 158, (int) (y + line));
		line += heigth;
 
		g2.drawString("售出商品数:" + sale_num + "件", (float) x, (float) y + line);
		g2.drawString("合计:" + sale_sum + "元", (float) x + 70, (float) y + line);
		line += heigth;
		g2.drawString("实收:" + practical + "元", (float) x, (float) y + line);
		g2.drawString("找零:" + changes + "元", (float) x + 70, (float) y + line);
		line += heigth;
		g2.drawString("客户姓名:" + rName + "元", (float) x, (float) y + line);
		g2.drawString("客户电话:" + rPhone + "元", (float) x + 70, (float) y + line);
		line += heigth;
		g2.drawString("客户地址:" + rAddress + "元", (float) x, (float) y + line);
		g2.drawString("配送时间:" + oPTime + "元", (float) x + 70, (float) y + line);
		line += heigth;
		g2.drawString("时间:" + Calendar.getInstance().getTime().toLocaleString(), (float) x, (float) y + line);

		line += heigth;
		g2.drawString("天天平价,日日新鲜", (float) x + 20, (float) y + line);
 
		line += heigth;
		g2.drawString("钱票请当面点清，离开柜台恕不负责", (float) x, (float) y + line);
		switch (pageIndex) {
		case 0:
 
			return PAGE_EXISTS;
		default:
			return NO_SUCH_PAGE;
 
		}
 
	}*/

	public static void main(String[] args) {
		//获取打印服务对象
		/*PrinterJob job = PrinterJob.getPrinterJob();
		PageFormat pageFormat = job.defaultPage();//得到默认页格式
		ArrayList<OrderingDesc> arrayList=new ArrayList<OrderingDesc>();
		arrayList.add(new OrderingDesc());
		arrayList.add(new OrderingDesc(1,"2",1,1,"3",2,2,2,new Ording(),new CakeInformation()));
		arrayList.add(new OrderingDesc(1,"2",1,1,"3",2,2,2,new Ording(),new CakeInformation()));
		job.setPrintable(new SalesTicket2(arrayList,"2","5","6","7","9","2","3","5","6","7"));//设置打印类
		try {
			//可以用printDialog显示打印对话框，在用户确认后打印；也可以直接打印
			boolean a=job.printDialog();
			if(a)
			{
				job.print();
			} else{
				job.cancel();
			}
		} catch (PrinterException e) {
			e.printStackTrace();
		}*/
	}

	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		Component c = null;
		// 转换成Graphics2D 拿到画笔
		Graphics2D g2 = (Graphics2D) graphics;
		// 设置打印颜色为黑色
		g2.setColor(Color.black);

		// 打印起点坐标
		double x = pageFormat.getImageableX();
		double y = pageFormat.getImageableY();

		// 虚线
		float[] dash1 = { 4.0f };
		// width - 此 BasicStroke 的宽度。此宽度必须大于或等于 0.0f。如果将宽度设置为
		// 0.0f，则将笔划呈现为可用于目标设备和抗锯齿提示设置的最细线条。
		// cap - BasicStroke 端点的装饰
		// join - 应用在路径线段交汇处的装饰
		// miterlimit - 斜接处的剪裁限制。miterlimit 必须大于或等于 1.0f。
		// dash - 表示虚线模式的数组
		// dash_phase - 开始虚线模式的偏移量

		// 设置画虚线
		g2.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 4.0f, dash1, 0.0f));

		// 设置打印字体（字体名称、样式和点大小）（字体名称可以是物理或者逻辑名称）
		font = new Font("宋体", Font.PLAIN, 11);
		g2.setFont(font);// 设置字体
		float heigth = font.getSize2D();// 字体高度
		// 标题
		g2.drawString("-----21Cake后台详细订单-----", (float) x+220, (float) y + heigth);
		float line = 3 * heigth;

		font = new Font("宋体", Font.PLAIN, 8);
		g2.setFont(font);// 设置字体
		heigth = font.getSize2D();// 字体高度

		// 显示收银员
		g2.drawString("收银员:" + cashier, (float) x+140, (float) y + line);
		// 显示订单号
		g2.drawString("订单号:" + orders, (float) x + 350, (float) y + line);

		line += heigth*3;
		// 显示标题
		g2.drawString("名称", (float) x + 110, (float) y + line);
		g2.drawString("单价", (float) x + 190, (float) y + line);
		g2.drawString("磅数", (float) x + 240, (float) y + line);
		g2.drawString("商品数量", (float) x + 290, (float) y + line);
		g2.drawString("餐具数量", (float) x + 340, (float) y + line);
		g2.drawString("总价", (float) x + 390, (float) y + line);
		line += heigth;
		g2.drawLine((int) x+50, (int) (y + line), (int) x + 520, (int) (y + line));

		// 第4行
		line += heigth*2;

		// 显示内容
		for (int i = 0; i < list.size(); i++) {

			OrderingDesc commodity = list.get(i);
			g2.drawString(commodity.getCakeInformation().getcName(), (float) x + 110, (float) y + line);
			g2.drawString(commodity.getOdPrice().toString(), (float) x + 190, (float) y + line);
			g2.drawString(commodity.getOdPoundage()+"", (float) x + 240, (float) y + line);
			g2.drawString(commodity.getOdNumber()+"", (float) x + 290, (float) y + line);
			g2.drawString(commodity.getOdTableWare()+"", (float) x + 340, (float) y + line);
			g2.drawString(commodity.getOdSumPrice().toString()+"", (float) x + 390, (float) y + line);
			line += heigth*2;

		}
		line += heigth;
		g2.drawLine((int) x+50, (int) (y + line), (int) x + 520, (int) (y + line));
		line += heigth*2;
		g2.drawString("售出商品数:" + sale_num + "件", (float) x+140, (float) y + line);
		g2.drawString("合计:" + sale_sum + "元", (float) x + 350, (float) y + line);
		line += heigth*2;
		g2.drawString("实收:" + practical + "元", (float) x+140, (float) y + line);
		g2.drawString("找零:" + changes + "元", (float) x + 350, (float) y + line);
		line += heigth*2;
		g2.drawString("客户姓名:" + rName, (float) x+140, (float) y + line);
		g2.drawString("客户电话:" + rPhone, (float) x + 350, (float) y + line);
		line += heigth*2;
		g2.drawString("时间:" + Calendar.getInstance().getTime().toLocaleString(), (float) x+140, (float) y + line);
		g2.drawString("配送时间:" + oPTime, (float) x + 350, (float) y + line);
		line += heigth*2;
		g2.drawString("客户地址:" + rAddress, (float) x+200, (float) y + line);
		line += heigth*2;
		/*g2.drawString("天天平价,日日新鲜", (float) x + 270, (float) y + line);
		line += heigth*2;
		g2.drawString("钱票请当面点清，离开柜台恕不负责", (float) x+230, (float) y + line);*/
		switch (pageIndex) {
			case 0:

				return PAGE_EXISTS;
			default:
				return NO_SUCH_PAGE;

		}
	}
}