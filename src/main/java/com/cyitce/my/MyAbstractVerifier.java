package com.cyitce.my;

import apijson.MethodAccess;
import apijson.RequestMethod;
import apijson.orm.*;
import apijson.orm.model.Access;
import apijson.orm.model.Function;
import apijson.orm.model.Request;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

import static apijson.RequestMethod.*;
import static apijson.orm.Operation.*;
import static apijson.orm.Operation.REFUSE;

/**
 * 校验器 校验 请求参数、角色
 * @version V1.0
 * @author 杨帅 2gamayibuxulong
 * @date 2022/3/23
 */
public class MyAbstractVerifier<T> implements Verifier<T>, AbstractSQLConfig.IdCallback {

    private static final String TAG = "MyAbstractVerifier";
    /**未登录，不明身份的用户
     */
    public static final String UNKNOWN = "UNKNOWN";
    /**已登录的用户
     */
    public static final String LOGIN = "LOGIN";
    /**联系人，必须已登录
     */
    public static final String CONTACT = "CONTACT";
    /**圈子成员(CONTACT + OWNER)，必须已登录
     */
    public static final String CIRCLE = "CIRCLE";
    /**拥有者，必须已登录
     */
    public static final String OWNER = "OWNER";
    /**管理员，必须已登录
     */
    public static final String ADMIN = "ADMIN";

    public static final Map<String, Entry<String, Object>> ROLE_MAP;
    public static final List<String> OPERATION_KEY_LIST;
    public static final Map<String, Map<RequestMethod, String[]>> SYSTEM_ACCESS_MAP;
    public static final Map<String, Map<RequestMethod, String[]>> ACCESS_MAP;
    public static final Map<String, SortedMap<Integer, JSONObject>> REQUEST_MAP;

    //静态代码块 初始化所有内容
    static{
        //角色初始化 有哪些角色
        ROLE_MAP = new LinkedHashMap<>();
        ROLE_MAP.put(UNKNOWN, new Entry<String, Object>());
        ROLE_MAP.put(LOGIN, new Entry<String, Object>("userId>", 0));
        ROLE_MAP.put(CONTACT, new Entry<String, Object>("userId{}", "contactIdList"));
        ROLE_MAP.put(CIRCLE, new Entry<String, Object>("userId-()", "verifyCircle()")); // "userId{}", "circleIdList"));  // 还是 {"userId":"currentUserId", "userId{}": "contactIdList", "@combine": "userId,userId{}" } ?
        ROLE_MAP.put(OWNER, new Entry<String, Object>("userId", "userId"));
        ROLE_MAP.put(ADMIN, new Entry<String, Object>("userId-()", "verifyAdmin()"));

        //操作信息
        OPERATION_KEY_LIST = new ArrayList<>();
        OPERATION_KEY_LIST.add(TYPE.name());
        OPERATION_KEY_LIST.add(VERIFY.name());
        OPERATION_KEY_LIST.add(INSERT.name());
        OPERATION_KEY_LIST.add(UPDATE.name());
        OPERATION_KEY_LIST.add(REPLACE.name());
        OPERATION_KEY_LIST.add(EXIST.name());
        OPERATION_KEY_LIST.add(UNIQUE.name());
        OPERATION_KEY_LIST.add(REMOVE.name());
        OPERATION_KEY_LIST.add(MUST.name());
        OPERATION_KEY_LIST.add(REFUSE.name());

        //每个表能访问的权限
        SYSTEM_ACCESS_MAP = new HashMap<String, Map<RequestMethod, String[]>>();
        SYSTEM_ACCESS_MAP.put(Access.class.getSimpleName(), getAccessMap(Access.class.getAnnotation(MethodAccess.class)));
        SYSTEM_ACCESS_MAP.put(Function.class.getSimpleName(), getAccessMap(Function.class.getAnnotation(MethodAccess.class)));
        SYSTEM_ACCESS_MAP.put(Request.class.getSimpleName(), getAccessMap(Request.class.getAnnotation(MethodAccess.class)));


        ACCESS_MAP = new HashMap<>(SYSTEM_ACCESS_MAP);

        // 单个与批量增删改
        REQUEST_MAP = new HashMap<>(ACCESS_MAP.size()*6);
    }


    /**获取权限Map，每种操作都只允许对应的角色
     * @param access
     * @return
     */
    public static HashMap<RequestMethod, String[]> getAccessMap(MethodAccess access) {

        if (access == null) {
            return null;
        }
        HashMap<RequestMethod, String[]> map = new HashMap<>();
        map.put(GET, access.GET());
        map.put(HEAD, access.HEAD());
        map.put(GETS, access.GETS());
        map.put(HEADS, access.HEADS());
        map.put(POST, access.POST());
        map.put(PUT, access.PUT());
        map.put(DELETE, access.DELETE());

        return map;
    }


    @Override
    public Object newId(RequestMethod method, String database, String schema, String table) {
        return null;
    }

    @Override
    public String getIdKey(String database, String schema, String datasource, String table) {
        return null;
    }

    @Override
    public String getUserIdKey(String database, String schema, String datasource, String table) {
        return null;
    }

    @Override
    public boolean verifyAccess(SQLConfig config) throws Exception {
        return false;
    }

    @Override
    public void verifyRole(String table, RequestMethod method, String role) throws Exception {

    }

    @Override
    public void verifyLogin() throws Exception {

    }

    @Override
    public void verifyAdmin() throws Exception {

    }

    @Override
    public void verifyRepeat(String table, String key, Object value) throws Exception {

    }

    @Override
    public void verifyRepeat(String table, String key, Object value, long exceptId) throws Exception {

    }

    @Override
    public JSONObject verifyRequest(RequestMethod method, String name, JSONObject target, JSONObject request, int maxUpdateCount, String globleDatabase, String globleSchema, SQLCreator creator) throws Exception {
        return null;
    }

    @Override
    public JSONObject verifyResponse(RequestMethod method, String name, JSONObject target, JSONObject response, String database, String schema, SQLCreator creator, OnParseCallback callback) throws Exception {
        return null;
    }

    @Override
    public Parser<T> createParser() {
        return null;
    }

    @Override
    public Visitor<T> getVisitor() {
        return null;
    }

    @Override
    public Verifier<T> setVisitor(Visitor<T> visitor) {
        return null;
    }

    @Override
    public String getVisitorIdKey(SQLConfig config) {
        return config.getUserIdKey();
    }

}
