package com.emi.client;

import java.util.Scanner;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import com.emi.controller.Controller;
import com.emi.vo.EmiVO;

public class RealTimeDI {

	static DefaultListableBeanFactory factory=null;
	static XmlBeanDefinitionReader reader=null;
	static Controller controller=null;

	static {
		System.out.println("Creation of IOC Container Processing");
		factory=new DefaultListableBeanFactory();
		reader=new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions("com/emi/cfgs/applicationContext.xml");
		System.out.println("IOC Container Created Successfully");
		controller=factory.getBean("controller",Controller.class);
	}

	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		String name=null,address=null,result=null,cid=null;
		String principle=null,rate=null,years=null;

		EmiVO vo=null;
		vo=new EmiVO();
		int i=0;

		System.out.println("\n"+ ++i +". New Entry");
		System.out.println(++i +". Search Details");
		System.out.print("\nEnter Option : ");
		i=sc.nextInt();sc.nextLine();

		try {
			if(i==1) {
				System.out.print("Enter Name	\t: ");
				name=sc.nextLine();
				System.out.print("Enter Address	\t: ");
				address=sc.nextLine();
				System.out.print("Enter Principle Amount\t: ");
				principle=sc.next();
				System.out.print("Enter Rate Of Interest\t: ");
				rate=sc.next();
				System.out.print("Enter Time in Years\t: ");
				years=sc.next();

				vo.setCname(name);
				vo.setCadd(address);
				vo.setPamt(principle);
				vo.setRoi(rate);
				vo.setYears(years);

				try {
					result = controller.processController(vo);
					System.out.println(result);
				} catch (Exception e) {
					System.out.println("Internal Problem Try Again");
					e.printStackTrace();
				}

			}else if(i==2) {
				System.out.print("Enter Customer ID	: ");
				cid=sc.next();
				vo.setCid(cid);

				try {
					result = controller.searchController(vo);
					System.out.println(result);
				} catch (Exception e) {
					System.out.println("Internal Problem Try Again");
					e.printStackTrace();
				}
			}
		}
		finally {
			sc.close();
			factory=null;
			reader=null;
			controller=null;
			vo=null;
			System.gc();	 
		}
	}@Override
	protected void finalize() throws Throwable {
		System.out.println("Garbage Collection is Performed by JVM");
	}
}

