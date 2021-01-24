package com.numeralasia.payment.model;

import lombok.Getter;
import lombok.Setter;

public class WSResponse<D, E> {

	@Getter
	@Setter
	int code ;
	@Getter@Setter D data ;
	@Getter@Setter String message ;
	@Getter@Setter E messageError ;
	@Getter long totalElement ;
	@Getter int totalPage ;
	@Getter int pageElement ;

	public static <D> WSResponse instance(int code, String message, D data){
		WSResponse instance = new WSResponse<>();
		instance.setCode(code);
		instance.setMessage(message);
		instance.setData(data);
		return instance ;
	}

	public static <D> WSResponse instance(int code, String message){
		WSResponse instance = new WSResponse<>();
		instance.setCode(code);
		instance.setMessage(message);
		return instance ;
	}

	public static <E> WSResponse instanceError(int code, String message, E messageError){
		WSResponse instance = new WSResponse<>();
		instance.setCode(code);
		instance.setMessage(message);
		instance.setMessageError(messageError);
		return instance ;
	}

	public static <D> WSResponse instance(int code, D data){
		WSResponse instance = new WSResponse<>();
		instance.setCode(code);
		instance.setData(data);
		return instance ;
	}

	public WSResponse setTotalElement(long totalElement) {
		this.totalElement = totalElement;
		return this ;
	}

	public WSResponse setTotalPage(int totalPage) {
		this.totalPage = totalPage;
		return this;
	}

	public WSResponse setPageElement(int pageElement) {
		this.pageElement = pageElement;
		return this ;
	}
}
