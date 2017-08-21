package com.kjh.hojak.spring_1.annotation;

import org.springframework.stereotype.Component;


/**
 *	<bean id = "hyundai" class = "com.kjh.hojak.annotation.HyundaiMaker" />
 */
@Component ("hyundai")
public class HyundaiMaker implements CarMaker{
	
	@Override
	public Car sell(Money money) {
		System.out.println("HyundaiMaker.sell : I got " + money.getAmount());
		
		Car car = new Car("Sonata");
		System.out.println("HyundaiMaker.sell : I made " + car.getName());
		System.out.println("HyundaiMaker.sell : I sold " + car.getName());
		
		return car;
	}
}
