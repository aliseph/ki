package com.ingta.framework.ibatis.dialect;

/**
 * @author aliseph
 *
 */
public class MySQLDialect implements Dialect {

    @Override
    public String getLimitString(String sql, boolean hasOffset) {
        return new StringBuilder(sql.length() + 20).append(sql)
                .append(hasOffset ? " limit ?,?" : " limit ?").toString();
    }

    @Override
    public String getLimitString(String sql, int skipResults, int maxResults) {
        StringBuilder sb = new StringBuilder(sql.length() + 20);
        sb.append(sql);
        if (skipResults > 0) {
            sb.append(" limit ").append(skipResults).append(',').append(maxResults);
        } else {
            sb.append(" limit ").append(maxResults);
        }
        return sb.toString();
    }

    @Override
    public boolean supportsLimit() {
        return true;
    }
}
