package www.ccyblog.novel.common.Interceptor;

import lombok.Singular;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by isghost on 2017/8/25.
 */
@Intercepts({
        @Signature(type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class,
                        CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
@Log4j
public class LogStatementInterceptor implements Interceptor{

    Properties properties = null;
    public Object intercept(Invocation invocation) throws Throwable {
        log.error("intercept intercept");
        Object obj = invocation.proceed();
        return obj;
    }

    public Object plugin(Object o) {
        log.error("intercept plugin");
        return Plugin.wrap(o, this);
    }

    public void setProperties(Properties properties) {
        log.error("intercept setProerties");
        this.properties = properties;
    }
}
