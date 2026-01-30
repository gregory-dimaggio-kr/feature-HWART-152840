<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty sessionScope.styleUrl}">
	<c:set var="styleUrl" scope="session" value="https://supplychainsys.kroger.com/style-prod/" />
	<%
	            String jndiName = "kroger/styleUrl";
	            String styleUrl = null;
	            org.springframework.jndi.JndiTemplate jndiTemplate = new org.springframework.jndi.JndiTemplate();
	            javax.naming.Context ctx = null;
	            try
	            {
	                try
	                {
	                    ctx = jndiTemplate.getContext();
	                    Object object = ctx.lookup(jndiName);
	                    if (object != null)
	                    {
	                        styleUrl = object.toString();
	                        session.setAttribute("styleUrl", styleUrl);
	                		System.out.println("Found JNDI entry: " + styleUrl);

	                    }
	                }
	                catch (javax.naming.NamingException e)
	                {
	                	System.out.println("Could not find JNDI entry: " + e.getMessage());
	                    // e.printStackTrace();
	                }
	            }
	            finally
	            {
	                if (ctx != null)
	                {
	                    jndiTemplate.releaseContext(ctx);
	                }
	            }
	%>
</c:if>
