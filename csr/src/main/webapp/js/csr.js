function forceCaps(e) {

	var keynum;
	var keychar;
	var ie = false;
	
	if(window.event) { //IE
		keynum = e.keyCode;
		ie = true;
	}
	else if(e.which) { // Netscape/Firefox/Opera
		keynum = e.which;
	}
	
	if ( keynum >= 96 && keynum <= 122 ) {
		keynum = keynum - 32;
		
		if ( ie ) {
			e.keyCode = keynum;
			return e.keyCode;
		}
		else if ( !e.ctrlKey ) {
			var t = e.target;
			var v = t.value;
			var startRange = t.selectionStart;
			var endRange = t.selectionEnd;
			v = v.substring(0,startRange) + String.fromCharCode(keynum) + v.substring(endRange,v.length);
			t.value = v;
			t.setSelectionRange(startRange+1,startRange+1);
			return false;
		}
	}
	
	return true;

}

function convertToCaps(element) {
	var pattern = /[a-z]/;
	var startRange = 0;
	var endRange = 0;

	if ( pattern.test(element.value) ) {
		if ( document.selection ) {
			var pastedText = window.clipboardData.getData('Text');
			startRange = element.value.indexOf(pastedText) + pastedText.length;
			endRange = startRange;
		}
	
		element.value = element.value.toUpperCase();
		
		if ( document.selection ) {
			var range = element.createTextRange();
			range.moveStart('character', startRange);
		    range.collapse();
		    range.select();
		}
		else {
			startRange = element.selectionStart;
			endRange = element.selectionEnd;
			element.setSelectionRange(startRange,endRange);
		}
	}
}

function removeNonDigits(element) {
    //use regex to remove all non-digits (\D).
    element.value = element.value.replace(/\D/g,'');
}

function forceDigits(e) {

	var keynum;
	var keychar;
	var numcheck;
	
	if(window.event) { //IE
		keynum = e.keyCode;
	}
	else if(e.which) { // Netscape/Firefox/Opera
		keynum = e.which;
	}
	
	if ( !keynum )
		return true;
	
	if ( keynum < 32 || keynum == 127 )
		return true;
	
	numcheck = /\d/;
	keychar = String.fromCharCode(keynum);
	
	return numcheck.test(keychar);

}

function arrowScrollingKeyListener(e) {
	if ( !e )
		e = window.event;

	var keynum = e.keyCode;
	var targetElement;
	
	if ( e.target ) {
		targetElement = e.target;
	}
	else if ( e.srcElement ) {
		targetElement = e.srcElement;
	}
	if ( targetElement.nodeType == 3 ) {
		targetElement = targetElement.parentNode;
	}
	
	var targetName = targetElement.tagName.toLowerCase();
	if ( targetName == 'input' )
		return true;
	
	
	
	if ( keynum == 38 ) {
		// UP ARROW
		selectPreviousRow('extractRow','extractId','selectedId','extractView');
		return false;
	}
	else if ( keynum == 40 ) {
		// DOWN ARROW
		selectNextRow('extractRow','extractId','selectedId','extractView');
		return false;
	}
	else if ( keynum == 13 && selectedIndex > -1 ) {
		// ENTER KEY
		document.getElementById('editButton').click();
		return false;
	}
	
	return true;
}

function saveTab(action,tabName) {
	AjaxTabSaveObject.startRequest(action,tabName);
}

var AjaxTabSaveObject = {
	
	handleSuccess:function(o) {
		this.processResult(o);
	},
	
	handleFailure:function(o) {
		// failure
	},
	
	processResult:function(o) {
		// success, continue with processing
	},
	
	startRequest:function(action,tabName) {
		YAHOO.util.Connect.asyncRequest('POST', '/csr/EditRxTab.action', tabCallback, 'saveCurrentTab=yes&currentTabName='+tabName);
	}
	
};

var tabCallback = {
	success:AjaxTabSaveObject.handleSuccess,
	failure:AjaxTabSaveObject.handleFailure,
	scope: AjaxTabSaveObject
};

function buttonOver(button) {
	button.className = 'buttonHighlight';
}

function buttonOut(button) {
	button.className = '';
}

