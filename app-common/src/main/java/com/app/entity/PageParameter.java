package com.app.entity;

import com.app.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.min;


@SuppressWarnings({"rawtypes", "serial"})
public class PageParameter extends HashMap<String, Object> implements Serializable {
    private int DEF_PAGE_VIEW_SIZE = 10;

    /**
     * 当前页
     */
    private int currPage;
    /**
     * 当前页显示记录条数
     */
    private int pageSize;
    /**
     * 取得查询总记录数
     */
    private int totalCount;
    private int pageCount;

    private String sortBy = "1 asc";
    private String condition;
    private int dataStart;

    private List<Property> properties;

    private int start;

    public PageParameter(HttpServletRequest request) {
        String param = request.getParameter("param");
        System.out.println(param);
    }


    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public int getDataStart() {
        return dataStart;
    }

    public void setDataStart(int dataStart) {
        this.dataStart = dataStart;
    }

    public void setSort(String column, String sortType) {
        if (!("".equals(sortBy) || sortBy.length() == 0 || sortBy == null)) {
            this.sortBy = column + " " + sortType;
            super.put("sort", sortBy);
        }

    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getSort() {
        return this.sortBy;
    }


    public String getCondition() {
        return condition;
    }


    public void setCondition(String condition) {
        this.condition = condition.trim();
        if (StringUtils.isEmpty(condition)) {
            return;
        }
        super.put("where", condition);

    }


    public int getTotalCount() {
        return totalCount;
    }


    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * 动作类型
     * <li>0：无动作</li>
     * <li>1：首页</li>
     * <li>2：前一页</li>
     * <li>3：后一页</li>
     * <li>4：末页</li>
     * <li>5：跳转页</li>
     * <li>6：重新设定每页记录数</li>
     */
    private int actionType;

    List records = Collections.emptyList();


    /**
     * (空)
     */
    public PageParameter() {
    }

    /**
     * 根据当前显示页与每页显示记录数设置查询信息初始对象
     *
     * @param page     当前显示页号
     * @param pageSize 当前页显示记录条数
     */
    public PageParameter(int page, int pageSize) {
        this.currPage = (page <= 0) ? 1 : page;
        this.pageSize = (pageSize <= 0) ? DEF_PAGE_VIEW_SIZE : pageSize;
    }
 
    /*public PageParameter(HttpServletRequest request) {
        String aoData = request.getParameter("aoData");
    			Map<String,Object> requestMap = this.jsonToMap(aoData,this);
    			this.putAll(requestMap);
    			this.setCondition("1=1");
    			this.setSort("", "1 asc");
    			Set set =requestMap.keySet();
    			
    			for(Iterator iterator=set.iterator();iterator.hasNext();){

    				String key = (String)iterator.next();
    				if(key.equals("iDisplayStart")){
    					this.dataStart=(int) requestMap.get(key)+1;
    				}  
    				if(key.equals("iDisplayLength")){
    					this.pageSize=(int) requestMap.get(key);
    				 	}
    				
    			}
    }*/

    /**
     * 取得动作类型
     *
     * @return 动作类型
     */
    public int getActionType() {
        return actionType;
    }

    /**
     * 设置动作类型
     *
     * @param actionType 动作类型
     */

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    /**
     * 取得当前显示页号
     *
     * @return 当前显示页号
     */
    public int getCurrPage() {
        return (currPage <= 0) ? 1 : currPage;
    }

    /**
     * 设置当前页
     *
     * @param page 当前页
     */
    public void setCurrPage(int page) {
        this.currPage = page;
    }

    /**
     * 取得当前显示页号最多显示条数
     *
     * @return 当前显示页号最多显示条数
     */
    public int getPageSize() {
        return (pageSize <= 0) ? DEF_PAGE_VIEW_SIZE : pageSize;
    }

    /**
     * 设置当前页显示记录条数
     *
     * @param pageSize 当前页显示记录条数
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 取得查询取得记录总数
     *
     * @return 取得查询取得记录总数
     */
    public int getCount() {
        return totalCount;
    }

    /**
     * 设置查询取得记录总数
     *
     * @param count 查询取得记录总数
     */
    public void setCount(int count) {
        this.totalCount = (count < 0) ? 0 : count;
        if (this.totalCount == 0) {
            this.currPage = 0;
            return;
        }
        switch (actionType) {
            case 1: //第一页
                this.currPage = 1;
                break;
            case 2: //前一页
                this.currPage = min(getPages(), this.currPage - 1);
                break;
            case 3: //后一页
                this.currPage = min(getPages(), this.currPage + 1);
                break;
            case 4: //最末页
                this.currPage = getPages();
                break;
            case 5: //指定页
            case 6: //重新设定每页显示条数时
            case 0: //无设定时
            default:
                this.currPage = min(getPages(), getCurrPage());
        }

    }

    /**
     * 取得当前查询总页数
     *
     * @return 当前查询总页数
     */
    public int getPages() {
        return (totalCount + getPageSize() - 1) / getPageSize();
    }

    /**
     * 取得起始显示记录号
     *
     * @return 起始显示记录号
     */
    public int getStartNo() {
        return ((getCurrPage() - 1) * getPageSize() + 1);
    }

    /**
     * 取得结束显示记录号
     *
     * @return 结束显示记录号
     */
    public int getEndNo() {
        return Math.min(getCurrPage() * getPageSize(), totalCount);
    }

    /**
     * 取得前一显示页码
     *
     * @return 前一显示页码
     */
    public int getPrePageNo() {
        return Math.max(getCurrPage() - 1, 1);
    }

    /**
     * 取得后一显示页码
     *
     * @return 后一显示页码
     */
    public int getNextPageNo() {
        return Math.min(getCurrPage() + 1, getPages());
    }

    public List getRecords() {
        return records;
    }

    public void setRecords(List records) {
        this.records = records;
    }

    // dwz 分页参数
    public void setPageNumber(int pageNumber) {
        setCurrPage(pageNumber);
    }
//	public PageParameter initPageparamater(String aoData,PageParameter pageParameter) {
//		//Map<String, String> pageProperty=null;
//		Map<String,Object> requestMap = this.jsonToMap(aoData);
//		Set set =requestMap.keySet();
//		
//		for(Iterator iterator=set.iterator();iterator.hasNext();){
//			if(pageProperty.containsKey("iDisplayStart")){
//				this.currPage=Integer.valueOf(pageProperty.get("iDisplayStart"));
//			}
//			if(pageProperty.containsKey("iDisplayStart")){
//				this.pageSize=Integer.valueOf(pageProperty.get("iDisplayLength"));
//			}
//			String key = (String)iterator.next();
//			if(key.equals("iDisplayStart")){
//				pageParameter.dataStart=(int) requestMap.get(key);
//	
//			}  
//			if(key.equals("iDisplayLength")){
//				pageParameter.pageSize=(int) requestMap.get(key);
//			} 
//	}
//		return pageParameter;
//	}
/*	public Map<String,Object> jsonToMap(String aoData,PageParameter pageParameter){
		Map<String,Object> map = new HashMap<String, Object>();
		JSONArray ja = (JSONArray) JSONArray.parse(aoData);
		List<Property>  properties=new ArrayList<Property>();
		for (int i = 0; i < ja.size(); i++) {
			JSONObject obj = (JSONObject) ja.get(i);
			String name=String.valueOf(obj.get("name"));
			if(name.startsWith("search_")){
				properties.add(setProperty(obj,name));
			}else {
				map.put(name, obj.get("value"));
			}
		}
		pageParameter.setProperties(properties);
		return map;
	}*/

    /*private Property setProperty(JSONObject obj,String name) {

        String type=name.split("_")[1].toString();
        String value=StringUtils.convertObjToString(obj.get("value"));
        String cType=name.split("_")[2].toString();
        //赋值属性
        return new Property(
                NamingConversionUtil.humpToLine2(name.split("_")[3]), type,
                value, cType);
    }*/

}