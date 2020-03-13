package com.mytest.mybatisreadwriteway2.common.mybatis;

import com.mytest.mybatisreadwriteway2.common.base.DbTypeHolder;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通过拦截器实现读写分离，
 * 这种通过插件的实现方法不能做更灵活的控制了，
 * 比如在特定的DAO方法上或者Service方法上通过注解指定走读库或者写库
 * 如果想要更灵活控制，可以再一层AOP，在AOP逻辑中标记应该走主库还是从库，然后保存
 * 在ThreadLocal中，在插件逻辑中再根据优先级来抉择出最终走哪个库
 */
@Intercepts({
        @Signature(type = Executor.class,
                method = "query",
                args = {
                        MappedStatement.class,
                        Object.class,
                        RowBounds.class,
                        ResultHandler.class
                }
        ),
        @Signature(type = Executor.class,
                method = "update",
                args = {
                        MappedStatement.class,
                        Object.class
                })
})
public class DataSourceToggleInterceptor implements Interceptor {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceToggleInterceptor.class);
    private static final String DB_TYPE_MASTER = "master";
    private static final String DB_TYPE_SLAVE = "salve";
    private static final String REGEX = ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";
    private static final Map<String, String> cacheMap = new ConcurrentHashMap<>();

    /**
     * 拦截器方法执行：进行读库和写库的切换
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        logger.info("DataSourceToggleInterceptor start....");
        boolean synchronizationActive = TransactionSynchronizationManager.isSynchronizationActive();
        if (synchronizationActive) {
            //如果事务开启，走主库
            logger.info("事务开启，走写库");
            DbTypeHolder.master();
        } else {
            Object[] objects = invocation.getArgs();
            //sql的ID，mapper文件中的id,可以理解为方法名
            MappedStatement ms = (MappedStatement) objects[0];
            String dbType = cacheMap.get(ms.getId());
            if (dbType == null) {
                //如果是查询语句
                if (ms.getSqlCommandType().equals(SqlCommandType.SELECT)) {
                    //!selectKey 为自增id查询主键(SELECT LAST_INSERT_ID() )方法，使用主库
                    if (ms.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
                        dbType = DB_TYPE_MASTER;
                    } else {
                        BoundSql boundSql = ms.getSqlSource().getBoundSql(objects[1]);
                        String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("[\\t\\n\\r]", " ");
                        //如果是insert或update或delete语句，则走写库
                        if (sql.matches(REGEX)) {
                            dbType = DB_TYPE_MASTER;
                        } else {
                            dbType = DB_TYPE_SLAVE;
                        }
                    }
                } else {
                    dbType = DB_TYPE_MASTER;
                }
                cacheMap.put(ms.getId(), dbType);
            }
            logger.info("当前读写类型为：" + dbType);
            if (DB_TYPE_MASTER.equals(dbType)) {
                DbTypeHolder.master();
            } else {
                DbTypeHolder.slave();
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }
}
