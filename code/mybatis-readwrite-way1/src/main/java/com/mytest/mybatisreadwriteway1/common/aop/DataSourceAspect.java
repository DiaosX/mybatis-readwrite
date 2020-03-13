package com.mytest.mybatisreadwriteway1.common.aop;

import com.mytest.mybatisreadwriteway1.common.base.DbTypeHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 缺点：类内部方法通过this.xx()方式相互调用时，aop不会进行拦截，需进行特殊处理。
 */

@Aspect
@Component
public class DataSourceAspect implements Ordered {
    @Pointcut("!@annotation(com.mytest.mybatisreadwriteway1.common.annotation.Master) " +
            "&& (execution(* com.mytest.mybatisreadwriteway1.service..*.select*(..)) " +
            "|| execution(* com.mytest.mybatisreadwriteway1.service..*.query*(..)) " +
            "|| execution(* com.mytest.mybatisreadwriteway1.service..*.find*(..)) " +
            "|| execution(* com.mytest.mybatisreadwriteway1.service..*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.mytest.mybatisreadwriteway1.common.annotation.Master) " +
            "|| execution(* com.mytest.mybatisreadwriteway1.service..*.insert*(..)) " +
            "|| execution(* com.mytest.mybatisreadwriteway1.service..*.add*(..)) " +
            "|| execution(* com.mytest.mybatisreadwriteway1.service..*.update*(..)) " +
            "|| execution(* com.mytest.mybatisreadwriteway1.service..*.edit*(..)) " +
            "|| execution(* com.mytest.mybatisreadwriteway1.service..*.delete*(..)) " +
            "|| execution(* com.mytest.mybatisreadwriteway1.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DbTypeHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DbTypeHolder.master();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
