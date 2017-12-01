package com.sitv.base;

import java.util.HashMap;
import java.util.Map;

public class QueryHandler extends Base{
    public static final String COUNT_SQL = "select count(*) ";
    public static final String KEYWORD_FROM = " from ";
    public static final String KEYWORD_ORDER = " order by ";
    public static final String KEYWORD_GROUP = " group by ";
    boolean whereFlag = true;
    boolean updateFlag = true;
    
    boolean orderFlag = true;
    boolean groupFlag = true;
    private StringBuilder sqlBuilder;
    private Map<String, Object> map;
    private Map<String, Object[]> arrayMap;
    private Integer firstResult;
    private Integer maxResults;
    private Boolean cacheable;

    public QueryHandler(String sql) {
        this.sqlBuilder = new StringBuilder(" ");
        sqlBuilder.append(sql);
    }

    public QueryHandler() {
        this.sqlBuilder = new StringBuilder();
    }

    public QueryHandler qcondition(String condition) {

        if (whereFlag) {
            whereFlag = false;
            sqlBuilder.append(" where (");
        } else {
            sqlBuilder.append(" and (");
        }
        sqlBuilder.append(condition);
        return this;
    }
    
    public QueryHandler condition(String condition) {
        if (whereFlag) {
            whereFlag = false;
            sqlBuilder.append(" where ");
        } else {
            sqlBuilder.append(" and ");
        }
        sqlBuilder.append(condition);
        return this;
    }
    
    public QueryHandler updateCondition(String condition) {
        if (updateFlag) {
        	updateFlag = false;
            sqlBuilder.append(" set ");
        } else {
            sqlBuilder.append(" , ");
        }
        sqlBuilder.append(condition);
        return this;
    }
    
    public QueryHandler addCondition(String... conditions) {
    	sqlBuilder.append("(");
    	for(Object condition : conditions){
    		sqlBuilder.append(condition +",");
    	}
    	sqlBuilder.deleteCharAt(sqlBuilder.length()-1);
    	sqlBuilder.append(")");
        return this;
    }
    public QueryHandler OrCondition(String condition) {

        sqlBuilder.append(" or ");
        
        sqlBuilder.append(condition);
        return this;
    }

    public QueryHandler order(String sqlString) {
        if (orderFlag) {
            orderFlag = false;
            append(KEYWORD_ORDER);
        } else {
            sqlBuilder.append(',');
        }
        sqlBuilder.append(sqlString);
        return this;
    }

    public QueryHandler group(String sqlString) {
        if (groupFlag) {
            groupFlag = false;
            sqlBuilder.append(KEYWORD_GROUP);
        } else {
            sqlBuilder.append(',');
        }
        sqlBuilder.append(sqlString);
        return this;
    }

    public QueryHandler append(String sqlString) {
        sqlBuilder.append(" ");
        sqlBuilder.append(sqlString);
        return this;
    }

    public QueryHandler setFirstResult(int firstResult) {
        this.firstResult = firstResult;
        return this;
    }

    public QueryHandler setMaxResults(int maxResults) {
        this.maxResults = maxResults;
        return this;
    }

    public QueryHandler setCacheable(boolean cacheable) {
        this.cacheable = cacheable;
        return this;
    }

    public QueryHandler setParameter(Object value) {
    	sqlBuilder.append(value.toString());
        return this;
    }

    public QueryHandler setParameter(Object[] values) {
    	sqlBuilder.append("(");
    	for(Object value : values){
    		sqlBuilder.append(value +",");
    	}
    	sqlBuilder.deleteCharAt(sqlBuilder.length()-1);
    	sqlBuilder.append(")");
        return this;
    }
    
    public QueryHandler setChangeParameter(Object... values) {
    	sqlBuilder.append("VALUES (");
    	for(Object value : values){
    		sqlBuilder.append(value +",");
    	}
    	sqlBuilder.deleteCharAt(sqlBuilder.length()-1);
    	sqlBuilder.append(")");
        return this;
    }
    public String getSql() {
        return sqlBuilder.toString();
    }
    
    public void ConvertorSql(String sql) {
        sqlBuilder = new StringBuilder(sql);
    }
}
