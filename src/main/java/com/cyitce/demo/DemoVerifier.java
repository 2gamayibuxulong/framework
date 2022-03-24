/*Copyright ©2016 TommyLemon(https://github.com/TommyLemon/APIJSON)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package com.cyitce.demo;

import apijson.RequestMethod;
import apijson.framework.APIJSONVerifier;
import apijson.orm.OnParseCallback;
import apijson.orm.SQLConfig;
import apijson.orm.SQLCreator;
import com.alibaba.fastjson.JSONObject;


/**安全校验器，校验请求参数、角色与权限等
 * 具体见 https://github.com/Tencent/APIJSON/issues/12
 * @author Lemon
 */
public class DemoVerifier extends APIJSONVerifier {
	public static final String TAG = "DemoVerifier";

	// 重写方法来自定义字段名等	
	//	@Override
	//	public String getVisitorIdKey(SQLConfig config) {
	//		return super.getVisitorIdKey(config);  // return "userid"; // return "uid" 等自定义的字段名
	//	}
	@Override
	public JSONObject verifyRequest(RequestMethod method, String name, JSONObject target, JSONObject request, int maxUpdateCount, String database, String schema, SQLCreator creator) throws Exception {
		return super.verifyRequest(method, name, target, request, maxUpdateCount, database, schema, creator);
	}

	@Override
	public JSONObject verifyResponse(RequestMethod method, String name, JSONObject target, JSONObject response, String database, String schema, SQLCreator creator, OnParseCallback callback) throws Exception {
		return super.verifyResponse(method, name, target, response, database, schema, creator, callback);
	}

	@Override
	public void verifyAdmin() throws Exception {
		super.verifyAdmin();
	}

	@Override
	public void verifyRepeat(String table, String key, Object value) throws Exception {
		super.verifyRepeat(table, key, value);
	}

	@Override
	public void verifyRepeat(String table, String key, Object value, long exceptId) throws Exception {
		super.verifyRepeat(table, key, value, exceptId);
	}

	/**
	 * 权限校验
	 */
	@Override
	public boolean verifyAccess(SQLConfig config) throws Exception {
		return super.verifyAccess(config);
	}

	@Override
	public void verifyLogin() throws Exception {
		super.verifyLogin();
	}

	@Override
	public void verifyRole(String table, RequestMethod method, String role) throws Exception {
		super.verifyRole(table, method, role);
	}
}
