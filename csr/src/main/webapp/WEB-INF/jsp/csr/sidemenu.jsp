<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<%@ taglib uri="http://web.commons.kroger.com/tlds/taglib.tld"
	prefix="kroger"%>
<stripes:layout-definition>
	<ul class="first-of-type">
		<li class="yuimenuitem first-of-type">
		<a class="yuimenubaritemlabel ${csrStateListPageCurrent}"
				href="<c:url value="/view/csr/csrstatelist.jsp"/>">
			State List</a>
		</li>
		<li class="yuimenuitem first-of-type">
		<a class="yuimenubaritemlabel ${rxExtractSearchPageCurrent}"
				href="<c:url value="/view/csr/rxextracts.jsp"/>">
			Reject Corrections</a>
		</li>
		<li class="yuimenuitem first-of-type">
		<a class="yuimenubaritemlabel ${runReportPageCurrent}"
				href="<c:url value="/view/csr/runreport.jsp"/>">
			Create CSR Report</a>
		</li>
	</ul>
</stripes:layout-definition>
