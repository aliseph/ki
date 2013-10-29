package com.ingta.framework.ibatis.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页实例类
 *
 * @author kaixin
 * @param <T>
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 5702944212260096740L;
    private List<T> data;// 封装分页后的数据。
    private int numPerPage;// 每页所包括的数据量
    private int pageNum;// 当前页数
    private long count;// 数据总数
    private int pages;// 总页数
    private SQLParams params;// 数据库查询参数

    public Page() {
        this.numPerPage = SQLConstant.numPerPage;// 默认的每页所包括的数据量
    }

    public Page(long count, SQLParams params, int numPerPage, int pageNum) {
        this.numPerPage = numPerPage;
        this.count = count;
        this.params = params;
        this.pageNum = pageNum == 0 ? 1 : pageNum;// 初始化时当前所在页默认为1
        this.pages = countPages(count, numPerPage);// 初始化计算出总页数
    }

    public Page(long count, SQLParams params) {
        this.numPerPage = SQLConstant.numPerPage;// 默认的每页所包括的数据量
        this.count = count;
        this.params = params;
        this.pageNum = 1;// 初始化时当前所在页默认为1
        this.pages = countPages(count, numPerPage);// 初始化计算出总页数
    }

    public Page(long count) {
        this.numPerPage = SQLConstant.numPerPage;// 默认的每页所包括的数据量
        this.count = count;
        this.pageNum = 1;// 初始化时当前所在页默认为1
        this.pages = countPages(count, numPerPage);// 初始化计算出总页数
    }

    /**
     * 把page对象组装为一个map对象
     *
     * @return map对象
     */
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("numPerPage", numPerPage);
        map.put("pageNum", pageNum);
        map.put("count", count);
        map.put("pages", pages);
        map.put("data", data);
        if (params != null) {
            map.put("role", params.getRole());
            map.put("permission", params.getPermission());
            map.putAll(params.getOtherParams());
            map.put("startTime", params.getStartTime());
            map.put("endTime", params.getEndTime());
        }
        return map;
    }

    /**
     * 把page对象组装为一个map对象
     *
     * @param dataName data对象的key
     * @return map对象
     */
    public Map<String, Object> toMap(String dataName) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("numPerPage", numPerPage);
        map.put("pageNum", pageNum);
        map.put("count", count);
        map.put("pages", pages);
        map.put(dataName, data);
        if (params != null) {
            map.put("role", params.getRole());
            map.put("permission", params.getPermission());
            map.putAll(params.getOtherParams());
            map.put("startTime", params.getStartTime());
            map.put("endTime", params.getEndTime());
        }
        return map;
    }

    /**
     * 通过数据总数(totalCOunt)与每页所包括的数据量(pageSize)计算总页数(pages)
     *
     * @return 总页数
     */
    private int countPages(long count, int numPerPage) {
        if (count == 0) {
            return 1;
        }
        if (count % numPerPage == 0) {
            return (int) count / numPerPage;
        } else {
            return (int) count / numPerPage + 1;
        }
    }

    /**
     * 获取分布查询时要跳过的条数
     */
    public int getSkipResults() {
        return (pageNum - 1) * numPerPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public SQLParams getParams() {
        return params;
    }

    public void setParams(SQLParams params) {
        this.params = params;
    }
}