function listOver(row) {
	var classNames = row.className;
	classNames = classNames + ' highlightedRow';
	row.className = classNames;
}

function listOut(row) {
	var classNames = row.className;
	classNames = classNames.replace('highlightedRow','');
	row.className = classNames;
}

var AjaxDateRangeObject = {
	startDateFieldId:'beginDateRange',
	endDateFieldId:'endDateRange',
		
	handleSuccess:function(o) {
		this.processResult(o);
	},
	
	handleFailure:function(o) {
		// failure
	},
	
	processResult:function(o) {
		var json = eval("("+o.responseText+")");
		
		document.getElementById(startDateFieldId).value = json.startDate;
		document.getElementById(endDateFieldId).value = json.endDate;
	},
	
	startRequest:function(action,stateCode,startDateField,endDateField) {
		startDateFieldId = startDateField;
		endDateFieldId = endDateField;
		YAHOO.util.Connect.asyncRequest('POST', action, dateRangeCallback, 'calculateDateRange=yes&stateCode='+stateCode);
	}
	
};

var dateRangeCallback = {
	success:AjaxDateRangeObject.handleSuccess,
	failure:AjaxDateRangeObject.handleFailure,
	scope: AjaxDateRangeObject
};

var selectedIndex = -1;

function selectRow(row, rowIndex, idElementPrefix, hiddenIdElement) {
	selectedIndex = rowIndex;

	var selectedRows = getElementsByClassName('selectedRow',pageForm);
	for ( i=0; i<selectedRows.length; i++ ) {
		selectedRows[i].className = selectedRows[i].className.replace('selectedRow','');
	}
	
	row.className = row.className + ' selectedRow';
	
	var checkboxIdPrefix = 'selectedCheckbox';
	var checkboxIdPattern = /^selectedCheckbox[0-9]*/;
	
	var pageForm = document.getElementById('stripesForm');
	for ( i=0; i<pageForm.elements.length; i++ ) {
		var element = pageForm.elements[i];
		
		if ( checkboxIdPattern.test(element.id) ) {
			element.checked = false;
		}
	}
		
	var itemBox = document.getElementById(checkboxIdPrefix+rowIndex);
	itemBox.checked = true;
	
	var itemId = document.getElementById(idElementPrefix+rowIndex);
	var currentId = document.getElementById(hiddenIdElement);
	
	currentId.value = itemId.value;
}

function selectNextRow(rowPrefix, idElementPrefix, hiddenIdElement, divElement) {
	var nextIndex = selectedIndex + 1;
	var row = document.getElementById(rowPrefix+nextIndex);
		
	if ( row != undefined ) {
		var div = document.getElementById(divElement);
		var rowScroll = (nextIndex*row.scrollHeight);
		if ( (div.scrollTop+div.clientHeight) < (rowScroll+row.scrollHeight) || div.scrollTop > rowScroll )
			div.scrollTop = rowScroll;
			
		selectRow(row, nextIndex, idElementPrefix, hiddenIdElement);
	}
}

function selectPreviousRow(rowPrefix, idElementPrefix, hiddenIdElement, divElement) {
	var nextIndex = selectedIndex - 1;
	var row = document.getElementById(rowPrefix+nextIndex);
	
	if ( row != undefined ) {
		var div = document.getElementById(divElement);
		var rowScroll = (nextIndex*row.scrollHeight);
		if ( (div.scrollTop+div.clientHeight) < (rowScroll+row.scrollHeight) || div.scrollTop > rowScroll )
			div.scrollTop = rowScroll;
	
		selectRow(row, nextIndex, idElementPrefix, hiddenIdElement);
	}
}

function getElementsByClassName(classname, node) {
	if(!node) node = document.getElementsByTagName("body")[0];
	var a = [];
	var re = new RegExp('\\b' + classname + '\\b');
	var els = node.getElementsByTagName("*");
	for(var i=0,j=els.length; i<j; i++)
		if(re.test(els[i].className))a.push(els[i]);
	return a;
}

function trimField(element) {
	var input = element.value;
	input = YAHOO.lang.trim(input);
	element.value = input;
}
