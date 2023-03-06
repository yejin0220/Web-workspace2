package com.kh.borard.model.vo;

public class ReplyBuilder {
	
	/*
	 * 빌더패턴
	 * - 자바에서 기존 객체생성방식의 단점을 보완해주는 새로운 객체 생성 디자인 패턴.
	 * - 생성자에 인자가 너무 많이 있을 대 고려해서 사용해보면됨
	 * (필드가 몇개 없는 간단한 생성자는 오히려 사용하지 않는 것을 권장)
	 * 
	 * 
	 * 기본 생성방식
	 *	1. 생성자를 이용한 객체 생성
	 *	   객체를 생성할 때 생성자에 매개변수로 데이터를 넣어줘서 객체를 생성하는 방식
	 *	   단점 1) 인자들이 많을 수록 생성자가 많아질 수 있다.
	 *	   	      ex)Member 클래스의 생성자로(로그인 했을때의 정보를 담을 수 있는 객체, 회원가입용 생정자, 로그인요청 등등)
	 *	   		  생성자는 딱 필요로 하는 데이터만 담고 있는게 좋으므로, 그에 맞는 생성자가 많이 필요해지게 된다.
	 *		   2). 매개변수의 정보를 설명할 수 없기 때문에 잘못된 위치에 데이터를 추가하게될 가능성이 있다.
	 *	   		  ex)email데이터가 추가되어야할 부분에 address가 추가된다던가 하는 등.  
	 *
	 * 2. setter함수를 이용한 객체 생성
	 * 	  생성자를 이용한 객체생성 방법보다 1번 단점이 보완되었다. 매개변수의 정보를 설명할 수 있기 때문에 가독성은 확보된다.
	 * 	  코드의 길이가 길어진다는 단점과 객체의 일관성이 깨질 수 있는 단점이 존재함
	 * 	  *객체의 일관성 : 한번 객체를 생성한 후 그 객체의 내부 데이터가 변경되지 않고 유지되는 상태.
	 * 
	 * 3. 빌더패턴을 이용한 객체 생성 방법 : 위 두가지 생성방식의 단점을 보완했다.
	 * 	  
	 * 	  장점1)불필요한 생성자 제거
	 * 	  장점2)데이터의순서와 상관없이 객체 생성 가능
	 * 	  장점3)명시적으로 선언하여 가독성이 좋고
	 * 	  장점4)각 인자가 어떤 데이터인지 확인할 수가 있음
	 * 	  장점)setter함수를 만들지 않으므로 변경불가능한 객체를 생성할 수 있다(객체의 일관성 유지)
	 * 
	 * 
	 * 
	 * 
	 */
	private int replyNo;
	private String replyContent;
	private int refBno;
	
	private ReplyBuilder(){
		super();
	}
	
	
	public static class Builder{
		private int replyNo; //필수로 받아야 하는 정보
		private String replyContent; //선택적으로 받아야 하는 정보
		private int refBno; //선택적으로 받아야 하는 정보
		
		//필수로 받아야 하는 정보는 생성자로 값을 전달해줌
		public Builder(int replyNo) {
			this.replyNo = replyNo;
		}
		
		//선택적으로 받아야 하는 빌더클래스의 필드값을 세터함수로 만듬(이때 반환값은 Builder객체로 반환)
		public Builder setReplyContent(String replyContent) {
			this.replyContent = replyContent;
			return this; //현재 객체를 반환함으로써 메소드 체이닝을 이용할 수 있게끔함
		}
		
		public Builder setRefBno(int refBno) {
			this.refBno = refBno;
			return this;
		}
		
		public ReplyBuilder build() {
			
			ReplyBuilder rb = new ReplyBuilder();
			rb.replyNo = this.replyNo;
			rb.replyContent = this.replyContent;
			rb.refBno = this.refBno;
			
			return rb;
		}
	}
	
	
	
	
	
}
