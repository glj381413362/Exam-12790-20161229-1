package com.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

//@Component
//@Aspect
public class RunningTimeAspect {
//	@Pointcut("execution(* com.service.impl.*.*(..))")
	public void runningTimeJointPointExpresion(){};
	
//	@Before("runningTimeJointPointExpresion()")
	public void beforeMethod(JoinPoint joinPoint){
		String mName = joinPoint.getSignature().getName();
//		System.out.println(mName+" start");
	}
//	@After("runningTimeJointPointExpresion()")
	public void afterMethod(){
		
	}
	public void afterReturnMethod(JoinPoint joinPoint , Object result) {
		String mName = joinPoint.getSignature().getName();
//		System.out.println(result);
	}
	
	public Object  aroundMethod(ProceedingJoinPoint proceedingJoinPoint){
		Object res =null;
		long begin =0;
		try {
			//相当于前置通知
			 begin = System.nanoTime();
			//执行目标方法 并返回结果
			res = proceedingJoinPoint.proceed();
			//相当于返回通知
		} catch (Throwable e) {
			//相当于异常通知
			e.printStackTrace();
		}   
		//相当于后置通知
        long end = System.nanoTime(); 
        System.out.println(proceedingJoinPoint.getSignature().getName()+"()方法运行了 " + (end-begin)/1000000+"ss"); 
        return res;
	}
}
