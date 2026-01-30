<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<%@ page import="org.springframework.security.web.WebAttributes"%>
<%@ page import="org.springframework.security.core.AuthenticationException"%>
<%@ page import="com.kroger.commons.security.spoofing.SpoofingUtil"%>
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
	pageTitle="Login" documentType="doc3" documentClass="yui-t7">
	<stripes:layout-component name="navbar">
		<ul class="first-of-type">
			<li class="yuimenubaritem first-of-type current">
				<a class="yuimenubaritemlabel" href="<c:url value="/view/public/logout.jsp"/>">Login</a> 
			</li>
		</ul>
	</stripes:layout-component>
	<stripes:layout-component name="body">
		<stripes:layout-component name="bodyAttributes">
			onload="document.spoof.login.focus()"	
		</stripes:layout-component>
		<%
		            if (!SpoofingUtil.isSpoofingAllowed())
		            {
		%>
			Your login attempt was not successful. (Spoofing is not available.)
			<p />
			Try this
<%--			<a href="<c:url value="/action/Login"/>">link</a>.--%>
		<stripes:useActionBean
				beanclass="com.kroger.pharmacy.csr.view.action.csr.config.LoginActionBean"
				id="loginActionBean" event="init" alwaysExecuteEvent="true" />

			<%
		}
		%>
			<%
			            if (SpoofingUtil.isSpoofingAllowed())
			            {
			%>
			<div class="page_message error">
			Your login attempt was not successful, try
				again.<br /> Reason: <%=session
                            .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION) != null
                        ? ((AuthenticationException) session
                            .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION))
                            .getMessage() : "No cause given."%></div>
			<%
			                    session
			                    .removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
			%>
		<form name="spoof" method="post"
			action="<c:url value="/action/SpoofLogin"/>">
			<table>
				<caption>
					Enter Spoofing Profile
				</caption>
				<tr>
					<td>
						Login:
					</td>
					<td>
						<input type="text" name="login" id="login" />
						<input type="hidden" name="password" id="password" />
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						Division:
					</td>
					<td>
						<select name="KSW_DIVNO" id="KSW_DIVNO">
							<option value="">
								Use Default Division
							</option>
							<option value="011">
								Atlanta - 011
							</option>
							<option value="021">
								Central - 021
							</option>
							<option value="014">
								Cincinnati - 014
							</option>
							<option value="625">
								CityMarkets - 625
							</option>
							<option value="025">
								Delta - 025
							</option>
							<option value="615">
								Dillon - 615
							</option>
							<option value="702">
								FM Jewelry - 702
							</option>
							<option value="704">
								Food 4 Less - 704
							</option>
							<option value="701">
								FredMeyer - 701
							</option>
							<option value="660">
								Frys - 660
							</option>
							<option value="060">
								General Office - 060
							</option>
							<option value="016">
								Great Lakes - 016
							</option>
							<option value="090">
								Jay C - 090
							</option>
							<option value="620">
								KingSooper - 620
							</option>
							<option value="672">
								Kwik Shop - 672
							</option>
							<option value="673">
								Loaf N Jug - 673
							</option>
							<option value="029">
								Mid-Atlantic - 029
							</option>
							<option value="024">
								Mid-South - 024
							</option>
							<option value="674">
								Mini Mart - 674
							</option>
							<option value="990">
								Postl Rx Svc - 990
							</option>
							<option value="705">
								QFC - 705
							</option>
							<option value="675">
								Quik Stop - 675
							</option>
							<option value="703">
								Ralphs - 703
							</option>
							<option value="706">
								Smiths - 706
							</option>
							<option value="034">
								Southwest - 034
							</option>
							<option value="671">
								Tom Thumb - 671
							</option>
							<option value="670">
								Turkey Hill - 670
							</option>
						</select>
					</td>
					<td>
						(060 is G.O.)
					</td>
				</tr>
				<tr>
					<td>
						Location:
					</td>
					<td>
						<input type="text" name="KSW_LOCATION" value="" />
					</td>
					<td>
						(Zone is first two, Store number is last 5 digits, e.g. 0K 00001)
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td align="left">
						<div class="actionButtons">
							<input type="submit" value="Submit"> 
						</div>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
			</table>
			<br />
		</form>
		<%
		}
		%>
	</stripes:layout-component>
	<stripes:layout-component name="announcement">
		<!--  no announcement -->
	</stripes:layout-component>
</stripes:layout-render>
