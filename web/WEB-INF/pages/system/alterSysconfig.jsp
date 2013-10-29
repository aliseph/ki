<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
   
</script>
<div class="pageContent">
    <form method="post" action="config/alter"
          class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent" layoutH="56">
			<dl>
				<dt>
					配置编码
				</dt>
				<dd>
					<input name="sysconfigcode" type="text" minlength="1"
						maxlength="8" size="35" readonly="readonly" value="${sysconfig.sysconfigcode}" />
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					配置名称
				</dt>
				<dd>
					<input type="text" name="sysconfigname" class="required" size="35"
						minlength="1" maxlength="25" value="${sysconfig.sysconfigname}"/>
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					配置值
				</dt>
				<dd>
					<input type="text" name="sysconfigvalue" class="required" size="35"
						minlength="1" maxlength="50" value="${sysconfig.sysconfigvalue}"/>
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					是否启用
				</dt>
				<dd>
					<select name="sfqy">
						<c:if test="${sysconfig.sfqy==1}">
							<option value="1" selected="selected">
								是
							</option>
							<option value="0">
								否
							</option>
						</c:if>
						<c:if test="${sysconfig.sfqy==0}">
							<option value="1">
								是
							</option>
							<option value="0"  selected="selected">
								否
							</option>
						</c:if>
					</select>
				</dd>
			</dl>
			
		</div>
        <div class="formBar">
            <ul>
                <!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="submit">保存</button>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="button" class="close">取消</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </form>
</div>