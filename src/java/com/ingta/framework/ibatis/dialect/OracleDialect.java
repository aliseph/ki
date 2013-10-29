package com.ingta.framework.ibatis.dialect;

/**
 * @author aliseph
 */
public class OracleDialect implements Dialect {

    @Override
    public String getLimitString(String sql, boolean hasOffset) {
        return new StringBuilder(sql.length() + 120)
                .append(hasOffset ? "select * from ( select row_.*, rownum rownum_ from ( "
                : "select * from ( ")
                .append(sql)
                .append(hasOffset ? " ) row_ ) where rownum_ <=? and rownum_ > ?"
                : " ) where rownum <= ?").toString();
    }

    @Override
    public String getLimitString(String sql, int skipResults, int maxResults) {
        StringBuilder sb = new StringBuilder(sql.length() + 120);
        if (skipResults > 0) {
            sb.append("select * from ( select row_.*, rownum rownum_ from ( ").append(sql);
        } else {
            sb.append("select * from ( ").append(sql);
        }
        if (skipResults > 0) {
            sb.append(" ) row_ ) where rownum_ <=").append(skipResults + maxResults)
                    .append(" and rownum_ > ").append(skipResults);
        } else {
            sb.append(" ) where rownum <= ").append(maxResults);
        }
        return sb.toString();
    }

    @Override
    public boolean supportsLimit() {
        return true;
    }
}
